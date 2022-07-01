package com.example.kassa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kassa.Adapter.GridAdapter;

import java.util.ArrayList;

public class LatteActivity extends AppCompatActivity {
    GridView gridview;
    Button btnOk, btnCancel;

    String[] itemName = {
            "Latte special",
            "Latte black",
            "Latte Ice",
            "Latte express",
            "Latte Star",
            "Latte double Ice"};

    int[] itemImage = {
            R.drawable.latte1,
            R.drawable.latte2,
            R.drawable.latte3,
            R.drawable.latte4,
            R.drawable.latte5,
            R.drawable.latte6,};

    int[] itemPrice = {5, 24, 10, 15, 19, 13};

    String[] itemDescription = {
            "Latte special",
            "Latte black",
            "Latte Ice",
            "Latte express",
            "Latte Star",
            "Latte double Ice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latte);
        getSupportActionBar().setTitle("Latte Menu");

        gridview = (GridView) findViewById(R.id.gridViewLatte);
        btnOk = findViewById(R.id.button_latte_ok);
        btnCancel = findViewById(R.id.button_latte_cancel);

        btnOk.setOnClickListener(view -> returnSelectedItems());
        btnCancel.setOnClickListener(view -> cancel());

        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), itemName, itemImage, itemPrice, itemDescription);

        gridview.setAdapter(gridAdapter);

    }

    public void cancel() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            setResult(RESULT_CANCELED, intent);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void returnSelectedItems() {

        ArrayList<String> itemNames = new ArrayList<>();
        ArrayList<Integer> itemPrices = new ArrayList<>();
        ArrayList<Integer> itemQuantities = new ArrayList<>();
        try {
            for (int i = 0; i < gridview.getChildCount(); i++) {
                View v = gridview.getChildAt(i);
                if (v instanceof LinearLayout) {
                    LinearLayout gridItem = (LinearLayout) v;
                    View v1 = gridItem.getChildAt(4);
                    EditText itemQuantity = (EditText) v1;

                    if (!TextUtils.isEmpty(itemQuantity.getText().toString())) {
                        itemNames.add(itemName[i]);
                        itemPrices.add(itemPrice[i]);
                        itemQuantities.add(Integer.parseInt(itemQuantity.getText().toString()));
                    }
                }
            }

            Intent intent = new Intent(this, MainActivity.class);
            intent.putStringArrayListExtra("latte_items", itemNames);
            intent.putIntegerArrayListExtra("latte_prices", itemPrices);
            intent.putIntegerArrayListExtra("latte_quantities", itemQuantities);
            setResult(RESULT_OK, intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
