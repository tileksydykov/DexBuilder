package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Span extends HTMLObject {
    public Span(String text) {
        setTemplate(Resourses.SPAN_SCOBE);
        bodyAppend(text);
    }
}
