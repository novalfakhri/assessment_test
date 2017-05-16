package com.noval.android.myinventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Toshiba on 5/16/2017.
 */

public class StokActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok);
        TextView nama = (TextView) findViewById(R.id.display_nama);
        TextView stok = (TextView) findViewById(R.id.display_stok);
        TextView harga = (TextView) findViewById(R.id.display_harga);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        StokBarang data = (StokBarang) bundle.getSerializable("data");
        nama.setText(data.getNama());
        stok.setText(data.getJumlah());
        harga.setText(data.getHarga());
    }
}
