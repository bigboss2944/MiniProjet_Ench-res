package fr.ENI.HiddenFigures.Enchere.bll;

public class ManagerUtilisateurAuthTokenSingl {
	private static ManagerUtilisateurAuthToken  instance;

	public static ManagerUtilisateurAuthToken  getInstance() {
		if(instance==null){
			instance = new ManagerUtilisateurAuthTokenImpl();
		}
		return instance;	
	}

}
