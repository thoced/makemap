package CorePanelInfo;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import CoreCalques.Calque;
import CoreCalques.CalquesManager;
import CoreCalques.ICalqueMVC;
import CorePanelCenter.panelCenter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.jsfml.system.Vector2f;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import javax.swing.JSlider;

public  class PropertiesPanel extends JPanel implements FocusListener,ICalqueMVC
{
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JComboBox cTypeCalque;
	private JLabel lblNewLabel_7;
	private JTextField tMasse;
	private JLabel lblNewLabel_8;
	private JComboBox cDanger;
	private JLabel lblNewLabel_9;
	private JTextField tSpeed;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextField tTargetX;
	private JTextField tTargetY;
	
	// parent
	
	private static PropertiesPanel parent;
	
	// calque
	private static Calque backCalqueSelected;
	private JLabel lblNewLabel;
	private JComboBox cLayer;
	private JLabel lblNewLabel_1;
	private JCheckBox cLight;
	private JLabel lblNewLabel_2;
	private JSlider sliderAlpha;
	
	public PropertiesPanel()
	{
		
		// parent
		parent = this;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(32dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(70dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblNewLabel_6 = new JLabel("type calque");
		add(lblNewLabel_6, "2, 4");
		
		cTypeCalque = new JComboBox();
		
		cTypeCalque.setModel(new DefaultComboBoxModel(new String[] {"statique", "physique", "dynamique"}));
		add(cTypeCalque, "5, 4, fill, default");
		
		lblNewLabel = new JLabel("Layer");
		add(lblNewLabel, "2, 6");
		
		cLayer = new JComboBox();
		cLayer.setModel(new DefaultComboBoxModel(new String[] {"background_level02", "background_level01", "middleground", "foreground_level01", "foreground_level02"}));
		add(cLayer, "5, 6, fill, default");
		
		lblNewLabel_7 = new JLabel("masse");
		add(lblNewLabel_7, "2, 8");
		
		tMasse = new JTextField();
		
		
		add(tMasse, "5, 8, fill, default");
		tMasse.setColumns(10);
		
		lblNewLabel_8 = new JLabel("danger");
		add(lblNewLabel_8, "2, 10");
		
		cDanger = new JComboBox();
		cDanger.setModel(new DefaultComboBoxModel(new String[] {"false", "true"}));
		add(cDanger, "5, 10, fill, default");
		
		lblNewLabel_9 = new JLabel("speed");
		add(lblNewLabel_9, "2, 12");
		
		tSpeed = new JTextField();
		add(tSpeed, "5, 12, fill, default");
		tSpeed.setColumns(10);
		
		lblNewLabel_10 = new JLabel("target X");
		add(lblNewLabel_10, "2, 14");
		
		tTargetX = new JTextField();
		add(tTargetX, "5, 14, fill, default");
		tTargetX.setColumns(10);
		
		lblNewLabel_11 = new JLabel("target Y");
		add(lblNewLabel_11, "2, 16");
		
		tTargetY = new JTextField();
		add(tTargetY, "5, 16, fill, default");
		tTargetY.setColumns(10);
		
		
		// listener
		Component[] comps = this.getComponents();
		for(Component c : comps)
		{
			c.addFocusListener(this);	
		}
		
		// attachement au CalquesManager
		CalquesManager.attachMVC(this);
		
		lblNewLabel_1 = new JLabel("Light");
		add(lblNewLabel_1, "2, 18");
		
		cLight = new JCheckBox("Calque maplight");
		add(cLight, "5, 18");
		
		lblNewLabel_2 = new JLabel("Alpha");
		add(lblNewLabel_2, "2, 20");
		
		sliderAlpha = new JSlider();
		sliderAlpha.setSnapToTicks(true);
		sliderAlpha.setPaintTicks(true);
		sliderAlpha.setMaximum(255);
		sliderAlpha.setMinimum(0);
		sliderAlpha.setValue(255);
		add(sliderAlpha, "5, 20");
		
	}
	
	

	public static void setCalque(Calque currentCalqueSelected) 
	{
		// TODO Auto-generated method stub	
		// on sauvegarde d'abord ce qui a déja été modifié sur le currentCalque
		parent.saveInfo();
		
		//currentCalque = currentCalqueSelected;
		
		if(currentCalqueSelected!=null)
		{
			// on modifie les propriété en fonction du calque selectionné
			parent.cTypeCalque.setSelectedItem(currentCalqueSelected.getType_calque());
			parent.cDanger.setSelectedItem(currentCalqueSelected.isDanger() == true ? "true" : "false");
			parent.tMasse.setText(String.valueOf(currentCalqueSelected.getMasse()));
			parent.tSpeed.setText(String.valueOf(currentCalqueSelected.getSpeed()));
			parent.tTargetX.setText(String.valueOf(currentCalqueSelected.getTargetX()));
			parent.tTargetY.setText(String.valueOf(currentCalqueSelected.getTargetY()));
			parent.cLayer.setSelectedIndex(currentCalqueSelected.getLayer());
			parent.cLight.setSelected(currentCalqueSelected.isLightMap());
			parent.sliderAlpha.setValue(currentCalqueSelected.getAlpha());
			
			// backCalque
			backCalqueSelected = currentCalqueSelected;
			
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void saveInfo()
	{
		if(backCalqueSelected != null)
		{
			// on update les modidications
			backCalqueSelected.setType_calque((String)this.cTypeCalque.getSelectedItem());
			backCalqueSelected.setDanger(this.cDanger.getSelectedItem() == "true" ? true : false);
			backCalqueSelected.setMasse(Float.parseFloat(this.tMasse.getText()));
			backCalqueSelected.setSpeed(Float.parseFloat(this.tSpeed.getText()));
			backCalqueSelected.setTargetX(Float.parseFloat(this.tTargetX.getText()));
			backCalqueSelected.setTargetY(Float.parseFloat(this.tTargetY.getText()));
			backCalqueSelected.setLayer(this.cLayer.getSelectedIndex());
			backCalqueSelected.setLightMap(this.cLight.isSelected());
			backCalqueSelected.setAlpha(this.sliderAlpha.getValue());
			// il faut retrier la liste
			//CalquesManager.sortCalques();
			
		}
	}
	
	@Override
	public void focusLost(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		//this.saveInfo();
	}



	@Override
	public void updateCalqueMVC(List<Calque> list)
	{
		// on enregistre le backCalqueSelected
		this.saveInfo();
		// on applique le nouveau calqu selectionné
		this.setCalque(CalquesManager.getCurrentCalque());
	}



	


	


	

}
