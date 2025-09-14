<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="all_component/favicon.png" type="">
<title>PizzaToons: Customize Pizza</title>
<%@include file="all_component/allCSS.jsp"%>
</head>
<style type="text/css">
body {
    overflow-x: hidden;
    background-image: url('img/customizePizza.jpg');
    background-size: cover;
    background-position: center center;
    background-attachment: fixed; 
}

.container {
    background-color: rgba(255, 255, 255, 0.8); 
    padding: 30px;
    border-radius: 10px;
}
</style>
<body>

  <!-- Start Navbar -->
    <%@include file="all_component/navbar.jsp"%>
    <!-- End Navbar -->
    
<div class="container mt-5">
   <div class="card mx-auto shadow-lg" style="max-width: 600px;">
      <div class="card-header bg-danger text-white text-center">
        <h2>🍕 Build Your Perfect Pizza!</h2>
        <p class="mb-0">Choose your size, crust, and toppings for the ultimate pizza experience!</p>
      </div>
      <div class="card-body">
        <form action="/submit_order" method="POST">
          <div class="mb-3">
            <label for="pizzaSize" class="form-label">Choose Pizza Size <span class="text-danger">*</span></label>
            <select class="form-select" id="pizzaSize" name="pizzaSize" required>
              <option value="" disabled selected>Select size</option>
              <option value="small">Small (6")</option>
              <option value="medium">Medium (9")</option>
              <option value="large">Large (12")</option>
              <option value="extra-large">Extra Large (16")</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="crustType" class="form-label">Choose Crust Type <span class="text-danger">*</span></label>
            <select class="form-select" id="crustType" name="crustType" required>
              <option value="" disabled selected>Select crust type</option>
              <option value="thin">Thin</option>
              <option value="thick">Thick</option>
              <option value="stuffed">Stuffed</option>
              <option value="cheese-burst">Cheese Burst</option>
            </select>
          </div>
        <!-- Toppings -->
<div class="mb-3">
  <label class="form-label">Choose Toppings (Select multiple)</label>
  <div class="row">
    <!-- Column 1 -->
    <div class="col-6">
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingPaneer" name="toppings[]" value="paneer">
        <label class="form-check-label" for="toppingPaneer">
          Paneer
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingCapsicum" name="toppings[]" value="capsicum">
        <label class="form-check-label" for="toppingCapsicum">
          Capsicum
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingCorn" name="toppings[]" value="corn">
        <label class="form-check-label" for="toppingCorn">
          Sweet Corn
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingJalapenos" name="toppings[]" value="jalapenos">
        <label class="form-check-label" for="toppingJalapenos">
          Jalapeños
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingOnions" name="toppings[]" value="onions">
        <label class="form-check-label" for="toppingOnions">
          Onions
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingTomatoes" name="toppings[]" value="tomatoes">
        <label class="form-check-label" for="toppingTomatoes">
          Tomatoes
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingMushrooms" name="toppings[]" value="mushrooms">
        <label class="form-check-label" for="toppingMushrooms">
          Mushrooms
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingSpinach" name="toppings[]" value="spinach">
        <label class="form-check-label" for="toppingSpinach">
          Spinach
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingPineapple" name="toppings[]" value="pineapple">
        <label class="form-check-label" for="toppingPineapple">
          Pineapple
        </label>
      </div>
    </div>
    <!-- Column 2 -->
    <div class="col-6">
    
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingBacon" name="toppings[]" value="bacon">
        <label class="form-check-label" for="toppingBacon">
          Bacon
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingShrimp" name="toppings[]" value="shrimp">
        <label class="form-check-label" for="toppingShrimp">
          Shrimp
        </label>
      </div>
       <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingGarlic" name="toppings[]" value="garlic">
        <label class="form-check-label" for="toppingGarlic">
          Garlic
        </label>
      </div>      
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingOlives" name="toppings[]" value="olives">
        <label class="form-check-label" for="toppingOlives">
          Olives
        </label>
      </div>
        <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingChickenTikka" name="toppings[]" value="chicken_tikka">
        <label class="form-check-label" for="toppingChickenTikka">
          Chicken Tikka
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingTandooriChicken" name="toppings[]" value="tandoori_chicken">
        <label class="form-check-label" for="toppingTandooriChicken">
          Tandoori Chicken
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingButterChicken" name="toppings[]" value="butter_chicken">
        <label class="form-check-label" for="toppingButterChicken">
          Butter Chicken
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingPepperoni" name="toppings[]" value="pepperoni">
        <label class="form-check-label" for="toppingPepperoni">
          Pepperoni
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="toppingGrilledChicken" name="toppings[]" value="grilled_chicken">
        <label class="form-check-label" for="toppingGrilledChicken">
          Grilled Chicken
        </label>
      </div>
    </div>
  </div>
</div>


          <div class="mb-3">
            <label for="quantity" class="form-label">Quantity <span class="text-danger">*</span></label>
            <input type="number" class="form-control" id="quantity" name="quantity" min="1" value="1" required>
          </div>
          <div class="mb-3">
            <label for="specialInstructions" class="form-label">Special Instructions</label>
            <textarea class="form-control" id="specialInstructions" name="specialInstructions" rows="3" placeholder="Any special instructions for your pizza?"></textarea>
          </div>
          <div class="text-center">
            <button type="submit" class="btn btn-danger w-100">Order Now</button>
          </div>
        </form>
      </div>
    </div>
  </div>
 <!-- Start Footer -->
    <%@include file="all_component/footer.jsp"%>
    <!-- End Footer -->
</body>
</html>
