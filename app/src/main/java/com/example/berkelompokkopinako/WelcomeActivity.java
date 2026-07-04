package com.example.berkelompokkopinako;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button btnEnjoyCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnEnjoyCoffee = findViewById(R.id.btnEnjoyCoffee);

        btnEnjoyCoffee.setOnClickListener(v -> {
            Toast.makeText(
                    WelcomeActivity.this,
                    "Lanjut ke halaman menu (dikerjakan teman)",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}