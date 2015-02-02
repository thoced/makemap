package CorePanelCenter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import makemap.DataManager;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class panelCenter extends JPanel implements MouseWheelListener,MouseListener,MouseMotionListener,ComponentListener,AdjustmentListener
{
	/**
	 * 
	 */
	// render texture jsfml
	private RenderTexture render;
	// 
	private View v;
	private float zoom = 1f;
	private Sprite sprite;
	private JScrollBar scrollBarHorizontale;
	private JScrollBar scrollBarVerticale;
	
	// taille de la map
	private int widthMap,heightMap;
	
	// private 
	private static panelCenter parent;
	
	// private list des calques
	private List<Calque> listCalques = new ArrayList<Calque>();

	public panelCenter() throws IOException, TextureCreationException
	{
		
		
		this.setBackground(Color.GRAY);
		// layout
		
		// ajout dans le datamanager de la réference this
		DataManager.panelCenter = this;
		
		
		render= new RenderTexture();
		
		try {
			render.create(800, 600);
			
		} catch (TextureCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
		
		v = new View();
		v.zoom(zoom);
		v.setSize(render.getSize().x,render.getSize().y);
		v.setCenter(new Vector2f(0,0));
	
				

		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addComponentListener(this);
		this.addMouseWheelListener(this);
		
		setLayout(new BorderLayout(0, 0));
		
		scrollBarVerticale = new JScrollBar();
		scrollBarVerticale.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
			}
		});
		
		add(scrollBarVerticale, BorderLayout.WEST);
		
		scrollBarHorizontale = new JScrollBar();
		scrollBarHorizontale.setOrientation(JScrollBar.HORIZONTAL);
		add(scrollBarHorizontale, BorderLayout.SOUTH);
		
	
		scrollBarHorizontale.addAdjustmentListener(this);
		scrollBarVerticale.addAdjustmentListener(this);
		
		parent = this;
	}
	

	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		render.clear(org.jsfml.graphics.Color.TRANSPARENT);
		render.setView(v);
		
		for(Calque calque : this.listCalques)
		{
			render.draw(calque.getSprite());
		}
		render.display();
		
		Texture mytexture = (Texture) render.getTexture();
		BufferedImage bi = mytexture.copyToImage().toBufferedImage();
		g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
	
		//this.setDoubleBuffered(true);
	}
	
	public static void insertCalque(Calque calque)
	{
		// ajout du calque
		parent.listCalques.add(calque);
		// affichage
		parent.repaint();
	}
	
	public  void createMap(int width,int height)
	{
		// taille de l'image
		this.widthMap = width;
		this.heightMap = height;
		
		this.scrollBarHorizontale.setMaximum(this.widthMap);
		this.scrollBarVerticale.setMaximum(this.heightMap);
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
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Vector2i posPanel = new Vector2i(e.getX(),e.getY());
		Vector2f posWorld = render.mapPixelToCoords(posPanel);
				
		for(Calque calque : this.listCalques)
		{
			calque.mousePosition(posWorld);
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
		Vector2i posPanel = new Vector2i(e.getX(),e.getY());
		Vector2f posWorld = render.mapPixelToCoords(posPanel);
		
		for(Calque calque : this.listCalques)
		{
			calque.selected(posWorld);
		}
		
		// reaffichage
		this.repaint();
	}


	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		for(Calque calque : this.listCalques)
		{
			calque.setSelected(false);
		}
		
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
		x = currentPositionView.x;
		y = currentPositionView.y;
		
		if(e.getSource() == scrollBarHorizontale)
		{
			x = e.getValue();
		}
		
		if(e.getSource() == scrollBarVerticale)
		{
			y = e.getValue();
			
		}
		
		v.setCenter(new Vector2f(x * 32,y * 32));
		repaint();
	}



	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		// TODO Auto-generageted method stub
	
		int ret = e.getWheelRotation();
		System.out.println(ret);
		
		if(ret > 0)
			zoom = 2f;
		
		
		if(ret < 0)
			zoom = 0.5f;
		
		v.zoom(zoom);
		this.repaint();
	}



	
}
