<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.OrdersDAOImpl"%>
<%@page import="com.DAO.UserDAOImpl"%>
<%@page import="java.util.*"%>
<%@page import="com.entity.Orders"%>
<%@page import="com.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin: Orders</title>
    <%@include file="allCSS.jsp"%>
    <style>
        .order-details td { vertical-align: middle; }
    </style>
</head>
<body>
<%
    User u = (User) session.getAttribute("userobj");
    if (u == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<%@include file="navbar.jsp"%>
<h3 class="text-center">Hello Admin</h3>
<div class="container-fluid my-4">
    <table class="table">
        <thead class="table-dark">
        <tr>
            <th>Order ID</th>
            <th>Name</th>
            <th>Phone No</th>
            <th>Email</th>
            <th>Address</th>
            <th>Payment Type</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Total Bill Amount</th>
        </tr>
        </thead>
        <tbody>
        <%
            OrdersDAOImpl dao = new OrdersDAOImpl(DBConnect.getConn());
            List<Orders> allOrders = dao.getAllOrders();

            // Map orderId -> list of orders
            Map<Integer, List<Orders>> ordersMap = new HashMap<>();
            for (Orders order : allOrders) {
                int orderId = order.getOrderId();
                ordersMap.putIfAbsent(orderId, new ArrayList<>());
                ordersMap.get(orderId).add(order);
            }

            UserDAOImpl userDao = new UserDAOImpl(DBConnect.getConn());

            for (Map.Entry<Integer, List<Orders>> entry : ordersMap.entrySet()) {
                int orderId = entry.getKey();
                List<Orders> orderDetails = entry.getValue();
                Orders firstOrder = orderDetails.get(0);
                String paymentType = firstOrder.getPaymentType();

                User order_user = userDao.getUserDetailsByOrderId(orderId);

                double totalBillAmount = 0;
                for (Orders order : orderDetails) {
                    totalBillAmount += order.getTotalAmount();
                }
        %>
        <tr>
            <td rowspan="<%=orderDetails.size()%>"><%=orderId%></td>
            <% if (order_user != null) { %>
            <td rowspan="<%=orderDetails.size()%>"><%=order_user.getName()%></td>
            <td rowspan="<%=orderDetails.size()%>"><%=order_user.getPhnNo()%></td>
            <td rowspan="<%=orderDetails.size()%>"><%=order_user.getEmail()%></td>
            <td rowspan="<%=orderDetails.size()%>"><%=firstOrder.getFullAddress()%></td>
            <% } else { %>
            <td rowspan="<%=orderDetails.size()%>">N/A</td>
            <td rowspan="<%=orderDetails.size()%>">N/A</td>
            <td rowspan="<%=orderDetails.size()%>">N/A</td>
            <td rowspan="<%=orderDetails.size()%>">N/A</td>
            <% } %>
            <td rowspan="<%=orderDetails.size()%>"><%=paymentType%></td>

            <% for (int i = 0; i < orderDetails.size(); i++) {
                Orders order = orderDetails.get(i); %>
            <td><%=order.getProductName()%></td>
            <td><%=order.getPrice()%></td>
            <td><%=order.getQuantity()%></td>
            <td><%=order.getTotalAmount()%></td>
            <% if (i == 0) { %>
            <td rowspan="<%=orderDetails.size()%>"><%=totalBillAmount%></td>
            <% } %>
        </tr>
        <% if (i < orderDetails.size() - 1) { %><tr><% } %>
                <% } %>
                <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
