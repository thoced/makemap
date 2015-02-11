package CoreIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.spi.JsonProvider;
import javax.swing.JOptionPane;

import makemap.DataManager;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import CoreCalques.Calque;
import CoreCalques.CalquesManager;
import CoreInfo.InfoMap;
import CoreObstacles.Obstacle;
import CoreObstacles.ObstaclesManager;
import CoreObstacles.PointObstacle;
import CoreTexturesManager.TexturesManager;

public class IOManager 
{
	// Fichier
	private File file;
	// class JsonWriter
	private JsonWriter writer;
	
	// 
	
	public static void readMap(File file) throws IOException
	{
		// creatin du inputstream
		FileInputStream fis = new FileInputStream(file);
		
		// création du JsonReader
		JsonReader reader = JsonProvider.provider().createReader(fis);
		// on ouvre la map
		JsonObject objMap = reader.readObject();
		
		// widthmap
		if(objMap.containsKey("width_map"))
			InfoMap.setWidthMap(objMap.getInt("width_map"));
		// heightmap
		if(objMap.containsKey("height_map"))
			InfoMap.setHeightMap(objMap.getInt("height_map"));
		
		// extract des calques
		if(objMap.containsKey("calques"))
		{
			extractAllCalques(objMap.getJsonArray("calques"));
		}
		// extract des obstacles
		if(objMap.containsKey("obstacles"))
		{
			extractAllObstacles(objMap.getJsonArray("obstacles"));
		}
		
		
		
		
	}
	
	public static void extractAllCalques(JsonArray calques) throws IOException
	{
		// on boucle dans la liste des calques
		for(int i=0;i<calques.size();i++)
		{
			// on récupère un calque
			JsonObject calque = calques.getJsonObject(i);
			String virtualName = null;
			String path = null;
			String typeCalque = null;
			float x = 0,y = 0;
			int layer = 0;
			float speed = 0;
			float masse = 0;
			float targetX = 0,targetY = 0;
			boolean danger = false;
			
			// virtual name
			if(calque.containsKey("virtual_name"))
				virtualName = calque.getString("virtual_name");
			// path
			if(calque.containsKey("path"))
				path = calque.getString("path");
			// type de calque
			if(calque.containsKey("type_calque"))
				typeCalque = calque.getString("type_calque");
			// x et y
			if(calque.containsKey("x") && calque.containsKey("y"))
			{
				x = (float) calque.getJsonNumber("x").doubleValue();
				y = (float) calque.getJsonNumber("y").doubleValue();
			}
			// layer
			if(calque.containsKey("layer"))
				layer = calque.getInt("layer");
			// vitesse
			if(calque.containsKey("speed"))
				speed = (float) calque.getJsonNumber("speed").doubleValue();
			// masse
			if(calque.containsKey("masse"))
				masse = (float) calque.getJsonNumber("masse").doubleValue();
			// targetX et targetY
			if(calque.containsKey("targetX") && calque.containsKey("targetY"))
			{
				targetX = (float) calque.getJsonNumber("targetX").doubleValue();
				targetY = (float) calque.getJsonNumber("targetY").doubleValue();
			}
			// danger
			if(calque.containsKey("danger"))
				danger = calque.getBoolean("danger");
			
			// on créer le calque
			// on crée d'abord un un chemin total sur base du répertoire de texture
			String tt = null;
			String os = System.getProperty("os.name");
			
			if(os.equals("Linux"))
				tt = DataManager.directoryTextures.getAbsolutePath() + "/" + path;
			else
				tt = DataManager.directoryTextures.getAbsolutePath() + "\\" + path;
				
			File file = new File(tt);
			Texture text = TexturesManager.GetTextureByName(file);
			Calque c = new Calque(text, file);
			
			// on initialise
			c.setLayer(layer);
			c.setMasse(masse);
			c.setVirtualName(virtualName);
			c.setSpeed(speed);
			c.setType_calque(typeCalque);
			c.setTargetX(targetX);
			c.setTargetY(targetY);
			c.getSprite().setPosition(new Vector2f(x,y));
			c.setDanger(danger);
		
			// ajout dans le manager calque
			CalquesManager.insertNewCalque(c);
		
		}
	}
	
