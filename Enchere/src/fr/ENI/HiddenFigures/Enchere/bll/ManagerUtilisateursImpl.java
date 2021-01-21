package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.UtilisateurDAO;

/**
 * @author tbui2020
 *
 */
public class ManagerUtilisateursImpl implements ManagerUtilisateurs {
	UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	String str2 = "1234567890";

	List<Utilisateur> listUtilisateurs = new ArrayList<>();

	public ManagerUtilisateursImpl() {
		try {
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			utilisateur = utilisateurDAO.insert(utilisateur);
			listUtilisateurs.add(utilisateur);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur ajouter l'utilisateur");
		}
		return utilisateur;

	}

	@Override
	public List<Utilisateur> getUtilisateur() {
		return listUtilisateurs;

	}
	/*
	 * Les contraintes dans 1003 1. Le pseudo et l'email doivent être unique sur
	 * toute la plateforme 2. Le pseudo n'accepte que des caractères alphanumériques
	 * 3. Les autres: le code postal ne contient que les chiffres, le nom, prénom et
	 * ville ne contiennent pas de chiffre le mail doit contenire @ et . tous les
	 * attributs sont obligatoires sauf telephone 4. Le numéro de telephone contient
	 * que 10 charactères
	 *
	 *
	 */

	// 1
	public void pseudoEstUnique(Utilisateur utilisateur) throws BLLException {
		boolean trouve = false;
		for (Utilisateur utilisateur2 : listUtilisateurs) {
			if (utilisateur2.getPseudo().equals(utilisateur.getPseudo())) {
				trouve = true;
				break;
			}
		}
		if (trouve) {
			throw new BLLException("Ce pseudo est déjà utilisé ");
		}
	}

	public void emailEstUnique(Utilisateur utilisateur) throws BLLException {
		boolean trouve = false;
		for (Utilisateur utilisateur2 : listUtilisateurs) {
			if (utilisateur2.getEmail().equals(utilisateur.getEmail())) {
				trouve = true;
				break;
			}
		}
		if (trouve) {
			throw new BLLException("Cet email est déjà utilisé ");
		}
	}

	// 2
	public void pseudoContientQueAlphanumeriques(Utilisateur utilisateur) throws BLLException {
		String str = "azertyuiopqsdfghjklmwxcvbn1234567890";
		String pseudoStr = utilisateur.getPseudo().toLowerCase();
		boolean existCharSpecial = false;
		for (int i = 0; i < pseudoStr.length(); i++) {
			if (str.indexOf(pseudoStr.charAt(i)) < 0) {
				existCharSpecial = true;
				break;
			}

		}

		if (existCharSpecial) {
			throw new BLLException("Le pseudo ne contient que des caractères alphanumériques  ");
		}
	}

	// 3
	public void lesAutresContraints(Utilisateur utilisateur) throws BLLException {
		String str = "azertyuiopqsdfghjklmwxcvbn1234567890";
		// String str2 ="1234567890";
		// le code postal ne contient que les chiffres
		String codePostal = utilisateur.getCodePostal();
		boolean existCharNonChiffre = false;
		for (int i = 0; i < codePostal.length(); i++) {
			if (str2.indexOf(codePostal.charAt(i)) < 0) {
				existCharNonChiffre = true;
				break;
			}

		}

		if (existCharNonChiffre) {
			throw new BLLException("Le code postal ne doit contenir que les chiffres  ");
		}
		// le nom, prénom et ville ne contiennent pas de chiffre
		String nom = utilisateur.getNom();
		String prenom = utilisateur.getNom();
		String ville = utilisateur.getNom();
		boolean existChiffre = false;
		for (int i = 0; i < nom.length(); i++) {
			if (str2.indexOf(nom.charAt(i)) >= 0) {
				existChiffre = true;
				break;
			}

		}

		if (existChiffre) {
			throw new BLLException("Le nom de famille  ne  contient pas de chiffre  ");
		}

		for (int i = 0; i < prenom.length(); i++) {
			if (str2.indexOf(prenom.charAt(i)) >= 0) {
				existChiffre = true;
				break;
			}

		}

		if (existChiffre) {
			throw new BLLException("Le prenom  ne  contient pas de chiffre  ");
		}

		for (int i = 0; i < ville.length(); i++) {
			if (str2.indexOf(ville.charAt(i)) >= 0) {
				existChiffre = true;
				break;
			}

		}

		if (existChiffre) {
			throw new BLLException("Le nom de ville  ne  contient pas de chiffre  ");
		}

		// le mail doit contenir @ et .
		String email = utilisateur.getEmail();
		boolean emailNonConforme = false;
		if (email.indexOf('@') < 0 || email.indexOf('.') < 0) {
			emailNonConforme = true;
		}

		if (emailNonConforme) {
			throw new BLLException("le mail doit contenir @ et .  ");
		}

		// tous les attributs sont obligatoires sauf telephone
		String rue = utilisateur.getRue();
		String pseudo = utilisateur.getPseudo();
		String motDePasse = utilisateur.getMotDePasse();
		boolean attributsUtilisateurNull = false;
		if (pseudo == null || nom == null || prenom == null || email == null || rue == null || codePostal == null
				|| ville == null || motDePasse == null || "".equals(pseudo.trim()) || "".equals(nom.trim())
				|| "".equals(prenom.trim()) || "".equals(email.trim()) || "".equals(rue.trim())
				|| "".equals(codePostal.trim()) || "".equals(motDePasse.trim())) {
			attributsUtilisateurNull = true;
		}
		if (attributsUtilisateurNull) {
			throw new BLLException("Tous les attributs sont obligatoires sauf telephone  ");
		}

	}

