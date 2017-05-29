package com.noval.android.myinventory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    private InventoryHelper dbHelper;
    EditText editTextTangal, editTextNama, editTextHarga, editTextStok, editTextPemasok, editTextKeterangan;

    Button simpan, ubah, hapus;
    LinearLayout buttonLayout;

    int inventoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inventoryID = getIntent().getIntExtra(MainActivity.KEY_EXTRA_CONTACT_ID, 0);

        setContentView(R.layout.activity_input);
        editTextTangal = (EditText) findViewById(R.id.edit_tanggal);
        editTextNama = (EditText) findViewById(R.id.edit_nama);
        editTextHarga = (EditText) findViewById(R.id.edit_harga);
        editTextStok = (EditText) findViewById(R.id.edit_jumlah);
        editTextPemasok = (EditText) findViewById(R.id.edit_pemasok);
//        editTextKeterangan = (EditText) findViewById(R.id.edit_keterangan);

        simpan = (Button) findViewById(R.id.button_simpan);
        simpan.setOnClickListener(this);
        buttonLayout = (LinearLayout) findViewById(R.id.button_layout);
        ubah = (Button) findViewById(R.id.button_ubah);
        ubah.setOnClickListener(this);
        hapus = (Button) findViewById(R.id.button_hapus);
        hapus.setOnClickListener(this);

        dbHelper = new InventoryHelper(this);

        if(inventoryID > 0) {
            simpan.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);

            Cursor rs = dbHelper.getInventory(inventoryID);
            rs.moveToFirst();
            String tanggal = rs.getString(rs.getColumnIndex(InventoryHelper.COLUMN_TANGGAL));
            String namaBarang = rs.getString(rs.getColumnIndex(InventoryHelper.COLUMN_NAMA_BARANG));
            int harga = rs.getInt(rs.getColumnIndex(InventoryHelper.COLUMN_HARGA));
            int stok = rs.getInt(rs.getColumnIndex(InventoryHelper.COLUMN_STOK));
            String pemasok = rs.getString(rs.getColumnIndex(InventoryHelper.COLUMN_PEMASOK));
            if (!rs.isClosed()) {
                rs.close();
            }

            editTextTangal.setText(tanggal);
            editTextTangal.setFocusable(false);
            editTextTangal.setClickable(false);

            editTextNama.setText(namaBarang);
            editTextNama.setFocusable(false);
            editTextNama.setClickable(false);

            editTextHarga.setText( ("Rp "+ harga));
            editTextHarga.setFocusable(false);
            editTextHarga.setClickable(false);

            editTextStok.setText((stok + ""));
            editTextStok.setFocusable(false);
            editTextStok.setClickable(false);

            editTextPemasok.setText(pemasok);
            editTextPemasok.setFocusable(false);
            editTextPemasok.setClickable(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_simpan:
                persistInventory();
                return;
            case R.id.button_ubah:
                simpan.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.GONE);

                editTextTangal.setEnabled(true);
                editTextTangal.setFocusableInTouchMode(true);
                editTextTangal.setClickable(true);

                editTextNama.setEnabled(true);
                editTextNama.setFocusableInTouchMode(true);
                editTextNama.setClickable(true);

                editTextHarga.setEnabled(true);
                editTextHarga.setFocusableInTouchMode(true);
                editTextHarga.setClickable(true);

                editTextStok.setEnabled(true);
                editTextStok.setFocusableInTouchMode(true);
                editTextStok.setClickable(true);

                editTextPemasok.setEnabled(true);
                editTextPemasok.setFocusableInTouchMode(true);
                editTextPemasok.setClickable(true);
                return;

            case R.id.button_hapus:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(null)
                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHelper.deleteInventory(inventoryID);
                                Toast.makeText(getApplicationContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Hapus Inventory?");
                d.show();
                return;
        }
    }

    public void persistInventory() {
        if(inventoryID > 0) {
            if(dbHelper.updateInventory(inventoryID,
                    editTextTangal.getText().toString(),
                    editTextNama.getText().toString(),
                    Integer.parseInt(editTextHarga.getText().toString()),
                    Integer.parseInt(editTextStok.getText().toString()),
                    editTextPemasok.getText().toString())) {

                Toast.makeText(getApplicationContext(), "Inventory Berhasil Diperbarui", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Perintah Gagal", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if(dbHelper.insertInventory(
                    editTextTangal.getText().toString(),
                    editTextNama.getText().toString(),
                    Integer.parseInt(editTextHarga.getText().toString()),
                    Integer.parseInt(editTextStok.getText().toString()),
                    editTextPemasok.getText().toString())) {

                Toast.makeText(getApplicationContext(), "Inventory Ditambahkan", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Tidak dapat menambah Inventory", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
