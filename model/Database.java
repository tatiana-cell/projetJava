package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import gui.Utils;

public class Database {
	private List<Recette>recettes;
	private Connection cnx;
	public Database()
	{recettes=new LinkedList();}

	public List getRecettes()
	{return Collections.unmodifiableList(recettes);}
	
	public void addRecette(Recette recette)
	{recettes.add(recette);	}
	
	public void saveToFile(File file) throws IOException 
	{
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		Recette[]rcttes=recettes.toArray(new Recette[recettes.size()]);
		oos.writeObject(rcttes);
		oos.close();
	}
	public void loadFromFile(File file) throws IOException, ClassNotFoundException {
		
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois=new ObjectInputStream(fis);
	try {	
			Recette[]rcttes=(Recette[])ois.readObject();
			recettes.clear();
			recettes.addAll(Arrays.asList(rcttes));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	  ois.close();
	}


	public void showRecettes()
    {String rctStr="";
	  String catCalorStr,pastryCategStr,cuissonCategStr;
    	for(Recette rct:recettes)
    	{
    		int id=rct.getId();
    		String path=rct.getPath();
    		String titre=rct.getTitre();
    		boolean isIngr=rct.isIngrid();
    		String ingrd=rct.getIngridient();
    		CalorieCateg catCalor=rct.getCalorieCateg();
    				if(catCalor!=null) catCalorStr=catCalor.toString();
    		else catCalorStr=null;
    		PastryCateg pastryCateg=rct.getPastryCateg();
    		if(pastryCateg!=null)pastryCategStr=pastryCateg.toString();
    		else pastryCategStr=null;
    		Cuisson cuissonCateg=rct.getGateauCuisson();
    		if(cuissonCateg!=null)cuissonCategStr=cuissonCateg.toString();
    		else cuissonCategStr=null;
    		String comment=rct.getCommentaire();
    		rctStr+=id+" "+path+" "+titre+" "+isIngr+" "+ingrd+" "+catCalorStr
    		+" "+pastryCategStr+" "+cuissonCategStr+" "+comment+"\n";
    		  	    		
     	}
    	System.out.println(rctStr);
    }
	//extracting the data from SQL database and loading in LinkedList of our DataBase
	public void load() throws SQLException
	
	{
		CalorieCateg calorieCateg=null;
		Cuisson cuissonCateg=null;
		PastryCateg pastryCateg=null;
		String calorieCategoryStr,pastryCategoryStr;
		recettes.clear();
		String sql="SELECT id,path,titre,isIngrd,ingridient,calorieCategory,pastryCategory,cuisson,commentaire FROM RECETTES order by titre";
	    Statement selectStmt=cnx.createStatement();
	    ResultSet results=selectStmt.executeQuery(sql);
	     while(results.next())
	     {
	    	 int id=results.getInt("id");
	    	 String path=results.getString("path");
	    	 String titre=results.getString("titre");
	    	 boolean isIngrd=results.getBoolean("isIngrd");
	    	 String ingridient=results.getString("ingridient");
	    	 calorieCategoryStr=results.getString("calorieCategory");
	    	 if(calorieCategoryStr!=null) calorieCateg=CalorieCateg.valueOf(calorieCategoryStr);
	    	 pastryCategoryStr=results.getString("pastryCategory");
	    	 if(pastryCategoryStr!=null)pastryCateg=PastryCateg.valueOf(pastryCategoryStr);
	    	 String cuissonStr=results.getString("cuisson");
	    	 if(cuissonStr!=null)cuissonCateg=Cuisson.valueOf(cuissonStr);
	    	 String commentStr=results.getString("commentaire");
	    	 String fileName=Utils.getFileName(path);
	    	 Recette rct=new Recette(id,path,titre,isIngrd,
		    			ingridient,calorieCateg,pastryCateg,cuissonCateg,
		    			commentStr);
	    	recettes.add(rct);
	    	
	       
	     }
	    results.close();
	    selectStmt.close();
	    
	}
	public void findRecette(Recette rct) throws SQLException {
		CalorieCateg calorieCateg = null;
		PastryCateg pastryCateg=null;
		Cuisson cuissonCateg=null;
		String calorieCategStr,pastryCategStr,cuissonCategStr;
		recettes.clear();
		String sql=formerRequete(rct);
		Statement findStmt=cnx.createStatement();
		ResultSet results=findStmt.executeQuery(sql);
		//String[]colonnes= {"titre","ingridient","calorieCategory",
		//		"pastryCategory","cuisson","commentaire"};
		while(results.next())
		{
			int id=results.getInt("id");
			 String path=results.getString("path");
			String titre=results.getString("titre");
			 boolean isIngrd=results.getBoolean("isIngrd");
			String ingridient=results.getString("ingridient");
			calorieCategStr=results.getString("calorieCategory");
			if(calorieCategStr!=null) calorieCateg=calorieCateg.valueOf(calorieCategStr);
			pastryCategStr=results.getString("pastryCategory");
			if(pastryCategStr!=null) pastryCateg=pastryCateg.valueOf(pastryCategStr);
			cuissonCategStr=results.getString("cuisson");
			if(cuissonCategStr!=null) cuissonCateg=cuissonCateg.valueOf(cuissonCategStr);
			String commentaireStr=results.getString("commentaire");
			Recette recette=new Recette(id,path,titre,isIngrd,ingridient,calorieCateg,pastryCateg,cuissonCateg,commentaireStr);
			recettes.add(recette);
		}
		results.close();
		findStmt.close();	
		
	}
	
/* pour trouver une recette on va
 *  former une requete de selection selon 
 *  les parametres des controls de la panel
 */
	public String formerRequete(Recette rct)
	{
		String ingridientStr=null,
		        calorieCat = null,
				pastryCat=null,
				cuissonCat=null,
				commentStr=null,
				titreStr=null;
		//String rqt2="SELECT id,path,titre,isIngrd,ingridient,calorieCategory,pastryCategory,cuisson,commentaire FROM RECETTES WHERE ";
		String rqt="SELECT * FROM RECETTES WHERE ";
		titreStr=rct.getTitre();
		//System.out.println("titreStr="+titreStr);
	    ingridientStr=rct.getIngridient();
		CalorieCateg calorCategory=rct.getCalorieCateg();
		if(calorCategory!=null) calorieCat=calorCategory.toString();
		PastryCateg pastryCateg=rct.getPastryCateg();
		if(pastryCateg!=null)pastryCat=pastryCateg.toString();
		Cuisson cuiss=rct.getGateauCuisson();
		if(cuiss!=null)cuissonCat=cuiss.toString();
	    commentStr=rct.getCommentaire();
			String[]colonnes={"titre","ingridient","calorieCategory",
				"pastryCategory","cuisson","commentaire"};
		String[]attribs= {titreStr,ingridientStr,calorieCat,pastryCat,cuissonCat,commentStr};
	    int i=0;
		String strings="";
		for( i=0;i<attribs.length;i++)
		{
			if(attribs[i]==null||attribs[i].isEmpty()) strings+="n";
			else strings+="s";
		}
		if(strings.contains("s"))
		{
		   System.out.println("strings contain s");
			int indxPrmrStr=strings.indexOf('s');
		   int indxDrnrStr=strings.lastIndexOf('s');
		   String debut="";
		   if(indxPrmrStr==indxDrnrStr) 
			{
			 debut+=colonnes[indxPrmrStr]+" like "+"\'%"+attribs[indxPrmrStr]+"%\'"+";"; 
			 rqt+=debut;
			}
		   else
			{
			  int indxScndStr=strings.indexOf('s', indxPrmrStr+1);
			  String srqt="";
			  debut=colonnes[indxPrmrStr]+"="+"\'"+attribs[indxPrmrStr]+"\'";
			  for(i=indxScndStr;i<=indxDrnrStr;i++)
			  {
				  if(strings.charAt(i)=='s') srqt+="and "+colonnes[i]+"="+"\'"+attribs[i]+"\'"; 
					  
			  }
			  
			   rqt+=debut+srqt+";";
			}
		}else rqt="Select * from recettes;";
		return rqt;
	}
		
	
	/*
	 * saves the data from our database to SQL database:

	 */
		
		public void save() throws SQLException
	{
		String calorieCategoryStr,pastryCategoryStr,cuissonStr;
		String checkSql="SELECT COUNT(*) AS COUNT FROM RECETTES WHERE id=?";
		PreparedStatement checkStmt=cnx.prepareStatement(checkSql);
	
String insertSql="INSERT INTO RECETTES (id,path,titre,isIngrd,"
				+ "ingridient,calorieCategory,pastryCategory,"
				+ "cuisson,commentaire)VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertStmt=cnx.prepareStatement(insertSql);
		
String updateSql="UPDATE RECETTES SET titre=?,isIngrd=?,ingridient=?,calorieCategory=?,pastryCategory=?,cuisson=?,commentaire=?,path=? WHERE id=?";
		PreparedStatement updateStmt=cnx.prepareStatement(updateSql);
		System.out.println("***********nombre de recettes est:"+recettes.size());	
		for(Recette rct:recettes)
		{
			int id=rct.getId();
			System.out.println("The ID is "+id);
			String titre=rct.getTitre();
    		boolean isIngrd=rct.isIngrid();
    		String ingridient=rct.getIngridient();
    		CalorieCateg calorCat=rct.getCalorieCateg();
    		if(calorCat!=null) calorieCategoryStr=calorCat.name();
    		else calorieCategoryStr=null;
    		PastryCateg pastryCat=rct.getPastryCateg();
    		if(pastryCat!=null)  pastryCategoryStr=pastryCat.name();
    		else pastryCategoryStr=null;
    		Cuisson cuissonCat=rct.getGateauCuisson();
    		if(cuissonCat!=null) cuissonStr=cuissonCat.name();
    		else cuissonStr=null;
			String comment=rct.getCommentaire();
			String path=rct.getPath();
            checkStmt.setInt(1,id);
          	ResultSet rslt=checkStmt.executeQuery();
			rslt.next();
			int countId=rslt.getInt(1);
			
			if(countId==0)
			{
			 System.out.println("Insertion de recette avec id "+id);	
			 int col=1;
			 insertStmt.setInt(col++,id);
			 insertStmt.setString(col++, path);
			 insertStmt.setString(col++,titre);
			 insertStmt.setBoolean(col++,isIngrd);
			 insertStmt.setString(col++,ingridient);
			 insertStmt.setString(col++,calorieCategoryStr);
			 insertStmt.setString(col++, pastryCategoryStr);
			 insertStmt.setString(col++, cuissonStr);
			 insertStmt.setString(col++,comment);
			
			 insertStmt.executeUpdate();
			 	
			}else {
			System.out.println("Updating de recette avec id "+id);	
			 int col=1;
			
			 updateStmt.setString(col++,titre);
			 updateStmt.setBoolean(col++,isIngrd);
			 updateStmt.setString(col++,ingridient);
			 updateStmt.setString(col++,calorieCategoryStr);
			 updateStmt.setString(col++,pastryCategoryStr);
			 updateStmt.setString(col++,cuissonStr);
			 updateStmt.setString(col++,comment);
			 updateStmt.setString(col++, path);
			 updateStmt.setInt(col++,id); 
			 
			 updateStmt.executeUpdate();
			}
		}
		updateStmt.close();
		insertStmt.close();
		checkStmt.close();
		
	}
	public void deleteRecette(int idnt) throws SQLException
	{
		String sql="DELETE FROM RECETTES WHERE id=?";
		PreparedStatement deleteStmt=cnx.prepareStatement(sql);
		deleteStmt.setInt(1,idnt);
		deleteStmt.execute();
	}

	public void removeRecette(int row) {
		recettes.remove(row);
		
	}

	public void connect() throws Exception {
	 if(cnx!=null)return;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("Le gestionnaire de pilotes est connecté!");
	  
	  } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		throw new Exception("Driver not Found");
	}
	  String url="jdbc:mysql://localhost:3306/bd_recettes?useSSL=false";
	    cnx=DriverManager.getConnection(url, "root","mysql");
	   // System.out.println("La connection à BD recettes est reussie:"+cnx);
	}

	public void disconnect() {
	   if(cnx!=null)
	   {
		   try {
			cnx.close();
		} catch (SQLException e) {
			System.out.println("Cannot close connection");
		}
	   }
	}

	
	


    
}
