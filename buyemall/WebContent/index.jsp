<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    	
	   String pageName=(String) request.getAttribute("pageName");
    	System.out.println(pageName);
	%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Buy'em All</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <!-- <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet"> -->

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>

	<script src="js/ajax-json.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.js"></script>
    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    
	<%@include file="src/Header.jsp" %>
	
	
	<%if(pageName.equals("Home")){%>
		<%@include file="src/Home/Home.jsp" %>
	<%} %>	
	
	<%if(pageName.equals("Detail")){%>
		<%@include file="src/Detail/Detail.jsp" %>
	<%} %>	
	
	<%if(pageName.equals("Shop")){%>
		<%@include file="src/Shop/Shop.jsp" %>
	<%} %>	
	
	<%if(pageName.equals("LgForward")){%>
		<%@include file="src/Login/Login.jsp" %>
	<%} %>	
	<%if(pageName.equals("RgForward")){%>
		<%@include file="src/Register/Register.jsp" %>
	<%} %>	
	
	<%if(pageName.equals("Cart")){%>
		<%@include file="src/Cart/cart.jsp" %>
	<%} %>	
    <%if(pageName.equals("Pagamento")){%>
		<%@include file="src/Pagamento/pagamento.jsp" %>
	<%} %>	
	
	<%if(pageName.equals("Profilo")){%>
		<%@include file="src/Profilo/profilo.jsp" %>
	<%} %>	
    
    <%@include file="src/Footer.jsp" %>

    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

<!-- da mettere nell'head*-->
    <!-- JavaScript Libraries -->
    
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
   <!--*-->

</body>

</html>