package CoreIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.spi.JsonProvider;

import CoreCalques.CalquesManager;
import CoreInfo.InfoMap;
import CoreObstacles.ObstaclesManager;

public class IOManager 
{
	// Fichier
	private File file;
	// class JsonWriter
	private JsonWriter writer;
	
	// 
	public static void writeMap(File file, InfoMap im,CalquesManager cm, ObstaclesManager om) throws FileNotFoundException
	{
		// création du OutputStream
		FileOutputStream fos = new FileOutputStream(file);
		
		// création du jsonwriter
		JsonWriter writer = JsonProvider.provider().createWriter(fos);
		
		// on enregistre 
		
		//1) on crée un objet JsonObject contenant la map en entier
		JsonObjectBuilder objectBuilder = JsonProvider.provider().createObjectBuilder();
		
		// 1) on créer l'objet map
		// ajout du nom de map
		objectBuilder.add("name_map", "test map");
		// ajout de la taille de la map
		objectBuilder.add("width_map", im.getWidthMap());
		objectBuilder.add("height_map", im.getHeightMap());
		
		// création du jsonobject
		JsonObject obj = objectBuilder.build();
		
		// enregistre
		writer.writeObject(obj);
		
		// close
		writer.close();
		
		
		
		
		
		
	}

}
