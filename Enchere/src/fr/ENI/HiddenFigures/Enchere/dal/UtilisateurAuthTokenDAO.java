package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.UtilisateurAuthToken;

public interface UtilisateurAuthTokenDAO {
	 void deleteByNoUtilisateur(Integer noUtilisateur ) throws DALException;
	 UtilisateurAuthToken insert(UtilisateurAuthToken token) throws DALException;
	 List<UtilisateurAuthToken> getAll() throws DALException;
	 void update( UtilisateurAuthToken newToken) throws DALException ;
	 void deleteBySelector(String selector) throws DALException;

}
