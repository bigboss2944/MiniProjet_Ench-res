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
	<br>
	<h2>Pseudo: ${vendeurModel.utilisateur.pseudo}</h2>
	<br> Nom: ${vendeurModel.utilisateur.nom}
	<br> Prénom: ${vendeurModel.utilisateur.prenom}
	<br> Email: ${vendeurModel.utilisateur.email}
	<br> Teléphone: ${vendeurModel.utilisateur.telephone}
	<br> Rue: ${vendeurModel.utilisateur.rue}
	<br> Code postal: ${vendeurModel.utilisateur.codePostal}
	<br> Ville: ${vendeurModel.utilisateur.ville}
	<br>





</body>
</html>