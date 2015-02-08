package CoreObstacles;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public class ObstaclesManager implements Drawable
{
	private List<Obstacle> listObstacles;
	
	// list du MVC attach
	private static List<IObstacleMVC> listMVC = new ArrayList();
	
	// parent
	private static ObstaclesManager parent;
	
	// cpt des noms
	private int nameCpt = 0;
	
	// currentObstacle selectionné
	private static Obstacle currentObstacle = null;
	
	
	
	public ObstaclesManager()
	{
		// parent
		parent = this;
		// instance
		listObstacles = new ArrayList<Obstacle>();
		
	}

	@Override
	public void draw(RenderTarget render, RenderStates state)
	{
		// TODO Auto-generated method stub
		for(Obstacle o : listObstacles)
		{
			render.draw(o,state);
		}
	}
	
	public Obstacle createNewObstacle()
	{
		// création d'un nouvelle objet obstacle
		Obstacle o = new Obstacle();
		// on specifie le currentObstacle
		this.currentObstacle = o;
		// créatin du nom de l'obstacle
		o.setName("obstacle " + String.valueOf(nameCpt));
		nameCpt++;
		// ajout de l'obstacle dans la liste des obstacles
		listObstacles.add(o);
		// update des mvc
		this.refreshMVC();
		// retour de l'obstalce
		
		return o;
	}
	
	public static void deleteObstacle(Obstacle obj)
	{
		// si l'obstacle est celui qui est selectionné, 
		if(parent.getCurrentObstacle() == obj)
		{
			obj.setSelected(false);
			parent.setCurrentObstacle(null);
		}
		// suppression de l'obstacle
		parent.listObstacles.remove(obj);
		// appel MVC
		parent.refreshMVC();
	}
	
	public void refreshMVC()
	{
		// rappel des mvc
		for(IObstacleMVC o : this.listMVC)
		{
			o.updateObstacleMVC(this.listObstacles);
		}
			
	}
	
	public static void attachMVC(IObstacleMVC obj)
	{
		// attach un objet mvc
		parent.listMVC.add(obj);
	}

	/**
	 * @return the currentObstacle
	 */
	public static Obstacle getCurrentObstacle() {
		return currentObstacle;
	}

	/**
	 * @param currentObstacle the currentObstacle to set
	 */
	public static void setCurrentObstacle(Obstacle currentObstacle) {
		ObstaclesManager.currentObstacle = currentObstacle;
	}

	
	
	
}
