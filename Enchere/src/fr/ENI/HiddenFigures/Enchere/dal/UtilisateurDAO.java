package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;


public interface UtilisateurDAO {
	Utilisateur insert(Utilisateur utilisateur) throws DALException;
	List<Utilisateur> getAll() throws DALException ;
	Utilisateur getUtilisateur(Integer idUtilisateur) throws DALException ;
	void updatePseudo(Integer noUtilisateur, String new_pseudo) throws DALException;
	void updateNom(Integer noUtilisateur, String new_nom) throws DALException;
	void updatePrenom(Integer noUtilisateur, String new_prenom) throws DALException;
	void updateEmail(Integer noUtilisateur, String new_email) throws DALException;
	 void updateTelephone(Integer noUtilisateur, String new_te) throws DALException;
	 void updateRue(Integer noUtilisateur, String new_rue) throws DALException;
	 void updateCodePostal(Integer noUtilisateur, String new_codePostal) throws DALException;
	 void updateVille(Integer noUtilisateur, String new_ville) throws DALException;
	 void updateMotDePasse(Integer noUtilisateur, String new_motDePasse) throws DALException;
	 void deleteByNoUtilisateur(Integer noUtilisateur) throws DALException;
}
