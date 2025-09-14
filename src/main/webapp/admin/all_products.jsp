<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.ProductDetails"%>
<%@ page import="com.DAO.ProductDAO"%>
<%@ page import="com.DAO.ProductDAOImpl"%>
<%@ page import="com.DB.DBConnect"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>Admin: All Products</title>
<%@include file="allCSS.jsp"%>
<style type="text/css">
body {
	overflow-x: hidden;
}
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
	<!-- Navbar -->
	<%@include file="navbar.jsp"%>
	<h3 class="text-center pt-3">Hello Admin</h3>
	<%
	String Status = (String) session.getAttribute("Status");
	if (Status != null) {
	%><div class="alert alert-primary text-center" role="alert">
		<%=Status%>
	</div>
	<%
	session.removeAttribute("Status");
	}
	%>
	<div class="container-fluid my-4">
		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Photo</th>
					<th scope="col">Product Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				ProductDAOImpl dao = new ProductDAOImpl(DBConnect.getConn());
				List<ProductDetails> list = dao.getAllProduct();
				for (ProductDetails p : list) {
				%>
				<tr>
					<th scope="row"><%=p.getProductId()%></th>
					<td><img src="../Products/<%=p.getImageURL()%>"
						style="width: 50px; height: 50px;"></td>
					<td><%=p.getProductName()%></td>
					<td><%=p.getProductCategory()%></td>
					<td><%=p.getPrice()%></td>
					<td><%=p.getQuantity() %></td>
					<td><%=p.getIsActive()%></td>
					<td><a href="edit_products.jsp?id=<%=p.getProductId()%>"
						class="btn btn-sm btn-primary"><i
							class="fa-solid fa-pen-to-square"></i> Edit</a> <a href="DeleteProductServlet?id=<%=p.getProductId() %>"
						class="btn btn-sm btn-danger"><i class="fa-solid fa-trash"></i>
							Delete</a></td>
				</tr>

				<%
				}
				%>

			</tbody>
		</table>
	</div>

</body>
</html>