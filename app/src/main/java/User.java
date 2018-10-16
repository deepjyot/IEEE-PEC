package com.example.hp.ieeepec;

public class User {
    private String emailId;
    private String firstName;
    private String lastName;
    private String encodedPassword;
    private boolean isIeeeMember;
    private String ieeeMembershipId;

    public User(String emailId, String firstName, String lastName, String encodedPassword){
        isIeeeMember = false;
        ieeeMembershipId = "NA";
    }

    public User(String emailId, String firstName, String lastName, String encodedPassword, boolean isIeeeMember, String ieeeMembershipId) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.encodedPassword = encodedPassword;
        this.isIeeeMember = isIeeeMember;
        this.ieeeMembershipId = ieeeMembershipId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public boolean isIeeeMember() {
        return isIeeeMember;
    }

    public void setIeeeMember(boolean ieeeMember) {
        isIeeeMember = ieeeMember;
    }

    public String getIeeeMembershipId() {
        return ieeeMembershipId;
    }

    public void setIeeeMembershipId(String ieeeMembershipId) {
        this.ieeeMembershipId = ieeeMembershipId;
    }
}