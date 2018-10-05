package com.wuzuqing.springbootdemo.book;

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
        String path = "/b/3/3465/";
        String name = "神话版三国";
        int start = 1746132;
        int end = 2216379;

        AbsDownload biXiaWenXue = new BiXiaWenXue(path, name, start, end);
        biXiaWenXue.startDownload();
    }


}
