package gui;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	private FormPanelListener formPanelListener;
	private JLabel titreLbl;
private JFileChooser fileChooser;

	private JTextField ingridientField;
	private JTextField commentField;
	private JLabel commentLbl;
	private JButton okBtn;
	private JButton findBtn;
	private JList caloriesList;
	private JComboBox pastryCategCombo;
	private JLabel cuissonLbl;
	private JCheckBox avecCsn;
	private JCheckBox sansCsn;
	private ButtonGroup cuissonGrp;
	private String strCuisson=null;
	private JRadioButton facile;
	private JRadioButton difficile;
	private ButtonGroup difficGrp;
	private JCheckBox ingridientCheck;
	private JTextField titreField;
	private JLabel ingridientLbl;
	private JLabel lblDificulte;
	private JLabel addLbl;
	private JButton addBtn;
	private String abspath,path,ingridient,pastryCateg;
	private String titre=null;
	private String comment=null;
	CaloriesCategory calorCateg;
	boolean isIngrd,isTicked;
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 255;
		setPreferredSize(dim);
		setMinimumSize(dim);
		addLbl=new JLabel("Ajouter:");
		addBtn=new JButton("+ fichier");
		findBtn=new JButton("Chercher");
		fileChooser=new JFileChooser();
		lblDificulte=new JLabel("difficulté:");
		ingridientCheck = new JCheckBox();
		titreLbl = new JLabel("Titre:");
		ingridientLbl = new JLabel("Ingridient:");
		titreField = new JTextField(10);
		ingridientField = new JTextField(10);
		caloriesList = new JList();
		pastryCategCombo = new JComboBox();
		cuissonLbl=new JLabel("cuisson:");
		avecCsn=new JCheckBox("avec cuisson");
		sansCsn=new JCheckBox("sans cuisson");
		cuissonGrp=new ButtonGroup();
		commentLbl=new JLabel("Commentaires");
		commentField=new JTextField(12);
        facile = new JRadioButton("facile");
        difficile = new JRadioButton("difficile");
	    difficGrp = new ButtonGroup();
		okBtn=new JButton("+ Recette");
	
		//SET UP mnemonics
		okBtn.setMnemonic(KeyEvent.VK_O);
		titreLbl.setDisplayedMnemonic(KeyEvent.VK_T);
		titreLbl.setLabelFor(titreField);

		/// SET UP ingridientCHECK/////////////////////////
		ingridientLbl.setEnabled(false);
		ingridientField.setEnabled(false);
		ingridientCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isTicked = ingridientCheck.isSelected();
				ingridientLbl.setEnabled(isTicked);
				ingridientField.setEnabled(isTicked);
			}
		});
		ingridientField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingridient=ingridientField.getSelectedText();				
			}
			
		});

		//// SET UP CaloriesCategory LIST///////////////////
		DefaultListModel caloriesModel = new DefaultListModel();
		caloriesModel.addElement(new CaloriesCategory(0,null));
		caloriesModel.addElement(new CaloriesCategory(1, "diététique"));
		caloriesModel.addElement(new CaloriesCategory(2, "fitness"));
		caloriesModel.addElement(new CaloriesCategory(3, "calorique"));
		caloriesList.setModel(caloriesModel);
		caloriesList.setSelectedIndex(0);
		caloriesList.setPreferredSize(new Dimension(110, 70));
		caloriesList.setBorder(BorderFactory.createEtchedBorder());
		/////// SET UP difficulte RADIO//////////////////////
		
		difficGrp.add(facile);
		difficGrp.add(difficile);
		facile.setSelected(false);
		facile.setActionCommand("facile");
		difficile.setActionCommand("difficile");
		

		////// SET UP Combo PastryCategory/////////////////////////
		DefaultComboBoxModel pastryCatModel = new DefaultComboBoxModel();
	    pastryCatModel.addElement(null);
		pastryCatModel.addElement("tartes");
		pastryCatModel.addElement("gâteaux");
		pastryCatModel.addElement("entremets");
		pastryCatModel.addElement("petitsFours");
		pastryCatModel.addElement("autres");
		pastryCategCombo.setModel(pastryCatModel);
		pastryCategCombo.setSelectedIndex(0);
		pastryCategCombo.setEditable(true);
		
