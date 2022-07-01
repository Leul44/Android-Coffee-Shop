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

public class AmericanoActivity extends AppCompatActivity {
    GridView gridview;
    Button btnOk, btnCancel;

    String[] itemName = {
            "Americano special",
            "Americano black",
            "Americano Ice",
            "Americano express",
            "Americano Star",
            "Americano double Ice"};

    int[] itemImage = {
            R.drawable.americano1,
            R.drawable.americano2,
            R.drawable.americano3,
            R.drawable.americano4,
            R.drawable.americano5,
            R.drawable.americano6,};

    int[] itemPrice = {10, 14, 11, 14, 9, 16};

    String[] itemDescription = {
            "Americano special",
            "Americano black",
            "Americano Ice",
            "Americano express",
            "Americano Star",
            "Americano double Ice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_americano);
        getSupportActionBar().setTitle("Americano Menu");

        gridview = (GridView) findViewById(R.id.gridViewAmericano);
        btnOk = findViewById(R.id.button_americano_ok);
        btnCancel = findViewById(R.id.button_americano_cancel);

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
            intent.putStringArrayListExtra("americano_items", itemNames);
            intent.putIntegerArrayListExtra("americano_prices", itemPrices);
            intent.putIntegerArrayListExtra("americano_quantities", itemQuantities);
            setResult(RESULT_OK, intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
