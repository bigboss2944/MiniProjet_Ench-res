<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageDetailMaVenteFinEnchere</title>
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
	
	<h2>  ${encherisseur.pseudo}  a remporté l'enchère </h2>
	
	${articleVenduModel.articleVendu.nomArticle} <br>
	Description: <br> <textarea  name="description" rows="6" cols="50" >${articleVenduModel.articleVendu.description}</textarea> <br>
	<br>  
						<form action="ProfilServlet">
							Meilleure offre:  ${montantEnchere}  points par  <input type="hidden" name="noVendeur"
								value="${encherisseur.noUtilisateur}"> <input type="submit"
								value="${encherisseur.pseudo}" class="link">

						</form>
	
	

	<br> Mise à prix:  ${articleVenduModel.articleVendu.miseAprix} points <br>
	Fin de l'enchère: <tags:localDate date="${articleVenduModel.articleVendu.dateFinEncheres}" pattern="dd/MM/yyyy"/>
	<br> Retrait:  ${user.rue} ${user.codePostal} ${user.ville}
	<br>
	 Vendeur:  ${user.pseudo}
	<br> 
	
	<a href=" "><input type="button" value="Retrait effectué"></a>





</body>
</html>