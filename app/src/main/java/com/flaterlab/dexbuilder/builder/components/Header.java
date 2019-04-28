package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;
import com.flaterlab.dexbuilder.builder.ThemeConfig;

public class Header extends Base{
    String nav = Resourses.NAVIGATION_SCOBE;
    String name;
    String standartButton = "<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
            "<span class=\"navbar-toggler-icon\"></span>\n</button>";

    public Header(String header, int theme) {
        name = header;
        map.put("link", header);
        ClassList classes = new ClassList();
        classes.add("navbar");
        classes.add("navbar-expand-lg");
        if(theme == ThemeConfig.DARK){
            classes.add("navbar-light bg-light");
        }else if (theme == ThemeConfig.LIGHT){
            classes.add("navbar-dark bg-dark");
        }
        map.put("class", classes.toString());
    }

    @Override
    public String toString() {
        String[] v = {"navbar-brand"};
        bodyAppend(new Link(name, new ClassList(v)).toString());
        bodyAppend(standartButton);
        return super.toString();
    }
}