//////////SET UP cuisson group////////////////////////////////
		avecCsn.setEnabled(false);
		sansCsn.setEnabled(false);
		cuissonLbl.setEnabled(false);
		cuissonGrp.add(avecCsn);
		cuissonGrp.add(sansCsn);
		
		titreField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
			  titre=titreField.getText();	
				
			}
			
		});
////////////SET UP Commentaires////////////////////////////////
		
		commentField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				comment=commentField.getText();
					}
			});
		
		
		
		pastryCategCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pastryCateg=(String)pastryCategCombo.getSelectedItem();
			  if(pastryCateg!=null)	
			  {	  
				if(pastryCateg.equals("gâteaux"))
				{
					cuissonLbl.setEnabled(true);
					avecCsn.setEnabled(true);
					sansCsn.setEnabled(true);
					
				}else {
					cuissonLbl.setEnabled(false);
					avecCsn.setEnabled(false);
					sansCsn.setEnabled(false);
					strCuisson=null;
				}
				System.out.println("categ="+pastryCateg);
			  }else pastryCateg=null;	
			}
		   
		   
		});
		avecCsn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean avecCuisson=avecCsn.isSelected();
				if(avecCuisson)  strCuisson="avecCuisson";
				else strCuisson=null;
			}
		});
	
		sansCsn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			boolean sansCuisson=sansCsn.isSelected();
			if(sansCuisson) strCuisson="sansCuisson";
			else strCuisson=null;
		}	
	});
		

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(FormPanel.this)==JFileChooser.APPROVE_OPTION)
				{
					File file=fileChooser.getSelectedFile();
					 abspath=file.getAbsolutePath();
					System.out.println(abspath);
					Desktop desk=Desktop.getDesktop();
					try {
						desk.open(new File(abspath));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				 reinitialiser();	
			}
			
		});
	
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				 path=abspath;
				 titre = titreField.getText();
				 ingridient = ingridientField.getText();
				 calorCateg = (CaloriesCategory) caloriesList.getSelectedValue();
				 pastryCateg = (String)pastryCategCombo.getSelectedItem();
				 commentField.getText();
				 isIngrd = ingridientCheck.isSelected();
				FormEvent evnt = new FormEvent(this,titre,ingridient,calorCateg.getId(),
						pastryCateg,strCuisson,comment,isIngrd,path);
				if (formPanelListener != null) {
					formPanelListener.addRecetteEventOccured(evnt);
				}
				reinitialiser();
				initialiserControles();	
			}
			
		});
	findBtn.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			 path=abspath;
			 titre = titreField.getText();
			 comment=commentField.getText();
			 ingridient = ingridientField.getText();
			 calorCateg = (CaloriesCategory) caloriesList.getSelectedValue();
			 pastryCateg = (String)pastryCategCombo.getSelectedItem();
			 System.out.println("pastryCateg="+pastryCateg);
			 System.out.println("cuisson de gateau?:"+strCuisson);
			 isIngrd = ingridientCheck.isSelected();
			FormEvent evnt = new FormEvent(this,titre,ingridient,calorCateg.getId(),
					pastryCateg,strCuisson,comment);
			System.out.println("Dans FormPanel titre est vide?:"+titre.isEmpty());
			System.out.println("evnt.pastryCateg="+evnt.getPastryCateg());
			if (formPanelListener!=null) {
				formPanelListener.findRecetteEventOccured(evnt);
			}
			reinitialiser();
			initialiserControles();	
    	}
		
	});	
		Border inBorder = BorderFactory.createTitledBorder("Ajouter Recette");
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
		layoutComponents();

	}// CONSTR
	
	public void initialiserControles()
	{
		path=null;
		titre=null;
		titreField.setText(titre);
		isTicked=false;
		ingridientLbl.setEnabled(isTicked);
		ingridientField.setEnabled(isTicked);
		calorCateg=null;
		caloriesList.setSelectedIndex(0);
		pastryCategCombo.setSelectedIndex(0);
		pastryCateg=null;
		strCuisson=null;
		comment=null;
		commentField.setText(comment);
		isIngrd=false;
		
	}

 public void reinitialiser()
	{
		titreField.setText(null);
		ingridientCheck.setSelected(false);
		cuissonLbl.setEnabled(false);
		avecCsn.setEnabled(false);
		sansCsn.setEnabled(false);
		facile.setSelected(true);
		ingridientField.setText(null);
		ingridientField.setEnabled(false);
	}

