package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Link extends Base {

    public Link(String body, ClassList classes) {
        template = Resourses.LINK;
        bodyAppend(body);
        map.put("class", classes.toString());
    }
}
