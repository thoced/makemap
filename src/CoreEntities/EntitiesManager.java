package CoreEntities;

import java.util.ArrayList;
import java.util.List;

import CoreCalques.ICalqueMVC;

public class EntitiesManager 
{
	// liste des entities
	private List<EntitiesBase> listEntities;
	// liste des mvc
	private List<IEntitiesMVC> listMVC;
	
	public EntitiesManager()
	{
		// instance de la liste des entities
		this.listEntities = new ArrayList<EntitiesBase>();
		// instance de la liste des mvc
		this.listMVC = new ArrayList<IEntitiesMVC>();
	}
	
	public  void attachMVC(IEntitiesMVC mvc)
	{
		// ajout dans la liste mvc
		this.listMVC.add(mvc);
	}
}
