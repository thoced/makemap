package CoreObstacles;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public class ObstaclesManager implements Drawable
{
	private static List<Obstacle> listObstacles;
	
	// list du MVC attach
	private static List<IObstacleMVC> listMVC;
	
	// parent
	private static ObstaclesManager parent;
	
	// cpt des noms
	private int nameCpt = 0;
	
	// currentObstacle selectionné
	private static Obstacle currentObstacle = null;
	
	
	
	public ObstaclesManager()
	{
		// instance du parent pour le static
		parent = this;
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
	
	public static void insertObstacle(Obstacle o)
	{
		// on donne un nom à l'obstacle
		o.setName("obstacle");
		// on précise au manager l'obstacle current
		// on insère l'obstacle dans la liste
		parent.listObstacles.add(o);
		
		parent.setCurrentObstacle(o);
		
		// on appel la liste des mvc
		parent.refreshMVC();
	}
	
	public static void fixObstacle()
	{
		// on fix l'obstacle en cours
		parent.setCurrentObstacle(null);
	}
	
	
	
	public static void deleteObstacle(Obstacle obj)
	{
		// si l'obstacle est celui qui est selectionné, 
		if(obj != null && parent.getCurrentObstacle() == obj)
		{
			// on supprime la référence de l'obstacle dans la liste
			parent.listObstacles.remove(obj);
			// on précise au manager qu'il n'y a plus d'obstacle current
			parent.setCurrentObstacle(null);
			// on appel la liste des mvc
			parent.refreshMVC();
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
	
	public static void attachMVC(IObstacleMVC mvc)
	{
		// on attache un mvc
		if(mvc != null)
			parent.listMVC.add(mvc);
	}

	/**
	 * @return the currentObstacle
	 */
	public static Obstacle getCurrentObstacle() {
		return parent.currentObstacle;
	}

	/**
	 * @param currentObstacle the currentObstacle to set
	 */
	public static void setCurrentObstacle(Obstacle currentObstacle) 
	{
		if(currentObstacle == null)
		{	// on va déselectionné l'obstacle précédent
			if(parent.currentObstacle != null)
				parent.currentObstacle.setSelected(false);
			
			parent.currentObstacle = null;
			parent.refreshMVC();
		}
		else
		{
			if(parent.currentObstacle != null)
				parent.currentObstacle.setSelected(false);
		
			// le nouvelle obstacle est spécifié au manager
			parent.currentObstacle = currentObstacle;
			// on spécifie à l'obstacle qu'il est selectionné
			parent.currentObstacle.setSelected(true);
			
			parent.refreshMVC();
		}
			
	}

	/**
	 * @return the listObstacles
	 */
	public static List<Obstacle> getListObstacles() {
		return parent.listObstacles;
	}

	/**
	 * @param listObstacles the listObstacles to set
	 */
	public static void setListObstacles(List<Obstacle> listObstacles) {
		parent.listObstacles = listObstacles;
	}

	
	
	
	
}
