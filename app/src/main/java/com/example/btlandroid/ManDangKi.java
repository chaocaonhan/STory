package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btlandroid.database.databaseDocTruyen;
import com.example.btlandroid.model.TaiKhoan;

public class ManDangKi extends AppCompatActivity {
    EditText dkTaiKhoanEdt, dkMatKhauEdt, dkEmailEdt;
    Button dkDangKiBtn, dkDangNhapBtn;
    databaseDocTruyen databaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ki);

        databaseDocTruyen = new databaseDocTruyen(this);

        dkTaiKhoanEdt = findViewById(R.id.dktaikhoan);
        dkMatKhauEdt = findViewById(R.id.dkmatkhau);
        dkEmailEdt = findViewById(R.id.dkemail);
        dkDangKiBtn = findViewById(R.id.dkdangki);
        dkDangNhapBtn = findViewById(R.id.dkdangnhap);

        dkDangKiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = dkTaiKhoanEdt.getText().toString();
                String matKhau = dkMatKhauEdt.getText().toString();
                String email = dkEmailEdt.getText().toString();

                TaiKhoan tk = createTaiKhoan();
                if(taiKhoan.equals("") || matKhau.equals("") || email.equals("")) {
                    Toast.makeText(ManDangKi.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR: ", "Nhập đầy đủ thông tin");
                } else {
                    databaseDocTruyen.AddTaiKhoan(tk);
                    Toast.makeText(ManDangKi.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dkDangNhapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private TaiKhoan createTaiKhoan() {
        String taiKhoan = dkTaiKhoanEdt.getText().toString();
        String matKhau = dkMatKhauEdt.getText().toString();
        String email = dkEmailEdt.getText().toString();
        int phanQuyen = 1;

        TaiKhoan tk = new TaiKhoan(taiKhoan, matKhau, email, phanQuyen);
        return tk;
    }
}