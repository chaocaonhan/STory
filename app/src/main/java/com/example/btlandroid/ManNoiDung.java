package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ManNoiDung extends AppCompatActivity {
    TextView tenTruyenTxt, noiDungTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_noi_dung);
        
        tenTruyenTxt = findViewById(R.id.tentruyen);
        noiDungTxt = findViewById(R.id.noidung);

        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("tentruyen");
        String noiDung = intent.getStringExtra("noidung");

        tenTruyenTxt.setText(tenTruyen);
        noiDungTxt.setText(noiDung);

        noiDungTxt.setMovementMethod(new ScrollingMovementMethod());
    }
}