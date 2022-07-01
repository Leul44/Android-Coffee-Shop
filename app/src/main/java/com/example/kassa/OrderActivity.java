
package com.example.kassa;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ListView listViewMocha, listViewLatte, listViewAmericano;
    TextView txtMochaSubTotal, txtLatteSubTotal, txtAmericanoSubTotal, txtTotal;

    ArrayList<String> mocha_items = new ArrayList<>();
    ArrayList<String> latte_items = new ArrayList<>();
    ArrayList<String> americano_items = new ArrayList<>();
    ArrayList<Integer> mocha_prices = new ArrayList<>();
    ArrayList<Integer> latte_prices = new ArrayList<>();
    ArrayList<Integer> americano_prices = new ArrayList<>();
    ArrayList<Integer> mocha_quantities = new ArrayList<>();
    ArrayList<Integer> latte_quantities = new ArrayList<>();
    ArrayList<Integer> americano_quantities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        listViewMocha = findViewById(R.id.listViewMocha);
        listViewLatte = findViewById(R.id.listViewLatte);
        listViewAmericano = findViewById(R.id.listViewAmericano);
        txtMochaSubTotal = findViewById(R.id.txtMochaSubTotal);
        txtLatteSubTotal = findViewById(R.id.txtLatteSubTotal);
        txtAmericanoSubTotal = findViewById(R.id.txtAmericanoSubTotal);
        txtTotal = findViewById(R.id.txtTotal);

        Bundle intent = getIntent().getExtras();
        mocha_items.addAll(intent.getStringArrayList("mocha_items"));
        mocha_prices.addAll(intent.getIntegerArrayList("mocha_prices"));
        mocha_quantities.addAll(intent.getIntegerArrayList("mocha_quantities"));

        americano_items.addAll(intent.getStringArrayList("americano_items"));
        americano_prices.addAll(intent.getIntegerArrayList("americano_prices"));
        americano_quantities.addAll(intent.getIntegerArrayList("americano_quantities"));

        latte_items.addAll(intent.getStringArrayList("latte_items"));
        latte_prices.addAll(intent.getIntegerArrayList("latte_prices"));
        latte_quantities.addAll(intent.getIntegerArrayList("latte_quantities"));

        int total = 0;

        int subtotal = 0;
        String[] items_mocha = new String[mocha_items.size()];
        for (int i = 0; i < mocha_items.size(); i++) {
            int qty = mocha_quantities.get(i);
            int price = mocha_prices.get(i);
            int cost = qty * price;
            items_mocha[i] = (mocha_items.get(i) + "     (" + qty + " items x $ " + price + ") = $" + cost);
            subtotal += cost;
        }
        listViewMocha.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, items_mocha));
        txtMochaSubTotal.setText("Sub total: $ " + subtotal);
        total += subtotal;


        subtotal = 0;
        int count = americano_items.size();
        String[] items_americano = new String[count];
        for (int i = 0; i < count; i++) {
            int qty = americano_quantities.get(i);
            int price = americano_prices.get(i);
            int cost = qty * price;
            items_americano[i] = (americano_items.get(i) + "     (" + qty + " items x $ " + price + ") = $" + cost);
            subtotal += cost;
        }
        listViewAmericano.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, items_americano));
        txtAmericanoSubTotal.setText("Sub total: $ " + subtotal);
        total += subtotal;

        subtotal = 0;
        String[] items_latte = new String[latte_items.size()];
        for (int i = 0; i < latte_items.size(); i++) {
            int qty = latte_quantities.get(i);
            int price = latte_prices.get(i);
            int cost = qty * price;
            items_latte[i] = (latte_items.get(i) + "     (" + qty + " items x $ " + price + ") = $" + cost);
            subtotal += cost;
        }
        listViewLatte.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, items_latte));
        txtLatteSubTotal.setText("Sub total: $ " + subtotal);
        total += subtotal;

        txtTotal.setText("Total: $ " + total);
    }
}