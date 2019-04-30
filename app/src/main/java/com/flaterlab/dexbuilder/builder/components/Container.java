package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Container extends HTMLObject {
    Div d = new Div();

    public Container() {
        setTemplate(Resourses.CONTAINER_SCOBE);
        classes.add("container");
        d.addClass("row");
    }

    public void addColumn(Column c){
        d.bodyAppend(c.render());
    }

    @Override
    public String render() {
        bodyAppend(d.render());
        return super.render();
    }
}
