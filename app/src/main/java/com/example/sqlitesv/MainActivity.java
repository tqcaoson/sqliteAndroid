package com.example.sqlitesv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button nhapLieu, nhapLai, dssv;
    EditText ten, truong, date;
    RadioButton rdNam, rdNu;
    CheckBox theThao, duLich, docSach;
    SinhVienDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        nhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten.setText("");
                truong.setText("");
                date.setText("");
                theThao.setChecked(false);
                duLich.setChecked(false);
                docSach.setChecked(false);
                rdNam.setChecked(false);
                rdNu.setChecked(false);
            }
        });
        nhapLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ht = ten.getText().toString();
                String ns = date.getText().toString();
                String trg = truong.getText().toString();
                String st = "";
                if(theThao.isChecked()) st += ",The Thao";
                if(duLich.isChecked()) st += ",Du Lich";
                if(docSach.isChecked()) st += ",Doc Sach";
                int gt = 0;
                if(rdNu.isChecked()) gt = 1;
                SinhVienModel svThem = new SinhVienModel(ht, ns, trg, st, gt);
                if(svThem != null) {
                    db.themSV(svThem);
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dssv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListSVActivity.class);
                startActivity(intent);
            }
        });
    }
    void anhXa(){
        nhapLai = findViewById(R.id.nhapLai);
        nhapLieu = findViewById(R.id.nhapLieu);
        ten = findViewById(R.id.ten);
        truong = findViewById(R.id.truong);
        date = findViewById(R.id.date);
        theThao = findViewById(R.id.theThao);
        duLich = findViewById(R.id.duLich);
        docSach = findViewById(R.id.docSach);
        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
        db = new SinhVienDatabase(this);
        dssv = findViewById(R.id.xemds);
    }
}
