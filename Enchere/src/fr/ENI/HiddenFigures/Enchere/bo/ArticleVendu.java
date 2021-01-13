package fr.ENI.HiddenFigures.Enchere.bo;

import java.time.LocalDate;

public class ArticleVendu {
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer miseAprix;
	private Integer prixVente;
	private Integer noUtilisateur;
	private Integer noCategorie;
	private String etatVente;
	private Retrait lieuRetrait;
	public ArticleVendu() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer miseAprix, Integer prixVente, String etatVente,Retrait lieuRetrait,Integer noCategorie) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
		this.noCategorie = noCategorie;
	}



	public Integer getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public Integer getMiseAprix() {
		return miseAprix;
	}
	public void setMiseAprix(Integer miseAprix) {
		this.miseAprix = miseAprix;
	}
	public Integer getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public Integer getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getEtatVente() {
		return etatVente;
	}



	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}



	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}



	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}



	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAprix="
				+ miseAprix + ", prixVente=" + prixVente + ", noUtilisateur=" + noUtilisateur + ", noCategorie="
				+ noCategorie + ", etatVente=" + etatVente + ", lieuRetrait=" + lieuRetrait + "]";
	}



	
	

}
