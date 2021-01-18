package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.dal.ArticleVenduDAO;
import fr.ENI.HiddenFigures.Enchere.dal.CategorieDAO;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.EnchereDAO;
import fr.ENI.HiddenFigures.Enchere.dal.UtilisateurDAO;

public class ManagerEncheresImpl implements ManagerEncheres {
	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	ArticleVenduDAO articleDAO= DAOFactory.getArticleDAO();
	UtilisateurDAO utilisateurDAO= DAOFactory.getUtilisateurDAO();
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
		try {
			ArticleVendu article = articleDAO.getArticleVendu(enchere.getNo_article());
			Utilisateur utilisateur = utilisateurDAO.getUtilisateur(enchere.getNo_utilisateur());
			if(enchere.getMontant_enchere()<EncherePlusHaute()) {
				throw new BLLException("Couche BLL - Enchere est inférieur à l'enchère la plus haute");
			}
			else if(enchere.getNo_utilisateur()==article.getNoUtilisateur()) {
				throw new BLLException("Couche BLL - Le vendeur ne peut enchérir sur le produit qu'il vend");
			}
			else if(enchere.getMontant_enchere()>utilisateur.getCredit()) {
				throw new BLLException("Couche BLL - L'enchérisseur ne peut proposer d'enchère supérieur à son crédit");
			}
			else if(enchere.getNo_utilisateur()<article.getPrixVente()) {
				throw new BLLException("Couche BLL - L'enchérisseur ne peut proposer de prix inférieur au prix de vente");
			}
			else {
				return true;
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}


	@Override
	public Integer EncherePlusHaute() throws BLLException {
		// TODO Auto-generated method stub
		Integer max=25;
		try {
			listEncheres = enchereDAO.getAll();
			for (Enchere enchere : listEncheres) {
				if(enchere.getMontant_enchere()>max) {
					max=enchere.getMontant_enchere();
				}
			}
			
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur lors de la récupération de la liste d'Enchères");
		}
		return max;
	}
	
	

}
