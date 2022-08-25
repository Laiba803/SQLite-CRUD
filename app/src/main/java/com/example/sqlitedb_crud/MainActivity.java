package com.example.sqlitedb_crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd, buttonViewAll;
    EditText editName, editRollNumber;
    Switch switchIsActive;
    ListView listViewStudent;
    ArrayList<StudentModel> list;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        editName = findViewById(R.id.editTextName);
        editRollNumber = findViewById(R.id.editTextRollNumber);
        switchIsActive = findViewById(R.id.switchStudent);
        listViewStudent = findViewById(R.id.listViewStudent);

        DBHelper dbHelper = new DBHelper(MainActivity.this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;
            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                    //Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                if(buttonAdd.getText() == "Update"){
                    dbHelper.updateStudent(studentModel, position);
                    buttonAdd.setText("Add");
                    list = dbHelper.getAllStudents();
                    StudentAdapter studentAdapter = new StudentAdapter(MainActivity.this, list);
                    listViewStudent.setAdapter(studentAdapter);
                    studentAdapter.notifyDataSetChanged();
                }
                else{
                    dbHelper.addStudent(studentModel);
                }
            }
        });

        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = dbHelper.getAllStudents();
                StudentAdapter studentAdapter = new StudentAdapter(MainActivity.this, list);
                listViewStudent.setAdapter(studentAdapter);
            }
        });

        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            StudentModel student;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                Log.d("itemclick", "onClick: yes in 0");
                position = i;
                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("itemclick", "onClick: yes in 1");
                        student = dbHelper.getOneStudent(i);
                        Log.d("itemclick", "onClick: " + student);
                        Log.d("itemclick", "onClick: yes in 2");
                        editName.setText(student.getName());
                        Log.d("itemclick", "onClick: yes in 3");
                        //editName.setText(((TextView) view).getText().toString());
                        //editRollNumber.setText(list.get(i).getRollNmber());
                        Log.d("itemclick", "onClick: yes in 4");
                        if(student.isEnroll()) {
                            Log.d("itemclick", "onClick: yes in 5 Enrolled");
                            switchIsActive.setChecked(true);
                        }
                        else {
                            Log.d("itemclick", "onClick: yes in 5 Note Enrolled");
                            switchIsActive.setChecked(false);
                        }
                        Log.d("itemclick", "onClick: yes in 5");
                        buttonAdd.setText("Update");
                        Log.d("itemclick", "onClick: yes in 6");
                        //editName.setText(((TextView) view).getText().toString());
                        dialog.dismiss();
                        //Intent intent = new Intent(MainActivity.this, DialogBox.class);
                        //startActivity(intent);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteStudent(i);
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}