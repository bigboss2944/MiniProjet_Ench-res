package fr.ENI.HiddenFigures.Enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

public class UtilisateurModel {
	Utilisateur utilisateur;
	List<Utilisateur> listUtilisateur = new ArrayList<>();

	public UtilisateurModel() {
		// TODO Auto-generated constructor stub
	}

	public UtilisateurModel(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Utilisateur> getListUtilisateur() {
		return listUtilisateur;
	}

	public void setListUtilisateur(List<Utilisateur> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}

}
