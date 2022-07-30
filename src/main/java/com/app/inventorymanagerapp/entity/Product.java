package com.app.inventorymanagerapp.entity;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="brand")
    private String brand;
    @Column(name="expiry_date")
    private String expiryDate;
    @Column(name="price")
    private int price;
    @Column(name="quantity")
    private int quantity;

    public Product(){

    }

    public Product(int productId, String productName, String brand, String expiryDate, int price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.expiryDate = expiryDate;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
