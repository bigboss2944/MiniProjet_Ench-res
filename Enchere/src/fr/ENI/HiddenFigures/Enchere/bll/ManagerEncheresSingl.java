package fr.ENI.HiddenFigures.Enchere.bll;

public class ManagerEncheresSingl {
	private static  ManagerEncheres  instance;

	public static  ManagerEncheres  getInstance() {
		if(instance==null){
			instance = new  ManagerEncheresImpl();
		}
		return instance;
	}

}
