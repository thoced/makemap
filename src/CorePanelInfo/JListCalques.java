package CorePanelInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import CoreCalques.Calque;
import CoreCalques.CalquesManager;
import CoreCalques.ICalqueMVC;
import CorePanelCenter.panelCenter;
import javax.swing.ListSelectionModel;

public class JListCalques extends JList implements ICalqueMVC,MouseListener,KeyListener,ActionListener
{

	public JListCalques()
	{
		super();
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Listener
		this.addMouseListener(this);
		this.addKeyListener(this);
		
	}
	@Override
	public void updateCalqueMVC(List<Calque> list) 
	{
		// Instance du default list model
		DefaultListModel model = new DefaultListModel();
		// clear
		model.clear();
		// on ajoute la liste des calques
		for(Calque c : list)
			model.addElement(c);
		// on place le model dans le jlist
		this.setModel(model);
		
		// si un currentCalques est selectionné
		if(CalquesManager.getCurrentCalque() != null)
			this.setSelectedValue(CalquesManager.getCurrentCalque(), false);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub

		// si c'est un clic droit c'est pour ouvrir un menu contextuel
		if(e.getButton() == MouseEvent.BUTTON3 )
		{
			
			
			
			// création du menubar
			JPopupMenu bar = new JPopupMenu("context");
			// création du menu
			JMenuItem duplic = new JMenuItem("Dupliquer");
			JMenuItem rename = new JMenuItem("Renommer");
			// listener
			duplic.addActionListener(this);
			rename.addActionListener(this);
			// action command
			duplic.setActionCommand("DUPLIC");
			rename.setActionCommand("RENAME");
			// ajout 
			 bar.add(duplic);
			 bar.add(rename);
			 // Affichage du popupmenu
			 bar.show(this, e.getX() , e.getY());
			 
		}
		else
		{
			// on receptionne l'objet calque selectionne
			Calque c = (Calque) this.getSelectedValue();
			// on deselectionne le calque déja selectionné, si il y en a un
			if(CalquesManager.getCurrentCalque() != null)
				CalquesManager.getCurrentCalque().setSelected(false);
			// on précise qu'il est selectionné
			c.setSelected(true);
			// on specifie au CalquesManager l'objet selectionné
			CalquesManager.setCurrentCalque(c);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		// touche de delete
		if(arg0.getKeyCode() == KeyEvent.VK_DELETE)
		{
				if(this.getSelectedValue() != null)
			CalquesManager.deleteCalque((Calque)this.getSelectedValue());
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
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// si on a cliqué sur duplic
		if(arg0.getActionCommand() == "DUPLIC")
		{
			// on vérifie que ce n'est pas null
			if(this.getSelectedValue() != null)
			{
				// on récupère l'objet calque selectionné à dupliquer
				Calque c = (Calque) this.getSelectedValue();
				// on duplique
				try 
				{
					Calque newCalque = (Calque) c.clone();
					// on ajoute dans le CalquesManager
					CalquesManager.insertNewCalque(newCalque);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		else if(arg0.getActionCommand() == "RENAME")
		{
			// on rename l'objet 
			
			if(this.getSelectedValue() != null)
			{
				// on recupère le calque à renomer
				Calque c = (Calque) this.getSelectedValue();
				
				DialogRename dia = new DialogRename(null,"Renommer le calque",true);
				String ret = dia.renameShow();
				if(ret != null)
				{
					// on renome
					c.rename(ret);
				}
			}
		}
		
	}

	
}
