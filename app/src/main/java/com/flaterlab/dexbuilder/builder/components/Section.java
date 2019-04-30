package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Section extends HTMLObject {

    public Section() {
        setTemplate(Resourses.SECTION);
    }

    public Section(Column c){
        setTemplate(Resourses.SECTION);
        Container n = new Container();
        n.addColumn(c);
        bodyAppend(n.render());
    }

    public Section(Column c, Column cc){
        setTemplate(Resourses.SECTION);
        Container n = new Container();
        n.addColumn(c);
        n.addColumn(cc);
        bodyAppend(n.render());
    }
}
