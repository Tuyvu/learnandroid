package com.example.lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivitySub extends AppCompatActivity {
    protected Button btnOk,btnCancel;
    protected EditText editTen,editSDT;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        btnOk = findViewById(R.id.btnOK);
        editSDT = findViewById(R.id.editSDT);
        editTen = findViewById(R.id.editTen);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        Bundle d = intent.getExtras();
        if(d!= null){
            String hoten = d.getString("Name");
            String SDT = d.getString("SDT");
            editTen.setText(hoten);
            editSDT.setText(SDT);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTen.getText().toString();
                String SDT = editSDT.getText().toString();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("Name", name);
                b.putString("SDT", SDT);
                intent.putExtras(b);
                setResult(150, intent);
                finish();
            }
        });


    }
}