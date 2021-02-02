package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Retrait;

 




public class CategorieDAOImpl implements CategorieDAO {
	private String SELECT = "SELECT * FROM CATEGORIES";
	private String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES where libelle =?";
	private String DELETE_BY_ID = "DELETE FROM CATEGORIES WHERE no_categorie =?";
	private String UPDATE_BY_ID = "UPDATE   CATEGORIES set libelle =? WHERE no_categorie =?";
	private String INSERT = "INSERT INTO CATEGORIES (libelle)  VALUES (?)";
	@Override
	public Categorie insert(Categorie categorie) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,  categorie.getLibelle());
			stmt.executeUpdate();
			
	
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans l'insertion d'un retrait");
		}
		return categorie;
	}
	@Override
	public void deleteByNoCategorie(Integer noCategorie) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_ID);
			stmt.setInt(1,  noCategorie)  ;
			//retirer les articles dans ArticleVendu qui ont ce noCategorie
			List<ArticleVendu> listArticlesVendus = DAOFactory.getArticleDAO().selectByNoCategorie(noCategorie);
			//supprimer toutes les enchères qui ont ce noUtilisateur ou concernent aux articles ci-dessus
			//Et supprimer le retrait des articles ci-dessus
			for (ArticleVendu articleVendu : listArticlesVendus) {
				DAOFactory.getEnchereDAO().deleteByNoArticle(articleVendu.getNoArticle());
				DAOFactory.getRetraitDAO().deleteByNoArticle(articleVendu.getNoArticle());
			}
			//supprimer toutes les articles  qui ont ce noUtilisateur
			DAOFactory.getArticleDAO().deleteByNoCategorie(noCategorie);
			stmt.executeUpdate(); 
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la suppression d'une catégorie");
		}
		
	}
	@Override
	public void updateByNoCategorie(Integer noCategorie, String new_libelle) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_BY_ID);
			stmt.setString(1,  new_libelle)  ;
			stmt.setInt(2,  noCategorie)  ;
			stmt.executeUpdate(); 
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification d'une catégorie");
		}
		
	}
	
	@Override
	public List<Categorie> getAll() throws DALException {
		List<Categorie> result = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				 
				result.add(categorie);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des categories");
		}
		return result;
	} 
	
	public Categorie selectCategorieByLibelle(String libelle)  throws DALException {
		Categorie categorie = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			stmt.setString(1, libelle  );
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(libelle);
			}
		} catch (Exception e) {
			throw new DALException("problème dans la selection des categories by libelle");
		}
		return categorie;
	}
 
	
	
	/*
	private String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String SELECT = "SELECT * FROM UTILISATEURS";
	public Utilisateur insert(Utilisateur utilisateur) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,  utilisateur.getPseudo())  ;  
			stmt.setString(2,  utilisateur.getNom())  ;
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			//stmt.setInt(10, 200);
			stmt.setInt(11, 0);
			int nbRows =stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
			}
	
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans l'insertion d'un utilisateur");
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> getAll() throws DALException {
		List<Utilisateur> result = new ArrayList<Utilisateur>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
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
				
				
				result.add(utilisateur);
			}
		} catch (Exception e) {
			throw new DALException("Couche DAL - Problème dans la selection des utilisateurs");
		}
		return result;
	}
	*/
	

}
