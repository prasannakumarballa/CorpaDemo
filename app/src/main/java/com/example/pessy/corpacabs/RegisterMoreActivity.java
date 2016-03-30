package com.example.pessy.corpacabs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pessy.corpacabs.db.DaoMaster;
import com.example.pessy.corpacabs.db.DaoSession;
import com.example.pessy.corpacabs.db.User;
import com.example.pessy.corpacabs.db.UserDao;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.annotations.BindClick;
import io.github.sporklibrary.annotations.BindLayout;
import io.github.sporklibrary.annotations.BindView;

/**
 * Created by pessy on 3/23/2016.
 */
@BindLayout(R.layout.register_more)
public class RegisterMoreActivity extends AppCompatActivity {

    @BindView(R.id.register_more_mobile)
    EditText register_more_mobile_input;
    @BindView(R.id.register_more_mobile_layout)
    TextInputLayout register_more_mobile_layout_activity;
    @BindView(R.id.password_register_more)
    EditText register_more_password_input;
    @BindView(R.id.password_register_more_layout)
    TextInputLayout password_register_more_layout_activity;

    @BindView(R.id.next_register_more_button)
    Button sign_up_Button;

    String name_string;
    String email_string;
    String mobile_string;
    String password_string;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spork.bind(this);
        mobile_string = register_more_mobile_input.getText().toString();
        password_string = register_more_password_input.getText().toString();

        Bundle extras = getIntent().getExtras();
        email_string= extras.getString("EMAIL");


        register_more_mobile_input.addTextChangedListener(new MyTextWatcher(register_more_mobile_input));
        register_more_password_input.addTextChangedListener(new MyTextWatcher(register_more_mobile_input));


    }

    @BindClick(R.id.next_register_more_button)
    public void SignUp() {



        Intent i = new Intent(this,NavDrawer.class);
        //Pass strings
        i.putExtra("NAME",name_string);
        i.putExtra("EMAIL_REG",email_string);

        startActivity(i);

    }

    private boolean validateMobile() {
        if (register_more_mobile_input.getText().toString().trim().isEmpty() || register_more_mobile_input.getText().toString().trim().length() < 10) {
            register_more_mobile_layout_activity.setError(getString(R.string.err_mobile_msg));
            requestFocus(register_more_mobile_input);
            return false;

        } else {
            register_more_mobile_layout_activity.setErrorEnabled(false);
        }
        return true;
    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {
        View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (view.getId()) {



            }

        }


    }


}
