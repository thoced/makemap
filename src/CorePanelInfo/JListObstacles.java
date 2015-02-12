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
		// si un currentObstacle est selectionné
			if(ObstaclesManager.getCurrentObstacle() != null)
				this.setSelectedValue(ObstaclesManager.getCurrentObstacle(), false);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		if(arg0.getButton() == MouseEvent.BUTTON1)
		{
		
			Obstacle o = (Obstacle)this.getSelectedValue();
			// on précise au manager d'obstacle que l'obstacle est selectionné
			// on spécifie à l'ancien objet obstacle selectionné, qu'il ne l'est plus
			if(ObstaclesManager.getCurrentObstacle() != null)
				ObstaclesManager.getCurrentObstacle().setSelected(false);
			
			// on spécifie le nouvelle obstacle selectionné
			ObstaclesManager.setCurrentObstacle(o);
			ObstaclesManager.getCurrentObstacle().setSelected(true);
		}
		
		// on rafraichit
		//panelCenter.repaintCalques();
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
				ObstaclesManager.deleteObstacle((Obstacle)this.getSelectedValue());
			
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
