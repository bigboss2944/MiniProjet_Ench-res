<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mot de passe oublie</title>
</head>
<body>
 
<h1><a href="/Enchere/AccueilNonConnecteServlet">ENI-ENCHERE</a></h1>
 
 
<h2 style="color:red;">${message}</h2>
	<form method="post" action="PasswordOublieServlet">
		Entrez votre email: <input type="mail" name="email" placeholder="Votre email enregistré"  required/> <br>
		 
		<input type="submit" value="Envoyer"><br>
		 
	</form>
 
  
</html>