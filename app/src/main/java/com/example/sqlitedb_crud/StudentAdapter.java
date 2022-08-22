package com.example.sqlitedb_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModel> {

    public StudentAdapter(@NonNull Context context, ArrayList<StudentModel> StudentList) {
        super(context, R.layout.activity_list_views, StudentList);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StudentModel list = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_views, parent, false);

        TextView StName = convertView.findViewById(R.id.StudentName);
        TextView StRollNo = convertView.findViewById(R.id.StudentRollNo);
        TextView StEnrolled = convertView.findViewById(R.id.StudentEnrolled);

        StName.setText(list.getName());
        StRollNo.setText(Integer.toString(list.getRollNmber()));
        if(list.isEnroll())
            StEnrolled.setText("Enrolled");
        else
            StEnrolled.setText("Not Enrolled");
        return convertView;
    }
}
