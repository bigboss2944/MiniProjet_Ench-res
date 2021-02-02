package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;

 

public interface ManagerCategories {
	List<Categorie> getCategories();
	 void supprimerCategorieParNoCategorie(Integer noCategorie) throws BLLException;
	 Categorie addCategorie(Categorie categorie) throws BLLException;
	 void libelleEstUnique(Categorie categorie) throws BLLException;
	 void modifierCategorieParNoCategorie(Integer noCategorie,String new_libelle) throws BLLException;
	 Categorie getCategorieParNoCategorie(Integer noCategorie) throws BLLException;
}
