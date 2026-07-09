package com.example.berkelompokkopinako;

class CartActivity {  // hapus "public"
    private String name;
    private int price;
    private int imageResId;
    private int quantity;

    public CartActivity(String name, int price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 1;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void increaseQuantity() { this.quantity++; }
    public void decreaseQuantity() {
        if (this.quantity > 1) this.quantity--;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}