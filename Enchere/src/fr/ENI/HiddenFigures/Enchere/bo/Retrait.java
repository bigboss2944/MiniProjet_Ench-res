package fr.ENI.HiddenFigures.Enchere.bo;

public class Retrait {
	private Integer noArticle;
	private String rue;
	private String code_postal;
	public String ville;
	public Retrait() {
		// TODO Auto-generated constructor stub
	}
	public Retrait(String rue, String code_postal, String ville) {
	
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}
	public Integer getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {//getter de l'attribut ville, qui renvoie l'attribut ville sous la forme d'une chaine de caractères
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	@Override
	public String toString() {
		return "Retrait [noArticle=" + noArticle + ", rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville
				+ "]";
	}
	

}
