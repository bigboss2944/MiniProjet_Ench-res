<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageListesEncheresConnecte</title>
 
<style>
.link {
	border: none;
	outline: none;
	background: none;
	cursor: pointer;
	color: #0000EE;
	padding: 0;
	text-decoration: underline;
	font-family: inherit;
	font-size: inherit;
}

.link:active {
	color: #FF0000;
}
}
</style>
<script type="text/javascript">
	function submitform() {
		document.forms["myform"].submit();
	}
	

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
 
	
    </script> 

</head>

<body>
	<h1><a href="/Enchere/ListeEncheresConnecteServlet">ENI-ENCHERE</a></h1>
	<h3>Bonjour ${user.pseudo}</h3>
	<nav>
		 <a href="">Enchères</a>
		 <a href="/Enchere/VendreUnArticlePhotoServlet">Vendre un article</a> 
		 <a href="/Enchere/monProfil.jsp">Mon profil</a>
		 <a href="/Enchere/LogoutServlet">Déconnexion</a>
	</nav>
	<h2 style="color: red;">${message}</h2>
	<h3>Liste des enchères</h3>

	<form action="ListeEncheresConnecteServlet" method="post">
		Filtres : <br> <input type="text" name="nomArticleContient"	placeholder="Le nom de l'article contient" /><BR /> 
		Catégorie: 
		<select	name="categorie">
			<option>Toutes
				<c:forEach var="categorie" items="${categorieModel.lstCategories}">
					<option value="${categorie.libelle}">${categorie.libelle}
					</option>
				</c:forEach>
		</select> <br> 
		<input type="radio" name="achatVente" value ="achat" id="enchereRadioButton" onclick="changeThis(this)"> Achats
			<input type="checkbox" name="encheresOuvertes" value="encheresOuvertes" id="enchereOuvertesCheckBox" disabled> enchères ouvertes
			<input type="checkbox" name="mesEncheres" value="mesEncheres" id="mesEncheresCheckBox" disabled> mes enchères
			<input type="checkbox" name="mesEncheresRemportees" value="mesEncheresRemportees" id="mesEncheresRemporteesCheckBox" disabled> mes enchères remportées <br>
		<input type="radio" name="achatVente" value ="vente"id="venteRadioButton" onclick="changeThis(this)"> Mes ventes
			<input type="checkbox" name="mesVentesEC"  value="mesVentesEC"id="mesVentesECCheckBox" disabled> mes ventes en cours
			<input type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees" id="ventesNonDebuteesCheckBox" disabled> ventes non débutées
			<input type="checkbox" name="ventesTerminees" value="ventesTerminees" id="ventesTermineesCheckBox" disabled> ventes terminées <br>
			 
			 

			 
		  <input type="submit" value="Rechercher">
	</form>
 
 
 
  

	<table border="1">

		<c:forEach var="u" items="${utilisateurModel.listUtilisateur}">
			<tr>
				<td><c:forEach var="a" items="${u.listArticlesVendus}"> 
					<form action="GestionAchatVente">
							<input type="hidden" name="noArticleVendu" value="${a.noArticle}"> 
							<input type="submit" value="${a.nomArticle}" class="link">

						</form>
				  
				 <br>
				Prix:	${a.miseAprix} points  <br> 
				Fin de l'enchère:	
				<tags:localDate date="${a.dateFinEncheres}" pattern="dd/MM/yyyy" />
						<br>

						<form action="ProfilServlet">
							Vendeur: <input type="hidden" name="noVendeur"
								value="${u.noUtilisateur}"> <input type="submit"
								value="${u.pseudo}" class="link">

						</form>

				<img src="images/Utilisateur${u.noUtilisateur}/${a.refPhoto}" width='80' height='80'/>


					</c:forEach>
					</td>

			</tr>
		</c:forEach>
	</table>



</body>
</html>