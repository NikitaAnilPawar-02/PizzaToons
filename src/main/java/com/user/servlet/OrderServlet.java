package com.user.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import com.DAO.CartDAOImpl;
import com.DAO.OrdersDAOImpl;
import com.DB.DBConnect;
import com.entity.Cart;
import com.entity.Orders;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			int id = Integer.parseInt(req.getParameter("id"));
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String fulladdress = address + ", " + landmark + ", " + city + ", "
					+ state + ", " + pincode;
			String paymentType = req.getParameter("paymentType");

			User u = (User) session.getAttribute("userobj");

			// Ensure user is not null and all required user details are
			// available
			if (u == null) {
				session.setAttribute("Msg",
						"User not found. Please log in again.");
				resp.sendRedirect("login.jsp");
				return;
			}
			// Fetch cart items for the user
			CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
			List<Cart> plist = dao.getProductByUser(id);

			if (plist.isEmpty()) {
				session.setAttribute("Msg", "Please Add Items");
				resp.sendRedirect("checkout.jsp");
			} else {
				OrdersDAOImpl dao2 = new OrdersDAOImpl(DBConnect.getConn());
				Orders o = null;
				ArrayList<Orders> orderList = new ArrayList<Orders>();

                for (Cart c : plist) {
                    o = new Orders();
                    o.setUserId(c.getUserId());
                    o.setProductId(c.getProductId());
                    o.setQuantity(c.getQuantity());
                    o.setPrice(c.getPrice());
                    o.setPaymentType(paymentType);
                    o.setStatus("Pending");
                    o.setOrderDate(new Timestamp(System.currentTimeMillis()));
                    o.setFullAddress(fulladdress);
                    orderList.add(o);
                }


                // Check if payment type is selected
				if ("noselect".equals(paymentType)) {
					session.setAttribute("Msg", "Please Select Payment Type");
					resp.sendRedirect("checkout.jsp");
				} else {
					// Save the order to the database
					boolean f = dao2.saveOrder(orderList);
					if (f) {
						// Clear cart after successful order
						dao.clearCart(id);
						resp.sendRedirect("order_success.jsp");
					} else {
						session.setAttribute("Msg",
								"Order Failed. Please try again.");
						resp.sendRedirect("checkout.jsp");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Log error and notify the user
			HttpSession session = req.getSession();
			session.setAttribute("Msg",
					"An error occurred while processing your order.");
			resp.sendRedirect("checkout.jsp");
		}
	}
}