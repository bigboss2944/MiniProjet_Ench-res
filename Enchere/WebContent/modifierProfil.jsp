<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageModifierProfil</title>
</head>

<body>
	<h1>
		<a href="/Enchere/ListeEncheresConnectePagination6Servlet">ENI-ENCHERE</a>
	</h1>

	<h2 style="color: red;">${message}</h2>
	<h3>Mon profil</h3>
	<form action="ModifierProfilServlet" method="post">
		Pseudo :<input type="text" name="pseudo" placeholder="${user.pseudo}" /><BR />
		Nom :<input type="text" name="nom" placeholder="${user.nom}" /><BR />
		Prenom :<input type="text" name="prenom" placeholder="${user.prenom}" /><BR />
		Email :<input type="email" name="email" placeholder="${user.email}" /><BR />
		Téléphone :<input type="text" name="telephone" pattern="[0-9]*"
			placeholder="${user.telephone}" /><BR /> Rue :<input type="text"
			name="rue" placeholder="${user.rue}" /><BR /> Code postal :<input
			type="text" name="codePostal" pattern="[0-9]*"
			placeholder="${user.codePostal}" /><BR /> Ville :<input type="text"
			name="ville" placeholder="${user.ville}" /><BR /> Mot de passe
		actuel :<input type="password" name="motDePasse" valeur="" required /><BR />
		Nouveau mot de passe :<input type="password" name="nouveauMotDePasse" /><BR />
		Confirmation :<input type="password" name="nouveauMotDePasse2" /><BR />
		Crédit: ${user.credit} <br> <input type="submit"
			value="Enregistrer">
	</form>
	<a href="/Enchere/SuppressionCompteServlet"><input type="button"
		value="Supprimer mon compte"></a>

</body>
</html>