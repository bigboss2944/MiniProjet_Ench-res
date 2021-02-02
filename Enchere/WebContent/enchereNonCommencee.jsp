<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageEnchereNonCommencee</title>
<link rel="stylesheet" href="Styles.css" />
</head>

<body>
<h1><a href="/Enchere/ListeEncheresConnecteServlet">ENI-ENCHERE</a></h1>
<h2 style="color:red;">${message}</h2>

 
	
	<h3>Modifier vente</h3>

	<form action="ModifierVenteServlet"  method="post"  >
		 
				 Article :<input type="text" name="nomArticle" value="${articleVenduModel.articleVendu.nomArticle}" />  <br>
	       		 Description : <br>
	       		 <textarea  name="description" rows="6" cols="50" >${articleVenduModel.articleVendu.description}</textarea> <br>
       	 		Catégorie: 
					<select	name="categorie">
						<option> ${libelleCategorie}
							<c:forEach var="categorie" items="${categorieModel.lstCategories}">
								<option value="${categorie.libelle}">${categorie.libelle}
								</option>
							</c:forEach>
					</select> <br> 
					
	       		Photo de l'article :
	       		<img src="images/Utilisateur${user.noUtilisateur}/${articleVenduModel.articleVendu.refPhoto}" width='80' height='80'/>
	 <br>
	       	
       		 Mise à prix :<input type="number" name="miseAPrix"  value="${articleVenduModel.articleVendu.miseAprix}" min ="0" step="1" />  <br>
	       		Début de l'enchère :<input type="date" name="dateDebutEnchere" value="${articleVenduModel.articleVendu.dateDebutEncheres}" />  <br>
       	 
			 Fin de l'enchere :<input type="date" name="dateFinEnchere" value="${articleVenduModel.articleVendu.dateFinEncheres}"  />  <br>
	       		Retrait :  <br>
	       		Rue: <input type="text" name="rue"  value="${retrait.rue}"  />   <br>
	       		Code postal: <input type="text" name="codePostal"   value="${retrait.code_postal}"  />   <br>
	       		Ville: <input type="text" name="ville"  value="${retrait.ville}"  />   <br>
       		 
				<input type="hidden" name="noArticleVendu" value="${articleVenduModel.articleVendu.noArticle}"> 
	       		 <input type="submit" value="Enregistrer" />  
       		  
	</form>
	 <a href="/Enchere/ListeEncheresConnecteServlet"><input type="button" value="Annuler">  <br>
	 
	  <form action="SuppressionUnArticleVendu">
							<input type="hidden" name="noArticleVendu" value="${articleVenduModel.articleVendu.noArticle}"> 
							<input type="submit" value="Annuler la vente"  >

		</form>
	   
 
 <a href="<%=request.getContextPath()+"/UploadFileServlet"%>?noArticleVendu=${articleVenduModel.articleVendu.noArticle}"><input type="button" value="Modifier photo"></a>

	
	
</body>
</html>