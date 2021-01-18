package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;


public interface UtilisateurDAO {
	Utilisateur insert(Utilisateur utilisateur) throws DALException;
	List<Utilisateur> getAll() throws DALException ;
	Utilisateur getUtilisateur(Integer idUtilisateur) throws DALException ;
}
