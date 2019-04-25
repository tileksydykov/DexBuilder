package com.flaterlab.dexbuilder.network;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostFlipdex {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String bowlingJson() {
        return "{'title':'HIGH_SCORE', 'id': 'tilek', 'body': 'tiwlk'}";
    }

    public static void main(String[] args) throws IOException {
        PostFlipdex example = new PostFlipdex();
        String json = example.bowlingJson();
        String response = example.post("https://flipdex.ru/ajax/setsite", json);
        System.out.println(response);

    }
}
