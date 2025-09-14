package com.entity;

public class Cart {
	private int userId;
	private int productId;
	private String productName;
	private int quantity;
	private double price;
	private double subTotal;
	private double grandTotal;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public double calculateSubTotal() {
		return this.price * this.quantity;
	}

	@Override
	public String toString() {
		return "Cart [userId=" + userId + ", productId=" + productId
				+ ", quantity=" + quantity + ", price=" + price + ", subTotal="
				+ subTotal + ", grandTotal=" + grandTotal + "]";
	}
}
