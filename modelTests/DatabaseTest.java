package modelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.CalorieCateg;
import model.Cuisson;
import model.Database;
import model.PastryCateg;
import model.Recette;

class DatabaseTest {

/*	@Test
	void testSave() {
		Database db=new Database();
		
		List<Recette>recettesAttnd;
		recettesAttnd=new LinkedList<Recette>();
		Recette r1=new Recette("doc1.txt","gateau coco",true,"coco",
				CalorieCateg.fitness,PastryCateg.gâteaux,Cuisson.avecCuisson,
				"cooment1");	
		Recette r2=new Recette("doc4.txt","gateau noisette",true,"noisette",
				CalorieCateg.fitness,PastryCateg.gâteaux,Cuisson.avecCuisson,
				"comment2");
		Recette r3=new Recette("doc3.txt","tarte pomme",true,"pomme",
				CalorieCateg.fitness,PastryCateg.tartes,Cuisson.avecCuisson,
				"comment3");
		String adresse="C:\\Users\\MASTER\\Documents\\A_recettes\\cookie.txt";
		Recette r4=new Recette(adresse,"cookie americain",true,"chocolat",
				CalorieCateg.fitness,PastryCateg.petitFours,Cuisson.avecCuisson,
				"comment4");
		db.addRecette(r1);
		db.addRecette(r2);
		db.addRecette(r3);
		db.addRecette(r4);
		List recettes=db.getRecettes();
		
		
	}*/

	@Test
	void testSaveToFile() {
		Recette rct1=new Recette(1,"monFichier.txt","Fraisier",true,"fraises",CalorieCateg.calorique,
				PastryCateg.gâteaux,Cuisson.avecCuisson,"un délice!"); 
		Recette rct2=new Recette(3,null,"tarte au citron",true,"citron",CalorieCateg.diététique,
				PastryCateg.tartes,Cuisson.avecCuisson,"acidulée trop"); 
		Recette rct3=new Recette(5,null,null,true,"chocolat",null,
				PastryCateg.petitFours,Cuisson.avecCuisson,null); 
		Recette rct4=new Recette(7,null,null,true,"fromage blanc",null,
				null,null,null); 
		
		
		
		
	}

/*	@Test
	void testLoadFromFile() {
		fail("Not yet implemented");
	}

	@Test
	void testLoad() {
		fail("Not yet implemented");
	}

	@Test
	void testFindRecette() {
		fail("Not yet implemented");
	}

	@Test
	void testFormerRequete() {
		
	}

	

	@Test
	void testDeleteRecette() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveRecette() {
		fail("Not yet implemented");
	}

	@Test
	void testConnect() {
		fail("Not yet implemented");
	}

	@Test
	void testDisconnect() {
		fail("Not yet implemented");
	}
	  */

}
