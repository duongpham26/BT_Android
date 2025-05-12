package com.duongpham26.bt6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    Context context;
    List<Student> list;

    public StudentAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int i, View v, ViewGroup parent) {
        if (v == null)
            v = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);

        Student s = list.get(i);
        ((TextView) v.findViewById(R.id.txtName)).setText(s.getName());
        ((TextView) v.findViewById(R.id.txtMSSV)).setText(s.getMssv());

        byte[] imgData = s.getAvatar();
        if (imgData != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            ((ImageView) v.findViewById(R.id.imgAvatar)).setImageBitmap(bitmap);
        }

        return v;
    }
}

