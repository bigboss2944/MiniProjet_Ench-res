<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageEncherir</title>
<link rel="stylesheet" href="Styles.css" />
</head>

<body>
<a href="/Enchere/ListeEncheresConnecteServlet">ENI-ENCHERE</a>
<h2 style="color:red;">${message}</h2>

 
	
	<h3>Détail vente</h3>

	<form action="EnchereServlet"  method="post"  >
		 
				 Article : ${articleVenduModel.articleVendu.nomArticle}  <br>
	       		 Description :<br>
	       		 <textarea  name="description" rows="6" cols="50" >${articleVenduModel.articleVendu.description}</textarea> <br>
       	 		Catégorie: 
					 
						  ${libelleCategorie}<br>
			 
			 
				 
	       	 
	       	 <c:choose>
    <c:when test="${encherisseur.pseudo!=null}">
     Meilleure offre:  ${montantEnchere}  points par   ${encherisseur.pseudo} 
        <br />
    </c:when>    
    <c:otherwise>
       Meilleure offre:  ${montantEnchere}  point par  personne
        <br />
    </c:otherwise>
</c:choose>
	       	  <br>
       		 Mise à prix : ${articleVenduModel.articleVendu.miseAprix}  points  <br>
	       		 
       	 
			 Fin de l'enchere :  <tags:localDate date="${articleVenduModel.articleVendu.dateFinEncheres}" pattern="dd/MM/yyyy"/> <br>
	       		Retrait :   ${vendeur.rue} ${vendeur.codePostal} ${vendeur.ville}
	<br>
	 Vendeur:  ${vendeur.pseudo}
	<br> 
	Ma proposition: <input type="number" name="miseAPrix"   min ="${articleVenduModel.articleVendu.miseAprix}" step="1" />  <br> 
				
	       		 <input type="submit" value="Enchérir" />  
       		  
	</form>
	 

	   
 


	
	
</body>
</html>