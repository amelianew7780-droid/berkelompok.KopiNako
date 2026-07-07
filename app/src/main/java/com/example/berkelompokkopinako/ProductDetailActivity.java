package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    Button btnAddToCart;
    ImageView btnBack, ivProductImage;
    TextView tvProductName, tvProductDescription, tvProductPrice;
    TextView tvAddOnsTitle;
    LinearLayout layoutAddOns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Inisialisasi views
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btnBack);
        ivProductImage = findViewById(R.id.ivProductImage);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductDescription = findViewById(R.id.tvProductDescription);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvAddOnsTitle = findViewById(R.id.tvAddOnsTitle);
        layoutAddOns = findViewById(R.id.layoutAddOns);

        // Terima data dari MenuActivity
        String namaProduk = getIntent().getStringExtra("nama_produk");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        int harga = getIntent().getIntExtra("harga", 0);
        int gambar = getIntent().getIntExtra("gambar", R.drawable.ic_coffee);
        String kategori = getIntent().getStringExtra("kategori");

        // Set data ke view
        if (namaProduk != null) {
            tvProductName.setText(namaProduk);
        }

        if (deskripsi != null) {
            tvProductDescription.setText(deskripsi);
        } else {
            tvProductDescription.setText("Produk berkualitas tinggi");
        }

        tvProductPrice.setText("Rp " + String.format("%,d", harga));

        try {
            ivProductImage.setImageResource(gambar);
        } catch (Exception e) {
            // Jika gambar tidak ditemukan, gunakan default
            ivProductImage.setImageResource(R.drawable.ic_coffee);
        }

        // Sembunyikan Add Ons jika minuman
        if ("minuman".equals(kategori)) {
            tvAddOnsTitle.setVisibility(View.GONE);
            layoutAddOns.setVisibility(View.GONE);
        } else {
            tvAddOnsTitle.setVisibility(View.VISIBLE);
            layoutAddOns.setVisibility(View.VISIBLE);
        }

        // Tombol Back
        btnBack.setOnClickListener(v -> finish());

        // Add to Cart (key disamakan dengan CartActivity: product_name, product_price, product_image)
        btnAddToCart.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            intent.putExtra("product_name", namaProduk);
            intent.putExtra("product_price", harga);
            intent.putExtra("product_image", gambar);
            startActivity(intent);

            Toast.makeText(this, namaProduk + " ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();
        });
    }
}