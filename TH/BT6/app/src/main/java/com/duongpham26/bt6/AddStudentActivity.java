package com.duongpham26.bt6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddStudentActivity extends AppCompatActivity {
    EditText edtName, edtMSSV;
    ImageView imgAvatar;
    byte[] avatarBytes;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtName = findViewById(R.id.edtName);
        edtMSSV = findViewById(R.id.edtMSSV);
        imgAvatar = findViewById(R.id.imgAvatar);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        imgAvatar.setImageURI(uri);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            avatarBytes = bitmapToBytes(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        imgAvatar.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(i);
        });

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String mssv = edtMSSV.getText().toString();
            if (!name.isEmpty() && !mssv.isEmpty() && avatarBytes != null) {
                Student s = new Student(0, name, mssv, avatarBytes);
                new StudentDatabaseHelper(this).insertStudent(s);
                finish();
            }
        });
    }

    private byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}

