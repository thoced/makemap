package CorePanelInfo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import CoreEntities.EntitiesBase;
import CoreEntities.IEntitiesMVC;
import CoreManager.Manager;

public class JListEntities extends JList implements IEntitiesMVC,MouseListener
{
	public JListEntities()
	{
		this.addMouseListener(this);
	}
	
	@Override
	public void updateEntitiesMVC(List<EntitiesBase> list) 
	{
		// TODO Auto-generated method stub
		// création du default list model
		DefaultListModel model = new DefaultListModel();
		// on boucle dans la liste et on ajoute dans le model
		for(EntitiesBase e : list)
		{
			model.addElement(e);
		}
		// on ajoute le model dans le jlist
		this.setModel(model);
		
		// on place la sélection
		if(Manager.getEntitiesManager().getCurrentEntities() != null)
		this.setSelectedValue(Manager.getEntitiesManager().getCurrentEntities(), false);
		
		// on spécifie le panel
			panelInfo.setPanelProperties(Manager.getEntitiesManager().getCurrentEntities().getPanelEntitiesProperties());
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		// on récupère l'entities selectionné
		EntitiesBase e = (EntitiesBase) this.getSelectedValue();
		if(e != null)
		{
			// on selectionne l'objet
			e.setSelected(true);
			// on spécifie au Manager que l'ancien entité ne doit plus être sélectionné
			if(Manager.getEntitiesManager().getCurrentEntities() != null)
				Manager.getEntitiesManager().getCurrentEntities().setSelected(false);
			// on indique au manager le nouvelle entitié sélectionné
			Manager.getEntitiesManager().setCurrentEntities(e);
			
			// on spécifie le panel
			panelInfo.setPanelProperties(e.getPanelEntitiesProperties());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
