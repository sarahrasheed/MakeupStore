package com.example.makeupstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;
    private CartManager cartManager;


    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        this.cartManager = new CartManager(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.product_list, viewGroup, false);
        }

        Product product = products.get(i);
        TextView nameText = view.findViewById(R.id.productName);
        TextView priceText = view.findViewById(R.id.productPrice);
        ImageView imageView = view.findViewById(R.id.productImage);

        nameText.setText(product.getName());
        priceText.setText("$" + String.format("%.2f", product.getPrice()));

        int imageResId = context.getResources().getIdentifier(
                product.getImageName(), "drawable", context.getPackageName());
        imageView.setImageResource(imageResId);

        Button addToCartButton = view.findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(v -> {
            cartManager.addToCart(product);
            Toast.makeText(context, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}

