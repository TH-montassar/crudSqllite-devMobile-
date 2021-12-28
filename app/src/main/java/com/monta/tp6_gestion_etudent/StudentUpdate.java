package com.monta.tp6_gestion_etudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.monta.tp6_gestion_etudent.Model.Student;
import com.monta.tp6_gestion_etudent.connxbd.studentOpenHelper;

public class StudentUpdate extends AppCompatActivity {
    EditText nameUpdate;
    EditText noteUpdate;
    Button update;

    studentOpenHelper db = new studentOpenHelper(StudentUpdate.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);

        nameUpdate=(EditText) findViewById(R.id.updateName);
        noteUpdate=(EditText) findViewById(R.id.UpdateNote);
        update=(Button) findViewById(R.id.save);
        int selectedpos=getIntent().getIntExtra("idselected",-1);
        Log.i("CheckedItem", "" + selectedpos + "");
        Student std=db.getStudent(selectedpos);
        nameUpdate.setText(std.getName());
        noteUpdate.setText(std.getNote().toString());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student std_update=new
                        Student(selectedpos,nameUpdate.getText().toString(),Double.parseDouble(noteUpdate.getText().toString()));
                int updatedone=db.updateStudent(std_update);
                Log.i("Updating", "" + updatedone + "");
                Intent i= new
                        Intent(getApplicationContext(),StudentList.class);
                startActivity(i);


            }
        });



    }
}