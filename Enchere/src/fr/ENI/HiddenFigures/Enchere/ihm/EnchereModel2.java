package fr.ENI.HiddenFigures.Enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

public class EnchereModel2 {
	Utilisateur utilisateur;
	Enchere enchere;
	ArticleVendu articleVendu;
	List<Utilisateur> listUtilisateur = new ArrayList<>();
	
	public EnchereModel2(){
		
	}
	
	public EnchereModel2(Enchere enchere){
		this.enchere=enchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public List<Utilisateur> getListUtilisateur() {
		return listUtilisateur;
	}

	public void setListUtilisateur(List<Utilisateur> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}
}
