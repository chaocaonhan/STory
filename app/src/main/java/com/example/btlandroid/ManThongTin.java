package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class ManThongTin extends AppCompatActivity {
    TextView thongTinTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_thong_tin);

        thongTinTxt = findViewById(R.id.textviewthongtin);

        String thongTin = "Ứng dụng được phát hành bởi: " +
                "\nĐàm Văn Đức " +
                "\nChảo Cáo Nhàn" +
                "\nTrần Tiến Dương" +
                "\nPhạm Trọng Tuấn Nghĩa";

        thongTinTxt.setText(thongTin);
        thongTinTxt.setTextColor(Color.parseColor("#FFFFFF"));

    }
}