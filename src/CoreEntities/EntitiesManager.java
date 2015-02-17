package CoreEntities;

import java.util.ArrayList;
import java.util.List;

public class EntitiesManager 
{
	// liste des entities
	private List<EntitiesBase> listEntities;
	
	public EntitiesManager()
	{
		// instance de la liste des entities
		this.listEntities = new ArrayList<EntitiesBase>();
	}
}
