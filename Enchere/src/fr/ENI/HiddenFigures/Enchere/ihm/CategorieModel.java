package fr.ENI.HiddenFigures.Enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;

 

 

public class CategorieModel {
	private List<Categorie> lstCategories = new ArrayList<Categorie>();

	public CategorieModel() {
		// TODO Auto-generated constructor stub
	}

	public List<Categorie> getLstCategories() {
		return lstCategories;
	}

	public void setLstCategories(List<Categorie> lstCategories) {
		this.lstCategories = lstCategories;
	}
	

}
