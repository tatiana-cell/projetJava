package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
	private JButton button;
	private boolean isPushed;
	private ButtonListener bListener=new ButtonListener();

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button=new JButton();
		button.setOpaque(true);
		button.addActionListener(bListener);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int col) {
		bListener.setRow(row);
		bListener.setColumn(col);
		bListener.setTable(table);
		button.setText((value==null)? "" : value.toString());
		
		return button;
	}
}


class ButtonListener implements ActionListener{
	private int row,col;
	private JTable table;
	private JButton button;
	public void setRow(int row)
	{this.row=row;}
	public void setColumn(int col)
	{this.col=col;}
	public void setTable(JTable table)
	{this.table=table;}
	public JButton getButton()
	{return this.button;}

	
	public void actionPerformed(ActionEvent e) {
		
	  int id=(int)table.getValueAt(this.row,this.col-1);
	
	  String path=((JButton)e.getSource()).getText();
	  try {
		Utils.openFile(path);
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
	
		
	}
	
}