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



public class UtilisateurDAOImpl implements UtilisateurDAO {
	private String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String SELECT = "SELECT * FROM UTILISATEURS";
	private String SELECT_ONE_UTILISATEUR = "select * from UTILISATEURS where no_utilisateur=?";
	private String UPDATE_PSEUDO = "UPDATE UTILISATEURS  SET pseudo =? WHERE no_utilisateur = ?";
	private String UPDATE_NOM = "UPDATE UTILISATEURS  SET nom =? WHERE no_utilisateur =?";
	private String UPDATE_PRENOM = "UPDATE UTILISATEURS  SET prenom =? WHERE no_utilisateur =?";
	private String UPDATE_EMAIL = "UPDATE UTILISATEURS  SET email =? WHERE no_utilisateur =?";
	private String UPDATE_TEL = "UPDATE UTILISATEURS  SET telephone =? WHERE no_utilisateur =?";
	private String UPDATE_RUE = "UPDATE UTILISATEURS  SET rue =? WHERE no_utilisateur =?";
	private String UPDATE_CODEPOSTAL= "UPDATE UTILISATEURS  SET code_postal =? WHERE no_utilisateur =?";
	private String UPDATE_VILLE = "UPDATE UTILISATEURS  SET ville =? WHERE no_utilisateur =?";
	private String UPDATE_MOTDEPASSE = "UPDATE UTILISATEURS  SET mot_de_passe =? where no_utilisateur =?";
	private String UPDATE_CREDIT = "UPDATE UTILISATEURS  SET credit =? where no_utilisateur =?";
	private String DELETE_BY_ID = "DELETE FROM UTILISATEURS WHERE no_utilisateur =?";

	public void deleteByNoUtilisateur(Integer noUtilisateur) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_ID);
			stmt.setInt(1,  noUtilisateur)  ;
			//retirer les articles dans ArticleVendu qui ont ce noUtilisateur
			List<ArticleVendu> listArticlesVendus = DAOFactory.getArticleDAO().selectByNoUtilisateur(noUtilisateur);
			//supprimer toutes les enchères qui ont ce noUtilisateur ou concernent aux articles ci-dessus
			//Et supprimer le retrait des articles ci-dessus
			for (ArticleVendu articleVendu : listArticlesVendus) {
				DAOFactory.getEnchereDAO().deleteByNoUtilisateurNoArticle(noUtilisateur, articleVendu.getNoArticle());
				DAOFactory.getRetraitDAO().deleteByNoArticle(articleVendu.getNoArticle());
			}
			//supprimer toutes les articles  qui ont ce noUtilisateur
			DAOFactory.getArticleDAO().deleteByNoUtilisateur(noUtilisateur);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la suppression d'un utilisateur");
		}

	}

	public void updatePseudo(Integer noUtilisateur, String new_pseudo) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_PSEUDO);
			stmt.setString(1, new_pseudo)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de pseudo d'un utilisateur");
		}

	}
	public void updateNom(Integer noUtilisateur, String new_nom) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_NOM);
			stmt.setString(1, new_nom)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de nom d'un utilisateur");
		}

	}
	public void updatePrenom(Integer noUtilisateur, String new_prenom) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_PRENOM);
			stmt.setString(1, new_prenom)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de prénom d'un utilisateur");
		}

	}
	public void updateEmail(Integer noUtilisateur, String new_email) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_EMAIL);
			stmt.setString(1, new_email)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification d'email d'un utilisateur");
		}

	}
	public void updateTelephone(Integer noUtilisateur, String new_te) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_TEL);
			stmt.setString(1, new_te)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de téléphone d'un utilisateur");
		}

	}
	public void updateRue(Integer noUtilisateur, String new_rue) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_RUE);
			stmt.setString(1, new_rue)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de rue d'un utilisateur");
		}

	}
	public void updateCodePostal(Integer noUtilisateur, String new_codePostal) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_CODEPOSTAL);
			stmt.setString(1, new_codePostal)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de code postal d'un utilisateur");
		}

	}
	public void updateVille(Integer noUtilisateur, String new_ville) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_VILLE);
			stmt.setString(1, new_ville)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de ville d'un utilisateur");
		}

	}
	public void updateMotDePasse(Integer noUtilisateur, String new_motDePasse) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_MOTDEPASSE);
			stmt.setString(1, new_motDePasse)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de mot de passe d'un utilisateur");
		}

	}
	
	public void updateCredit(Integer noUtilisateur, Integer credit) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_CREDIT);
			stmt.setInt(1, credit)  ;
			stmt.setInt(2,  noUtilisateur)  ;
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Couche DAL - problème dans la modification de mot de passe d'un utilisateur");
		}

	}

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

	@Override
	public Utilisateur getUtilisateur(Integer idUtilisateur) throws DALException {
		// TODO Auto-generated method stub
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Utilisateur utilisateur = new Utilisateur();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ONE_UTILISATEUR);
			stmt.setInt(1, idUtilisateur);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
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


				
			}
			return utilisateur;
		} catch (Exception e) {
			System.out.println(e);
			throw new DALException("Couche DAL - Problème dans la selection des utilisateurs");
		}
	}


}
