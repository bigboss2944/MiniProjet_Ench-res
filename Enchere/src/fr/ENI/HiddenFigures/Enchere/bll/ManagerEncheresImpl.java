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
				throw new EnchereException("Couche BLL - Erreur � l'ajout de l'ench�re");
			}

		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur � l'ajout de l'Ench�re dans la base de donn�es");
		}
		return enchere;
	}

	
	@Override
	public Enchere updateEnchere(Enchere enchere) throws BLLException,EnchereException {
		// TODO Auto-generated method stub
		try {
			if(EnchereOK(enchere)) {
				enchere = enchereDAO.update(enchere);
				listEncheres = enchereDAO.getAll();
			}
			else {
				throw new EnchereException("Couche BLL - Erreur � l'ajout de l'ench�re");
			}

		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur � l'ajout de l'Ench�re dans la base de donn�es");
		}
		return enchere;
	}

	@Override
	public List<Enchere> getLstEnchere(){
		// TODO Auto-generated method stub
		return listEncheres;
	}

	@Override
	public Enchere getEnchere(Integer idEnchere){
		// TODO Auto-generated method stub
		for (Enchere enchere : listEncheres) {
			if(enchere.getNo_enchere()==idEnchere) {
				return enchere;
			}
		}
		return null;
	}

	@Override
	public Enchere deleteEnchere(Integer idEnchere) throws BLLException {
		// TODO Auto-generated method stub
		try {
			for (Enchere enchere : listEncheres) {
				if(enchere.getNo_enchere()==idEnchere) {
					enchereDAO.deleteEnchere(enchere.getNo_enchere());
					int i=listEncheres.indexOf(enchere.getNo_enchere());
					listEncheres.remove(i);
				}
			}
		} catch (DALException e) {
			throw new BLLException("Couche BLL - Erreur lors de la r�cup�ration de la liste d'Ench�res");
		}
		return null;
	}

	
	@Override
	public boolean EnchereOK(Enchere enchere) throws BLLException {
		// TODO Auto-generated method stub
		try {
			ArticleVendu article = articleDAO.getArticleVendu(enchere.getNo_article());
			Utilisateur utilisateur = utilisateurDAO.getUtilisateur(enchere.getNo_utilisateur());
			if(enchere.getMontant_enchere()<=EncherePlusHaute(article.getNoArticle())) {
				throw new BLLException("Couche BLL - Enchere est inf�rieur � l'ench�re la plus haute");
			}
			else if(enchere.getNo_utilisateur()==article.getNoUtilisateur()) {
				throw new BLLException("Couche BLL - Le vendeur ne peut ench�rir sur le produit qu'il vend");
			}
			else if(enchere.getMontant_enchere()>utilisateur.getCredit()) {
				throw new BLLException("Couche BLL - L'ench�risseur ne peut proposer d'ench�re sup�rieur � son cr�dit");
			}
			else if(enchere.getMontant_enchere()<article.getMiseAprix()) {
				throw new BLLException("Couche BLL - L'ench�risseur ne peut proposer de prix inf�rieur � la mise � prix");
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
	public Integer EncherePlusHaute(Integer idArticle) throws BLLException {
		// TODO Auto-generated method stub
		Integer max=0;
		for (Enchere enchere : listEncheres) {
			if((enchere.getNo_article()==idArticle)&&(enchere.getMontant_enchere()>max)) {
				max=enchere.getMontant_enchere();
			}
		}

		return max;
	}

	@Override
	public Enchere updateEnchere(Integer idEnchere) throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> getLstEnchereOfHighestOffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> getLstEnchereOfUserById(Integer noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> getLstEnchereOfHighestOfferOfUserById(Integer noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}



}
