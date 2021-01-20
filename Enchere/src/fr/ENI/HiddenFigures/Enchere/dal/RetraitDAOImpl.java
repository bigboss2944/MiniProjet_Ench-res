package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.ENI.HiddenFigures.Enchere.bo.Retrait;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

public class RetraitDAOImpl implements RetraitDAO {
	private String DELETE_BY_NO_ARTICLE = "DELETE FROM  RETRAITS where   no_article =?";
	private String INSERT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) "
			+ " VALUES (?,?,?,?)";

	public void deleteByNoArticle(Integer noArticleVendu) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO_ARTICLE);

			stmt.setInt(1, noArticleVendu);
			stmt.executeUpdate(); 

		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la suppression des retraits par  noArticleVendu ");
		}

	}
	public Retrait insert(Retrait retrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,  retrait.getNoArticle());
			stmt.setString(2,  retrait.getRue());
			stmt.setString(3, retrait.getCode_postal());
			stmt.setString(4, retrait.getVille());
			 
			 
			 
			stmt.executeUpdate();
			
	
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans l'insertion d'un retrait");
		}
		return retrait;
	}

}
