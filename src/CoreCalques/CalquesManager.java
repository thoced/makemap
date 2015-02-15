package CoreCalques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import CoreObstacles.Obstacle;

public class CalquesManager implements Drawable
{
	// list des calques
	private  List<Calque> listCalques = new ArrayList();
	// list des referecens mvc
	private  List<ICalqueMVC> listMVC = new ArrayList();
	
	// currentCalque
	private  Calque currentCalque;
	
	
	
	public CalquesManager()
	{
		
	}
	
	public  void insertNewCalque(Calque nc)
	{
		// on spécifie au calque qu'il est directment selectionné
		nc.setSelected(true);
		// on précise a l'ancien calque selectionné qu'il ne l'est plus
		if(this.getCurrentCalque() != null)
			this.getCurrentCalque().setSelected(false);
		// on specifie au manager le calque selectionné
		this.setCurrentCalque(nc);
		// ajout du calque dans la liste
		this.listCalques.add(nc);
		// on tire
		this.sortCalques();
		// refresh mvc
		this.refreshMVC();
		
	}
	
	public  void attachMVC(ICalqueMVC mvc)
	{
		// ajout dans la liste mvc
		this.listMVC.add(mvc);
	}
	
	public void refreshMVC()
	{
		// appel des objets attachés mvc
		for(ICalqueMVC mvc : this.listMVC)
			mvc.updateCalqueMVC(this.listCalques);
	}

	/**
	 * @return the currentCalque
	 */
	public  Calque getCurrentCalque() {
		return this.currentCalque;
	}

	/**
	 * @param currentCalque the currentCalque to set
	 */
	public  void setCurrentCalque(Calque currentCalque)
	{
		this.currentCalque = currentCalque;
		// on trie
		this.sortCalques();
		// refresh mvc
		this.refreshMVC();
	}

	@Override
	public void draw(RenderTarget render, RenderStates state) 
	{
		// TODO Auto-generated method stub
		for(Calque c : this.listCalques)
			c.draw(render, state);
		
	}

	/**
	 * @return the listCalques
	 */
	public  List<Calque> getListCalques() {
		return this.listCalques;
	}

	/**selected
	 * @param listCalques the listCalques to set
	 */
	public  void setListCalques(List<Calque> listCalques) {
		this.listCalques = listCalques;
	}
	
	public  void deleteCalque(Calque obj)
	{
		// si l'obstacle est celui qui est selectionné, 
		if(this.getCurrentCalque() == obj)
		{
			obj.setSelected(false);
			this.setCurrentCalque(null);
		}
		// suppression de l'obstacle
		this.listCalques.remove(obj);
		// appel MVC
		this.refreshMVC();
	}
	
	
	public  void sortCalques()
	{
		// tri
		Collections.sort(this.getListCalques());
		// refresh mvc
		//parent.refreshMVC();
	}
	
	public  void upCalque(Calque c)
	{
		// remonte la priorité du calque
		// on obtient l'indice du calque dans la liste
		for(int i=0;i<this.listCalques.size();i++)
		{
			// on récupère le calque
			Calque current = this.listCalques.get(i);
			
			if(current == c)
			{
				// on l'ajoute en indice -1 si l'indice n'est pas le 0
				if(i!=0)
				{
					// si il s'agit du même on le place plus haut
					// on remonte le calque
					this.listCalques.remove(current);
					this.listCalques.add(i-1,current);
				
					
				}
				break;
				
			}
				
		}
		
		// on appel les mvc
		this.refreshMVC();
	}
	
	public  void downCalque(Calque c)
	{
		// descend la priorité du calque
				// on obtient l'indice du calque dans la liste
				for(int i=0;i<this.listCalques.size();i++)
				{
					// on récupère le calque
					Calque current = this.listCalques.get(i);
					
					if(current == c)
					{
						
						// on supprime et ajoute si ce n'est pas le dernier
						if(i != this.listCalques.size() - 1)
						{
							// si il s'agit du même on le place plus bas
							// on supprime la calque
							// on remonte le calque
							this.listCalques.remove(current);
							this.listCalques.add(i+1,current);
							
							
						}
						break;
							
						
					}
						
				}
				
				// on appel les mvc
				this.refreshMVC();
	}
	
	
	
	
}
