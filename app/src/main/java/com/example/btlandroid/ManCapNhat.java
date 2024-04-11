package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btlandroid.database.databaseDocTruyen;
import com.example.btlandroid.model.Truyen;

public class ManCapNhat extends AppCompatActivity {
    EditText tenTruyenEdt, noiDungEdt, anhEdt;
    Button suaBaiBtn;
    databaseDocTruyen databaseDocTruyen;

    boolean isNew = true;
    int truyenID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_sua_bai);

        Log.d("ManCapNhat", "onCreate: Started");

        tenTruyenEdt = findViewById(R.id.dbtentruyen);
        noiDungEdt = findViewById(R.id.dbnoidung);
        anhEdt = findViewById(R.id.dbimg);
        suaBaiBtn = findViewById(R.id.dbsuabai);

        databaseDocTruyen = new databaseDocTruyen(this);

        Intent intent = getIntent();
        truyenID = intent.getIntExtra("truyenID", -1);
        String tenTruyen = intent.getStringExtra("tenTruyen");
        String noiDung = intent.getStringExtra("noiDung");
        String Anh = intent.getStringExtra("anh");
        int id = intent.getIntExtra("id", -1);

        tenTruyenEdt.setText(tenTruyen);
        noiDungEdt.setText(noiDung);
        anhEdt.setText(Anh);
        suaBaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenTruyenEdt.getText().toString();
                String nd = noiDungEdt.getText().toString();
                String anh = anhEdt.getText().toString();

                // Check if any of the fields is empty
                if (ten.isEmpty() || nd.isEmpty() || anh.isEmpty()) {
                    Toast.makeText(ManCapNhat.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR: ", "Nhập đầy đủ thông tin");
                    return; // Return without further execution if any field is empty
                } else {
                    // Create a Truyen object with updated values
                    Truyen updatedTruyen = new Truyen(ten, nd, anh);
                    // Update the Truyen in the database
                    databaseDocTruyen.UpdateTruyen(updatedTruyen);
                    Toast.makeText(ManCapNhat.this, "Đã cập nhật truyện", Toast.LENGTH_SHORT).show();

                }
                // After adding or updating Truyen, return to the previous activity
                Intent intent = new Intent(ManCapNhat.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}