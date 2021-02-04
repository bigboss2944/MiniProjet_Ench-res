package fr.ENI.HiddenFigures.Enchere.dal;



public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}

	public static ArticleVenduDAO getArticleDAO() {  
		return new ArticleVenduDAOImpl();
	}
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
	}
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
	}
	public static UtilisateurAuthTokenDAO getTokenDAO() {
		return new UtilisateurAuthTokenDAOImpl();
	}
}
