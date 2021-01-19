<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageListesEncheresConnecte</title>
</head>

<body>

<script type="text/javascript">

function changeThis(sender) { 
	  if(document.getElementById('enchereRadioButton').checked){
	    document.getElementById("enchereOuvertesCheckBox").removeAttribute('disabled');
	    document.getElementById("mesEncheresCheckBox").removeAttribute('disabled');
	    document.getElementById("mesEncheresRemporteesCheckBox").removeAttribute('disabled');
	    document.getElementById("mesVentesECCheckBox").disabled = true;
	    document.getElementById("mesVentesECCheckBox").checked = false;
	    document.getElementById("ventesNonDebuteesCheckBox").disabled = true;
	    document.getElementById("ventesNonDebuteesCheckBox").checked = false;
	    document.getElementById("ventesTermineesCheckBox").disabled = true;
	    document.getElementById("ventesTermineesCheckBox").checked = false;
	  }
	  else if(document.getElementById('venteRadioButton').checked){
		document.getElementById("enchereOuvertesCheckBox").disabled=true;
		document.getElementById("enchereOuvertesCheckBox").checked=false;
		document.getElementById("mesEncheresCheckBox").disabled=true;
		document.getElementById("mesEncheresCheckBox").checked=false;
		document.getElementById("mesEncheresRemporteesCheckBox").disabled=true;
		document.getElementById("mesEncheresRemporteesCheckBox").checked=false;
		document.getElementById("mesVentesECCheckBox").removeAttribute('disabled');
	    document.getElementById("ventesNonDebuteesCheckBox").removeAttribute('disabled');
	    document.getElementById("ventesTermineesCheckBox").removeAttribute('disabled');
	    
	  }
	}
</script>


<h1>ENI-Enchère</h1>
<h3>Bonjour  ${user.pseudo}</h3>
<nav>
<a href=" ">Enchères</a>
<a href=" ">Vendre un article</a>
<a href="/Enchere/monProfil.jsp">Mon profil</a>
<a href="/Enchere/LogoutServlet">Déconnexion</a> 
</nav>
<h2 style="color:red;">${message}</h2>
<h3>Liste des enchères</h3>

	<form action="ListeEncheresConnecteServlet" method="post">
		Filtres : <br>
		<input type="text" name="nomArticleContient" placeholder="Le nom de l'article contient" /><BR />
		Catégorie: 
		<select name ="categorie">
			<option > Toutes
			<c:forEach var="categorie" items="${categorieModel.lstCategories}">
				<option value="${categorie.libelle}">  ${categorie.libelle} </option>
			</c:forEach>
		</select> <br>
		<input type="radio" name="achatVente" id="enchereRadioButton" onclick="changeThis(this)"> Achats
			<input type="checkbox" name="encheresOuvertes" id="enchereOuvertesCheckBox" > enchères ouvertes
			<input type="checkbox" name="mesEncheres" id="mesEncheresCheckBox"> mes enchères
			<input type="checkbox" name="mesEncheresRemportees" id="mesEncheresRemporteesCheckBox"> mes enchères remportées <br>
		<input type="radio" name="achatVente" id="venteRadioButton" onclick="changeThis(this)"> Mes ventes
			<input type="checkbox" name="mesVentesEC" id="mesVentesECCheckBox"> mes ventes en cours
			<input type="checkbox" name="ventesNonDebutees" id="ventesNonDebuteesCheckBox"> ventes non débutées
			<input type="checkbox" name="ventesTerminees" id="ventesTermineesCheckBox"> ventes terminées <br>
			 
		<input type="submit" value="Rechercher" > 
	</form>
	
	
	<table border="1">
	 
		<c:forEach var="u" items="${utilisateurModel.listUtilisateur}">
			<tr>
				<td> <c:forEach var="a" items="${u.listArticlesVendus}">   
				<a href="/Enchere/EnchereServlet">${a.nomArticle}</a> <br>
				Prix:	${a.miseAprix} points  <br> 
				Fin de l'enchère:	
				<tags:localDate date="${a.dateFinEncheres}" pattern="dd/MM/yyyy"/>
				 <br>
				Vendeur:	${u.pseudo}       <br>
				</c:forEach>
				</td>
				 
			</tr>
		</c:forEach>
	</table> 
 
      
	
</body>
</html>