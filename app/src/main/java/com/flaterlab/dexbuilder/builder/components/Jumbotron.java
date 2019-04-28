package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;
import com.flaterlab.dexbuilder.builder.ThemeConfig;

public class Jumbotron extends Base {

    private String title;

    private String text;
    private String button;

    public Jumbotron() {
        setTemplate(Resourses.CONTAINER_SCOBE);
        addClass("jumbotron");
    }

    public void setTitle(String title) {
        this.title = new Title(title, ThemeConfig.MAIN_TITLE).toString();
    }

    public void setText(String text) {
        this.text = new Paragraph(text).toString();
    }

    public void setButton(String button) {
        Button m = new Button(button);
        m.setButtonLink("/");
        this.button = m.toString();
    }

    @Override
    public String toString() {
        bodyAppend(title);
        bodyAppend(text);
        bodyAppend(button);
        return render();
    }
}
