package com.duongpham26.bt03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_TEACHER = 99; // Định nghĩa requestCode

    private static final int RESULT_CODE = 88;

    EditText editStudentText, editTeacherText;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editStudentText = findViewById(R.id.editStudentText);
        editTeacherText = findViewById(R.id.editTeacherText);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String submittedText = editStudentText.getText().toString();
                Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                intent.putExtra("submittedText", submittedText);
                startActivityForResult(intent, REQUEST_CODE_TEACHER); // Gửi requestCode
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TEACHER && resultCode == RESULT_CODE && data != null) {
            String correctedText = data.getStringExtra("correctedText");
            editTeacherText.setText(correctedText); // Hiển thị đoạn đã chỉnh sửa
        }
    }
}
