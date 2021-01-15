package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

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
	String str2 ="1234567890";

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
	/*
	Les contraintes dans 1003
	1. Le pseudo et l'email doivent être unique sur toute la plateforme
	2. Le pseudo n'accepte que des caractères alphanumériques
	3. Les autres: le code postal ne contient que les chiffres,
					le nom, prénom et ville ne contiennent pas de chiffre
					le mail doit contenire @ et .
					tous les attributs sont obligatoires sauf telephone
	4. Le numéro de telephone contient que 10 charactères


	*/

	//1
	public void pseudoEstUnique(Utilisateur utilisateur) throws BLLException {
		boolean trouve =false;
		for (Utilisateur utilisateur2 : listUtilisateurs) {
			if(utilisateur2.getPseudo().equals(utilisateur.getPseudo()) ) {
				trouve = true;
				break;
			}
		}
		if(trouve) {
			throw new BLLException("Ce pseudo est déjà utilisé ");
		}
	}
	public void emailEstUnique(Utilisateur utilisateur) throws BLLException {
		boolean trouve =false;
		for (Utilisateur utilisateur2 : listUtilisateurs) {
			if(utilisateur2.getEmail().equals(utilisateur.getEmail()) ) {
				trouve = true;
				break;
			}
		}
		if(trouve) {
			throw new BLLException("Cet email est déjà utilisé ");
		}
	}

	//2
	public void pseudoContientQueAlphanumeriques(Utilisateur utilisateur) throws BLLException {
		String str ="azertyuiopqsdfghjklmwxcvbn1234567890";
		String pseudoStr = utilisateur.getPseudo().toLowerCase();
		boolean existCharSpecial =false;
		for (int i = 0; i < pseudoStr.length(); i++) {
			if (str.indexOf( pseudoStr.charAt(i))< 0) {
				existCharSpecial =true;
				break;
			}

		}

		if(existCharSpecial) {
			throw new BLLException("Le pseudo ne contient que des caractères alphanumériques  ");
		}
	}
	//3
	public void lesAutresContraints(Utilisateur utilisateur) throws BLLException {
		String str ="azertyuiopqsdfghjklmwxcvbn1234567890";
		//String str2 ="1234567890";
		//le code postal ne contient que les chiffres
		String codePostal = utilisateur.getCodePostal();
		boolean existCharNonChiffre =false;
		for (int i = 0; i < codePostal.length(); i++) {
			if (str2.indexOf( codePostal.charAt(i))< 0) {
				existCharNonChiffre =true;
				break;
			}

		}

		if(existCharNonChiffre) {
			throw new BLLException("Le code postal ne doit contenir que les chiffres  ");
		}
		//le nom, prénom et ville ne contiennent pas de chiffre
		String nom = utilisateur.getNom();
		String prenom = utilisateur.getNom();
		String ville = utilisateur.getNom();
		boolean existChiffre =false;
		for (int i = 0; i < nom.length(); i++) {
			if (str2.indexOf( nom.charAt(i))>= 0) {
				existChiffre =true;
				break;
			}

		}

		if(existChiffre) {
			throw new BLLException("Le nom de famille  ne  contient pas de chiffre  ");
		}

		for (int i = 0; i < prenom.length(); i++) {
			if (str2.indexOf( prenom.charAt(i))>= 0) {
				existChiffre =true;
				break;
			}

		}

		if(existChiffre) {
			throw new BLLException("Le prenom  ne  contient pas de chiffre  ");
		}

		for (int i = 0; i < ville.length(); i++) {
			if (str2.indexOf( ville.charAt(i))>= 0) {
				existChiffre =true;
				break;
			}

		}

		if(existChiffre) {
			throw new BLLException("Le nom de ville  ne  contient pas de chiffre  ");
		}

		//le mail doit contenir @ et .
		String email = utilisateur.getEmail();
		boolean emailNonConforme = false;
		if(email.indexOf('@') <0 || email.indexOf('.') <0) {
			emailNonConforme =true;
		}

		if(emailNonConforme) {
			throw new BLLException("le mail doit contenir @ et .  ");
		}

		//tous les attributs sont obligatoires sauf telephone
		String rue = utilisateur.getRue();
		String pseudo = utilisateur.getPseudo();
		String motDePasse= utilisateur.getMotDePasse();
		boolean attributsUtilisateurNull =false;
		if(pseudo==null|| nom ==null|| prenom==null||email==null||rue==null||codePostal==null||ville==null||motDePasse==null
				|| "".equals(pseudo.trim()) || "".equals(nom.trim()) || "".equals(prenom.trim()) || "".equals(email.trim()) || "".equals(rue.trim()) || "".equals(codePostal.trim())|| "".equals(motDePasse.trim() )) {
			attributsUtilisateurNull =true;
		}
		if(attributsUtilisateurNull) {
			throw new BLLException("Tous les attributs sont obligatoires sauf telephone  ");
		}


	}
	//4. Le numéro de telephone contient que 10 chiffres
	public void verificationTelephone(Utilisateur utilisateur) throws BLLException {
		String telephone = utilisateur.getTelephone();
		boolean pasBonTelephone= false;
		for (int i = 0; i < telephone.length(); i++) {
			if (str2.indexOf( telephone.charAt(i)) < 0) {
				pasBonTelephone =true;
				break;
			}
		}
		if (telephone.length() != 10  || pasBonTelephone  ) {
			throw new BLLException("Le numéro de telephone contient que 10 chiffres  ");
		}

	}




}
