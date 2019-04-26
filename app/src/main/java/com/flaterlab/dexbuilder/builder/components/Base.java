package com.flaterlab.dexbuilder.builder.components;

import java.util.HashMap;
import java.util.Set;

public abstract class Base {
    public String render(String template, HashMap<String, String> vars){
        String v, r;
        Set<String> keys = vars.keySet();
        for (String key : keys){
            v = new StringBuilder()
                    .append("@@")
                    .append(key)
                    .append("@@")
                    .toString();
            r = (vars.get(key) != null) ? vars.get(key) : " ";
            template = template.replace(v, r);
        }
        template = template.replaceAll("(@@)[^&]*(@@)", "");
        return template;
    }
}
