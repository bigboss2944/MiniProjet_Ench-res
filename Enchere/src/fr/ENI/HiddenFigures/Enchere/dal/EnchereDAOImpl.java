package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private String DELETE_BY_NO_ARTICLE = "DELETE FROM  ENCHERES where   no_article =?";
	private String Insert="Insert into ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) values(GETDATE(),?,?,?)";
	//private String Insert="Insert into ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) values(?,?,?,?)";
	
	@Override
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
	public void deleteByNoArticle( Integer noArticleVendu) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO_ARTICLE); 

			stmt.setInt(1, noArticleVendu);

			stmt.executeUpdate();

		} catch (Exception e) {
			throw new DALException(
					"Couche DAL - Problème dans la suppression des encheres par   noArticleVendu ");
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
	@Override
	public Enchere insert(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
//		LocalDateTime date = enchere.getDateEnchere();
//		java.util.Date utilDate;
//		String dateFormat = "yyyy-MM-dd";
//		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(dateFormat);
//		SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
//		
//		try {
//			utilDate = sdf1.parse(date.format(dtf1));
//		} catch (ParseException e) {
//		    utilDate = null; // handle the exception
//		}
//		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		//Date date_enchereLocalDateTime = Date.valueOf(date.toString());//Conversion LocalDateTime vers SQL Date
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(Insert);
			//stmt.setDate(1, sqlDate);
			stmt.setInt(1, enchere.getMontant_enchere());
			stmt.setInt(2, enchere.getNo_article());
			stmt.setInt(3, enchere.getNo_utilisateur());
			
			stmt.executeUpdate();
		}catch (Exception e) {
			throw new DALException("Couche DAL - Problème � l'insertion de l'ench�re");
		}

		return enchere;
	}
	
}
