package com.example.pessy.corpacabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vstechlab.easyfonts.EasyFonts;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.annotations.BindLayout;
import io.github.sporklibrary.annotations.BindView;

@BindLayout(R.layout.activity_login_screen)
public class LoginScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "Google Sign-In";
    GoogleApiClient mGoogleApiClient;
    GoogleSignInOptions gso;
    SignInButton signInButton;

    @BindView(R.id.tv_logo_home)
    TextView app_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spork.bind(this);
        app_logo.setTypeface(EasyFonts.recognition(this));


        //For Google Sign-In It requires three methods
        GoogleSigninoptions();
        GoogleApiclient();
        SigninButton();
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
            String name = acct.getDisplayName();
            String email = acct.getEmail();
            Uri photo = acct.getPhotoUrl();
            //If email address is present in db then go inside
            //search it in db
            if(acct.getDisplayName()!=null)// if email is not there in DB
            {
                //go to register more activity with name and email
                Intent i = new Intent(this, RegisterMoreActivity.class);
                //pass name and email
                Bundle extras = new Bundle();
                extras.putString("EXTRA_NAME", name);
                extras.putString("EXTRA_EMAIL", email);
                i.putExtras(extras);
                startActivity(i);
            } else // if email is there in db.
            {
                Toast.makeText(this, "Signed In As " + acct.getDisplayName(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, NavDrawer.class));

            }

            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
            Toast.makeText(this, "Check your Google Mail Address", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Check your Internet Settings", Toast.LENGTH_LONG).show();
    }
}







