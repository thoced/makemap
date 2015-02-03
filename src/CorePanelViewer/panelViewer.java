package CorePanelViewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
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
		
		
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) 
	{
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		//render.setView(v);
		render.clear(org.jsfml.graphics.Color.TRANSPARENT);
		if(sprite!=null)
			render.draw(sprite);
		render.display();
		
		Texture mytexture = (Texture) render.getTexture();
		BufferedImage bi = mytexture.copyToImage().toBufferedImage();
		g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		
		
	}
	
	public static void setTexture(Texture texture)
	{
		 
		 parent.sprite.setTexture(texture);
		// Vector2f ori = new Vector2f(texture.getSize());
		 //sprite.setOrigin(Vector2f.div(ori, 2f));
		// parent.sprite.setOrigin(new Vector2f(texture.getSize().x / 2f,texture.getSize().y / 2f));
		 parent.sprite.setPosition(new Vector2f(0f,0f));
		 
		 // création du scale
		 float widthpanel = parent.getWidth();
		 float heightpanel = parent.getHeight();
		 
		 float widthTexture = texture.getSize().x;
		 float heightTexture = texture.getSize().y;
		 
		 float divx = widthpanel / widthTexture;
		 float divy = heightpanel / heightTexture;
		 


		 parent.sprite.setScale(new Vector2f(divx,divy));
		 
		
		 
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

	@Override
	public void componentResized(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		try 
		{
			render.create(this.getSize().width, this.getSize().height);
			
			v.setSize(new Vector2f(this.getSize().width,this.getSize().height));
			v.setCenter(new Vector2f(this.getSize().width/2, this.getSize().height/2));
			
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