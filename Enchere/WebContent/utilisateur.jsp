<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageCreerCompte</title>
<link rel="stylesheet" href="Styles.css" />
</head>

<body>
<jsp:include page="Header.jsp"></jsp:include>
<h2 style="color:red;">${message}</h2>

<div class="MonProfil">
	
	<h3>Mon profil</h3>

	<form action="UtilisateurServlet"  method="post" class="wrapper">
		<table align="center">
			<tr>
				<td>Pseudo* :<input type="text" name="pseudo" required /></td>
	       		<td>Nom* :<input type="text" name="nom" required /></td>
       		</tr>
       		<tr>
				<td>Prenom* :<input type="text" name="prenom" required /></td>
	       		<td>Email* :<input type="email" name="email" required /></td>
       		</tr>
       		<tr>
				<td>Téléphone :<input type="text" name="telephone" pattern ="[0-9]*" /></td>
	       		<td>Rue* :<input type="text" name="rue" required /></td>
       		</tr>
       		<tr>
				<td>Code postal* :<input type="text" name="codePostal" required pattern ="[0-9]*" /></td>
	       		<td>Ville* :<input type="text" name="ville" required /></td>
       		</tr>
       		<tr>
				<td>Password* :<input type="password" name="motDePasse" required/></td>
	       		<td>Confirmation* :<input type="password" name="motDePasse2"  required/></td>
       		</tr>
			
			<tr>
				<td><a href="/NotesFraisMultiUsers/SignOutServlet"><input type="button" value="Annuler"></td>
	       		<td><input type="submit" value="Créer" /> </td>
       		</tr>
		</table>
	
	
	</form>
	
</div>


	
	
</body>
</html>