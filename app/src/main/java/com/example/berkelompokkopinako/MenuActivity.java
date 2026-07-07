package com.example.berkelompokkopinako;
import com.example.berkelompokkopinako.ProfileActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// ✅ TAMBAH IMPORT INI
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    CardView cardSosisBakar, cardMatchaLatte;

    // ✅ TAMBAH DEKLARASI INI
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardSosisBakar = findViewById(R.id.cardSosisBakar);
        cardMatchaLatte = findViewById(R.id.cardMatchaLatte);

        // ✅ TAMBAH KODE BOTTOM NAVIGATION INI
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                // Sudah di halaman home, tidak perlu pindah
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

        // Klik Sosis Bakar (MAKANAN)
        cardSosisBakar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("nama_produk", "Sosis Bakar");
            intent.putExtra("harga", 15000);
            intent.putExtra("gambar", R.drawable.ic_sosisbakar);
            intent.putExtra("kategori", "makanan"); // ✅ TAMBAH KATEGORI
            startActivity(intent);
        });

        // Klik Matcha Latte (MINUMAN)
        cardMatchaLatte.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("nama_produk", "Matcha Latte");
            intent.putExtra("harga", 20000);
            intent.putExtra("gambar", R.drawable.ic_matchalatte);
            intent.putExtra("kategori", "minuman"); // ✅ TAMBAH KATEGORI
            startActivity(intent);
        });
    }
}
