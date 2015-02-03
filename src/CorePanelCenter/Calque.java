package CorePanelCenter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Calque 
{
	// fichier de la texture corresondante
	private Texture fileTexture;
	// sprite
	private Sprite sprite;
	// selected
	private boolean isSelected = false;
	
	public Calque(Texture texture) throws IOException
	{
		this.fileTexture = texture;
		
		if(this.fileTexture != null)
		{
			sprite = new Sprite(this.fileTexture);
			sprite.setPosition(new Vector2f(0,0));
			Vector2f v = new Vector2f(sprite.getTexture().getSize());
		//	sprite.setOrigin(Vector2f.div(v,2f));
		}
		
	}
	
	public void selected(Vector2f pos)
	{
		if(this.sprite.getGlobalBounds().contains(pos))
		{
			this.isSelected = true;
		}
	}
	
	
	
	public Sprite getSprite()
	{
		return this.sprite;
	}	
	
	public void clickMousePosition(int x,int y)
	{
		if(this.sprite.getGlobalBounds().contains(new Vector2f(x,y)))
		{
			// on click sur le calque
			isSelected = true;
		}
	}
	
	public void mousePosition(Vector2f pos)
	{
		if(isSelected)
			this.sprite.setPosition(pos);
		
		
	}
	
	public void setSelected(boolean selected)
	{
		isSelected = selected;
	}
	

	
}
