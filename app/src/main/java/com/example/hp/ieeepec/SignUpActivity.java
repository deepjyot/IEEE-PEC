package com.example.hp.ieeepec;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.hp.ieeepec.util.EmailValidatorImpl;
import com.example.hp.ieeepec.util.PasswordValidatorImpl;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private final String TAG = this.getClass().toString();
    private final EmailValidatorImpl emailValidator = new EmailValidatorImpl();
    private final PasswordValidatorImpl passwordValidator = new PasswordValidatorImpl();
    private TextInputLayout emailIdInput;
    private TextInputLayout passwordInput;
    private TextInputLayout confirmPasswordInput;
    private TextInputEditText emailIdEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initialize();
    }

    public void cancel(){
        //Dummy operation to check working
            finish();
    }

    public void signUp(View view){

        if(emailValidator.validate(emailIdEditText.getText().toString()) &&
                passwordValidator.validate(passwordEditText.getText().toString()) &&
                passwordEditText.getText().toString().compareTo(confirmPasswordEditText.getText().toString()) == 0){

            emailIdInput.setErrorEnabled(false);
            confirmPasswordInput.setErrorEnabled(false);
            passwordInput.setErrorEnabled(false);

            Snackbar snackbar = Snackbar.make(view, "Account created", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        else{
            if(!emailValidator.validate(emailIdEditText.getText().toString())){
                emailIdInput.setError("Incorrect email Id");
            }
            if(!passwordValidator.validate(passwordEditText.getText().toString())){
                passwordInput.setError("Password length must be between 8 to 20 characters and" +
                        " only allowed special characters are @$_");
            }
            if(passwordEditText.getText().toString().compareTo(confirmPasswordEditText.getText().toString()) != 0){
                confirmPasswordInput.setError("Password don't match");
            }
        }
    }

    private void toggleMembership() {
        Switch membershipSwitch = findViewById(R.id.membership_switch);
        TextInputEditText ieeeMembershipEditText = findViewById(R.id.ieee_membership_id_edit_text);
        TextInputLayout ieeeMembershipInput = findViewById(R.id.ieee_membership_id_input);

        if(membershipSwitch.isChecked()){
            ieeeMembershipEditText.setVisibility(View.VISIBLE);
            ieeeMembershipInput.setVisibility(View.VISIBLE);
        }
        else{
            ieeeMembershipEditText.setVisibility(View.GONE);
            ieeeMembershipInput.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.sign_up_next_button:
                signUp(view);
                break;

            case R.id.sign_up_cancel_button:
                cancel();
                break;

            case R.id.membership_switch:
                toggleMembership();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();

        if(hasFocus){
            //Do nothing
            return;
        }

        if(id == R.id.sign_up_confirm_password_edit_text){
            if(passwordEditText.getText().toString().compareTo(confirmPasswordEditText.getText().toString()) != 0){
                confirmPasswordInput.setError("Password don't match");
            }
            else{
                confirmPasswordInput.setErrorEnabled(false);
            }

        }
        else if(id == R.id.sign_up_password_edit_text){
            if(!passwordValidator.validate(passwordEditText.getText().toString())){
                passwordInput.setError("Password length must be between 8 to 20 characters and" +
                        " only allowed special characters are @$_");
            }
            else{
                passwordInput.setErrorEnabled(false);
            }
        }
        else if(id == R.id.sign_up_email_id_edit_text){
            if(!emailValidator.validate(emailIdEditText.getText().toString())){
                emailIdInput.setError("Incorrect email Id");
            }
            else{
                emailIdInput.setErrorEnabled(false);
            }
        }
    }

    private void initialize(){

        //Initializing variables
        emailIdInput = findViewById(R.id.sign_up_email_id_input);
        passwordInput = findViewById(R.id.sign_up_password_input);
        confirmPasswordInput = findViewById(R.id.sign_up_confirm_password_input);
        emailIdEditText = findViewById(R.id.sign_up_email_id_edit_text);
        passwordEditText = findViewById(R.id.sign_up_password_edit_text);
        confirmPasswordEditText = findViewById(R.id.sign_up_confirm_password_edit_text);

        //Setting onClickListener
        findViewById(R.id.sign_up_cancel_button).setOnClickListener(this);
        findViewById(R.id.sign_up_next_button).setOnClickListener(this);
        findViewById(R.id.membership_switch).setOnClickListener(this);

        //Adding OnFocusChangeListeners
        emailIdEditText.setOnFocusChangeListener(this);
        passwordEditText.setOnFocusChangeListener(this);
        confirmPasswordEditText.setOnFocusChangeListener(this);

    }
}
//TODO: Just found that the shortcut menu that pops up when we copy or paste something is looking weird. Have to fix it.