package fr.ENI.HiddenFigures.Enchere.test;

import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthToken;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthTokenSingl;
import fr.ENI.HiddenFigures.Enchere.bo.UtilisateurAuthToken;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;

public class TestToken {

	public static void main(String[] args) {
		ManagerUtilisateurAuthToken managerUtilisateurAuthToken = ManagerUtilisateurAuthTokenSingl.getInstance();
		UtilisateurAuthToken token = managerUtilisateurAuthToken.findBySelector("8S77RcMc3SoK");
//		try {
//			System.out.println(DAOFactory.getTokenDAO().getAll());
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
