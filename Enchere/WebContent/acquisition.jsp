<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageAccquisition</title>
<style>
.link {
	border: none;
	outline: none;
	background: none;
	cursor: pointer;
	color: #0000EE;
	padding: 0;
	text-decoration: underline;
	font-family: inherit;
	font-size: inherit;
}

.link:active {
	color: #FF0000;
}
}
</style>
</head>

<body>
	<a href="/Enchere/ListeEncheresConnecteServlet">ENI-ENCHERE</a>
	<br>
	
	<h2>  Vous a remporté la vente </h2>
	
	${articleVenduModel.articleVendu.nomArticle} <br>
	Description: <br> <textarea  name="description" rows="6" cols="50" >${articleVenduModel.articleVendu.description}</textarea> <br>
	<br>  
						 
	Meilleure offre:  ${montantEnchere}  points 

						 
	
	

	<br> Mise à prix:  ${articleVenduModel.articleVendu.miseAprix} points <br>
	 
	<br> Retrait:  ${retrait.rue} ${retrait.code_postal} ${retrait.ville}
	<br>
	 Vendeur:  ${vendeur.pseudo}
	<br> 
	Tel:  ${vendeur.telephone}
	<br> 
	<a href="/Enchere/listeEncheresConnecte.jsp "><input type="button" value="Back"></a>





</body>
</html>