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
import org.jsfml.system.Vector2f;

public class panelViewer extends JPanel
{
	private RenderTexture render;
	
	private Sprite sprite;
	
	public panelViewer() throws TextureCreationException
	{
		super();
		
		// 
		this.setBackground(Color.GRAY);
		
		this.setSize(256, 256);
		
		// creation du rendertexture
		render = new RenderTexture();
		render.create(256, 256);
		
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) 
	{
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		render.clear(org.jsfml.graphics.Color.TRANSPARENT);
		render.draw(sprite);
		render.display();
		
		Texture mytexture = (Texture) render.getTexture();
		BufferedImage bi = mytexture.copyToImage().toBufferedImage();
		g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		
		
	}
	
	public void setTexture(Texture texture)
	{
		 sprite = new Sprite(texture);
		// Vector2f ori = new Vector2f(texture.getSize());
		 //sprite.setOrigin(Vector2f.div(ori, 2f));
		 sprite.setPosition(new Vector2f(0f,0f));
		 
		
	}
}
