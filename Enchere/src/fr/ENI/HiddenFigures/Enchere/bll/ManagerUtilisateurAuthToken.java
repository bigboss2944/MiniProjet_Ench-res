package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.UtilisateurAuthToken;

public interface ManagerUtilisateurAuthToken {
	UtilisateurAuthToken addutilisateurAuthToken(UtilisateurAuthToken utilisateurAuthToken) throws BLLException;
	List<UtilisateurAuthToken> getListUtilisateurAuthToken();
	 UtilisateurAuthToken findBySelector(String selector) ;
	 void modifier(UtilisateurAuthToken new_utilisateurAuthToken) throws BLLException ;

}
