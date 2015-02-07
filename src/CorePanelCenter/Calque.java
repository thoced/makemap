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

import CorePanelInfo.panelInfo;

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
	
	// proprietés
	
	private String type_calque;
	
	private float masse;
	
	private boolean danger;
	
	private float targetX;
	
	private float targetY;
	
	private float speed;
	
	
	
	
	/**
	 * @return the type_calque
	 */
	public String getType_calque() {
		return type_calque;
	}

	/**
	 * @param type_calque the type_calque to set
	 */
	public void setType_calque(String type_calque) {
		this.type_calque = type_calque;
	}

	/**
	 * @return the masse
	 */
	public float getMasse() {
		return masse;
	}

	/**
	 * @param masse the masse to set
	 */
	public void setMasse(float masse) {
		this.masse = masse;
	}

	/**
	 * @return the danger
	 */
	public boolean isDanger() {
		return danger;
	}

	/**
	 * @param danger the danger to set
	 */
	public void setDanger(boolean danger) {
		this.danger = danger;
	}

	/**
	 * @return the targetX
	 */
	public float getTargetX() {
		return targetX;
	}

	/**
	 * @param targetX the targetX to set
	 */
	public void setTargetX(float targetX) {
		this.targetX = targetX;
	}

	/**
	 * @return the targetY
	 */
	public float getTargetY() {
		return targetY;
	}

	/**
	 * @param targetY the targetY to set
	 */
	public void setTargetY(float targetY) {
		this.targetY = targetY;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

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
			
			sprite.setOrigin(new Vector2f(0,0));
		}
		
	}
	
	public boolean selected(Vector2f pos)
	{
		if(this.sprite.getGlobalBounds().contains(pos))
		{
			this.isSelected = true;
			// modification de la couleur
			this.getSprite().setColor(Color.BLUE);
			
			// selection dans la listCalque
			panelInfo.selectedCalque(this);
			
			return true;
		}
		// deselection dans la listCalque
			//panelInfo.deselectAllCalque();
		return false;
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
