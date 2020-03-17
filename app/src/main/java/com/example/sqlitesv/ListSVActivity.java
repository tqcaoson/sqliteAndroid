package com.example.sqlitesv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSVActivity extends AppCompatActivity {
    SinhVienDatabase database;
    ListView lvCV;
    ArrayList<SinhVienModel> arraySV;
    AdapterSV adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sv);
        database = new SinhVienDatabase(this);
        lvCV = findViewById(R.id.listviewSV);
        arraySV = new ArrayList<>();
        adapter = new AdapterSV(this, R.layout.custom_listview, arraySV);
        lvCV.setAdapter(adapter);
        GetDataCV();
        adapter.notifyDataSetChanged();
    }
    void DialogXoaCV(final int id){
        AlertDialog.Builder diablogXoa = new AlertDialog.Builder(this);
        diablogXoa.setMessage("Delete?");
        diablogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                database.QueryData("DELETE FROM SV WHERE Id = '"+ id +"'");
                Toast.makeText(ListSVActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                GetDataCV();
                adapter.notifyDataSetChanged();
            }
        });
        diablogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        diablogXoa.show();
    }
    public void DialogSuaCV(final int id, String ten, int gioiTinh, String ns, String soThich, String truong){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_activity);
        final EditText tens = (EditText) dialog.findViewById(R.id.ten);
        final EditText nss = (EditText) dialog.findViewById(R.id.date);
        final EditText truongs = (EditText) dialog.findViewById(R.id.truong);
        final RadioButton Nams =  dialog.findViewById(R.id.rdNam);
        final RadioButton Nus = dialog.findViewById(R.id.rdNu);
        final CheckBox dls =  dialog.findViewById(R.id.duLich);
        final CheckBox dss = dialog.findViewById(R.id.docSach);
        final CheckBox tts = dialog.findViewById(R.id.theThao);
        Button btnhuysua = (Button) dialog.findViewById(R.id.nhapLai);
        Button btnsua = (Button) dialog.findViewById(R.id.Sua);
        tens.setText(ten);
        nss.setText(ns);
        truongs.setText(truong);
        if(gioiTinh == 0) Nams.setChecked(true);
        else Nus.setChecked(true);
        if(soThich.contains("Du Lich")) dls.setChecked(true);
        if(soThich.contains("Doc Sach")) dss.setChecked(true);
        if(soThich.contains("The Thao")) tts.setChecked(true);
        btnhuysua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = tens.getText().toString();
                String truongmoi = truongs.getText().toString();
                int gtmoi = 0;
                if(Nus.isChecked()) gtmoi = 1;
                String nsmoi = nss.getText().toString();
                String soThichmoi = "";
                if(tts.isChecked()) soThichmoi += ",The Thao";
                if(dls.isChecked()) soThichmoi += ",Du Lich";
                if(dss.isChecked()) soThichmoi += ",Doc Sach";
                database.QueryData("UPDATE SV SET ten = '"+ tenmoi +"', truong = '"+ truongmoi +"', gioiTinh = '"+ gtmoi +"', ns = '"+ nsmoi +"', soThich = '"+ soThichmoi +"' WHERE Id = '"+ id +"'");
                Toast.makeText(ListSVActivity.this, "da sua", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCV();
                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }
    void GetDataCV(){
        Cursor dataCV = database.GetData("SELECT * FROM SV");
        arraySV.clear();
        while(dataCV.moveToNext()){
            int id = dataCV.getInt(0);
            String ht = dataCV.getString(1);
            String ns = dataCV.getString(2);
            String trg = dataCV.getString(3);
            String st = dataCV.getString(5);
            int gt = dataCV.getInt(4);
            arraySV.add(new SinhVienModel(id, ht, ns, trg, st, gt));
            //  Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
        }
    }
}
