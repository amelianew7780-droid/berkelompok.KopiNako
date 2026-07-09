package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    // ✅ Deklarasi CardView
    CardView cardSosisBakar, cardMatchaLatte;

    // ✅ Deklarasi Tombol Plus
    ImageView btnAddSosisBakar, btnAddMatchaLatte;

    // ✅ Deklarasi Bottom Navigation
    BottomNavigationView bottomNavigationView;

    // ✅ TAMBAHAN: Deklarasi Search Bar
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ✅ Inisialisasi CardView
        cardSosisBakar = findViewById(R.id.cardSosisBakar);
        cardMatchaLatte = findViewById(R.id.cardMatchaLatte);

        // ✅ Inisialisasi Tombol Plus
        btnAddSosisBakar = findViewById(R.id.btnAddSosisBakar);
        btnAddMatchaLatte = findViewById(R.id.btnAddMatchaLatte);

        // ✅ Inisialisasi Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // ✅ TAMBAHAN: Inisialisasi Search Bar
        etSearch = findViewById(R.id.etSearch);

        // ✅ TAMBAHAN: Saat search bar di-tap, langsung buka SearchActivity
        etSearch.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, SearchActivity.class));
        });

        // ✅ TAMBAHAN: Saat user mengetik lalu tekan "Search" di keyboard
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                String query = etSearch.getText().toString();
                Intent intent = new Intent(MenuActivity.this, SearchActivity.class);
                intent.putExtra("search_query", query);
                startActivity(intent);
                return true;
            }
            return false;
        });

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

        // ✅ Klik Tombol Plus Sosis Bakar (TIDAK buka CartActivity, cuma tambah ke cart)
        btnAddSosisBakar.setOnClickListener(v -> {
            Toast.makeText(this, "Sosis Bakar ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();

            // Tambahkan ke cart tanpa buka halaman baru
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putExtra("product_name", "Sosis Bakar");
            intent.putExtra("product_price", 15000);
            intent.putExtra("product_image", R.drawable.ic_sosisbakar);
            startActivity(intent);
        });

        // ✅ Klik Tombol Plus Matcha Latte (TIDAK buka CartActivity, cuma tambah ke cart)
        btnAddMatchaLatte.setOnClickListener(v -> {
            Toast.makeText(this, "Matcha Latte ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();

            // Tambahkan ke cart tanpa buka halaman baru
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putExtra("product_name", "Matcha Latte");
            intent.putExtra("product_price", 20000);
            intent.putExtra("product_image", R.drawable.ic_matchalatte);
            startActivity(intent);
        });
    }
}