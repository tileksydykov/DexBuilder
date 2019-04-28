package com.flaterlab.dexbuilder.builder;

import com.flaterlab.dexbuilder.builder.components.Header;

public class Tester {

    public static void main(String[] args) {
        Header b = new Header("Tielk", ThemeConfig.DARK);
        System.out.println(b.toString());
    }
}
