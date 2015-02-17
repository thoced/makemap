package CoreEntities;

import javax.swing.JPanel;

import org.jsfml.graphics.Sprite;

public class EntitiesBase 
{
	// sprite graphique representant l'entities dans makemap
	protected Sprite sprite;

	// panel contenant les propriété relative à l'entité
	protected JPanel panelEntitiesProperties;
	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
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
	
	
}
