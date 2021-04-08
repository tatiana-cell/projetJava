package modelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;

class DBMethodesFindRecetteTest {
	DBMethodes test=new DBMethodes();
	@Test
	void testFindRecette() throws Exception  {
	List<Recette> recettes=new LinkedList<Recette>();
		Recette rct1 =new Recette(1,"cake_citron.txt", "cake citron", false, "citron", CalorieCateg.fitness,
				PastryCateg.autres, null, "trop sucré!");
		Recette rct2 =new Recette(1,null, "gâteau citron", true, "citron", CalorieCateg.fitness,
				PastryCateg.gâteaux, Cuisson.avecCuisson, "superb!");
		
		Recette rct3 =new Recette(1,null, "fraisier", true, "citron", CalorieCateg.fitness,
				PastryCateg.gâteaux, Cuisson.sansCuisson, "à refaire!");
		Recette rct4 =new Recette(1,null, "fraisier", false, null, CalorieCateg.fitness,
				PastryCateg.autres, null, "à refaire!");
		Recette rct5=new Recette(1,null, "charlotte à fraises",true, "fraises", CalorieCateg.fitness,
				PastryCateg.autres, null, "ajouter plus de gelo");
		recettes.add(rct1);
		recettes.add(rct2);
		recettes.add(rct3);
		recettes.add(rct4);
		recettes.add(rct5);
		Recette rctParametres=new Recette(null, null,true, "citron", null,
				null, null, null);	
		 test.connect();
		test.save(recettes);
		List<Recette>recettesTrv=test.findRecette(rctParametres);
		String resultTrv=test.listToString(recettesTrv);
		System.out.println(resultTrv);
		test.disconnect();
		
		
	}

}
