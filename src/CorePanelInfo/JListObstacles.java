package CorePanelInfo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import CoreCalques.CalquesManager;
import CoreManager.Manager;
import CoreObstacles.IObstacleMVC;
import CoreObstacles.Obstacle;
import CoreObstacles.ObstaclesManager;
import CorePanelCenter.panelCenter;

public class JListObstacles extends JList implements IObstacleMVC, MouseListener, KeyListener
{
	public  JListObstacles()
	{
		super();
		// listener
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	
	@Override
	public void updateObstacleMVC(List<Obstacle> list) 
	{
		// TODO Auto-generated method stub
		DefaultListModel model = new DefaultListModel();
		
		// on supprime la liste
		model.clear();
		// on recrée la liste
		for(Obstacle o : list)
			model.addElement(o);
		
	
		// on replace le default list model
		this.setModel(model);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		if(arg0.getButton() == MouseEvent.BUTTON1)
		{
			// on récupère l'obstacle sélectionné
			Obstacle obs = (Obstacle) this.getSelectedValue();
			// on précise au manager l'obstacle sélectionné
			Manager.getObstaclesManager().setCurrentObstacle(obs);
		}
		
		// on rafraichit
		//panelCenter.repaintCalques();
		// si un currentCalques est selectionné
		if(Manager.getObstaclesManager().getCurrentObstacle() != null)
			this.setSelectedValue(Manager.getObstaclesManager().getCurrentObstacle(), false);
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

	@Override
	public void keyPressed(KeyEvent e)
	{
		// en appuyant sur la touche delete, on supprime 
		if(e.getKeyCode() == KeyEvent.VK_DELETE)
		{
			// on supprime l'objet obstacle selectionné
			if(this.getSelectedValue() != null)
				Manager.getObstaclesManager().deleteObstacle((Obstacle)this.getSelectedValue());
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
