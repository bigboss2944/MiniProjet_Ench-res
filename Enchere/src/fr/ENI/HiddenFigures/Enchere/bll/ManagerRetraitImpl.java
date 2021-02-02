package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Retrait;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.RetraitDAO;


 

public class ManagerRetraitImpl implements ManagerRetrait{
	RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
	 
	
	List<Retrait> listRetraits = new ArrayList<>();
	public ManagerRetraitImpl() {
		try {
			listRetraits = retraitDAO.getAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Retrait addRetrait(Retrait retrait) throws BLLException{
		try {
			retrait = retraitDAO.insert(retrait);
			listRetraits.add(retrait);
		} catch (DALException e) {
			throw new BLLException("Couche BLL - problème dans l'insertion d'un retrait");
		}
		return retrait;
	}
	@Override
	public Retrait modifierRetrait(Retrait retrait) throws BLLException{
		try {
			retrait = retraitDAO.update(retrait);
			listRetraits = retraitDAO.getAll();
		} catch (DALException e) {
			throw new BLLException("Couche BLL - problème dans UPDATE un retrait");
		}
		return retrait;
	}
	
	@Override
	public Retrait selectRetrait(Integer noArticle) throws BLLException{
		Retrait retrait = new Retrait();
		try {
			retrait = retraitDAO.selectByNoArticle(noArticle);;
		} catch (DALException e) {
			throw new BLLException("Couche BLL - problème dans la sélectiond' un retrait par noArticle");
		}
		return retrait;
	}
	 
	
	

}
