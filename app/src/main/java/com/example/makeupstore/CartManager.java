package com.example.makeupstore;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "MyPrefs";
    private static final String CART_KEY = "cart";
    private SharedPreferences prefs;
    private Gson gson;

    public CartManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addToCart(Product product) {
        List<Product> cart = getCart();
        cart.add(product);
        saveCart(cart);
    }

    public List<Product> getCart() {
        String json = prefs.getString(CART_KEY, null);
        if (json == null) return new ArrayList<>();

        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveCart(List<Product> cart) {
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(cart);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    public void clearCart() {
        prefs.edit().remove(CART_KEY).apply();
    }
}

