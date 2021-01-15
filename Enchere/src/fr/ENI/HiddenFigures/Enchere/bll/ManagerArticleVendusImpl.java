package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.dal.ArticleVenduDAO;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
 

public class ManagerArticleVendusImpl implements ManagerArticleVendus {
	ArticleVenduDAO articleDAO = DAOFactory.getArticleDAO();
	List<ArticleVendu> listArticlesVendus = new ArrayList<>();
	public ManagerArticleVendusImpl() {
		try {
			listArticlesVendus = articleDAO.getAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<ArticleVendu> getArticleByEtatVenteEnCours() throws BLLException   {
		List<ArticleVendu> listArticlesVendus = new ArrayList<>();
		try {
			listArticlesVendus = articleDAO.selectByEtatVenteEnCours();
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur de sélection par etatVente les articles  en cours ");
		} 
		return listArticlesVendus;
		
	}
	
	@Override
	public String getPseudoByArticle (ArticleVendu article) throws BLLException   {
		 String pseudo =null;
		try {
			pseudo = articleDAO.selectUtilisateurByArticle(article);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur de sélection Pseudo par Article ");
		} 
		return pseudo;
		
	}

	@Override
	public ArticleVendu addArticleVendu(ArticleVendu articleVendu) throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> getLstArticleVendus() throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifAdresseRetrait(ArticleVendu articleVendu) throws BLLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArticleVendu getArticleVendu(Integer idArticle) throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu deleteArticleVendu(Integer idArticle) throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}

}
