package com.duongpham26.bt03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherActivity extends AppCompatActivity {
    private static final int RESULT_CODE = 88;

    EditText editTeacherInput;
    Button btnSendBack;
    String submittedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        editTeacherInput = findViewById(R.id.editTeacherInput);
        btnSendBack = findViewById(R.id.btnSendBack);

        // Lấy dữ liệu từ MainActivity
        Intent intent = getIntent();
        if (intent.hasExtra("submittedText")) {
            submittedText = intent.getStringExtra("submittedText");
            editTeacherInput.setText(submittedText); // Hiển thị nội dung học sinh nhập
        }

        btnSendBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correctedText = editTeacherInput.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("correctedText", correctedText); // Gửi lại nội dung đã chỉnh sửa
                setResult(RESULT_CODE, resultIntent);
                finish(); // Đóng TeacherActivity và quay lại MainActivity
            }
        });
    }
}
