package com.wuzuqing.springbootdemo.book.bxwx;

import com.wuzuqing.springbootdemo.book.AbsDownload;

public class BiXiaWenXue extends AbsDownload {

    public BiXiaWenXue(String bookPath, String bookName, int startIndex, int endIndex) {
        super(bookPath, bookName, startIndex, endIndex);
    }

    @Override
    protected String initHost() {
        return "https://www.bxwx.la";
    }

    @Override
    protected boolean canParseCatalog() {
        return true;
    }
    // 鸿蒙之始  "/b/55/55097" "鸿蒙之始" 2889764  860
    //洪荒之红云大道  b/136/136524/   6919782    // 7260998
    //网游之九转轮回  b/96/96125/     4908276             5146121


    public static void main(String[] args) {
        //6562
        String path = "b/96/96125/";
        String name = "网游之九转轮回";
        int start = 4908276;
        int end = 5146121;

        AbsDownload biXiaWenXue = new BiXiaWenXue(path, name, start, end);
        biXiaWenXue.startDownload();
    }


}
