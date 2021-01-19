package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public interface EnchereDAO {
	 List<Enchere> getAll() throws DALException;
	void deleteByNoUtilisateurNoArticle(Integer noUtilisateur, Integer noArticleVendu ) throws DALException;
}
