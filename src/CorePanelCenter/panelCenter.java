package CorePanelCenter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Context;
import org.jsfml.window.ContextActivationException;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import CoreCalques.Calque;
import CoreCalques.CalquesManager;
import CoreCalques.ICalqueMVC;
import CoreManager.Manager;
import CoreObstacles.IObstacleMVC;
import CoreObstacles.Obstacle;

import CorePanelInfo.PropertiesPanel;
import CorePanelInfo.panelInfo;
import CoreTexturesManager.TexturesManager;
import makemap.DataManager;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class panelCenter extends JPanel implements KeyListener,MouseWheelListener,MouseListener,MouseMotionListener,ComponentListener,AdjustmentListener,ICalqueMVC,IObstacleMVC
{
	/**
	 * 
	 */
	
	
	// render texture jsfml
	private RenderTexture render;
	
	// Shape
	private RectangleShape shape;
	
	private static View v;
	private float zoom = 1f;
	private int   levelZoom = 0;
	private Sprite sprite;
	private JScrollBar scrollBarHorizontale;
	private JScrollBar scrollBarVerticale;
	
	// taille de la map
	private int widthMap,heightMap;
	// echelle 
	private final int size = 32;
	// ismap
	private boolean isMapCreate = false;
	// Grid
	private Grid grid;
	private boolean isViewGrid = false;
	// Snap grid
	private boolean isSnapGrid = false;
	
	// position start pour le déplacement
	private Vector2f posStart,posDiff;
	
	// current Calque
	private Calque currentCalque;
	
	// Calque manager
	private CalquesManager calquesManager;
	// si c'est un obstacle
	//private Manager.getObstaclesManager() Manager.getObstaclesManager();
	
	
	private  boolean isManagerObstacle = false;
	
	// Font
	private Font font;
	// Text
	private Text text;
	// vecteur différence pour la positin start du glissview
	private Vector2f diffGlissView;
	
	// private 
	private static panelCenter parent;
	
	// private list des calques
	//private List<Calque> listCalques = new ArrayList<Calque>();

	public panelCenter() throws IOException, TextureCreationException, ContextActivationException
	{
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		this.setBackground(Color.GRAY);
		// set double buffered
		this.setDoubleBuffered(true);
		// layout
		
		// ajout dans le datamanager de la réference this
		DataManager.panelCenter = this;
	
		
		render= new RenderTexture();
		
		// rectangle shape
		shape = new RectangleShape();
		shape.setOutlineThickness(8);
		shape.setFillColor(org.jsfml.graphics.Color.TRANSPARENT);
		shape.setOutlineColor(org.jsfml.graphics.Color.RED);
		
		
		try {
			render.create(800, 600);
			
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		v = new View();
		v.zoom(zoom);
		v.setSize(render.getSize().x,render.getSize().y);
		v.setCenter(0f,0f);
		
	
				

		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addComponentListener(this);
		this.addMouseWheelListener(this);
		this.addKeyListener(this);
		
		setLayout(new BorderLayout(0, 0));
		
		scrollBarVerticale = new JScrollBar();
		
		
		add(scrollBarVerticale, BorderLayout.WEST);
		
		scrollBarHorizontale = new JScrollBar();
		scrollBarHorizontale.setOrientation(JScrollBar.HORIZONTAL);
		add(scrollBarHorizontale, BorderLayout.SOUTH);
		
	
		scrollBarHorizontale.addAdjustmentListener(this);
		scrollBarVerticale.addAdjustmentListener(this);
		
		parent = this;
		
		// chargement du font
		font = new Font();
		font.loadFromStream(panelCenter.class.getResourceAsStream("/Fonts/ManilaSansReg.otf"));
		// text
		text = new Text();
		
		// Calques manager
		calquesManager = new CalquesManager();
		calquesManager.attachMVC(this);
		// Obstacle Manager
		
		Manager.getObstaclesManager().attachMVC(this);
		
	}
	
	

	




	/**
	 * @return the v
	 */
	public static View getV() {
		return v;
	}



	/**
	 * @param v the v to set
	 */
	public static void setV(View v) {
		panelCenter.v = v;
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		render.clear(org.jsfml.graphics.Color.TRANSPARENT);
		render.setView(v);
		
		// dessin du carre
		if(grid != null && isViewGrid)
		{
			//render.draw(shape);
			render.draw(grid);
		}
		// dessin des calques
		render.draw(calquesManager);
		// dessin des obstacles
		render.draw(Manager.getObstaclesManager());
		
		render.display();
		
		// copie de l'image généré par jsfml dans le panel
		Texture mytexture = (Texture) render.getTexture();
		BufferedImage bi = mytexture.copyToImage().toBufferedImage();
		g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		
		
	}
	
	public static void sortCalques()
	{
		// tri
		Collections.sort(parent.calquesManager.getListCalques());
		// repaint
		parent.repaint();
		// on rafraichit également la liste des calques
		panelInfo.refreshListCalque(parent.calquesManager.getListCalques());
	}
	
	public static void insertCalque(Calque calque)
	{
		// ajout du calque
		CalquesManager.insertNewCalque(calque);
		// on trie le calque
		sortCalques();
		// affichage
		parent.repaint();
	}
	
	public  void createMap(int width,int height) throws IOException, TextureCreationException
	{
		// taille de l'image
		this.widthMap = width;
		this.heightMap = height;
		
		// creatino du shape 
		this.shape.setSize(new Vector2f(this.widthMap * this.size,this.heightMap * this.size));
		
		this.scrollBarHorizontale.setMaximum(this.widthMap);
		this.scrollBarVerticale.setMaximum(this.heightMap);
		// on place 
		// on active le rectangle
		this.isMapCreate = true;
		// repaint
		// création du grid
		this.createGrid();
		
		this.repaint();
	}
	
	public  void setObstacleManager(boolean active)
	{
		parent.isManagerObstacle = active;
		if(!active && Manager.getObstaclesManager().getCurrentObstacle() != null)
		{
			Manager.getObstaclesManager().fixObstacle();
			Manager.getObstaclesManager().setCurrentObstacle(null);
			this.repaintCalques();
		}
	}
	
	/**
	 * @return the isSnapGrid
	 */
	public boolean isSnapGrid() {
		return isSnapGrid;
	}

	/**
	 * @param isSnapGrid the isSnapGrid to set
	 */
	public void setSnapGrid(boolean isSnapGrid) {
		this.isSnapGrid = isSnapGrid;
	}

	/**
	 * @return the isViewGrid
	 */
	public boolean isViewGrid() {
		return isViewGrid;
	}

	/**
	 * @param isViewGrid the isViewGrid to set
	 */
	public void setViewGrid(boolean isViewGrid) 
	{
		// afficher la grille
		this.isViewGrid = isViewGrid;
		// repaint
		this.repaint();
	}

	public void createGrid() throws IOException, TextureCreationException
	{
		grid = new Grid((int)this.shape.getSize().x,(int)this.shape.getSize().y);
		
	}
	
	public void setSizeOfMap(int width,int height)
	{
		this.widthMap = width;
		this.heightMap = height;
	}

	public void ZoomIn() 
	{
		// TODO Auto-generated method stub
		View v = (View) render.getView();
		v.zoom(0.5f);
		render.setView(v);
		this.repaint();	
	}



	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		// transformation en coordonnée du world
		
	}
	

	public void mouseDragged(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Vector2i posPanel = new Vector2i(e.getX(),e.getY());
		Vector2f posWorld = render.mapPixelToCoords(posPanel);
		
		int modifier = e.getModifiers();
		int button1Mask = (modifier & InputEvent.BUTTON1_MASK);
		int button3Mask = (modifier & InputEvent.BUTTON3_MASK);
		
			if(this.isSnapGrid)
			{
				// Si on a activé le snap grid
				
				int x = (int) (posWorld.x / this.size) * this.size;
				int y = (int) (posWorld.y / this.size) * this.size;
				posWorld = new Vector2f(x,y);
					
			}
		
			if(button1Mask == InputEvent.BUTTON1_MASK)
			{
			
				if(this.isManagerObstacle)
				{
					// drag dans la mode obstacle
					if(Manager.getObstaclesManager().getCurrentObstacle() != null)
					{
						Manager.getObstaclesManager().getCurrentObstacle().dragPoint(posWorld);
						//this.repaint();
					}
				}
				else
				{
					// drag dans le mode calque
					if(CalquesManager.getCurrentCalque() != null)
						CalquesManager.getCurrentCalque().mousePosition(Vector2f.sub(posWorld, posDiff));
				}
		
			}
			
			// glissview
			if(button3Mask == InputEvent.BUTTON3_MASK)
			{
				
				Vector2f diff = Vector2f.sub(posWorld, diffGlissView);
				v.setCenter(diff);
			}
		
		
		// 
		this.repaint();
		
	}



	@Override
	public void mouseMoved(MouseEvent e) 
	{
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
		// clic droit
		Vector2i posPanel = new Vector2i(e.getX(),e.getY());
		Vector2f posWorld = render.mapPixelToCoords(posPanel);
		
		if(this.isSnapGrid)
		{
			// Si on a activé le snap grid
			
			int x = (int) (posWorld.x / this.size) * this.size;
			int y = (int) (posWorld.y / this.size) * this.size;
			posWorld = new Vector2f(x,y);
				
		}
		
		if(this.isManagerObstacle)
		{
			if(e.getButton() == MouseEvent.BUTTON1)
			{
				
				
				// il s'agit de la gestion des obstacles
				if(Manager.getObstaclesManager().getCurrentObstacle() == null)
				{
					Obstacle oo = new Obstacle("super");
					// il s'agit d'un nouvelle obstacle
					Manager.getObstaclesManager().insertObstacle(oo);
					// c'est le premier clic, on va positionner le premier point
					Manager.getObstaclesManager().getCurrentObstacle().insertPoint(posWorld);
				}
				else
				{
					// on insère le point dans un obstacle déja instancié
					Manager.getObstaclesManager().getCurrentObstacle().insertPoint(posWorld);
				}
			}
			
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				if(Manager.getObstaclesManager().getCurrentObstacle() != null)
				{
					// on selectionne un point d'un obstacle si on est dessus
					Manager.getObstaclesManager().getCurrentObstacle().hitPoint(posWorld);
				}
			}
			
			if(e.getButton() == MouseEvent.BUTTON2)
			{
				// on utilise le clic de la molette pour fixer l'objet obstacle
				Manager.getObstaclesManager().fixObstacle();
			}
			
			this.repaint();
			return;
		}
		
				
		if(e.getButton() == MouseEvent.BUTTON2)
		{
			boolean isOneCalqueSelected = false;
			
			for(Calque c : CalquesManager.getListCalques())
			{   

					isOneCalqueSelected = c.selected(posWorld);
					
					if(isOneCalqueSelected)
					{
						// on spécifie a l'ancien calque selectionné qu'il ne l'est plus
						if(CalquesManager.getCurrentCalque() != null)
							CalquesManager.getCurrentCalque().setSelected(false);
						// on spécifie au nouveau calque selectionné qu'il l'est
						c.setSelected(true);
						// on spécifie au manager le nouveau calque sélectionné
						CalquesManager.setCurrentCalque(c);
						//CalquesManager.getCurrentCalque().setSelected(true);
						//PropertiesPanel.setCalque(CalquesManager.getCurrentCalque());
						this.repaintCalques();
						
						// break;7
						break;
		
					}
				
			
			}
			
			if(!isOneCalqueSelected)
			{
				// si aucun selectionné, on deselectionne tout
				if(CalquesManager.getCurrentCalque() != null)
				{
					CalquesManager.getCurrentCalque().setSelected(false);
					CalquesManager.setCurrentCalque(null);
				}
			}
					
			
		}
		
		// clic gauche
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			posStart = posWorld;
			// on calque le vecteur diff entre le posStart et la position du calque
			if(CalquesManager.getCurrentCalque() != null)
			{
				// valeur a soustraire sur le calque lors du déplacement
				posDiff = Vector2f.sub(posStart, CalquesManager.getCurrentCalque().getSprite().getPosition());
				// valeur modifié pour calquer sur la grille
				if(this.isSnapGrid)
				{
					int x = (int) (posDiff.x / this.size) * this.size;
					int y = (int) (posDiff.y / this.size) * this.size;
					posDiff = new Vector2f(x,y);
				}
			}
		}
		
		// glissView
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			// initialisation du diffglissview
			diffGlissView = Vector2f.sub(posWorld,v.getCenter());
		}
		
		// reaffichage
		this.repaint();
	}


	public void mouseReleased(MouseEvent e) 
	{
		
		
	}

	public static void repaintCalques()
	{
		parent.repaint();
	}


	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentResized(ComponentEvent e) 
	{
		// TODO Auto-generated method stub
		try 
		{
			render.create(this.getSize().width, this.getSize().height);
			
			// positionnement du text
			this.text.setPosition(32, 32);
			this.text.setCharacterSize(16);
			this.text.setColor(org.jsfml.graphics.Color.BLUE);
			
			this.repaint();
			
		} catch (TextureCreationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}



	@Override
	public void componentShown(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		// TODO Auto-generated method stub
		float x,y;
		Vector2f currentPositionView = v.getCenter();
		x = currentPositionView.x / this.size;
		y = currentPositionView.y / this.size;
		
		if(e.getSource() == scrollBarHorizontale)
		{
			x = scrollBarHorizontale.getValue();
		}
		
		if(e.getSource() == scrollBarVerticale)
		{
			y = scrollBarVerticale.getValue();
			
		}
		
		v.setCenter(new Vector2f(x * this.size,y * this.size));
		repaint();
	}



	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		// TODO Auto-generageted method stub
	
		int ret = e.getWheelRotation();
		
		// ajout au levelZoom
		this.levelZoom += ret;
		this.text.setString("Zoom");
		
		
		
		if(ret > 0)
		{
			// on aggrandit
			zoom = 2f;
			//this.scrollBarHorizontale.setMaximum(this.scrollBarHorizontale.getMaximum() / 2);
			//this.scrollBarVerticale.setMaximum(this.scrollBarVerticale.getMaximum() / 2);
			
		}
		
		if(ret < 0)
		{
			// on diminue
			zoom = 0.5f;
			//this.scrollBarHorizontale.setMaximum(this.scrollBarHorizontale.getMaximum() * 2);
			//this.scrollBarVerticale.setMaximum(this.scrollBarVerticale.getMaximum() * 2);
			
		}
		v.zoom(zoom);
		this.repaint();
	}



	@Override
	public void keyPressed(KeyEvent arg0) 
	{

		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
	
		
	}



	@Override
	public void updateObstacleMVC(List<Obstacle> list) 
	{
		// TODO Auto-generated method stub
		parent.repaintCalques();
		
	}



	@Override
	public void updateCalqueMVC(List<Calque> list)
	{
		// TODO Auto-generated method stub
		parent.repaintCalques();
	}



	
}
