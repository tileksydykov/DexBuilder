package com.flaterlab.dexbuilder;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProjectActivity extends AppCompatActivity {
    LayoutInflater inflater;
    ViewGroup mMainContainer;
    View v;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mMainContainer.removeAllViews();
                    v = inflater.inflate(R.layout.navbar_edit_layout, mMainContainer);
                    return true;
                case R.id.navigation_dashboard:
                    mMainContainer.removeAllViews();
                    v = inflater.inflate(R.layout.body_edit_layout, mMainContainer);
                    return true;
                case R.id.navigation_footer:
                    mMainContainer.removeAllViews();
                    v = inflater.inflate(R.layout.content_edit_layout, mMainContainer);
                    return true;
                case R.id.navigation_publish:
                    mMainContainer.removeAllViews();
                    v = inflater.inflate(R.layout.save_project_layout, mMainContainer);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = findViewById(R.id.project_activity_main_frame);
        mMainContainer = (ViewGroup) view;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
