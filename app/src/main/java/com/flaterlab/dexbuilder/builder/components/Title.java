package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

import java.util.HashMap;

public class Title extends Base{

    private String h1 = Resourses.TITLE_1;
    private String h2 = Resourses.TITLE_2;
    private String h3 = Resourses.TITLE_3;
    private String h4 = Resourses.TITLE_4;
    private String h5 = Resourses.TITLE_5;
    private String h6 = Resourses.TITLE_6;

    public String getH1(String title) {
        map.put("body", title);
        return render(h1, map);
    }
    public String getH2(String title) {
        map.put("body", title);
        return render(h2, map);
    }
    public String getH3(String title) {
        map.put("body", title);
        return render(h3, map);
    }
    public String getH4(String title) {
        map.put("body", title);
        return render(h4, map);
    }
    public String getH5(String title) {
        map.put("body", title);
        return render(h5, map);
    }
    public String getH6(String title) {
        map.put("body", title);
        return render(h6, map);
    }
}
