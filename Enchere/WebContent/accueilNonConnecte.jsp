<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageCreerCompte</title>
</head>

<body>


<jsp:include page="Header.jsp"></jsp:include>

<h2 style="color:red;">${message}</h2>
<h3>Liste des enchères</h3>

	<form action="AccueilNonConnecteServlet" method="post">
		Filtres : <br>
		<input type="text" name="nomArticleContient" placeholder="Le nom de l'article contient" /><BR />
		Catégorie: 
		<select name ="categorie">
			<option > Toutes
			<c:forEach var="categorie" items="${categorieModel.lstCategories}">
				<option value="${categorie.libelle}">  ${categorie.libelle} </option>
			</c:forEach>
		</select> <br>
		<input type="submit" value="Rechercher" > 
	</form>
	
	
	<table border="1">
	 
		<c:forEach var="u" items="${utilisateurModel.listUtilisateur}">
			<tr>
				<td> <c:forEach var="a" items="${u.listArticlesVendus}">   
				${a.nomArticle} <br>
				Prix:	${a.miseAprix} points  <br> 
				Fin de l'enchère:	
				<tags:localDate date="${a.dateFinEncheres}" pattern="dd/MM/yyyy"/>
				 <br>
				Vendeur:	${u.pseudo}       <br>
				</c:forEach>
				</td>
				 
			</tr>
		</c:forEach>
	</table> 
 
      
	
</body>
</html>