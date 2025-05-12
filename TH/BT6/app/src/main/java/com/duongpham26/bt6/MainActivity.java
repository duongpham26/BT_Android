package com.duongpham26.bt6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Student> studentList;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lvStudent);

        studentList = new StudentDatabaseHelper(this).getAllStudents();
        adapter = new StudentAdapter(this, studentList);
        lv.setAdapter(adapter);

//        lv.setOnItemClickListener((parent, view, position, id) -> {
//            Intent i = new Intent(this, StudentDetailActivity.class);
//            i.putExtra("student", (CharSequence) studentList.get(position));
//            startActivity(i);
//        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Student s = studentList.get(position);
            Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
            intent.putExtra("name", s.getName());
            intent.putExtra("mssv", s.getMssv());
            intent.putExtra("avatar", s.getAvatar());
            startActivity(intent);
        });

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            startActivity(new Intent(this, AddStudentActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentList.clear();
        studentList.addAll(new StudentDatabaseHelper(this).getAllStudents());
        adapter.notifyDataSetChanged();
    }
}


