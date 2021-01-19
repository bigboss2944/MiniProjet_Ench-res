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

 
	
	<h3>Nouvelle vente</h3>

	<form action="VendreUnArticleServlet"  method="post"  >
		 
				 Article :<input type="text" name="nomArticle" placeholder="30 caractères maximum" required />  <br>
	       		 Description :
	       		 <textarea  name="description" rows="6" cols="50" placeholder="300 caractères maximum" required>  </textarea> <br>
       	 		Catégorie: 
					<select	name="categorie">
						<option>Toutes
							<c:forEach var="categorie" items="${categorieModel.lstCategories}">
								<option value="${categorie.libelle}">${categorie.libelle}
								</option>
							</c:forEach>
					</select> <br> 
	       		Photo de l'article :<input type="file" id="img" name="img" >  <br>
       		 Mise à prix :<input type="number" name="miseAPrix"  " required />  <br>
	       		Début de l'enchère :<input type="date" name="dateDebutEnchere" required />  <br>
       	 
			 Fin de l'enchere :<input type="date" name="dateFinEnchere" required  " />  <br>
	       		Retrait :  <br>
	       		Rue: <input type="text" name="rue"  value="${user.rue}"  />   <br>
	       		Code postal: <input type="text" name="codePostal"   value="${user.codePostal}"  />   <br>
	       		Ville: <input type="text" name="ville"  value="${user.ville}"  />   <br>
       		 
				
	       		 <input type="submit" value="Enregistrer" />  
       		  
	</form>
	 <a href="/Enchere/ListeEncheresConnecteServlet"><input type="button" value="Annuler"> 
 


	
	
</body>
</html>