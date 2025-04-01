package com.duongpham26.b02_lifecycleactiviti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            EditText edtName = findViewById(R.id.edtName);
            EditText edtMSSV = findViewById(R.id.edtMSSV);
            EditText edtLop = findViewById(R.id.edtLop);
            EditText edtSDT = findViewById(R.id.edtSDT);
            EditText edtDevelopment = findViewById(R.id.edtDevelopment);

            RadioGroup rgYear = findViewById(R.id.rgYear);
            CheckBox cb1 = findViewById(R.id.cbMT);
            CheckBox cb2 = findViewById(R.id.cbMT2);
            CheckBox cb3 = findViewById(R.id.cbMT3);

            // Kiểm tra nếu trường nào trống
            if (edtName.getText().toString().trim().isEmpty()) {
                edtName.setError("Vui lòng nhập họ tên");
                edtName.requestFocus();
                return;
            }

            if (edtMSSV.getText().toString().trim().isEmpty()) {
                edtMSSV.setError("Vui lòng nhập MSSV");
                edtMSSV.requestFocus();
                return;
            }

            if (edtLop.getText().toString().trim().isEmpty()) {
                edtLop.setError("Vui lòng nhập lớp");
                edtLop.requestFocus();
                return;
            }

            if (edtSDT.getText().toString().trim().isEmpty()) {
                edtSDT.setError("Vui lòng nhập số điện thoại");
                edtSDT.requestFocus();
                return;
            }

            if (edtDevelopment.getText().toString().trim().isEmpty()) {
                edtDevelopment.setError("Vui lòng nhập kế hoạch phát triển");
                edtDevelopment.requestFocus();
                return;
            }

            // Kiểm tra nếu không có chuyên ngành nào được chọn
            if (!cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất một chuyên ngành", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra nếu chưa chọn năm học
            int checkedYearId = rgYear.getCheckedRadioButtonId();
            if (checkedYearId == -1) {
                Toast.makeText(this, "Vui lòng chọn năm học", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy dữ liệu hợp lệ
            RadioButton rbYear = findViewById(checkedYearId);
            String year = rbYear.getText().toString();

            String majors = "";
            if (cb1.isChecked()) majors += cb1.getText() + " ";
            if (cb2.isChecked()) majors += cb2.getText() + " ";
            if (cb3.isChecked()) majors += cb3.getText();

            // Tạo Intent để gửi dữ liệu
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("name", edtName.getText().toString());
            intent.putExtra("mssv", edtMSSV.getText().toString());
            intent.putExtra("lop", edtLop.getText().toString());
            intent.putExtra("sdt", edtSDT.getText().toString());
            intent.putExtra("year", year);
            intent.putExtra("majors", majors);
            intent.putExtra("development", edtDevelopment.getText().toString());

            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String message = data.getStringExtra("resultMessage");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}

