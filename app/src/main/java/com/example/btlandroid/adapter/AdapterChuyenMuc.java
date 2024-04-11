package com.example.btlandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btlandroid.R;
import com.example.btlandroid.model.ChuyenMuc;

import java.util.List;

public class AdapterChuyenMuc extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ChuyenMuc> chuyenMucList;

    public AdapterChuyenMuc(Context context, int layout, List<ChuyenMuc> chuyenMucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenMucList = chuyenMucList;
    }

    @Override
    public int getCount() {
        return chuyenMucList.size();
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
        ImageView img = (ImageView) convertView.findViewById(R.id.imgchuyenmuc);
        TextView txt = (TextView) convertView.findViewById(R.id.textviewtentruyenmuc);
        ChuyenMuc chuyenMuc = chuyenMucList.get(position);
        img.setImageResource(chuyenMuc.getHinhAnhChuyenMuc());
        txt.setText(chuyenMuc.getTenChuyenMuc());
        return convertView;
    }
}
