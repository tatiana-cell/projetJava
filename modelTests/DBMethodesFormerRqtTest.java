package modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;


class DBMethodesFormerRqtTest {
//************TESTS pour former de Requ�tes de recherche 
//****selon les controls choisis:
	@Test
	void testFormerRequete() {
		DBMethodes test=new DBMethodes();
		Recette rct1 =new Recette(null, "cake citron", false, "citron", CalorieCateg.fitness,
				PastryCateg.autres, null, "trop sucr�!");
		String rqtCalcul�e=test.formerRequete(rct1);
		String rqtAttendu="SELECT * FROM RECETTES WHERE titre='cake citron'and ingridient='citron'and "
				+ "calorieCategory='fitness'and pastryCategory='autres'and"
				+ " commentaire='trop sucr�!';";
		Assert.assertEquals("Requ�te pour recette "+rct1 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct1);
        
	}
	@Test
	public void testFormerRequete2() {
		DBMethodes test=new DBMethodes();
		Recette rct2=new Recette(null, "", false, "banane",CalorieCateg.fitness,PastryCateg.autres, null,"un d�lice!");
		
		String rqtCalcul�e=test.formerRequete(rct2);
		String rqtAttendu="SELECT * FROM RECETTES WHERE "
				+ "ingridient='banane'and calorieCategory='fitness'and"
				+ " pastryCategory='autres'and commentaire='un d�lice!';";
		Assert.assertEquals("Requ�te pour recette "+rct2 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct2);
        
	}
	@Test
	public void testFormerRequete3() {
		DBMethodes test=new DBMethodes();
		Recette rct3=new Recette(null, null, false, null, CalorieCateg.fitness,
				PastryCateg.autres, null,null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE calorieCategory='fitness'and pastryCategory='autres';";
		String rqtCalcul�e=test.formerRequete(rct3);
		Assert.assertEquals("Requ�te pour recette "+rct3 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct3);
        
	}
	
	@Test
	public void testFormerRequete4() {
		DBMethodes test=new DBMethodes();
		Recette rct4=new Recette(null, null, false, null, null,
				PastryCateg.tartes, null,null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE pastryCategory='tartes';";		
		String rqtCalcul�e=test.formerRequete(rct4);
		Assert.assertEquals("Requ�te pour recette "+rct4 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct4);
	}	
	
	
	@Test
	public void testFormerRequete5() {
		DBMethodes test=new DBMethodes();
		Recette rct5=new Recette(null, null, false, null, null,
				null, null, "superb");
		String rqtAttendu="SELECT * FROM RECETTES WHERE commentaire='superb';";			
		String rqtCalcul�e=test.formerRequete(rct5);
		Assert.assertEquals("Requ�te pour recette "+rct5 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct5);
	}	
	
	
	@Test
	public void testFormerRequete6() {
		DBMethodes test=new DBMethodes();
		Recette rct6=new Recette(null, null, false, "pommes", null,
				PastryCateg.tartes, null, null);
        String rqtAttendu="SELECT * FROM RECETTES WHERE "
        		+ "ingridient='pommes'and pastryCategory='tartes';";			
		String rqtCalcul�e=test.formerRequete(rct6);
		Assert.assertEquals("Requ�te pour recette "+rct6 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct6);
	}	
	
	@Test
	public void testFormerRequete7() {
		DBMethodes test=new DBMethodes();
		Recette rct7=new Recette(null, "", false, "", null,
				PastryCateg.tartes, null, "bon march�");
		String rqtAttendu="SELECT * FROM RECETTES WHERE "
				+ "pastryCategory='tartes'and commentaire='bon march�';";
		
		String rqtCalcul�e=test.formerRequete(rct7);
		Assert.assertEquals("Requ�te pour recette "+rct7 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct7);
	}	
	
	@Test
	public void testFormerRequete8() {
		DBMethodes test=new DBMethodes();
		Recette rct8=new Recette(null, "", false, "", null,
				PastryCateg.tartes, null, "");
		String rqtAttendu="SELECT * FROM RECETTES WHERE pastryCategory='tartes';";
		
		String rqtCalcul�e=test.formerRequete(rct8);
		Assert.assertEquals("Requ�te pour recette "+rct8 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct8);
	}	
	
	@Test
	public void testFormerRequete9() {
		DBMethodes test=new DBMethodes();
		Recette rct9=new Recette(null, "", false, "", null,
				null, null, "");
		String rqtAttendu="Select * from recettes;";
		
		String rqtCalcul�e=test.formerRequete(rct9);
		Assert.assertEquals("Requ�te pour recette "+rct9 ,rqtAttendu, rqtCalcul�e);
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
		
		String rqtCalcul�e=test.formerRequete(rct10);
		Assert.assertEquals("Requ�te pour recette "+rct10 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct10);
	}	
	@Test
		
	public void testFormerRequete11() {
		DBMethodes test=new DBMethodes();
		Recette rct11=new Recette(null, null, false, "fromage blanc", null,
				null, null, null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE "
				+ "ingridient='fromage blanc';";
		
		String rqtCalcul�e=test.formerRequete(rct11);
		Assert.assertEquals("Requ�te pour recette "+rct11 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct11);
	}	
	
	
	@Test
	
	public void testFormerRequete12() {
		DBMethodes test=new DBMethodes();
		Recette rct12=new Recette(null, null, false, null, null,PastryCateg.g�teaux, Cuisson.sansCuisson, null);
		String rqtAttendu="SELECT * FROM RECETTES WHERE pastryCategory='g�teaux'and cuisson='sansCuisson';";
		String rqtCalcul�e=test.formerRequete(rct12);
		Assert.assertEquals("Requ�te pour recette "+rct12 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct12);
	}	
	
@Test
	
	public void testFormerRequete13() {
	DBMethodes test=new DBMethodes();
		Recette rct13=new Recette(null,"tarte aux pommes",false,null,null,null,null,"the best apple pie!");
		String rqtAttendu="SELECT * FROM RECETTES WHERE titre='tarte aux pommes'and commentaire='the best apple pie!';";

		String rqtCalcul�e=test.formerRequete(rct13);
		Assert.assertEquals("Requ�te pour recette "+rct13 ,rqtAttendu, rqtCalcul�e);
		test.afficher(rct13);
	}	
//*****************FIN DE TESTS DE REQUETES************	

}
