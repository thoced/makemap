package CoreTexturesManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Time;
import org.jsfml.window.event.Event;


public class TexturesManager
{
	// hash des textures
	private static Hashtable<String,Texture> hashTextures ;
	// Texture blanck pour éviter le plantage
	private static Texture blankTexture = null;
	
	// manager - permet a la methode static d'appeller une methode de l'objet
	private static TexturesManager manager;
	
	
	public TexturesManager()
	{
		// instance du hashtextures
		hashTextures = new Hashtable<String,Texture>();
		
		manager = this;
	}
	
	// methode static de récupération d'un objet texture sur base du nom
	public static Texture GetTextureByName(File path)
	{
		// le nom de la texture n'existe pas, on la charge
		if(!hashTextures.containsKey(path.getName()))
		{
			try 
			{
				manager.LoadTexture(path);
				return hashTextures.get(path.getName());
				
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
		
		
		
	}
	
	
	
	private void LoadTexture(File path) throws IOException
	{
		// TODO Auto-generated method stub
		
			Texture text = new Texture();
			text.loadFromFile(path.toPath());
			hashTextures.put(path.getName(), text);
			
		
	}

	
}
