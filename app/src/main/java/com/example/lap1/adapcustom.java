package com.example.lap1;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class adapcustom extends ArrayAdapter<Person> {
    private LayoutInflater inflater;
    private ArrayList<Person> contacts;
    public adapcustom(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
