<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<title>PageCreerCompte</title>

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<link rel="stylesheet" href="style.css">


</head>

<body>

	<nav>
		<div>
			<h1>
				<a href="/Enchere/ListeEncheresConnectePagination6Servlet">
					ENI-ENCHERES</a>
			</h1>
		</div>
		<div class="nav login">
			<a href="/Enchere/UtilisateurServlet"><button>S'inscrire
				</button></a> <a href="/Enchere/LoginServlet"><button>Se connecter</button></a>
		</div>
	</nav>


	<div>
		<h2>Liste des encheres</h2>
		<h3 style="color: red;">${message}</h3>
	</div>

	<form method="post"
		action="AccueilNonConnectePagination6RechercheServlet">
		<input type="hidden" name="currentPage" value="1"> Filtres : <input
			type="text" name="nomArticleContient"
			placeholder="Le nom de l'article contient" /> Catégorie: <select
			name="categorie">
			<option>Toutes
				<c:forEach var="categorie" items="${categorieModel.lstCategories}">
					<option value="${categorie.libelle}">${categorie.libelle}
					</option>
				</c:forEach>
		</select> <input type="submit" value="Rechercher">


	</form>



	<c:forEach var="u" items="${utilisateurModel.listUtilisateur}">

		<c:forEach var="a" items="${u.listArticlesVendus}">


			<img src="images/Utilisateur${u.noUtilisateur}/${a.refPhoto}" />
			<br>
				
				
						
							 ${a.nomArticle} <br>
							
					
					Prix :${a.miseAprix} points <br>
						Fin de l'enchère :
							<tags:localDate date="${a.dateFinEncheres}" pattern="dd/MM/yyyy" />
			<br>
						
							Vendeur : ${u.pseudo}  <br>


		</c:forEach>

	</c:forEach>

















	<nav aria-label="Navigation for countries">
		<ul class="pagination">
			<c:if test="${currentPage != 1}">
				<li class="page-item"><a class="page-link"
					href="AccueilNonConnectePagination6Servlet?currentPage=${currentPage-1}">Previous</a>
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
							href="AccueilNonConnectePagination6Servlet?currentPage=${i}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage lt noOfPages}">
				<li class="page-item"><a class="page-link"
					href="AccueilNonConnectePagination6Servlet?currentPage=${currentPage+1}">Next</a>
				</li>
			</c:if>
		</ul>
	</nav>

</body>
</html>