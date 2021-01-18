package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public interface ManagerEncheres {
	Enchere addEnchere(Enchere enchere) throws BLLException,EnchereException;
	Enchere updateEnchere(Enchere enchere) throws BLLException,EnchereException;
	List<Enchere> getLstEnchere() throws BLLException;
	Enchere getEnchere(Integer idEnchere) throws BLLException;
	Enchere deleteEnchere(Integer idEnchere) throws BLLException;
	boolean EnchereOK(Enchere enchere) throws BLLException;
	Integer EncherePlusHaute() throws BLLException;
}
