package fr.ENI.HiddenFigures.Enchere.bll;

import java.time.LocalDate;
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
	public List<ArticleVendu> getArticleByEtatNonDebute()     {
		List<ArticleVendu> listArticlesVendusNonDebute = new ArrayList<>();
		for (ArticleVendu articleVendu : this.listArticlesVendus) {
			if(articleVendu.getDateDebutEncheres().compareTo(LocalDate.now()) > 0) {
				listArticlesVendusNonDebute.add(articleVendu);
			} 
		} 	
		return listArticlesVendusNonDebute;
		
	}
	@Override
	public List<ArticleVendu> getArticleByEtatTermine()     {
		List<ArticleVendu> listArticlesVendusTermine= new ArrayList<>();
		for (ArticleVendu articleVendu : this.listArticlesVendus) {
			if(articleVendu.getDateFinEncheres().compareTo(LocalDate.now()) < 0) {
				listArticlesVendusTermine.add(articleVendu);
			} 
		} 	
		return listArticlesVendusTermine;
		
	}
	@Override
	public List<ArticleVendu> getArticleByNomArticleContient(String motCle)     {
		List<ArticleVendu> listArticlesVendusContientMotCle= new ArrayList<>();
		for (ArticleVendu articleVendu : this.listArticlesVendus) {
			if(articleVendu.getNomArticle().toLowerCase().contains(motCle.toLowerCase())) {
				listArticlesVendusContientMotCle.add(articleVendu);
			} 
		} 	
		return listArticlesVendusContientMotCle;
		
	}
	@Override
	public List<ArticleVendu> getArticleByCategorie(Integer noCategorie)     {
		List<ArticleVendu> listArticlesVendusByCategorie= new ArrayList<>();
		for (ArticleVendu articleVendu : this.listArticlesVendus) {
			if(articleVendu.getNoCategorie() ==noCategorie) {
				listArticlesVendusByCategorie.add(articleVendu);
			} 
		} 	
		return listArticlesVendusByCategorie;
		
	}
	@Override
	public List<ArticleVendu> getArticleByNomArticleContientEtNoCategorie(String motCle, Integer noCategorie)     {
		List<ArticleVendu> listArticlesVendusContientMotCleCategorie= new ArrayList<>();
		for (ArticleVendu articleVendu : this.listArticlesVendus) {
			if(articleVendu.getNomArticle().toLowerCase().contains(motCle.toLowerCase()) &&  articleVendu.getNoCategorie() ==noCategorie) {
				listArticlesVendusContientMotCleCategorie.add(articleVendu);
			} 
		} 	
		return listArticlesVendusContientMotCleCategorie;
		
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
		try {
			articleVendu =  articleDAO.insert(articleVendu);
			VerifyArticleVenduOK(articleVendu);
			listArticlesVendus.add(articleVendu);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur d'insertion un article dans BDD ");
		}
		
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> getLstArticleVendus()  {
		 
		return listArticlesVendus;
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
	@Override
	public List<ArticleVendu> getArticleByEtatVenteNonDebute() {
		// TODO Auto-generated method stub
		return null;
	}
	public void VerifyArticleVenduOK(ArticleVendu articlevendu) throws BLLException {
		 if("".equals(articlevendu.getNomArticle().trim())) {
			 throw new BLLException("Nom d'article doit contenir au moin un caractère");
		 }
		 if(articlevendu.getNomArticle().length() > 30) {
			 throw new BLLException("Nom d'article contient maximum 30 caractères");
		 }
		 if("".equals(articlevendu.getDescription().trim())) {
			 throw new BLLException("Description doit contenir au moin un caractère");
		 }
		 if( articlevendu.getDescription().length() > 300 ) {
			 throw new BLLException("Description contient maximum 300 caractères");
		 }
		 if(articlevendu.getMiseAprix() <0){
			 throw new BLLException("Prix initial doit être positive");
		 }
		 if(articlevendu.getDateDebutEncheres().compareTo(articlevendu.getDateFinEncheres())>=0){
			 throw new BLLException("La date de fin d'enchère doit être après la date de début d'enchère");
		 }
		// if(articlevendu.get)
	}

}
