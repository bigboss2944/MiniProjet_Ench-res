package fr.ENI.HiddenFigures.Enchere.test;

import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.UtilisateurDAO;

public class TestUtilisateurDAO {
	static UtilisateurDAO utilisateurDAO= DAOFactory.getUtilisateurDAO();
	public static void main(String[] args) {
		try {
			utilisateurDAO.getUtilisateur(2);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
