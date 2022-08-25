package com.example.sqlitedb_crud;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
//        Button edit = convertView.findViewById(R.id.editbutton);
//        Button del = convertView.findViewById(R.id.delbutton);
//        EditText editName =convertView.findViewById(R.id.editTextName);
//        EditText editRollNumber = convertView.findViewById(R.id.editTextRollNumber);
//        Switch isSwitch = convertView.findViewById(R.id.switchStudent);

        StName.setText(list.getName());
        StRollNo.setText(Integer.toString(list.getRollNmber()));
        if(list.isEnroll())
            StEnrolled.setText("Enrolled");
        else
            StEnrolled.setText("Not Enrolled");

//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("itemclick", "onClick: yes in 1");
//                //editName.setText(StName.getText().toString());
////                editName.setText(list.getName(), TextView.BufferType.EDITABLE);
////                editName.setText(list.getName());
//                Log.d("itemclick", "onClick: yes in 2");
//                //editName.setText(StRollNo.getText().toString());
////                editRollNumber.setText(list.getRollNmber(), TextView.BufferType.EDITABLE);
////                editRollNumber.setText(list.getRollNmber());
//                Log.d("itemclick", "onClick: yes in 3");
//                if(true){
//                    Log.d("itemclick", "onClick: yes in 4");
//                    isSwitch.setChecked(true);
//                }
//                else{
//                    isSwitch.setChecked(false);
//                }
//            }
//        });
//        del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DBHelper dbHelper = new DBHelper(getContext());
//                dbHelper.deleteStudent(position);
//                remove(list);
//            }
//        });
        return convertView;
    }
}
