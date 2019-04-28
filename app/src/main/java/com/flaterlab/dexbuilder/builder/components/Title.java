package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

public class Title extends Base{

    private String h1 = Resourses.TITLE_1;
    private String h2 = Resourses.TITLE_2;
    private String h3 = Resourses.TITLE_3;
    private String h4 = Resourses.TITLE_4;
    private String h5 = Resourses.TITLE_5;
    private String h6 = Resourses.TITLE_6;

    private int value;

    public Title(String title, int value){
        bodyAppend(title);
        this.value = value;
    }

    public Title(String title, int value, ClassList list){
        bodyAppend(title);
        map.put("class", list.toString());
        this.value = value;
    }

    @Override
    public String toString() {
        String template = "";
        switch (value){
            case 1:
                template = h1;
                break;
            case 2:
                template = h2;
                break;
            case 3:
                template = h3;
                break;
            case 4:
                template = h4;
                break;
            case 5:
                template = h5;
                break;
            case 6:
                template = h6;
                break;
        }
        setTemplate(template);
        return render();
    }

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
