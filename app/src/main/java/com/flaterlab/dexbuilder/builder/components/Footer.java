package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Footer extends HTMLObject {
    public Footer(){
        setTemplate(Resourses.FOOTER);
        addClass("footer");
    }

    public Footer(String body) {
        setTemplate(Resourses.FOOTER);
        bodyAppend(body);
        addClass("footer");

    }
    public Footer(String body, ClassList classes){
        setTemplate(Resourses.FOOTER);
        bodyAppend(body);
        classes.add("footer");
        addClass("footer");
    }

    public String getStandartFooter(String userName){
        Container c = new Container();
        Span text = new Span("Made by "+ userName);
        text.addClass("text-muted");
        c.bodyAppend(text.render());

        bodyAppend(c.render());

        return render();
    }
}
