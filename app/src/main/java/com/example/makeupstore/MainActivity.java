package com.example.makeupstore;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productListView = findViewById(R.id.productListView);
        List<Product> products = loadProducts();

        ProductAdapter adapter = new ProductAdapter(this, products);
        productListView.setAdapter(adapter);
    }

    private List<Product> loadProducts() {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("products", null);

        if (json != null) {
            Type type = new TypeToken<List<Product>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            List<Product> products = new ArrayList<>();
            products.add(new Product(1, "Red Lipstick", 29.99, "redlipstick", 10));
            products.add(new Product(2, "Liquid Foundation", 42.50, "foundation", 8));
            products.add(new Product(3, "Mascara", 24.00, "mascara", 12));
            products.add(new Product(4, "Blush Palette", 35.75, "blush", 6));
            products.add(new Product(5, "Eyebrow Pencil", 14.90, "eyebrow", 15));
            products.add(new Product(6, "Eyeshadow Pallete", 39.99, "eyeshadow", 7));
            products.add(new Product(7, "Setting Spray", 21.00, "settingspray", 9));
            products.add(new Product(8, "Beauty Blender", 10.00, "beautyblender", 20));
            products.add(new Product(9, "Highlighter", 19.95, "highlighter", 10));
            products.add(new Product(10, "Lip Gloss", 17.99, "lipgloss", 13));


            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("products", gson.toJson(products));
            editor.apply();

            return products;
        }
    }
}
