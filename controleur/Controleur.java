package controleur;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gui.FormEvent;
import model.CalorieCateg;
import model.Database;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;

public class Controleur {
	private Database db=new Database();
	private List<Recette>recettes;
	
	public List<Recette> getRecettes()
	{
		return db.getRecettes();
	}
	/*
	 * La method va recuperer tous les données des controles de panel
	 * transmises en parametre evt, puis forme une nouvelle instance 
	 * de class Recette et le transmis au Database pour ajouter au List
	 */
	
	public void addRecette(FormEvent evt)
     	{
		     Recette recette=formerNouveauRecette(evt);
			db.addRecette(recette);
		}
public void findRecette(FormEvent evt) throws SQLException 
    {
       Recette recette=formerNouveauRecette(evt);
		db.findRecette(recette);
	}
	
	public Recette formerNouveauRecette(FormEvent evt)
	{
		String path=evt.getPath();
		String titre=evt.getTitre();
		boolean isIngrd=evt.getIsIngrd();
		String ingridient=evt.getIngridient();
		int calorCatInt=evt.getCaloriesCategory();
		String pastryCatStr=evt.getPastryCateg();
		String strCuisson=evt.getCuisson();
		String comment=evt.getCommentaire();
		CalorieCateg calorieCateg=null;
		    
		switch(calorCatInt)
		{

		case 0:
			calorieCateg=null;
			break;
		case 1:
			calorieCateg=calorieCateg.diététique;
			break;
		case 2:
			calorieCateg=calorieCateg.fitness;
			break;
		case 3:
			calorieCateg=calorieCateg.calorique;
			break;
		
		}
		
		PastryCateg pastryCateg=null;
		if(pastryCatStr!=null)
		{	
		 if(pastryCatStr.equals("tartes"))
		 {
			 pastryCateg=pastryCateg.tartes;
		 }
		 else if(pastryCatStr.equals("gâteaux"))
			 pastryCateg=pastryCateg.gâteaux;
		 else if(pastryCatStr.equals("petitsFours"))
			 pastryCateg=pastryCateg.petitFours;
		 else  pastryCateg=pastryCateg.autres;
		}else pastryCateg=null;
		
		Cuisson cuisson = null;
		if(strCuisson!=null)
		{
		 if(strCuisson.equals("avecCuisson"))
		   {cuisson=cuisson.avecCuisson;	}
		 else if(strCuisson.equals("sansCuisson"))
			{cuisson=cuisson.sansCuisson;}
		}
		
		 return new Recette(path,titre,isIngrd,ingridient,calorieCateg,
					pastryCateg,cuisson,comment);
	}

	public void afficherRecette(Recette rct)
	{
	System.out.println("id="+rct.getId());
	System.out.println("path="+rct.getPath());
    System.out.println("titre="+rct.getTitre());
    System.out.println("isIngrd="+rct.isIngrid());
    System.out.println("ingridient="+rct.getIngridient());
     System.out.println("calorieCateg="+rct.getCalorieCateg());
     System.out.println("pastryCateg="+rct.getPastryCateg());
     System.out.println("cuissonCateg="+rct.getGateauCuisson());
    System.out.println("comment="+rct.getCommentaire());
	
	
	}
	public void saveToFile(File file) throws IOException
	{
		db.saveToFile(file);
	}
	public void loadFromFile(File file) throws ClassNotFoundException, IOException {
		db.loadFromFile(file);
	}
	
	public void showRecettes()
	{
		db.showRecettes();
	}
	
	public void openFile(File file) 
	{
		String abspath=file.getAbsolutePath();
		
		int debutIndx=abspath.lastIndexOf('\\')+1;
		int finIndx=abspath.length();
		String nomFichier=abspath.substring(debutIndx, finIndx);
		//System.out.println(abspath);
		//System.out.println(nomFichier);
	}
	public void removeRecette(int row) {
		db.removeRecette(row);
		
	}
  public void save() throws SQLException
  {
	  db.save();
  }
  public void load() throws SQLException
  {
	  db.load();
  }
  public void connect() throws Exception
  {
	  db.connect();
  }
  public void disconnect()
  {
	  db.disconnect();
  }
public void deleteRecette(int id) throws SQLException {
	
	db.deleteRecette(id);
	
}



}
