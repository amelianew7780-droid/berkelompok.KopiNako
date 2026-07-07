package com.example.berkelompokkopinako;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<SearchActivity.Product> productList;
    private Context context;

    public ProductAdapter(List<SearchActivity.Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        SearchActivity.Product product = productList.get(position);
        holder.textViewName.setText(product.getName());
        holder.textViewDesc.setText(product.getDescription());
        holder.textViewPrice.setText("Rp " + String.format("%,d", product.getPrice()));
        holder.imageView.setImageResource(product.getImageResId());

        // 🔽 Klik item produk akan mengarah ke halaman Keranjang
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CartActivity.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_desc", product.getDescription());
            intent.putExtra("product_image", product.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName, textViewDesc, textViewPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewProduct);
            textViewName = itemView.findViewById(R.id.textViewProductName);
            textViewDesc = itemView.findViewById(R.id.textViewProductDesc);
            textViewPrice = itemView.findViewById(R.id.textViewProductPrice);
        }
    }
}