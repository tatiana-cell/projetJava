package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.PastryCateg;
import model.Recette;

public class TablePanel extends JPanel {
   private JTable table;
   private RecetteTableModel tableModel;
   private JPopupMenu popup;
   private JMenuItem removeItem;
   private TablePanelListener tablePanelListener;
   public TablePanel()
   {
	tableModel=new RecetteTableModel();   
   table=new JTable(tableModel);
   table.setRowHeight(22);
   table.setDefaultRenderer(PastryCateg.class, new PastryCategRenderer());
   table.setDefaultEditor(PastryCateg.class,new PastryCategEditor());
   table.getColumn("Path").setCellRenderer(new ButtonRenderer());
   table.getColumn("Path").setCellEditor(new ButtonEditor(new JCheckBox()));
   popup=new JPopupMenu();
   removeItem=new JMenuItem("Delete row");
   popup.add(removeItem);
   table.addMouseListener(new MouseAdapter() {

	@Override
	public void mousePressed(MouseEvent e) {
		int row=table.rowAtPoint(e.getPoint());
		
		table.getSelectionModel().setSelectionInterval(row,row);
	  if(e.getButton()==MouseEvent.BUTTON3)
	  {popup.show(table,e.getX(),e.getY());}
	}

	public void mouseClicked(MouseEvent e) {
      int row=table.rowAtPoint(e.getPoint());
		table.getSelectionModel().setSelectionInterval(row,row);
		if(e.getButton()==MouseEvent.MOUSE_CLICKED)
		{
		//System.out.println("row clicked:"+e.getX());
		System.out.println("column clicked:"+e.getY());
		}
	}
	   
   });
   
   removeItem.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent arg0) {
		int row=table.getSelectedRow();
		int id= (int) table.getValueAt(row, 0);
		//System.out.println("Selected row:"+row);
		//System.out.println("Selected ID:"+id);
		if(tablePanelListener!=null) {
			tablePanelListener.rowDeleted(row);
			tableModel.fireTableRowsDeleted(row, row);
			try {
				tablePanelListener.recetteDeleted(id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
			
		}
	}
	   
   });
 
   setLayout(new BorderLayout());
   add(new JScrollPane(table),BorderLayout.CENTER);
   }
    
   public void setData(List<Recette>db)
   {
	  tableModel.setData(db); 
   }
public void refresh() {
	tableModel.fireTableDataChanged();
}

public void setTablePanelListener(TablePanelListener tablePanelListener) {
	this.tablePanelListener=tablePanelListener;
}
   
}
