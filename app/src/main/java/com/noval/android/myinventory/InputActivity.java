package com.noval.android.myinventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Toshiba on 5/16/2017.
 */

public class InputActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Button input = (Button) findViewById(R.id.button_input);
        final EditText tanggal = (EditText) findViewById(R.id.edit_tanggal);
        final EditText namaBarang = (EditText) findViewById(R.id.edit_nama);
        final EditText jumlahBarang = (EditText) findViewById(R.id.edit_jumlah);
        final EditText hargaBarang = (EditText) findViewById(R.id.edit_harga);
        final EditText pemasok = (EditText) findViewById(R.id.edit_pemasok);
        final EditText keterangan = (EditText) findViewById(R.id.edit_keterangan);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean tanggalKosong = tanggal.length() == 0;
                boolean namaKosong = namaBarang.length() == 0;
                boolean jumlahKosong = jumlahBarang.length() == 0;
                boolean hargaKosong = hargaBarang.length() == 0;

                if (tanggalKosong || namaKosong || jumlahKosong || hargaKosong) {
                    Toast.makeText(InputActivity.this, "Form tidak boleh kosong", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(InputActivity.this, StokActivity.class);
                    StokBarang stok = new StokBarang(tanggal.getText().toString(), namaBarang.getText().toString(), jumlahBarang.getText().toString(), hargaBarang.getText().toString(), pemasok.getText().toString(), keterangan.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", stok);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }


}
