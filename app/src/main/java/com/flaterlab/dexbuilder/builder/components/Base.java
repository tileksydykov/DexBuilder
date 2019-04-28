package com.flaterlab.dexbuilder.builder.components;

import java.util.HashMap;
import java.util.Set;

public abstract class Base {
    HashMap<String, String> map;
    private String body;
    private String GenTemplate;

    public Base() {
        this.map = new HashMap<>();
        body = "";
    }

    public void setTemplate(String template) {
        this.GenTemplate = template;
    }

    public void bodyAppend(String html){
        this.body = new StringBuilder()
                .append(this.body)
                .append(html)
                .toString();
    }

    public void bodyPreAppend(String html){
        this.body = new StringBuilder()
                .append(html)
                .append(body)
                .toString();
    }

    public String render(String template, HashMap<String, String> vars){
        String v, r;
        Set<String> keys = vars.keySet();
        for (String key : keys){
            v = new StringBuilder()
                    .append("@@")
                    .append(key)
                    .append("@@")
                    .toString();
            r = vars.get(key);
            if(r == null){r = " ";}
            template = template.replace(v, r);
        }
        template = template.replaceAll("(@@)[^&]*(@@)", "");
        return template;
    }
    public String render(String template){
        map.put("body", body);
        HashMap<String, String> vars = map;
        String v, r;
        Set<String> keys = vars.keySet();
        for (String key : keys){
            v = new StringBuilder()
                    .append("@@")
                    .append(key)
                    .append("@@")
                    .toString();
            r = vars.get(key);
            if(r == null){r = " ";}
            template = template.replace(v, r);
        }
        template = template.replaceAll("(@@)[^&]*(@@)", "");
        return template;
    }

    public String render(){
        String template = getTemplate();
        map.put("body", body);
        HashMap<String, String> vars = map;
        String v, r;
        Set<String> keys = vars.keySet();
        for (String key : keys){
            v = new StringBuilder()
                    .append("@@")
                    .append(key)
                    .append("@@")
                    .toString();
            r = vars.get(key);
            if(r == null){r = " ";}
            template = template.replace(v, r);
        }
        template = template.replaceAll("(@@)[^&]*(@@)", "");
        return template;
    }

    public String getTemplate() {
        return GenTemplate;
    }

    @Override
    public String toString() {
        return render(GenTemplate, map);
    }
}
