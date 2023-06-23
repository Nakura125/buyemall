<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dettagli del prodotto</title>
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

    <h1>Dettagli del prodotto</h1>
    <div class="text-center mb-4">
	<table class="table table-bordered text-center mb-0">
        <thead class="bg-secondary text-dark">
        <tr>
        	<th>ID</th>
            <th>Tipo</th>
            <th>Quantità</th>
            <th>Nome</th>
            <th>Descrizione</th>
            <th>Prezzo</th>
            <th>Generazione</th>
            <th>Nazionalità</th>
        </tr>
        </thead>
        </tbody>
        
        <tr>
        	<td>${product.idProdotto}</td>
            <td>${product.tipo}</td>
            <td>${product.quantita}</td>
            <td>${product.nome}</td>
            <td>${product.descrizione}</td>
            <td>${product.prezzo}</td>
            <td>${product.generazione}</td>
            <td>${product.nazionalita}</td>
        </tr>
        </tbody>
    </table>
    </div>
     <a href="Admin">Torna alla Dashboard Admin</a>
</body>
</html>




