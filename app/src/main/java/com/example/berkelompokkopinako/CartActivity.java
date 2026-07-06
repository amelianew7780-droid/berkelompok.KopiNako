package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    Button btnCheckout, btnTambahPesanan, btnPlus, btnMinus;
    ImageView btnBackToMenu, btnRemove;
    TextView tvQuantity, tvTotal, tvCartItemName, tvCartItemPrice;
    ImageView ivCartItem;

    int quantity = 1;
    int harga = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnCheckout = findViewById(R.id.btnCheckout);
        btnTambahPesanan = findViewById(R.id.btnTambahPesanan);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnRemove = findViewById(R.id.btnRemove);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvTotal = findViewById(R.id.tvTotal);
        tvCartItemName = findViewById(R.id.tvCartItemName);
        tvCartItemPrice = findViewById(R.id.tvCartItemPrice);
        ivCartItem = findViewById(R.id.ivCartItem);

        // Terima data
        String namaProduk = getIntent().getStringExtra("nama_produk");
        harga = getIntent().getIntExtra("harga", 15000);
        int gambar = getIntent().getIntExtra("gambar", R.drawable.ic_sosisbakar);

        // Set data
        tvCartItemName.setText(namaProduk);
        tvCartItemPrice.setText(harga / 1000 + "K");
        ivCartItem.setImageResource(gambar);
        updateTotal();

        // Back to Menu
        btnBackToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });

        // Tambah Pesanan
        btnTambahPesanan.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        // Plus quantity
        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
            updateTotal();
        });

        // Minus quantity
        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
                updateTotal();
            }
        });

        // Remove item
        btnRemove.setOnClickListener(v -> {
            finish();
        });

        // Checkout
        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            intent.putExtra("total", harga * quantity);
            startActivity(intent);
        });
    }

    private void updateTotal() {
        tvTotal.setText((harga * quantity / 1000) + "K");
    }
}