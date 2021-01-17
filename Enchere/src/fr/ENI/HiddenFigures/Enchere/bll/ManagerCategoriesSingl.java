package fr.ENI.HiddenFigures.Enchere.bll;

public class ManagerCategoriesSingl {
	private static  ManagerCategories  instance;

	public static  ManagerCategories  getInstance() {
		if(instance==null){
			instance = new  ManagerCategoriesImpl();
		}
		return instance;	
	}


}
