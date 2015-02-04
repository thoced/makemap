package CorePanelInfo;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.PanelPeer;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;

import CorePanelCenter.Calque;
import CorePanelCenter.panelCenter;
import CorePanelViewer.panelViewer;
import CoreTexturesManager.TexturesManager;
import makemap.DataManager;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class panelInfo extends JPanel implements MouseListener,KeyListener 
{
	
	private JList listCalques;
	private panelViewer pViewer;
	private JList listTextures;
	
	
	// calque selectionné courant
	private  Calque currentCalqueSelected;
	
	// parent
	private static panelInfo parent;
	
	// rename popup
	private  JTextField rename;
	// popup
	private  Popup pop;
	
	public panelInfo()
	{
		// ajout du parent pour les appels static
		parent = this;
		
		setLayout(new GridLayout(3,1,8,8));
		listCalques = new JList();
		JScrollPane scrollPaneCalques = new JScrollPane(listCalques);
		add(scrollPaneCalques);
		
		// ajout du model pour le calque
		DefaultListModel modelCalque = new DefaultListModel();
		listCalques.setModel(modelCalque);
		
		// creation du listener pour le listCalques
		listCalques.addMouseListener(this);
		
		try 
		{
			pViewer = new panelViewer();
			add(pViewer);
			
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		listTextures = new JList();
		JScrollPane scrollPaneTextures = new JScrollPane(listTextures);
		add(scrollPaneTextures);
		
		// listener
		listTextures.addMouseListener(this);
	
		
		
	}
	
	public void loadTextures(File repertoire)
	{
		if(repertoire != null && repertoire.isDirectory())
		{
			File[] files = repertoire.listFiles();
			
		
			DefaultListModel dlm = new DefaultListModel();
			
			for(File file : files)
			{
				if(!file.isDirectory())
				{
					DataListTextures dlt = new DataListTextures(file);
					dlm.addElement(dlt);
				}
			}
			
			listTextures.setModel(dlm);
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// on place un calque
		
		if(arg0.getSource() == listTextures)
		{
		// gestion des évenements sur la liste des textures
			
		DataListTextures data = (DataListTextures) listTextures.getSelectedValue();
		
		if(data!=null)
		{
			//DataManager.panelCenter.addCalque(data.getFile());
			
			Texture text = TexturesManager.GetTextureByName(data.getFile());
			if(text != null)
			{
				try 
				{
					if(arg0.getClickCount() == 2)
					{
						Calque calque = new Calque(text,data.getFile());
						// insert du calque
						panelCenter.insertCalque(calque);
						//insert du nom du calque dans la listeCalque
						DefaultListModel model = (DefaultListModel) listCalques.getModel();
						model.addElement(calque);
						listCalques.setModel(model);
					}
					else
					{
						// un seul clic, on affiche juste dans l'aperçu
						panelViewer.setTexture(text);
					}
					
				
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		}
		
		if(arg0.getSource() == listCalques)
		{
			// gestion des évenement sur la liste des calques
			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
				// si il y a déja un calque selectionné, on le déselectionne
				if(currentCalqueSelected != null)
					currentCalqueSelected.setSelected(false);
				
				// on selectoinne le calque 
				currentCalqueSelected = (Calque) listCalques.getSelectedValue();
				currentCalqueSelected.setSelected(true);
				// on demande le rafraichissement
				panelCenter.repaintCalques();
				
			}
			
			if(arg0.getButton() == MouseEvent.BUTTON3)
			{
				
				
				// on veut renomer le calque
				   PopupFactory factory = PopupFactory.getSharedInstance();
				   rename = new JTextField();
				   rename.setColumns(16);
				   rename.setActionCommand("POPUPTEXTFIELD");
				   rename.addKeyListener(this);
				   // on passe le nom du calque selectionné
				   if(currentCalqueSelected != null)
				   {
					   rename.setText(currentCalqueSelected.toString()); rename.setColumns(16);
					   rename.setEnabled(true);
					   rename.setEditable(true);
					   pop = factory.getPopup(this, rename, this.getX(),arg0.getYOnScreen());
					   pop.show();
					   // désactivé la possibilité de changer de calque
					   listCalques.setEnabled(false);
					   
					   
				   }
				
				  
				   
				  
			}
		}
	}
	
	public static Calque getCurrentCalqueSelected() throws NullPointerException
	{
		// retourne le calque courant selectionné
		if(parent.currentCalqueSelected != null) 
			return parent.currentCalqueSelected;
		else
			throw new NullPointerException();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(arg0.getSource() == rename )
		{
			
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if(currentCalqueSelected !=null)
					currentCalqueSelected.setVirtualName(rename.getText());
				
				pop.hide();
				// on redonne la possibilité de selectionné un calque
				 listCalques.setEnabled(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
