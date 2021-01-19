package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;


public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	private String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ " VALUES (?,?,?,?,?,?,?,?)";
	private String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	private String SELECT_ONE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private String SELECT_BY_ETAT_VENTE_ENCOURS = "SELECT * FROM  ARTICLES_VENDUS where GETDATE() BETWEEN "
			+ " date_debut_encheres AND date_fin_encheres";
	private String select_Utilisayeur_By_Article = "Select pseudo from ARTICLES_VENDUS a inner Join UTILISATEURS u on u.no_utilisateur = ?";
	private String DELETE_BY_NO_UTILISATEUR = "DELETE FROM  ARTICLES_VENDUS where  no_utilisateur=?";
	private String SELECT_BY_NO_UTILISATEUR = "SELECT * FROM  ARTICLES_VENDUS where  no_utilisateur=?";
	
	public List<ArticleVendu> selectByEtatVenteEnCours() throws DALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ETAT_VENTE_ENCOURS);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description")); 
				
				String dateDebutEncheresString = rs.getString("date_debut_encheres");
				LocalDate dateDebutEncheresLocalDate = Date.valueOf(dateDebutEncheresString).toLocalDate();
				
				articleVendu.setDateDebutEncheres(dateDebutEncheresLocalDate);
				
				String dateFinEncheresString = rs.getString("date_fin_encheres");
				LocalDate dateFinEncheresLocalDate = Date.valueOf(dateFinEncheresString).toLocalDate();
				
				articleVendu.setDateFinEncheres(dateFinEncheresLocalDate);
				
				articleVendu.setMiseAprix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVendu.setNoCategorie(rs.getInt("no_categorie"));
	
				
				
				result.add(articleVendu);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des utilisateurs par l'état de vente en cours");
		}
		return result;
	}
	public String selectUtilisateurByArticle(ArticleVendu article ) throws DALException {
		String pseudo= null; 
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(select_Utilisayeur_By_Article);
			stmt.setInt(1,  article.getNoUtilisateur())  ;  
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { 
				pseudo =  rs.getString("pseudo");

			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des utilisateurs par l'article");
		}
		return pseudo;
	}
	public void deleteByNoUtilisateur(Integer noUtilisateur ) throws DALException {
		 
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO_UTILISATEUR);
			stmt.setInt(1, noUtilisateur )  ;  
			stmt.executeUpdate(); 
			 
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la suppression des articles par noUtilisateur");
		}
		 
	}
	public List<ArticleVendu> selectByNoUtilisateur(Integer noUtilisateur) throws DALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NO_UTILISATEUR);
			stmt.setInt(1, noUtilisateur );
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description")); 
				
				String dateDebutEncheresString = rs.getString("date_debut_encheres");
				LocalDate dateDebutEncheresLocalDate = Date.valueOf(dateDebutEncheresString).toLocalDate();
				
				articleVendu.setDateDebutEncheres(dateDebutEncheresLocalDate);
				
				String dateFinEncheresString = rs.getString("date_fin_encheres");
				LocalDate dateFinEncheresLocalDate = Date.valueOf(dateFinEncheresString).toLocalDate();
				
				articleVendu.setDateFinEncheres(dateFinEncheresLocalDate);
				
				articleVendu.setMiseAprix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVendu.setNoCategorie(rs.getInt("no_categorie"));
	
				
				
				result.add(articleVendu);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des utilisateurs par noUtilisateur");
		}
		return result;
	}
	@Override
	public ArticleVendu insert(ArticleVendu articleVendu) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,  articleVendu.getNomArticle())  ;  
			stmt.setString(2,  articleVendu.getDescription())  ;
			
			Date dateDebutEncheresSQL = Date.valueOf(articleVendu.getDateDebutEncheres()); 
			stmt.setDate(3, dateDebutEncheresSQL);
			
			Date dateFinEncheresSQL = Date.valueOf(articleVendu.getDateFinEncheres()); 
			stmt.setDate(4, dateFinEncheresSQL);
			
			stmt.setInt(5, articleVendu.getMiseAprix());
			stmt.setInt(6, articleVendu.getPrixVente());
			stmt.setInt(7, articleVendu.getNoUtilisateur());
			stmt.setInt(8, articleVendu.getNoCategorie());
			 
			int nbRows =stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					articleVendu.setNoArticle(rs.getInt(1));
				}
			}
	
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans l'insertion d'article vendu");
		}
		return articleVendu;
	}

	@Override
	public ArticleVendu getArticleVendu(Integer idArticle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> getAll() throws DALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description")); 
				
				String dateDebutEncheresString = rs.getString("date_debut_encheres");
				LocalDate dateDebutEncheresLocalDate = Date.valueOf(dateDebutEncheresString).toLocalDate();
				
				articleVendu.setDateDebutEncheres(dateDebutEncheresLocalDate);
				
				String dateFinEncheresString = rs.getString("date_fin_encheres");
				LocalDate dateFinEncheresLocalDate = Date.valueOf(dateFinEncheresString).toLocalDate();
				
				articleVendu.setDateFinEncheres(dateFinEncheresLocalDate);
				
				articleVendu.setMiseAprix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVendu.setNoCategorie(rs.getInt("no_categorie"));
	
				
				
				result.add(articleVendu);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des utilisateurs par noUtilisateur");
		}
		return result;
	}

	@Override
	public ArticleVendu update(ArticleVendu utilisateur) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu deleteArticleVendu(Integer idArticle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
