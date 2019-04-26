package com.flaterlab.dexbuilder.network;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostFlipdex {

    public static final String SET_SITE_URL ="https://flipdex.ru/ajax/setsite";
    public static final String CHECK_SITE_URL ="https://flipdex.ru/ajax/sitecheck";
    public static final String DELETE_SITE_URL ="https://flipdex.ru/ajax/sitedelete";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String post(String url, String json, String key,String title) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Id", key)
                .addHeader("Title", title)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }



    public static void main(String[] args) throws IOException {
        PostFlipdex example = new PostFlipdex();

        String response = example.post(SET_SITE_URL, "body", "tilek", "title");
        System.out.println(response);
    }
}
