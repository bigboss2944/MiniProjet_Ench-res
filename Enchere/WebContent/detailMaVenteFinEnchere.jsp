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
	
		 
	       	 <c:choose>
    <c:when test="${encherisseur.pseudo!=null}">
    <h2>  ${encherisseur.pseudo}  a remporté l'enchère </h2>
        <br />
    </c:when>    
    <c:otherwise>
    <h2> Personne n'a remporté l'enchère </h2>
        <br />
    </c:otherwise>
</c:choose>
	
	
	${articleVenduModel.articleVendu.nomArticle} <br>
	Description: <br> <textarea  name="description" rows="6" cols="50" >${articleVenduModel.articleVendu.description}</textarea> <br>
	<br>  
		 
	       	 <c:choose>
    <c:when test="${encherisseur.pseudo!=null}">
    <form action="ProfilServlet">
							Meilleure offre:  ${montantEnchere}  points par  <input type="hidden" name="noVendeur"
								value="${encherisseur.noUtilisateur}"> <input type="submit"
								value="${encherisseur.pseudo}" class="link">

						</form>
        <br />
    </c:when>    
    <c:otherwise>
       Meilleure offre:  ${montantEnchere}  point par  personne
        <br />
    </c:otherwise>
</c:choose>
	
						
	
	

	<br> Mise à prix:  ${articleVenduModel.articleVendu.miseAprix} points <br>
	Fin de l'enchère: <tags:localDate date="${articleVenduModel.articleVendu.dateFinEncheres}" pattern="dd/MM/yyyy"/>
	<br> Retrait:  ${retrait.rue} ${retrait.code_postal} ${retrait.ville}
	<br>
	 Vendeur:  ${user.pseudo}
	<br> 
	<img src="images/Utilisateur${user.noUtilisateur}/${articleVenduModel.articleVendu.refPhoto}" width='80' height='80'/>
	 <br>
		 
	       	 <c:choose>
    <c:when test="${encherisseur.pseudo!=null}">
     <a href=" "><input type="button" value="Retrait effectué"></a>
        <br />
    </c:when>    
    <c:otherwise>
     <a href=" "><input type="button" value="Vente terminée"></a>
        <br />
    </c:otherwise>
</c:choose>
	
<h2>Liste des enchérisseurs de cet article</h2>

<c:if test="${encherisseur.pseudo!=null}">
		 <c:forEach var="utiArt" items="${utilisateurModel.listUtilisateur}"> 
					  <c:forEach var="encArt" items="${utiArt.listEncheres}"> 
					  ${encArt.montant_enchere} points par  
<a href="<%=request.getContextPath()+"/ProfilServlet"%>?noVendeur=${utiArt.noUtilisateur}"> ${utiArt.pseudo}</a>	<br>
					</c:forEach>
		</c:forEach>
</c:if>
<c:if test="${encherisseur.pseudo ==null}">
		  Personne enchérit pour cet article
</c:if>



</body>
</html>