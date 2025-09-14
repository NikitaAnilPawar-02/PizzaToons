<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.entity.ProductDetails"%>
<%@ page import="com.DAO.ProductDAO"%>
<%@ page import="com.DAO.ProductDAOImpl"%>
<%@ page import="com.DB.DBConnect"%>
<%@ page import="com.entity.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>PizzaToons: Home</title>
<%@include file="all_component/allCSS.jsp"%>

<style type="text/css">
.PizzaToons {
	font-weight: bold;
	font-size: 4.1rem;
	text-align: center;
}

.caption-text {
	margin-top: 55px;
	font-size: 3.0rem;
	background: #FF0000;
}

.subcaption-text {
	font-size: 2.1rem;
	margin-top: 25px;
	background: #FFEA00;
}

.card-hover:hover {
	background-color: #e0e0e0;
}

.title {
	background-color: black;
	color: white;
}

.image-container {
	position: relative;
	overflow: hidden;
}

.blur-image {
	filter: blur(3px);
	width: 100%;
	height: 400px;
}

.about-us-container {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: rgba(255, 255, 255, 0.8);
	padding: 20px;
	border-radius: 10px;
	text-align: center;
	width: 80%;
}

body {
	overflow-x: hidden;
}
</style>
</head>

<body>

	<%
	User u = (User) session.getAttribute("userobj");
	%>

	<!-- Start Navbar -->
	<%@include file="all_component/navbar.jsp"%>
	<!-- End Navbar -->


