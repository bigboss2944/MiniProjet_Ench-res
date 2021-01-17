package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
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
	
	
	

}
