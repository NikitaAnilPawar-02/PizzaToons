package com.DAO;

import java.util.*;
import com.entity.ProductDetails;

public interface ProductDAO {
	public boolean addproducts(ProductDetails p);

	public List<ProductDetails> getAllProduct();

	public ProductDetails getProductById(int id);

	public boolean updateEditProducts(ProductDetails p);

	public boolean deleteProducts(int id);

	public List<ProductDetails> getNewVegPizzas();

	public List<ProductDetails> getNewNonVegPizzas();

	public List<ProductDetails> getAllVegPizzas();

	public List<ProductDetails> getAllNonVegPizzas();

	public List<ProductDetails> getProductBySearch(String ch);

}
