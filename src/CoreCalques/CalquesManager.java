package CoreCalques;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public class CalquesManager implements Drawable
{
	// list des calques
	private List<Calque> listCalques;
	// list des referecens mvc
	private List<ICalqueMVC> listMVC;
	
	// currentCalque
	private Calque currentCalque;
	
	// parent
	private static CalquesManager parent;
	
	public CalquesManager()
	{
		//instanc de la liste
		listCalques = new ArrayList();
		// instance de la liste MVC
		listMVC = new ArrayList();
		// parent
		parent = this;
	}
	
	public static void insertNewCalque(Calque nc)
	{
		// ajout du calque dans la liste
		parent.listCalques.add(nc);
		// refresh mvc
		
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
	public Calque getCurrentCalque() {
		return currentCalque;
	}

	/**
	 * @param currentCalque the currentCalque to set
	 */
	public void setCurrentCalque(Calque currentCalque) {
		this.currentCalque = currentCalque;
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
	public List<Calque> getListCalques() {
		return listCalques;
	}

	/**
	 * @param listCalques the listCalques to set
	 */
	public void setListCalques(List<Calque> listCalques) {
		this.listCalques = listCalques;
	}
	
	
	
	
}
