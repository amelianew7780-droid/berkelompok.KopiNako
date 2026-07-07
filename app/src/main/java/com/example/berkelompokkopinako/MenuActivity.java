package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    // ✅ Deklarasi CardView
    CardView cardSosisBakar, cardMatchaLatte;

    // ✅ TAMBAHAN: Deklarasi Tombol Plus
    ImageView btnAddSosisBakar, btnAddMatchaLatte;

    // ✅ Deklarasi Bottom Navigation
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ✅ Inisialisasi CardView
        cardSosisBakar = findViewById(R.id.cardSosisBakar);
        cardMatchaLatte = findViewById(R.id.cardMatchaLatte);

        // ✅ TAMBAHAN: Inisialisasi Tombol Plus
        btnAddSosisBakar = findViewById(R.id.btnAddSosisBakar);
        btnAddMatchaLatte = findViewById(R.id.btnAddMatchaLatte);

        // ✅ Inisialisasi Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // ✅ Bottom Navigation Listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_search) {
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            } else if (itemId == R.id.navigation_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }

            return false;
        });

        // ✅ Klik CardView Sosis Bakar
        cardSosisBakar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("nama_produk", "Sosis Bakar");
            intent.putExtra("harga", 15000);
            intent.putExtra("gambar", R.drawable.ic_sosisbakar);
            intent.putExtra("kategori", "makanan");
            startActivity(intent);
        });

        // ✅ Klik CardView Matcha Latte
        cardMatchaLatte.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("nama_produk", "Matcha Latte");
            intent.putExtra("harga", 20000);
            intent.putExtra("gambar", R.drawable.ic_matchalatte);
            intent.putExtra("kategori", "minuman");
            startActivity(intent);
        });

        // ✅ TAMBAHAN: Klik Tombol Plus Sosis Bakar
        btnAddSosisBakar.setOnClickListener(v -> {
            Toast.makeText(this, "Sosis Bakar ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();

            // Langsung ke CartActivity
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putExtra("nama_produk", "Sosis Bakar");
            intent.putExtra("harga", 15000);
            intent.putExtra("gambar", R.drawable.ic_sosisbakar);
            intent.putExtra("kategori", "makanan");
            startActivity(intent);
        });

        // ✅ TAMBAHAN: Klik Tombol Plus Matcha Latte
        btnAddMatchaLatte.setOnClickListener(v -> {
            Toast.makeText(this, "Matcha Latte ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();

            // Langsung ke CartActivity
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putExtra("nama_produk", "Matcha Latte");
            intent.putExtra("harga", 20000);
            intent.putExtra("gambar", R.drawable.ic_matchalatte);
            intent.putExtra("kategori", "minuman");
            startActivity(intent);
        });
    }
}