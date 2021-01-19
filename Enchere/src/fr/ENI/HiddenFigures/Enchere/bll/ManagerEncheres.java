package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public interface ManagerEncheres {
	Enchere addEnchere(Enchere Enchere) throws BLLException, EnchereException;
	Enchere updateEnchere(Integer idEnchere) throws BLLException;
	List<Enchere> getLstEnchere()  ;
	Enchere getEnchere(Integer idEnchere) throws BLLException;
	Enchere deleteEnchere(Integer idEnchere) throws BLLException;
	List<Enchere> getLstEnchereOfHighestOffer();
	List<Enchere> getLstEnchereOfUserById(Integer noUtilisateur);
	List<Enchere> getLstEnchereOfHighestOfferOfUserById(Integer noUtilisateur);
	Enchere updateEnchere(Enchere enchere) throws BLLException, EnchereException;
	boolean EnchereOK(Enchere enchere) throws BLLException;
	Integer EncherePlusHaute(Integer idArticle) throws BLLException;





}
