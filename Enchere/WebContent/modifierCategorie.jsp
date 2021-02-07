<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageModifierCategorie</title>
</head>

<body>
	<h1>
		<a href="/Enchere/ListeEncheresConnectePagination6Servlet">ENI-ENCHERE</a>
	</h1>

	<h2 style="color: red;">${message}</h2>
	<h3>Catégorie</h3>
	<form action="AdminModifierCategorieServlet" method="post">
		Libellé :<input type="text" name="libelle"
			placeholder="${categorie.libelle}" /><BR /> <input type="hidden"
			name="noCategorie" value="${categorie.noCategorie}" /> <input
			type="submit" value="Enregistrer">
	</form>

	<a href="/Enchere/AdministrationServlet"><input type="button"
		value="Back"> </a>
</body>
</html>