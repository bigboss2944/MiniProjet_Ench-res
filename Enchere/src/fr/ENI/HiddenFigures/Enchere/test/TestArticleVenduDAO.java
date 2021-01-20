package fr.ENI.HiddenFigures.Enchere.test;

 
import java.time.LocalDate;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;

public class TestArticleVenduDAO {
	
	public static void main(String[] args) {
		Integer idUtilisateur=4;
		 try {
			Utilisateur listArticlesVendus = DAOFactory.getUtilisateurDAO().getUtilisateur(idUtilisateur);
			System.out.println(listArticlesVendus);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
}