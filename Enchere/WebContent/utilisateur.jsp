<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageCreerCompte</title>
</head>

<body>
<h1>ENI-Enchère</h1>
<h2 style="color:red;">${message}</h2>
<h3>Mon profil</h3>
	<form action="UtilisateurServlet" method="post">
		Pseudo* :<input type="text" name="pseudo" required /><BR />
		Nom* :<input type="text" name="nom" required /><BR />
		Prenom* :<input type="text" name="prenom" required /><BR />
		Email* :<input type="email" name="email" required /><BR />
		Téléphone :<input type="text" name="telephone" pattern ="[0-9]*" /><BR />
		Rue* :<input type="text" name="rue" required /><BR />
		Code postal* :<input type="text" name="codePostal" required pattern ="[0-9]*" /><BR />
		Ville* :<input type="text" name="ville" required /><BR />
		Password* :<input type="password" name="motDePasse" required/><BR />
		Confirmation* :<input type="password" name="motDePasse2"  required/><BR />
		<input type="submit" value="Créer" > 
	</form>
	<a href="/NotesFraisMultiUsers/SignOutServlet"><input type="button" value="Annuler"></a>
	
</body>
</html>