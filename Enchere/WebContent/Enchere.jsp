<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page Enchere</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<h2 align="center"> Détail Vente </h2>
	
	<table align="left">
		<tr>
			<td><img id="imageProduit" alt="Image Produit" src="D:\Photos\Leboncoin\planche_bic_rumba_1.jpg" width="250px" height="250px"> </td>
			<td>
				${article.nomArticle} <br>
				Description: ${article.description} <br>
				Catégorie: ${categorie} <br>
				Meilleur offre: ${encherePlusHauteIdUser} par ${encherePlusHauteUtilisateur.pseudo} <br>
				Mise à prix: ${article.miseAprix} <br>
				Fin de l'enchère: ${article.dateFinEncheres} <br>
				Retrait: ${article.lieuRetrait} <br>
				Vendeur: ${utilisateur.pseudo} <br>
			
			<form method="post" action="EnchereServlet" >
				
		
		
				Ma proposition:<input type="hidden" name="nomArticle"
								value="${a.nomArticle}"> <input type="number" name="propButton"/> <input type="submit"/>
			</form> </td>
		
		</tr>
	
	</table>
	
	
</body>
</html>