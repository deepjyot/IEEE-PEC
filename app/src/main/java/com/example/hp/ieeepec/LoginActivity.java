package com.example.hp.ieeepec;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hp.ieeepec.util.EmailValidatorImpl;
import com.example.hp.ieeepec.util.PasswordValidatorImpl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.net.URL;

import static android.os.SystemClock.sleep;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private GoogleSignInClient mGoogleSignInClient;

    private final String TAG = this.getClass().toString();
    private static final int RC_SIGN_IN = 0;

    private final EmailValidatorImpl emailValidator = new EmailValidatorImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initialize();
    }

    @Override
    public void onStart(){
        super.onStart();
        //For checking if a previous account is already logged in the app or not
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    public void cancel(View view){
        //Dummy operation to check working
        if(view.getId() == R.id.login_cancel_button){
            finish();
        }
    }

    public void login(View view){
        TextInputEditText emailIdEditText = findViewById(R.id.login_email_id_edit_text);
        TextInputLayout emailIdInputLayout = findViewById(R.id.login_email_id_input_layout);

        if(emailValidator.validate(emailIdEditText.getText().toString())){
            finish();
            Intent intent = new Intent(this, RSSFeedActivity.class);
            startActivity(intent);
        }
        else{
            Snackbar snackbar = Snackbar.make(view, "Login Unsuccessful", Snackbar.LENGTH_SHORT);
            snackbar.show();

            //TODO: Add validating user password with the one stored in the DB
            //Also add checking user emailId stored in the DB
            if(!emailValidator.validate(emailIdEditText.getText().toString()))
                emailIdInputLayout.setError("Incorrect email Id");
        }

    }

    public void googleSignIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut(View view){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Snackbar snackbar = Snackbar.make(view, "Logged out", Snackbar.LENGTH_SHORT);
                        snackbar.show();

                        updateUI(null);
                    }
                });
    }

    private void updateUI(GoogleSignInAccount account){
        MaterialButton signOutButton = findViewById(R.id.sign_out_button);
        SignInButton googleSignInButton = findViewById(R.id.google_sign_in_button);

        if(account != null){
            String name = account.getDisplayName();
            String email = account.getEmail();
            Uri imageURL = account.getPhotoUrl();

            Log.w(TAG,"NAME: " + name);
            Log.w(TAG,"EMAIL: " + email);
            Log.w(TAG,"IMAGE_URL: " + imageURL);

            Intent intent = new Intent(this, RSSFeedActivity.class);
            startActivity(intent);
            finish();

        }
        else{
            googleSignInButton.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.google_sign_in_button:
                googleSignIn();
                break;

            case R.id.login_button:
                login(view);
                break;

            case R.id.sign_out_button:
                signOut(view);
                break;

            case R.id.login_cancel_button:
                cancel(view);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            //Do Nothing
            return;
        }
        TextInputEditText emailIdEditText = findViewById(R.id.login_email_id_edit_text);
        TextInputLayout emailIdInputLayout = findViewById(R.id.login_email_id_input_layout);

        if(!emailValidator.validate(emailIdEditText.getText().toString())){
            emailIdInputLayout.setError("Incorrect Email Id");
        }
        else{
            emailIdInputLayout.setErrorEnabled(false);
        }
    }

    private void initialize(){
        SignInButton googleSignInButton = findViewById(R.id.google_sign_in_button);
        googleSignInButton.setSize(SignInButton.SIZE_WIDE);

        //Creating onClickListeners
        googleSignInButton.setOnClickListener(this);
        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.login_cancel_button).setOnClickListener(this);

        //Adding OnFocusChangeListeners
        findViewById(R.id.login_email_id_edit_text).setOnFocusChangeListener(this);
    }
}
