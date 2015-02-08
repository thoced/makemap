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
	private static List<Calque> listCalques = new ArrayList();
	// list des referecens mvc
	private static List<ICalqueMVC> listMVC = new ArrayList();
	
	// currentCalque
	private static Calque currentCalque;
	
	// parent
	private static CalquesManager parent;
	
	public CalquesManager()
	{
		// parent
		parent = this;
	}
	
	public static void insertNewCalque(Calque nc)
	{
		// ajout du calque dans la liste
		parent.listCalques.add(nc);
		// refresh mvc
		parent.refreshMVC();
		
	}
	
	public static void attachMVC(ICalqueMVC mvc)
	{
		// ajout dans la liste mvc
		parent.listMVC.add(mvc);
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
	public static Calque getCurrentCalque() {
		return CalquesManager.currentCalque;
	}

	/**
	 * @param currentCalque the currentCalque to set
	 */
	public static void setCurrentCalque(Calque currentCalque)
	{
		CalquesManager.currentCalque = currentCalque;
		// refresh mvc
		parent.refreshMVC();
	}

	@Override
	public void draw(RenderTarget render, RenderStates state) 
	{
		// TODO Auto-generated method stub
		for(Calque c : this.listCalques)
			render.draw(c.getSprite(),state);
		
	}

	/**
	 * @return the listCalques
	 */
	public static List<Calque> getListCalques() {
		return CalquesManager.listCalques;
	}

	/**selected
	 * @param listCalques the listCalques to set
	 */
	public static void setListCalques(List<Calque> listCalques) {
		CalquesManager.listCalques = listCalques;
	}
	
	public static void deleteCalque(Calque obj)
	{
		// si l'obstacle est celui qui est selectionné, 
		if(parent.getCurrentCalque() == obj)
		{
			obj.setSelected(false);
			parent.setCurrentCalque(null);
		}
		// suppression de l'obstacle
		parent.listCalques.remove(obj);
		// appel MVC
		parent.refreshMVC();
	}
	
	
	public static void sortCalques()
	{
		// tri
		Collections.sort(CalquesManager.getListCalques());
		// refresh mvc
		parent.refreshMVC();
	}
	
	public static void upCalques(Calque c)
	{
		// remonte la priorité du calque
		// on obtient l'indice du calque dans la liste
		for(int i=0;i<parent.listCalques.size();i++)
		{
			// on récupère le calque
			Calque current = parent.listCalques.get(i);
			
			if(current == c)
			{
				// si il s'agit du même on le place plus haut
				// on supprime la calque
				parent.listCalques.remove(i);
				// on l'ajoute en indice -1 si l'indice n'est pas le 0
				if(i!=0)
					parent.listCalques.add(i-1,current);
				
			}
				
		}
		
		// on appel les mvc
		parent.refreshMVC();
	}
	
	
	
	
}
