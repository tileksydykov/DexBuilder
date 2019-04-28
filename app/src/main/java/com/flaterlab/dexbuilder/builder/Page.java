package com.flaterlab.dexbuilder.builder;

import com.flaterlab.dexbuilder.builder.components.Header;
import com.flaterlab.dexbuilder.builder.components.Jumbotron;
import com.flaterlab.dexbuilder.builder.components.Section;

import java.util.ArrayList;

public class Page {

    private String header;
    private String footer;
    private String name;
    private int theme = ThemeConfig.DARK;
    private String body;

    private String main = "";

    private ArrayList<Section> sections;

    public Page(){

    }

    public Page(int theme) {
        this.theme = theme;
        if (header == null){
            name = "Sample";
        }
        this.header = new Header(name, theme).toString();
    }

    public Page(int theme,String nameOfPage) {
        this.theme = theme;
    }

    public void setName(String nameOfPage) {
        this.name = nameOfPage;
    }

    public void refreshHeader() {
        if (header == null){
            name = "Sample";
        }
        this.header = new Header(name, theme).toString();
    }

    private void addToPage(String html){
        main = new StringBuilder()
                .append(main)
                .append(html)
                .toString();
    }

    public String getPage(){
        Jumbotron n = new Jumbotron();
        n.setTitle("tielk");
        n.setButton("get");
        n.setText("himifrhgu");
        refreshHeader();
        addToPage(header);
        addToPage(n.toString());
        return main;
    }
}
