package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import model.Cuisson;
import model.PastryCateg;

class FormEventTest {
//	FormEvent(Object source,String titre,String ingridient,int calorCat,
//			String pastryCateg,String cuisson,String comment, boolean isIngrd,String path)

	@Test
	void testGetCuisson1() {
	FormEvent evt=new FormEvent(this,"tarte citron","citron",1,"tartes","avecCuisson","délice",true,"myFile.txt");
		Assert.assertEquals("avecCuisson", evt.getCuisson());
	}
	@Test
	void testGetCuisson2() {
	FormEvent evt=new FormEvent(this,null,"citron",1,"tartes",null,"délice",true,"myFile.txt");
		Assert.assertEquals(null, evt.getCuisson());
	}
	void testGetCuisson3() {
		FormEvent evt=new FormEvent(this,"tarte citron","citron",1,"tartes","sansCuisson","délice",true,"myFile.txt");
			Assert.assertEquals(Cuisson.sansCuisson, evt.getCuisson());
		}
	
	@Test
	void testGetIsIngrd() {
	FormEvent evt=new FormEvent(this,"tarte citron","citron",1,"tartes","","délice",true,"myFile.txt");	
		Assert.assertEquals(true, evt.getIsIngrd());
	}

	@Test
	void testGetPath1() {
		FormEvent evt=new FormEvent(this,"tarte citron","citron",1,"tartes","","délice",true,"myFile.txt");	
	   Assert.assertEquals("myFile.txt", evt.getPath());
	}

	@Test
	void testGetPath2() {
		FormEvent evt=new FormEvent(this,"tarte citron","citron",1,"tartes","","délice",true,null);	
	   Assert.assertEquals(null, evt.getPath());
	}
	
	
	@Test
	void testGetCommentaire() {
		FormEvent evt=new FormEvent(this,"tarte citron","citron",1,"tartes","","délice",true,null);	
		   Assert.assertEquals("délice", evt.getCommentaire());
	}

	@Test
	void testGetTitre1() {
		FormEvent evt=new FormEvent(this,"fraisier",null,1,"gateau","","superb",true,null);	
		   Assert.assertEquals("fraisier", evt.getTitre());	
	}
	@Test
	void testGetTitre2() {
		FormEvent evt=new FormEvent(this,"",null,1,"gateau","","superb",true,null);	
		   Assert.assertEquals("", evt.getTitre());	
	}
	

	@Test
	void testGetIngridient() {
		FormEvent evt=new FormEvent(this,"","fraises",1,"gateau","","superb",true,null);
		Assert.assertEquals("fraises", evt.getIngridient());	
	}
	@Test
	void testGetIngridient2() {
		FormEvent evt=new FormEvent(this,"",null,1,"gateau","","superb",true,null);
		Assert.assertEquals(null, evt.getIngridient());	
	}

	@Test
	void testGetCaloriesCategory() {
		FormEvent evt=new FormEvent(this,"",null,1,"gateau","","superb",true,null);
		Assert.assertEquals(1, evt.getCaloriesCategory());	
	}

	@Test
	void testGetPastryCateg() {
		FormEvent evt=new FormEvent(this,"",null,1,"autres","","superb",true,null);
		Assert.assertEquals(PastryCateg.autres.toString(), evt.getPastryCateg());
	}

}
