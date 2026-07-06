package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button btnEnjoyCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnEnjoyCoffee = findViewById(R.id.btnEnjoyCoffee);

        btnEnjoyCoffee.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });
    }
}