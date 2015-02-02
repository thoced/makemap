package GUI;

import java.awt.Frame;

import javax.swing.JDialog;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;

import makemap.DataManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class diaNouveau extends JDialog implements ActionListener
{
	private JTextField tWidth;
	private JTextField tHeight;
	private JButton btnNewButton;
	private JButton bAnnuler;
	public diaNouveau(Frame frame,String titre,boolean modal)
	{
		super(frame,titre, modal);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		this.setSize(640, 480);
		

		
		this.setLocationRelativeTo(frame);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Taille du niveau");
		lblNewLabel.setBounds(12, 88, 316, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Width");
		lblNewLabel_1.setBounds(12, 149, 70, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Height");
		lblNewLabel_2.setBounds(12, 217, 70, 15);
		getContentPane().add(lblNewLabel_2);
		
		tWidth = new JTextField();
		tWidth.setBounds(88, 147, 114, 19);
		getContentPane().add(tWidth);
		tWidth.setColumns(10);
		
		tHeight = new JTextField();
		tHeight.setBounds(88, 215, 114, 19);
		getContentPane().add(tHeight);
		tHeight.setColumns(10);
		
		btnNewButton = new JButton("Confirmer\n");
		btnNewButton.setBounds(485, 383, 117, 25);
		getContentPane().add(btnNewButton);
		
		bAnnuler = new JButton("Annuler");
	
		bAnnuler.setBounds(337, 383, 117, 25);
		getContentPane().add(bAnnuler);
		
		
		btnNewButton.addActionListener(this);
		bAnnuler.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == btnNewButton)
		{
		
			DataManager.widthMap = Integer.parseInt(tWidth.getText());
			
			DataManager.heigthMap = Integer.parseInt(tHeight.getText());
			
			this.setVisible(false);
		}
		
		if(e.getSource() == bAnnuler)
		{
			this.setVisible(false);
		}
		
		
		
		
	}
}
