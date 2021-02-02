package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
 

public interface ManagerArticleVendus {
	 List<ArticleVendu> getArticleByEtatVenteEnCours() throws BLLException ;
	 String getPseudoByArticle (ArticleVendu article) throws BLLException ;
	 List<ArticleVendu> getArticleByEtatNonDebute()   ;
	 List<ArticleVendu> getArticleByEtatTermine()  ;
	 List<ArticleVendu> getArticleByNomArticleContient(String motCle) ;
	 List<ArticleVendu> getArticleByNomArticleContientEtNoCategorie(String motCle, Integer noCategorie) ;
	 List<ArticleVendu> getArticleByCategorie(Integer noCategorie) ;
	 List<ArticleVendu> getArticleByNoUtilisateur(Integer noUtilisateur) ;
	 ArticleVendu getArticleVenduByNom(String nomArticle) throws BLLException ;
	 void modifierPrixVente(Integer noArticle, Integer newPrixVente) throws BLLException;
	 void VerifyArticleVenduOK(ArticleVendu articlevendu) throws BLLException;
	 void modifierArticleVendu(ArticleVendu articleVendu) throws BLLException;
	 
	ArticleVendu addArticleVendu(ArticleVendu articleVendu) throws BLLException;
	
	 
	
	List<ArticleVendu> getLstArticleVendus()  ;
	 
	ArticleVendu getArticleVendu(Integer idArticle) ;
	 
	 void deleteArticleVendu(Integer idArticle) throws BLLException;
 
	void verifAdresseRetrait(ArticleVendu articleVendu) throws BLLException;
	void modifierRefPhoto(Integer noArticle, String new_refPhoto) throws BLLException;
	
	
	
	
}
