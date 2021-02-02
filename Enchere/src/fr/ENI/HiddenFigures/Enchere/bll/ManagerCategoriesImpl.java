package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Retrait;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.dal.CategorieDAO;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;


 

public class ManagerCategoriesImpl implements ManagerCategories{
	CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	 
	
	List<Categorie> listCategories = new ArrayList<>();
	public ManagerCategoriesImpl() {
		try {
			listCategories = categorieDAO.getAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Categorie addCategorie(Categorie categorie) throws BLLException{
		try {
			categorie = categorieDAO.insert(categorie);
			listCategories.add(categorie);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - problème dans l'insertion d'une catégorie");
		}
		return categorie;
	}

	public List<Categorie> getCategories()   {
		return listCategories;
	}
	public  Categorie getCategorieByLibelle(String libelle) throws BLLException   {
		Categorie categorie;
		try {
			categorie = categorieDAO.selectCategorieByLibelle(libelle);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur seclection Categorie par libelle");
		}
		return categorie;
	}
	
	public void supprimerCategorieParNoCategorie(Integer noCategorie) throws BLLException {
		try {
			categorieDAO.deleteByNoCategorie(noCategorie);
			listCategories = categorieDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la suppression une catégorie par noCategorie");
		}

	}
	@Override
	public void modifierCategorieParNoCategorie(Integer noCategorie,String new_libelle) throws BLLException {
		try {
			categorieDAO.updateByNoCategorie(noCategorie, new_libelle);
			listCategories = categorieDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL-Problème de la modification une catégorie par noCategorie");
		}

	}
	@Override
	public void libelleEstUnique(Categorie categorie) throws BLLException {
		boolean trouve = false;
		for (Categorie categorie2 : listCategories) {
			if (categorie2.getLibelle().equals(categorie.getLibelle())) {
				trouve = true;
				break;
			}
		}
		if (trouve) {
			throw new BLLException("Cette libelle est déjà utilisé ");
		}
	}
	@Override
	public Categorie getCategorieParNoCategorie(Integer noCategorie) throws BLLException {
		Categorie categorie = new Categorie();
		if(listCategories!=null) {
			for (Categorie categorie2 : listCategories) {
				if(categorie2.getNoCategorie() ==noCategorie ) {
					categorie = categorie2;
				}
			}
		}
		return categorie;

	}
	

}
