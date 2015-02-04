package CorePanelCenter;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Vertex;
import org.jsfml.graphics.VertexArray;
import org.jsfml.system.Vector2f;

public class Grid implements Drawable 
{

	// liste des vertex
	private VertexArray vectors;
	
	public Grid(int width,int height)
	{
		vectors = new VertexArray(org.jsfml.graphics.PrimitiveType.LINES);
		
		int nbTilesX = width / 32;
		int nbTilesY = height / 32;
		
		/*for(int y=0;y < height ;y+=32)
		{
			for(int x=0;x<width;x+=32)
			{
				Vertex v1 = new Vertex(new Vector2f(x,y),Color.WHITE);
				Vertex v2 = new Vertex(new Vector2f(x+32,y),Color.WHITE);
				Vertex v3 = new Vertex(new Vector2f(x+32,y+32),Color.WHITE);
				Vertex v4 = new Vertex(new Vector2f(x,y+32),Color.WHITE);
				
				// ajout dans le vertexarray
				vectors.add(v1);
				vectors.add(v2);
				vectors.add(v3);
				vectors.add(v4);
			}
		}*/
		
		Color color = new Color(152,152,152);
		
		for(int x=0;x<width;x+=32)
		{
			Vertex v1 = new Vertex(new Vector2f(x,0),color);
			Vertex v2 = new Vertex(new Vector2f(x ,height),color);
			
			vectors.add(v1);
			vectors.add(v2);
		}
		
		for(int y=0;y<height;y+=32)
		{
			Vertex v1 = new Vertex(new Vector2f(0,y),color);
			Vertex v2 = new Vertex(new Vector2f(width ,y),color);
			
			vectors.add(v1);
			vectors.add(v2);
		}
		
		
		
		
	}
	
	@Override
	public void draw(RenderTarget render, RenderStates state) {
		// TODO Auto-generated method stub
		
		render.draw(vectors,state);
	}

}
