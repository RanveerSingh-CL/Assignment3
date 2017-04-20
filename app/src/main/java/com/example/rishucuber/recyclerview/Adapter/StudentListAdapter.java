package com.example.rishucuber.recyclerview.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rishucuber.recyclerview.Activity.CreateStudentActivity;
import com.example.rishucuber.recyclerview.Model.StudentInformation;
import com.example.rishucuber.recyclerview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rishucuber on 7/4/17.
 */

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.MyViewHolder> {
    List<StudentInformation> data = Collections.emptyList();
    Context context;

    public StudentListAdapter(Context context, List<StudentInformation> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_list_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final StudentInformation current = data.get(position);
        holder.name.setText(current.getmName());
        holder.school.setText(current.getmSchool());
        holder.gender.setText(current.getmGender());
        holder.email.setText(current.getmEmail());
        holder.rollno.setText(String.valueOf(current.getmRollno()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle("Select Option");
                CharSequence[] dialogueItems = {"View", "Edit", "Delete"};
                ad.setItems(dialogueItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                ArrayList<String> view = new ArrayList<String>();
                                view.add(current.getmName());
                                view.add(current.getmSchool());
                                        view.add(current.getmGender());
                                view.add(current.getmEmail());
                                view.add(String.valueOf(current.getmRollno()));
                                view.add(String.valueOf(position));
                                Intent viewIntent = new Intent(context, CreateStudentActivity.class);
                                viewIntent.putStringArrayListExtra("position", view);
                                context.startActivity(viewIntent);
                                break;
                            case 1:
                                ArrayList<String> view2 = new ArrayList<String>();
                                view2.add(current.getmName());
                                view2.add(current.getmSchool());
                                view2.add(current.getmGender());
                                view2.add(current.getmEmail());
                                view2.add(String.valueOf(current.getmRollno()));
                                view2.add(String.valueOf(position));
                                Intent editIntent = new Intent(context, CreateStudentActivity.class);

                                context.startActivity(editIntent);
                                break;
                            case 2:
                                System.out.println("Del Del");
                                data.remove(position);
                                notifyDataSetChanged();

                                break;


                        }
                    }
                });


                ad.show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, school, email, gender, rollno;

        public MyViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            school = (TextView) itemView.findViewById(R.id.school);
            email = (TextView) itemView.findViewById(R.id.email);
            gender = (TextView) itemView.findViewById(R.id.gender);
            rollno = (TextView) itemView.findViewById(R.id.rollno);


        }
    }


    public void addStudent(StudentInformation student) {
        data.add(student);
        notifyItemInserted(data.size());
    }

    public void viewStudentDetail() {


    }

    public void editStudentDetail() {

    }

    public void deleteStudentDetail() {

    }


}
