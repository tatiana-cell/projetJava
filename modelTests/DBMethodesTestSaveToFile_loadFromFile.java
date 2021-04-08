package modelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;

class DBMethodesTestSaveToFile_loadFromFile {
	List<Recette> recettes = new LinkedList<Recette>();
	
	int i=0;
	DBMethodes test=new DBMethodes();
/*
 * Test verifie enregistrement d'une List de recettes dans un fichier
 * et puis la lecture de cette liste depuis le fichier
 */
	
	@Test
	void test_1_SaveToFileAndLoadFromFile () throws IOException, ClassNotFoundException {
		List<Recette> recettes = new LinkedList<Recette>();
		Recette rct1 =new Recette(1,"cake_citron.txt", "cake citron", true, "citron", CalorieCateg.fitness,
				PastryCateg.autres, null, "trop sucr�!");
		Recette rct2 =new Recette(2,"fraisier.txt", "fraisier", true, "fraises", CalorieCateg.calorique,
				PastryCateg.g�teaux, Cuisson.avecCuisson, "trop bon!!!");
		String recetteOutStrAttnd="1 cake_citron.txt cake citron true citron fitness autres null trop sucr�!"+"\n"
				+ "2 fraisier.txt fraisier true fraises calorique g�teaux avecCuisson trop bon!!!"+"\n";
		recettes.add(rct1);
		recettes.add(rct2);
		
		File f=new File("mes_recettes_"+i+".txt");
		test.saveToFile(f, recettes);
		List<Recette>recettesSortis=test.loadFromFile(f);
		String rctSortiesStrCalcul�e=test.listToString(recettesSortis);
		System.out.println("String calcul�e:");
		System.out.println(rctSortiesStrCalcul�e);
		System.out.println("String attendues:");
		System.out.println(recetteOutStrAttnd);
		Assert.assertEquals("erreur d'ecriture", recetteOutStrAttnd, rctSortiesStrCalcul�e);
		i+=1;	
	}
	@Test
	void test_2_SaveToFileAndLoadFromFile () throws IOException, ClassNotFoundException {
		List<Recette> recettes = new LinkedList<Recette>();
		Recette rct1 =new Recette(0,"cake_citron.txt", "cake citron", true, "citron", CalorieCateg.fitness,
				PastryCateg.autres, null,null);
		Recette rct2 =new Recette(0,"fraisier.txt", "fraisier", true, "fraises", CalorieCateg.calorique,
				PastryCateg.g�teaux, Cuisson.avecCuisson,null);
		String recetteOutStrAttnd="0 cake_citron.txt cake citron true citron fitness autres null null"+"\n"
				+ "0 fraisier.txt fraisier true fraises calorique g�teaux avecCuisson null"+"\n";
		recettes.add(rct1);
		recettes.add(rct2);
		
		File f=new File("mes_recettes_"+i+".txt");
		test.saveToFile(f, recettes);
		List<Recette>recettesSortis=test.loadFromFile(f);
		String rctSortiesStrCalcul�e=test.listToString(recettesSortis);
		System.out.println("String calcul�e:");
		System.out.println(rctSortiesStrCalcul�e);
		System.out.println("String attendues:");
		System.out.println(recetteOutStrAttnd);
		Assert.assertEquals("erreur d'ecriture", recetteOutStrAttnd, rctSortiesStrCalcul�e);
		i+=1;	
	}
	
	@Test
	void test_3_SaveToFileAndLoadFromFile () throws IOException, ClassNotFoundException {
		List<Recette> recettes = new LinkedList<Recette>();
		Recette rct1 =new Recette(101,null, null, false, null, null,
				null, null,null);
		Recette rct2 =new Recette(0,null, "fraisier", true, null, null,
				null, null,"lourde!!!");
		String recetteOutStrAttnd="101 null null false null null null null null"+"\n"
				+ "0 null fraisier true null null null null lourde!!!"+"\n";
		recettes.add(rct1);
		recettes.add(rct2);
		
		File f=new File("mes_recettes_"+i+".txt");
		test.saveToFile(f, recettes);
		List<Recette>recettesSortis=test.loadFromFile(f);
		String rctSortiesStrCalcul�e=test.listToString(recettesSortis);
		System.out.println("String calcul�e:");
		System.out.println(rctSortiesStrCalcul�e);
		System.out.println("String attendues:");
		System.out.println(recetteOutStrAttnd);
		Assert.assertEquals("erreur d'ecriture", recetteOutStrAttnd, rctSortiesStrCalcul�e);
		i+=1;	
	}
	
	@Test
	void test_4_SaveToFileAndLoadFromFile () throws IOException, ClassNotFoundException {
		List<Recette> recettes = new LinkedList<Recette>();
		Recette rct1 =new Recette(0,null, null, false, null, null,
				null, null,null);
		Recette rct2 =new Recette(0,null, null, true, null, null,
				null, null,null);
		String recetteOutStrAttnd="0 null null false null null null null null"+"\n"
				+ "0 null null true null null null null null"+"\n";
		recettes.add(rct1);
		recettes.add(rct2);
		
		File f=new File("mes_recettes_"+i+".txt");
		test.saveToFile(f, recettes);
		List<Recette>recettesSortis=test.loadFromFile(f);
		String rctSortiesStrCalcul�e=test.listToString(recettesSortis);
		System.out.println("String calcul�e:");
		System.out.println(rctSortiesStrCalcul�e);
		System.out.println("String attendues:");
		System.out.println(recetteOutStrAttnd);
		Assert.assertEquals("erreur d'ecriture", recetteOutStrAttnd, rctSortiesStrCalcul�e);
		i+=1;	
	}
	@Test
	void test_5_SaveToFileAndLoadFromFile () throws IOException, ClassNotFoundException {
		List<Recette> recettes = new LinkedList<Recette>();
		Recette rct1 =new Recette(10001,"cookies.txt", null, true, "chocolat", null,
				null, null,null);
		Recette rct2 =new Recette(101,null, null, true, null, null,
				null, null,"bon march�");
		String recetteOutStrAttnd="10001 cookies.txt null true chocolat null null null null"+"\n"
				+ "101 null null true null null null null bon march�"+"\n";
		recettes.add(rct1);
		recettes.add(rct2);
		
		File f=new File("mes_recettes_"+i+".txt");
		test.saveToFile(f, recettes);
		List<Recette>recettesSortis=test.loadFromFile(f);
		String rctSortiesStrCalcul�e=test.listToString(recettesSortis);
		System.out.println("String calcul�e:");
		System.out.println(rctSortiesStrCalcul�e);
		System.out.println("String attendues:");
		System.out.println(recetteOutStrAttnd);
		Assert.assertEquals("erreur d'ecriture", recetteOutStrAttnd, rctSortiesStrCalcul�e);
		i+=1;	
	}
		
}
