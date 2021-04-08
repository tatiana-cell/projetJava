package model;

import java.io.Serializable;

public class Recette implements Serializable{
	
	private static int count=1;
	
	 private int id;
	 private String titre;
	 private String ingridient;
	 private CalorieCateg calorieCateg;
	 private PastryCateg pastryCateg;
	 private Cuisson cuissonCateg;
	 private String commentaireStr;
	 private boolean isIngrd;
	 private String path;
	
	public Recette(String path,String titre,boolean isIngrd,String ingridient,CalorieCateg calorieCateg,
	PastryCateg pastryCateg,Cuisson cuissonCateg,String commentaire) 
	 
	 
	 {
		 this.path=path;
		 this.titre=titre;
		 this.ingridient=ingridient;
		 this.calorieCateg=calorieCateg;
		 this.pastryCateg=pastryCateg;
		 this.cuissonCateg=cuissonCateg;
		 this.commentaireStr=commentaire;
		 this.isIngrd=isIngrd;
				 
		 this.id=count;
		 count++;
	 }

  public Recette(int id, String path, String titre, boolean isIngrd, String ingridient, 
		  CalorieCateg calorieCateg,PastryCateg pastryCateg, Cuisson cuissonCateg,
		  String commentaire) 
       {
		this(path,titre,isIngrd,ingridient,calorieCateg,pastryCateg,cuissonCateg,commentaire);
		this.id=id;
	   }
 

public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getIngridient() {
		return ingridient;
	}

	public void setIngridient(String ingridient) {
		this.ingridient = ingridient;
	}



	public PastryCateg getPastryCateg() {
		return pastryCateg;
	}

	public void setPastryCateg(PastryCateg pastryCateg) {
		this.pastryCateg = pastryCateg;
	}

	public Cuisson getGateauCuisson() {
		return cuissonCateg;
	}

	public void setGateauCuisson(Cuisson cuisson) {
		this.cuissonCateg = cuisson;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Recette.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CalorieCateg getCalorieCateg() {
		return calorieCateg;
	}

	public void setCalorieCateg(CalorieCateg calorieCateg) {
		this.calorieCateg = calorieCateg;
	}

	public String getCommentaire() {
		return commentaireStr;
	}

	public void setDifficulteCateg(String commentaire) {
		this.commentaireStr=commentaire;
	}

	public boolean isIngrid() {
		return isIngrd;
	}

	public void setIngrid(boolean isIngrid) {
		this.isIngrd = isIngrid;
	}
	 
	public int hashCode()
	{
		int result=17;
		String coctan=titre+ingridient+
		commentaireStr+path;
		if(coctan!=null)
		{
			result=31*result+coctan.hashCode()+id;
		}
		return result;
	}

	public boolean equals(Object obj)
	{
		if(obj==null)return false;
		if(obj instanceof Recette && this==obj) return true;
		Recette rct=(Recette)obj;
		if(id!=rct.id) return false;
		if(path!=rct.path) return false;
		if(titre!=rct.titre)return false;
		if(isIngrd!=rct.isIngrd)return false;
		if(ingridient!=rct.ingridient)return false;
		if(calorieCateg!=rct.calorieCateg)return false;
		if(pastryCateg!=rct.pastryCateg)return false;
		if(cuissonCateg!=rct.cuissonCateg)return false;
		if(commentaireStr!=rct.commentaireStr)return false;
		
		return true;
	
	}
	 
}
