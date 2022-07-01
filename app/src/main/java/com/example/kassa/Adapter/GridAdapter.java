package com.example.kassa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kassa.R;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] name;
    int[] image;
    LayoutInflater inflater;
    int[] price;
    String[] dis;

    public GridAdapter(Context context, String[] foodname, int[] foodimg, int[] price, String[] descip) {
        ;
        this.context = context;
        this.name = foodname;
        this.image = foodimg;
        this.price = price;
        this.dis = descip;
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);
        TextView textView1 = convertView.findViewById(R.id.price);
        TextView textView2 = convertView.findViewById(R.id.dis);
        imageView.setImageResource(image[position]);
        textView.setText(name[position]);
        textView1.setText("Price: $" + price[position]);
        textView2.setText(dis[position]);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(context);
        button.setLayoutParams(params);
        button.setText("Testing");

        return convertView;
    }
}

