package com.example.saedf.app7learn.dataModel;

public class User {
    private String firstName="" ;
    private String lastname="";
    private boolean isHtmlExpert=false;
    private boolean isJavaExpert=false;
    private boolean isCSSExpert=false;
    public final static byte MALE=0;
    public final static byte FEMALE=1;
    private byte Gender=MALE;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isHtmlExpert() {
        return isHtmlExpert;
    }

    public void setHtmlExpert(boolean htmlExpert) {
        isHtmlExpert = htmlExpert;
    }

    public boolean isJavaExpert() {
        return isJavaExpert;
    }

    public void setJavaExpert(boolean javaExpert) {
        isJavaExpert = javaExpert;
    }

    public boolean isCSSExpert() {
        return isCSSExpert;
    }

    public void setCSSExpert(boolean CSSExpert) {
        isCSSExpert = CSSExpert;
    }

    public byte getGender() {
        return Gender;
    }

    public void setGender(byte gender) {
        Gender = gender;
    }
}
