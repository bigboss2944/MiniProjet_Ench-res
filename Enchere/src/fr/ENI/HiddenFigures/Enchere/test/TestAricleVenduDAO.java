package fr.ENI.HiddenFigures.Enchere.test;

 
import java.time.LocalDate;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Retrait;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;

public class TestAricleVenduDAO {
	
	public static void main(String[] args) {
		
		ArticleVendu article = new ArticleVendu();
		article.setNomArticle("test");
		article.setDescription("test");
		article.setDateDebutEncheres(LocalDate.now() );
		article.setDateFinEncheres( LocalDate.of(2021, 2, 20));
		article.setMiseAprix(3);;
		article.setNoUtilisateur(2);
		article.setNoCategorie(3);
		article.setRefPhoto("chouky.jpg");
		try {
			DAOFactory.getArticleDAO().insert(article);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
	}
}
