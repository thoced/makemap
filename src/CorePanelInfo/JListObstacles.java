package CorePanelInfo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import CoreObstacles.IObstacleMVC;
import CoreObstacles.Obstacle;
import CoreObstacles.ObstaclesManager;
import CorePanelCenter.panelCenter;

public class JListObstacles extends JList implements IObstacleMVC, MouseListener
{
	public  JListObstacles()
	{
		super();
		
		this.addMouseListener(this);
	}
	
	@Override
	public void updateMVC(List<Obstacle> list) 
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
		Obstacle o = (Obstacle)this.getSelectedValue();
		// on défini l'obstacle comme étant selected
		o.setSelected(true);
		// on précise au manager d'obstacle que l'obstacle est selectionné
		ObstaclesManager.setCurrentObstacle(o);
		
		// on rafraichit
		panelCenter.repaintCalques();
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
