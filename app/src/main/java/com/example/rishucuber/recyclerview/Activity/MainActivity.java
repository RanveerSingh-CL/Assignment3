package com.example.rishucuber.recyclerview.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.rishucuber.recyclerview.Adapter.StudentListAdapter;
import com.example.rishucuber.recyclerview.Model.StudentInformation;
import com.example.rishucuber.recyclerview.Others.NameComparator;
import com.example.rishucuber.recyclerview.Others.RollComparator;
import com.example.rishucuber.recyclerview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentListAdapter adapter;
    private Button mButtonCreateStudent;
    private ToggleButton mtoggleButton;
    private Spinner mSort;
    public final static boolean ACTIVITY_MODE_VIEW_DATA = false;
    public final static int ACTIVITY_MODE_ADD_DATA = 2;
    public final static int ACTIVITY_MODE_EDIT_DATA = 3;
    int position;

    private ArrayList<StudentInformation> mArrayListStudentData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mtoggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        mArrayListStudentData = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new StudentListAdapter(this,mArrayListStudentData);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mSort = (Spinner) findViewById(R.id.sortSpinner);
        // Adding options in Spinner
        List<String> arraylistSpinner = new ArrayList<String>();
        arraylistSpinner.add("Sort By Name");
        arraylistSpinner.add("Sort Bu Roll");
        // Creating Adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arraylistSpinner );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSort.setAdapter(dataAdapter);
        mSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    sortDataByName();
                else
                    sortDataByRoll();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });




        mtoggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

                }
            }
        });


        mButtonCreateStudent = (Button) findViewById(R.id.createButton);
        mButtonCreateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createStudentIntent = new Intent(MainActivity.this,CreateStudentActivity.class);
                       createStudentIntent.putExtra("mode", ACTIVITY_MODE_ADD_DATA);
                        startActivityForResult(createStudentIntent,1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case 1 :if(resultCode == RESULT_OK){
                StudentInformation student = (StudentInformation) data.getSerializableExtra("student");

                adapter.addStudent(student);
                break;

            }
        }
    }
    private void sortDataByRoll() {
        Collections.sort(mArrayListStudentData, new RollComparator());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    /**
     * Sorts the data in the recycler view by RollNo. using @{@link NameComparator}
     */
    private void sortDataByName() {
        Collections.sort(mArrayListStudentData, new NameComparator());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    /**
     * Switch between the RecyclerView layout manager -Grid and Linear Layout
     */

}

