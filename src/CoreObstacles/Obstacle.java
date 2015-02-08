package CoreObstacles;


import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Vertex;
import org.jsfml.graphics.VertexArray;
import org.jsfml.system.Vector2f;

public class Obstacle implements Drawable
{

	private List<PointObstacle> listPoints;
	
	// point selected
	private PointObstacle currentPointSelected = null;
	
	// VertexArray
	private VertexArray vectors;
	
	// name de l'obstacle
	private String name;
	
	// is Obstacle Selected
	private boolean isSelected = false;
	
	
	
	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected the isSelected to set
	 */
	public void setSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
		
		if(this.isSelected)
		{
			// l'obstacle est selectionné, on le rend blanc
			this.releaseVectors();
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Obstacle()
	{
		// Instance de la liste des points
		listPoints = new ArrayList<PointObstacle>();
		// Instance du vectors
		vectors = new VertexArray(PrimitiveType.LINE_STRIP);
	}
	
	@Override
	public void draw(RenderTarget render, RenderStates state) 
	{
		// TODO Auto-generated method stub
		render.draw(vectors,state);
		
	}
	
	public void insertPoint(Vector2f p)
	{
		// ajout du point
		PointObstacle point = new PointObstacle(p);
		// ajout
		listPoints.add(point);
		 
		// release vectors
		this.releaseVectors();
	
	}
	
	private void releaseVectors()
	{
		// mise à zero du nouveau vectors (vertexarray)
				vectors.clear();
				// création du vertex array
				for(PointObstacle po : listPoints)
				{
					Vertex v = new Vertex(po.getPoint(),Color.WHITE);
					// ajout dans le vectors
					vectors.add(v);
				}
	}
	
	public void hitPoint(Vector2f pos)
	{
		// on bouge le point si il est dans le hit
			boolean isSelected = false;
			for(PointObstacle p: listPoints)
			{
				if(p.getHitBoxPoint().contains(pos))
				{
					currentPointSelected = p;
					isSelected = true;
				}
					
			}
			
			if(!isSelected)
			{
				// rien n'a été selectionné, on met à null le current
				currentPointSelected = null;
			}
	}
	
	
	public void dragPoint(Vector2f pos)
	{
		if(currentPointSelected != null)
			currentPointSelected.setPoint(pos);
		
		// on modifie 
		this.releaseVectors();
	}
	
	public void setFixObstalce()
	{
		vectors.clear();
		// création du vertex array
		for(PointObstacle po : listPoints)
		{
			Vertex v = new Vertex(po.getPoint(),Color.BLUE);
			// ajout dans le vectors
			vectors.add(v);
		}
	}
	
	public String toString()
	{
		return this.getName();
	}
	
	
	

}
