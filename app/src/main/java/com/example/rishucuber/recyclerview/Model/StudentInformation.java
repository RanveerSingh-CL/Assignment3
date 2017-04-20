package com.example.rishucuber.recyclerview.Model;

import java.io.Serializable;

/**
 * Created by rishucuber on 7/4/17.
 */

public class StudentInformation implements Serializable {
    private String mName, mSchool, mGender, mEmail;
    private long mRollno;


    public String getmName() {
        return mName;
    }

    public String getmSchool() {
        return mSchool;
    }

    public String getmGender() {
        return mGender;
    }

    public String getmEmail() {
        return mEmail;
    }

    public long getmRollno() {
        return mRollno;
    }

    public StudentInformation(String mName, String mSchool, String mGender, String mEmail, long mRollno) {
        this.mName = mName;
        this.mSchool = mSchool;
        this.mGender = mGender;
        this.mEmail = mEmail;
        this.mRollno = mRollno;
    }
}