	public static void extractAllObstacles(JsonArray obstacles)
	{
		// on boucle dans la liste des obstacles
	
		for(int i=0;i<obstacles.size();i++)
		{
			// oh récupère un obsacle
			JsonObject obstacle = obstacles.getJsonObject(i);
			
			String name = obstacle.getString("name_obstacle");
			
			// on récupère l'array des obstacles
			JsonArray points = obstacle.getJsonArray("list_points");
			
			// on créer un objet Obstacle
			ObstaclesManager.setCurrentObstacle(ObstaclesManager.createNewObstacle());
			
			// on boucle dans la liste des points
			for(int j=0;j<points.size();j++)
			{
				JsonObject point = points.getJsonObject(j);
				
				// on récupère les x,y
				float x = (float) point.getJsonNumber("x").doubleValue();
				float y = (float) point.getJsonNumber("y").doubleValue();
				
				// ajout du point
				ObstaclesManager.getCurrentObstacle().insertPoint(new Vector2f(x,y));
				
			}
			
			// ajout du nom
			ObstaclesManager.getCurrentObstacle().setName(name);
			// fix obstacle
			ObstaclesManager.getCurrentObstacle().setFixObstalce();
			// ajout dans le manager
			ObstaclesManager.setCurrentObstacle(null);
			
		}
		
		
	}
	
	
	public static void writeMap(File file) throws FileNotFoundException
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
		objectBuilder.add("width_map", InfoMap.getWidthMap());
		objectBuilder.add("height_map", InfoMap.getHeightMap());
		
		// insert des calques
		insertAllCalques(objectBuilder);
		// insert des obstacles
		insertAllObstacles(objectBuilder);
		// build()
		JsonObject objWrite = objectBuilder.build();
		// enregistre
		writer.writeObject(objWrite);
		
		// close
		writer.close();
		
	}
	
	private static void insertAllCalques(JsonObjectBuilder builderObjectMap)
	{
		// création d'un arrayjson pour l'ensemble des calques
		JsonArrayBuilder arrayCalques = JsonProvider.provider().createArrayBuilder();
		
		// ajout des calques
		for(Calque c : CalquesManager.getListCalques())
		{
			JsonObjectBuilder objCalque = JsonProvider.provider().createObjectBuilder();
			// ajout du nom
			objCalque.add("virtual_name", c.getVirtualName());
			// ajout du path
			objCalque.add("path", c.getNameFileCalque().getName());
			// type de calque
			objCalque.add("type_calque", c.getType_calque());
			// position
			objCalque.add("x", c.getSprite().getPosition().x);
			objCalque.add("y", c.getSprite().getPosition().y);
			// layer
			objCalque.add("layer", c.getLayer());
			// vitesse
			objCalque.add("speed", c.getSpeed());
			// masse
			objCalque.add("masse", c.getMasse());
			// targetX et targetY
			objCalque.add("targetX",c.getTargetX());
			objCalque.add("targetY", c.getTargetY());
			// danger
			objCalque.add("danger",c.isDanger());
			
			// on ajoute dans le array
			arrayCalques.add(objCalque);
			
		}
		
		// on ajoute l'array dans le builder map principal
		builderObjectMap.add("calques", arrayCalques);
	}
	
	private static void insertAllObstacles(JsonObjectBuilder builderObjectMap)
	{
		// création d'un arrayjson pour l'ensemble des obstacles
		JsonArrayBuilder arrayObstacles = JsonProvider.provider().createArrayBuilder();
		
		// ajout des obstacles
		for(Obstacle o : ObstaclesManager.getListObstacles())
		{
			// création d'un objet pour l'obstacle
			JsonObjectBuilder objObstacle = JsonProvider.provider().createObjectBuilder();
			// ajout des information de l'obstacle
			objObstacle.add("name_obstacle", o.getName());
			// ajout de la liste des points
			JsonArrayBuilder arrayPoints = JsonProvider.provider().createArrayBuilder();
			for(PointObstacle point : o.getListPoints())
			{
				// création d'un objet pour placer le point
				JsonObjectBuilder pointBuilder = JsonProvider.provider().createObjectBuilder();
				// ajout du point
				pointBuilder.add("x", point.getPoint().x);
				pointBuilder.add("y", point.getPoint().y);
				
				// ajout dans l'array
				arrayPoints.add(pointBuilder);
				
			}
			// ajout dans l'objet obstacle du arrayPoint
			objObstacle.add("list_points",arrayPoints);
			// ajout de l'objObstacle dans le vecteur des obstacles
			arrayObstacles.add(objObstacle);
		}
		
		// on ajoute l'array des obstacles dans le builderobjectmap
		builderObjectMap.add("obstacles", arrayObstacles);
	}

}
