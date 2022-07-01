package com.example.kassa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    public static final int AMERICANO_REQUEST_CODE = 1;
    public static final int LATTE_REQUEST_CODE = 2;
    public static final int MOCHA_REQUEST_CODE = 3;

    ArrayList<String> mocha_items = new ArrayList<>();
    ArrayList<String> latte_items = new ArrayList<>();
    ArrayList<String> americano_items = new ArrayList<>();
    ArrayList<Integer> mocha_prices = new ArrayList<>();
    ArrayList<Integer> latte_prices = new ArrayList<>();
    ArrayList<Integer> americano_prices = new ArrayList<>();
    ArrayList<Integer> mocha_quantities = new ArrayList<>();
    ArrayList<Integer> latte_quantities = new ArrayList<>();
    ArrayList<Integer> americano_quantities = new ArrayList<>();


    TextView textView;
    Button btnViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btnViewOrder = findViewById(R.id.btnViewOrder);

        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewOrder();
            }
        });
    }

    public void viewOrder() {
        Intent intent = new Intent(getApplicationContext(), OrderActivity.class);

        intent.putExtra("americano_items", americano_items);
        intent.putExtra("americano_prices", americano_prices);
        intent.putExtra("americano_quantities", americano_quantities);

        intent.putExtra("mocha_items", mocha_items);
        intent.putExtra("mocha_prices", mocha_prices);
        intent.putExtra("mocha_quantities", mocha_quantities);

        intent.putExtra("latte_items", latte_items);
        intent.putExtra("latte_prices", latte_prices);
        intent.putExtra("latte_quantities", latte_quantities);
        startActivity(intent);
    }

    public void showpopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MOCHA_REQUEST_CODE && resultCode == RESULT_OK) {
            for (int i = 0; i < data.getStringArrayListExtra("mocha_items").size(); i++) {
                String name = (data.getStringArrayListExtra("mocha_items").get(i));
                int price = (data.getIntegerArrayListExtra("mocha_prices").get(i));
                int quantity = (data.getIntegerArrayListExtra("mocha_quantities").get(i));

                mocha_items.add(name);
                mocha_prices.add(price);
                mocha_quantities.add(quantity);
            }
        } else if (requestCode == LATTE_REQUEST_CODE && resultCode == RESULT_OK) {
            for (int i = 0; i < data.getStringArrayListExtra("latte_items").size(); i++) {
                String name = (data.getStringArrayListExtra("latte_items").get(i));
                int price = (data.getIntegerArrayListExtra("latte_prices").get(i));
                int quantity = (data.getIntegerArrayListExtra("latte_quantities").get(i));

                latte_items.add(name);
                latte_prices.add(price);
                latte_quantities.add(quantity);
            }
        } else if (requestCode == AMERICANO_REQUEST_CODE && resultCode == RESULT_OK) {
            for (int i = 0; i < data.getStringArrayListExtra("americano_items").size(); i++) {
                String name = (data.getStringArrayListExtra("americano_items").get(i));
                int price = (data.getIntegerArrayListExtra("americano_prices").get(i));
                int quantity = (data.getIntegerArrayListExtra("americano_quantities").get(i));

                americano_items.add(name);
                americano_prices.add(price);
                americano_quantities.add(quantity);
            }
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, AmericanoActivity.class);
                startActivityForResult(intent, AMERICANO_REQUEST_CODE);

                return true;
            case R.id.item2:
                Intent intent2 = new Intent(this, MochaActivity.class);
                startActivityForResult(intent2, MOCHA_REQUEST_CODE);
                return true;
            case R.id.item3:
                Intent intent3 = new Intent(this, LatteActivity.class);
                startActivityForResult(intent3, LATTE_REQUEST_CODE);
                return true;
            default:
                return false;
        }
    }

}
