package fr.ENI.HiddenFigures.Enchere.test;

import java.time.LocalDate;

import fr.ENI.HiddenFigures.Enchere.bo.*;

public class BoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Retrait retrait = new Retrait("Montplaisir","690007","Lyon");
		System.out.println(retrait);
		
		retrait.ville="Paris";
		
		System.out.println(retrait);
	}

}
