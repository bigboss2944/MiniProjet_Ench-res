package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
 

public interface ManagerArticleVendus {
	 List<ArticleVendu> getArticleByEtatVenteEnCours() throws BLLException ;
	 String getPseudoByArticle (ArticleVendu article) throws BLLException ;
	
	
	
	/***
	 * Ajouter un article vendu
	 * @param articleVendu
	 * @return
	 * @throws BLLException
	 */
	ArticleVendu addArticleVendu(ArticleVendu articleVendu) throws BLLException;
	
	/***
	 * Lister tous les articles vendus
	 * @param articleVendu
	 * @return
	 * @throws BLLException
	 */
	
	List<ArticleVendu> getLstArticleVendus() throws BLLException;
	/***
	 * Lister un article vendu
	 * @param articleVendu
	 * @return
	 * @throws BLLException
	 */
	ArticleVendu getArticleVendu(Integer idArticle) throws BLLException;
	/***
	 * Supprimer un article vendu
	 * @param articleVendu
	 * @return
	 * @throws BLLException
	 */
	ArticleVendu deleteArticleVendu(Integer idArticle) throws BLLException;
	
	/***
	 * Verifier l'adresse de retrait de l'article
	 * @param articleVendu
	 * @return
	 * @throws BLLException
	 */
	void verifAdresseRetrait(ArticleVendu articleVendu) throws BLLException;
	
	
	
}
