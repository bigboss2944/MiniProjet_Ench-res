package fr.ENI.HiddenFigures.Enchere.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.dal.DALException;
import fr.ENI.HiddenFigures.Enchere.dal.DAOFactory;

 

public class TestEnchereDAO {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.parse("2016-09-21 13:43:27.000", formatter);
		LocalDateTime date_enchereLocalDateTime = LocalDateTime.parse("2021-01-17 22:33:57.260",formatter );

		//		System.out.println(dateTime);
//		System.out.println(date_enchereLocalDateTime);
		try {
			List<Enchere> listEncheres = DAOFactory.getEnchereDAO().getAll();
			Enchere enchere = new Enchere(date_enchereLocalDateTime,150);
			enchere.setNo_article(4);
			DAOFactory.getEnchereDAO().insert(enchere);
			System.out.println(listEncheres);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 
			 
			
			
}
