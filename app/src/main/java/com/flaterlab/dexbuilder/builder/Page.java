package com.flaterlab.dexbuilder.builder;

import com.flaterlab.dexbuilder.builder.components.Body;
import com.flaterlab.dexbuilder.builder.components.Title;

import java.util.HashMap;

public class Page {

    public String getPage(){
        String page = "";
        return page;
    }

    public static void main(String[] args) {
        Title b = new Title();
        String n = b.getH1("himik");
        System.out.println(n);
    }
}
