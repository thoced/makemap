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
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import org.jsfml.system.Vector2f;

import CoreCalques.Calque;
import CoreCalques.CalquesManager;
import CoreManager.Manager;
import CoreObstacles.ObstaclesManager;
import CorePanelCenter.panelCenter;
import CorePanelViewer.panelViewer;
import CoreTexturesManager.TexturesManager;
import makemap.DataManager;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;

public class panelInfo extends JPanel implements MouseListener,KeyListener 
{
	
	// JList des calques
	private JListCalques listCalques;
	// JList des obstacles
	private JListObstacles listObstacles;
	// panel des aperçus
	private panelViewer pViewer;
	// JList des textures disponibles
	private JList listTextures;
	
	// Properties panel
	private PropertiesPanel prop;
	
	// calque selectionné courant
	private  Calque currentCalqueSelected;
	
	// parent
	private static panelInfo parent;
	
	// rename popup
	private  JTextField rename;
	// popup
	private  Popup pop;
	private JTabbedPane tabbedPane;
	
	// tabbedPane pour les obstacle et calques
	private JTabbedPane tabbedPaneUp;
	
	public panelInfo()
	{
		// ajout du parent pour les appels static
		parent = this;
		// layout
		setLayout(new GridLayout(3,1,8,8));
		
		// tabbed pane obstacle
		tabbedPaneUp = new JTabbedPane(JTabbedPane.TOP);
		
		// ajout du tab listcalques
		listCalques = new JListCalques();
		JScrollPane scrollPaneCalques = new JScrollPane(listCalques);
		tabbedPaneUp.add("Calques", scrollPaneCalques);
		
		// attachement mvc de listCalques
		Manager.getCalquesManager().attachMVC(listCalques);
	
	
		// ajout du tab listObstacles
		listObstacles = new JListObstacles();
		JScrollPane scrollPaneObstacles = new JScrollPane(listObstacles);
		tabbedPaneUp.add("Obstacles", scrollPaneObstacles);
		// attachement mvc de listObstacles
		Manager.getObstaclesManager().attachMVC(listObstacles);
		
		add(tabbedPaneUp);
		
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
		
		// tabbed
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		listTextures = new JList();
		JScrollPane scrollPaneTextures = new JScrollPane(listTextures);
	
		
		prop = new PropertiesPanel();

		tabbedPane.add("Textures", scrollPaneTextures);
		
		JScrollPane scrollProperties = new JScrollPane(prop);
		
		tabbedPane.add("Properties",scrollProperties);
		
		
		
		
		
		
		// listener
		listTextures.addMouseListener(this);
		
		
		
	}
	
	public static void deselectAllCalque()
	{
		parent.listCalques.clearSelection();
		parent.currentCalqueSelected.setSelected(false);
		parent.currentCalqueSelected = null;
	}
	
	public static void selectedCalque(Calque currentCalqueSelected)
	{
		// on deselectionne l'ancien currentCalque
		if(parent.currentCalqueSelected != null)
		{
			parent.currentCalqueSelected.setSelected(false);
			parent.currentCalqueSelected = null;
		}
		// on selectionne le nouveau
		parent.listCalques.setSelectedValue(currentCalqueSelected, true);
		parent.currentCalqueSelected = currentCalqueSelected;
		parent.currentCalqueSelected.setSelected(true);
	//	PropertiesPanel.setCalque(currentCalqueSelected);
		
	}
	
	public static void refreshListCalque(List<Calque> lc)
	{
		// on efface la liste
		DefaultListModel m = (DefaultListModel) parent.listCalques.getModel();
		m.clear();
		// on 
		for(Calque c : lc)
			m.addElement(c);
	}
	
	public void loadTextures(File repertoire)
	{
		if(repertoire != null && repertoire.isDirectory())
		{
			// on filtre pour n'afficher que les fichier portant les extensions png
			 FilenameFilter filter = new FilenameFilter() {
			        public boolean accept(File directory, String fileName) {
			            return fileName.endsWith(".png");
			        }
			        };
			
			File[] files =repertoire.listFiles(filter);
			
			// on créer le vecteur pour le tri
			List<DataListTextures> listFiles = new ArrayList<DataListTextures>();
			
			
			
			DefaultListModel dlm = new DefaultListModel();
			
			for(File file : files)
			{
				if(!file.isDirectory())
				{
					DataListTextures dlt = new DataListTextures(file);
					// on copie la liste pour le tri
					listFiles.add(dlt);
				
					
				
				}
			}
			
			// on trie
			Collections.sort(listFiles);
			
			// on recopie le tout dans le default listmodel
			for(DataListTextures d : listFiles)
				dlm.addElement(d);
			
			listTextures.setModel(dlm);
			
			// on trie
			
			
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
						// on modifie la position
						calque.getSprite().setPosition(panelCenter.getV().getCenter());
						// insert du calque
						Manager.getCalquesManager().insertNewCalque(calque);
					
						
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
			/*if(arg0.getButton() == MouseEvent.BUTTON1)
			{
				// si il y a déja un calque selectionné, on le déselectionne
				if(currentCalqueSelected != null)
					currentCalqueSelected.setSelected(false);
				
				// on selectoinne le calque 
				currentCalqueSelected = (Calque) listCalques.getSelectedValue();
				currentCalqueSelected.setSelected(true);
				// on demande le rafraichissement
				panelCenter.repaintCalques();
				// on modifie le panel de propriété
				PropertiesPanel.setCalque(currentCalqueSelected);
				
				
			}
			
			if(arg0.getButton() == MouseEvent.BUTTON3)
			{
				
				
				
				
				  
				   
				  
			}*/
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
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
