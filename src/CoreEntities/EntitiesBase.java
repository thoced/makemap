package CoreEntities;

import javax.swing.JPanel;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

import CoreManager.Manager;


public class EntitiesBase implements Drawable
{
	// Shape graphique representant l'entities dans makemap
	protected RectangleShape shape;

	// panel contenant les propriété relative à l'entité
	protected JPanel panelEntitiesProperties;
	// private float posxStart,posyStart;
	protected float posX,posY;
	
	// type de l'entité
	protected String typeName;
	// isSelected
	protected boolean isSelected = false;
	
	
	
	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void hitEntities(Vector2f pos)
	{
		if(shape.getGlobalBounds().contains(pos))
		{
			this.isSelected = true;
			// on enlève l'ancien entité du current
			if(Manager.getEntitiesManager().getCurrentEntities() != null)
				Manager.getEntitiesManager().getCurrentEntities().setSelected(false);
			// on spécifie qu'on est le curren
			Manager.getEntitiesManager().setCurrentEntities(this);
		}
		else
			this.isSelected = false;
		
		
	}
	
	public void dragEntities(Vector2f pos)
	{
		if(this.isSelected)
		{
			this.setPosxStart(pos.x);
			this.setPosyStart(pos.y);
		}
	}
	
	/**
	 * @return the posxStart
	 */
	public float getPosxStart() {
		return this.posX;
	}

	/**
	 * @param posyStart the posyStart to set
	 */
	public void setPosyStart(float posyStart) {
		this.posY = posyStart;
		// placement de la position y
		this.shape.setPosition(this.shape.getPosition().x,this.posY);
	}
	
	/**
	 * @param posxStart the posxStart to set
	 */
	public void setPosxStart(float posxStart) 
	{
		this.posX = posxStart;
		
		// placement de la position
		this.shape.setPosition(this.posX, this.shape.getPosition().y);
	}

	/**
	 * @return the posyStart
	 */
	public float getPosyStart() {
		return this.posY;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @return the shape
	 */
	public RectangleShape getShape() {
		return shape;
	}

	/**
	 * @param shape the shape to set
	 */
	public void setShape(RectangleShape shape) {
		this.shape = shape;
	}

	/**
	 * @return the panelEntitiesProperties
	 */
	public JPanel getPanelEntitiesProperties() {
		return panelEntitiesProperties;
	}

	/**
	 * @param panelEntitiesProperties the panelEntitiesProperties to set
	 */
	public void setPanelEntitiesProperties(JPanel panelEntitiesProperties) {
		this.panelEntitiesProperties = panelEntitiesProperties;
	}

	@Override
	public void draw(RenderTarget render, RenderStates state) {
		
		// render du shape de l'entité
		if(this.shape != null)
			render.draw(shape,state);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.typeName;
	}
	
	
	
}
