package com.example.makeupstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<Product> cart;

    public CartAdapter(Context context, List<Product> cart) {
        this.context = context;
        this.cart = cart;
    }

    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int i) {
        return cart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cart.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.product_list, viewGroup, false);
        }

        Product product = cart.get(i);
        TextView name = view.findViewById(R.id.productName);
        TextView price = view.findViewById(R.id.productPrice);
        ImageView image = view.findViewById(R.id.productImage);
        Button addButton = view.findViewById(R.id.addToCartButton);
        addButton.setVisibility(View.GONE); // hide "Add to Cart" button in cart screen

        name.setText(product.getName());
        price.setText("$" + String.format("%.2f", product.getPrice()));
        int resId = context.getResources().getIdentifier(product.getImageName(), "drawable", context.getPackageName());
        image.setImageResource(resId);

        return view;
    }
}
