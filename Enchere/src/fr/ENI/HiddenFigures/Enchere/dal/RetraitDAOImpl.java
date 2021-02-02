package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Retrait;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

public class RetraitDAOImpl implements RetraitDAO {
	private String DELETE_BY_NO_ARTICLE = "DELETE FROM  RETRAITS where   no_article =?";
	private String INSERT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) "
			+ " VALUES (?,?,?,?)";
	private String UPDATE = "UPDATE RETRAITS set rue=?, code_postal=?, ville=? where no_article =?";
	private String SELECT = "SELECT * FROM RETRAITS ";
	private String SELECT_BY_NO_ARTICLE = "SELECT * FROM RETRAITS WHERE  no_article =?";
	@Override
	public Retrait selectByNoArticle(Integer noArticleVendu) throws DALException {
		Retrait retrait = new Retrait();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);

			stmt.setInt(1, noArticleVendu);
			ResultSet rs =stmt.executeQuery();
			while(rs.next()) {
				
				 retrait.setNoArticle(rs.getInt("no_article"));
				 retrait.setCode_postal(rs.getString("code_postal"));
				 retrait.setRue(rs.getString("rue"));
				 retrait.setVille(rs.getString("ville"));
				
				   
			}
			

		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la sélection des retraits par  noArticleVendu ");
		}
		return retrait;

	}
	@Override
	public List<Retrait> getAll() throws DALException {
		List<Retrait> result = new ArrayList<Retrait>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Retrait retrait = new Retrait();
				 retrait.setNoArticle(rs.getInt("no_article"));
				 retrait.setCode_postal(rs.getString("code_postal"));
				 retrait.setRue(rs.getString("rue"));
				 retrait.setVille(rs.getString("ville"));
				
				
				result.add(retrait);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des retraits");
		}
		return result;
	}
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
	@Override
	public Retrait update(Retrait retrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE);
			stmt.setInt(4,  retrait.getNoArticle());
			stmt.setString(1,  retrait.getRue());
			stmt.setString(2, retrait.getCode_postal());
			stmt.setString(3, retrait.getVille());
			 
			 
			 
			stmt.executeUpdate();
			
	
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans UPDATE d'un retrait");
		}
		return retrait;
	}
	

}
