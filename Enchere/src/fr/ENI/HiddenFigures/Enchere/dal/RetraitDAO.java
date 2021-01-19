package fr.ENI.HiddenFigures.Enchere.dal;

public interface RetraitDAO {
	void deleteByNoArticle(Integer noArticleVendu) throws DALException;
}
