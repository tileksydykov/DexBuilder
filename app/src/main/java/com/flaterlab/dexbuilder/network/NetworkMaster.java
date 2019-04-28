package com.flaterlab.dexbuilder.network;


public class NetworkMaster  {
    private static final String SET_SITE_URL ="https://flipdex.ru/ajax/setsite";
    private static final String CHECK_SITE_URL ="https://flipdex.ru/ajax/sitecheck";
    private static final String DELETE_SITE_URL ="https://flipdex.ru/ajax/sitedelete";

    public boolean checkIfSiteExist(String title){
        return true;
    }
    public boolean setSite(String body,String siteName, String title){
        return false;
    }
}
