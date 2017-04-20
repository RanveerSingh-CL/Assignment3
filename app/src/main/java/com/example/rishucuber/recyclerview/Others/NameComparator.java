package com.example.rishucuber.recyclerview.Others;


import com.example.rishucuber.recyclerview.Model.StudentInformation;

import java.util.Comparator;


public class NameComparator implements Comparator<StudentInformation> {
    @Override
    public int compare(StudentInformation o1, StudentInformation
            o2) {
        return o1.getmName().compareTo(o2.getmName());
    }
}
