package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import model.PastryCateg;

public class PastryCategEditor extends AbstractCellEditor implements TableCellEditor {

	private JComboBox combo;
           
	public PastryCategEditor()
	{
		combo=new JComboBox(PastryCateg.values());
	}

	@Override
	public Object getCellEditorValue() {
				return combo.getSelectedItem();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
				return true;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		  combo.setSelectedItem(value);
		  combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
			  
		  });
		return combo;
	}

}
