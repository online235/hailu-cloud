package com.hailu.cloud.api.mall.module.goods.tool;

public class HtmlReplace {

    public static String htmlReplace(String str) {
        if (str.indexOf("&le;") != -1) {
            str = str.replace("&le;", "≤");
            return str;
        }
        if (str.indexOf("&ge;") != -1) {
            str = str.replace("&ge;", "≥");
            return str;
        }
        if (str.indexOf("&plusmn;") != -1) {
            str = str.replace("&plusmn;", "±");
            return str;
        }
        return str;
    }

}
