package com.flaterlab.dexbuilder.network;


import java.io.IOException;

public class NetworkMaster {
    private static final String SET_SITE_URL ="https://flipdex.ru/ajax/setsite";
    private static final String CHECK_SITE_URL ="https://flipdex.ru/ajax/sitecheck";
    private static final String DELETE_SITE_URL ="https://flipdex.ru/ajax/sitedelete";

    public boolean checkIfSiteExist(String title){
        PostFlipdex example = new PostFlipdex();
        try {
            String response = example.get(CHECK_SITE_URL + "/" + title);
            switch (response.trim()){
                case "true":
                    return true;
                case "false":
                    return false;
            }
        }catch (IOException e){
            System.out.println();
            return true;
        }
        return true;
    }
    public boolean setSite(String body,String siteName, String title){
        try {
            PostFlipdex example = new PostFlipdex();
            String response = example.post(SET_SITE_URL, body, siteName, title);
            switch (response.trim()){
                case "true":
                    return true;
                case "false":
                    return false;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return false;
    }
}
