<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageCreerCompte</title>
</head>

<body>


	<jsp:include page="HeaderPagination6.jsp"></jsp:include>

	<h2 style="color: red;">${message}</h2>
	<h3>Liste des enchères</h3>

	<form action="AccueilNonConnectePagination6RechercheServlet"
		method="post">

		<input type="hidden" name="currentPage" value="1"> Filtres : <br>
		<input type="text" name="nomArticleContient"
			placeholder="Le nom de l'article contient" /><BR /> Catégorie: <select
			name="categorie">
			<option>Toutes
				<c:forEach var="categorie" items="${categorieModel.lstCategories}">
					<option value="${categorie.libelle}">${categorie.libelle}
					</option>
				</c:forEach>
		</select> <br> <input type="submit" value="Rechercher">
	</form>


	<table border="1">

		<c:forEach var="u" items="${utilisateurModel.listUtilisateur}">
			<tr>
				<td><c:forEach var="a" items="${u.listArticlesVendus}">   
				${a.nomArticle} <br>
				Prix:	${a.miseAprix} points  <br> 
				Fin de l'enchère:	
				<tags:localDate date="${a.dateFinEncheres}" pattern="dd/MM/yyyy" />
						<br>
				
				Vendeur:	${u.pseudo}       <br>
						<img src="images/Utilisateur${u.noUtilisateur}/${a.refPhoto}"
							width='80' height='80' />
					</c:forEach></td>

			</tr>
		</c:forEach>
	</table>

	<nav aria-label="Navigation for countries">
		<ul class="pagination">
			<c:if test="${currentPage != 1}">
				<li class="page-item"><a class="page-link"
					href="AccueilNonConnectePagination6RechercheServlet?currentPage=${currentPage-1}">Previous</a>
				</li>
			</c:if>

			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<li class="page-item active"><a class="page-link"> ${i} <span
								class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="AccueilNonConnectePagination6RechercheServlet?currentPage=${i}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage lt noOfPages}">
				<li class="page-item"><a class="page-link"
					href="AccueilNonConnectePagination6RechercheServlet?currentPage=${currentPage+1}">Next</a>
				</li>
			</c:if>
		</ul>
	</nav>

</body>
</html>