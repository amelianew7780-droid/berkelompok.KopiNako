package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CardView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    CardView cardSosisBakar, cardMatchaLatte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardSosisBakar = findViewById(R.id.cardSosisBakar);
        cardMatchaLatte = findViewById(R.id.cardMatchaLatte);

        // Klik Sosis Bakar
        cardSosisBakar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("nama_produk", "Sosis Bakar");
            intent.putExtra("harga", 15000);
            intent.putExtra("gambar", R.drawable.ic_sosisbakar);
            startActivity(intent);
        });

        // Klik Matcha Latte
        cardMatchaLatte.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("nama_produk", "Matcha Latte");
            intent.putExtra("harga", 20000);
            intent.putExtra("gambar", R.drawable.ic_matchalatte);
            startActivity(intent);
        });
    }
}