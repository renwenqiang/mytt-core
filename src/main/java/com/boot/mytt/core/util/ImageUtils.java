package com.boot.mytt.core.util;

import com.boot.mytt.core.exception.UtilException;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class ImageUtils {

    /**
     * 开始压缩图片
     *
     * @param path 图片路径
     * @return 压缩后的图片路径
     */
    public static String imageZipDefault(String path) {
        String target = path + "target";
        File srcfile = new File(path);
        log.debug("用户自拍照压缩前自拍照大小:" + srcfile.length());
        imageZip(path, target, 888, 888, null);
        File distfile = new File(target);
        log.debug("用户自拍照压缩后自拍照大小:" + distfile.length());
        return target;
    }

    /**
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
     *
     * @param imgSrc     源图片地址
     * @param imgDist    目标图片地址
     * @param widthDist  压缩后图片宽度（当rate==null时，必传）
     * @param heightDist 压缩后图片高度（当rate==null时，必传）
     * @param rate       压缩比例
     */
    public static void imageZip(String imgSrc, String imgDist, int widthDist, int heightDist, Float rate) {
        try (FileOutputStream out = new FileOutputStream(imgDist)) {
            File srcfile = new File(imgSrc);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }

            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                Integer[] results = getImgWidth(srcfile);
                if (!ArrayUtils.isNotEmpty(results) || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthDist = (int) (results[0] * rate);
                    heightDist = (int) (results[1] * rate);
                }
            }

            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage(widthDist, heightDist, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src.getScaledInstance(widthDist, heightDist, Image.SCALE_SMOOTH), 0, 0, null);
            float per = (float) 0.85;
            saveAsJPEG(100, tag, per, out);
        } catch (Exception e) {
            throw new UtilException("ImageUtil reduceImg fail", e);
        }
    }

    /**
     * 以JPEG编码保存图片
     *
     * @param dpi         分辨率
     * @param imageToSave 要处理的图像图片
     * @param compression 压缩比
     * @param fos         文件输出流
     * @throws IOException
     */
    private static void saveAsJPEG(Integer dpi, BufferedImage imageToSave, float compression, FileOutputStream fos) {
        try (ImageOutputStream ios = ImageIO.createImageOutputStream(fos)) {
            ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
            imageWriter.setOutput(ios);
            IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(imageToSave), null);
            if (!Objects.isNull(dpi)) {
                //new metadata
                Element tree = (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
                Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0);
                jfif.setAttribute("Xdensity", Integer.toString(dpi));
                jfif.setAttribute("Ydensity", Integer.toString(dpi));
            }

            if (compression >= 0 && compression <= 1f) {
                JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
                jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
                jpegParams.setCompressionQuality(compression);

            }

            imageWriter.write(imageMetaData, new IIOImage(imageToSave, null, null), null);
            imageWriter.dispose();
        } catch (Exception e) {
            throw new UtilException("ImageUtil save jpeg fail", e);
        }
    }

    /**
     * 获取图片宽度
     *
     * @param file 图片文件
     * @return 宽度
     */
    private static Integer[] getImgWidth(File file) {
        BufferedImage src = null;
        Integer result[] = {0, 0};
        try (InputStream is = new FileInputStream(file);) {
            src = ImageIO.read(is);
            // 得到源图宽
            result[0] = src.getWidth(null);
            // 得到源图高
            result[1] = src.getHeight(null);
        } catch (Exception e) {
            throw new UtilException("ImageUtil get image width fail", e);
        }
        return result;
    }
}