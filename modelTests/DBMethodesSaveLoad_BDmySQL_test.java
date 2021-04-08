package modelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;

class DBMethodesSaveLoad_BDmySQL_test {
	
	DBMethodes test=new DBMethodes();
	
	@Test
	void Save_1ToEtLoadFromMysqlBD() throws Exception {
		String recettesCalculées=null;
		List<Recette> recettes1IN = new LinkedList<Recette>();
		Recette rct1 =new Recette(1,"cake_citron.txt", "cake citron", true, "citron", CalorieCateg.fitness,
				PastryCateg.autres, null, "trop sucré!");
		Recette rct2 =new Recette(2,"fraisier.txt", "fraisier", true, "fraises", CalorieCateg.calorique,
				PastryCateg.gâteaux, Cuisson.avecCuisson, "trop sucré!");
		recettes1IN.add(rct1);
		recettes1IN.add(rct2);
		String attnd=test.listToString(recettes1IN);
		 test.connect();
		test.save(recettes1IN);
		List<Recette>recettes1OUT=test.load();
		
		if(recettes1OUT.size()>0)
			{recettesCalculées=test.listToString(recettes1OUT);}
		System.out.println(recettesCalculées);
        Assert.assertEquals("Données IN et OUT de DB ne correspondent pas", attnd, recettesCalculées);
		test.disconnect();
	}
	@Test
	void Save_2ToEtLoadFromMysqlBD() throws Exception {
		String recettesCalculées=null;
		List<Recette> recettes2IN = new LinkedList<Recette>();
		
		
		Recette rct1 =new Recette(10101,"cakeCitron.txt", "cake citron", true, null, CalorieCateg.diététique,
				null, null, "trop acidulé");
		Recette rct2 =new Recette(20201,"fraisier.txt", "fraisier", true, "fraises", CalorieCateg.calorique,
				null, null, "trop cher");
		recettes2IN.add(rct1);
		recettes2IN.add(rct2);
		String attnd=test.listToString(recettes2IN);
		 test.connect();
		test.save(recettes2IN);
		List<Recette>recettes2OUT=test.load();
		
		if(recettes2OUT.size()>0)
			{recettesCalculées=test.listToString(recettes2OUT);}
		System.out.println(recettesCalculées);
        Assert.assertEquals("Données IN et OUT de DB ne correspondent pas", attnd, recettesCalculées);
		test.disconnect();
	}
	
	
	
	
	
	
	

}
