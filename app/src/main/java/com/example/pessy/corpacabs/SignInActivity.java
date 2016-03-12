package com.example.pessy.corpacabs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "Google Sign-In";
    GoogleApiClient mGoogleApiClient;
    GoogleSignInOptions gso;
    SignInButton signInButton;


    //Using Bind all are declared and initialized
    @Bind(R.id.mobile_number_signin_input)
    EditText mobile_signin_input;
    @Bind(R.id.mobile_number_signin_design_input)
    TextInputLayout mobile_signin_layout;
    @Bind(R.id.password_signin_input)
    EditText password_signin_input;
    @Bind(R.id.password_signin_design_input)
    TextInputLayout password_signin_layout;
    @Bind(R.id.signin_button)
    Button signin_button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        //This is for Google sign-in
        GoogleSigninoptions();
        GoogleApiclient();
        SigninButton();

        //Let's do modifying the views.
        mobile_signin_input.addTextChangedListener(new MyTextWatcher(mobile_signin_input));


    }

    private void SigninButton() {
        // Customize sign-in button. The sign-in button can be displayed in
// multiple sizes and color schemes. It can also be contextually
// rendered based on the requested scopes. For example. a red button may
// be displayed when Google+ scopes are requested, but a white button
// may be displayed when only basic profile is requested. Try adding the
// Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
// difference.
        signInButton = (SignInButton) findViewById(R.id.sign_in_google_button);
        signInButton.setSize(SignInButton.COLOR_DARK);
        signInButton.setScopes(gso.getScopeArray());

    }

    @OnClick(R.id.sign_in_google_button)
    public void LoginGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.signin_button)
    public void NextActivity() {
        Intent gotomapsIntent = new Intent(this, MapsActivity.class);
        startActivity(gotomapsIntent);
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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

    private class MyTextWatcher implements TextWatcher {
        View view;

        public MyTextWatcher(View view) {
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
                case R.id.mobile_number_signin_input:
                    validateMobile();
                    break;
                case R.id.password_signin_input:
                    validatePassword();
                    break;


            }


        }
    }

    private boolean validateMobile() {
        if (mobile_signin_input.getText().toString().trim().isEmpty() || mobile_signin_input.getText().toString().trim().length() < 10) {
            mobile_signin_layout.setError(getString(R.string.err_mobile_msg));
            requestFocus(mobile_signin_input);
            return false;

        } else {
            mobile_signin_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (password_signin_input.getText().toString().trim().isEmpty() || password_signin_input.getText().toString().trim().length() < 8) {
            password_signin_layout.setError(getString(R.string.err_password_msg));
            requestFocus(password_signin_input);
            return false;
        } else {
            password_signin_layout.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }
}
