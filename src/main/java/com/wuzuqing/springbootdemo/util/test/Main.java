//package com.wuzuqing.springbootdemo.util.test;
//
//import org.opencv.core.*;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.objdetect.CascadeClassifier;
//
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReadParam;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.FileImageInputStream;
//import javax.imageio.stream.ImageInputStream;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.Arrays;
//
//
//public class Main {
//    private static final Double avatarSpacePer = 0.16;
//    private static final Double avatarPer = 0.28;
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        /*
//         * System.loadLibrary("opencv_java320"); Mat m = Mat.eye(3, 3,
//         * CvType.CV_8UC1); System.out.println("m = " + m.dump());
//         */
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        /*
//         * Mat mat = Imgcodecs.imread("d:/data/1.jpg"); System.out.println(mat);
//         */
//        File file = new File("f:/cut");
//        if (file.isDirectory()) {
//            File[] files = file.listFiles();
//            for (File f : files) {
//                test(f.getPath());
//            }
//        }
//        // test("d:/data/1.png");
//    }
//
//    private static void test(String fileName) {
//        int index = fileName.lastIndexOf("\\");
//        String suffix = index != -1 ? fileName.substring(index + 1) : ".png";
//        int[] rectPosition = detectFace(fileName);
//        System.out.println("x=" + rectPosition[0] + " y=" + rectPosition[1] + " width=" + rectPosition[2] + " height="
//                + rectPosition[3]);
//
//        int x = rectPosition[0];
//        int y = rectPosition[1];
//        int w = rectPosition[2];
//        int h = rectPosition[3];
//        int[] imgRect = new int[2];
//        try {
//            imgRect = getImageWidth(fileName);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        /*
//         * if(x == 0 || y == 0 || w == 0 || h == 0 ){
//         * System.out.println("人脸识别失败:" + fileName + " 把身份证变成黑白照片再次进行识别");
//         * String destFile = "d:/data/temp" + suffix;
//         * //convertBackWhiteImage(fileName, destFile); changeImge(new
//         * File(fileName)); rectPosition = detectFace(fileName); // rectPosition
//         * = detectFace(destFile); System.out.println("x=" + rectPosition[0] +
//         * " y=" + rectPosition[1] + " width=" + rectPosition[2] + " height=" +
//         * rectPosition[3]);
//         *
//         * x = rectPosition[0]; y = rectPosition[1]; w = rectPosition[2]; h =
//         * rectPosition[3]; }
//         */
//        if (x == 0 || y == 0 || w == 0 || h == 0) {
//
//            x = imgRect[0];
//            y = imgRect[1];
//            w = (int) (x * avatarPer);
//            h = w;
//            System.out.println("人脸识别失败:" + fileName + " 采用默认识别");
//
//        }
//        /*
//         * int cutX = x/4 + 20; int cutY = (y + h ) *2;
//         */
//        int cutX = x / 2 - 20;
//
//        String destFile = "d:/data/idcard" + suffix;
//
//        int width = imgRect[0] - cutX - 30;
//        // ImageUtils.cut(fileName, destFile, cutX, cutY, width/2, 120);
//        // ImageUtils.cut(fileName, "d:/data/avatar.jpg", x, y, w, h);
//        int cutHeight = 140;
//        int imgHieght = imgRect[1];
//        int topSpace = (int) (imgHieght * avatarPer);
//        int height = (int) (imgHieght * avatarSpacePer);
//        int cutY = y + h + height;
//        if (imgHieght - cutY < cutHeight - 5) {
//            cutY = imgHieght - (int) (imgHieght / 3.8) - 10;
//        }
//        if (imgHieght < 500) {
//            cutY = cutY + 20;
//        }
//        new Main().cutImage(fileName, destFile, cutX, cutY, width, cutHeight);
//        OrcTest.test(destFile);
//    }
//
//
//    /**
//     * 图片转换成黑白图片
//     *
//     * @param fileName
//     * @param destFile
//     */
//    public static void convertBackWhiteImage(String fileName, String destFile) {
//        OutputStream output = null;
//        String suffix = null;
//        String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
//        // 获取图片后缀
//        if (fileName.indexOf(".") > -1) {
//            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        } // 类型和图片后缀全部小写，然后判断后缀是否合法
//        if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
//            return;
//        }
//        try {
//            BufferedImage sourceImg = replaceWithWhiteColor(ImageIO.read(new FileInputStream(fileName)));
//            output = new FileOutputStream(destFile);
//            ImageIO.write(sourceImg, suffix, output);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            if (output != null)
//                try {
//                    output.close();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//        }
//    }
//
//    /**
//     * 把图像处理成黑白照片
//     *
//     * @param bi
//     * @return
//     */
//    public static BufferedImage replaceWithWhiteColor(BufferedImage bi) {
//
//        int[] rgb = new int[3];
//
//        int width = bi.getWidth();
//
//        int height = bi.getHeight();
//
//        int minx = bi.getMinX();
//
//        int miny = bi.getMinY();
//
//        /**
//         *
//         * 遍历图片的像素，为处理图片上的杂色，所以要把指定像素上的颜色换成目标白色 用二层循环遍历长和宽上的每个像素
//         *
//         */
//
//        int hitCount = 0;
//
//        for (int i = minx; i < width - 1; i++) {
//
//            for (int j = miny; j < height; j++) {
//
//                /**
//                 *
//                 * 得到指定像素（i,j)上的RGB值，
//                 *
//                 */
//
//                int pixel = bi.getRGB(i, j);
//
//                int pixelNext = bi.getRGB(i + 1, j);
//
//                /**
//                 *
//                 * 分别进行位操作得到 r g b上的值
//                 *
//                 */
//
//                rgb[0] = (pixel & 0xff0000) >> 16;
//
//                rgb[1] = (pixel & 0xff00) >> 8;
//
//                rgb[2] = (pixel & 0xff);
//
//                /**
//                 *
//                 * 进行换色操作，我这里是要换成白底，那么就判断图片中rgb值是否在范围内的像素
//                 *
//                 */
//
//                // 经过不断尝试，RGB数值相互间相差15以内的都基本上是灰色，
//
//                // 对以身份证来说特别是介于73到78之间，还有大于100的部分RGB值都是干扰色，将它们一次性转变成白色
//
//                if ((Math.abs(rgb[0] - rgb[1]) < 15)
//
//                        && (Math.abs(rgb[0] - rgb[2]) < 15)
//
//                        && (Math.abs(rgb[1] - rgb[2]) < 15) &&
//
//                        (((rgb[0] > 73) && (rgb[0] < 78)) || (rgb[0] > 100))) {
//
//                    // 进行换色操作,0xffffff是白色
//
//                    bi.setRGB(i, j, 0xffffff);
//
//                }
//
//            }
//
//        }
//
//        return bi;
//
//    }
//
//    public static int[] getImageWidth(String fileName) throws FileNotFoundException, IOException {
//        File picture = new File(fileName);
//        BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
//        int[] imgRect = new int[2];
//        imgRect[0] = sourceImg.getWidth();
//        imgRect[1] = sourceImg.getHeight();
//        return imgRect;
//    }
//
//    public static int[] detectFace(String fileName) {
//        int[] rectPosition = new int[4];
//        CascadeClassifier faceDetector = new CascadeClassifier("./data/lbpcascade_frontalface.xml");
//        Mat image = Imgcodecs.imread(fileName);
//        MatOfRect faceDetections = new MatOfRect();
//        Size minSize = new Size(120, 120);
//
//        Size maxSize = new Size(250, 250);
//        faceDetector.detectMultiScale(image, faceDetections, 1.1f, 4, 0, minSize, maxSize);
//
//        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
//        for (Rect rect : faceDetections.toArray()) {
//            System.out.println(rect.toString());
//            rectPosition[0] = rect.x;
//            rectPosition[1] = rect.y;
//            rectPosition[2] = rect.width;
//            rectPosition[3] = rect.height;
//        }
//
//        /*
//         * String filename = "d:/data/avatar.jpg";
//         * System.out.println(String.format("Writing %s", filename));
//         * Imgcodecs.imwrite(filename, image);
//         */
//        return rectPosition;
//    }
//
//    /**
//     * <p>
//     * Title: cutImage
//     * </p>
//     * <p>
//     * Description: 根据原图与裁切size截取局部图片
//     * </p>
//     *
//     * @param srcImg  源图片
//     * @param destImg 图片输出流
//     * @param rect    需要截取部分的坐标和大小
//     */
//    public void cutImage(File srcImg, File destImg, java.awt.Rectangle rect) {
//        if (srcImg.exists()) {
//            java.io.FileInputStream fis = null;
//            ImageInputStream iis = null;
//            OutputStream output = null;
//            try {
//                fis = new FileInputStream(srcImg);
//                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
//                // JPEG, WBMP, GIF, gif]
//                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
//                String suffix = null;
//                // 获取图片后缀
//                if (srcImg.getName().indexOf(".") > -1) {
//                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
//                } // 类型和图片后缀全部小写，然后判断后缀是否合法
//                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
//                    return;
//                }
//                // 将FileInputStream 转换为ImageInputStream
//                iis = ImageIO.createImageInputStream(fis);
//                // 根据图片类型获取该种类型的ImageReader
//                ImageReader reader = ImageIO.getImageReaders(new FileImageInputStream(srcImg)).next(); // ImageIO.getImageReadersBySuffix(suffix).next();
//                reader.setInput(iis, true);
//                ImageReadParam param = reader.getDefaultReadParam();
//                param.setSourceRegion(rect);
//                BufferedImage bi = reader.read(0, param);
//                output = new FileOutputStream(destImg);
//                ImageIO.write(bi, suffix, output);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (fis != null)
//                        fis.close();
//                    if (iis != null)
//                        iis.close();
//                    if (output != null)
//                        output.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//        }
//    }
//
//    public void cutImage(String srcImg, String destImg, int x, int y, int width, int height) {
//        cutImage(new File(srcImg), new File(destImg), new java.awt.Rectangle(x, y, width, height));
//    }
//
//}