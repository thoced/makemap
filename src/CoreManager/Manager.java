package CoreManager;

import CoreObstacles.*;

public class Manager 
{
	private static ObstaclesManager obstaclesManager;
	
	public Manager()
	{
		Manager.obstaclesManager = new ObstaclesManager();
	}

	/**
	 * @return the obstaclesManager
	 */
	public static ObstaclesManager getObstaclesManager() {
		return obstaclesManager;
	}

	/**
	 * @param obstaclesManager the obstaclesManager to set
	 */
	public static void setObstaclesManager(ObstaclesManager obstaclesManager) {
		Manager.obstaclesManager = obstaclesManager;
	}
	
	
}
