package com.monta.tp6_gestion_etudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.monta.tp6_gestion_etudent.Model.Student;
import com.monta.tp6_gestion_etudent.connxbd.studentOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name, note;
    ImageView listedudent;
    Button ajoute;
    List  <Student> students =new ArrayList<>();
    studentOpenHelper db =new studentOpenHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameid);
        note = findViewById(R.id.noteid);

        ajoute = findViewById(R.id.btnid);

        listedudent=findViewById(R.id.listid);
        ajoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namestudent =name.getText().toString();
                String notestudent =note.getText().toString();

                if (namestudent.isEmpty() || notestudent.isEmpty() ){


                    Toast.makeText(MainActivity.this, "remplir first", Toast.LENGTH_SHORT).show();

                }else{
                    Double notestudents= Double.parseDouble(notestudent);
                    Student student =new Student(namestudent,notestudents);
                    db.addStudent(student);
                    name.setText("");
                    note.setText("");
                    Toast.makeText(MainActivity.this, "etudent ajouter", Toast.LENGTH_SHORT).show();

                }

            }
        });
        listedudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,StudentList.class);
                startActivity(intent);
            }
        });



    }
}