	// 4. Le numéro de telephone contient que 10 chiffres
	public void verificationTelephone(Utilisateur utilisateur) throws BLLException {
		String telephone = utilisateur.getTelephone();
		boolean pasBonTelephone = false;
		if (telephone != null) {
			for (int i = 0; i < telephone.length(); i++) {
				if (str2.indexOf(telephone.charAt(i)) < 0) {
					pasBonTelephone = true;
					break;
				}
			}
			if (telephone.length() != 10 || pasBonTelephone) {
				throw new BLLException("Le numéro de telephone contient que 10 chiffres  ");
			}
		}

	}

	// Pour l'écran de connexion
	// Rechercher un utilisateur par login (pseudo ou email) et password
	@Override
	public Map<Integer, Utilisateur> rechercherUtilisateurParLoginPassword(String login, String password)
			throws BLLException {

		Map<Integer, Utilisateur> mapUtilisateur = new HashMap<Integer, Utilisateur>();
		boolean trouve = false;

		for (Utilisateur user : listUtilisateurs) {
			if ((user.getPseudo().equals(login) || user.getEmail().toLowerCase().equals(login.toLowerCase()))
					&& user.getMotDePasse().equals(password)) {
				trouve = true;
				mapUtilisateur.put(user.getNoUtilisateur(), user);
				break;
			}
		}

		if (!trouve) {
			throw new BLLException("Il n'existe pas cet utilisateur dans BDD");
		}
		return mapUtilisateur;

	}

	// Rechercher un utilisateur par noUtilisateur
	@Override
	public Utilisateur rechercherUtilisateurParNoUtilisateur(Integer noUtilisateur) throws BLLException {

		Utilisateur utilisateur = new Utilisateur();
		boolean trouve = false;

		for (Utilisateur user : listUtilisateurs) {
			if (user.getNoUtilisateur() == noUtilisateur) {
				trouve = true;
				utilisateur = user;
				break;
			}
		}

		if (!trouve) {
			throw new BLLException("Il n'existe pas cet utilisateur dans BDD");
		}
		return utilisateur;

	}

	// Modifier le pseudo
	public void modifierPseudo(Integer noUtilisateur, String new_pseudo) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_pseudo);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de pseudo");
		}

	}

	// Modifier le nom

	@Override
	public void modifierNom(Integer noUtilisateur, String new_nom) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_nom);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de nom");
		}

	}

	// Modifier le prenom

	@Override
	public void modifierPrenom(Integer noUtilisateur, String new_prenom) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_prenom);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de prénom");
		}

	}

	// Modifier l'email

	@Override
	public void modifierEmail(Integer noUtilisateur, String new_email) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_email);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification d'email");
		}

	}

	// Modifier le téléphone

	@Override
	public void modifierTelephone(Integer noUtilisateur, String new_telephone) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_telephone);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de téléphone");
		}

	}

	// Modifier le rue

	@Override
	public void modifierRue(Integer noUtilisateur, String new_rue) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_rue);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de rue");
		}

	}

	// Modifier le code postal

	@Override
	public void modifierCodePostal(Integer noUtilisateur, String new_codePostal) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_codePostal);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de code postal");
		}

	}

	// Modifier la ville
	@Override

	public void modifierVille(Integer noUtilisateur, String new_ville) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_ville);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de ville");
		}

	}

	// Modifier le mot de passe
	@Override

	public void modifierMotDePasse(Integer noUtilisateur, String new_motDePasse) throws BLLException {
		try {
			utilisateurDAO.updatePseudo(noUtilisateur, new_motDePasse);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de mot de passe");
		}

	}

	@Override
	public void modifierCredit(Integer noUtilisateur, Integer credit) throws BLLException {
		try {
			for(Utilisateur utilisateur:listUtilisateurs) {
				if((utilisateur.getCredit()<credit)&&(utilisateur.getNoUtilisateur()==noUtilisateur)){
					throw new BLLException("Couche BLL-L'enchère est supérieur au crédit de l'enchérisseur");
				}
				else if(utilisateur.getNoUtilisateur()==noUtilisateur){
					utilisateurDAO.updateCredit(noUtilisateur, utilisateur.getCredit()-credit);
					listUtilisateurs = utilisateurDAO.getAll();
				}
			}


		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification de mot de passe");
		}

	}

	//Supprimer un compte par id
	public void supprimerUtilisateurParNoUtilisateur(Integer noUtilisateur) throws BLLException {
		try {
			utilisateurDAO.deleteByNoUtilisateur(noUtilisateur);
			listUtilisateurs = utilisateurDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la suppression un compte par noUtilisateur");
		}

	}

	@Override
	public Utilisateur getUtilisateurById(Integer idUser) {
		// TODO Auto-generated method stub
		for(Utilisateur utilisateur : listUtilisateurs) {
			if (utilisateur.getNoUtilisateur()==idUser) {
				return utilisateur;
			}
		}
		return null;
	}




}
