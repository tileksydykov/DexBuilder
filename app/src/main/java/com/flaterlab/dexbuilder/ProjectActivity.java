package com.flaterlab.dexbuilder;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.flaterlab.dexbuilder.builder.Page;
import com.flaterlab.dexbuilder.builder.ThemeConfig;
import com.flaterlab.dexbuilder.builder.components.Column;
import com.flaterlab.dexbuilder.builder.components.Footer;
import com.flaterlab.dexbuilder.builder.components.Header;
import com.flaterlab.dexbuilder.builder.components.Jumbotron;
import com.flaterlab.dexbuilder.builder.components.Section;
import com.flaterlab.dexbuilder.helper.DBConfig;
import com.flaterlab.dexbuilder.helper.StylesConfig;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.paperdb.Paper;
import jp.wasabeef.richeditor.RichEditor;

public class ProjectActivity extends AppCompatActivity {


    final int EDIT_PROJECT_NAVBAR = 1;
    final int EDIT_PROJECT_BODY = 2;
    final int EDIT_PROJECT_SETTING = 3;
    final int EDIT_PROJECT_SAVE = 4;

    LayoutInflater inflater;
    ViewGroup mMainContainer;
    View v;

    //save
    Button mPublishProjectButton;
    // save end

    // navbar
    EditText mEditSiteName;
    EditText mEditJumbotronTitle;
    EditText mEditJumbotronText;
    EditText mEditJumbotronButton;
    EditText mEditButtonLink;
    HashMap<String, String> project;
    String projectName;
    ProgressBar mProgressBar;
    ProjectActivity context;
    Switch mJumbotronSwitch;
    LinearLayout mJumbotronLayout;
    RadioGroup mButtonStyleRadioGroup;
    // navbar end

    // setting

    // setting end

    // body

    // body end

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

                project.put(DBConfig.JUMBOTRON_BUTTON_LINK,
                        mEditButtonLink.getText().toString());

                project.put(DBConfig.JUMBOTRON_BUTTON_TEXT,
                        mEditJumbotronButton.getText().toString());

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
        mEditButtonLink = v.findViewById(R.id.edit_jumbotron_button_link);
        mEditJumbotronButton = v.findViewById(R.id.edit_jumbotron_button_text);
        mButtonStyleRadioGroup = findViewById(R.id.button_style_radio);


        // set up our site nav bar and jumbotron text
        mEditJumbotronText.setText(project.get(DBConfig.JUMBOTRON_TEXT));
        mEditJumbotronTitle.setText(project.get(DBConfig.JUMBOTRON_TITLE));
        mEditSiteName.setText(project.get(DBConfig.NAVBAR_TITLE));
        mEditJumbotronButton.setText(project.get(DBConfig.JUMBOTRON_BUTTON_TEXT));
        mEditButtonLink.setText(project.get(DBConfig.JUMBOTRON_BUTTON_LINK));

        // initialize group button
        int idForButtonRadio = R.id.style_grey;
        String styleForButton = project.get(DBConfig.JUMBOTRON_BUTTON_STYLE);
        if (styleForButton != null){
            switch (styleForButton){
                case StylesConfig.BTN_BLUE:
                    idForButtonRadio = R.id.style_blue;
                    break;
                case StylesConfig.BTN_RED:
                    idForButtonRadio = R.id.style_red;
                    break;
                case StylesConfig.BTN_GREEN:
                    idForButtonRadio = R.id.style_green;
                    break;
                case StylesConfig.BTN_YELLOW:
                    idForButtonRadio = R.id.style_yellow;
                    break;
                case StylesConfig.BTN_GREY:
                    idForButtonRadio = R.id.style_grey;
                    break;
                default:
                    break;
            }
        }
        // check button style
        mButtonStyleRadioGroup.check(idForButtonRadio);

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
        // set check listener on Radio group
        mButtonStyleRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.style_grey:
                        project.put(DBConfig.JUMBOTRON_BUTTON_STYLE, StylesConfig.BTN_GREY);
                        break;
                    case R.id.style_red:
                        project.put(DBConfig.JUMBOTRON_BUTTON_STYLE, StylesConfig.BTN_RED);
                        break;
                    case R.id.style_green:
                        project.put(DBConfig.JUMBOTRON_BUTTON_STYLE, StylesConfig.BTN_GREEN);
                        break;
                    case R.id.style_blue:
                        project.put(DBConfig.JUMBOTRON_BUTTON_STYLE, StylesConfig.BTN_BLUE);
                        break;
                    case R.id.style_yellow:
                        project.put(DBConfig.JUMBOTRON_BUTTON_STYLE, StylesConfig.BTN_YELLOW);
                        break;
                    default:
                        break;
                }
            }
        });
        String j = project.get(DBConfig.JUMBOTRON_IS_OFF);
        if(j != null){
            if(j.equals(DBConfig.JUMBOTRON_ON)){
                mJumbotronSwitch.setChecked(true);
            }
        }

    }

    protected void onSettingEditCreate(View v){

    }

    protected void onBodyEditCreate(View v){
        final RichEditor mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setEditorHeight(200);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                // Do Something
                Log.d("RichEditor", "Preview " + text);
            }
        });
        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBullets();
            }
        });

        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                        "dachshund");
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertTodo();
            }
        });
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


                // init jumbotron if it should be
                if(project.get(DBConfig.JUMBOTRON_IS_OFF).equals(DBConfig.JUMBOTRON_ON)){

                    // ! ! ! ! ! order is important ! ! ! ! ! !

                    Jumbotron j = new Jumbotron();

                    j.setTitle(project.get(DBConfig.JUMBOTRON_TITLE));

                    j.setText(project.get(DBConfig.JUMBOTRON_TEXT));

                    j.setButtonStyle(project.get(DBConfig.JUMBOTRON_BUTTON_STYLE));

                    j.setButtonLink(project.get(DBConfig.JUMBOTRON_BUTTON_LINK));

                    j.setButton(project.get(DBConfig.JUMBOTRON_BUTTON_TEXT));

                    p.setJumbotron(j);
                }



                // init body
                Column first = new Column();

                first.bodyAppend("himikfndsaou wrh g;orw gbtkle ghoten ohetsnhbt snh tnbhten hornt ohntesl elsu idbi");

                Section s = new Section(first);

                p.addToPage(s.render());

                // init footer

                Footer f = new Footer();
                p.addToPage(f.getStandartFooter("himik"));

                // send and publish page on server
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
