package CoreObstacles;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public class ObstaclesManager implements Drawable
{
	private  List<Obstacle> listObstacles;
	
	// list du MVC attach
	private  List<IObstacleMVC> listMVC;
	
	// parent
	private  ObstaclesManager parent;
	
	// cpt des noms
	private int nameCpt = 0;
	
	// currentObstacle selectionné
	private  Obstacle currentObstacle = null;
	
	
	
	public ObstaclesManager()
	{
		// instance du parent pour le static
		
		// instance de la liste des obstacles
		listObstacles = new ArrayList<Obstacle>();
		// instance de la liste des MVC
		listMVC = new ArrayList<IObstacleMVC>();
	
		
	}

	@Override
	public void draw(RenderTarget render, RenderStates state)
	{
		// on boucle sur les obstacles
		for(Obstacle obs : listObstacles)
		{
			obs.draw(render, state);
		}
	}
	
	public  void insertObstacle(Obstacle o)
	{
		// on donne un nom à l'obstacle
		o.setName("obstacle");
		// on précise au manager l'obstacle current
		// on insère l'obstacle dans la liste
		listObstacles.add(o);
		
		setCurrentObstacle(o);
		
		// on appel la liste des mvc
		refreshMVC();
	}
	
	public  void fixObstacle()
	{
		// on fix l'obstacle en cours
		setCurrentObstacle(null);
	}
	
	
	
	public  void deleteObstacle(Obstacle obj)
	{
		// si l'obstacle est celui qui est selectionné, 
		if(obj != null && this.getCurrentObstacle() == obj)
		{
			// on supprime la référence de l'obstacle dans la liste
			listObstacles.remove(obj);
			// on précise au manager qu'il n'y a plus d'obstacle current
			setCurrentObstacle(null);
			// on appel la liste des mvc
			refreshMVC();
		}
		
	}
	
	public void refreshMVC()
	{
		// on appels les mvc attachés
		for(IObstacleMVC mvc : this.listMVC)
		{
			mvc.updateObstacleMVC(listObstacles);
		}
			
	}
	
	public  void attachMVC(IObstacleMVC mvc)
	{
		// on attache un mvc
		if(mvc != null)
			listMVC.add(mvc);
	}

	/**
	 * @return the currentObstacle
	 */
	public  Obstacle getCurrentObstacle() {
		return currentObstacle;
	}

	/**
	 * @param currentObstacle the currentObstacle to set
	 */
	public  void setCurrentObstacle(Obstacle newCurrent) 
	{
		if(newCurrent == null)
		{	// on va déselectionné l'obstacle précédent
			if(this.currentObstacle != null)
				this.currentObstacle.setSelected(false);
			
			this.currentObstacle = null;
			refreshMVC();
		}
		else
		{
			if(this.currentObstacle != null)
				this.currentObstacle.setSelected(false);
		
			// le nouvelle obstacle est spécifié au manager
			currentObstacle = newCurrent;
			// on spécifie à l'obstacle qu'il est selectionné
			currentObstacle.setSelected(true);
			
			refreshMVC();
		}
			
	}

	/**
	 * @return the listObstacles
	 */
	public  List<Obstacle> getListObstacles() {
		return listObstacles;
	}

	/**
	 * @param listObstacles the listObstacles to set
	 */
	public  void setListObstacles(List<Obstacle> listObstacles) {
		listObstacles = listObstacles;
	}

	
	
	
	
}