public void layoutComponents()
{
	setLayout(new GridBagLayout());
	GridBagConstraints gc = new GridBagConstraints();

	gc.fill = GridBagConstraints.NONE;
	//*************** FIRST ROW*********************************///
	gc.gridy = 0;
	gc.weightx = 1;

	gc.weighty=0.02;
	gc.gridx = 0;

	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(addLbl, gc);
	
	gc.weightx = 1;
	gc.gridx = 1;
	gc.gridy = 0;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(addBtn, gc);
	//****************next row:TITRE ROW *************************************//
	gc.gridy++;

	gc.weightx = 1;
	gc.weighty=0.01;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(titreLbl, gc);

	gc.gridx = 1;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(titreField, gc);
	
	//**************************next row*******************************//
	gc.gridy++;

	gc.weightx = 1;

	gc.weighty=0.01;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(new JLabel("ingridient princ"), gc);

	gc.gridx = 1;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(ingridientCheck, gc);
	//****************************next row*********************************//
	gc.gridy++;

	gc.weightx = 1;
	gc.weighty = 0.01;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(ingridientLbl, gc);

	gc.gridx = 1;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(ingridientField, gc);
	
	//***************************next row*************************************//
	gc.gridy++;

	gc.weightx = 1;
	gc.weighty = 0.05;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(new JLabel("Calories:"), gc);
	gc.gridx = 1;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(caloriesList, gc);
//**************************************next********************************	
	gc.gridy++;

	gc.weightx = 1;
	gc.weighty = 0.1;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(new JLabel("Categorie:"), gc);
	gc.gridx = 1;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(pastryCategCombo, gc);
////**************next:CUISSON ********************************
	gc.gridy++;
	gc.weighty = 0.0001;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(cuissonLbl, gc);
	gc.gridx = 1;
	gc.anchor = GridBagConstraints.FIRST_LINE_START;
	add(avecCsn, gc);
	
    gc.gridy++;
    gc.weighty=0.0001;
	gc.gridx = 1;
	gc.anchor = GridBagConstraints.FIRST_LINE_START;
	add(sansCsn, gc);
	
///******COMMENTAIRES***//////////////////////////
	gc.gridy++;

	gc.weightx = 1;
	gc.weighty=0.02;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_END;
	add(commentLbl, gc);

	gc.gridx = 1;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(commentField, gc);


	////BUTTONS//////////////////////////////////////////

	gc.gridy++;

	gc.weightx = 1;
	gc.weighty=0.02;
	gc.gridx = 0;
	gc.insets = new Insets(0, 0, 0, 5);
	gc.anchor = GridBagConstraints.LINE_START;
	add(findBtn, gc);

	gc.gridx = 1;
	gc.insets = new Insets(0, 0, 0, 0);
	gc.anchor = GridBagConstraints.LINE_START;
	add(okBtn, gc);	
	
	

}// *********************************************fin 2
			
	public void setFormPanelListener(FormPanelListener formPanelListener) {
		this.formPanelListener = formPanelListener;
	}

}

class CaloriesCategory {
	private int id;
	private String text;

	public CaloriesCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public String toString() {
		return text;
	}

	public int getId() {
		return id;
	}

}
