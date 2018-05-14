package com.example.saedf.app7learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.saedf.app7learn.ApiService.ApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText editTextFirstname=findViewById(R.id.edittext_first_name);
        final EditText editTextLastName=findViewById(R.id.edittext_last_name);
        final EditText editTextAge=findViewById(R.id.edittext_age);

        final CheckBox chkCSS=findViewById(R.id.checkbox_signup_isCSS);
        final CheckBox chkHtml=findViewById(R.id.checkbox_signup_isHTML);
        final CheckBox chkJava=findViewById(R.id.checkbox_signup_isJava);
        final RadioButton rdbMale=findViewById(R.id.rdb_signup_male);
        final RadioButton rdbFemale=findViewById(R.id.rdb_signup_female);

        final SwitchCompat swicthHasJob=findViewById(R.id.has_job_switch);

        Button btnRegistring=findViewById(R.id.btn_signup_register);
        btnRegistring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService=new ApiService(SignUpActivity.this);
                JSONObject jsonObjectrequest=new JSONObject();
                try {
                    jsonObjectrequest.put("first_name",editTextFirstname.getText().toString());
                    jsonObjectrequest.put("last_name",editTextLastName.getText().toString());
                    jsonObjectrequest.put("age",editTextAge.getText().toString());
                    jsonObjectrequest.put("has_job",swicthHasJob.isChecked());
                    if (rdbMale.isChecked()){
                        jsonObjectrequest.put("gender","Male");
                    }else{
                        jsonObjectrequest.put("gender","Female");
                    }
                    JSONArray skillsJsonArray=new JSONArray();
                    if (chkCSS.isChecked()){
                        skillsJsonArray.put(chkCSS.getText());
                    }
                    if (chkHtml.isChecked()){
                        skillsJsonArray.put(chkHtml.getText());
                    }
                    if (chkJava.isChecked()){
                        skillsJsonArray.put(chkJava.getText());
                    }
                    jsonObjectrequest.put("skills",skillsJsonArray);
                    apiService.signUpUser(jsonObjectrequest, new ApiService.OnSignupComplete() {
                        @Override
                        public void onSignUp(boolean success) {
                            if (success){
                                Toast.makeText(SignUpActivity.this, "ثبت نام شما با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(SignUpActivity.this, "لطفا مجددا سعی نمائید!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
