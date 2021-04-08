package controleur;

import java.sql.SQLException;

import model.CalorieCateg;
import model.Cuisson;
import model.Database;

import model.PastryCateg;
import model.Recette;

public class TestDataBase {

	public static void main(String[] args) throws SQLException {
		Database db=new Database();
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	db.save();	
	db.load();
		
		db.disconnect();

	}

}
