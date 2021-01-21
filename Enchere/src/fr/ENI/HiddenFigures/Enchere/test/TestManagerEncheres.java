package fr.ENI.HiddenFigures.Enchere.test;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bll.ManagerEncheres;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerEncheresSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public class TestManagerEncheres {
	private static  ManagerEncheres managerEncheres = ManagerEncheresSingl.getInstance(); 
	public static void main(String[] args) {
		System.out.println(managerEncheres.getLstEnchere());
		//System.out.println(managerEncheres.getLstEnchereOfUserById(2));
		System.out.println(managerEncheres.getLstEnchereOfHighestOffer());
		//System.out.println(managerEncheres.getLstEnchereOfHighestOfferOfUserById(2));
		
		
	}
}
