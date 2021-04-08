package modelTests;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;
import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;

public class RunDatabaseMethodes {
	
	public static void main(String[] args) throws Exception {
		List<Recette> recettes = new LinkedList<Recette>();
		List<Recette>recettesSortis;
		int i=0;
		DBMethodes test=new DBMethodes();
		
		Recette rct1 =new Recette(1,"cake_citron.txt", "cake citron", true, "citron", CalorieCateg.fitness,
				PastryCateg.autres, null, "trop sucré!");
		Recette rct2 =new Recette(2,"fraisier.txt", "fraisier", true, "fraises", CalorieCateg.calorique,
				PastryCateg.gâteaux, Cuisson.avecCuisson, "trop sucré!");
		
		recettes.add(rct1);
		recettes.add(rct2);
		/*test.showRecettes(recettes);
		File f=new File("mes_recettes_"+i+".txt");
		test.saveToFile(f, recettes);
		recettesSortis=test.loadFromFile(f);
		String rctSortiesStr=test.showRecettes(recettesSortis);
		//System.out.println(rctSortiesStr);
		i+=1;*/
		
		//******************************************************************
		
		test.connect();
		
		test.save(recettes);
		recettes=test.load();
		String recettesFromDB=null;
		if(recettes.size()>0)
			{recettesFromDB=test.listToString(recettes);}
		System.out.println(recettesFromDB);
		
		test.disconnect();
		
	}

}
