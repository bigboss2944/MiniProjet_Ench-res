package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public interface ManagerEncheres {
	Enchere addEnchere(Enchere enchere) throws BLLException,EnchereException;
	Enchere updateEnchere(Integer idEnchere) throws BLLException;
	List<Enchere> getLstEnchere()  ;
	Enchere getEnchere(Integer idEnchere) throws BLLException;
	Enchere deleteEnchere(Integer idEnchere) throws BLLException;
	List<Enchere> getLstEnchereOfHighestOffer();
	List<Enchere> getLstEnchereOfUserById(Integer noUtilisateur);
	List<Enchere> getLstEnchereOfHighestOfferOfUserById(Integer noUtilisateur);
	List<Enchere> getLstEnchereWonOfUserById(Integer noUtilisateur);
	Integer IdUserEncherePlusHaute(Integer idArticle) throws BLLException ;
	Integer EncherePlusHaute(Integer idArticle) throws BLLException;
	
	
}
