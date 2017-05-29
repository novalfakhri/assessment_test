package com.noval.android.myinventory;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";

    private ListView listView;
    InventoryHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Button button = (Button) findViewById(R.id.btn_tambah);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, InputActivity.class);
                    intent.putExtra(KEY_EXTRA_CONTACT_ID, 0);
                    startActivity(intent);
                }
            });

            dbHelper = new InventoryHelper(this);

            final Cursor cursor = dbHelper.getAllInventories();
            String[] columns = new String[]{
                    InventoryHelper.COLUMN_TANGGAL,
                    InventoryHelper.COLUMN_NAMA_BARANG,
                    InventoryHelper.COLUMN_HARGA,
                    InventoryHelper.COLUMN_STOK,
                    InventoryHelper.COLUMN_PEMASOK,
            };
            int[] widgets = new int[]{
                    R.id.display_tanggal,
                    R.id.display_nama,
                    R.id.display_harga,
                    R.id.display_stok
            };

            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_stok,
                    cursor, columns, widgets, 0);
            listView = (ListView) findViewById(R.id.list_inventory);
            listView.setAdapter(cursorAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                    Cursor itemCursor = (Cursor) MainActivity.this.listView.getItemAtPosition(position);
                    int inventoryID = itemCursor.getInt(itemCursor.getColumnIndex(InventoryHelper._ID));
                    Intent intent = new Intent(getApplicationContext(), InputActivity.class);
                    intent.putExtra(KEY_EXTRA_CONTACT_ID, inventoryID);
                    startActivity(intent);
                }
            });

        }

    }

