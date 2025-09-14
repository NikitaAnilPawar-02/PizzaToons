<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>Admin: Add Products</title>
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

	<div class="container my-3">
		<div class="card mx-auto shadow-lg" style="max-width: 750px;">
			<div class="card-header bg-danger text-white text-center">
				<h2>ADD PIZZA</h2>
			</div>
			<div class="card-body">

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
				<form action="ProductsAdd" method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-6 mb-3 pt-2">
							<label for="productName" class="form-label">Pizza Name</label> 
							<input type="text" class="form-control border border-2" id="productName" required="required" name="productName">
						</div>

						<div class="col-md-6 mb-3">
							<label class="form-label">Pizza Category</label> 
							<select class="form-select border border-2" name="productCategory">
								<option selected>--- Select ---</option>
								<option value="Veg Pizzas">Veg Pizzas</option>
								<option value="Non Veg Pizzas">Non Veg Pizzas</option>
							</select>
						</div>

						<div class="col-md-6 mb-3">
							<label for="description" class="form-label">Description</label>
							<textarea class="form-control border border-2" id="description" name="description" required="required"></textarea>
						</div>

						<div class="col-md-6 mb-3">
							<label for="price" class="form-label">Price</label> 
							<input type="number" class="form-control border border-2" id="price" required="required" name="price">
						</div>

						<div class="col-md-6 mb-3">
							<label for="quantity" class="form-label">Quantity</label> 
							<input type="number" class="form-control border border-2" id="quantity" required="required" name="quantity">
						</div>

						<div class="col-md-6 mb-3">
							<label for="formFile" class="form-label">Upload Photo</label> 
							<input class="form-control border border-2" type="file" id="formFile" name="imageURL">
						</div>

						<div class="col-md-6 mb-3">
							<label class="form-label">Status</label> 
							<select class="form-select border border-2" name="isActive">
								<option selected>--- Select ---</option>
								<option value="Active">Active</option>
								<option value="Inactive">Inactive</option>
							</select>
						</div>

					</div>
					
					<div class="text-center">
						<button type="submit" class="btn btn-outline-success">Add Pizza</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
