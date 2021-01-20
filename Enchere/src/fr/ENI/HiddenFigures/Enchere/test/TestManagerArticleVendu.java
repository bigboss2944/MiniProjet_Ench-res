package fr.ENI.HiddenFigures.Enchere.test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendus;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendusSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;

public class TestManagerArticleVendu {
	static ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	public static void main(String[] args) {
		
		 
			List<ArticleVendu> list;
			try {
				list = managerArticles.getArticleByEtatVenteEnCours();
				for (ArticleVendu articleVendu : list) {
					System.out.println(articleVendu);
				}
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//List<ArticleVendu> list = managerArticles.getLstArticleVendus() ;
			//List<ArticleVendu> list = managerArticles.getArticleByEtatNonDebute();
			//List<ArticleVendu>  = managerArticles.getArticleByEtatTermine();
				
			
			
		
		 
		
		
	}
}
