package fr.ENI.HiddenFigures.Enchere.bll;

import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public interface ManagerEncheres {
	Enchere addEnchere(Enchere Enchere) throws BLLException;
	Enchere updateEnchere(Integer idEnchere) throws BLLException;
	Enchere getLstEnchere() throws BLLException;
	Enchere getEnchere(Integer idEnchere) throws BLLException;
	Enchere deleteEnchere(Integer idEnchere) throws BLLException;
	
	
	
	
}
