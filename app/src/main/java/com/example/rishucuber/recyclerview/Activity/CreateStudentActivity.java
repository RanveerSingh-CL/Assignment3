package com.example.rishucuber.recyclerview.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.rishucuber.recyclerview.Model.StudentInformation;
import com.example.rishucuber.recyclerview.R;

import java.util.ArrayList;

public class CreateStudentActivity extends AppCompatActivity {
    private EditText mEditTextName, mEditTextSchool, mEditTextEmail, mEditTextRollno;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonMale, mRadioButtonFemale, getmRadioButtonother, mRadiobutton;
    private Button mButtonSave;
    private final static String GENDERMALE = "Male";
    private final static String GENDERFEMALE = "Female";
    private final static String GENDEROTHER = "Other";
    private int mPositionInDataSet = -1;
    String gender = null;
    private String name, school, email, rollno;

    Bundle extras;
    ArrayList<String> getdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        extras = getIntent().getExtras();
        mEditTextName = (EditText) findViewById(R.id.editText);
        mEditTextSchool = (EditText) findViewById(R.id.editText1);
        mEditTextEmail = (EditText) findViewById(R.id.editText2);
        mEditTextRollno = (EditText) findViewById(R.id.editText3);
        mRadioButtonFemale = (RadioButton) findViewById(R.id.female);
        mRadioButtonMale = (RadioButton) findViewById(R.id.male);
        getmRadioButtonother = (RadioButton) findViewById(R.id.other);
        mButtonSave = (Button) findViewById(R.id.button2);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        gender = GENDERMALE;
                        break;
                    case R.id.female:
                        gender = GENDERFEMALE;
                        break;
                    case R.id.other:
                        gender = GENDEROTHER;
                        break;
                    default:
                        gender = GENDEROTHER;
                        break;


                }
            }
        });
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getdata();
                    StudentInformation studentInformation = new StudentInformation(name, school, gender, email, Long.parseLong(rollno));
                    Intent intent = new Intent();
                    // intent.putExtra("position",mPositionInDataSet);
                    intent.putExtra("student", studentInformation);
                    setResult(RESULT_OK, intent);
                    finish();

            }
        });

        if (extras != null) {
            setDatatoViews();
        }


    }

    public void getdata() {

        name = mEditTextName.getText().toString().trim();
        school = mEditTextSchool.getText().toString().trim();
        email = mEditTextEmail.getText().toString().trim();
        rollno = mEditTextRollno.getText().toString().trim();


    }

    private void setDatatoViews() {


        getdata = extras.getStringArrayList("position");



        if (getdata != null) {
            mEditTextName.setText(getdata.get(0));
            mEditTextSchool.setText(getdata.get(1));
            mEditTextEmail.setText(getdata.get(3));
            if (getdata.get(2).equals("Male"))
                mRadioButtonMale.setChecked(true);
            else if (getdata.get(2).equals("Female"))
                mRadioButtonFemale.setChecked(true);
            else if (getdata.get(2).equals("Other"))
                getmRadioButtonother.setChecked(true);
            else {
            }
//                if (mRadioButtonMale.isChecked()) mRadioButtonMale.setChecked(true);
//                else if (mRadioButtonFemale.isChecked()) mRadioButtonFemale.setChecked(true);
            //               else getmRadioButtonother.setChecked(true);
            mEditTextRollno.setText(getdata.get(4));

        }

    }


}
