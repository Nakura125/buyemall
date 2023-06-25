<%@page import="java.util.LinkedList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="it.unisa.bean.Stato"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.bean.Prodotto" %>
<%@ page import="it.unisa.DAO.ProdottoDAO" %>
<%@ page import="it.unisa.bean.Ordine" %>
<%@ page import="it.unisa.DAO.OrdineDAO" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%-- <%@ page import="java.sql.Date" %> --%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin</title>
    
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
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<a href="Home">Torna alla Home</a>
	<div class="container-fluid mb-5">
	<div class="text-center mb-4">
    <h1 class="section-title px-5"><span class="px-2">welcome Admin!</span></h1>
    
    <form action="Admin?action=logout" method="post">
        <button class="btn btn-primary py-2 px-4" type="submit">Logout</button>
    </form>
    </div>

<!-- aggiungi prodotto -->
<div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Add Product</span></h2>
    <form action="Admin?action=addProduct" method="post">
        <input class="form-control" type="text" name="tipo" placeholder="Tipo" required>
        <input class="form-control" type="text" name="nome" placeholder="Nome" required>
        <input class="form-control" type="text" name="descrizione" placeholder="Descrizione" required>
        <input class="form-control" type="text" name="prezzo" placeholder="Prezzo" required>
        <input class="form-control" type="text" name="quantita" placeholder="Quantità" required>
        <input class="form-control" type="text" name="generazione" placeholder="Generazione" required>
        <input class="form-control" type="text" name="nazionalita" placeholder="Nazionalità" required>
        <input class="form-control" type="text" name="link" placeholder="Link" required>
        <input class="form-control"type="text" name="link_small" placeholder="Small Link" required>
        <button class="btn btn-primary py-2 px-4" type="submit">Add Product</button>
    </form>
    </div>
    
    <%-- Mostra un messaggio se il prodotto è stato aggiunto con successo --%>
<%
    
    boolean AddSuccess = request.getAttribute("AddSuccess") != null ? (boolean) request.getAttribute("AddSuccess") : false;
    if (AddSuccess) {
%>
    <div>Il prodotto è stato aggiunto con successo.</div>
<%
    }
%>


<!-- modifica prodotto -->
<div class="text-center mb-4">
	<h2 class="section-title px-5"><span class="px-2">Modifica Prodotto</span></h2>
	<form action="Admin?action=modificaProdotto" method="post">
    <input class="form-control" type="hidden" name="action" value="modificaProdotto">
    <label for="idProdotto">ID Prodotto:</label>
    <input class="form-control" type="text" name="idProdotto" id="idProdotto" required><br>
    <label for="nuovoNome">Nuovo Nome:</label>
    <input class="form-control" type="text" name="nuovoNome" id="nuovoNome" required><br>
    <label for="nuovaQuantita">Nuova Quantità:</label>
    <input class="form-control" type="text" name="nuovaQuantita" id="nuovaQuantita" required><br>
    <label for="nuovaDescrizione">Nuova Descrizione:</label>
    <input class="form-control"type="text" name="nuovaDescrizione" id="nuovaDescrizione" required><br>
    <label for="nuovoPrezzo">Nuovo Prezzo:</label>
    <input class="form-control" type="text" name="nuovoPrezzo" id="nuovoPrezzo" required><br>
    <input class="btn btn-primary py-2 px-4" type="submit" value="Modifica Prodotto">
</form>
</div>

<%
    // Verifica l'attributo per mostrare il messaggio di modifica avvenuta con successo
    boolean modificaSuccess = request.getAttribute("modificaSuccess") != null ? (boolean) request.getAttribute("modificaSuccess") : false;
    if (modificaSuccess) {
%>
    <div>La modifica è avvenuta con successo.</div>
<%
    }
    // Verifica l'attributo per mostrare il messaggio di prodotto non trovato
    boolean prodottoNonTrovato = request.getAttribute("prodottoNonTrovato") != null ? (boolean) request.getAttribute("prodottoNonTrovato") : false;
    if (prodottoNonTrovato) {
%>
    <div>Il prodotto non è stato trovato.</div>
<%
    }
%>

