package com.duongpham26.bt6;

public class Student {
    private int id;
    private String name;
    private String mssv;
    private byte[] avatar;

    public Student(int id, String name, String mssv, byte[] avatar) {
        this.id = id;
        this.name = name;
        this.mssv = mssv;
        this.avatar = avatar;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMssv() { return mssv; }
    public byte[] getAvatar() { return avatar; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMssv(String mssv) { this.mssv = mssv; }
    public void setAvatar(byte[] avatar) { this.avatar = avatar; }
}


