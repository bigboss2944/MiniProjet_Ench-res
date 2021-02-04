package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.bo.UtilisateurAuthToken;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.UtilisateurAuthTokenDAO;
import fr.ENI.HiddenFigures.Enchere.dal.UtilisateurDAO;

public class ManagerUtilisateurAuthTokenImpl implements ManagerUtilisateurAuthToken {
	UtilisateurAuthTokenDAO utilisateurAuthTokenDAO = DAOFactory.getTokenDAO();
	 

	List<UtilisateurAuthToken> listUtilisateurAuthTokens = new ArrayList<>();

	public  ManagerUtilisateurAuthTokenImpl() {
		try {
			listUtilisateurAuthTokens = utilisateurAuthTokenDAO.getAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public UtilisateurAuthToken addutilisateurAuthToken(UtilisateurAuthToken utilisateurAuthToken) throws BLLException {
		try {
			utilisateurAuthToken = utilisateurAuthTokenDAO.insert(utilisateurAuthToken);
			listUtilisateurAuthTokens.add(utilisateurAuthToken);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur ajouter l'UtilisateurAuthToken");
		}
		return utilisateurAuthToken;

	}
	
	@Override
	public UtilisateurAuthToken findBySelector(String selector) {
		UtilisateurAuthToken utilisateurAuthToken =new UtilisateurAuthToken();
		if(listUtilisateurAuthTokens !=null) {
			for (UtilisateurAuthToken utilisateurAuthToken2 : listUtilisateurAuthTokens) {
				
				
				if(utilisateurAuthToken2.getSelector().equals(selector)) {
					utilisateurAuthToken =utilisateurAuthToken2;
				
				}
			}
		}
		return utilisateurAuthToken;
	}
	
	@Override
	public List<UtilisateurAuthToken> getListUtilisateurAuthToken() {
		 
		return listUtilisateurAuthTokens;
	}
	
	@Override
	public void modifier(UtilisateurAuthToken new_utilisateurAuthToken) throws BLLException {
		 
		try {
			utilisateurAuthTokenDAO.update(new_utilisateurAuthToken);
			listUtilisateurAuthTokens = utilisateurAuthTokenDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur modifier l'UtilisateurAuthToken");
		}
		
	}
	

}
