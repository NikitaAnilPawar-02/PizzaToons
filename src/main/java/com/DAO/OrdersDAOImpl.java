package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.entity.Orders;

public class OrdersDAOImpl implements OrdersDAO {
	private Connection conn;

	public OrdersDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

    @Override
    public boolean saveOrder(List<Orders> orders) {
        String insertOrderQuery = "INSERT INTO `order` "
                + "(userId, productId, quantity, totalAmount, paymentType, status, orderDate, fullAddress) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertOrderQuery)) {
            for (Orders o : orders) {
                ps.setInt(1, o.getUserId());
                ps.setInt(2, o.getProductId());
                ps.setInt(3, o.getQuantity());
                double totalAmount = o.getQuantity() * o.getPrice();
                ps.setDouble(4, totalAmount);
                ps.setString(5, o.getPaymentType());
                ps.setString(6, o.getStatus());
                ps.setTimestamp(7, new Timestamp(o.getOrderDate().getTime()));
                ps.setString(8, o.getFullAddress());
                ps.addBatch();
            }
            int[] results = ps.executeBatch();
            return Arrays.stream(results).allMatch(r -> r > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Orders> getAllOrders() {
        List<Orders> list = new ArrayList<>();
        try {
            String sql = "SELECT o.orderId, o.productId, o.userId, o.quantity, o.totalAmount, o.status, o.orderDate, "
                    + "o.paymentType, o.fullAddress, "
                    + "u.name, u.phnNo, u.email, "
                    + "p.productName, p.price "
                    + "FROM `order` o "
                    + "JOIN user u ON o.userId = u.userId "
                    + "JOIN product p ON o.productId = p.productId";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderId(rs.getInt("orderId"));
                o.setUserId(rs.getInt("userId"));
                o.setProductId(rs.getInt("productId"));
                o.setQuantity(rs.getInt("quantity"));
                o.setTotalAmount(rs.getDouble("totalAmount"));
                o.setStatus(rs.getString("status"));
                o.setOrderDate(rs.getTimestamp("orderDate"));
                o.setPaymentType(rs.getString("paymentType"));
                o.setFullAddress(rs.getString("fullAddress"));

                // extra details
                o.setUserName(rs.getString("name"));
                o.setUserPhone(rs.getString("phnNo"));
                o.setUserEmail(rs.getString("email"));
                o.setProductName(rs.getString("productName"));
                o.setPrice(rs.getDouble("price"));

                list.add(o);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Orders> getProduct(int userID) {
        List<Orders> list = new ArrayList<>();
        try {
            String sql = "SELECT o.orderId, o.productId, o.userId, o.quantity, o.totalAmount, o.status, o.orderDate, "
                    + "o.paymentType, o.fullAddress, "
                    + "u.name, u.phnNo, u.email, "
                    + "p.productName, p.price "
                    + "FROM `order` o "
                    + "JOIN user u ON o.userId = u.userId "
                    + "JOIN product p ON o.productId = p.productId "
                    + "WHERE o.userId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderId(rs.getInt("orderId"));
                o.setUserId(rs.getInt("userId"));
                o.setProductId(rs.getInt("productId"));
                o.setQuantity(rs.getInt("quantity"));
                o.setTotalAmount(rs.getDouble("totalAmount"));
                o.setStatus(rs.getString("status"));
                o.setOrderDate(rs.getTimestamp("orderDate"));
                o.setPaymentType(rs.getString("paymentType"));
                o.setFullAddress(rs.getString("fullAddress"));

                // extra details
                o.setUserName(rs.getString("name"));
                o.setUserPhone(rs.getString("phnNo"));
                o.setUserEmail(rs.getString("email"));
                o.setProductName(rs.getString("productName"));
                o.setPrice(rs.getDouble("price"));

                list.add(o);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }




    @Override
    public List<Orders> getOrdersByUserId(int userId) {
        List<Orders> orders = new ArrayList<>();
        try {
            String sql = "SELECT o.orderId, o.productId, o.userId, o.quantity, o.totalAmount, o.status, o.orderDate, "
                    + "o.paymentType, o.fullAddress, "
                    + "u.name, u.phnNo, u.email, "
                    + "p.productName, p.price "
                    + "FROM `order` o "
                    + "JOIN user u ON o.userId = u.userId "
                    + "JOIN product p ON o.productId = p.productId "
                    + "WHERE o.userId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderId(rs.getInt("orderId"));
                o.setUserId(rs.getInt("userId"));
                o.setProductId(rs.getInt("productId"));
                o.setQuantity(rs.getInt("quantity"));
                o.setTotalAmount(rs.getDouble("totalAmount"));
                o.setStatus(rs.getString("status"));
                o.setOrderDate(rs.getTimestamp("orderDate"));
                o.setPaymentType(rs.getString("paymentType"));
                o.setFullAddress(rs.getString("fullAddress"));

                // extra details
                o.setUserName(rs.getString("name"));
                o.setUserPhone(rs.getString("phnNo"));
                o.setUserEmail(rs.getString("email"));
                o.setProductName(rs.getString("productName"));
                o.setPrice(rs.getDouble("price"));

                orders.add(o);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
