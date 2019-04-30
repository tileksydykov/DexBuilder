package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Link extends HTMLObject {

    public Link(String body, ClassList classes) {
        setTemplate(Resourses.LINK);
        bodyAppend(body);
        map.put("class", classes.toString());
        map.put("link", "#");
    }

    public Link(String body, String link) {
        setTemplate(Resourses.LINK);
        bodyAppend(body);
        map.put("link", link);
    }

    public Link(String body, ClassList classes, String link) {
        setTemplate(Resourses.LINK);
        bodyAppend(body);
        this.classes = classes;
        map.put("link", link);
    }

    @Override
    public String toString() {
        return render();
    }
}
