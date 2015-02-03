package CorePanelInfo;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.PanelPeer;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

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

public class panelInfo extends JPanel implements MouseListener 
{
	
	private JList listCalques;
	private panelViewer pViewer;
	private JList listTextures;
	
	public panelInfo()
	{
		setLayout(new GridLayout(3,1,0,16));
		listCalques = new JList();
		JScrollPane scrollPaneCalques = new JScrollPane(listCalques);
		add(scrollPaneCalques);
		
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
						Calque calque = new Calque(text);
						// insert du calque
						panelCenter.insertCalque(calque);
					}
					else
					{
						panelViewer.setTexture(text);
					}
					
				
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
