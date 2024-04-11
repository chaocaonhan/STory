package com.example.btlandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.btlandroid.R;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.btlandroid.database.databaseDocTruyen;
import com.example.btlandroid.adapter.AdapterChuyenMuc;
import com.example.btlandroid.adapter.AdapterThongTin;
import com.example.btlandroid.adapter.AdapterTruyen;
import com.example.btlandroid.model.ChuyenMuc;
import com.example.btlandroid.model.TaiKhoan;
import com.example.btlandroid.model.Truyen;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listview, listviewnew, listviewthongtin;
    DrawerLayout drawerLayout;
    String email;
    String tenTaiKhoan;
    int i = 0;

    ArrayList<ChuyenMuc> chuyenMucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    ArrayList<Truyen> truyenArrayList;
    AdapterChuyenMuc adapterChuyenMuc;
    AdapterThongTin adapterThongTin;
    AdapterTruyen adapterTruyen;
    databaseDocTruyen databaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDocTruyen = new databaseDocTruyen(this);

        Intent intentpq = getIntent();
        i = intentpq.getIntExtra("ADMIN", 0);
        int id = intentpq.getIntExtra("id", 0);
        email = intentpq.getStringExtra("email");
        tenTaiKhoan = intentpq.getStringExtra("tentk");

        Mapping();
        ActionBar();

        listviewnew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ManNoiDung.class);

                String tenTruyen = truyenArrayList.get(position).getTenTruyen();
                String noiDung = truyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen", tenTruyen);
                intent.putExtra("noidung", noiDung);
                startActivity(intent);
            }
        });
        listviewnew.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle long click event
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                String tenTruyen = truyenArrayList.get(position).getTenTruyen();
                popupMenu.inflate(R.menu.context_menu); // Inflate the context menu resource file
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.edit && "admin".equals(tenTaiKhoan)){
                            Intent intentEdit = new Intent(MainActivity.this, ManCapNhat.class);
                            intentEdit.putExtra("truyenID", truyenArrayList.get(position).getID());
                            intentEdit.putExtra("tenTruyen", truyenArrayList.get(position).getTenTruyen());
                            intentEdit.putExtra("noiDung", truyenArrayList.get(position).getNoiDung());
                            intentEdit.putExtra("anh", truyenArrayList.get(position).getAnh());
                            intentEdit.putExtra("id", truyenArrayList.get(position).getID_TK());
                            Log.d("Test", "ID = " + truyenArrayList.get(position).getID());
                            startActivity(intentEdit);
                            return true;
                        } else if (itemId == R.id.delete && "admin".equals(tenTaiKhoan)){
                            databaseDocTruyen.Delete(tenTruyen);
                            truyenArrayList.remove(position);
                            adapterTruyen.notifyDataSetChanged();
                            return true;
                        } else {
                            Toast.makeText(MainActivity.this,"Bạn không có quyền !",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                });
                popupMenu.show();
                return true; // Return true to consume the long click event
            }
        });
    }
    private void Mapping() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        listview = findViewById(R.id.listviewanhmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        listviewnew = findViewById(R.id.listviewnew);
        drawerLayout = findViewById(R.id.drawerLayout);
        listviewthongtin = findViewById(R.id.listviewthongtin);

        truyenArrayList = new ArrayList<>();

        Cursor cursor = databaseDocTruyen.getData2();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenTruyen = cursor.getString(1);
            String noiDung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            truyenArrayList.add(new Truyen(tenTruyen, noiDung, anh, id_tk));
        }

        adapterTruyen = new AdapterTruyen(getApplicationContext(), truyenArrayList);
        listviewnew.setAdapter(adapterTruyen);

        cursor.moveToFirst();
        cursor.close();

        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tenTaiKhoan, email));

        adapterThongTin = new AdapterThongTin(this, R.layout.navigation_thongtin, taiKhoanArrayList);
        listviewthongtin.setAdapter(adapterThongTin);

        chuyenMucArrayList = new ArrayList<>();
        if("admin".equals(tenTaiKhoan)) {
            chuyenMucArrayList.add(new ChuyenMuc("Thông tin", R.drawable.ic_baseline_face_24));
            chuyenMucArrayList.add(new ChuyenMuc("Thêm truyện mới", R.drawable.addstoryicon));
            chuyenMucArrayList.add(new ChuyenMuc("Đăng xuất", R.drawable.ic_baseline_logout_24));
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     if (position == 0) {
                        // Start ManDangNhap activity
                        Intent intentDangNhap = new Intent(MainActivity.this, ManThongTin.class);
                        startActivity(intentDangNhap);
                    } else if (position == 1) {
                        Intent intentDangBai = new Intent(MainActivity.this, ManDangBai.class);
                        startActivity(intentDangBai);
                    } else if (position == 2) {
                        finish();
                    }
                }
            });
        } else {
            chuyenMucArrayList.add(new ChuyenMuc("Thông tin", R.drawable.ic_baseline_face_24));
            chuyenMucArrayList.add(new ChuyenMuc("Đăng xuất", R.drawable.ic_baseline_logout_24));

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0){
                        Intent intent = new Intent(MainActivity.this,ManThongTin.class);
                        startActivity(intent);
                    }
                    if (position == 1) {
                        Intent intent = new Intent(MainActivity.this,ManDangNhap.class);
                        startActivity(intent);
                    }
                }
            });
        }
        adapterChuyenMuc = new AdapterChuyenMuc(this, R.layout.chuyenmuc, chuyenMucArrayList);
        listview.setAdapter(adapterChuyenMuc);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu1) {
            Intent intent = new Intent(MainActivity.this, ManTimKiem.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}