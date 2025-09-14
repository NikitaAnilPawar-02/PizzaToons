<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.entity.ProductDetails"%>
<%@ page import="com.DAO.ProductDAO"%>
<%@ page import="com.DAO.ProductDAOImpl"%>
<%@ page import="com.DB.DBConnect"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>Admin: Edit Products</title>
<%@include file="allCSS.jsp"%>
<style type="text/css">
body {
	overflow-x: hidden;
}
</style>
</head>
<body style="background-color: black">
	<%
	User u = (User) session.getAttribute("userobj");
	if (u == null) {
		response.sendRedirect("../login.jsp");
		return;
	}
	%>
	<!-- Navbar -->
	<%@include file="navbar.jsp"%>

	<div class="container my-4">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h4 class="text-center">EDIT PRODUCTS</h4>


						<%
						int id = Integer.parseInt(request.getParameter("id"));
						ProductDAOImpl dao = new ProductDAOImpl(DBConnect.getConn());
						ProductDetails p = dao.getProductById(id);
						%>
						<form action="EditProductServlet" method="post">
							<input type="hidden" name="productId" value="<%=p.getProductId()%>">
							<div class="mb-3  pt-2">
								<label for="productName" class="form-label">Product Name</label>
								<input type="text" class="form-control border border-2 "
									id="productName" aria-describedby="emailHelp"
									required="required" name="name" value="<%=p.getProductName()%>">
							</div>
							<div class="mb-3">
								<label class="form-label">Product Category</label> <select
									class="form-select border border-2"
									aria-label="Default select example" name="category">
									<option value="Veg Pizzas"
										<%=p.getProductCategory().equals("Veg Pizzas") ? "selected" : ""%>>Veg Pizzas</option>
									<option value="Non Veg Pizzas"
										<%=p.getProductCategory().equals("Non Veg Pizzas") ? "selected" : ""%>>Non Veg Pizzas</option>
								</select>
							</div>
							<div class="mb-3">
								<label for="price" class="form-label">Price</label> <input
									type="number" class="form-control border border-2" id="price"
									aria-describedby="emailHelp" required="required" name="price"
									value="<%=p.getPrice()%>">
							</div>
							<div class="mb-3">
								<label class="form-label">Product Status</label> <select
									class="form-select border border-2"
									aria-label="Default select example" name="status">
									<option value="Active"
										<%=p.getIsActive().equals("Active") ? "selected" : ""%>>Active</option>
									<option value="Inactive"
										<%=p.getIsActive().equals("Inactive") ? "selected" : ""%>>Inactive</option>
								</select>
							</div>

							<div class="text-center">
								<button type="submit" class="btn btn-outline-success">Update</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>