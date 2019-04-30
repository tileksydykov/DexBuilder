package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Paragraph extends HTMLObject {

    public Paragraph(String text) {
        setTemplate(Resourses.PARAGRAPH);
        map.put("body", text);
    }

    public Paragraph(String text, ClassList classses) {
        setTemplate(Resourses.PARAGRAPH);
        map.put("body", text);
        this.classes = classses;
    }

}
