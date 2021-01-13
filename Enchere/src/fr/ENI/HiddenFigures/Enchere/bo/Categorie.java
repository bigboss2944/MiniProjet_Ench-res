package fr.ENI.HiddenFigures.Enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	private Integer noCategorie;
	private String libelle;
	private List<ArticleVendu> listArticlesVendus = new ArrayList<>();
	public Categorie() {
		// TODO Auto-generated constructor stub
	}
	public Categorie(String libelle) {
		 
		this.libelle = libelle;
	}
	public Integer getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<ArticleVendu> getListArticlesVendus() {
		return listArticlesVendus;
	}
	public void setListArticlesVendus(List<ArticleVendu> listArticlesVendus) {
		this.listArticlesVendus = listArticlesVendus;
	}
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
}
