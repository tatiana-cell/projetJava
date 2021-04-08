package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	private JButton btnSave;
	private JButton btnRefresh;
	private TxtPanel txtPanel;
	private ToolbarListener toolbarListener;
	
	public Toolbar()
	{
		setBorder(BorderFactory.createEtchedBorder());
		btnSave=new JButton("Save");
		btnSave.setIcon(createIcon("/images/save_bd2.png"));
		btnRefresh=new JButton("Actualiser");
		btnRefresh.setIcon(createIcon("/images/rafraichir_neutre.png"));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(btnSave);
		btnSave.addActionListener(this);
		add(btnRefresh);
		btnRefresh.addActionListener(this);
	}

	private Icon createIcon(String path) {
		URL url=getClass().getResource(path);
		  if(url==null)
		  {
			  System.err.println("Unable to load image:"+path);
		  }
		ImageIcon icon=new ImageIcon(url);
		return icon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clicked=(JButton)e.getSource();
		if(clicked==btnSave)
		{		   
			toolbarListener.saveEventOccured();
		}
		else if(clicked==btnRefresh)
		{
			toolbarListener.refreshEventOccured();
		}
		
	}
	public void setTxtPanel(TxtPanel txtPanel)
	{
		this.txtPanel=txtPanel;
	}

	public void setToolbarListener(ToolbarListener toolbarListener) {
		this.toolbarListener=toolbarListener;
		
	}


}
