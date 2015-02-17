package CoreManager;

import CoreCalques.CalquesManager;
import CoreEntities.EntitiesManager;
import CoreObstacles.*;

public class Manager 
{
	// obstacles manager
	private static ObstaclesManager obstaclesManager;
	// calques manager
	private static CalquesManager calquesManager;
	// entities manager
	private static EntitiesManager entitiesManager;
	
	public Manager()
	{
		// instance
		Manager.obstaclesManager = new ObstaclesManager();
		
		Manager.calquesManager = new CalquesManager();
		
		Manager.entitiesManager = new EntitiesManager();
	}

	
	/**
	 * @return the entitiesManager
	 */
	public static EntitiesManager getEntitiesManager() {
		return entitiesManager;
	}

	/**
	 * @param entitiesManager the entitiesManager to set
	 */
	public static void setEntitiesManager(EntitiesManager entitiesManager) {
		Manager.entitiesManager = entitiesManager;
	}




	/**
	 * @return the calquesManager
	 */
	public static CalquesManager getCalquesManager() {
		return calquesManager;
	}



	/**
	 * @param calquesManager the calquesManager to set
	 */
	public static void setCalquesManager(CalquesManager calquesManager) {
		Manager.calquesManager = calquesManager;
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
