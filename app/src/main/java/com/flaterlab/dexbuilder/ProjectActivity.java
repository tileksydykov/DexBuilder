package com.flaterlab.dexbuilder;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.flaterlab.dexbuilder.builder.Page;
import com.flaterlab.dexbuilder.builder.ThemeConfig;
import com.flaterlab.dexbuilder.builder.components.Header;
import com.flaterlab.dexbuilder.builder.components.Jumbotron;
import com.flaterlab.dexbuilder.helper.DBConfig;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import io.paperdb.Paper;

public class ProjectActivity extends AppCompatActivity {


    final int EDIT_PROJECT_NAVBAR = 1;
    final int EDIT_PROJECT_BODY = 2;
    final int EDIT_PROJECT_SETTING = 3;
    final int EDIT_PROJECT_SAVE = 4;

    LayoutInflater inflater;
    ViewGroup mMainContainer;
    View v;
    Button mPublishProjectButton;
    EditText mEditSiteName;
    EditText mEditJumbotronTitle;
    EditText mEditJumbotronText;
    HashMap<String, String> project;
    String projectName;
    ProgressBar mProgressBar;
    ProjectActivity context;
    Switch mJumbotronSwitch;
    LinearLayout mJumbotronLayout;

    int currentProjectEditView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    saveAllChanges();

                    mMainContainer.removeAllViews();

                    v = inflater.inflate(R.layout.navbar_edit_layout, mMainContainer);

                    onNavbarEditCreate(v);
                    return true;

                case R.id.navigation_dashboard:

                    saveAllChanges();

                    currentProjectEditView = EDIT_PROJECT_BODY;

                    mMainContainer.removeAllViews();

                    v = inflater.inflate(R.layout.body_edit_layout, mMainContainer);

                    onBodyEditCreate(v);
                    return true;

                case R.id.navigation_footer:

                    saveAllChanges();

                    currentProjectEditView = EDIT_PROJECT_SETTING;

                    mMainContainer.removeAllViews();

                    v = inflater.inflate(R.layout.settings_edit_layout, mMainContainer);

                    onSettingEditCreate(v);

                    return true;

                case R.id.navigation_publish:

                    saveAllChanges();

                    currentProjectEditView = EDIT_PROJECT_SAVE;

                    mMainContainer.removeAllViews();

                    v = inflater.inflate(R.layout.save_project_layout, mMainContainer);

                    onSaveProjectCreate(v);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        projectName = extras.getString("projectName");

        context = this;

        project = Paper.book(DBConfig.PROJECT_NODE)
                .read( projectName,
                        new HashMap<String, String>());


