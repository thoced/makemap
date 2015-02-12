package CoreCalques;

import java.io.File;
import java.io.IOException;

import org.jsfml.graphics.Texture;

public class CalqueNull extends Calque 
{

	public CalqueNull(Texture texture, File nameFile) throws IOException {
		super(texture, nameFile);
		// TODO Auto-generated constructor stub
	}
	
	public String toString()
	{
		return "-------------";
			
	}

}
