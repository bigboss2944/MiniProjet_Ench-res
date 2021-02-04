package fr.ENI.HiddenFigures.Enchere.bo;

public class UtilisateurAuthToken {
	private Integer id;
	private String selector;
	private String validator;
	private Utilisateur utilisateur;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public String getValidator() {
		return validator;
	}
	public void setValidator(String validator) {
		this.validator = validator;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	@Override
	public String toString() {
		return "UtilisateurAuthToken [id=" + id + ", selector=" + selector + ", validator=" + validator
				+ ", utilisateur=" + utilisateur + "]";
	}
	
	

}
