<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageEncherir</title>
<link rel="stylesheet" href="Styles.css" />
</head>

<body>
	<h1>
		<a href="/Enchere/ListeEncheresConnectePagination6Servlet">ENI-ENCHERE</a>
	</h1>
	<h2 align="center">Détail Vente</h2>
	<h2 style="color: red;">${message}</h2>

	<table align="left">
		<tr>
			<td><img id="imageProduit" alt="Image Produit"
				src="images/Utilisateur${vendeur.noUtilisateur}/${articleVenduModel.articleVendu.refPhoto}"
				width="250px" height="250px" /></td>
			<td>Article : ${articleVenduModel.articleVendu.nomArticle} <br>
				Description :<br> <textarea name="description" rows="6"
					cols="50">${articleVenduModel.articleVendu.description}</textarea>
				<br> Catégorie: ${libelleCategorie}<br> <c:choose>
					<c:when test="${encherisseur.pseudo!=null}">
     Meilleure offre:  ${montantEnchere}  points par   ${encherisseur.pseudo} 
        <br />
					</c:when>
					<c:otherwise>
       Meilleure offre:  ${montantEnchere}  point par  personne
        <br />
					</c:otherwise>
				</c:choose> <br> Mise à prix : ${articleVenduModel.articleVendu.miseAprix}
				points <br> Fin de l'enchere : <tags:localDate
					date="${articleVenduModel.articleVendu.dateFinEncheres}"
					pattern="dd/MM/yyyy" /> <br> Retrait : ${retrait.rue}
				${retrait.code_postal} ${retrait.ville} <br> Vendeur:
				${vendeur.pseudo} <br>

				<form action="EnchereServlet" method="post">
					Ma proposition: <input type="number" name="propButton"
						value="${articleVenduModel.articleVendu.miseAprix}"
						min="${articleVenduModel.articleVendu.miseAprix}" step="1" /> <br>
					<input type="hidden" name="montantEnchereMaxPrecedent"
						value=" ${montantEnchere}"> <input type="hidden"
						name="noEncherisseurCurrent" value="${encherisseur.noUtilisateur}">
					<input type="hidden" name="noArticleVendu"
						value="${articleVenduModel.articleVendu.noArticle}"> <input
						type="submit" value="Enchérir" />

				</form>




			</td>




		</tr>

	</table>





</body>
</html>