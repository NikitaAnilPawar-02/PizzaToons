package com.DAO;

import java.sql.*;
import java.util.*;

import com.entity.ProductDetails;

public class ProductDAOImpl implements ProductDAO {

	private Connection conn;

	public ProductDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean addproducts(ProductDetails p) {
		boolean f = false;
		try {
			String sql = "INSERT INTO product(productName, productCategory, description, price, quantity, imageURL, isActive) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.setString(2, p.getProductCategory());
			ps.setString(3, p.getDescription());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5, p.getQuantity());
			ps.setString(6, p.getImageURL());
			ps.setString(7, p.getIsActive());
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<ProductDetails> getAllProduct() {
		List<ProductDetails> list = new ArrayList<ProductDetails>();
		ProductDetails p = null;

		try {
			String sql = "SELECT * FROM product";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new ProductDetails();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductCategory(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPrice(rs.getFloat(5));
				p.setQuantity(rs.getInt(6));
				p.setImageURL(rs.getString(7));
				p.setIsActive(rs.getString(8));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ProductDetails getProductById(int id) {
		ProductDetails p = null;

		try {
			String sql = "SELECT * FROM product WHERE productId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new ProductDetails();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductCategory(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPrice(rs.getFloat(5));
				p.setQuantity(rs.getInt(6));
				p.setImageURL(rs.getString(7));
				p.setIsActive(rs.getString(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public boolean updateEditProducts(ProductDetails p) {
		boolean f = false;
		try {
			String sql = "UPDATE product SET productName = ?, productCategory = ?, description = ?, price = ?, quantity = ?, imageURL=?, isActive = ? WHERE productId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.setString(2, p.getProductCategory());
			ps.setString(3, p.getDescription());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5, p.getQuantity());
			ps.setString(6, p.getImageURL());
			ps.setString(7, p.getIsActive());
			ps.setInt(8, p.getProductId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public boolean deleteProducts(int id) {
		boolean f = false;
		try {
			String sql = "DELETE FROM product WHERE productId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	@Override
	public List<ProductDetails> getNewVegPizzas() {
		List<ProductDetails> list = new ArrayList<ProductDetails>();
		ProductDetails p = null;
		try {
			String sql = "SELECT * FROM product WHERE productCategory=? and isActive=? ORDER BY productId DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Veg Pizzas");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;
			while (rs.next() && i <= 8) {
				p = new ProductDetails();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductCategory(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPrice(rs.getFloat(5));
				p.setQuantity(rs.getInt(6));
				p.setImageURL(rs.getString(7));
				p.setIsActive(rs.getString(8));
				list.add(p);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductDetails> getNewNonVegPizzas() {
		List<ProductDetails> list = new ArrayList<ProductDetails>();
		ProductDetails p = null;
		try {
			String sql = "SELECT * FROM product WHERE productCategory=? and isActive=? ORDER BY productId DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Non Veg Pizzas");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;
			while (rs.next() && i <= 8) {
				p = new ProductDetails();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductCategory(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPrice(rs.getFloat(5));
				p.setQuantity(rs.getInt(6));
				p.setImageURL(rs.getString(7));
				p.setIsActive(rs.getString(8));
				list.add(p);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductDetails> getAllVegPizzas() {
		List<ProductDetails> list = new ArrayList<ProductDetails>();
		ProductDetails p = null;
		try {
			String sql = "SELECT * FROM product WHERE productCategory =? and isActive=? ORDER BY productId DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Veg Pizzas");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new ProductDetails();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductCategory(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPrice(rs.getFloat(5));
				p.setQuantity(rs.getInt(6));
				p.setImageURL(rs.getString(7));
				p.setIsActive(rs.getString(8));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductDetails> getAllNonVegPizzas() {
		List<ProductDetails> list = new ArrayList<ProductDetails>();
		ProductDetails p = null;
		try {
			String sql = "SELECT * FROM product WHERE productCategory =? and isActive=? ORDER BY productId DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Non Veg Pizzas");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new ProductDetails();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductCategory(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPrice(rs.getFloat(5));
				p.setQuantity(rs.getInt(6));
				p.setImageURL(rs.getString(7));
				p.setIsActive(rs.getString(8));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductDetails> getProductBySearch(String ch) {
		List<ProductDetails> list = new ArrayList<ProductDetails>();
		ProductDetails p = null;

		try {
			String sql = "SELECT * FROM product WHERE productName like ? or  productCategory  like ? and isActive = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + ch + "%");
			ps.setString(2, "%" + ch + "%");
			ps.setString(3, "Active");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new ProductDetails();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductCategory(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPrice(rs.getFloat(5));
				p.setQuantity(rs.getInt(6));
				p.setImageURL(rs.getString(7));
				p.setIsActive(rs.getString(8));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
