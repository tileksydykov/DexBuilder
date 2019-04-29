package com.flaterlab.dexbuilder.builder;

import com.flaterlab.dexbuilder.R;
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

    public Page(){ }

    public Page(int theme) {
        this.theme = theme;
        if (header == null){
            name = "Sample";
        }
        this.header = new Header(name, theme).toString();
    }

    public Page(int theme,String nameOfPage) {
        name = nameOfPage;
        this.theme = theme;
    }

    public void setName(String nameOfPage) {
        this.name = nameOfPage;
    }

    public void refreshHeader() {
        if (name == null){
            name = "Sample";
        }
        this.header = new Header(name, theme).getHeader();
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    private void addToPage(String html){
        main = new StringBuilder()
                .append(main)
                .append(html)
                .toString();
    }

    public String getPage(String name){
        Jumbotron n = new Jumbotron();
        n.setTitle(name);
        n.setButton("Sample");
        n.setText("This is your first page!!! Enjoy!");
        refreshHeader();
        addToPage(header);
        addToPage(n.toString());
        return main;
    }
    public String getJumbotronSample(String name){
        setName(name);
        Jumbotron n = new Jumbotron();
        n.setTitle(name);
        n.setButton("Sample");
        n.setText("This is your first page!!! Enjoy!");
        refreshHeader();
        addToPage(header);
        addToPage(n.toString());
        return main;
    }

    public void setHeader(Header h){
        addToPage(h.toString());
    }

    public void setJumbotron(Jumbotron j){
        addToPage(j.toString());
    }

    public void addSection(Section s){
        addToPage(s.toString());
    }

    public String getPage(){
        return main;
    }
}
