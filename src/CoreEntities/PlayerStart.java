package CoreEntities;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import CoreEntities.CoreEntitiesPanel.PanelPlayerStart;
import CoreTexturesManager.TexturesManager;

public class PlayerStart extends EntitiesBase 
{
	public static enum MYTYPE {SMALL_ROBOT,BIG_ROBOT};
	
	private static String[] TypePlayer = {"SmallRobot","BigRobot"};
	// position de départ playstart
	private float posxStart,posyStart;
	
	private String typePlayer;
	
	public PlayerStart()
	{
		// appel au super constructeur
		super();
		// création du sprite
		this.shape = new RectangleShape();
		this.shape.setFillColor(Color.MAGENTA);
		this.shape.setSize(new Vector2f(32,32f));
		// on spécifie le type d'entité
		this.typeName = "PLAYERSTART";
		// instance du panel
		this.panelEntitiesProperties = new PanelPlayerStart();
		
	}

	

	/**
	 * @return the typePlayer
	 */
	public String getTypePlayer() {
		return typePlayer;
	}

	/**
	 * @param typePlayer the typePlayer to set
	 */
	public void setTypePlayer(String typePlayer) {
		this.typePlayer = typePlayer;
	}
	
	
	
	
}
