package CorePanelInfo;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import CoreCalques.Calque;
import CoreCalques.ICalqueMVC;

public class JListCalques extends JList implements ICalqueMVC
{

	public JListCalques()
	{
		super();
		
	}
	@Override
	public void updateCalqueMVC(List<Calque> list) 
	{
		// Instance du default list model
		DefaultListModel model = new DefaultListModel();
		// clear
		model.clear();
		// on ajoute la liste des calques
		for(Calque c : list)
			model.addElement(list);
		// on place le model dans le jlist
		this.setModel(model);
		
	}
	
}
