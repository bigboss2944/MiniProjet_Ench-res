package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public class EnchereDAOImpl implements EnchereDAO {
	private String DELETE_BY_NO_UTILISATEUR_OU_NO_ARTICLE = "DELETE FROM  ENCHERES where  no_utilisateur=? or no_article =?";
	private String SELECT = "SELECT * from ENCHERES";
	

	public void deleteByNoUtilisateurNoArticle(Integer noUtilisateur, Integer noArticleVendu) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO_UTILISATEUR_OU_NO_ARTICLE);

			stmt.setInt(1, noUtilisateur);

			stmt.setInt(2, noArticleVendu);

			stmt.executeUpdate();

		} catch (Exception e) {
			throw new DALException(
					"Couche DAL - Problème dans la suppression des encheres par noUtilisateur ou noArticleVendu ");
		}

	}

	@Override
	public List<Enchere> getAll() throws DALException {
		List<Enchere> result = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNo_enchere(rs.getInt("no_enchere"));

				String date_enchereString = rs.getString("date_enchere").substring(0, 19); //recupérer la date de la base de données de format yyyy-MM-dd HH:mm:ss 
				 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime date_enchereLocalDateTime = LocalDateTime.parse(date_enchereString,formatter);
				enchere.setDateEnchere(date_enchereLocalDateTime);
				

				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				 
				enchere.setNo_article(rs.getInt("no_article"));
				 
				enchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
				 
				result.add(enchere);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des enchères");
		}
		return result;
	}
	
	
}
