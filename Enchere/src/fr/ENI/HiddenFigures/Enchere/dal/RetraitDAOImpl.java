package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RetraitDAOImpl implements RetraitDAO {
	private String DELETE_BY_NO_ARTICLE = "DELETE FROM  RETRAITS where   no_article =?";

	public void deleteByNoArticle(Integer noArticleVendu) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO_ARTICLE);

			stmt.setInt(1, noArticleVendu);
			stmt.executeUpdate(); 

		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la suppression des retraits par  noArticleVendu ");
		}

	}

}
