package com.duongpham26.bt6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {
    TextView txtName, txtMSSV;
    ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

//        txtName = findViewById(R.id.txtDetailName);
//        txtMSSV = findViewById(R.id.txtDetailMSSV);
//        imgAvatar = findViewById(R.id.imgDetailAvatar);
//
//        Student s = (Student) getIntent().getSerializableExtra("student");
//
//        txtName.setText(s.getName());
//        txtMSSV.setText(s.getMssv());
//        Bitmap bitmap = BitmapFactory.decodeByteArray(s.getAvatar(), 0, s.getAvatar().length);
//        imgAvatar.setImageBitmap(bitmap);

//        Intent intent = getIntent();
//
//
//        byte[] avatarBytes = intent.getByteArrayExtra("avatar");
//        if (avatarBytes != null)
//            imgAvatar.setImageBitmap(BitmapFactory.decodeByteArray(avatarBytes, 0, avatarBytes.length));

        ImageView img = findViewById(R.id.imgDetailAvatar);
        TextView txtName = findViewById(R.id.txtDetailName);
        TextView txtMSSV = findViewById(R.id.txtDetailMSSV);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String mssv = i.getStringExtra("mssv");
        byte[] avatar = i.getByteArrayExtra("avatar");

        txtName.setText(name);
        txtMSSV.setText(mssv);
        if (avatar != null && avatar.length > 0) {
            Bitmap bmp = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
            img.setImageBitmap(bmp);
        } else {
            img.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }
}


