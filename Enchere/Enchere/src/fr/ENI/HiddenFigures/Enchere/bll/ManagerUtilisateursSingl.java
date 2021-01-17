package fr.ENI.HiddenFigures.Enchere.bll;

public class ManagerUtilisateursSingl {
	private static ManagerUtilisateurs  instance;

	public static ManagerUtilisateurs  getInstance() {
		if(instance==null){
			instance = new ManagerUtilisateursImpl();
		}
		return instance;	
	}

}
