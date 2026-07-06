package com.example.berkelompokkopinako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    Button btnBayar;
    ImageView btnBack;
    TextView tvTotalPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btnBayar = findViewById(R.id.btnBayar);
        btnBack = findViewById(R.id.btnBack);
        tvTotalPayment = findViewById(R.id.tvTotalPayment);

        // Terima total
        int total = getIntent().getIntExtra("total", 17000);
        tvTotalPayment.setText(String.valueOf(total));

        // Back
        btnBack.setOnClickListener(v -> finish());

        // Bayar
        btnBayar.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, SuccessActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
