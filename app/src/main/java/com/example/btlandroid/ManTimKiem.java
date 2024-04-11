package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.btlandroid.adapter.AdapterTruyen;
import com.example.btlandroid.database.databaseDocTruyen;
import com.example.btlandroid.model.Truyen;

import java.util.ArrayList;

public class ManTimKiem extends AppCompatActivity {
    EditText timKiemEdt;
    ListView listView;
    ArrayList<Truyen> arrayList;
    ArrayList<Truyen> truyenArrayList;
    AdapterTruyen adapterTruyen;
    databaseDocTruyen databaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        timKiemEdt = findViewById(R.id.timkiem);
        listView = findViewById(R.id.listviewtimkiem);

        initList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManTimKiem.this, ManNoiDung.class);
                String tenTruyen = arrayList.get(position).getTenTruyen();
                String noiDung = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen", tenTruyen);
                intent.putExtra("noidung", noiDung);
                startActivity(intent);
            }
        });

        timKiemEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void initList() {
        arrayList = new ArrayList<>();
        truyenArrayList = new ArrayList<>();
        databaseDocTruyen = new databaseDocTruyen(this);
        Cursor cursor = databaseDocTruyen.getData2();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenTruyen = cursor.getString(1);
            String noiDung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            arrayList.add(new Truyen(tenTruyen, noiDung, anh, id_tk));
            truyenArrayList.add(new Truyen(tenTruyen, noiDung, anh, id_tk));
        }

        adapterTruyen = new AdapterTruyen(getApplicationContext(), truyenArrayList);
        listView.setAdapter(adapterTruyen);

        cursor.moveToFirst();
        cursor.close();
    }

    private void filter(String text) {
        arrayList.clear();
        ArrayList<Truyen> filteredList = new ArrayList<>();
        for(Truyen item : truyenArrayList)
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                arrayList.add(item);
            }
        adapterTruyen.filterList(filteredList);
    }
}