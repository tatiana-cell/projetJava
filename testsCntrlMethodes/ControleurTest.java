package testsCntrlMethodes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import controleur.Controleur;
import gui.FormEvent;
import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;

class ControleurTest {

	@Test
	void testFormerRecette1() {
		Controleur cntrl=new Controleur();
		List<Recette>rctsAttendus;
		List<Recette>rctCalculées;
		FormEvent evt=new FormEvent(this,"Fraisier","fraises",3,
				"gâteaux","avecCuisson",
				"un délice!", true,"monFichier.txt");
System.out.println("ClrCtg:"+CalorieCateg.calorique.ordinal());		
 Recette rctCalc= cntrl.formerNouveauRecette(evt);
Recette rctAttnd=new Recette(1,"monFichier.txt","Fraisier",true,"fraises",CalorieCateg.calorique,
		PastryCateg.gâteaux,Cuisson.avecCuisson,"un délice!"); 
  System.out.println("RECETTE CALCULEE:");
  cntrl.afficherRecette(rctCalc);
  System.out.println("% RECETTE ATTENDUE %");
  cntrl.afficherRecette(rctAttnd);
      		Assert.assertEquals(rctAttnd,rctCalc);
      		
	}
	
	@Test
	void testFormerRecette2() {
		Controleur cntrl=new Controleur();
		List<Recette>rctsAttendus;
		List<Recette>rctCalculées;
		FormEvent evt=new FormEvent(this,"tarte au citron","citron",1,
				"tartes","avecCuisson",
				"acidulée trop", true,null);
 Recette rctCalc= cntrl.formerNouveauRecette(evt);
Recette rctAttnd=new Recette(3,null,"tarte au citron",true,"citron",CalorieCateg.diététique,
		PastryCateg.tartes,Cuisson.avecCuisson,"acidulée trop"); 
  System.out.println("RECETTE CALCULEE:");
  cntrl.afficherRecette(rctCalc);
  System.out.println("% RECETTE ATTENDUE %");
  cntrl.afficherRecette(rctAttnd);
      		Assert.assertEquals(rctAttnd,rctCalc);
      		
	}
	
	@Test
	void testFormerRecette3() {
		Controleur cntrl=new Controleur();
		List<Recette>rctsAttendus;
		List<Recette>rctCalculées;
		FormEvent evt=new FormEvent(this,null,"chocolat",0,
				"petitsFours","avecCuisson",
				null, true,null);
Recette rctCalc= cntrl.formerNouveauRecette(evt);
Recette rctAttnd=new Recette(5,null,null,true,"chocolat",null,
		PastryCateg.petitFours,Cuisson.avecCuisson,null); 
  System.out.println("RECETTE CALCULEE:");
  cntrl.afficherRecette(rctCalc);
  System.out.println("% RECETTE ATTENDUE %");
  cntrl.afficherRecette(rctAttnd);
      		Assert.assertEquals(rctAttnd,rctCalc);
 	
	}	
	
	@Test
	void testFormerRecette4() {
		Controleur cntrl=new Controleur();
		List<Recette>rctsAttendus;
		List<Recette>rctCalculées;
		FormEvent evt=new FormEvent(this,null,"fromage blanc",0,
				null,null,
				null, true,null);
 Recette rctCalc= cntrl.formerNouveauRecette(evt);
Recette rctAttnd=new Recette(7,null,null,true,"fromage blanc",null,
		null,null,null); 
  System.out.println("RECETTE CALCULEE:");
  cntrl.afficherRecette(rctCalc);
  System.out.println("% RECETTE ATTENDUE %");
  cntrl.afficherRecette(rctAttnd);
      		Assert.assertEquals(rctAttnd,rctCalc);
 	
	}	
	
	@Test
	void testFormerRecette5() {
		Controleur cntrl=new Controleur();
		List<Recette>rctsAttendus;
		List<Recette>rctCalculées;
		FormEvent evt=new FormEvent(this,null,null,0,
				null,null,
				null, true,"monFichier.txt");
	
 Recette rctCalc= cntrl.formerNouveauRecette(evt);
 
Recette rctAttnd=new Recette(9,"monFichier.txt",null,true,null,null,
		null,null,null); 
  System.out.println("RECETTE CALCULEE:");
  cntrl.afficherRecette(rctCalc);
  System.out.println("% RECETTE ATTENDUE %");
  cntrl.afficherRecette(rctAttnd);
      		Assert.assertEquals(rctAttnd,rctCalc);
 	
	}	
	@Test
	
	void testFormerRecette6() {
		Controleur cntrl=new Controleur();
		List<Recette>rctsAttendus;
		List<Recette>rctCalculées;
		FormEvent evt=new FormEvent(this,null,null,0,
				null,null,
				null, true,null);
System.out.println("ClrCtg:"+CalorieCateg.diététique.ordinal());		
 Recette rctCalc= cntrl.formerNouveauRecette(evt);
 
Recette rctAttnd=new Recette(11,null,null,true,null,null,
		null,null,null); 
  System.out.println("RECETTE CALCULEE:");
  cntrl.afficherRecette(rctCalc);
  System.out.println("% RECETTE ATTENDUE %");
  cntrl.afficherRecette(rctAttnd);
      		Assert.assertEquals(rctAttnd,rctCalc);
 	
	}	
	
@Test
	
	void testFormerRecette7() {
		Controleur cntrl=new Controleur();
		List<Recette>rctsAttendus;
		List<Recette>rctCalculées;
		FormEvent evt=new FormEvent(this,null,null,0,
				null,null,
				null, true,"myFile.txt");
		
 Recette rctCalc= cntrl.formerNouveauRecette(evt);
Recette rctAttnd=new Recette(13,"myFile.txt",null,true,null,null,
		null,null,null); 
  System.out.println("RECETTE CALCULEE:");
  cntrl.afficherRecette(rctCalc);
  System.out.println("% RECETTE ATTENDUE %");
  cntrl.afficherRecette(rctAttnd);
      		Assert.assertEquals(rctAttnd,rctCalc);
 	
	}	
}
