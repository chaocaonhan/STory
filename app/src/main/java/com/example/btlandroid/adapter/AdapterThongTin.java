package com.example.btlandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btlandroid.R;
import com.example.btlandroid.model.TaiKhoan;

import java.util.List;

public class AdapterThongTin extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TaiKhoan> taiKhoanList;

    public AdapterThongTin(Context context, int layout, List<TaiKhoan> taiKhoanList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public int getCount() {
        return taiKhoanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        TextView txtTenTaiKhoan = (TextView) convertView.findViewById(R.id.Text_name);
        TextView txtGmail = (TextView) convertView.findViewById(R.id.text_gmail);
        TaiKhoan taiKhoan = taiKhoanList.get(position);
        txtTenTaiKhoan.setText(taiKhoan.getmTenTaiKhoan());
        txtGmail.setText(taiKhoan.getmEmail());
        return convertView;
    }
}
