package com.example.hp.ieeepec.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorImpl implements Validator {
    private Pattern pattern;

    private static final String PASSWORD_PATTERN = "^[@$_A-Za-z0-9]{8,20}$";

    public PasswordValidatorImpl(){
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean validate(final String password){
        if(password != null){
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
        return false;
    }
}
