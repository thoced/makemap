package CoreObstacles;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public class ObstaclesManager implements Drawable
{
	private List<Obstacle> listObstacles;
	
	public ObstaclesManager()
	{
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
		// ajout de l'obstacle dans la liste des obstacles
		listObstacles.add(o);
		// retour de l'obstalce
		return o;
	}
}
