package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    Button btnAddToCart;
    ImageView btnBack, ivProductImage;
    TextView tvProductName, tvProductDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btnBack);
        ivProductImage = findViewById(R.id.ivProductImage);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductDescription = findViewById(R.id.tvProductDescription);

        // Terima data dari MenuActivity
        String namaProduk = getIntent().getStringExtra("nama_produk");
        int harga = getIntent().getIntExtra("harga", 0);
        int gambar = getIntent().getIntExtra("gambar", R.drawable.ic_sosisbakar);

        // Set data ke view
        tvProductName.setText(namaProduk);
        ivProductImage.setImageResource(gambar);

        // Tombol Back
        btnBack.setOnClickListener(v -> finish());

        // Add to Cart
        btnAddToCart.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            intent.putExtra("nama_produk", namaProduk);
            intent.putExtra("harga", harga);
            intent.putExtra("gambar", gambar);
            startActivity(intent);

            Toast.makeText(this, namaProduk + " ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();
        });
    }
}