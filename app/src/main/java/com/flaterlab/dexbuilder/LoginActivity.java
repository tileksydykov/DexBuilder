package com.flaterlab.dexbuilder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.flaterlab.dexbuilder.builder.Page;
import com.flaterlab.dexbuilder.builder.ThemeConfig;
import com.flaterlab.dexbuilder.helper.DBConfig;
import com.flaterlab.dexbuilder.helper.StylesConfig;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    TextView messageForUser;
    ProgressBar mProgresBar;
    String lastProjectName;
    LoginActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        final Button checkSiteButton = findViewById(R.id.check_site);

        final EditText siteTitleEditText = findViewById(R.id.site_title);

        context = this;

        messageForUser = findViewById(R.id.message_for_user);

        mProgresBar = findViewById(R.id.progressBar);

        checkSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String siteName = siteTitleEditText.getText().toString();
                String err = "";

                if(siteName.length() < 3){
                    Toast.makeText(getApplicationContext(), context.getString(R.string.short_link_error), Toast.LENGTH_LONG).show();
                    err = "z";
                }
                if (siteName.contains(" ")){
                    Toast.makeText(getApplicationContext(), context.getString(R.string.link_contains_spaces_error), Toast.LENGTH_LONG).show();
                    err = "z";

                }
                if (siteName.contains(".")){
                    Toast.makeText(getApplicationContext(), "Site link shouldn't contain any dots", Toast.LENGTH_LONG).show();
                    err = "z";
                }
                if(err.equals("")){
                    mProgresBar.setVisibility(View.VISIBLE);
                    new AsyncTaskRunner(context).execute(siteName.trim());
                }
            }
        });
    }

    private static class  AsyncTaskRunner extends AsyncTask<String, String, String>{
        private WeakReference<LoginActivity> activityReference;

        AsyncTaskRunner(LoginActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected String doInBackground(String... strings) {
            String CHECK_SITE_URL ="https://flipdex.ru/ajax/sitecheck";
            String SET_SITE_URL = "https://flipdex.ru/ajax/setsite";

            LoginActivity activity = activityReference.get();

            if (activity == null || activity.isFinishing()) return "z";

                OkHttpClient client = new OkHttpClient();
                activity.lastProjectName = strings[0];
                Request request = new Request.Builder()
                        .url(CHECK_SITE_URL + "/" + strings[0])
                        .build();

                String res = "";
                try{
                    Response response = client.newCall(request).execute();
                    res = response.body().string();
                }catch (IOException e){
                    Toast.makeText(activity.getApplicationContext(), "No INTERNET", Toast.LENGTH_LONG).show();
                }

                Log.d("check", "doInBackground: " + res);
                if(true){
                    Page p = new Page();
                    p.setTheme(ThemeConfig.DARK);
                    RequestBody formBody = new FormEncodingBuilder()
                            .add("id", strings[0])
                            .add("body", p.getJumbotronSample(strings[0]))
                            .add("title", strings[0] + " - Powered by DexBuilder")
                            .build();
                    Request request2 = new Request.Builder()
                            .url(SET_SITE_URL)
                            .post(formBody)
                            .build();
                    String res2 = "";
                    try{
                        Response response2 = client.newCall(request2).execute();
                        res2 = response2.body().string();
                        Log.d("check", "post executer server res :" + res2);
                    }catch (IOException e){
                        Log.d("check", "cant read response");
                    }
                }
                return res;
        }

        @Override
        protected void onPostExecute(String s) {
            LoginActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            if(s.equals("1")){
                activity.messageForUser.setText("WE ALREADY HAVE THE SAME PAGE NAME \n PLEASE CHOOSE ANOTHER PAGE NAME");
            }else {
                saveProjectInDB(activity.lastProjectName);
                Intent intent=new Intent();
                activity.setResult(2,intent);
                activity.finish();
            }
            activity.mProgresBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        void saveProjectInDB (String name) {
            ArrayList<String> projects = Paper.book().read("projects", new ArrayList<String>());
            projects.add(name);
            Paper.book().write("projects", projects);




            // init project config

            HashMap<String, String> project = new HashMap<>();
            project.put(DBConfig.JUMBOTRON_IS_OFF, DBConfig.JUMBOTRON_ON);
            project.put(DBConfig.CONTENT_MAIN, "content");
            project.put(DBConfig.TITLE, name);
            project.put(DBConfig.NAVBAR_TITLE, name);
            project.put(DBConfig.JUMBOTRON_TITLE, name);
            project.put(DBConfig.JUMBOTRON_BUTTON_TEXT, "sample");
            project.put(DBConfig.JUMBOTRON_BUTTON_LINK, "");
            project.put(DBConfig.JUMBOTRON_BUTTON_STYLE, StylesConfig.BTN_GREY);
            project.put(DBConfig.JUMBOTRON_TEXT, "This is your first page!!! Enjoy!");


            // save project in DB
            Paper.book(DBConfig.PROJECT_NODE).write( name,  project);
        }
    }
}
