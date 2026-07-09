package com.example.berkelompokkopinako;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private TextView textViewNoResults;
    private List<Product> productList;
    private List<Product> filteredList;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Inisialisasi views
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        textViewNoResults = findViewById(R.id.textViewNoResults);

        // Inisialisasi list
        productList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Load data produk (contoh)
        loadSampleData();

        // Setup RecyclerView
        setupRecyclerView();

        // Setup SearchView
        setupSearchView();

        // ✅ TAMBAHAN: Ambil query dari MenuActivity (jika ada), lalu auto-search
        String initialQuery = getIntent().getStringExtra("search_query");
        if (initialQuery != null && !initialQuery.isEmpty()) {
            searchView.setQuery(initialQuery, true);
        }
    }

    private void loadSampleData() {
        // Data Minuman (Kopi)
        productList.add(new Product("Kopi Latte", "Kopi susu dengan rasa creamy", 25000, R.drawable.ic_coffee));
        productList.add(new Product("Kopi Espresso", "Kopi hitam pekat", 20000, R.drawable.ic_coffee));
        productList.add(new Product("Kopi Cappuccino", "Kopi dengan busa susu", 28000, R.drawable.ic_coffee));
        productList.add(new Product("Kopi Americano", "Kopi espresso dengan air panas", 22000, R.drawable.ic_coffee));
        productList.add(new Product("Kopi Mocha", "Kopi dengan coklat", 30000, R.drawable.ic_coffee));
        productList.add(new Product("Matcha Latte", "Matcha dengan susu creamy", 20000, R.drawable.ic_coffee));

        // Data Makanan - Menggunakan ic_coffee sementara (ganti nanti jika ada gambar)
        productList.add(new Product("Sosis Bakar", "Sosis bakar dengan saus spesial", 15000, R.drawable.ic_coffee));
        productList.add(new Product("Kentang Goreng", "Kentang goreng crispy", 18000, R.drawable.ic_coffee));
        productList.add(new Product("Mix Platter", "Campuran snack favorit", 35000, R.drawable.ic_coffee));
        productList.add(new Product("Tahu Cabe Garam", "Tahu goreng dengan cabe dan garam", 12000, R.drawable.ic_coffee));
        productList.add(new Product("Nasi Goreng", "Nasi goreng dengan telur dan ayam", 25000, R.drawable.ic_coffee));

        filteredList.addAll(productList);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(filteredList, this);
        recyclerView.setAdapter(adapter);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Pencarian saat submit
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Pencarian saat mengetik
                filter(newText);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                // Tampilkan semua data saat search ditutup
                filteredList.clear();
                filteredList.addAll(productList);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void filter(String text) {
        filteredList.clear();

        if (text.isEmpty()) {
            filteredList.addAll(productList);
        } else {
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(product);
                }
            }
        }

        adapter.notifyDataSetChanged();

        // Tampilkan pesan jika tidak ada hasil
        if (filteredList.isEmpty()) {
            textViewNoResults.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            textViewNoResults.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    // Inner class Product
    public static class Product {
        private String name;
        private String description;
        private int price;
        private int imageResId;

        public Product(String name, String description, int price, int imageResId) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.imageResId = imageResId;
        }

        public String getName() { return name; }
        public String getDescription() { return description; }
        public int getPrice() { return price; }
        public int getImageResId() { return imageResId; }
    }
}