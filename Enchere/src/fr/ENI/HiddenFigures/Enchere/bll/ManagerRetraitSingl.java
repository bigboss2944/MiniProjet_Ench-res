package fr.ENI.HiddenFigures.Enchere.bll;

public class ManagerRetraitSingl {
	private static  ManagerRetrait  instance;

	public static  ManagerRetrait  getInstance() {
		if(instance==null){
			instance = new  ManagerRetraitImpl();
		}
		return instance;	
	}


}