<% 
String Status = (String) session.getAttribute("Status"); 
if (Status != null) { 
%>
<!-- Toast Notification -->
<div class="toast align-items-center text-bg-success border-0 position-fixed top-0 start-50 translate-middle-x" role="alert" aria-live="assertive" aria-atomic="true" style="z-index: 1050;">
    <div class="d-flex">
        <div class="toast-body">
            <%= Status %>
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
</div>
<%
    session.removeAttribute("Status");
}
%>

	<!-- Start Home Image -->
	<div
		style="background-color: black; display: flex; align-items: center; justify-content: center;">
		<div class="container">
			<div class="row align-items-center m-2">
				<!-- Image Section -->
				<div class="col-md-6 d-flex justify-content-center">
					<img src="img/hero-bg.jpg" alt="Image"
						class="img-fluid rounded-circle"
						style="width: 450px; height: 450px;">
				</div>
				<!-- Text Section -->
				<div class="col-md-6 text-center text-md-start mt-3">
					<div class="caption p-4 text-center">
						<h1 class="fw-bolder">
							<span style="font-size: 4rem; color: red;">PIZZA</span><span style="font-size: 4rem; color: gold;">TOONS</span>
						</h1>
						<h3 class="caption-text text-white"
							style="font-size: 2rem; font-weight: 600;">Fresh, Organic
							Pizzas</h3>
						<h4 class="subcaption-text text-white"
							style="font-size: 1.8rem; font-weight: 500;">Taste the
							difference with every bite!</h4>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Home Image -->

	<!-- Start Vegetables -->
	<div class="container">
		<h4 class="text-center my-5 py-2 title rounded-pill ">VEG PIZZAS</h4>

		<div class="row">
			<%
			ProductDAOImpl VegPizzasDAO = new ProductDAOImpl(DBConnect.getConn());
			List<ProductDetails> VegPizzasList = VegPizzasDAO.getNewVegPizzas();
			for (ProductDetails p : VegPizzasList) {
			%>
			<div class="col-md-3">
				<div class="card card-hover position-relative">
					<div class="card-body text-center"
						style="position: relative; line-height: 0.5;">
						<img class="img-fluid rounded-circle" alt=""
							src="Products/<%=p.getImageURL()%>"
							style="height: 200px; width: 200px;"> <span
							class="badge bg-danger position-absolute top-0 start-60 translate-middle fs-5"></span>
						<p class="pt-3"><%=p.getProductName()%></p>
						<p>
							Category:
							<%=p.getProductCategory()%></p>
						<div class="row">
							<div class="d-inline p-1">
								<%
								if (u == null) {
								%>
								<a href="login.jsp" class="btn btn-outline-danger btn-sm"><i
									class="fa-solid fa-cart-plus"></i> Add Cart</a>
								<%
								} else {
								%>
								<a
									href="CartServlet?productId=<%=p.getProductId()%>&userId=<%=u.getUserId()%>"
									class="btn btn-outline-danger btn-sm"> <i
									class="fa-solid fa-cart-plus"></i> Add Cart
								</a>

								<%
								}
								%>
								<a href="view_product.jsp?id=<%=p.getProductId()%>"
									class="btn btn-outline-success btn-sm"><i
									class="fa-solid fa-eye"></i> View</a> <a href=""
									class="btn btn-outline-primary btn-sm"><i
									class="fa-solid fa-indian-rupee-sign"></i> <%=p.getPrice()%>/-</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%
			}
			%>
		</div>
	</div>

	<div class="text-center mt-3">
		<a href="all_veg_pizzas.jsp" class="btn btn-primary">View All</a>
	</div>
	<!-- End Vegetables -->

	<hr>

	<!-- Start Our Story -->
	<div class="container-fluid image-container">
		<img src="img/C.jpg" class="blur-image" alt="Image">
		<div class="about-us-container">
			<h2>OUR STORY</h2>
			<h5>Crafting the Perfect Pizza, One Slice at a Time</h5>
			<p>At PizzaToons, we are passionate about delivering the finest
				pizzas made with fresh, locally sourced ingredients. What started as
				a small idea to serve delicious, high-quality pizzas, has grown into
				a journey of perfecting every flavor. From handpicked vegetables to
				our signature pizza dough, we focus on quality at every step.
				Whether it's a family dinner or a casual meal, we promise you a
				pizza experience that’s always fresh, always satisfying.</p>
			<a href="about.jsp"><button type="button" class="btn btn-success">Know
					More</button></a>
		</div>
	</div>
	<!-- End Our Story -->

	<!-- Start Fruits -->
	<div class="container">
		<h4 class="text-center my-5 py-2 title rounded-pill ">NON VEG
			PIZZAS</h4>

		<div class="row">
			<%
			ProductDAOImpl NonVegPizzasDAO = new ProductDAOImpl(DBConnect.getConn());
			List<ProductDetails> NonVegPizzasList = NonVegPizzasDAO.getNewNonVegPizzas();
			for (ProductDetails q : NonVegPizzasList) {
			%>
			<div class="col-md-3">
				<div class="card card-hover position-relative">
					<div class="card-body text-center"
						style="position: relative; line-height: 0.5;">
						<img class="img-fluid rounded-circle" alt=""
							src="Products/<%=q.getImageURL()%>"
							style="height: 200px; width: 200px;"> <span
							class="badge bg-danger position-absolute top-0 start-60 translate-middle fs-5"></span>
						<p class="pt-3"><%=q.getProductName()%></p>
						<p>
							Category:
							<%=q.getProductCategory()%></p>
						<div class="row">
							<div class="d-inline p-1">
								<%
								if (u == null) {
								%>
								<a href="login.jsp" class="btn btn-outline-danger btn-sm"><i
									class="fa-solid fa-cart-plus"></i> Add Cart</a>
								<%
								} else {
								%>
								<a
									href="CartServlet?productId=<%=q.getProductId()%>&userId=<%=u.getUserId()%>"
									class="btn btn-outline-danger btn-sm"> <i
									class="fa-solid fa-cart-plus"></i> Add Cart
								</a>

								<%
								}
								%>
								<a href="view_product.jsp?id=<%=q.getProductId()%>"
									class="btn btn-outline-success btn-sm"><i
									class="fa-solid fa-eye"></i> View</a> <a href=""
									class="btn btn-outline-primary btn-sm"><i
									class="fa-solid fa-indian-rupee-sign"></i> <%=q.getPrice()%></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

	<div class="text-center mt-3">
		<a href="all_nonveg_pizzas.jsp" class="btn btn-primary">View All</a>
	</div>
	<!-- End Fruits -->

	<hr>

	<!-- Start Reviews -->
	<div class="container">
		<h4 class="text-center mt-5 py-2 title rounded-pill ">REVIEWS</h4>
	</div>
	<div id="carouselExampleAutoplaying" class="carousel slide"
		data-bs-ride="carousel" style="padding: 75px;">
		<div class="carousel-inner"
			style="background-color: #eeeeee; padding: 75px; color: black;">
			<div class="carousel-item active" data-bs-interval="2500">
				<div class="d-block w-100">
					<div class="container p-5">
						<div class="carousel-caption d-none d-md-block "
							style="color: black; margin-top: 200px;">
							<h5>"The best pizza I've had in years! Fresh ingredients and
								perfect crust. Highly recommend!"</h5>
							<p>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="carousel-item" data-bs-interval="2500">
				<div class="d-block w-100">
					<div class="container p-5">
						<div class="carousel-caption d-none d-md-block "
							style="color: black; margin-top: 200px;">
							<h5>"PizzaToons' pizzas are always fresh and packed with
								flavor. My go-to place for pizza!"</h5>

							<p>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: grey;"></i></span>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="carousel-item" data-bs-interval="2500">
				<div class="d-block w-100">
					<div class="container p-5">
						<div class="carousel-caption d-none d-md-block "
							style="color: black; margin-top: 200px;">
							<h5>"Love variety of toppings and the freshness of the
								veggies. A pizza experience like no other!"</h5>

							<p>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
								<span><i class="fa-solid fa-star" style="color: #f4d11f;"></i></span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- End Reviews -->

	<!-- Start Footer -->
	<%@include file="all_component/footer.jsp"%>
	<!-- End Footer -->
<!-- Bootstrap JS (required for toast functionality) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<!-- Activate Toast -->
<script>
    var toastElement = document.querySelector('.toast');
    var toast = new bootstrap.Toast(toastElement);
    toast.show();
</script>
</body>
</html>