package com.flaterlab.dexbuilder.builder;

import com.flaterlab.dexbuilder.builder.components.Body;

import java.util.HashMap;

public class Page {

    public String getPage(){
        String page = "";
        return page;
    }

    public static void main(String[] args) {
        Body b = new Body();
        String n = b.render("tielk @@@himik@@@", new HashMap<String, String>());
        System.out.println(n);
    }
}
