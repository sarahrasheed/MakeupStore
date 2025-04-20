package com.example.makeupstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView totalPriceText;
    private Button checkoutButton;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        totalPriceText = findViewById(R.id.totalPriceText);
        checkoutButton = findViewById(R.id.checkoutButton);
        cartManager = new CartManager(this);

        List<Product> cart = cartManager.getCart();
        CartAdapter adapter = new CartAdapter(this, cart);
        cartListView.setAdapter(adapter);

        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }

        totalPriceText.setText("Total: $" + String.format("%.2f", total));

        //
        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }
}

