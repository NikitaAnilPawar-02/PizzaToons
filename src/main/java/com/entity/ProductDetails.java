package com.entity;

public class ProductDetails {
	private int productId, quantity;
	private String productName, productCategory, description, imageURL,
			isActive;
	private Double price;

	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetails(String productName, String productCategory,
			String description, Double price, int quantity, String imageURL,
			String isActive) {
		super();
		this.productName = productName;
		this.productCategory = productCategory;
		this.description = description;
		this.imageURL = imageURL;
		this.isActive = isActive;
		this.price = price;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double f) {
		this.price = f;
	}

	@Override
	public String toString() {
		return "ProductDetails [productId=" + productId + ", quantity="
				+ quantity + ", productName=" + productName
				+ ", productCategory=" + productCategory + ", description="
				+ description + ", imageURL=" + imageURL + ", isActive="
				+ isActive + ", price=" + price + "]";
	}

}