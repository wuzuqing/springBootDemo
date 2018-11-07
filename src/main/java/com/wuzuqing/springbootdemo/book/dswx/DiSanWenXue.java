package com.wuzuqing.springbootdemo.book.dswx;

import com.wuzuqing.springbootdemo.book.AbsBookJSoupHelper;
import com.wuzuqing.springbootdemo.book.AbsDownload;
import com.wuzuqing.springbootdemo.book.BookCatalog;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;

public class DiSanWenXue extends AbsDownload {

    public DiSanWenXue(String bookPath, String bookName, int startIndex, int endIndex) {
        super(bookPath, bookName, startIndex, endIndex);
    }

    @Override
    protected Elements parseCatalogTag(Document document) {
        Elements list = document.getElementsByClass("book_list");
        if (list == null) return null;
        return list.select("a[href]");
    }


    @Override
    protected String initHost() {
        return "http://www.d3zww.com/";
    }

    private static String getFirstPath() {
        return "book/85/85260/";
    }


    @Override
    protected BookCatalog createCatalog(String path, String title, int index) {
        return new BookCatalog(title, String.format("%s%s", getFirstPath(), path), index);
    }

    @Override
    protected boolean canParseCatalog() {
        return true;
    }

    @Override
    protected boolean checkPath(String path) {
        return true;
    }

    public static void main(String[] args) {
        //6562
        String path = getFirstPath();
        String name = " 网游之携美封圣";
        int start = 16686718;
        int end = 37279406;

        AbsDownload biXiaWenXue = new DiSanWenXue(path, name, start, end);
        biXiaWenXue.startDownload();
    }

    @Override
    protected AbsBookJSoupHelper createHelper(File bookFile, int index) {
        return new DSWXBookHelper(bookFile, index);
    }
}
