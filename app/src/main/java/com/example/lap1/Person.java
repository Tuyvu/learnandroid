package com.example.lap1;

import android.widget.ImageView;

public class Person {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String HoTen;
    public ImageView images;

    String img;
    String SDT;
    int gt;
    String Que;

    public ImageView getImages() {
        return images;
    }

    public void setImages(ImageView images) {
        this.images = images;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setGt(int gt) {
        this.gt = gt;
    }

    public void setQue(String que) {
        Que = que;
    }


    public String getSDT() {
        return SDT;
    }

    public int getGt() {
        return gt;
    }

    public String getQue() {
        return Que;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Person(String hoTen, String SDT, int gt, String que) {
//        img =img;
        HoTen = hoTen;
        this.SDT = SDT;
        this.gt = gt;
        Que = que;
    }

    public Person(int id, String hoTen, String images, String SDT) {
        this.id = id;
        HoTen = hoTen;
        this.img = images;
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return HoTen + "-" + SDT + "-" + gt + "-" + Que;
    }
}
