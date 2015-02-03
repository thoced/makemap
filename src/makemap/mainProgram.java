package makemap;

import java.awt.Color;
import java.awt.Dialog;

import CorePanelCenter.panelCenter;
import CorePanelInfo.panelInfo;
import CorePanelViewer.panelViewer;
import CoreTexturesManager.TexturesManager;
import GUI.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JCheckBoxMenuItem;

public class mainProgram {

	private JFrame frame;
	
	private panelInfo pInfo;
	private JMenu menuFichier;
	private JMenuItem mFermer;
	private JMenuItem mProperties;
	
	private panelCenter pCenter;
	
	
	
	private JMenuItem mNouveau;
	
	// Texture Manager
	private TexturesManager texturesManager = new TexturesManager();
	/**
	 * @wbp.nonvisual location=51,-33
	 */
	private final JTable table = new JTable();
	private JMenu menuView;
	private JCheckBoxMenuItem mAffGrid;
	private JCheckBoxMenuItem mSnapGrid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{
					mainProgram window = new mainProgram();
					//window.frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
					window.frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainProgram() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1024, 768);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pInfo = new panelInfo();
		
		
		try 
		{
			pCenter = new panelCenter();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame.getContentPane().add(pInfo, BorderLayout.EAST);
		frame.getContentPane().add(pCenter,BorderLayout.CENTER);
		frame.setTitle("make map");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);
		
		mProperties = new JMenuItem("Proprietes");
		mProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				diaProperties dia = new diaProperties(frame,"Propriétés",true);
				dia.setVisible(true);
				
				// on rafraichit le recherche des textures
				pInfo.loadTextures(DataManager.directoryTextures);
			}
		});
		
		mNouveau = new JMenuItem("Nouveau");
		mNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				diaNouveau dia = new diaNouveau(frame,"Nouvelle map",true);
				dia.setVisible(true);
				
				//on créer la map
				try {
					pCenter.createMap(DataManager.widthMap, DataManager.heigthMap);
				} catch (IOException | TextureCreationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuFichier.add(mNouveau);
		menuFichier.add(mProperties);
		
		mFermer = new JMenuItem("Fermer");
		mFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		menuFichier.add(mFermer);
		
		menuView = new JMenu("View");
		menuBar.add(menuView);
		
		mAffGrid = new JCheckBoxMenuItem("Afficher la grille");
		mAffGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				// afficher la grille
				if(!pCenter.isViewGrid())
					pCenter.setViewGrid(true);
				else
					pCenter.setViewGrid(false);
			}
		});
		menuView.add(mAffGrid);
		
		mSnapGrid = new JCheckBoxMenuItem("Coller à la grille");
		mSnapGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//activation du snapgrid
				if(!pCenter.isSnapGrid())
						pCenter.setSnapGrid(true);
				else
						pCenter.setSnapGrid(false);
			}
		});
		menuView.add(mSnapGrid);
	}

}
