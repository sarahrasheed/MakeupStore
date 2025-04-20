package com.example.makeupstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    EditText nameEditText, addressEditText, cardEditText;
    Button confirmOrderButton;
    CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        cardEditText = findViewById(R.id.cardEditText);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);
        cartManager = new CartManager(this);

        confirmOrderButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String card = cardEditText.getText().toString().trim();

            if (name.isEmpty() || address.isEmpty() || card.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // clear cart, show message
                cartManager.clearCart();
                Toast.makeText(this, "Thank you, " + name + "! Your order is placed.", Toast.LENGTH_LONG).show();
                finish(); // Close the checkout screen
            }
        });
    }
}

