package com.user.servlet;

import java.io.IOException;
import java.util.List;

import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "QuantityIncreDecreServlet", urlPatterns = {
		"/QuantityIncreDecreServlet"})
public class QuantityIncreDecreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		int productId = Integer.parseInt(req.getParameter("productId"));
		int userId = Integer.parseInt(req.getParameter("userId"));

		if (action != null && userId >= 1) {
			CartDAOImpl cartDAO = new CartDAOImpl(DBConnect.getConn());

			Cart cartItem = cartDAO.getCartItemByProductAndUser(productId,
					userId);

			if (cartItem != null) {
				double subtotal = calculateSubTotal(cartItem.getQuantity(),
						cartItem.getPrice());
				cartItem.setSubTotal(subtotal);

				if ("inc".equals(action)) {
					incrementQuantity(cartDAO, cartItem);
				} else if ("dec".equals(action)) {
					decrementQuantity(cartDAO, cartItem);
				}
			}

			double grandTotal = calculateGrandTotal(cartDAO, userId);
			cartDAO.updateGrandTotal(userId, grandTotal);

			resp.sendRedirect("checkout.jsp");
		}
	}

	private void incrementQuantity(CartDAOImpl cartDAO, Cart cartItem) {
		int newQuantity = cartItem.getQuantity() + 1;
		cartItem.setQuantity(newQuantity);
		cartDAO.updateQuantity(cartItem.getUserId(), cartItem.getProductId(),
				newQuantity);

		double newSubtotal = calculateSubTotal(cartItem.getQuantity(),
				cartItem.getPrice());
		cartItem.setSubTotal(newSubtotal);
	}

	private void decrementQuantity(CartDAOImpl cartDAO, Cart cartItem) {
		if (cartItem.getQuantity() > 1) {
			int newQuantity = cartItem.getQuantity() - 1;
			cartItem.setQuantity(newQuantity);
			cartDAO.updateQuantity(cartItem.getUserId(),
					cartItem.getProductId(), newQuantity);

			double newSubTotal = calculateSubTotal(cartItem.getQuantity(),
					cartItem.getPrice());
			cartItem.setSubTotal(newSubTotal);
		}
	}

	private double calculateSubTotal(int quantity, double price) {
		return quantity * price;
	}

	private double calculateGrandTotal(CartDAOImpl cartDAO, int userId) {
		double grandTotal = 0;
		List<Cart> cartItems = cartDAO.getCartItemsByUser(userId);

		for (Cart item : cartItems) {
			grandTotal += item.getSubTotal();
		}
		return grandTotal;
	}
}
