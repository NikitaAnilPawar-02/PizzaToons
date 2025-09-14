<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.OrdersDAOImpl"%>
<%@page import="com.DAO.OrdersDAO"%>
<%@page import="com.entity.Orders"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>PizzaToons: Orders</title>
<%@ include file="all_component/allCSS.jsp" %>
<style type="text/css">
body {
    overflow-x: hidden;
}
</style>
</head>
<body>
    <%
    User user = (User) session.getAttribute("userobj");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    %>
    <!-- Start Navbar -->
    <%@ include file="all_component/navbar.jsp" %>
    <!-- End Navbar -->
    <div class="container p-1">
        <h5 class="text-center">Your Orders</h5>
        <table class="table mt-3 table-info">
            <thead>
                <tr>
                    <th scope="col">Order ID</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Total</th>
                    <th scope="col">Total Bill Amount</th>
                    <th scope="col">Payment Type</th>
                </tr>
            </thead>
            <tbody>
                <%
                OrdersDAOImpl dao = new OrdersDAOImpl(DBConnect.getConn());
                List<Orders> allOrders = dao.getOrdersByUserId(user.getUserId());

                Map<Integer, List<Orders>> ordersMap = new HashMap<>();

                // Group orders by Order ID
                for (Orders order : allOrders) {
                    int orderId = order.getOrderId();
                    if (!ordersMap.containsKey(orderId)) {
                        ordersMap.put(orderId, new ArrayList<>());
                    }
                    ordersMap.get(orderId).add(order);
                }

                // Iterate through orders map to display orders
                for (Map.Entry<Integer, List<Orders>> entry : ordersMap.entrySet()) {
                    int orderId = entry.getKey();
                    List<Orders> orderDetails = entry.getValue();
                    Orders firstOrder = orderDetails.get(0);
                %>
                <tr>
                    <td rowspan="<%=orderDetails.size()%>"><%= orderId %></td>
                    <td><%= firstOrder.getProductName() %></td>
                    <td><%= firstOrder.getPrice() %></td>
                    <td><%= firstOrder.getQuantity() %></td>
                    <td><%= firstOrder.getTotalPrice() %></td>
                    <td rowspan="<%= orderDetails.size() %>"><%= calculateTotalBillAmount(orderDetails) %></td>
                    <td rowspan="<%= orderDetails.size() %>"><%= firstOrder.getPaymentType() %></td>
                </tr>
                <%
                for (int i = 1; i < orderDetails.size(); i++) {
                %>
                <tr>
                    <td><%= orderDetails.get(i).getProductName() %></td>
                    <td><%= orderDetails.get(i).getPrice() %></td>
                    <td><%= orderDetails.get(i).getQuantity() %></td>
                    <td><%= orderDetails.get(i).getTotalPrice() %></td>
                </tr>
                <%
                }
                %>
                <%
                }
                %>
            </tbody>
        </table>
    </div>
    <!-- Start Footer -->
    <%@ include file="all_component/footer.jsp" %>
    <!-- End Footer -->

<%! private double calculateTotalBillAmount(List<Orders> orderDetails) {
		double totalBillAmount = 0;
		for (Orders order : orderDetails) {
			totalBillAmount += order.getTotalAmount();
		}
		return totalBillAmount;
	} %>

</body>
</html>
