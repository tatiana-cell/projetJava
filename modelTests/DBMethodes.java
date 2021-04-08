package modelTests;

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
import java.util.LinkedList;
import java.util.List;

import gui.Utils;
import model.CalorieCateg;
import model.Cuisson;
import model.PastryCateg;
import model.Recette;


public class DBMethodes {
	private Connection cnx;
	public List findRecette(Recette rct) throws SQLException {
		CalorieCateg calorieCateg = null;
		PastryCateg pastryCateg=null;
		Cuisson cuissonCateg=null;
		String calorieCategStr,pastryCategStr,cuissonCategStr;
	    List<Recette> recettes=new LinkedList<Recette>();
		//recettes.clear();
		
		String sql=formerRequete(rct);
		System.out.println(sql);
		Statement findStmt=cnx.createStatement();
		ResultSet results=findStmt.executeQuery(sql);
		String[]colonnes= {"titre","ingridient","calorieCategory",
				"pastryCategory","cuisson","commentaire"};
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
		return recettes;	
		
	}
	public void save(List<Recette>recettes) throws SQLException
	{
		String calorieCategoryStr,pastryCategoryStr,cuissonStr;
	
String insertSql="INSERT INTO RECETTES (id,path,titre,isIngrd,"
				+ "ingridient,calorieCategory,pastryCategory,"
				+ "cuisson,commentaire)VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertStmt=cnx.prepareStatement(insertSql);

String clrTbl="TRUNCATE RECETTES;";
PreparedStatement truncTblStmt=cnx.prepareStatement(clrTbl);
	truncTblStmt.executeUpdate();	
	
		for(Recette rct:recettes)
		{
			int id=rct.getId();
		
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
			 
			 	
			
		}
	
		insertStmt.close();
	
		truncTblStmt.close();
	}
	
public List load() throws SQLException
	
	{   
	    List<Recette>recettes=new LinkedList<Recette>();
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
		return recettes;
	    
	}
	public void saveToFile(File file, List<Recette>recettes) throws IOException 
	{
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		Recette[]rcttes=recettes.toArray(new Recette[recettes.size()]);
		oos.writeObject(rcttes);
		oos.close();
	}	

public List loadFromFile(File file) throws IOException, ClassNotFoundException {
	List<Recette>recettes=new LinkedList<Recette>();
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
return recettes;
}	
public String listToString(List<Recette>recettes)
{ String rctStr="";
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
	return rctStr;
}	
	
	public String formerRequete(Recette rct)
	{
		String ingridientStr=null,
		        calorieCatStr = null,
				pastryCatStr=null,
				cuissonCatStr=null,
				commentStr=null,
				titreStr=null;
		
		String rqt="SELECT * FROM RECETTES WHERE ";
		titreStr=rct.getTitre();
	    ingridientStr=rct.getIngridient();
		CalorieCateg calorCategory=rct.getCalorieCateg();
		if(calorCategory!=null) calorieCatStr=calorCategory.toString();
		PastryCateg pastryCateg=rct.getPastryCateg();
		if(pastryCateg!=null)pastryCatStr=pastryCateg.toString();
		Cuisson cuiss=rct.getGateauCuisson();
		if(cuiss!=null)cuissonCatStr=cuiss.toString();
		commentStr=rct.getCommentaire();
	
			
		String[]colomns= {"titre","ingridient","calorieCategory",
				"pastryCategory","cuisson","commentaire"};
		String[]attribs= {titreStr,ingridientStr,calorieCatStr,pastryCatStr,cuissonCatStr,commentStr};
	    int i=0;
		String strings="";
		for( i=0;i<attribs.length;i++)
		{
			if(attribs[i]==null||attribs[i].isEmpty()) strings+="n";
			else strings+="s";
		}
		
		int indxPrmrStr=strings.indexOf('s');
		int indxDrnrStr=strings.lastIndexOf('s');
		String debut="";
		if(strings.contains("s"))
		{	
		if(indxPrmrStr==indxDrnrStr) 
			{
			 debut=colomns[indxPrmrStr]+"="+"\'"+attribs[indxPrmrStr]+"\'"+";";
			 rqt+=debut;
			 
			}
		else
			{
			  int indxScndStr=strings.indexOf('s', indxPrmrStr+1);
			  String srqt="";
			  debut=colomns[indxPrmrStr]+"="+"\'"+attribs[indxPrmrStr]+"\'";
			  for(i=indxScndStr;i<=indxDrnrStr;i++)
			  {
				  if(strings.charAt(i)=='s') srqt+="and "+colomns[i]+"="+"\'"+attribs[i]+"\'"; 
					  
			  }
			    rqt+=debut+srqt+";";
			
			}
		
		}
		else rqt="Select * from recettes;";
		
		return rqt;
		
}
	public  void afficher(Recette rct)
	{
	   String rqt=formerRequete(rct);	
		System.out.println(rqt);
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
		  String url="jdbc:mysql://localhost:3306/bd_recettes_bis?useSSL=false";
		    cnx=DriverManager.getConnection(url, "root","mysql");
		    System.out.println("La connection à bd_recettes_bis est reussie:"+cnx);
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
