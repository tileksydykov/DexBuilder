package com.flaterlab.dexbuilder.builder.components;

import java.util.ArrayList;
import java.util.Collections;

public class ClassList extends ArrayList<String> {

    public ClassList() {
    }

    public ClassList(String[] ar) {
        Collections.addAll(this, ar);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.size(); i++) {
            result = new StringBuilder()
                    .append(result)
                    .append(" ")
                    .append(this.get(i))
                    .toString();
        }
        return result;
    }
}
