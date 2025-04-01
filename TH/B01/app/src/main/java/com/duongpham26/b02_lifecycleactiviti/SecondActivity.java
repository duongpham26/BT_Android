package com.duongpham26.b02_lifecycleactiviti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView tvDisplayData;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvDisplayData = findViewById(R.id.tvDisplayData);
        btnBack = findViewById(R.id.btnBack);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String mssv = intent.getStringExtra("mssv");
        String lop = intent.getStringExtra("lop");
        String sdt = intent.getStringExtra("sdt");
        String year = intent.getStringExtra("year");
        String majors = intent.getStringExtra("majors");
        String developmentPlan = intent.getStringExtra("development");

        // Hiển thị dữ liệu
        String display = "Họ tên: " + name + "\n"
                + "MSSV: " + mssv + "\n"
                + "Lớp: " + lop + "\n"
                + "SĐT: " + sdt + "\n"
                + "Sinh viên năm: " + year + "\n"
                + "Chuyên ngành: " + majors + "\n"
                + "Kế hoạch phát triển:\n" + developmentPlan;
        tvDisplayData.setText(display);

        // Xử lý nút Back
        btnBack.setOnClickListener(v -> {
            // Gửi dữ liệu phản hồi (nếu cần)
            Intent resultIntent = new Intent();
            resultIntent.putExtra("resultMessage", "Dữ liệu đã được xem");
            setResult(RESULT_OK, resultIntent);
            finish(); // Đóng activity
        });
    }
}