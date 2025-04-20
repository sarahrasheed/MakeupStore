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
            products.add(new Product(1, "Red Lipstick", 35.00, "lipstick_red", 10));
            products.add(new Product(2, "Foundation Cream", 50.00, "foundation_nude", 5));
            products.add(new Product(3, "Mascara Black", 25.00, "mascara_black", 8));

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("products", gson.toJson(products));
            editor.apply();

            return products;
        }
    }
}
