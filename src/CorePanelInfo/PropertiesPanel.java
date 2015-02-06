package CorePanelInfo;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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

public class PropertiesPanel extends JPanel 
{
	private JTextField tName;
	private JTextField tX;
	private JTextField tY;
	private JTextField tWidth;
	private JTextField tHeight;
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
		
		JLabel lblNewLabel = new JLabel("Nom:\n");
		add(lblNewLabel, "2, 4");
		
		tName = new JTextField();
		add(tName, "6, 4, fill, top");
		
		JLabel lblNewLabel_1 = new JLabel("X");
		add(lblNewLabel_1, "2, 6");
		
		tX = new JTextField();
		add(tX, "6, 6, fill, default");
		tX.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Y");
		add(lblNewLabel_2, "2, 8");
		
		tY = new JTextField();
		add(tY, "6, 8, fill, default");
		tY.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Width");
		add(lblNewLabel_3, "2, 10");
		
		tWidth = new JTextField();
		add(tWidth, "6, 10, fill, default");
		tWidth.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Height");
		add(lblNewLabel_4, "2, 12");
		
		tHeight = new JTextField();
		add(tHeight, "6, 12, fill, default");
		tHeight.setColumns(10);
		
		lblNewLabel_6 = new JLabel("type calque");
		add(lblNewLabel_6, "2, 14");
		
		cTypeCalque = new JComboBox();
		cTypeCalque.setModel(new DefaultComboBoxModel(new String[] {"statique", "physique", "dynamique"}));
		add(cTypeCalque, "6, 14, fill, default");
		
		lblNewLabel_7 = new JLabel("masse");
		add(lblNewLabel_7, "2, 16");
		
		tMasse = new JTextField();
		add(tMasse, "6, 16, fill, default");
		tMasse.setColumns(10);
		
		lblNewLabel_8 = new JLabel("danger");
		add(lblNewLabel_8, "2, 18");
		
		cDanger = new JComboBox();
		cDanger.setModel(new DefaultComboBoxModel(new String[] {"false", "true"}));
		add(cDanger, "6, 18, fill, default");
		
		lblNewLabel_9 = new JLabel("speed");
		add(lblNewLabel_9, "2, 20");
		
		tSpeed = new JTextField();
		add(tSpeed, "6, 20, fill, default");
		tSpeed.setColumns(10);
		
		lblNewLabel_10 = new JLabel("target X");
		add(lblNewLabel_10, "2, 22");
		
		tTargetX = new JTextField();
		add(tTargetX, "6, 22, fill, default");
		tTargetX.setColumns(10);
		
		lblNewLabel_11 = new JLabel("target Y");
		add(lblNewLabel_11, "2, 24");
		
		tTargetY = new JTextField();
		add(tTargetY, "6, 24, fill, default");
		tTargetY.setColumns(10);
		
		
	}
	
	

	public static void setCalque(Calque currentCalqueSelected) 
	{
		// TODO Auto-generated method stub	
		currentCalque = currentCalqueSelected;
	}


	public static void refreshProperties()
	{
		// TODO Auto-generated method stub
		if(currentCalque != null)
		{
			// nom du calque
			parent.tName.setText(currentCalque.getVirtualName());
			// x,y
			Vector2f pos = currentCalque.getSprite().getPosition();
			parent.tX.setText(String.valueOf(pos.x));
			parent.tY.setText(String.valueOf(pos.y));
			
			
		}
	}

}
