package CoreObstacles;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.CircleShape;
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
	
	public Obstacle(String name)
	{
		// instance de la liste des points
		listPoints = new ArrayList<PointObstacle>();
		// instance du vertexarray
		vectors = new VertexArray(PrimitiveType.LINE_STRIP);
		// name
		this.setName(name);
	}
	
	
	public  List<PointObstacle> getListPoints() {
		return listPoints;
	}

	
	public  void setListPoints(List<PointObstacle> listPoints) {
		this.listPoints = listPoints;
	}

	
	public boolean isSelected() {
		return isSelected;
	}

	
	public void setSelected(boolean isSelected)
	{
		// spécifie à l'obstacle qu'il est selectionné ou pas
		this.isSelected = isSelected;
		// on recrée le vectorArray
		releaseVectors();
		
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public void insertPoint(Vector2f p)
	{
		// on vérifie si le parametre n'est pas null
		if(p != null)
		{
			// instance du point
			PointObstacle point = new PointObstacle(p);
			// ajout dans la liste des points
			listPoints.add(point);
			// on recrée le vectors
			releaseVectors();
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
	
	public void deletePoint(Vector2f pos)
	{
		// on boucle dans la liste des points
		for(PointObstacle point : this.listPoints)
			{
				if(point.getHitBoxPoint().contains(pos))
				{
					// un point est sélectionné
					this.currentPointSelected = point;
					break;
					
				}
			}
		
		// on supprime le poinkt
		this.listPoints.remove(this.currentPointSelected);
		
		// on recrée le vecteur
		this.releaseVectors();
		
		// on appel les mvc
	}
	
	public void hitPoint(Vector2f pos)
	{
		// on boucle dans la liste des points
		for(PointObstacle point : this.listPoints)
		{
			if(point.getHitBoxPoint().contains(pos))
			{
				// un point est sélectionné
				this.currentPointSelected = point;
			}
		}
	}
	
	
	public void dragPoint(Vector2f pos)
	{
		// on drag le point selectionné
		if(this.currentPointSelected != null)
		{
			this.currentPointSelected.setPoint(pos);
			// on recrée le vecteur
			this.releaseVectors();
		}
		
		
	}
	


	@Override
	public void draw(RenderTarget render, RenderStates state) {
		// TODO Auto-generated method stub
		// on affiche l'obstacle
		
	
				if(this.vectors != null)
					render.draw(this.vectors, state);
				
				// affichage des cercles sur les sommets
				for(Vertex v : this.vectors)
				{
					CircleShape shape = new CircleShape();
					shape.setPosition(v.position);
					shape.setRadius(4f);
					shape.setOrigin(4f,4f);
					if(this.isSelected())
						shape.setFillColor(Color.BLUE);
					else
						shape.setFillColor(Color.WHITE);
					
					render.draw(shape,state);
				}
				
				
		
	}
	
	
}
