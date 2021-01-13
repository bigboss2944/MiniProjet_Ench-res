package fr.ENI.HiddenFigures.Enchere.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Enchere {
	private Integer noEnchere;
	private LocalDate dateEnchere;
	private Integer montant_enchere;
	private Integer noArticle;
	private Integer noUtilisateur;
	private List<ArticleVendu> listArticlesVendus = new ArrayList<>();
	
	public Enchere() {
		// TODO Auto-generated constructor stub
	}
	public Enchere(LocalDate dateEnchere, Integer montant_enchere) {
		
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		
	}
	public Integer getNo_enchere() {
		return noEnchere;
	}
	public void setNo_enchere(Integer no_enchere) {
		this.noEnchere = no_enchere;
	}
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public Integer getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	public Integer getNo_article() {
		return noArticle;
	}
	public void setNo_article(Integer no_article) {
		this.noArticle = no_article;
	}
	public Integer getNo_utilisateur() {
		return noUtilisateur;
	}
	public void setNo_utilisateur(Integer no_utilisateur) {
		this.noUtilisateur = no_utilisateur;
	}
	
	public List<ArticleVendu> getListArticlesVendus() {
		return listArticlesVendus;
	}
	public void setListArticlesVendus(List<ArticleVendu> listArticlesVendus) {
		this.listArticlesVendus = listArticlesVendus;
	}
	@Override
	public String toString() {
		return "Enchere [no_enchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montant_enchere="
				+ montant_enchere + ", no_article=" + noArticle + ", no_utilisateur=" + noUtilisateur + "]";
	}
	
}
