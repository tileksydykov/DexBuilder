package com.flaterlab.dexbuilder.builder.components;

public class Footer extends Base {
    public Footer(String body) {
        map.put("body", body);
        map.put("class", map.get("class") + " footer");
    }
    public Footer(String body, ClassList classes){
        map.put("body", body);
        classes.add("footer");
        map.put("class", classes.toString());
    }

}
