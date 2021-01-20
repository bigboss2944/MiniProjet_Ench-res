package fr.ENI.HiddenFigures.Enchere.ihm;

import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

public class ArticleVenduModel {
	List<ArticleVendu> listArticlesVendus;
	Utilisateur vendeur;
	ArticleVendu articleVendu;
	public ArticleVenduModel(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	public ArticleVenduModel() {
		// TODO Auto-generated constructor stub
	}
	public ArticleVenduModel(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	
	public Utilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	

}
