package GUI;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import makemap.DataManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class diaProperties extends JDialog 
{
	private JTextField tRepertoireTextures;
	
	private JButton bRepertoireTextures;
	
	public diaProperties(Frame frame,String titre,boolean modal)
	{
		super(frame,titre,modal);
		
		this.setSize(640, 480);
		
		this.setLocationRelativeTo(frame);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chemin du répertoire des textures :");
		lblNewLabel.setBounds(12, 76, 281, 15);
		panel.add(lblNewLabel);
		
		tRepertoireTextures = new JTextField();
		tRepertoireTextures.setEnabled(false);
		tRepertoireTextures.setBounds(280, 74, 218, 19);
		panel.add(tRepertoireTextures);
		tRepertoireTextures.setColumns(10);
		
		bRepertoireTextures = new JButton("Chemin");
		bRepertoireTextures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					File directory = chooser.getSelectedFile();
					tRepertoireTextures.setText(directory.getAbsolutePath());
					
					DataManager.directoryTextures = directory;
				}
				
				
			}
		});
		bRepertoireTextures.setBounds(510, 71, 128, 25);
		panel.add(bRepertoireTextures);
	}
	// retour du chemin du répertoire
	
	public String getRepertories()
	{
		return tRepertoireTextures.getText();
	}
}
