package CorePanelInfo;

import java.io.File;

public class DataListTextures 
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

	
	
	
}
