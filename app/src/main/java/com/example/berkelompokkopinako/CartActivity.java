package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private ImageView btnBackToMenu, btnRemove;
    private TextView tvCartItemName, tvCartItemPrice, tvQuantity, tvTotal;
    private Button btnMinus, btnPlus, btnTambahPesanan, btnCheckout;

    private static String productName = "Sosis Bakar";
    private static int productPrice = 15000;
    private static int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Inisialisasi
        btnBackToMenu = findViewById(R.id.btnBackToMenu);
        btnRemove = findViewById(R.id.btnRemove);
        tvCartItemName = findViewById(R.id.tvCartItemName);
        tvCartItemPrice = findViewById(R.id.tvCartItemPrice);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvTotal = findViewById(R.id.tvTotal);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnTambahPesanan = findViewById(R.id.btnTambahPesanan);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Cek data dari Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product_name")) {
            productName = intent.getStringExtra("product_name");
            productPrice = intent.getIntExtra("product_price", 0);
        }

        updateDisplay();

        // ✅ PASTIKAN BUTTON BISA DIKLIK
        btnBackToMenu.setClickable(true);
        btnBackToMenu.setFocusable(true);
        btnBackToMenu.setOnClickListener(v -> {
            Toast.makeText(this, "Back clicked", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnRemove.setOnClickListener(v -> {
            Toast.makeText(this, "Item dihapus", Toast.LENGTH_SHORT).show();
            quantity = 0;
            updateDisplay();
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateDisplay();
            }
        });

        btnPlus.setOnClickListener(v -> {
            quantity++;
            updateDisplay();
        });

        // ✅ BUTTON TAMBAH PESANAN
        btnTambahPesanan.setClickable(true);
        btnTambahPesanan.setFocusable(true);
        btnTambahPesanan.setOnClickListener(v -> {
            Toast.makeText(this, "Tambah pesanan clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(CartActivity.this, MenuActivity.class);
            startActivity(i);
            finish();
        });

        // ✅ BUTTON CHECKOUT - SUDAH DIPERBAIKI
        btnCheckout.setClickable(true);
        btnCheckout.setFocusable(true);
        btnCheckout.setOnClickListener(v -> {
            int total = productPrice * quantity;
            Toast.makeText(this, "Checkout clicked - Total: " + total, Toast.LENGTH_SHORT).show();

            // ✅ TAMBAHKAN INTENT KE HALAMAN CHECKOUT/PAYMENT
            Intent checkoutIntent = new Intent(CartActivity.this, PaymentActivity.class);
            checkoutIntent.putExtra("total", total);
            checkoutIntent.putExtra("product_name", productName);
            checkoutIntent.putExtra("quantity", quantity);
            startActivity(checkoutIntent);
        });
    }

    private void updateDisplay() {
        tvCartItemName.setText(productName);
        tvCartItemPrice.setText((productPrice / 1000) + "K");
        tvQuantity.setText(String.valueOf(quantity));
        tvTotal.setText((productPrice * quantity / 1000) + "K");
    }
}