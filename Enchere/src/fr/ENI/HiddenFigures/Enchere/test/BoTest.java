package fr.ENI.HiddenFigures.Enchere.test;

import java.time.LocalDate;

import fr.ENI.HiddenFigures.Enchere.bo.*;

public class BoTest {

	public static void main(String[] args) {
		
		Retrait retrait= new  Retrait ("bateau", "0025", "Lyon");
		
		System.out.println(retrait);
		
		System.out.println(retrait);
		
		retrait.setVille("Nantes"); 
		

		 retrait.getVille();
		
		

		 
		System.out.println(retrait.getVille()+" ,"+ retrait.getCode_postal());
		
		
		Utilisateur utilisateur= new Utilisateur ("fabrice", "Thuy", "Thierno", "alviinmunk@gmail.diner", "0312254587", "popo",
				"69000", "Sevres", "MPdon4*", 1500 , true);
		
		System.out.println(utilisateur);
		
		utilisateur.setNom("DIALLO");
		System.out.println(utilisateur);
	
		retrait.setRue("Hanoi");
		System.out.println(retrait.getRue());
		
		utilisateur.setPrenom("Fabrice");
		System.out.println(utilisateur.getPrenom());
		
		utilisateur.setPseudo("Tintin");
		System.out.println(utilisateur.getPseudo());
		
		retrait.setCode_postal("0024");
		System.out.println(retrait.getCode_postal());
		utilisateur.setVille("Senzhen");
		
		System.out.println(utilisateur.getVille());
		
		utilisateur.setCredit(0021);
		System.out.println(utilisateur.getCredit());
		
		
		utilisateur.setVille("Conakry");
		System.out.println(utilisateur.getVille());
		
		
		
		
	
		
	}

	
	
	

}
