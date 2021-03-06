package CoreCalques;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.jsfml.graphics.BlendMode;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import CorePanelInfo.panelInfo;

public class Calque implements Drawable,java.lang.Comparable,Cloneable
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
	protected String virtualName;
	
	// proprietés
	
	private String type_calque = "statique";
	
	private float masse;
	
	private boolean danger;
	
	private float targetX;
	
	private float targetY;
	
	private float speed;
	
	private int layer = 2; // middleground	
	
	private boolean lightMap = false; 	
	
	private int alpha = 255;
	
	
	
	/**
	 * @return the alpha
	 */
	public int getAlpha() {
		return alpha;
	}



	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}



	/**
	 * @return the lightMap
	 */
	public boolean isLightMap() {
		return lightMap;
	}



	/**
	 * @param lightMap the lightMap to set
	 */
	public void setLightMap(boolean lightMap) {
		this.lightMap = lightMap;
	}



	public void rename(String name)
	{
		this.virtualName = name;
	}

	
	
	/**
	 * @return the fileTexture
	 */
	public Texture getFileTexture() {
		return fileTexture;
	}



	/**
	 * @param fileTexture the fileTexture to set
	 */
	public void setFileTexture(Texture fileTexture) {
		this.fileTexture = fileTexture;
	}



	/**
	 * @return the nameFileCalque
	 */
	public File getNameFileCalque() {
		return nameFileCalque;
	}



	/**
	 * @param nameFileCalque the nameFileCalque to set
	 */
	public void setNameFileCalque(File nameFileCalque) {
		this.nameFileCalque = nameFileCalque;
	}



	/**
	 * @return the layer
	 */
	public int getLayer() {
		return layer;
	}

	/**
	 * @param layer the layer to set
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}

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
	
	public Calque()
	{
		
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

	

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		
		Calque other = (Calque)arg0;
		
		if(other.getLayer() > this.getLayer())
			return -1;
		else if(other.getLayer() == this.getLayer())
			return 0;
		else return 1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException 
	{
		// TODO Auto-generated method stub
		Calque clone = null;
		
		try 
		{
			clone = new Calque((Texture)this.getSprite().getTexture(), this.nameFileCalque);
			clone.setDanger(this.isDanger());
			clone.setLayer(this.getLayer());
			clone.setMasse(this.getMasse());
			clone.setSpeed(this.getSpeed());
			clone.setTargetX(this.getTargetX());
			clone.setTargetY(this.getTargetY());
			clone.setType_calque(this.getType_calque());
			clone.setVirtualName(this.getVirtualName());
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clone;
		
	}



	@Override
	public void draw(RenderTarget render, RenderStates state) {
		// TODO Auto-generated method stub
		
		// modif du transparence du sprite
		Color color = new Color(255,255,255,this.alpha);
		this.getSprite().setColor(color);
		// creation du renderstate
		RenderStates st;
		if(this.isLightMap())
		{
			st = new RenderStates(BlendMode.ADD);
			render.draw(this.getSprite(),st);
		}
		else
			render.draw(this.getSprite());
	}

	
	
}
