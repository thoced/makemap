package CoreObstacles;


import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

public class PointObstacle 
{
	// point
	private Vector2f point;
	// hitbox point
	private FloatRect hitBoxPoint;
	
	public PointObstacle(Vector2f p)
	{
		// creation de la hitbox point
		hitBoxPoint = new FloatRect(p.x-4,p.y-4,8,8);
		// on place le point
		point = p;
	}

	/**
	 * @return the point
	 */
	public Vector2f getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(Vector2f p)
{
		this.point = p;
		hitBoxPoint = new FloatRect(p.x-4,p.y-4,8,8);
	}

	/**
	 * @return the hitBoxPoint
	 */
	public FloatRect getHitBoxPoint() {
		return hitBoxPoint;
	}

	
	
	

}
