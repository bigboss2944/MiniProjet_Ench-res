package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.dal.CategorieDAO;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;
import fr.ENI.HiddenFigures.Enchere.dal.EnchereDAO;

public class ManagerEncheresImpl implements ManagerEncheres {
	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
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
			if(enchereOfMaxOfArticleVendu !=null) {
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
	public Enchere addEnchere(Enchere Enchere) throws BLLException {
		// TODO Auto-generated method stub
		return null;
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

}
