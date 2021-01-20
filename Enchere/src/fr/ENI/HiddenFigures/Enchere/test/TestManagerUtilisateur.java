package fr.ENI.HiddenFigures.Enchere.test;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;

public class TestManagerUtilisateur {
	private static  ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance(); 
	public static void main(String[] args) {
		try {
			managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(23);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
