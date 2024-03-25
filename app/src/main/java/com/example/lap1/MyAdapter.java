package com.example.lap1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Person> {
    Activity context;
    int Idlayout;
    ArrayList<Person> mylist;

    public MyAdapter(Activity context, int idlayout, ArrayList<Person> mylist) {
        super(context, idlayout,mylist);
        this.context = context;
        Idlayout = idlayout;
        this.mylist = mylist;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater myInF = context.getLayoutInflater();
//        convertView = myInF.inflate(Idlayout,null);
//        Person myper = mylist.get(position);
//
//    }
}