        // get data via the key
        if (projectName != null) {
            // do something with the data
            setTitle("Project: "+projectName);
        }
        inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = findViewById(R.id.project_activity_main_frame);
        mMainContainer = (ViewGroup) view;
        mProgressBar = findViewById(R.id.progressBarLoadingProject);
        v = inflater.inflate(R.layout.navbar_edit_layout, mMainContainer);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        onNavbarEditCreate(v);
    }

    protected void saveAllChanges(){
        switch (currentProjectEditView){
            case EDIT_PROJECT_NAVBAR:

                project.put(DBConfig.JUMBOTRON_TEXT,
                        mEditJumbotronText.getText().toString());
                project.put(DBConfig.JUMBOTRON_TITLE,
                        mEditJumbotronTitle.getText().toString());

                project.put(DBConfig.NAVBAR_TITLE,
                        mEditSiteName.getText().toString());

            case EDIT_PROJECT_BODY:

            case EDIT_PROJECT_SAVE:

            case EDIT_PROJECT_SETTING:

        }

    }

    protected void onNavbarEditCreate(View v){
        currentProjectEditView = EDIT_PROJECT_NAVBAR;
        mEditSiteName =  v.findViewById(R.id.edit_site_name);
        mEditJumbotronTitle =  v.findViewById(R.id.edit_jumbotron_title);
        mEditJumbotronText =  v.findViewById(R.id.edit_jumbotron_text);
        mJumbotronSwitch = v.findViewById(R.id.switch_jumbotron);
        mJumbotronLayout = v.findViewById(R.id.jumbotron_edit_layout);

        // set up our site nav bar and jumbotron text
        mEditJumbotronText.setText(project.get(DBConfig.JUMBOTRON_TEXT));
        mEditJumbotronTitle.setText(project.get(DBConfig.JUMBOTRON_TITLE));
        mEditSiteName.setText(project.get(DBConfig.NAVBAR_TITLE));



       mJumbotronSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mJumbotronLayout.setVisibility(View.VISIBLE);
                    project.put(DBConfig.JUMBOTRON_IS_OFF, DBConfig.JUMBOTRON_ON);
                } else {
                    mJumbotronLayout.setVisibility(View.INVISIBLE);
                    project.put(DBConfig.JUMBOTRON_IS_OFF, DBConfig.JUMBOTRON_OFF);
                }
            }
        });
        Boolean isJumbotronEnabled = project.get(DBConfig.JUMBOTRON_IS_OFF).equals(DBConfig.JUMBOTRON_ON);
        if(isJumbotronEnabled){
            mJumbotronSwitch.setChecked(true);
        }

    }

    protected void onSettingEditCreate(View v){

    }

    protected void onBodyEditCreate(View v){

    }

    protected void onSaveProjectCreate(View v){
        mPublishProjectButton = v.findViewById(R.id.button_publish);
        mPublishProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                Page p = new Page();
                Header h = new Header(project.get(DBConfig.NAVBAR_TITLE),
                        ThemeConfig.DARK);
                p.setHeader(h);

                if(project.get(DBConfig.JUMBOTRON_IS_OFF).equals(DBConfig.JUMBOTRON_ON)){
                    Jumbotron j = new Jumbotron();

                    j.setTitle(project.get(DBConfig.JUMBOTRON_TITLE));

                    j.setText(project.get(DBConfig.JUMBOTRON_TEXT));

                    j.setButton(project.get(DBConfig.JUMBOTRON_TITLE));
                    p.setJumbotron(j);
                }




                new AsyncTaskRunner(context).execute(
                        projectName,
                        project.get(DBConfig.TITLE),
                        p.getPage()
                );

            }
        });
    }

    protected void saveAllProjectChanges(){
        Paper.book(DBConfig.PROJECT_NODE).write(projectName, project);
        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        saveAllChanges();
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        saveAllChanges();
        saveAllProjectChanges();
        super.onStop();
    }

    @Override
    protected void onStart() {
        project = Paper.book(DBConfig.PROJECT_NODE)
                .read( projectName,
                        new HashMap<String, String>());
        super.onStart();
    }

    private static class  AsyncTaskRunner extends AsyncTask<String, String, String> {
        private WeakReference<ProjectActivity> activityReference;

        AsyncTaskRunner(ProjectActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected String doInBackground(String... strings) {
            String SET_SITE_URL = "https://flipdex.ru/ajax/setsite";

            ProjectActivity activity = activityReference.get();

            if (activity == null || activity.isFinishing()) return "z";
            OkHttpClient client = new OkHttpClient();

            Page p = new Page();
            p.setTheme(ThemeConfig.DARK);
            RequestBody formBody = new FormEncodingBuilder()
                    .add("id", strings[0])
                    .add("body", strings[2])
                    .add("title", strings[1] + " - Powered by DexBuilder")
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

            return res2;
        }

        @Override
        protected void onPostExecute(String s) {
            ProjectActivity activity = activityReference.get();

            Toast.makeText(activity.getApplicationContext(),
                    "published on flipdex.ru/"+ activity.projectName ,
                    Toast.LENGTH_LONG).show();

            activity.mProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }
}
