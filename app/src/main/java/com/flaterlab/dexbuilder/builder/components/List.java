package com.flaterlab.dexbuilder.builder.components;

import com.flaterlab.dexbuilder.builder.Resourses;

import java.util.ArrayList;

public class List extends Base{
    private String list = Resourses.LIST;
    private String listEl = Resourses.LIST_ELEMENT;

    private ArrayList<String> data;

    public List() {

    }

    public List(ArrayList list) {
        data = list;
    }

    public String generateListFromArray(ArrayList<String> array){
        String body = "";
        for(String item: array){
            map.put("body", item);
            body = body.concat(render(listEl, map));
        }
        map.put("body", body);
        return render(list, map);
    }


}
