package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.DB.DBConnect;
import com.entity.Orders;

public class OrdersDAOImpl implements OrdersDAO {
	private Connection conn;

	public OrdersDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean saveOrder(List<Orders> orders) {
		// Wrap `order` in backticks to avoid syntax issues
		String insertOrderQuery = "INSERT INTO `order` (userId, productId, quantity, totalAmount, paymentType, status, orderDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(insertOrderQuery)) {
			for (Orders o : orders) {
				ps.setInt(1, o.getUserId());
				ps.setInt(2, o.getProductId());
				ps.setInt(3, o.getQuantity());
				ps.setDouble(4, o.getTotalAmount());
				ps.setString(5, o.getPaymentType());
				ps.setString(6, o.getStatus());
				ps.setTimestamp(7, new Timestamp(o.getOrderDate().getTime())); // Convert
																				// java.util.Date
																				// to
																				// java.sql.Timestamp
				ps.addBatch();
			}
			int[] results = ps.executeBatch();
			return Arrays.stream(results).allMatch(result -> result > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Orders> getProduct(int userID) {
		List<Orders> list = new ArrayList<>();
		try {
			// Wrap `order` in backticks
			String sql = "SELECT * FROM `order` WHERE userId = ?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, userID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Orders o = new Orders();
					o.setOrderId(rs.getInt("orderId"));
					o.setUserId(rs.getInt("userId"));
					o.setProductId(rs.getInt("productId"));
					o.setQuantity(rs.getInt("quantity"));
					o.setTotalAmount(rs.getDouble("totalAmount"));
					o.setPaymentType(rs.getString("paymentType"));
					o.setStatus(rs.getString("status"));
					o.setOrderDate(rs.getTimestamp("orderDate"));
					list.add(o);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Orders> getAllOrders() {
		List<Orders> list = new ArrayList<>();
		try {
			// Wrap `order` in backticks
			String sql = "SELECT o.orderId, o.productId, o.userId, o.quantity, o.totalAmount, o.status, o.orderDate, o.paymentType, u.fullAddress "
					+ "FROM `order` o " + // Backticks added
					"JOIN user u ON o.userId = u.userId";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Orders o = new Orders();
					o.setOrderId(rs.getInt("orderId"));
					o.setUserId(rs.getInt("userId"));
					o.setFullAddress(rs.getString("fullAddress"));
					o.setProductId(rs.getInt("productId"));
					o.setQuantity(rs.getInt("quantity"));
					o.setTotalAmount(rs.getDouble("totalAmount"));
					o.setStatus(rs.getString("status"));
					o.setOrderDate(rs.getTimestamp("orderDate"));
					o.setPaymentType(rs.getString("paymentType"));
					list.add(o);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Orders> getOrdersByUserId(int userId) {
		List<Orders> orders = new ArrayList<>();
		try {
			// Wrap `order` in backticks
			String query = "SELECT o.orderId, o.productId, o.userId, o.quantity, o.totalAmount, o.status, o.orderDate, o.paymentType, u.fullAddress "
					+ "FROM `order` o " + // Backticks added
					"JOIN user u ON o.userId = u.userId "
					+ "WHERE o.userId = ?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Orders order = new Orders();
					order.setOrderId(rs.getInt("orderId")); // Changed to int
															// for consistency
					order.setUserId(rs.getInt("userId"));
					order.setFullAddress(rs.getString("fullAddress"));
					order.setProductId(rs.getInt("productId"));
					order.setQuantity(rs.getInt("quantity"));
					order.setTotalAmount(rs.getDouble("totalAmount"));
					order.setStatus(rs.getString("status"));
					order.setOrderDate(rs.getTimestamp("orderDate"));
					order.setPaymentType(rs.getString("paymentType"));
					orders.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
}
