package fr.ENI.HiddenFigures.Enchere.bll;

public class ManagerArticleVendusSingl {
	private static ManagerArticleVendus  instance;

	public static ManagerArticleVendus  getInstance() {
		if(instance==null){
			instance = new ManagerArticleVendusImpl();
		}
		return instance;	
	}
}
