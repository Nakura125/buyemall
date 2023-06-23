<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.bean.Ordine" %>
<%@ page import="it.unisa.DAO.OrdineDAO" %>
<%@ page import="it.unisa.bean.Stato" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza Ordini</title>
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
    <h1>Visualizza Ordini</h1>

    <% 
    OrdineDAO orderDAO = new OrdineDAO();
    
    // Verifica se è stata inviata una richiesta POST dal form
    if (request.getMethod().equalsIgnoreCase("post")) {
        String statoParam = request.getParameter("stato");
        Stato stato = Stato.valueOf(statoParam);

        List<Ordine> orderList = orderDAO.visualizzaOrdiniPerStato(stato, "completati");
        
        if (orderList != null && !orderList.isEmpty()) {
            %>
            <div class="text-center mb-4">
            <table class="table table-bordered text-center mb-0">
        	<thead class="bg-secondary text-dark">	
                <tr>
                    <th>ID Ordine</th>
                    <th>Data</th>
                    <th>Stato</th>
                    <th>Utente</th>
                </tr>
           </thead>
           <tbody>
                <%
                for (Ordine ordine : orderList) {
                %>
                <tr>
                    <td><%= ordine.getIdOrdine() %></td>
                    <td><%= ordine.getData() %></td>
                    <td><%= ordine.getStato() %></td>
                    <td><%= ordine.getU() %></td>
                </tr>
                <%
                }
                %>
            </tbody>
            </table>
           
            <%
        } else {
            %>
            <p>Nessun ordine disponibile per lo stato selezionato.</p>
            <%
        }
    }
    %>
    </div>
<div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Visualizza Ordini Per Stato</span></h2>
    <form action="Admin" method="post">
        <input class="form-control" type="hidden" name="action" value="visualizzaOrdiniPerStato">
        <label for="stato">Stato:</label>
        <select name="stato" id="stato">
            <option value="completato" <% if ("completato".equals(request.getParameter("stato"))) out.print("selected"); %>>completato</option>
            <option value="attesa" <% if ("attesa".equals(request.getParameter("stato"))) out.print("selected"); %>>attesa</option>
            <option value="rimborsato" <% if ("rimborsato".equals(request.getParameter("stato"))) out.print("selected"); %>>rimborsato</option>
            <option value="annullato" <% if ("annullato".equals(request.getParameter("stato"))) out.print("selected"); %>>annullato</option>
        </select>
        <input class="btn btn-primary py-2 px-4" type="submit" value="Visualizza Ordini">
    </form>
</div>

    <a href="Admin">Torna alla Dashboard Admin</a>
</body>
</html>
