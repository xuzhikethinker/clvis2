package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import vis.Visualizer;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;

public class App extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9137520946567692984L;
	
	/**
	 * Side Bar
	 */
	public final JPanel side = new JPanel();
	 
	 /**
	  * Content Panel
	  */
	 public final JPanel cont = new JPanel();

	 /**
	  * Screen Size
	  */
	 public static int WIDTH = 1200;
	 public static int HEIGHT = 600;
	 
	 /**
	  * Buttons
	  */
	 private Dimension buttonDim = new Dimension(48,48);
	 public JButton scale_inc = new JButton("+");
	 public JButton scale_dec = new JButton("-");
//	 public JButton scale_inc = new JButton( new ImageIcon("app/images/+.png") );
//	 public JButton scale_dec = new JButton( new ImageIcon("app/images/-.png") );
//	 public JButton refresh = new JButton( new ImageIcon("refresh.png") );
//	 public JButton analysis = new JButton( "Anallyze" );
		
	/**
	 * Graph container 
	 */
	public GraphZoomScrollPane gzp;
	 
	/**
	 * Entrance point
	 */
	public void init() {
		Container pane = getContentPane();	
		
//		Look and feel
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch(Exception e) {
			e.printStackTrace();
		};
		
//		Recalculate screen size
		if (getParameter("width") != null) {
			if (getParameter("width").equals("100%")) {
				Toolkit toolkit =  Toolkit.getDefaultToolkit ();
				Dimension dim = toolkit.getScreenSize();
				WIDTH = (int)dim.getWidth();
			} else {
				WIDTH = Integer.parseInt(getParameter("width"));
			}
		}
		if (getParameter("height") != null) {
			HEIGHT = Integer.parseInt(getParameter("height"));
		}
		
//		Add Side Bar
		side.setPreferredSize(new Dimension(60,HEIGHT));
		side.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.GRAY));
		pane.add(side, BorderLayout.LINE_START);
		
//		Add Content Panel
		pane.add(cont, BorderLayout.CENTER);

//		Set buttons sizes
		scale_inc.setPreferredSize(buttonDim);
		scale_dec.setPreferredSize(buttonDim);
		
//		Add buttons
		side.add(scale_inc);
		side.add(scale_dec);		
		
//		Set context
		if (getParameter("context") != null) {
//			Configuration.context = getParameter("context"); 
		}
		
//		Set menubar
		setJMenuBar(new MenuBar());
		
//		Init visualizer
		Visualizer.init(this);
		
//		Create Graph Model
		gzp = new GraphZoomScrollPane(Visualizer.getVV());
		final GraphZoomScrollPane panel = gzp;
        panel.setPreferredSize(getDimension());
        cont.add(panel);

	}
		
	public static Dimension getDimension() {
		return new Dimension(WIDTH-60,HEIGHT-5);
	}
	
}
