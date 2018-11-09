package com.wuzuqing.springbootdemo.util;

import com.wuzuqing.springbootdemo.util.test.OrcTest;
import com.wuzuqing.springbootdemo.util.youtu.ImageParse;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class YouTuTest {

    private static boolean isWorking;

    private static class ParseRunnable implements Runnable {
        private byte[] bytes;
        private String outputFilePath;

        public ParseRunnable(String file) {
            this.outputFilePath = file;
        }

        public ParseRunnable(byte[] bytes) {
            this.bytes = bytes;
        }

        public ParseRunnable() {
        }

        @Override
        public void run() {
            UsedTime.get().begin();
            if (bytes != null) {
                ImageParse.getSyncData(bytes, result -> {
                    System.out.println(result);
                    isWorking = false;
                    UsedTime.get().printUsedTime();
                });
            } else if (outputFilePath != null) {
//                ImageParse.getSyncData(outputFilePath, result -> {
//                    System.out.println(result);
//                    UsedTime.get().printUsedTime();
//                    isWorking = false;
//                });
//                String path = "f:\\cut\\a.png";
                String tesseract = OrcTest.tesseract(outputFilePath);
                String[] split = tesseract.split("  ");
                System.out.println(Arrays.toString(split));
                UsedTime.get().printUsedTime();
                isWorking = false;
            }
        }
    }


    public static class TimerTaskTest03 extends TimerTask {
        private static int x = 618;
        private static int y = 80;
        private static String orightColor = "#275881";

        @Override
        public void run() {
            if (isWorking) {
                return;
            }
            File file = getScreenPixel(x, y, orightColor, 2);
            if (file != null) {
                isWorking = true;
                new Thread(new ParseRunnable(file.getAbsolutePath())).start();
            }
        }
    }


    //图片到byte数组
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTaskTest03(), 100, 500);

    }


    /**
     * 转换BufferedImage 数据为byte数组
     *
     * @param bImage Image对象
     * @param format image格式字符串.如"gif","png"
     * @return byte数组
     */
    private static byte[] imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static <T> T getScreenPixel(int x, int y, String color, int type) { // 函数返回值为颜色的RGB值。
        Robot rb; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
        try {
            rb = new Robot();
            Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
            Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
            Rectangle rec = new Rectangle(0, 0, 640, 480);
//            Rectangle rec = new Rectangle(0, 0, di.width, di.height);
            BufferedImage bi = rb.createScreenCapture(rec);
            int pixelColor = bi.getRGB(x, y);
            String hexString = "#" + Integer.toHexString(pixelColor).substring(2);
            if (type == 1) {
                return (T) hexString;
            }
            System.out.println(hexString);
            if (color.equals(hexString)) {
                BufferedImage subimage = bi.getSubimage(119, 289, 380, 100);
                File file = new File("f:\\cut\\cut1.png");
                if (type == 2) {
                    ImageIO.write(subimage, "png", file);
                    return (T) file;
                } else if (type == 3) {
                    return (T) imageToBytes(subimage, "png");
                }
            }
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
 
 
