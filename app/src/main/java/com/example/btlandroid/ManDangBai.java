package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btlandroid.database.databaseDocTruyen;
import com.example.btlandroid.model.Truyen;

public class ManDangBai extends AppCompatActivity {
    EditText tenTruyenEdt, noiDungEdt, anhEdt;
    Button dangBaiBtn;
    databaseDocTruyen databaseDocTruyen;

    boolean isNew = true;
    int truyenID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_bai);

        tenTruyenEdt = findViewById(R.id.dbtentruyen);
        noiDungEdt = findViewById(R.id.dbnoidung);
        anhEdt = findViewById(R.id.dbimg);
        dangBaiBtn = findViewById(R.id.dbdangbai);

        databaseDocTruyen = new databaseDocTruyen(this);

        dangBaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTruyen = tenTruyenEdt.getText().toString();
                String noiDung = noiDungEdt.getText().toString();
                String img = anhEdt.getText().toString();

                // Check if any of the fields is empty
                if (tenTruyen.isEmpty() || noiDung.isEmpty() || img.isEmpty()) {
                    Toast.makeText(ManDangBai.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR: ", "Nhập đầy đủ thông tin");
                    return; // Return without further execution if any field is empty
                } else {
                    Truyen truyen = createTruyen();
                    databaseDocTruyen.AddTruyen(truyen);
                    Toast.makeText(ManDangBai.this, "Đã thêm truyện", Toast.LENGTH_SHORT).show();
                }
                // After adding or updating Truyen, return to the previous activity
                Intent intent = new Intent(ManDangBai.this, MainActivity.class);

                finish();
                SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", email);
                editor.putString("tentk", tenTaiKhoan);
                editor.apply();

                startActivity(intent);
            }
        });
    }

    private Truyen createTruyen() {
        String tenTruyen = tenTruyenEdt.getText().toString();
        String noiDung = noiDungEdt.getText().toString();
        String img = anhEdt.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);
        Truyen truyen = new Truyen(tenTruyen, noiDung, img, id);
        return truyen;
    }
}