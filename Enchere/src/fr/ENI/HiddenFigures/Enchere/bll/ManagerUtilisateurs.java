package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.Map;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;



public interface ManagerUtilisateurs {
	Utilisateur addUtilisateur(Utilisateur utilisateur) throws BLLException;
	void pseudoEstUnique(Utilisateur utilisateur) throws BLLException;
	void emailEstUnique(Utilisateur utilisateur) throws BLLException;
	void pseudoContientQueAlphanumeriques(Utilisateur utilisateur) throws BLLException;
	void lesAutresContraints(Utilisateur utilisateur) throws BLLException;
	void verificationTelephone(Utilisateur utilisateur) throws BLLException;
	Map<Integer, Utilisateur> rechercherUtilisateurParLoginPassword(String login, String password) throws BLLException;
	Utilisateur rechercherUtilisateurParNoUtilisateur(Integer noUtilisateur) throws BLLException;


}
