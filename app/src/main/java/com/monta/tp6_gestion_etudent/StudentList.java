package com.monta.tp6_gestion_etudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.monta.tp6_gestion_etudent.Model.Student;
import com.monta.tp6_gestion_etudent.connxbd.studentOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity {
    ListView listViewstudent;
    Button update, delete;


    List<Student> students = new ArrayList<>();
    studentOpenHelper db = new studentOpenHelper(StudentList.this);
    int selectedpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        listViewstudent = findViewById(R.id.List);


        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnSupprime);


        students = db.getAllStudent();
        ArrayList<String> lv_items = new ArrayList<String>();
        ArrayList<Integer> ids_list = new ArrayList<Integer>();


        for (Student st : students) {
            lv_items.add(st.getId() + " " + st.getName() + " " + st.getNote());
            ids_list.add(st.getId());
        }
        ArrayAdapter<String> adt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, lv_items);
        listViewstudent.setAdapter(adt);


        listViewstudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedpos = position;
                Log.i("CheckedItem", "" + selectedpos + "" + ids_list.get(selectedpos));
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StudentUpdate.class);
                i.putExtra("idselected", ids_list.get(selectedpos));
                startActivity(i);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder adb = new
                        AlertDialog.Builder(StudentList.this);
                adb.setTitle("Supprimer?");
                adb.setMessage("Vous voulez vraiment supprimer l'étudiant " + ids_list.get(selectedpos));
                adb.setNegativeButton("Annuler", null);
                adb.setPositiveButton("Supprimer", new
                        AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteStudent(ids_list.get(selectedpos));
                                lv_items.remove(selectedpos);
                                ids_list.remove(selectedpos);
                                adt.notifyDataSetChanged();
                                listViewstudent.clearChoices();

                            }
                        });
                adb.show();


            }
        });


    }
}