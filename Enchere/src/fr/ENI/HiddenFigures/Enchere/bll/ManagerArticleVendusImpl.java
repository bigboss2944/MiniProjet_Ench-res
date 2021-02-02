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
	public List<ArticleVendu> getArticleByNoUtilisateur(Integer noUtilisateur)     {
		List<ArticleVendu> listArticleByNoUtilisateur = new ArrayList<>();
		for (ArticleVendu articleVendu : this.listArticlesVendus) {
			if(articleVendu.getNoUtilisateur() == noUtilisateur ) {
				listArticleByNoUtilisateur.add(articleVendu);
			} 
		} 	
		return listArticleByNoUtilisateur;
		
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
			VerifyArticleVenduOK(articleVendu);
			articleVendu =  articleDAO.insert(articleVendu);
			listArticlesVendus.add(articleVendu);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur d'insertion un article ");
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
	public ArticleVendu getArticleVendu(Integer idArticle)   {
		ArticleVendu articleVendu =new ArticleVendu();
		for (ArticleVendu articleVendu2 : listArticlesVendus) {
			if(articleVendu2.getNoArticle()==idArticle) {
				articleVendu =articleVendu2;
			}
			
		} 
		return articleVendu;
	}

	@Override
	public void deleteArticleVendu(Integer idArticle) throws BLLException {
		try {
			articleDAO.deleteArticleVendu(idArticle);
			listArticlesVendus = articleDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la suppression un article");
		}
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
		 if(articlevendu.getDateDebutEncheres().compareTo(LocalDate.now()) <0){
			 throw new BLLException("La date de debut d'enchère doit être à partir d'aujourd'hui");
		 }
	}
	
	@Override
	public ArticleVendu getArticleVenduByNom(String nomArticle) throws BLLException {
		// TODO Auto-generated method stub
		ArticleVendu articleVendu=null;
		for (ArticleVendu a : listArticlesVendus) {
			if(a.getNomArticle().equals(nomArticle)) {
				articleVendu=a;
			}
		}
		return articleVendu;
	}
	
	@Override
	 
	public void modifierPrixVente(Integer noArticle, Integer newPrixVente) throws BLLException {
		try {
			articleDAO.updatePrixVente(noArticle, newPrixVente);
			listArticlesVendus = articleDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de prix vente");
		}

	}
	@Override
	 
	public void modifierRefPhoto(Integer noArticle, String new_refPhoto) throws BLLException {
		try {
			articleDAO.updateRefPhoto(noArticle, new_refPhoto);
			listArticlesVendus = articleDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de refPhoto");
		}

	}
	
	@Override
	 
	public void modifierArticleVendu(ArticleVendu articleVendu) throws BLLException {
		try {
			articleDAO.update(articleVendu);
			listArticlesVendus = articleDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de prix vente");
		}

	}

}
