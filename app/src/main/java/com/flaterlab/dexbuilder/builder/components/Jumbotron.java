package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;
import com.flaterlab.dexbuilder.builder.ThemeConfig;

public class Jumbotron extends HTMLObject {

    private String title;

    private String text;
    private String button;
    private String buttonLink = "/";
    private String buttonStyle = "";

    public Jumbotron() {
        setTemplate(Resourses.CONTAINER_SCOBE);
        addClass("jumbotron");
    }

    public void setTitle(String title) {
        Title m = new Title(title, ThemeConfig.MAIN_TITLE);
        m.addClass("display-3");
        this.title = m.toString();
    }

    public void setButtonLink(String buttonLink) {
        this.buttonLink = buttonLink;
    }

    public void setButtonStyle(String buttonStyle) {
        this.buttonStyle = buttonStyle;
    }

    public void setText(String text) {
        this.text = new Paragraph(text).toString();
    }

    public void setButton(String button) {
        Button m = new Button(button);
        m.setButtonLink(buttonLink);
        m.setButtonStyle(buttonStyle);
        this.button = new Paragraph(m.toString()).toString();
    }

    @Override
    public String toString() {
        Container m = new Container();
        m.bodyAppend(title);
        m.bodyAppend(text);
        m.bodyAppend(button);
        bodyAppend(m.render());
        return render();
    }
}
