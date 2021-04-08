package modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;


class DBMethodesFormerRqtTest {
//************TESTS pour former de Requêtes de recherche 
//****selon les controls choisis:
	@Test
	void testFormerRequete() {
		DBMethodes test=new DBMethodes();
		Recette rct1 =new Recette(null, "cake citron", false, "citron", CalorieCateg.fitness,
				PastryCateg.autres, null, "trop sucré!");
		String rqtCalculée=test.formerRequete(rct1);
		String rqtAttendu="SELECT * FROM RECETTES WHERE titre='cake citron'and ingridient='citron'and "
				+ "calorieCategory='fitness'and pastryCategory='autres'and"
				+ " commentaire='trop sucré!';";
		Assert.assertEquals("Requête pour recette "+rct1 ,rqtAttendu, rqtCalculée);
		test.afficher(rct1);
        
	}
	@Test
	public void testFormerRequete2() {
		DBMethodes test=new DBMethodes();
		Recette rct2=new Recette(null, "", false, "banane",CalorieCateg.fitness,PastryCateg.autres, null,"un délice!");
		
		String rqtCalculée=test.formerRequete(rct2);
		String rqtAttendu="SELECT * FROM RECETTES WHERE "
				+ "ingridient='banane'and calorieCategory='fitness'and"
				+ " pastryCategory='autres'and commentaire='un délice!';";
		Assert.assertEquals("Requête pour recette "+rct2 ,rqtAttendu, rqtCalculée);
		test.afficher(rct2);
        
	}
	@Test
	public void testFormerRequete3() {
		DBMethodes test=new DBMethodes();
		Recette rct3=new Recette(null, null, false, null, CalorieCateg.fitness,
				PastryCateg.autres, null,null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE calorieCategory='fitness'and pastryCategory='autres';";
		String rqtCalculée=test.formerRequete(rct3);
		Assert.assertEquals("Requête pour recette "+rct3 ,rqtAttendu, rqtCalculée);
		test.afficher(rct3);
        
	}
	
	@Test
	public void testFormerRequete4() {
		DBMethodes test=new DBMethodes();
		Recette rct4=new Recette(null, null, false, null, null,
				PastryCateg.tartes, null,null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE pastryCategory='tartes';";		
		String rqtCalculée=test.formerRequete(rct4);
		Assert.assertEquals("Requête pour recette "+rct4 ,rqtAttendu, rqtCalculée);
		test.afficher(rct4);
	}	
	
	
	@Test
	public void testFormerRequete5() {
		DBMethodes test=new DBMethodes();
		Recette rct5=new Recette(null, null, false, null, null,
				null, null, "superb");
		String rqtAttendu="SELECT * FROM RECETTES WHERE commentaire='superb';";			
		String rqtCalculée=test.formerRequete(rct5);
		Assert.assertEquals("Requête pour recette "+rct5 ,rqtAttendu, rqtCalculée);
		test.afficher(rct5);
	}	
	
	
	@Test
	public void testFormerRequete6() {
		DBMethodes test=new DBMethodes();
		Recette rct6=new Recette(null, null, false, "pommes", null,
				PastryCateg.tartes, null, null);
        String rqtAttendu="SELECT * FROM RECETTES WHERE "
        		+ "ingridient='pommes'and pastryCategory='tartes';";			
		String rqtCalculée=test.formerRequete(rct6);
		Assert.assertEquals("Requête pour recette "+rct6 ,rqtAttendu, rqtCalculée);
		test.afficher(rct6);
	}	
	
	@Test
	public void testFormerRequete7() {
		DBMethodes test=new DBMethodes();
		Recette rct7=new Recette(null, "", false, "", null,
				PastryCateg.tartes, null, "bon marché");
		String rqtAttendu="SELECT * FROM RECETTES WHERE "
				+ "pastryCategory='tartes'and commentaire='bon marché';";
		
		String rqtCalculée=test.formerRequete(rct7);
		Assert.assertEquals("Requête pour recette "+rct7 ,rqtAttendu, rqtCalculée);
		test.afficher(rct7);
	}	
	
	@Test
	public void testFormerRequete8() {
		DBMethodes test=new DBMethodes();
		Recette rct8=new Recette(null, "", false, "", null,
				PastryCateg.tartes, null, "");
		String rqtAttendu="SELECT * FROM RECETTES WHERE pastryCategory='tartes';";
		
		String rqtCalculée=test.formerRequete(rct8);
		Assert.assertEquals("Requête pour recette "+rct8 ,rqtAttendu, rqtCalculée);
		test.afficher(rct8);
	}	
	
	@Test
	public void testFormerRequete9() {
		DBMethodes test=new DBMethodes();
		Recette rct9=new Recette(null, "", false, "", null,
				null, null, "");
		String rqtAttendu="Select * from recettes;";
		
		String rqtCalculée=test.formerRequete(rct9);
		Assert.assertEquals("Requête pour recette "+rct9 ,rqtAttendu, rqtCalculée);
		test.afficher(rct9);
	}	
	
	@Test
	public void testFormerRequete10() {
		DBMethodes test=new DBMethodes();
		Recette rct10=new Recette(null, null, false, "citron", CalorieCateg.fitness,
				PastryCateg.entremets, null, "rafraichissant");
		String rqtAttendu="SELECT * FROM RECETTES WHERE "
				+ "ingridient='citron'and calorieCategory='fitness'and"
				+ " pastryCategory='entremets'and commentaire='rafraichissant';";
		
		String rqtCalculée=test.formerRequete(rct10);
		Assert.assertEquals("Requête pour recette "+rct10 ,rqtAttendu, rqtCalculée);
		test.afficher(rct10);
	}	
	@Test
		
	public void testFormerRequete11() {
		DBMethodes test=new DBMethodes();
		Recette rct11=new Recette(null, null, false, "fromage blanc", null,
				null, null, null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE "
				+ "ingridient='fromage blanc';";
		
		String rqtCalculée=test.formerRequete(rct11);
		Assert.assertEquals("Requête pour recette "+rct11 ,rqtAttendu, rqtCalculée);
		test.afficher(rct11);
	}	
	
	
	@Test
	
	public void testFormerRequete12() {
		DBMethodes test=new DBMethodes();
		Recette rct12=new Recette(null, null, false, null, null,PastryCateg.gâteaux, Cuisson.sansCuisson, null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE pastryCategory='gâteaux'and cuisson='sansCuisson';";
		String rqtCalculée=test.formerRequete(rct12);
		Assert.assertEquals("Requête pour recette "+rct12 ,rqtAttendu, rqtCalculée);
		test.afficher(rct12);
	}	
	
@Test
	
	public void testFormerRequete13() {
	DBMethodes test=new DBMethodes();
		Recette rct13=new Recette(null,"tarte aux pommes",false,null,null,null,null,"the best apple pie!");
		String rqtAttendu="SELECT * FROM RECETTES WHERE titre='tarte aux pommes'and commentaire='the best apple pie!';";

		String rqtCalculée=test.formerRequete(rct13);
		Assert.assertEquals("Requête pour recette "+rct13 ,rqtAttendu, rqtCalculée);
		test.afficher(rct13);
	}	
//*****************FIN DE TESTS DE REQUETES************	

}
