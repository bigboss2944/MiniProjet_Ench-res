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
	<h2 align="center"> D�tail Vente </h2>
	
	<table align="left">
		<tr>
			<td><img id="imageProduit" alt="Image Produit" src="D:\Photos\Leboncoin\planche_bic_rumba_1.jpg" width="250px" height="250px"> </td>
			<td><form method="EnchereServlet" action="POST" >
				${article.nomArticle} <br>
				Description: ${article.description} <br>
				Cat�gorie: ${categorie.libelle} <br>
				Meilleur offre: ${enchere.encherePlusHaute} par ${enchere.encherePlusHauteUtilisateur} <br>
				Mise � prix: ${article.miseAprix} <br>
				Fin de l'ench�re: ${article.dateFinEncheres} <br>
				Retrait: ${article.lieuRetrait} <br>
				Vendeur: ${utilisateur.pseudo} <br>
		
		
				Ma proposition: <input type="range"> <input type="submit"/>
			</form> </td>
		
		</tr>
	
	</table>
	
	
</body>
</html>