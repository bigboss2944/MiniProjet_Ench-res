package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

public interface ArticleVenduDAO {
	ArticleVendu insert(ArticleVendu utilisateur) throws DALException;
	ArticleVendu getArticleVendu(Integer idArticle) throws DALException;
	List<ArticleVendu> getAll() throws DALException ;
	ArticleVendu update(ArticleVendu utilisateur) throws DALException;
	void deleteArticleVendu(Integer idArticle) throws DALException;
	String selectUtilisateurByArticle(ArticleVendu article ) throws DALException;
	List<ArticleVendu> selectByEtatVenteEnCours() throws DALException;
	void deleteByNoUtilisateur(Integer noUtilisateur ) throws DALException;
	 List<ArticleVendu> selectByNoUtilisateur(Integer noUtilisateur) throws DALException;
//	 List<ArticleVendu> selectByEtatVenteNonDebute() throws DALException;
//	 List<ArticleVendu> selectByEtatVenteTermine() throws DALException;
}
