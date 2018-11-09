package com.wuzuqing.springbootdemo.util.test;

import com.wuzuqing.springbootdemo.util.UsedTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zwp
 * @version 2.0
 * @date 创建时间：2017年3月24日 下午5:12:12
 * @parameter
 * @return
 * @since
 */
public class OrcTest {


    private final String EOL = System.getProperty("line.separator");


    /**
     * @param imageFile 传入的图像文件
     * @return 识别后的字符串
     */
    public String recognizeText(File imageFile) throws Exception {

        File outputFile = new File(imageFile.getParentFile(), "result");
        StringBuffer strB = new StringBuffer();
        List<String> cmd = new ArrayList<>();

        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            cmd.add("tesseract");
        } else {
            cmd.add("tesseract");
        }
        cmd.add(imageFile.getName());
        cmd.add(outputFile.getName());
        cmd.add("digits");
//	      cmd.add("chi_sim");
//	        cmd.add("eng");
//        cmd.add("-psm 7");
        ProcessBuilder pb = new ProcessBuilder();


        pb.directory(imageFile.getParentFile());
        pb.command(cmd);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        /**
         * the exit value of the process. By convention, 0 indicates normal
         * termination.
         */
//        System.out.println(cmd.toString());
        int w = process.waitFor();
        if (w == 0)// 0代表正常退出
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),
                    "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files. There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recognize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString();
    }


    public static String tesseract(String fileString)  {
        File file = new File(fileString);
        String recognizeText = null;
        try {
            recognizeText = new OrcTest().recognizeText(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(recognizeText);
        return recognizeText;
    }

    public static void main(String[] args) throws Exception {
        UsedTime.get().begin();
        String file = "F:\\cut\\cut1.png";
        tesseract(file);
        UsedTime.get().printUsedTime();
    }
//
//    public static String test(String path) {
//        File imageFile = new File(path);
//
//        Tesseract instance = Tesseract.getInstance();
//        instance.setDatapath("C:\\Program Files (x86)\\Tesseract-OCR\\tessdata");//设置训练库的位置
////        instance.setLanguage("chi_sim");//中文识别
//        instance.setLanguage("eng");//中文识别
//        try {
//            String result = instance.doOCR(imageFile);
//            System.out.println(result);
//            return result;
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        return "";

//    }

}