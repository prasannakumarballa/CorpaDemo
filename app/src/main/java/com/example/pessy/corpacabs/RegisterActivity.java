package com.example.pessy.corpacabs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "Google Sign-In";
    GoogleApiClient mGoogleApiClient;
    GoogleSignInOptions gso;
    SignInButton signInButton;


    //All Views are declared and initialized here
    @Bind(R.id.first_name_register_textinput)
    EditText first_name_register;
    @Bind(R.id.last_name_register_textinput)
    EditText last_name_register;
    @Bind(R.id.email_register_textinput)
    EditText email_register_input;
    @Bind(R.id.mobile_number_register_input)
    EditText mobile_number_register_input;
    @Bind(R.id.password_register_input)
    EditText password_register_input;
    @Bind(R.id.next_button)
    Button next_button;
    @Bind(R.id.first_name_register_design_textinput)
    TextInputLayout first_name_layout;
    @Bind(R.id.last_name_register_design_textinput)
    TextInputLayout last_name_layout;
    @Bind(R.id.email_register_design_textinput)
    TextInputLayout email_layout;
    @Bind(R.id.password_register_design_input)
    TextInputLayout password_layout;
    @Bind(R.id.mobile_number_register_design_input)
    TextInputLayout mobile_number_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //For Google Sign-In It requires three methods
        GoogleSigninoptions();
        GoogleApiclient();
        SigninButton();

        //Now we can do whatever with those views
        first_name_register.addTextChangedListener(new MyTextWatcher(first_name_register));
        last_name_register.addTextChangedListener(new MyTextWatcher(last_name_register));
        email_register_input.addTextChangedListener(new MyTextWatcher(email_register_input));
        password_register_input.addTextChangedListener(new MyTextWatcher(password_register_input));
        mobile_number_register_input.addTextChangedListener(new MyTextWatcher(mobile_number_register_input));


    }

    protected void SigninButton() {
        // Customize sign-in button. The sign-in button can be displayed in
// multiple sizes and color schemes. It can also be contextually
// rendered based on the requested scopes. For example. a red button may
// be displayed when Google+ scopes are requested, but a white button
// may be displayed when only basic profile is requested. Try adding the
// Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
// difference.
        signInButton = (SignInButton) findViewById(R.id.sign_in_google_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setColorScheme(SignInButton.COLOR_DARK);

    }

    private void GoogleApiclient() {
        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void GoogleSigninoptions() {
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(this, "Signed In As " + acct.getDisplayName(), Toast.LENGTH_LONG).show();
            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
        }
    }


    @OnClick(R.id.next_button)
    public void Next_Register() {
        Intent gotomapsIntent = new Intent(this, MapsActivity.class);
        startActivity(gotomapsIntent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
                case R.id.first_name_register_textinput:
                    validateFirstName();
                    break;
                case R.id.last_name_register_textinput:
                    validateLastName();
                    break;
                case R.id.email_register_textinput:
                    validateEmail();
                    break;
                case R.id.mobile_number_register_input:
                    validateMobile();
                case R.id.password_register_input:
                    validatePassword();
                    break;


            }

        }


    }


    protected boolean validateMobile() {
        if (mobile_number_register_input.getText().toString().trim().isEmpty() || mobile_number_register_input.getText().toString().trim().length() < 10) {
            mobile_number_layout.setError(getString(R.string.err_mobile_msg));
            requestFocus(mobile_number_register_input);
            return false;

        } else {
            mobile_number_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (password_register_input.getText().toString().trim().isEmpty() || password_register_input.getText().toString().trim().length() < 8) {
            password_layout.setError(getString(R.string.err_password_msg));
            requestFocus(password_register_input);
            return false;
        } else {
            password_layout.setErrorEnabled(false);
        }
        return true;
    }

    protected boolean validateEmail() {
        String email = email_register_input.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            email_layout.setError(getString(R.string.err_email_msg));
            requestFocus(email_register_input);
            return false;
        } else {
            email_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateLastName() {
        if (last_name_register.getText().toString().trim().isEmpty()) {
            last_name_layout.setError(getString(R.string.err_msg_last_name));
            requestFocus(last_name_register);
            return false;
        } else {
            last_name_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFirstName() {
        if (first_name_register.getText().toString().trim().isEmpty()) {
            first_name_layout.setError(getString(R.string.err_msg_first_name));
            requestFocus(first_name_register);
            return false;
        } else {
            first_name_layout.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
