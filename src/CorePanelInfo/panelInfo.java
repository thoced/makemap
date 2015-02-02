package CorePanelInfo;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import CorePanelCenter.Calque;
import CorePanelCenter.panelCenter;
import CoreTexturesManager.TexturesManager;
import makemap.DataManager;

public class panelInfo extends JPanel implements MouseListener 
{
	private JList listCalques;
	private JList listTextures;
	
	public panelInfo()
	{
		setLayout(new BorderLayout(0, 0));
		
		listCalques = new JList();
		JScrollPane scrollPaneCalques = new JScrollPane(listCalques);
		add(scrollPaneCalques, BorderLayout.NORTH);
		
		listTextures = new JList();
		
		
		JScrollPane scrollPaneTextures = new JScrollPane(listTextures);
		add(scrollPaneTextures, BorderLayout.CENTER);
		
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
					Calque calque = new Calque(text);
					// insert du calque
					panelCenter.insertCalque(calque);
					
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
