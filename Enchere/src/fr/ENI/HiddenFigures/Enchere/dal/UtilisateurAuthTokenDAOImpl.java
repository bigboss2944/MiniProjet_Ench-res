package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.bo.UtilisateurAuthToken;

public class UtilisateurAuthTokenDAOImpl implements UtilisateurAuthTokenDAO {
	private String DELETE_BY_NO_UTILISATEUR = "DELETE FROM UtilisateurAuthToken WHERE no_utilisateur =?";
	private String INSERT = "INSERT INTO UtilisateurAuthToken (selector,validator,no_utilisateur ) "
			+ " VALUES (?,?,?)";
	private String SELECT = "SELECT * FROM UtilisateurAuthToken";
	private String UPDATE= "UPDATE UtilisateurAuthToken  SET selector=?, validator=? where id =?";
	
	
	@Override
	public void deleteByNoUtilisateur(Integer noUtilisateur ) throws DALException {
		 
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO_UTILISATEUR);
			stmt.setInt(1, noUtilisateur )  ;  
			stmt.executeUpdate(); 
			 
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la suppression des Tokens par noUtilisateur");
		}
		 
	}
	@Override
	public UtilisateurAuthToken insert(UtilisateurAuthToken token) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,  token.getSelector())  ;  
			stmt.setString(2, token.getValidator())  ;
			stmt.setInt(3, token.getUtilisateur().getNoUtilisateur());
			 
			
			int nbRows =stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					token.setId(rs.getInt(1));
				}
			}
			 
	
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans l'insertion d'un UtilisateurAuthToken ");
		}
		return token;
	}
	
	@Override
	public List<UtilisateurAuthToken> getAll() throws DALException {
		List<UtilisateurAuthToken> result = new ArrayList<UtilisateurAuthToken>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				UtilisateurAuthToken utilisateurAuthToken = new UtilisateurAuthToken();
				utilisateurAuthToken.setId(rs.getInt("id"));
 
				utilisateurAuthToken.setSelector(rs.getString("selector"));
				utilisateurAuthToken.setValidator(rs.getString("validator"));
				Utilisateur  utilisateur =DAOFactory.getUtilisateurDAO().getUtilisateur(rs.getInt("no_utilisateur"));
				utilisateurAuthToken.setUtilisateur(utilisateur);
 
				result.add(utilisateurAuthToken);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des UtilisateurAuthTokens");
		}
		return result;
	}
	@Override
	public void update( UtilisateurAuthToken newToken) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE);
			stmt.setString(1, newToken.getSelector())  ;  
			stmt.setString(2,  newToken.getValidator())  ;
			stmt.setInt(3,  newToken.getId())  ;
			stmt.executeUpdate(); 
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de UtilisateurAuthToken");
		}
		
	}

}
