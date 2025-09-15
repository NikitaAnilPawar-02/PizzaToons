<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.OrdersDAOImpl"%>
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
            <th>Order ID</th>
            <th>Product Name</th>
            <th>Payment Type</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <%
            OrdersDAOImpl dao = new OrdersDAOImpl(DBConnect.getConn());
            List<Orders> allOrders = dao.getOrdersByUserId(user.getUserId());

            double grandTotal = 0; // total bill amount

            for (Orders order : allOrders) {
                double rowTotal = order.getPrice() * order.getQuantity();
                grandTotal += rowTotal;
        %>
        <tr>
            <td><%= order.getOrderId() %></td>
            <td><%= order.getProductName() %></td>
            <td><%= order.getPaymentType() %></td>
            <td><%= order.getPrice() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= rowTotal %></td>
        </tr>
        <% } %>

        <!-- Grand Total Row -->
        <tr>
            <td colspan="5" class="text-end"><b>Total Bill Amount</b></td>
            <td><b><%= grandTotal %></b></td>
        </tr>
        </tbody>
    </table>
</div>

<!-- Start Footer -->
<%@ include file="all_component/footer.jsp" %>
<!-- End Footer -->
</body>
</html>
