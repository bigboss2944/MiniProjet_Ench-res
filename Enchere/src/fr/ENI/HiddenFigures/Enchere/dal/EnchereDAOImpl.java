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
	private String SelectAll="Select * from ENCHERES";
	private String SelectOne="Select * from ENCHERES where no_enchere=?";
	private String SelectAllByUsers="Select * from ENCHERES where no_utilisateur=?";
	private String Insert="Insert into ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) values(?,?,?,?)";
	private String Update="Update ENCHERES set date_enchere=?,montant_enchere=?,no_article=?,no_utilisateur=? where no_enchere=?";
	private String DELETE_BY_NO_UTILISATEUR_OU_NO_ARTICLE = "DELETE FROM  ENCHERES where  no_utilisateur=? or no_article =?";
	private String Delete="Delete from ENCHERES where no_enchere=?";
	private String DeleteByIdArticle="Delete from ENCHERES where no_article=?";


	@Override
	public Enchere insert(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
		LocalDateTime date = enchere.getDateEnchere();
		java.util.Date utilDate;
		String dateFormat = "yyyy-MM-dd";
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(dateFormat);
		SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
		
		try {
			utilDate = sdf1.parse(date.format(dtf1));
		} catch (ParseException e) {
		    utilDate = null; // handle the exception
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		//Date date_enchereLocalDateTime = Date.valueOf(date.toString());//Conversion LocalDateTime vers SQL Date
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(Insert);
			stmt.setDate(1, sqlDate);
			stmt.setInt(2, enchere.getMontant_enchere());
			stmt.setInt(3, enchere.getNo_article());
			stmt.setInt(4, enchere.getNo_utilisateur());
			stmt.executeUpdate();

		}catch (Exception e) {
			throw new DALException("Couche DAL - Problème � l'insertion de l'ench�re");
		}

		return enchere;
	}

	public void deleteByNoUtilisateurNoArticle(Integer noUtilisateur, Integer noArticleVendu) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO_UTILISATEUR_OU_NO_ARTICLE);
			stmt.setInt(1, noUtilisateur);
			stmt.setInt(2, noArticleVendu);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la suppression des encheres par noUtilisateur ou noArticleVendu");
		}
	}
	@Override
	public Enchere getEnchere(Integer idEnchere) throws DALException {
		// TODO Auto-generated method stub
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SelectOne);
			stmt.setInt(1, idEnchere);
			ResultSet rs = stmt.executeQuery();
			String date_enchereString = rs.getString("date_enchere").substring(0,19);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date_enchereLocalDateTime = LocalDateTime.parse(date_enchereString,formatter);
            enchere.setDateEnchere(date_enchereLocalDateTime);
			enchere.setNo_enchere(rs.getInt("no_enchere"));
			enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			enchere.setNo_article(rs.getInt("no_article"));
			enchere.setNo_utilisateur(rs.getInt("no_utilisateur"));

		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème � l'accession de l'ench�re");
		}

		return enchere;

	}

	@Override
	public List<Enchere> getAll() throws DALException {
		// TODO Auto-generated method stub
		List<Enchere> result = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SelectAll);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				String date_enchereString = rs.getString("date_enchere");
	            LocalDateTime date_enchereLocalDateTime = LocalDateTime.parse(date_enchereString);
	            enchere.setDateEnchere(date_enchereLocalDateTime);
				enchere.setNo_enchere(rs.getInt("no_enchere"));
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				enchere.setNo_article(rs.getInt("no_article"));
				enchere.setNo_article(rs.getInt("no_utilisateur"));

				result.add(enchere);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Probl�mes dans le listing des Ench�res");
		}
		return result;
	}

	@Override
	public List<Enchere> getAllEncheresByUtilisateur(Integer idUtilisateur) throws DALException {
		// TODO Auto-generated method stub
		List<Enchere> result = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SelectAllByUsers);
			stmt.setInt(1, idUtilisateur);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				String date_enchereString = rs.getString("date_enchere");
	            LocalDateTime date_enchereLocalDateTime = LocalDateTime.parse(date_enchereString);
	            enchere.setDateEnchere(date_enchereLocalDateTime);
				enchere.setNo_enchere(rs.getInt("no_enchere"));
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				enchere.setNo_article(rs.getInt("no_article"));
				enchere.setNo_article(rs.getInt("no_utilisateur"));

				result.add(enchere);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Probl�mes dans le listing des Ench�res par Utilisateurs");
		}
		return result;
	}

	@Override
	public Enchere update(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
		LocalDateTime date = enchere.getDateEnchere();
		Date date_enchereLocalDateTime = Date.valueOf(date.toString());//Conversion LocalDateTime vers SQL Date
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(Update);
			stmt.setDate(1, date_enchereLocalDateTime);
			stmt.setInt(2,enchere.getMontant_enchere());
			stmt.setInt(3, enchere.getNo_article());
			stmt.setInt(4, enchere.getNo_utilisateur());
			stmt.setInt(5, enchere.getNo_enchere());

		}catch (Exception e) {
			throw new DALException("Couche DAL - Problème � la mise � jour de l'ench�re");
		}

		return enchere;
	}

	@Override
	public Enchere deleteEnchere(Integer idEnchere) throws DALException {
		// TODO Auto-generated method stub
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(Delete);
			stmt.setInt(1, idEnchere);
			ResultSet rs = stmt.executeQuery();
		}catch (Exception e) {
			throw new DALException("Couche DAL - Problème � la suppression de l'ench�re");
		}
		return enchere;
	}

	@Override
	public Enchere deleteEnchereByArticle(Integer idArticle) throws DALException {
		// TODO Auto-generated method stub
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(DeleteByIdArticle);
			stmt.setInt(1, idArticle);
			ResultSet rs = stmt.executeQuery();
		}catch (Exception e) {
			throw new DALException("Couche DAL - Problème � la suppression de l'ench�re");
		}
		return enchere;
	}


	@Override
	public String selectUtilisateurByEnchere(Enchere idEnchere) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
