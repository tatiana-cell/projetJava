package gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import model.CalorieCateg;
import model.Cuisson;

import model.PastryCateg;
import model.Recette;

public class RecetteTableModel extends AbstractTableModel {
private List<Recette> db;
private String[]colNames= {"ID","Path","Titre","Ingrd?","Ingridient",
		"Caloricité","Categorie","Cuisson","Commentaire"};

public RecetteTableModel()
{     }

public void setData(List<Recette>db)
{
	this.db=db;
}

	public int getColumnCount() {
				return 9;
	}

	@Override
	public String getColumnName(int column) {
				return colNames[column];
	}

	@Override
	public int getRowCount() 
	{	return db.size();	}
	

	@Override
	public boolean isCellEditable(int row, int col) {
		switch(col)
		{
		case 1:
			if(getValueAt(row,col) instanceof JButton) 
				return false;
			else
			    return true;
		case 2:
			return true;
		case 3:
			return true;
		case 6:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
	   if(db==null)return;
	   Recette recette=db.get(row);
	    switch(col)
	    {
	   
	    case 2:
	    	recette.setTitre((String) value);
	    	break;
	    case 3:
	    	recette.setIngrid((Boolean)value);
	    	break;
	    case 6:
	    	recette.setPastryCateg((PastryCateg) value);
	    	break;
	    default:
	    	return;
	    }
	}

	@Override
	public Object getValueAt(int row, int col) {
		Recette rct=db.get(row);
		switch(col) {
		case 0:
			return rct.getId();
		 case 1:
			 return rct.getPath();
		 case 2:
			 return rct.getTitre();
		 case 3:
			 return rct.isIngrid();
		 case 4:
			 return rct.getIngridient();
		 case 5:
			 return rct.getCalorieCateg();
		 case 6:
			 return rct.getPastryCateg();
		 case 7:
			 return rct.getGateauCuisson();
		 case 8:
			 return rct.getCommentaire();
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int col) {
		switch(col) {
		case 0:
			return Integer.class;
		 case 1:
			 return JButton.class;
		 case 2:
			 return String.class;
		 case 3:
			 return Boolean.class;
		 case 4:
			 return String.class;
		 case 5:
			 return CalorieCateg.class;
		 case 6:
			 return PastryCateg.class;
		 case 7:
			 return Cuisson.class;
		 case 8:
			 return String.class;
		default:
			return null;
			 
		}
	}

}
