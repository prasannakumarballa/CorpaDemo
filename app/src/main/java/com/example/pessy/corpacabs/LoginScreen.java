package com.example.pessy.corpacabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginScreen extends AppCompatActivity {


    @Bind(R.id.tv_logo_home)
    TextView app_name_textview;
    @Bind(R.id.sign_in_button)
    Button sign_in_button;
    @Bind(R.id.register_button)
    Button register_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        ButterKnife.bind(this);
        app_name_textview.setTypeface(EasyFonts.recognition(this));


    }


    @OnClick(R.id.sign_in_button)
    public void ClickSignIn() {
        Intent sign_in_intent = new Intent(this, SignInActivity.class);
        startActivity(sign_in_intent);
    }

    @OnClick(R.id.register_button)
    public void ClickRegister() {
        Intent register_intent = new Intent(this, RegisterActivity.class);
        startActivity(register_intent);
    }
}







