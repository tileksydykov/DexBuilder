package com.flaterlab.dexbuilder;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flaterlab.dexbuilder.helper.DBConfig;

import java.util.HashMap;

import io.paperdb.Paper;

public class ProjectActivity extends AppCompatActivity {
    final String TAG  = "Project activity";

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

                    return true;

                case R.id.navigation_footer:

                    saveAllChanges();

                    currentProjectEditView = EDIT_PROJECT_SETTING;

                    mMainContainer.removeAllViews();

                    v = inflater.inflate(R.layout.settings_edit_layout, mMainContainer);

                    return true;

                case R.id.navigation_publish:

                    saveAllChanges();

                    currentProjectEditView = EDIT_PROJECT_SAVE;

                    mMainContainer.removeAllViews();

                    v = inflater.inflate(R.layout.save_project_layout, mMainContainer);

                    mPublishProjectButton = v.findViewById(R.id.button_publish);

                    setListenersOnSaveProjectView();

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
    protected void setListenersOnSaveProjectView(){
        mPublishProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "published", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onNavbarEditCreate(View v){
        currentProjectEditView = EDIT_PROJECT_NAVBAR;
        mEditSiteName =  v.findViewById(R.id.edit_site_name);
        mEditJumbotronTitle =  v.findViewById(R.id.edit_jumbotron_title);
        mEditJumbotronText =  v.findViewById(R.id.edit_jumbotron_text);

        Log.d(TAG, "onNavbarEditCreate: " + project.toString());
        mEditJumbotronText.setText(project.get(DBConfig.JUMBOTRON_TEXT));
        mEditJumbotronTitle.setText(project.get(DBConfig.JUMBOTRON_TITLE));
        mEditSiteName.setText(project.get(DBConfig.NAVBAR_TITLE));
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
}
