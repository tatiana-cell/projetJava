package gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable tablePanel,Object value,boolean isSelected,boolean hasFocus,int row,int col)
	{
	  setText((value!=null) ? value.toString(): "");
		return this;
	}

}
