package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Link extends Base {

    public Link(String body, ClassList classes) {
        setTemplate(Resourses.LINK);
        bodyAppend(body);
        map.put("class", classes.toString());
        map.put("link", "#");
    }

    public Link(String body, ClassList classes, String link) {
        setTemplate(Resourses.LINK);
        bodyAppend(body);
        map.put("class", classes.toString());
        map.put("link", link);
    }

    @Override
    public String toString() {
        return render();
    }
}
