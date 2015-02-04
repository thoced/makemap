package CorePanelCenter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Calque 
{
	// fichier de la texture corresondante
	private Texture fileTexture;
	// File
	private File nameFileCalque;
	// sprite
	private Sprite sprite;
	// selected
	private boolean isSelected = false;
	// virtual name
	private String virtualName;
	
	
	
	/**
	 * @return the virtualName
	 */
	public String getVirtualName() {
		return virtualName;
	}

	/**
	 * @param virtualName the virtualName to set
	 */
	public void setVirtualName(String virtualName) {
		this.virtualName = virtualName;
	}

	public Calque(Texture texture,File nameFile) throws IOException
	{
		this.fileTexture = texture;
		this.nameFileCalque = nameFile;
		this.virtualName = (String) this.nameFileCalque.getName();
		
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
			// modification de la couleur
			this.getSprite().setColor(Color.BLUE);
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
		if(isSelected)
			this.getSprite().setColor(Color.BLUE);
		else
			this.getSprite().setColor(Color.WHITE);
	}
	
	public String toString()
	{
		if(this.virtualName != null)
		{
			return this.virtualName;
		}
			
		else
			return "NO NAME";
			
	}

	
}
