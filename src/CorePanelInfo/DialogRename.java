package CorePanelInfo;

import java.awt.Frame;

import javax.swing.JDialog;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogRename extends JDialog implements ActionListener
{
	private JTextField tName;
	
	private String retname = null;
	
	public DialogRename(Frame frame,String titre,boolean modal)
	{
		super(frame,titre,modal);
		
		this.setSize(274,160);
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		tName = new JTextField();
		tName.setBounds(12, 36, 222, 19);
		getContentPane().add(tName);
		tName.setColumns(10);
		
		JButton bRenommer = new JButton("Renommer");
		bRenommer.setBackground(Color.PINK);
		bRenommer.setBounds(142, 83, 117, 25);
		getContentPane().add(bRenommer);
		
		bRenommer.setActionCommand("RENAME");
		
		// listener
		bRenommer.addActionListener(this);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
	
		if(arg0.getActionCommand() == "RENAME")
		{
			this.retname = tName.getText();
		}
		else
			this.retname = null;
		
		// on quitte
		this.setVisible(false);
	}
	
	public String renameShow()
	{
		this.setVisible(true);
		// on retourne le nom modifier, si pas modifie alors null
		return this.retname;
	}
}
