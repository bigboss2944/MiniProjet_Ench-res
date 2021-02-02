package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;

 
 
 


public interface CategorieDAO {
	List<Categorie> getAll() throws DALException;
	 Categorie selectCategorieByLibelle(String libelle)  throws DALException ;
	void deleteByNoCategorie(Integer noCategorie) throws DALException;
	Categorie insert(Categorie categorie) throws DALException;
	void updateByNoCategorie(Integer noCategorie, String new_libelle) throws DALException;
}
