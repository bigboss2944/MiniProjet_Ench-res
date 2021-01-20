<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageDetailMaVenteEnCours</title>
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
	
	
	
	${articleVenduModel.articleVendu.nomArticle} <br>
	Description: <br> <textarea  name="description" rows="6" cols="50" >${articleVenduModel.articleVendu.description}</textarea> <br>
	<br>  
						
							 <c:choose>
							    <c:when test="${encherisseur.pseudo!=null}">
							    <form action="ProfilServlet">
							Meilleure offre:  ${montantEnchere}  points par
							       <input type="hidden" name="noVendeur"
								value="${encherisseur.noUtilisateur}"> <input type="submit"
								value="${encherisseur.pseudo}" class="link">
							        <br />
							        </form>
							    </c:when>    
							    <c:otherwise>
							       Meilleure offre:  ${montantEnchere}  point par personne
							        <br />
							    </c:otherwise>
							</c:choose>
							
							  

						
	
	

	<br> Mise à prix:  ${articleVenduModel.articleVendu.miseAprix} points <br>
	Fin de l'enchère: <tags:localDate date="${articleVenduModel.articleVendu.dateFinEncheres}" pattern="dd/MM/yyyy"/>
	<br> Retrait:  ${user.rue} ${user.codePostal} ${user.ville}
	<br>
	 Vendeur:  ${user.pseudo} <br>
	
	<a href=" "><input type="button" value="Vente en cours"></a>





</body>
</html>