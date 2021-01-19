package fr.ENI.HiddenFigures.Enchere.test;

 
import java.time.LocalDate;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;

public class TestAricleVenduDAO {
	
	public static void main(String[] args) {
		Integer no = 4;
		 try {
			 
			
			List<ArticleVendu> listArticlesVendus = DAOFactory.getArticleDAO().selectByNoUtilisateur(10);
			for (ArticleVendu articleVendu : listArticlesVendus) {
				 if ( articleVendu.getDateFinEncheres() .compareTo(LocalDate.now()) > 0) {
					 System.out.println( articleVendu.getDateFinEncheres() + " is later than now");
				 }
				 else {
					 System.out.println("bad");
				 }
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
}
