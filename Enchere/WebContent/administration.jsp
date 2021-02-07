<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administration</title>
</head>
<body>
	<h1>
		<a href="/Enchere/ListeEncheresConnectePagination6Servlet">ENI-ENCHERE</a>
	</h1>

	<h2 style="color: red;">${message}</h2>
	<h2>Liste des utilisateurs</h2>



	<table border="1">
		<tr>
			<th>Pseudo</th>
			<th>Administrateur</th>
			<th>Etat compte</th>
			<th></th>
			<th></th>

		</tr>
		<c:forEach var="uti" items="${utilisateurModel.listUtilisateur}">
			<tr>
				<td><a
					href="<%=request.getContextPath()+"/ProfilServlet"%>?noVendeur=${uti.noUtilisateur}">${uti.pseudo}</a>
				</td>
				<td>${uti.administrateur}</td>
				<td>${uti.etatCompte}</td>
				<td><c:if test="${!uti.administrateur}">
						<a
							href="<%=request.getContextPath()+"/AdminSupprimerCompteServlet"%>?noVendeur=${uti.noUtilisateur}"><input
							type="button" value="Supprimer"></a>
					</c:if> <c:if test="${uti.administrateur}">
						(=.=)
					</c:if></td>
				<td><c:if test="${!uti.administrateur}">
						<c:if test="${uti.getEtatCompte().equals('A')}">
							<a
								href="<%=request.getContextPath()+"/AdminDesactiverCompteServlet"%>?noVendeur=${uti.noUtilisateur}"><input
								type="button" value="Désactiver"></a>
						</c:if>
						<c:if test="${!uti.getEtatCompte().equals('A')}">
							<a
								href="<%=request.getContextPath()+"/AdminActiverCompteServlet"%>?noVendeur=${uti.noUtilisateur}"><input
								type="button" value="Activer"></a>
						</c:if>
					</c:if> <c:if test="${uti.administrateur}">
							(=.=)
						</c:if></td>

			</tr>
		</c:forEach>
	</table>

	<h2>Liste des catégories</h2>

	<table border="1">
		<tr>
			<th>Catégories</th>


			<th></th>
			<th></th>

		</tr>
		<c:forEach var="categorie" items="${categorieModel.lstCategories}">
			<tr>
				<td>${categorie.libelle}</td>
				<td><a
					href="<%=request.getContextPath()+"/AdminSupprimerCategorieServlet"%>?noCategorie=${categorie.noCategorie}"><input
						type="button" value="Supprimer"></a></td>
				<td><a
					href="<%=request.getContextPath()+"/AdminModifierCategorieServlet"%>?noCategorie=${categorie.noCategorie}"><input
						type="button" value="Modifier"></a></td>

			</tr>


		</c:forEach>
	</table>
	<h3>Ajouter une catégorie</h3>
	<form action="AdminAjouterCategorieServlet" method="post">
		Catégorie :<input type="text" name="libelleCat" /> <input
			type="submit" value="Ajouter" />
	</form>


</body>
</html>