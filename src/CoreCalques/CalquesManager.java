package CoreCalques;

import java.util.ArrayList;
import java.util.List;

public class CalquesManager 
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
	
	
}
