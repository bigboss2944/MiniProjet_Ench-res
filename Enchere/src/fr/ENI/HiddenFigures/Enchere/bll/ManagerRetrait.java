package fr.ENI.HiddenFigures.Enchere.bll;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Retrait;

 

public interface ManagerRetrait {
	Retrait addRetrait(Retrait retrait) throws BLLException;

}
