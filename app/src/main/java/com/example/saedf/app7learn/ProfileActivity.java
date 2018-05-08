package com.example.saedf.app7learn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.saedf.app7learn.dataModel.User;
import com.example.saedf.app7learn.sharedPreferences.UserSharedPrefManager;


public class ProfileActivity extends AppCompatActivity {
    private EditText txtFirstName;
    private EditText txtLastName;
    private CheckBox chkIsJavaExpert;
    private CheckBox chkIsCssExpert;
    private CheckBox chkIsHtmlExpert;
    private RadioButton rdbMale;
    private RadioButton rdbFemale;
    private ImageButton imgbtnBack;
    private User user = new User();
    ;
    private UserSharedPrefManager userSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        userSharedPrefManager = new UserSharedPrefManager(this);
        getinfoUser();


//        imgbtnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });


        txtFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user.setFirstName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user.setLastname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        chkIsJavaExpert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                user.setJavaExpert(b);
            }
        });
        chkIsCssExpert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                user.setCSSExpert(b);

            }
        });
        chkIsHtmlExpert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                user.setHtmlExpert(b);

            }
        });

        rdbMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbMale.isChecked()) {
                    user.setGender(User.MALE);
                } else {
                    user.setGender(User.FEMALE);
                }
            }
        });

        Button btnSabtInfo = findViewById(R.id.btn_profileAcivity_sabtInfo);
        btnSabtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSharedPrefManager.savedUserInfo(user);
                Toast.makeText(ProfileActivity.this, "اطلاعات با موفقیت ثبت شد!", Toast.LENGTH_SHORT).show();


            }
        });
        setupToolBar();
    }

    private void getinfoUser() {
        user = userSharedPrefManager.getUser();
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastname());
        chkIsHtmlExpert.setChecked(user.isHtmlExpert());
        chkIsCssExpert.setChecked(user.isCSSExpert());
        chkIsJavaExpert.setChecked(user.isJavaExpert());
        byte gender = user.getGender();
        if (gender == User.MALE) {
            rdbMale.isChecked();
        } else {
            rdbFemale.isChecked();
        }
    }

    private void initView() {
        txtFirstName = findViewById(R.id.editText_profileActivity_firstname);
        txtLastName = findViewById(R.id.editText_profileActivity_Lastname);
        chkIsJavaExpert = findViewById(R.id.checkbox_profileactivity_isJava);
        chkIsCssExpert = findViewById(R.id.checkbox_profileactivity_isCSS);
        chkIsHtmlExpert = findViewById(R.id.checkbox_profileactivity_isHTML);
        //imgbtnBack = findViewById(R.id.imgbtn_profileActivity_buttonBack);
        rdbFemale = findViewById(R.id.rdb_profielActivity_female);
        rdbMale = findViewById(R.id.rdb_profielActivity_male);
    }

    private void setupToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar_profilaActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
