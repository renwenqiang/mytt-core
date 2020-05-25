package com.boot.mytt.core.util;

import com.boot.mytt.core.exception.UtilException;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * 二维码生成工具
 *
 * @author wuxj
 */
@Slf4j
public class ZxingUtils {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "jpg";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    private ZxingUtils() {

    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param qrName       二维码名称
     * @param content      内容
     * @param imgPath      LOGO地址
     * @param destPath     存放目录
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static String encode(String qrName, String content, String imgPath, String destPath, boolean needCompress) {
        try {
            BufferedImage image = createImage(content, imgPath, needCompress);
            FileUtils.forceMkdir(new File(destPath));
            String destFileName = qrName + "." + FORMAT_NAME;
            String destFileFullPath = destPath + "/" + destFileName;
            ImageIO.write(image, FORMAT_NAME, new File(destFileFullPath));
            return destFileFullPath;
        } catch (Exception e) {
            throw new UtilException("ZxingUtil encode fail", e);
        }
    }

    /**
     * 生成二维码
     *
     * @param qrName   二维码文件名
     * @param content  内容
     * @param destPath 保存地址
     * @return
     * @throws Exception
     */
    public static String encode(String qrName, String content, String destPath) {
        try {
            BufferedImage image = createImage(content, "", false);
            FileUtils.forceMkdir(new File(destPath));
            String destFileName = qrName + "." + FORMAT_NAME;
            String destFileFullPath = destPath + "/" + destFileName;
            ImageIO.write(image, FORMAT_NAME, new File(destFileFullPath));
            return destFileFullPath;
        } catch (Exception e) {
            throw new UtilException("ZxingUtil encode fail", e);
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content      内容
     * @param imgPath      LOGO地址
     * @param output       输出流
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress) {
        try {
            BufferedImage image = createImage(content, imgPath, needCompress);
            ImageIO.write(image, FORMAT_NAME, output);
        } catch (Exception e) {
            throw new UtilException("ZxingUtil encode fail", e);
        }
    }

    /**
     * 生成二维码图片
     *
     * @param content      内容
     * @param imgPath      logo地址
     * @param needCompress 是否压缩
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) {
        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            if (StringUtils.isEmpty(imgPath)) {
                return image;
            }
            // 插入图片
            insertImage(image, imgPath, needCompress);
            return image;
        } catch (Exception e) {
            throw new UtilException("ZxingUtil create image fail", e);
        }
    }

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param imgPath      LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) {
        try {
            File file = new File(imgPath);
            if (!file.exists()) {
                log.debug("" + imgPath + "   该文件不存在！");
                return;
            }
            Image src = ImageIO.read(new File(imgPath));
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            if (needCompress) { // 压缩LOGO
                if (width > WIDTH) {
                    width = WIDTH;
                }
                if (height > HEIGHT) {
                    height = HEIGHT;
                }
                Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(image, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                src = image;
            }
            // 插入LOGO
            Graphics2D graph = source.createGraphics();
            int x = (QRCODE_SIZE - width) / 2;
            int y = (QRCODE_SIZE - height) / 2;
            graph.drawImage(src, x, y, width, height, null);
            Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
            graph.setStroke(new BasicStroke(3f));
            graph.draw(shape);
            graph.dispose();
        } catch (Exception e) {
            throw new UtilException("ZxingUtil insert image fail", e);
        }
    }


    /**
     * 解析二维码
     *
     * @param path 二维码图片地址
     * @return
     * @throws Exception
     */
    public static String decode(String path) {
        return decode(new File(path));
    }

    /**
     * 解析二维码
     *
     * @param file 二维码图片
     * @return
     * @throws Exception
     */
    public static String decode(File file) {
        try {
            BufferedImage image;
            image = ImageIO.read(file);
            if (image == null) {
                return null;
            }
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result;
            Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            throw new UtilException("ZxingUtil decode fail");
        }
    }
}
