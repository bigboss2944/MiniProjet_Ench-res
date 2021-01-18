package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.dal.ArticleVenduDAO;
import fr.ENI.HiddenFigures.Enchere.dal.CategorieDAO;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.EnchereDAO;

public class ManagerEncheresImpl implements ManagerEncheres {
	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	ArticleVenduDAO articleDAO= DAOFactory.getArticleDAO();
	List<Enchere> listEncheres = new ArrayList<>();
	public ManagerEncheresImpl() {
		try {
			listEncheres = enchereDAO.getAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Enchere addEnchere(Enchere enchere) throws BLLException,EnchereException {
		// TODO Auto-generated method stub
		try {
			if(EnchereOK(enchere)) {
				enchere = enchereDAO.insert(enchere);
				listEncheres.add(enchere);
			}
			else {
				throw new EnchereException("Couche BLL - Erreur à l'ajout de l'enchère");
			}
			
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur à l'ajout de l'Enchère dans la base de données");
		}
		return enchere;
	}

	@Override
	public Enchere updateEnchere(Enchere enchere) throws BLLException,EnchereException {
		// TODO Auto-generated method stub
		try {
			if(EnchereOK(enchere)) {
				enchere = enchereDAO.update(enchere);
				listEncheres.add(enchere);
			}
			else {
				throw new EnchereException("Couche BLL - Erreur à l'ajout de l'enchère");
			}
			
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur à l'ajout de l'Enchère dans la base de données");
		}
		return enchere;
	}

	@Override
	public List<Enchere> getLstEnchere() throws BLLException {
		// TODO Auto-generated method stub
		try {
			listEncheres = enchereDAO.getAll();
			
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur lors de la récupération de la liste d'Enchères");
		}
		return listEncheres;
	}

	@Override
	public Enchere getEnchere(Integer idEnchere) throws BLLException {
		// TODO Auto-generated method stub
		try {
			listEncheres = enchereDAO.getAll();
			for (Enchere enchere : listEncheres) {
				if(enchere.getNo_enchere()==idEnchere) {
					return enchere;
				}
			}
			
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur lors de la récupération de la liste d'Enchères");
		}
		return null;
	}

	@Override
	public Enchere deleteEnchere(Integer idEnchere) throws BLLException {
		// TODO Auto-generated method stub
		try {
			listEncheres = enchereDAO.getAll();
			for (Enchere enchere : listEncheres) {
				if(enchere.getNo_enchere()==idEnchere) {
					enchereDAO.deleteEnchere(enchere.getNo_enchere());
					int i=listEncheres.indexOf(enchere.getNo_enchere());
					listEncheres.remove(i);
				}
			}
			
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur lors de la récupération de la liste d'Enchères");
		}
		return null;
	}

	@Override
	public boolean EnchereOK(Enchere enchere) throws BLLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Integer EncherePlusHaute() throws BLLException {
		// TODO Auto-generated method stub
		try {
			listEncheres = enchereDAO.getAll();
			for (Enchere enchere : listEncheres) {
				if(enchere.getNo_enchere()==idEnchere) {
					enchereDAO.deleteEnchere(enchere.getNo_enchere());
					int i=listEncheres.indexOf(enchere.getNo_enchere());
					listEncheres.remove(i);
				}
			}
			
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur lors de la récupération de la liste d'Enchères");
		}
		return null;
	}
	
	

}
