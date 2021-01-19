package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;


public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	private String INSERT = "INSERT INTO ARTICLES_VENDUS (nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAprix, prixVente, noUtilisateur, noCategorie, etatVente, lieuRetrait) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	private String SELECT_ONE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private String SELECT_BY_ETAT_VENTE_ENCOURS = "SELECT * FROM  ARTICLES_VENDUS where date_fin_encheres >= CONVERT (date, GETDATE());";
	private String select_Utilisateur_By_Article = "Select pseudo from ARTICLES_VENDUS a inner Join UTILISATEURS u on u.no_utilisateur = ?";

	
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
			
			PreparedStatement stmt = cnx.prepareStatement(select_Utilisateur_By_Article);
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
	@Override
	public ArticleVendu insert(ArticleVendu utilisateur) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu getArticleVendu(Integer idArticle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
/*
	@Override
	public List<ArticleVendu> getAll() throws DALException {
		// TODO Auto-generated method stub
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				
				/*articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setDescription(rs.getString("description"));
				//articleVendu.setEtatVente(etatVente);
				articleVendu.setLieuRetrait(lieuRetrait);
				articleVendu.set
				
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getInt("administrateur")==1);
				//TODO: A vérifier
				
				
				result.add(articleVendu);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des utilisateurs");
		}
		return result;
	}
*/
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
	@Override
	public List<ArticleVendu> getAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
