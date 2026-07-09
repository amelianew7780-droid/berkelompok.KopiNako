package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    // ✅ DEKLARASI VARIABEL (ini yang hilang!)
    private LinearLayout cartContainer;
    private TextView tvTotalPrice;
    private BottomNavigationView bottomNavigationView;

    // Static list untuk cart
    public static List<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // ✅ Inisialisasi views
        cartContainer = findViewById(R.id.cartContainer);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Cek data dari Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product_name")) {
            String name = intent.getStringExtra("product_name");
            int price = intent.getIntExtra("product_price", 0);
            int image = intent.getIntExtra("product_image", 0);

            boolean found = false;
            for (CartItem item : cartItems) {
                if (item.getName().equals(name)) {
                    item.increaseQuantity();
                    found = true;
                    break;
                }
            }

            if (!found) {
                cartItems.add(new CartItem(name, price, image));
            }
        }

        displayCartItems();

        // Bottom Navigation
        bottomNavigationView.setSelectedItemId(R.id.navigation_cart);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, MenuActivity.class));
                return true;
            } else if (itemId == R.id.navigation_search) {
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            } else if (itemId == R.id.navigation_cart) {
                return true;
            } else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
    }

    private void displayCartItems() {
        cartContainer.removeAllViews();
        int totalPrice = 0;

        for (CartItem item : cartItems) {
            android.view.View itemView = getLayoutInflater().inflate(R.layout.item_cart, cartContainer, false);

            ImageView ivImage = itemView.findViewById(R.id.ivProductImage);
            TextView tvName = itemView.findViewById(R.id.tvProductName);
            TextView tvPrice = itemView.findViewById(R.id.tvProductPrice);
            TextView tvQuantity = itemView.findViewById(R.id.tvQuantity);

            ivImage.setImageResource(item.getImageResId());
            tvName.setText(item.getName());
            tvPrice.setText("Rp " + item.getTotalPrice());
            tvQuantity.setText("x" + item.getQuantity());

            totalPrice += item.getTotalPrice();
            cartContainer.addView(itemView);
        }

        tvTotalPrice.setText("Total: Rp " + totalPrice);
    }
}