package com.boot.mytt.core.util;

import com.boot.mytt.core.exception.UtilException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class ZipUtils {

    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * 压缩成ZIP 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构; false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    public static void toZip(String srcDir, OutputStream out, boolean keepDirStructure) {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), keepDirStructure);
        } catch (Exception e) {
            throw new UtilException("zip error from ZipUtil", e);
        }
        long end = System.currentTimeMillis();
        log.debug("压缩完成，耗时：" + (end - start) + " ms");
    }

    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     */

    public static void toZip(List<File> srcFiles, OutputStream out) {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                try (FileInputStream in = new FileInputStream(srcFile)) {
                    while ((len = in.read(buf)) != -1) {
                        zos.write(buf, 0, len);
                    }
                    zos.closeEntry();
                } catch (Exception e) {
                    throw new UtilException("zip error from ZipUtil", e);
                }
            }
        } catch (Exception e) {
            throw new UtilException("zip error from ZipUtil", e);
        }
        long end = System.currentTimeMillis();
        log.debug("压缩完成，耗时：" + (end - start) + " ms");
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean keepDirStructure) {
        try (FileInputStream in = new FileInputStream(sourceFile)) {
            byte[] buf = new byte[BUFFER_SIZE];
            if (sourceFile.isFile()) {
                // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
                zos.putNextEntry(new ZipEntry(name));
                // copy文件到zip输出流中
                int len;
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
            } else {
                File[] listFiles = sourceFile.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
                    if (keepDirStructure) {
                        // 空文件夹的处理
                        zos.putNextEntry(new ZipEntry(name + "/"));
                    }
                } else {
                    for (File file : listFiles) {
                        // 判断是否需要保留原来的文件结构
                        if (keepDirStructure) {
                            // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                            // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                            compress(file, zos, name + "/" + file.getName(), keepDirStructure);
                        } else {
                            compress(file, zos, file.getName(), keepDirStructure);
                        }
                    }
                }
            }
            zos.closeEntry();
        } catch (Exception e) {
            throw new UtilException("zip error from ZipUtil", e);
        }
    }
}
