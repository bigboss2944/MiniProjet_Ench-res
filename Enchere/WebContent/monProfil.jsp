<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageMonProfil</title>
</head>

<body>
	<h1>
		<a href="/Enchere/ListeEncheresConnectePagination6Servlet">ENI-ENCHERE</a>
	</h1>
	<br> Pseudo: ${user.pseudo}
	<br> Nom: ${user.nom}
	<br> Prénom: ${user.prenom}
	<br> Email: ${user.email}
	<br> Teléphone: ${user.telephone}
	<br> Rue: ${user.rue}
	<br> Code postal: ${user.codePostal}
	<br> Ville: ${user.ville}
	<br>

	<a href="/Enchere/ModifierProfilServlet"><input type="button"
		value="Modifier"></a>



</body>
</html>