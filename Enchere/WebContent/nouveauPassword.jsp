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
<h1><a href="/Enchere/AccueilNonConnecteServlet">ENI-ENCHERE</a></h1>

<h2 style="color:red;">${message}</h2>
<h3>Nouveau Password</h3>
	<form action="NouveauPasswordServlet" method="post">
		 
		Email :<input type="email" name="email"placeholder="Votre email enregistré"  /><BR />
		 
		Nouveau mot de passe :<input type="password" name="nouveauMotDePasse" /><BR />
		Confirmation :<input type="password" name="nouveauMotDePasse2"  /><BR />
		 
		<input type="submit" value="Enregistrer" > 
	</form>
	
	
</body>
</html>