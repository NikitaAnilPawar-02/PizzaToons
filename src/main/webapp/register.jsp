<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>PizzaToons: Register</title>
<%@include file="all_component/allCSS.jsp"%>
<style type="text/css">
a {
	text-decoration: none;
}

body {
	overflow-x: hidden;
	background-color: black;
	color: #fff;
}

#passwordHelp {
	font-size: 0.9rem;
	color: #6c757d;
}
</style>
</head>
<body>
	<%@include file="all_component/navbar.jsp"%>
	<div class="container p-3">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h4 class="text-center">REGISTER</h4>
						<%
						String registrationStatus = (String) session.getAttribute("registrationStatus");
						if (registrationStatus != null) {
						%>
						<div class="alert alert-primary text-center" role="alert">
							<%=registrationStatus %>
						</div>
						<%
						session.removeAttribute("registrationStatus");
						}
						%>
						<form action="register" method="post">
							<div class="mb-3">
								<label for="name" class="form-label">Name</label>
								<input type="text" 
									class="form-control border border-2 border-success-subtle" 
									id="name" 
									aria-describedby="nameHelp" 
									pattern="[A-Za-z\s]+" 
									required 
									name="name">
								<div id="nameHelp" class="form-text">Enter your full name.</div>
							</div>
							<div class="mb-3">
								<label for="phnno" class="form-label">Phone No</label>
								<input type="tel" 
									class="form-control border border-2 border-success-subtle" 
									id="phnno" 
									pattern="[7-9]{1}[0-9]{9}" 
									required 
									name="phnno">
								<div id="phoneHelp" class="form-text">Enter a valid 10-digit phone number.</div>
							</div>
							<div class="mb-3">
								<label for="email" class="form-label">Email address</label>
								<input type="email" 
									class="form-control border border-2 border-success-subtle" 
									id="email" 
									required 
									name="email">
								<div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Create Password</label>
								<input type="password" 
									class="form-control border border-2 border-success-subtle" 
									id="password" 
									required 
									name="password"
									pattern=".{6,}">
								<div id="passwordHelp" class="form-text">Password must be at least 6 characters long.</div>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-outline-success">Register</button>
								<div class="pt-2">
									Already Registered? Please <a href="login.jsp">Login</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
