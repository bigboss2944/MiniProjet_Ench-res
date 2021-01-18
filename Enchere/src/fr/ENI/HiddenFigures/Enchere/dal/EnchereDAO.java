package fr.ENI.HiddenFigures.Enchere.dal;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Enchere;

public interface EnchereDAO {
	Enchere insert(Enchere enchere) throws DALException;
	Enchere getEnchere(Integer idEnchere) throws DALException;
	List<Enchere> getAll() throws DALException ;
	List<Enchere> getAllEncheresByUtilisateur(Integer idUtilisateur) throws DALException ;
	Enchere update(Enchere enchere) throws DALException;
	Enchere deleteEnchere(Integer idEnchere) throws DALException;
	Enchere deleteEnchereByArticle(Integer idArticle) throws DALException;
	String selectUtilisateurByEnchere(Enchere idEnchere) throws DALException;
}
