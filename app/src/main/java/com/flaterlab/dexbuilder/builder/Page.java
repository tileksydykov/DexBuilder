package com.flaterlab.dexbuilder.builder;

import com.flaterlab.dexbuilder.builder.components.ClassList;
import com.flaterlab.dexbuilder.builder.components.Header;

public class Page {

    public String getPage(){
        String page = "";
        return page;
    }

    public static void main(String[] args) {
        Header b = new Header("Tielk", ThemeConfig.DARK);
        System.out.println(b.toString());
    }
}
