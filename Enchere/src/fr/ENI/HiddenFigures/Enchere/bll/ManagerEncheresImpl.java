package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.dal.ArticleVenduDAO;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.EnchereDAO;
import fr.ENI.HiddenFigures.Enchere.dal.UtilisateurDAO;

public class ManagerEncheresImpl implements ManagerEncheres {
	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	ArticleVenduDAO articleDAO= DAOFactory.getArticleDAO();
	UtilisateurDAO utilisateurDAO= DAOFactory.getUtilisateurDAO();
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();

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

	public List<Enchere> getLstEnchere()   {
		return listEncheres;
	}
	@Override

	public List<Enchere> getLstEnchereOfHighestOffer()  {
		List<Enchere> listEncheresOfHighestOffer = new ArrayList<>();
		
		for (ArticleVendu articleVendu: managerArticles.getLstArticleVendus()) {
			Integer MaxOfArticleVendu = 0;
			Enchere enchereOfMaxOfArticleVendu =new Enchere();
			for (Enchere enchere : listEncheres) {
				if(enchere.getNo_article()==articleVendu.getNoArticle() && enchere.getMontant_enchere()> MaxOfArticleVendu) {
					MaxOfArticleVendu =enchere.getMontant_enchere();
					enchereOfMaxOfArticleVendu= enchere;
				}

			}
			if(enchereOfMaxOfArticleVendu.getNo_enchere() !=null) {
				listEncheresOfHighestOffer.add(enchereOfMaxOfArticleVendu);
			}
		}

		return listEncheresOfHighestOffer;
	}

	@Override

	public List<Enchere> getLstEnchereOfHighestOfferOfUserById(Integer noUtilisateur)  {
		List<Enchere> listEncheresOfHighestOfferOfUserById= new ArrayList<>();
		for (Enchere enchere :  this.getLstEnchereOfHighestOffer()) {
			if(enchere.getNo_utilisateur() == noUtilisateur ) {
				listEncheresOfHighestOfferOfUserById.add(enchere);
			}

		}
		return listEncheresOfHighestOfferOfUserById;
	}

	@Override

	public List<Enchere> getLstEnchereWonOfUserById(Integer noUtilisateur)  {
		List<Enchere> lstEnchereWonOfUserById= new ArrayList<>();
		for (ArticleVendu articleVendu: managerArticles.getArticleByEtatTermine()) {
			for (Enchere enchere :  this.getLstEnchereOfHighestOfferOfUserById(noUtilisateur)) {
				if(enchere.getNo_article()  == articleVendu.getNoArticle()) {
					lstEnchereWonOfUserById.add(enchere);
				}

			}
		}
		return lstEnchereWonOfUserById;
	}


	@Override

	public List<Enchere> getLstEnchereOfUserById(Integer noUtilisateur)  {
		List<Enchere> listEncheresOfUserById = new ArrayList<>();
		for (Enchere enchere : listEncheres) {
			if(enchere.getNo_utilisateur() == noUtilisateur ) {
				listEncheresOfUserById.add(enchere);
			}

		}
		return listEncheresOfUserById;
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
	private boolean EnchereOK(Enchere enchere) throws BLLException {
		// TODO Auto-generated method stub
		try {
			ArticleVendu article = articleDAO.getArticleVendu(enchere.getNo_article());

			Utilisateur utilisateur = utilisateurDAO.getUtilisateur(enchere.getNo_utilisateur());
			if(enchere.getMontant_enchere()<=EncherePlusHaute(article.getNoArticle())) {
				throw new BLLException("Enchere est inférieur à l'enchère la plus haute");
			}
			else if(enchere.getNo_utilisateur()==article.getNoUtilisateur()) {
				throw new BLLException("Le vendeur ne peut enchérir sur le produit qu'il vend");
			}
			else if(enchere.getMontant_enchere()>utilisateur.getCredit()) {
				throw new BLLException("Couche BLL - L'enchérisseur ne peut proposer d'enchère supérieur à son crédit");
			}
			else if(enchere.getMontant_enchere()<article.getMiseAprix()) {
				throw new BLLException("Couche BLL - L'enchérisseur ne peut proposer de prix inférieur à la mise à prix");
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
	public Enchere getEnchere(Integer idEnchere) throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere deleteEnchere(Integer idEnchere) throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer IdUserEncherePlusHaute(Integer idArticle) throws BLLException {
		// TODO Auto-generated method stub
		Integer max=0;
		Integer idUser=0;

		if(listEncheres!=null) {
			for (Enchere enchere : listEncheres) {
				if((enchere.getNo_article()==idArticle)&&(enchere.getMontant_enchere()>max)) {
					max=enchere.getMontant_enchere();
					idUser=enchere.getNo_utilisateur();
				}
			}

			return idUser;
		}
		else {
			return null;
		}


	}


}
