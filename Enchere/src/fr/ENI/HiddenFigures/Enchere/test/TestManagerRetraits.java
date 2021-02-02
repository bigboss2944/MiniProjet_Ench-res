package fr.ENI.HiddenFigures.Enchere.test;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerRetrait;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerRetraitSingl;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.RetraitDAO;

public class TestManagerRetraits {
	 static ManagerRetrait managerRetraits = ManagerRetraitSingl.getInstance();
	 static RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
	public static void main(String[] args) {
		try {
			DAOFactory.getRetraitDAO().deleteByNoArticle(10);
			System.out.println("bon");
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
