<%@page import="it.unisa.bean.Account"%>
<%@page import="java.sql.SQLException"%>
<%@page import="it.unisa.DAO.AccountDAO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="it.unisa.bean.Prodotto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
    	List<Prodotto> carousel=(List<Prodotto>)request.getAttribute("carousel");
    
    	if(carousel==null || carousel.size()<=0)
    	{
    		carousel=new LinkedList<>();
    		for(int i=0;i<5;i++)
    			carousel.add(Prodotto.nullProduct());
    	}		
    %>
    
	<!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row bg-secondary py-2 px-xl-5">
            <div class="col-lg-6 d-none d-lg-block">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark" href="">FAQs</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Help</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Support</a>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-dark pl-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class=".back row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="Home" class="text-decoration-none">
                    <img src="img/Logo Grande.png" alt="Logo Grande" width="300" height="90">
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="">
                    <div class="input-group" style="width: max-content;">
                        <input type="text" class="form-control" placeholder="Cerca Prodotti">
                        <a href="Shop" class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </a>
                    </div>
                </form>
            </div>
            <div class="container-fluid col-lg-auto text-right">
                <table>
                <caption></caption>
                    <tr>
                        <td>
                            <div class="nav-item dropdown" style="width: min-content">
                            <%
                            
                            AccountDAO conn=new AccountDAO();
                    		Account accountBean=null;
                    		Boolean cs=false;
                            Integer count=0;
                    		String account=(String)request.getAttribute("accountBean");
                    		if (account != null) {
                    			try {
                    				accountBean = conn.doRetrieveByKey(account);
                    				count=accountBean.getCarrello().size();
                    				cs=true;
                    			} catch (SQLException e) {
									cs=false;
                    				accountBean = null;
                    			}
                    		}
                            
                             %>
                            
                            <%if(cs){%>
                                <a href="#" class="nav-link dropdown-toggle border" data-toggle="dropdown"><%=accountBean.getUsername() %>
                                <img src="img/Icon Account.png" alt="Icon_Account" width="25" height="20"></a>
                                <div class="dropdown-menu rounded-0 m-0">
                                    <a href="Profilo" class="dropdown-item">Profilo</a>
                                    <a href="Logout" class="dropdown-item">Logout</a>
                                </div>
                                <%}else{ %>
                            
                                <a href="#" class="nav-link dropdown-toggle border" data-toggle="dropdown">Guest 
                                <img src="img/Icon Account.png" alt="Icon_Account" width="25" height="20"></a>
                                <div class="dropdown-menu rounded-0 m-0">
                                    <a href="LgForward" class="dropdown-item">Login</a>
                                    <a href="RgForward" class="dropdown-item">Register</a>
                                </div>
                                <%} %>
                            </div>
                        </td>
                        <td>
                            <div>
                            <%if(!cs){%>
                                <a href="Login" class="btn border">
                                        <img src="img/cart.png" alt="cart" width="25" height="25">
                                    <span class="badge">0</span>
                                </a>
                               <%}else{  %>
                               
                               <a href="Cart" class="btn border">
                                        <img src="img/cart.png" alt="cart" width="25" height="25">
                                    <span class="badge"><%= count %></span>
                                </a>
                                <%} %>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid mb-5">
        <div class="row border-top px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                    <h6 class="m-0">Categorie</h6>
                    <i class="fa fa-angle-down text-dark"></i>
                </a>
                <nav class="collapse <%if(pageName.equals("Home")){%> show<%} %> navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                    <div class="navbar-nav w-100 overflow-hidden">
                        <div class="nav-item dropdown">

                        </div>
                        <a href="Shop" class="nav-item nav-link">Carte</a>
                        <a href="Shop" class="nav-item nav-link">Boxex</a>
                        <a href="Shop" class="nav-item nav-link">Sets</a>
                        <a href="Shop" class="nav-item nav-link">Rom</a>
                    </div>
                </nav>
            </div>
            <div class="col-lg-9">
                <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                    <a href="index.html" class="text-decoration-none d-block d-lg-none">
                        <img src="img/Logo Grande.png" alt="Logo Grande" width="300" height="90">
                    </a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <a href="Home" class="nav-item nav-link <%if(pageName.equals("Home")){%> active<%} %>">Home</a>
                            <a href="Shop" class="nav-item nav-link <%if(pageName.equals("Shop")){%> active<%} %>">Shop</a>
                            <a href="Detail" class="nav-item nav-link <%if(pageName.equals("Detail")){%> active<%} %>">Shop Detail</a>
                            <% if((Boolean)request.getAttribute("isAdmin")){%>
                            <a href="Admin" class="nav-item nav-link">Admin</a>
                            <%} %>
                        </div>
                        
                    </div>
                </nav>
                
                <!-- Carousel -->
                <%if(pageName.equals("Home")){%> 
                
                <div id="header-carousel" class="carousel slide" data-ride="carousel">
                
                    <div class="carousel-inner">
                    <%for(int i=0;i<5;i++){
                    	Prodotto carou=carousel.get(i);
                    	%>
                        <div class="carousel-item <%if(i==0) {%>active<%} %>" style="height: 410px;">
                            <img class="img-fluid" src=<%=carou.getSprites().get(0).getLink() %> alt="Image" style="object-fit: contain">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                <div class="p-3" style="max-width: 700px;">
                                    <h3 class="display-4 text-white font-weight-semi-bold mb-4"><%=carou.getNome() %></h3>
                                    <a href="Detail?idProdotto=<%=carou.getIdProdotto() %>" class="btn btn-primary px-3">Compra Ora</a>
                                </div>
                            </div>
                        </div>
                        <%} %>
                        
                    </div>
                    <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                        <div class="btn btn-dark" style="width: 45px; height: 45px;">
                            <span class="carousel-control-prev-icon mb-n2"></span>
                        </div>
                    </a>
                    <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                        <div class="btn btn-dark" style="width: 45px; height: 45px;">
                            <span class="carousel-control-next-icon mb-n2"></span>
                        </div>
                    </a>
                </div>
            	
            	<%} %>
            </div>
        </div>
    </div>
    
    
    <!-- Navbar End -->
