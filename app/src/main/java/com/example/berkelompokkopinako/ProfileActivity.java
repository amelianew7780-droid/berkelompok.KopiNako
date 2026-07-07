package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imageViewProfile;
    private EditText editTextName, editTextEmail, editTextPhone, editTextAddress;
    private Button buttonSave, buttonLogout;
    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inisialisasi views
        imageViewProfile = findViewById(R.id.imageViewProfile);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLogout = findViewById(R.id.buttonLogout);
        textViewTitle = findViewById(R.id.textViewTitle);

        // Load data user (contoh)
        loadUserData();

        // Setup button Save
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });

        // Setup button Logout
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void loadUserData() {
        // Contoh data user - nanti bisa diganti dengan data dari database/shared preferences
        editTextName.setText("John Doe");
        editTextEmail.setText("john.doe@email.com");
        editTextPhone.setText("081234567890");
        editTextAddress.setText("Jl. Kopi No. 123, Jakarta");
    }

    private void saveUserData() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        // Validasi
        if (name.isEmpty()) {
            editTextName.setError("Nama harus diisi");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email harus diisi");
            editTextEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Nomor telepon harus diisi");
            editTextPhone.requestFocus();
            return;
        }

        // Simpan data (contoh - nanti bisa disimpan ke database/shared preferences)
        Toast.makeText(this, "Profil berhasil disimpan", Toast.LENGTH_SHORT).show();

        // Kembali ke menu
        Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void logout() {
        // Konfirmasi logout
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    // Kembali ke WelcomeActivity atau SplashActivity
                    Intent intent = new Intent(ProfileActivity.this, WelcomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        // Kembali ke MenuActivity
        Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
