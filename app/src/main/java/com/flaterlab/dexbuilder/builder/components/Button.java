package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Button extends HTMLObject {
    private String link = "";
    private String text;
    private String type;

    public Button(String text) {
        setTemplate(Resourses.BUTTON);
        addClass("btn");
        this.text = text;
    }

    public void setButtonLink(String link) {
        this.link = link;
    }

    public void setButtonStyle(String buttonStyle) {
        addClass(buttonStyle);
    }

    public void setType(String type) {
        map.put("type", type);
        this.type = type;
    }

    @Override
    public String toString() {
        bodyAppend(text);
        setType("button");
        if (link.equals("")){
            return render();
        }else{
            return new Link(render(), link).render();
        }
    }
}
