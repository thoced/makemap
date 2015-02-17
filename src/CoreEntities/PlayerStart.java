package CoreEntities;

public class PlayerStart extends EntitiesBase 
{
	private static String[] TypePlayer = {"SmallRobot","BigRobot"};
	// position de départ playstart
	private float posxStart,posyStart;
	
	private String typePlayer;
	
	public PlayerStart()
	{
		super();
	}

	/**
	 * @return the posxStart
	 */
	public float getPosxStart() {
		return posxStart;
	}

	/**
	 * @param posxStart the posxStart to set
	 */
	public void setPosxStart(float posxStart) {
		this.posxStart = posxStart;
	}

	/**
	 * @return the posyStart
	 */
	public float getPosyStart() {
		return posyStart;
	}

	/**
	 * @param posyStart the posyStart to set
	 */
	public void setPosyStart(float posyStart) {
		this.posyStart = posyStart;
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
