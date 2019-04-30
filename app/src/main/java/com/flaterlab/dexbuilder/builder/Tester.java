package com.flaterlab.dexbuilder.builder;

import android.util.Log;

import com.flaterlab.dexbuilder.builder.components.Button;
import com.flaterlab.dexbuilder.builder.components.Footer;
import com.flaterlab.dexbuilder.builder.components.Header;
import com.flaterlab.dexbuilder.builder.components.Span;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.net.ssl.HandshakeCompletedEvent;

public class Tester {

    public static void main(String[] args) {
        Span s = new Span("himii");
        System.out.println(s.render());
    }
}