package fr.ENI.HiddenFigures.Enchere.bll;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;


public interface ManagerUtilisateurs {
	Utilisateur addUtilisateur(Utilisateur utilisateur) throws BLLException;
	void pseudoEstUnique(Utilisateur utilisateur) throws BLLException;
	void emailEstUnique(Utilisateur utilisateur) throws BLLException;
	void pseudoContientQueAlphanumeriques(Utilisateur utilisateur) throws BLLException;
	void lesAutresContraints(Utilisateur utilisateur) throws BLLException;
	void verificationTelephone(Utilisateur utilisateur) throws BLLException;



}
