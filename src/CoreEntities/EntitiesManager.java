package CoreEntities;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import CoreCalques.ICalqueMVC;

public class EntitiesManager implements Drawable
{
	// liste des entities
	private List<EntitiesBase> listEntities;
	// liste des mvc
	private List<IEntitiesMVC> listMVC;
	// current entities
	private EntitiesBase currentEntities;
	
	public EntitiesManager()
	{
		// instance de la liste des entities
		this.listEntities = new ArrayList<EntitiesBase>();
		// instance de la liste des mvc
		this.listMVC = new ArrayList<IEntitiesMVC>();
	}
	
	
	
	/**
	 * @return the listEntities
	 */
	public List<EntitiesBase> getListEntities() {
		return listEntities;
	}



	/**
	 * @param listEntities the listEntities to set
	 */
	public void setListEntities(List<EntitiesBase> listEntities) {
		this.listEntities = listEntities;
	}

	public void deleteEntities(EntitiesBase del)
	{
		// suppresion dans la liste
		this.listEntities.remove(del);
		// appel des mvc
		this.refreshMVC();
	}

	public  void attachMVC(IEntitiesMVC mvc)
	{
		// ajout dans la liste mvc
		this.listMVC.add(mvc);
	}
	
	public void refreshMVC()
	{
		// on appel les objets mvc attachés
		for(IEntitiesMVC i : this.listMVC)
			i.updateEntitiesMVC(this.listEntities);
	}
	
	public void insertEntities(EntitiesBase e)
	{
		// si l'objet n'existe pas on l'ajoute dans la liste
		if(!this.listEntities.contains(e))
		{
			this.listEntities.add(e);
		}
		// on rafraichit
		this.refreshMVC();
	}

	@Override
	public void draw(RenderTarget render, RenderStates state)
	{
	// on boucle sur la liste des entités
		for(EntitiesBase e : this.listEntities)
			e.draw(render, state);
		
	}

	/**
	 * @return the currentEntities
	 */
	public EntitiesBase getCurrentEntities() {
		return currentEntities;
	}

	/**
	 * @param currentEntities the currentEntities to set
	 */
	public void setCurrentEntities(EntitiesBase currentEntities) 
	{
		this.currentEntities = currentEntities;
		// on appel les mvc
		this.refreshMVC();
	}
	
	
}
