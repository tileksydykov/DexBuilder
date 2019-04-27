package com.flaterlab.dexbuilder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    TextView messageForUser;
    ProgressBar mProgresBar;
    String lastProjectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        final Button checkSiteButton = findViewById(R.id.check_site);

        final EditText siteTitleEditText = findViewById(R.id.site_title);

        messageForUser = findViewById(R.id.message_for_user);

        mProgresBar = findViewById(R.id.progressBar);


        checkSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String siteName = siteTitleEditText.getText().toString();
                mProgresBar.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute(siteName);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            String CHECK_SITE_URL ="https://flipdex.ru/ajax/sitecheck";

                OkHttpClient client = new OkHttpClient();
                lastProjectName = strings[0];
                Request request = new Request.Builder()
                        .url(CHECK_SITE_URL + "/" + strings[0])
                        .build();
                String res = "";
                try{
                    Response response = client.newCall(request).execute();
                    res = response.body().string();
                }catch (IOException e){
                    Log.d("check", "cant read response");
                }
                return res;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("1")){
                messageForUser.setText("WE ALREADY HAVE THE SAME PAGE NAME \n PLEASE CHOOSE ANOTHER PAGE NAME");
            }else {
                ArrayList<String> projects = Paper.book().read("projects", new ArrayList<String>());
                projects.add(lastProjectName);
                Paper.book().write("projects", projects);
                Intent intent=new Intent();
                setResult(2,intent);
                finish();
            }
            mProgresBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
