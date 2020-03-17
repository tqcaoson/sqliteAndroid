package com.example.sqlitesv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterSV extends BaseAdapter {
    private ListSVActivity context;
    private int layout;
    private List<SinhVienModel> CVList;

    public AdapterSV(ListSVActivity context, int layout, List<SinhVienModel> CVList) {
        this.context = context;
        this.layout = layout;
        this.CVList = CVList;
    }


    public int getCount() {
        return CVList.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView ten, ns, truong, soThich, gioiTinh;
        ImageView imgDelete, imgEdit;
    }
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.ten = (TextView) view.findViewById(R.id.ten);
            holder.ns = (TextView) view.findViewById(R.id.ns);
            holder.gioiTinh = (TextView) view.findViewById(R.id.gioiTinh);
            holder.truong = (TextView) view.findViewById(R.id.truong);
            holder.soThich = (TextView) view.findViewById(R.id.soThich);
            holder.imgDelete = (ImageView) view.findViewById(R.id.bua);
            holder.imgEdit = (ImageView) view.findViewById(R.id.trung);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        final SinhVienModel cv = CVList.get(i);
        holder.ten.setText(""+cv.getHoTen());
        holder.truong.setText(""+cv.getTruong());
        holder.gioiTinh.setText("Nam");
        if(cv.getGioiTinh() == 1) holder.gioiTinh.setText("Nu");
        holder.ns.setText(""+cv.getNs());
        holder.soThich.setText(""+cv.getSoThich());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaCV(cv.getId(), cv.getHoTen(), cv.getGioiTinh(), cv.getNs(), cv.getSoThich(), cv.getTruong());
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaCV(cv.getId());
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
