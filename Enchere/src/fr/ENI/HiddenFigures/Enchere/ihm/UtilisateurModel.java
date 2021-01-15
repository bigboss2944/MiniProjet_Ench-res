package fr.ENI.HiddenFigures.Enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

 

 

public class UtilisateurModel {
	List<Utilisateur> listUtilisateur = new ArrayList<>();

	public List<Utilisateur> getListUtilisateur() {
		return listUtilisateur;
	}

	public void setListUtilisateur(List<Utilisateur> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}
	

}
