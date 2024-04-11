package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.btlandroid.database.databaseDocTruyen;

public class ManDangNhap extends AppCompatActivity {
    EditText taiKhoanEdt, matKhauEdt;
    Button dangNhapBtn;
     TextView dangKiBtn;
    databaseDocTruyen databaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);

        databaseDocTruyen = new databaseDocTruyen(this);

        taiKhoanEdt = findViewById(R.id.taikhoan);
        matKhauEdt = findViewById(R.id.matkhau);
        dangNhapBtn = findViewById(R.id.dangnhap);
        dangKiBtn = findViewById(R.id.dangki);

        dangNhapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = taiKhoanEdt.getText().toString();
                String matKhau = matKhauEdt.getText().toString();

                Cursor cursor = databaseDocTruyen.getData();
                while (cursor.moveToNext()) {
                    String dataTaiKhoan = cursor.getString(1);
                    String dataMatKhau = cursor.getString(2);
                    if(dataTaiKhoan.equals(taiKhoan) && dataMatKhau.equals(matKhau)) {
                        int ADMIN = cursor.getInt(4);
                        int id = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Intent intent = new Intent(ManDangNhap.this, MainActivity.class);
                        intent.putExtra("ADMIN", ADMIN);
                        intent.putExtra("id", id);
                        intent.putExtra("email", email);
                        intent.putExtra("tentk", tentk);
                        startActivity(intent);
                    }
                }

                cursor.moveToFirst();
                cursor.close();
            }
        });

        dangKiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManDangNhap.this, ManDangKi.class);
                startActivity(intent);
            }
        });
    }
}