<!-- elimina prodotto -->
<div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Delete Product</span></h2>
    <form action="Admin?action=deleteProduct" method="post">
        <input class="form-control" type="hidden" name="action" value="deleteProduct">
        
        <label for="idprodotti">Product ID:</label>
        <input class="form-control" type="text" name="idprodotti" id="idprodotti" required><br>
        
        <input class="btn btn-primary py-2 px-4" type="submit" value="Delete Product">
    </form>
</div>

<%
    // Verifica l'attributo per mostrare il messaggio di modifica avvenuta con successo
    boolean deleteSuccess = request.getAttribute("deleteSuccess") != null ? (boolean) request.getAttribute("deleteSuccess") : false;
    if (deleteSuccess) {
%>
    <div>L'eliminazione è avvenuta con successo.</div>
<%
    }
    // Verifica l'attributo per mostrare il messaggio di prodotto non trovato
    boolean productNonTrovato = request.getAttribute("prodottoNonTrovato") != null ? (boolean) request.getAttribute("prodottoNonTrovato") : false;
    if (productNonTrovato) {
%>
    <div>Il prodotto non è stato trovato.</div>
<%
    }
%>

<!--     Ricerca prodotto -->
<div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Search Product</span></h2>
    <form action="Admin?action=searchProduct" method="post">
        <input class="form-control" type="hidden" name="action" value="searchProduct">
        <label for="idprodotti">Product ID:</label>
        <input class="form-control" type="text" name="idprodotti" id="idprodotti" required><br>
        <input class="btn btn-primary py-2 px-4"  type="submit" value="Search Product">
    </form>
    </div>   
    <%-- Mostra un messaggio se il prodotto non è stato trovato nel database --%>
    <% if (request.getParameter("ProductnotFound") != null) { %>
        <div>Il prodotto cercato non è stato trovato nel database.</div>
    <% } %>






<!--   Recupera tutti i prodotti dal database -->



    <h2>Elenco Prodotti</h2>
    <div class="text-center mb-4">
    <table class="table table-bordered text-center mb-0">
        <thead class="bg-secondary text-dark">
        <tr>
            <th>ID Prodotto</th>
            <th>Nome</th>
            <th>Prezzo</th>
            <th>Descrizione</th>
            <th>Quantità</th>
            <th>Tipo</th>
        </tr>
        </thead>
        <tbody id="Selettore">
<%--         <% for (Prodotto product : productList) { %> --%>
<!--             <tr> -->
<%--                 <td><%= product.getIdProdotto() %></td> --%>
<%--                 <td><%= product.getTipo() %></td> --%>
<%--                 <td><%= product.getNome() %></td> --%>
<%--                 <td><%= product.getDescrizione() %></td> --%>
<%--                 <td><%= product.getPrezzo() %></td> --%>
<%--                 <td><%= product.getQuantita() %></td> --%>
<!--             </tr> -->
<%--         <% } %> --%>
        </tbody>
    </table>

</div>






<!-- visualizza ordini per stato -->
<div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Visualizza Ordini Per Stato</span></h2>
    
    <form action="Admin?action=visualizzaOrdiniPerStato" method="post">
        <input class="form-control" type="hidden" name="action" value="visualizzaOrdiniPerStato">
        <label for="stato">Stato:</label>
        <select name="stato" id="stato">
            <option value="completato">completato</option>
            <option value="attesa">attesa</option>
            <option value="rimborsato">rimborsato</option>
            <option value="annullato">annullato</option>
        </select>
        <input class="btn btn-primary py-2 px-4"type="submit" value="Visualizza Ordini">
    </form>
</div>


<!-- Elimina Ordine -->
<div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Delete Ordine</span></h2>
    <form action="Admin?action=deleteOrdine" method="post" >
        <input class="form-control" type="hidden" name="action" value="deleteOrdine">
        <label for="idOrdine">Ordine ID:</label>
        <input class="form-control" type="text" name="idOrdine" id="idOrdine" required><br>
        <input class="btn btn-primary py-2 px-4" type="submit" value="Delete Ordine">
    </form>
</div>

    <%-- Mostra un messaggio se l'ordine è stato eliminato con successo --%>
