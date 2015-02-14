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

	private static List<PointObstacle> listPoints;
	
	// point selected
	private PointObstacle currentPointSelected = null;
	
	// VertexArray
	private VertexArray vectors;
	
	// name de l'obstacle
	private String name;
	
	// is Obstacle Selected
	private boolean isSelected = false;
	
	private static Obstacle parent;
	
	
	
	/**
	 * @return the listPoints
	 */
	public static List<PointObstacle> getListPoints() {
		return parent.listPoints;
	}

	/**
	 * @param listPoints the listPoints to set
	 */
	public static void setListPoints(List<PointObstacle> listPoints) {
		parent.listPoints = listPoints;
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return parent.isSelected;
	}

	/**
	 * @param isSelected the isSelected to set
	 */
	public void setSelected(boolean isSelected)
	{
		// spécifie à l'obstacle qu'il est selectionné ou pas
		this.isSelected = isSelected;
		// on recrée le vectorArray
		releaseVectors();
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return parent.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		parent.name = name;
	}

	public Obstacle()
	{
		// parent statique
		parent = this;
		// instance de la liste des points
		listPoints = new ArrayList<PointObstacle>();
		// instance du vertexarray
		vectors = new VertexArray(PrimitiveType.LINE_STRIP);
	}
	
	@Override
	public void draw(RenderTarget render, RenderStates state) 
	{
		// on affiche l'obstacle
		if(parent.vectors != null)
			render.draw(parent.vectors, state);
	}
	
	public void insertPoint(Vector2f p)
	{
		// on vérifie si le parametre n'est pas null
		if(p != null)
		{
			// instance du point
			PointObstacle point = new PointObstacle(p);
			// ajout dans la liste des points
			parent.listPoints.add(point);
			// on recrée le vectors
			parent.releaseVectors();
		}
	
	}
	
	private void releaseVectors()
	{
		// on vide le vectors array
		vectors.clear();
		// on boucle dans la liste des points et on crée les vertex
		for(PointObstacle point : this.listPoints)
		{
			Vertex v;
			// si l'obstacle est selectionné alors celui-ci est en bleu
			if(this.isSelected())
				 v = new Vertex(point.getPoint(),org.jsfml.graphics.Color.BLUE);
			else
				 v = new Vertex(point.getPoint(),org.jsfml.graphics.Color.WHITE);
			// ajout dans le vectors array
			vectors.add(v);
			
		}
	}
	
	public void hitPoint(Vector2f pos)
	{
		
	}
	
	
	public void dragPoint(Vector2f pos)
	{
		
	}
	
	public void setFixObstalce()
	{
		
	}
	
	public String toString()
	{
		return parent.getName();
	}
	
	
	

}
