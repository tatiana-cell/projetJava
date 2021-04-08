package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import controleur.Controleur;

public class MainFrame extends JFrame {

	private ToolbarListener toolbarListener;
	private Toolbar toolBar;
	private TxtPanel txtPanel; 
	private FormPanel formPanel;
	private TablePanel tablePanel;
	private JFileChooser fileChooser;
	private Controleur controleur;
	private PrefsDialog prefsDialog;
	private Preferences prefs; 
	private JSplitPane splitPane;
	private JTabbedPane tabPane;
	
	public MainFrame()
	{
		super("My Recette");
		fileChooser=new JFileChooser();
		fileChooser.addChoosableFileFilter(new RecetteFileFilter());
		controleur=new Controleur();
		formPanel =new FormPanel();
		txtPanel=new TxtPanel();
		tablePanel=new TablePanel();
		tablePanel.setData(controleur.getRecettes());
//////////////////////////////////////////////////////////////////////////		
		tabPane=new JTabbedPane();
		tabPane.addTab("Recettes BD", tablePanel);
		tabPane.addTab("Messages",txtPanel);
		splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,formPanel,tabPane);
	    splitPane.setOneTouchExpandable(true);
		prefs=Preferences.userRoot().node("db");
		prefsDialog=new PrefsDialog(this);
		prefsDialog.setPrefsListener(new PrefsListener() {
			public void preferencesSet(String user, String password, int port) {
			  System.out.println("user:"+user+"psw:"+password+"port:"+port);
			  prefs.put("user", user);
			  prefs.put("password", password);
			  prefs.putInt("port",port);
				
			}
		});
		String user=prefs.get("user","");
		String password=prefs.get("password","");
		Integer portInt=prefs.getInt("port",3306);
		int port=portInt;
		prefsDialog.setDefaults(user, password, port);
		
		toolBar=new Toolbar();
		setJMenuBar(createMenuBar());
		toolBar.setTxtPanel(txtPanel);
		toolBar.setToolbarListener(new ToolbarListener() {
			public void saveEventOccured() {
			connect();	
	
			try {
				controleur.save();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Cannot Save to DataBase",
						 "probleme de sauveguarde,vérifiez ajout d'un fichier",JOptionPane.ERROR_MESSAGE);}
			
			  try {
				controleur.save();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			}
		
			public void refreshEventOccured() {
			  connect();
			  try {
				controleur.load();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			  
			 try {
				controleur.load();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Cannot Load from DataBase",
						 "DBase loading problem",JOptionPane.ERROR_MESSAGE);}
			  tablePanel.refresh();
			}
		});
		formPanel.setFormPanelListener(new FormPanelListener() {
			public void addRecetteEventOccured(FormEvent evt)
			{
				controleur.addRecette(evt);
				tablePanel.refresh();
		
		
			}

			@Override
			public void findRecetteEventOccured(FormEvent evt) {
				try {
					controleur.findRecette(evt);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				tablePanel.refresh();
				
			}
			
		});
		tablePanel.setTablePanelListener(new TablePanelListener() {
			public void rowDeleted(int row) {
				controleur.removeRecette(row);	
				//System.out.println("row to remove:"+row);
			}

			@Override
			public void recetteDeleted(int id) throws SQLException {
				controleur.deleteRecette(id);
			}
			
		});

		setLayout(new BorderLayout());
	
		add(toolBar,BorderLayout.PAGE_START);
		add(splitPane,BorderLayout.CENTER);
		
      	setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}//FIN CONSTR

private void connect() {
	try {
		controleur.connect();
		} catch (Exception e) {
		 JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to DataBase",
				 "Dbase connection problem",JOptionPane.ERROR_MESSAGE);
		}
}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
	
		JMenuItem exportDataItem=new JMenuItem("Export Data....");
		JMenuItem importDataItem=new JMenuItem("Import Data....");
		JMenuItem exitItem=new JMenuItem("Exit");
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu windowMenu=new JMenu("Window");
		JMenuItem prefsItem=new JMenuItem("Preferences....");
		windowMenu.add(prefsItem);
		  prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
				
			}
			  
		  });
		
		JMenu showMenu=new JMenu("Show");
		JCheckBoxMenuItem showFormItem=new JCheckBoxMenuItem("Recette Forme");
		showFormItem.setSelected(true);
		  showMenu.add(showFormItem);
		 windowMenu.add(showMenu); 
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION)
					try {
						controleur.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,"On n'arrive pas"
								+ " télécharger les datas du fichier", "Erreur",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
			  
		  }
			});
		  exportDataItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(fileChooser.showSaveDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION)
					
					{
						try {
							controleur.saveToFile(fileChooser.getSelectedFile());
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(MainFrame.this, "On n'arrive pas"
									+ " enregistrer données dans le fichier","Erreur",JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}
	  }
				});  
	
		  exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=JOptionPane.showInputDialog(MainFrame.this,"Entrez votre nom de User:","UserNom",
				JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE);
				System.out.println(text);
				int action=JOptionPane.showConfirmDialog(MainFrame.this,"Voulez-vous vraiment quitter l'application?",
						"ConfirmExit",JOptionPane.OK_OPTION);
			    if(action==JOptionPane.OK_OPTION)	
				{System.exit(0);}
				
			}
				});
		
		    showFormItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JCheckBoxMenuItem menuItem=(JCheckBoxMenuItem)e.getSource();
					if(menuItem.isSelected())
					{
						splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
					}
					formPanel.setVisible(menuItem.isSelected());
				}
		    	
		    });
		return menuBar;
	}
	

	




}
