package com.user.servlet;

import java.io.IOException;

import com.DAO.CartDAOImpl;
import com.DAO.ProductDAOImpl;
import com.DB.DBConnect;
import com.entity.Cart;
import com.entity.ProductDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int productId = Integer.parseInt(req.getParameter("productId"));
			int userId = Integer.parseInt(req.getParameter("userId"));

			CartDAOImpl cartDAO = new CartDAOImpl(DBConnect.getConn());
			Cart existingCartItem = cartDAO
					.getCartItemByProductAndUser(productId, userId);
			HttpSession session = req.getSession();

			if (existingCartItem != null) {
				session.setAttribute("Status", "Item already in Cart");
			} else {
				ProductDAOImpl productDAO = new ProductDAOImpl(
						DBConnect.getConn());
				ProductDetails product = productDAO.getProductById(productId);

				Cart cartItem = new Cart();
				cartItem.setProductId(productId);
				cartItem.setUserId(userId);
				cartItem.setProductName(product.getProductName());
				cartItem.setPrice(product.getPrice());
				cartItem.setQuantity(1);

				boolean added = cartDAO.addCart(cartItem);

				if (added) {
					session.setAttribute("Status", "Added to Cart");
				} else {
					session.setAttribute("Status", "Failed to add to Cart");
				}
			}

			ProductDetails product = new ProductDAOImpl(DBConnect.getConn())
					.getProductById(productId);
			if (product.getProductCategory().equals("VegPizzas")) {
				resp.sendRedirect("all_veg_pizzas.jsp");
			} else if (product.getProductCategory().equals("NonVegPizzas")) {
				resp.sendRedirect("all_nonveg_pizzas.jsp");
			} else {
				resp.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
