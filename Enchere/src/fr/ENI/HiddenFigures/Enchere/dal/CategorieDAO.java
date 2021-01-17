package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;

 
 
 


public interface CategorieDAO {
	List<Categorie> getAll() throws DALException;
	public Categorie selectCategorieByLibelle(String libelle)  throws DALException ;
}
