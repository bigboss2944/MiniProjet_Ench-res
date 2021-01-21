package fr.ENI.HiddenFigures.Enchere.dal;

import fr.ENI.HiddenFigures.Enchere.bo.Retrait;

public interface RetraitDAO {
	void deleteByNoArticle(Integer noArticleVendu) throws DALException;
	Retrait insert(Retrait retrait) throws DALException;
}
