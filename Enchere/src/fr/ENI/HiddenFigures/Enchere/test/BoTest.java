package fr.ENI.HiddenFigures.Enchere.test;

import java.time.LocalDate;

import fr.ENI.HiddenFigures.Enchere.bo.*;

public class BoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer credit = 100;
		Utilisateur utilisateur1=new Utilisateur("fafanoel","No�l","Fabrice","nimp@gmail.com","0123456789","12, rue des foug�res","75000","Paris","MotDePasse",credit,true);
		Retrait retrait = new Retrait(utilisateur1.getRue(),utilisateur1.getCodePostal(),utilisateur1.getVille());
		ArticleVendu articleVendu1 = new ArticleVendu("pc Dell","Pc portable Gamer",LocalDate.now(),LocalDate.of(2020, 2, 2),15,15,"en cours",retrait,1);
		
		Enchere enchere1 = new Enchere();
		
		System.out.println(utilisateur1);
		System.out.println(articleVendu1);
		
		
	}

}
