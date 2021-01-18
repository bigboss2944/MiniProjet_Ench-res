package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	private String Delete="Delete from ENCHERES where no_enchere=?";
	private String DeleteByIdArticle="Delete from ENCHERES where no_article=?";
	
	
	@Override
	public Enchere insert(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
		LocalDateTime date = enchere.getDateEnchere();
		Date date_enchereLocalDateTime = Date.valueOf(date.toString());//Conversion LocalDateTime vers SQL Date
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(Insert);
			stmt.setDate(1, date_enchereLocalDateTime);
			stmt.setInt(2,enchere.getMontant_enchere());
			stmt.setInt(3, enchere.getNo_article());
			stmt.setInt(4, enchere.getNo_utilisateur());
			
		}catch (Exception e) {
			throw new DALException("Couche DAL - ProblÃ¨me à l'insertion de l'enchère");
		}
		
		return enchere;
	}

	@Override
	public Enchere getEnchere(Integer idEnchere) throws DALException {
		// TODO Auto-generated method stub
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SelectOne);
			stmt.setInt(1, idEnchere);
			ResultSet rs = stmt.executeQuery();
			
			String date_enchereString = rs.getString("date_enchere");
            LocalDateTime date_enchereLocalDateTime = LocalDateTime.parse(date_enchereString);
            enchere.setDateEnchere(date_enchereLocalDateTime);
			enchere.setNo_enchere(rs.getInt("no_enchere"));
			enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			enchere.setNo_article(rs.getInt("no_article"));
			enchere.setNo_article(rs.getInt("no_utilisateur"));
			
		} catch (Exception e) {
			throw new DALException("Couche DAL - ProblÃ¨me à l'accession de l'enchère");
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
			throw new DALException("Couche DAL - Problèmes dans le listing des Enchères");
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
			throw new DALException("Couche DAL - Problèmes dans le listing des Enchères par Utilisateurs");
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
			throw new DALException("Couche DAL - ProblÃ¨me à la mise à jour de l'enchère");
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
			throw new DALException("Couche DAL - ProblÃ¨me à la suppression de l'enchère");
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
			throw new DALException("Couche DAL - ProblÃ¨me à la suppression de l'enchère");
		}
		return enchere;
	}
	

	@Override
	public String selectUtilisateurByEnchere(Enchere idEnchere) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
