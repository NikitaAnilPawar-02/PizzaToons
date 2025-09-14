package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.entity.Cart;

public class CartDAOImpl implements CartDAO {

	private Connection conn;

	public CartDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean addCart(Cart c) {
		boolean f = false;
		try {
			String priceQuery = "SELECT price FROM product WHERE productId = ?";
			PreparedStatement psPrice = conn.prepareStatement(priceQuery);
			psPrice.setInt(1, c.getProductId());
			ResultSet rs = psPrice.executeQuery();

			if (rs.next()) {
				double productPrice = rs.getDouble("price");
				c.setPrice(productPrice); // Set the price from the product
											// table
			}

			String sql = "INSERT INTO cart (productId, userId, quantity, grandTotal) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getProductId());
			ps.setInt(2, c.getUserId());
			ps.setInt(3, c.getQuantity());
			ps.setDouble(4, c.getGrandTotal());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<Cart> getCartItemsByUser(int userId) {
		List<Cart> cartItems = new ArrayList<>();
		String query = "SELECT cart.productId, cart.userId, cart.quantity, p.price FROM cart "
				+ "INNER JOIN product p ON cart.productId = p.productId WHERE cart.userId = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cart cart = new Cart();
				cart.setUserId(rs.getInt("userId"));
				cart.setProductId(rs.getInt("productId"));
				cart.setQuantity(rs.getInt("quantity"));
				double price = rs.getDouble("price"); // Fetch price from
														// product table
				cart.setPrice(price); // Set the price
				cart.setSubTotal(cart.calculateSubTotal()); // You can calculate
															// subTotal here or
															// inside Cart class
				cartItems.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartItems;
	}

	public void updateGrandTotal(int userId, double grandTotal) {
		String query = "UPDATE cart SET grandTotal = ? WHERE userId = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setDouble(1, grandTotal);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteProduct(int pid, int uid) {
		boolean f = false;
		try {
			String sql = "DELETE FROM cart WHERE productId = ? AND userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setInt(2, uid);
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
	public Cart getCartItemByProductAndUser(int productId, int userId) {
		Cart item = null;
		try {
			String sql = "SELECT cart.cartId, cart.productId, cart.userId, cart.quantity, p.price, p.productName "
					+ "FROM cart INNER JOIN product p ON cart.productId = p.productId WHERE cart.productId = ? AND cart.userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				item = new Cart();
				item.setProductId(rs.getInt("productId"));
				item.setUserId(rs.getInt("userId"));
				item.setQuantity(rs.getInt("quantity"));
				double productPrice = rs.getDouble("price");
				item.setPrice(productPrice);
				item.setProductName(rs.getString("productName"));
				item.setSubTotal(item.calculateSubTotal());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean updateQuantity(int userId, int productId, int newQuantity) {
		boolean success = false;
		try {
			String sql = "SELECT price FROM product WHERE productId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double price = rs.getDouble("price");
				double totalPrice = price * newQuantity;

				String updateSql = "UPDATE cart SET quantity = ?, grandTotal = ? WHERE userId = ? AND productId = ?";
				PreparedStatement updatePs = conn.prepareStatement(updateSql);
				updatePs.setInt(1, newQuantity);
				updatePs.setDouble(2, totalPrice);
				updatePs.setInt(3, userId);
				updatePs.setInt(4, productId);

				int rowsUpdated = updatePs.executeUpdate();
				if (rowsUpdated > 0) {
					success = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public double getTotalByProduct(int userId, int productId, int quantity) {
		double totalPrice = 0.0;
		try {
			String sql = "SELECT price FROM product WHERE productId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double price = rs.getDouble("price");
				totalPrice = price * quantity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPrice;
	}

	@Override
	public List<Cart> placedOrder(int userId) {
		List<Cart> c = new ArrayList<>();
		try {
			String sql = "SELECT cart.cartId, cart.productId, cart.userId, cart.quantity, p.price, p.productName "
					+ "FROM cart INNER JOIN product p ON cart.productId = p.productId WHERE cart.userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cart cart = new Cart();
				cart.setUserId(rs.getInt("userId"));
				cart.setProductId(rs.getInt("productId"));
				cart.setQuantity(rs.getInt("quantity"));
				double productPrice = rs.getDouble("price");
				cart.setPrice(productPrice);
				cart.setProductName(rs.getString("productName"));
				cart.setSubTotal(cart.calculateSubTotal());
				c.add(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void clearCart(int userId) {
		try {
			String sql = "DELETE FROM cart WHERE userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cart> getProductByUser(int userId) {
		List<Cart> list = new ArrayList<>();
		try {
			String sql = "SELECT c.productId, c.userId, c.quantity, p.productName, p.price "
					+ "FROM cart c "
					+ "INNER JOIN product p ON c.productId = p.productId "
					+ "WHERE c.userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cart cart = new Cart();
				cart.setProductId(rs.getInt("productId"));
				cart.setUserId(rs.getInt("userId"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setProductName(rs.getString("productName"));
				cart.setPrice(rs.getDouble("price"));
				list.add(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
