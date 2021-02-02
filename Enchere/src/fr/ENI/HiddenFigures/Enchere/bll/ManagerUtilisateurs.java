package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;
import java.util.Map;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
 


public interface ManagerUtilisateurs {
	Utilisateur addUtilisateur(Utilisateur utilisateur) throws BLLException;
	List<Utilisateur> getUtilisateur();
	void pseudoEstUnique(Utilisateur utilisateur) throws BLLException;
	void emailEstUnique(Utilisateur utilisateur) throws BLLException;
	void pseudoContientQueAlphanumeriques(Utilisateur utilisateur) throws BLLException;
	void lesAutresContraints(Utilisateur utilisateur) throws BLLException;
	void verificationTelephone(Utilisateur utilisateur) throws BLLException;
	Map<Integer, Utilisateur> rechercherUtilisateurParLoginPassword(String login, String password) throws BLLException;
	Utilisateur rechercherUtilisateurParNoUtilisateur(Integer noUtilisateur) throws BLLException;
	void modifierPseudo(Integer noUtilisateur, String new_pseudo) throws BLLException;
	void modifierNom(Integer noUtilisateur, String new_nom) throws BLLException;
	void modifierMotDePasse(Integer noUtilisateur, String new_motDePasse) throws BLLException;
	void modifierVille(Integer noUtilisateur, String new_ville) throws BLLException;
	void modifierCodePostal(Integer noUtilisateur, String new_codePostal) throws BLLException;
	void modifierRue(Integer noUtilisateur, String new_rue) throws BLLException;
	void modifierTelephone(Integer noUtilisateur, String new_telephone) throws BLLException;
	void modifierEmail(Integer noUtilisateur, String new_email) throws BLLException;
	void modifierPrenom(Integer noUtilisateur, String new_prenom) throws BLLException;
	void supprimerUtilisateurParNoUtilisateur(Integer noUtilisateur) throws BLLException;
	void modifierCredit(Integer noUtilisateur, Integer newCredit) throws BLLException;
	void modifierCreditThuy(Integer noUtilisateur, Integer newCredit) throws BLLException;
	List<Utilisateur> getListUtilisateursIncludeCompteDesactive() throws BLLException;
	void modifierEtatCompte(Integer noUtilisateur, String new_etatCompte) throws BLLException;
	Utilisateur rechercherUtilisateurParEmail(String email) throws BLLException;
}
