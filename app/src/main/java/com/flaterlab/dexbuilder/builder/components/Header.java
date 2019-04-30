package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;
import com.flaterlab.dexbuilder.builder.ThemeConfig;

public class Header extends Base{
    String name;
    String standartButton = "<button class=\"navbar-toggler\" type=\"button\" " +
            "data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" " +
            "aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" " +
            "aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>";

    public Header(String header, int theme) {
        setTemplate(Resourses.NAVIGATION_SCOBE);
        name = header;
        ClassList classes = new ClassList();
        classes.add("navbar");
        classes.add("navbar-expand-lg");
        if(theme == ThemeConfig.LIGHT){
            classes.add("navbar-light bg-light");
        }else if (theme == ThemeConfig.DARK){
            classes.add("navbar-dark bg-dark");
        }
        map.put("class", classes.toString());
    }

    public String getHeader(){
        String[] v = {"navbar-brand"};
        Link l = new Link(name, new ClassList(v), "/");
        Div d = new Div();
        d.bodyAppend(l.toString());
        d.addClass("header");
        bodyAppend(d.render());
        // bodyAppend(standartButton);
        map.put("body", getBody());
        return render(getTemplate(), map);
    }

    @Override
    public String toString() {
        String[] v = {"navbar-brand"};
        Div d = new Div();
        d.addClass("navbar-header");
        d.bodyAppend(new Link(name, new ClassList(v), "/").toString());
        bodyAppend(d.toString());
        // bodyAppend(standartButton);
        return render();
    }
}
