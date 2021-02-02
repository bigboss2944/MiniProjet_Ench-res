package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Retrait;

public interface RetraitDAO {
	void deleteByNoArticle(Integer noArticleVendu) throws DALException;
	Retrait insert(Retrait retrait) throws DALException;
	Retrait update(Retrait retrait) throws DALException;
	List<Retrait> getAll() throws DALException;
	 Retrait selectByNoArticle(Integer noArticleVendu) throws DALException;
}
