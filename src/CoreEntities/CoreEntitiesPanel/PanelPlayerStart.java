package CoreEntities.CoreEntitiesPanel;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Window.Type;
import CoreEntities.PlayerStart.MYTYPE;
import java.lang.ProcessBuilder.Redirect;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PanelPlayerStart extends JPanel 
{
	private JComboBox cType;
	public PanelPlayerStart() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblType = new JLabel("Type");
		add(lblType, "4, 4, right, default");
		
		cType = new JComboBox();
		cType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) 
			{
				
			}
		});
		cType.setModel(new DefaultComboBoxModel(new String[] {"SMALL_ROBOT", "BIG_ROBOT"}));
		add(cType, "6, 4, fill, default");
	}
	/**
	 * @return the cType
	 */
	public JComboBox getcType() {
		return cType;
	}
	/**
	 * @param cType the cType to set
	 */
	public void setcType(JComboBox cType) {
		this.cType = cType;
	}
	
	

}
