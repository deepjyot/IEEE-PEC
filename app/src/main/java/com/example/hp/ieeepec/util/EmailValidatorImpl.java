package com.example.hp.ieeepec.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorImpl implements Validator {
    private Pattern pattern;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public EmailValidatorImpl(){
        pattern = Pattern.compile(this.EMAIL_PATTERN);
    }

    @Override
    public boolean validate(final String emailId){
        if(emailId != null){
            Matcher matcher = pattern.matcher(emailId);
            return matcher.matches();
        }
        return false;
    }
}
