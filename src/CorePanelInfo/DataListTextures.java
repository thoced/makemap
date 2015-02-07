package CorePanelInfo;

import java.io.File;

public class DataListTextures implements java.lang.Comparable
{
	private File file;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public DataListTextures(File name)
	{
		file = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(file.getName().length() >= 4)
		{
			String temp = file.getName().substring(0, file.getName().length() - 4);
			return temp;
		}
		else
		{
			return file.getName();
		}
		
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public int compareTo(Object other) 
	{
		int valuePrefix = 0;
		int valueOther = 0;
		
		// TODO Auto-generated method stub
		DataListTextures data = (DataListTextures) other;
		String nameOther = data.getFile().getName();
		String prefixOther = nameOther.substring(0, 3);
		
		String nameCurrent = this.getFile().getName();
		String prefixCurrent = nameCurrent.substring(0, 3);
		
		// value pour prefixcurrent
		switch(prefixCurrent)
		{
		case "1EP" :  valuePrefix = 0;break;
		
		
		case "2EP": valuePrefix = 1; break;
		
			
		case "3EP": valuePrefix = 2; break;
		
		case "4EP": valuePrefix = 3; break;
		
		case "5EP" : valuePrefix = 4; break;
		}
		
		switch(prefixOther)
		{
		case "1EP" :  valueOther = 0;break;
		
		
		case "2EP": valueOther = 1; break;
		
			
		case "3EP": valueOther = 2; break;
		
		case "4EP": valueOther = 3; break;
		
		case "5EP" : valueOther = 4; break;
		}
		
		if(valueOther > valuePrefix)
			return -1;
		else if(valueOther == valuePrefix)
			return 0;
		else 
			return 1;
			
	}

	
	
	
}
