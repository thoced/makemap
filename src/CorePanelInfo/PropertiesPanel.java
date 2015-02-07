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

import CorePanelCenter.Calque;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.jsfml.system.Vector2f;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public  class PropertiesPanel extends JPanel implements FocusListener
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
	private static Calque currentCalque;
	private JLabel lblNewLabel;
	private JComboBox cLayer;
	
	public PropertiesPanel()
	{
		
		// parent
		parent = this;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(32dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
		add(cTypeCalque, "6, 4, fill, default");
		
		lblNewLabel_7 = new JLabel("masse");
		add(lblNewLabel_7, "2, 6");
		
		tMasse = new JTextField();
		
		
		add(tMasse, "6, 6, fill, default");
		tMasse.setColumns(10);
		
		lblNewLabel_8 = new JLabel("danger");
		add(lblNewLabel_8, "2, 8");
		
		cDanger = new JComboBox();
		cDanger.setModel(new DefaultComboBoxModel(new String[] {"false", "true"}));
		add(cDanger, "6, 8, fill, default");
		
		lblNewLabel_9 = new JLabel("speed");
		add(lblNewLabel_9, "2, 10");
		
		tSpeed = new JTextField();
		add(tSpeed, "6, 10, fill, default");
		tSpeed.setColumns(10);
		
		lblNewLabel_10 = new JLabel("target X");
		add(lblNewLabel_10, "2, 12");
		
		tTargetX = new JTextField();
		add(tTargetX, "6, 12, fill, default");
		tTargetX.setColumns(10);
		
		lblNewLabel_11 = new JLabel("target Y");
		add(lblNewLabel_11, "2, 14");
		
		tTargetY = new JTextField();
		add(tTargetY, "6, 14, fill, default");
		tTargetY.setColumns(10);
		
		lblNewLabel = new JLabel("Layer");
		add(lblNewLabel, "2, 16");
		
		cLayer = new JComboBox();
		cLayer.setModel(new DefaultComboBoxModel(new String[] {"background_level02", "background_level01", "middleground", "foreground_level01", "foreground_level02"}));
		add(cLayer, "6, 16, fill, default");
		
		
		// listener
		Component[] comps = this.getComponents();
		for(Component c : comps)
		{
			c.addFocusListener(this);	
		}
		
	}
	
	

	public static void setCalque(Calque currentCalqueSelected) 
	{
		// TODO Auto-generated method stub	
		// on sauvegarde d'abord ce qui a déja été modifié sur le currentCalque
		parent.saveInfo();
		
		currentCalque = currentCalqueSelected;
		
		if(currentCalque!=null)
		{
			// on modifie les propriété en fonction du calque selectionné
			parent.cTypeCalque.setSelectedItem(currentCalque.getType_calque());
			parent.cDanger.setSelectedItem(currentCalque.isDanger() == true ? "true" : "false");
			parent.tMasse.setText(String.valueOf(currentCalque.getMasse()));
			parent.tSpeed.setText(String.valueOf(currentCalque.getSpeed()));
			parent.tTargetX.setText(String.valueOf(currentCalque.getTargetX()));
			parent.tTargetY.setText(String.valueOf(currentCalque.getTargetY()));
			parent.cLayer.setSelectedItem(currentCalque.getLayer());
			
			
			
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void saveInfo()
	{
		if(currentCalque != null)
		{
			// on update les modidications
			currentCalque.setType_calque((String)this.cTypeCalque.getSelectedItem());
			currentCalque.setDanger(this.cDanger.getSelectedItem() == "true" ? true : false);
			currentCalque.setMasse(Float.parseFloat(this.tMasse.getText()));
			currentCalque.setSpeed(Float.parseFloat(this.tSpeed.getText()));
			currentCalque.setTargetX(Float.parseFloat(this.tTargetX.getText()));
			currentCalque.setTargetY(Float.parseFloat(this.tTargetY.getText()));
			currentCalque.setLayer((String)this.cLayer.getSelectedItem());
		}
	}
	
	@Override
	public void focusLost(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		this.saveInfo();
	}



	


	


	

}
