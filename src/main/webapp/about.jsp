<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>PizzaToons: About</title>
<style type="text/css">
.title {
    background-color: black;
    color: white;
}

body {
    overflow-x: hidden;
}
</style>

<%@include file="all_component/allCSS.jsp"%>
</head>
<body>
    <!-- Start Navbar -->
    <%@include file="all_component/navbar.jsp"%>
    <!-- End Navbar -->

    <div class="container">
        <h4 class="text-center my-5 py-2 title rounded-pill">ABOUT US</h4>
        <p>Welcome to PizzaToons, a distinctive destination for food lovers who value quality and authenticity. We specialize in blending traditional Indian flavors with modern tastes, offering pizzas and food experiences that celebrate our cultural roots while prioritizing fresh, locally-sourced ingredients.</p>
        
        <p>At PizzaToons, we believe in ethical sourcing and sustainable practices. Our network of farmers, who share our commitment to quality and environmental responsibility, supplies us with the finest produce, from fruits and vegetables to herbs and spices, bringing a burst of freshness to every dish.</p>
        
        <p>PizzaToons is more than just pizza—our journey reflects a deep-rooted history. Established on a foundation dating back to the mid-19th century, our commitment to quality and freshness began with our founders, Abhinav and Samyak Jaggi, who started by bringing farm-fresh products to tables in Maharashtra and beyond. Today, we continue this legacy, now enhanced by an expanded range and sustainable packaging practices.</p>
        
        <p>Join us in experiencing the art of great food crafted from the heart of India, delivered with care and responsibility for our planet and community.</p>
    </div>

    <div class="container">
        <div class="row mt-5">
            <div class="col-md-4">
                <div class="d-flex justify-content-center align-items-center">
                    <img src="img/a1.png" alt="Fresh Produce" class="img-fluid" style="width: 100px; height: 100px;">
                </div>
            </div>
            <div class="col-md-8">
                <h4>Freshest Ingredients</h4>
                <p>We pride ourselves on selecting only the best, freshest ingredients to ensure every bite is bursting with flavor. At PizzaToons, quality is never compromised, and we guarantee a premium experience that’s crafted to delight.</p>
            </div>
        </div>
        
        <div class="row mt-5">
            <div class="col-md-4">
                <div class="d-flex justify-content-center align-items-center">
                    <img src="img/a2.png" alt="Curated Selection" class="img-fluid" style="width: 100px; height: 100px;">
                </div>
            </div>
            <div class="col-md-8">
                <h4>Carefully Curated Selection</h4>
                <p>With over 400 carefully selected fresh food items and a growing grocery section, we offer a variety that caters to diverse tastes and dietary preferences. Our collection is curated based on quality and feedback from our valued customers.</p>
            </div>
        </div>

        <div class="row mt-5">
            <div class="col-md-4">
                <div class="d-flex justify-content-center align-items-center">
                    <img src="img/a3.png" alt="Direct to Customer" class="img-fluid" style="width: 100px; height: 100px;">
                </div>
            </div>
            <div class="col-md-8">
                <h4>From Farm to Table</h4>
                <p>Our commitment to quality extends to our facilities, which meet the highest standards of hygiene and safety. We prioritize sustainable, recyclable packaging, and use only food-grade materials to ensure our produce reaches you in the freshest condition.</p>
            </div>
        </div>

        <div class="row mt-5">
            <div class="col-md-4">
                <div class="d-flex justify-content-center align-items-center">
                    <img src="img/a4.png" alt="Traceability" class="img-fluid" style="width: 100px; height: 100px;">
                </div>
            </div>
            <div class="col-md-8">
                <h4>Transparency and Traceability</h4>
                <p>At PizzaToons, we value transparency in every step of our supply chain. We believe in knowing exactly where our food comes from, and our traceability practices ensure that every ingredient meets our high standards for quality and sustainability.</p>
            </div>
        </div>
    </div>

    <!-- Start Footer -->
    <%@include file="all_component/footer.jsp"%>
    <!-- End Footer -->
</body>
</html>
