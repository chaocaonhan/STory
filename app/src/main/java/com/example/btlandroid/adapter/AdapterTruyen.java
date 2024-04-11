package com.example.btlandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Gravity;

import com.example.btlandroid.R;
import com.example.btlandroid.model.Truyen;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public AdapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView txtTenTruyen;
        ImageView imgTruyen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.itemtruyen, null);
        viewHolder.txtTenTruyen = convertView.findViewById(R.id.textviewtentruyennew);
        viewHolder.imgTruyen = convertView.findViewById(R.id.imgnewtruyen);

        //căn giữa tên truyện theo chiều dọc
        viewHolder.txtTenTruyen.setGravity(Gravity.CENTER_VERTICAL);
        convertView.setTag(viewHolder);
        Truyen truyen = (Truyen) getItem(position);
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());
        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHolder.imgTruyen);
        return convertView;
    }
}
