package com.wuzuqing.springbootdemo.book;

import com.wuzuqing.springbootdemo.book.bxwx.BXWXBookHelper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public abstract class AbsDownload {
    protected String host;
    protected String saveParent = "d://book/";
    protected String bookPath;
    private static int startIndex;
    private int endIndex;
    private int MAX_POOL = 15;
    private File bookFile;
    private String bookName;
    private static int endCount;
    private boolean isHeBinging = false;
    protected TreeMap<String, BookCatalog> bookCatalogs;


    /**
     * @param bookPath   路径
     * @param bookName   书的名称
     * @param startIndex 开始的位置
     * @param endIndex   最后一张的位置
     */
    public AbsDownload(String bookPath, String bookName, int startIndex, int endIndex) {
        this.host = initHost();
        this.bookPath = bookPath;
        this.bookName = bookName;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    protected abstract String initHost();

    protected abstract boolean canParseCatalog();

    protected Elements parseCatalogTag(Document document) {
        Element list = document.getElementById("list");
        if (list == null) return null;
        return list.select("a[href]");
    }

    private Runnable downloadTask = () -> {
        AbsBookJSoupHelper helper;
        Document doc;
        while (!bookCatalogs.isEmpty()) { //判断是否结束
            helper = createHelper();
            try {
                System.out.println(helper.getUrl());
                doc = Jsoup.connect(helper.getUrl()).get();
                if (doc != null) {
                    helper.setDocument(doc).startAnaylizeByJsoup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        endCount++;
        if (endCount >= MAX_POOL) { //是否最后一个线程结束
            heBinging();
        }
    };

    public void startDownload() {
        if (bookFile == null) {
            bookFile = new File(saveParent + bookName);
            bookFile.mkdirs();
        } else if (!bookFile.exists()) {
            bookFile.mkdirs();
        }

        bookCatalogs = new TreeMap<>();
        if (canParseCatalog()) {
            downBookCatalog();
        } else {
            defaultCatalog();
        }
        start();
    }

    protected void start() {
        if (bookCatalogs.isEmpty()) return;
        isHeBinging = false;
        endCount = 0;
        startTime = System.currentTimeMillis();
        System.out.println("开始下载 : " + startTime);
        for (int i = 0; i < MAX_POOL; i++) {
            new Thread(downloadTask).start();
        }
    }

    private long startTime;

    /**
     * 下载目录
     */
    private void downBookCatalog() {
        JSoupHelper helper = new JSoupHelper() {
            private int index = 1;

            @Override
            public Elements getRootElements(Document document) {
                return parseCatalogTag(document);
            }

            @Override
            public void anaylizeRootElement(Element rootElement) {
                String path = rootElement.attr("href");
                String title = rootElement.text();
                if (checkPath(path)) {
                    bookCatalogs.put(path, createCatalog(path, title, index));
                    index++;
                }
            }
        };

        try {
            Document document;
            File file = new File("d://book/" + bookName + ".html");
            if (file.exists()) {
                document = Jsoup.parse(file, "utf-8");
                helper.setDocument(document).startAnaylizeByJsoup();
            } else {
                String url = String.format("%s%s", host, bookPath);
                Connection connect = Jsoup.connect(url);
                document = connect.get();
            }
            helper.setDocument(document).startAnaylizeByJsoup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean checkPath(String path) {
        return !bookCatalogs.containsKey(path) && path.contains(bookPath);
    }

    private void defaultCatalog() {
        int index = 1;
        for (int i = startIndex; i <= endIndex; i++) {
            String path = String.format("%s/%d.html", bookPath, i);
            bookCatalogs.put(path, createCatalog(path, String.format("第%d章", index), index));
            index++;
        }
    }


    protected BookCatalog createCatalog(String path, String title, int index) {
        return new BookCatalog(title, path, index);
    }


    /**
     * url 拼接格式  host - path - index - .html
     *
     * @return 解析器
     */
    private synchronized AbsBookJSoupHelper createHelper() {
        BookCatalog bookCatalog = bookCatalogs.remove(bookCatalogs.firstKey());
        AbsBookJSoupHelper helper = createHelper(bookFile, bookCatalog.getIndex());
        helper.setUrl(String.format("%s%s", host, bookCatalog.getPath()));
        return helper;
    }

    protected AbsBookJSoupHelper createHelper(File bookFile, int index) {
        return new BXWXBookHelper(bookFile, index);
    }

    private synchronized void heBinging() {  //文件合并
        if (isHeBinging) return;
        isHeBinging = true;
        File[] files = bookFile.listFiles();
        if (files == null || files.length == 0) return;
        File endFile = new File(bookFile, bookName + ".txt");
        if (endFile.exists()) { //如果文件存在则删除
            endFile.delete();
        }

        List<File> fileList = new ArrayList<>(Arrays.asList(files));
        Collections.sort(fileList, Comparator.comparingInt(f -> Integer.parseInt(f.getName())));
        FileOutputStream fileOutputStream;
        try {
            endFile.createNewFile();
            fileOutputStream = new FileOutputStream(endFile, true);
            int readLen;
            byte[] buff = new byte[4096];
            for (File file : fileList) {
                FileInputStream stream = new FileInputStream(file);
                while ((readLen = stream.read(buff)) != -1) {
                    fileOutputStream.write(buff, 0, readLen);
                }
                stream.close();
                file.delete();
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            fileOutputStream = null;

            System.out.println("下载结束 use : " + ((System.currentTimeMillis() - startTime) / 1000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCatalog() {
        if (bookCatalogs != null)
            System.out.println("目录:" + bookCatalogs.toString());
    }

}
