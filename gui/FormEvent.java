package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {
 private String titre;
 private String ingridient;
 private int calorCat;
 private String pastryCateg;
 private String cuisson;
 private String comment;
 private boolean isIngrd;
 private String path;
	public FormEvent(Object source) {
		super(source);	
	}
	public FormEvent(Object source,String titre,String ingridient,int calorCat,
			String pastryCateg,String cuisson,String comment, boolean isIngrd,String path) {
		super(source);	
		this.titre=titre;
		this.ingridient=ingridient;
		this.calorCat=calorCat;
		this.pastryCateg=pastryCateg;
		this.cuisson=cuisson;
		this.comment=comment;
		this.isIngrd=isIngrd;
		this.path=path;
		
	}
	public FormEvent(Object source,String titre,String ingridient,int calorCat,
			String pastryCateg,String cuisson,String comment) {
		super(source);	
		this.titre=titre;
		this.ingridient=ingridient;
		this.calorCat=calorCat;
		this.pastryCateg=pastryCateg;
		this.cuisson=cuisson;
		this.comment=comment;
		this.isIngrd=isIngrd;
		this.path=path;
		
	}	

public String getCuisson() {
	return cuisson;
	
}
public boolean getIsIngrd()
{
	return isIngrd;
}
public String getPath()
{
	return path;
}

public String getCommentaire()
{
	return comment;
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
	public int getCaloriesCategory()
	{
		return calorCat;
	}
	public String getPastryCateg()
	{
		return pastryCateg;
	}
	
	
}