<%
    // Verifica l'attributo per mostrare il messaggio di modifica avvenuta con successo
    boolean deleteOrderSuccess = request.getAttribute("deleteOrderSuccess") != null ? (boolean) request.getAttribute("deleteOrderSuccess") : false;
    if (deleteOrderSuccess) {
%>
    <div>L'eliminazione è avvenuta con successo.</div>
<%
    }
    // Verifica l'attributo per mostrare il messaggio di prodotto non trovato
    boolean ordineNonTrovato = request.getAttribute("ordineNonTrovato") != null ? (boolean) request.getAttribute("ordineNonTrovato") : false;
    if (productNonTrovato) {
%>
    <div>Il prodotto non è stato trovato.</div>
<%
    }
%>
    
    
<!-- Conferma Ordini Completati -->
<div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Conferma Ordini Completati</span></h2>
    <form action="Admin?action=confermaOrdiniCompletati" method="post">
        <input class="form-control" type="hidden" name="action" value="confermaOrdiniCompletati">
        <label for="idOrdine">Ordine ID:</label>
        <input class="form-control" type="text" name="idOrdine" id="idOrdine" required><br>
        <input class="btn btn-primary py-2 px-4" type="submit" value="Conferma Ordini Completati">
    </form>
</div>

<%-- Mostra un messaggio se l'ordine è stato confermato con successo --%>
<%
    boolean confermaSuccess = request.getAttribute("confermaSuccess") != null ? (boolean) request.getAttribute("confermaSuccess") : false;
    if (confermaSuccess) {
%>
    <div>L'ordine è stato confermato con successo</div>
<%
    }
    //Ordine non trovato oppure non in stato di attesa
    boolean confermaErrors = request.getAttribute("confermaErrors") != null ? (boolean) request.getAttribute("confermaErrors") : false;
    if (confermaErrors) {
%>
    <div>L'ordine non è stato trovato oppure non in stato di attesa.</div>
<%
    }
%>
    </div>
    
<!--     visualizzaOrdiniFiltrati -->
	<div class="container-fluid pt-5">
		<form action="Admin?action=visualizzaOrdiniFiltrati" method="post">
			<div class="control-group">
				<input type="date" name="data1" class="form-control" id="datascad"
					placeholder="Data di Scadenza" required="required"
					data-validation-required-message="Inserisci la data di scadenza" />
				<p class="help-block text-danger"></p>
			</div>

			<div class="control-group">
				<input type="date" name="data2" class="form-control" id="datascad"
					placeholder="Data di Scadenza" required="required"
					data-validation-required-message="Inserisci la data di scadenza" />
				<p class="help-block text-danger"></p>
			</div>
			<input class="btn btn-primary py-2 px-4" type="submit"
				value="visualizza Ordini Filtrati">
		</form>
	</div>



<% 	 
		List<Ordine> orco=new LinkedList<>();
		
 		try{
			
 			orco=(List<Ordine>) request.getAttribute("dateOrdine");
			
 			if(orco==null)
 				orco=new LinkedList<>();
 		}catch( ClassCastException  e){
 			orco=new LinkedList<>();
 			e.printStackTrace();
 		}
		
		
	 %>
	<h2>Elenco Ordini</h2>
	<div class="text-center mb-4">
		<table class="table table-bordered text-center mb-0">
			<thead class="bg-secondary text-dark">
				<tr>
					<th>Idordine</th>
					<th>Data</th>
					<th>Stato</th>
					<th>Prezzo</th>
					
				</tr>
			</thead>

			<tbody class="align-middle">
				<%for(int i=0;i<orco.size();i++) {%>
					<tr>
					<td><%= orco.get(i).getIdOrdine()%></td>
					<td class="align-middle"><%= orco.get(i).getData().toString() %></td>
					<td class="align-middle"><%= orco.get(i).getStato().toString()%></td>
					<td class="align-middle"><%= orco.get(i).getPrezzo()%></td>
					
				</tr>
				<%} %>
			</tbody>
		</table>

	</div>


	
    <script src="js/ajax-json.js" ></script>
    <script src="js/AdminPaging.js"></script>
    
</body>
</html>

