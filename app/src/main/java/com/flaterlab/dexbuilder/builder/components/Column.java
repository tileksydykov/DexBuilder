package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Column extends HTMLObject{
    public Column() {
        setTemplate(Resourses.CONTAINER_SCOBE);
        addClass("col");
    }
}
