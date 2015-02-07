package CorePanelViewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.graphics.Vertex;
import org.jsfml.graphics.VertexArray;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class panelViewer extends JPanel implements ComponentListener
{
	private RenderTexture render;
	
	private View v;
	
	private Sprite sprite = null;
	
	private static panelViewer parent;
	
	// creation du vertexarray
	private VertexArray vectors;
	private Texture texture;
	
	public panelViewer() throws TextureCreationException
	{
		super();
		
		parent = this;
		
		// 
		this.setBackground(Color.lightGray);
		
		this.setSize(256, 256);
		
		// creation du rendertexture
		render = new RenderTexture();
		render.create(256, 256);
		// creation du sprite
		parent.sprite = new Sprite();
		// View
		v = new View();
		
		// ajout des listeners
		this.addComponentListener(this);
		
		// instance
		vectors = new VertexArray(PrimitiveType.QUADS);
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) 
	{
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		// creation du renderstate
		RenderStates state = new RenderStates(this.texture);		
		//render.setView(v);
		render.clear(org.jsfml.graphics.Color.TRANSPARENT);
		render.setView(v);
			render.draw(this.vectors,state);
		render.display();
		
		// affichage sur le panel
		Texture mytexture = (Texture) render.getTexture();
		BufferedImage bi = mytexture.copyToImage().toBufferedImage();
		g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		
		
	}
	
	public static void setTexture(Texture texture)
	{
		 parent.texture = texture; 
		
		 // on recrée le vectors
		 parent.refreshVectors();
	
		// parent.sprite.setScale(new Vector2f(divx,divy));
		// parent.v.setSize(widthTexture, heightTexture);
		 
		 parent.repaint();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void refreshVectors()
	{
		Vector2f p1 = new Vector2f(0,0);
		Vector2f p2 = new Vector2f(this.getSize().width,0);
		Vector2f p3 = new Vector2f(this.getSize().width,this.getSize().height);
		Vector2f p4 = new Vector2f(0,this.getSize().height);
		
		// texture
		Vector2f t1 = new Vector2f(0,0);
		Vector2f t2 = new Vector2f(texture.getSize().x,0);
		Vector2f t3 = new Vector2f(texture.getSize().x,texture.getSize().y);
		Vector2f t4 = new Vector2f(0,texture.getSize().y);
		
		// modif du vectors
		Vertex v1 = new Vertex(p1,t1);
		Vertex v2 = new Vertex(p2,t2);
		Vertex v3 = new Vertex(p3,t3);
		Vertex v4 = new Vertex(p4,t4);
		
		// update du vectors
		vectors.clear();
		vectors.add(v1);
		vectors.add(v2);
		vectors.add(v3);
		vectors.add(v4);
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		try 
		{
			render.create(this.getSize().width, this.getSize().height);
			
			v.setSize(new Vector2f(this.getSize().width,this.getSize().height));
			v.setCenter(new Vector2f(this.getSize().width/2, this.getSize().height/2));
			
			if(this.texture != null)
				this.refreshVectors();
			
			// repaint()
			this.repaint();
			
		} catch (TextureCreationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void componentShown(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
		this.repaint();
	}
}