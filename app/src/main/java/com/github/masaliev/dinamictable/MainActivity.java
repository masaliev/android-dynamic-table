package com.github.masaliev.dinamictable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etSerialNumber;
    private EditText etItems;
    private EditText etBrand;
    private EditText etPrice;

    private ItemAdapter itemAdapter;
    private StoreItems currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSerialNumber = (EditText) findViewById(R.id.etSerialNumber);
        etItems = (EditText) findViewById(R.id.etItems);
        etBrand = (EditText) findViewById(R.id.etBrand);
        etPrice = (EditText) findViewById(R.id.etPrice);

        RecyclerView rvTable = (RecyclerView) findViewById(R.id.rvTable);
        itemAdapter = new ItemAdapter(new ArrayList<StoreItems>(), R.layout.item_layout, this);

        rvTable.setAdapter(itemAdapter);
        rvTable.setHasFixedSize(false);
        rvTable.setItemAnimator(new DefaultItemAnimator());

        rvTable.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentItem == null) {
                    //@TODO check edit text values
                    StoreItems item = new StoreItems(etSerialNumber.getText().toString(),
                            etItems.getText().toString(),
                            etBrand.getText().toString(),
                            etPrice.getText().toString(),
                            "getID",        //If you need id you can do as another fields
                            "Checked");
                    itemAdapter.addItem(item);
                } else {
                    currentItem.setSerialNumber(etSerialNumber.getText().toString());
                    currentItem.setItems(etItems.getText().toString());
                    currentItem.setBrand(etBrand.getText().toString());
                    currentItem.setPrice(etPrice.getText().toString());
                    itemAdapter.updateItem(currentItem);
                    currentItem = null;
                }
                //reset form fields
                etSerialNumber.setText("");
                etItems.setText("");
                etBrand.setText("");
                etPrice.setText("");
            }
        });

        findViewById(R.id.btnUpload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StoreItems> items = itemAdapter.getItems();
                for(StoreItems item:items){
                    Log.i("Get items :::", String.valueOf(item.getStatus()));
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_edit) {
            etSerialNumber.setText(currentItem.getSerialNumber());
            etItems.setText(currentItem.getItems());
            etBrand.setText(currentItem.getBrand());
            etPrice.setText(currentItem.getPrice());
        }else if (item.getItemId() == R.id.option_delete) {
            itemAdapter.deleteItem(currentItem);
            currentItem = null;
        }
        return true;
    }

    public void openMenu(View view){
        currentItem = (StoreItems) view.getTag();
        openContextMenu(view);
    }
}
