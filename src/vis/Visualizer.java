package vis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.App;
import main.MenuBar;
import vis.colorers.EdgeColorer;
import vis.colorers.VertexColorer;
import vis.filters.EdgeFilter;
import vis.filters.VertexFilter;
import vis.info.EdgeLabeler;
import vis.info.EdgeStringer;
import vis.info.VertexLabeler;
import vis.info.VertexStringer;
import vis.mouse.EdgeStateListener;
import vis.mouse.VertexStateListener;
import vis.mouse.VisualizerMouseListener;
import vis.renderers.EdgeRenderer;
import vis.renderers.VertexRenderer;
import vis.renderers.VertexShaper;
import vis.zoom.Zoom;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.util.Animator;

public class Visualizer {

	/**
	 * 
	 */
	private static Graph<String, String> graph = RandomGraph.generate(20);
	private static VisualizationViewer<String, String> vv;

	/**
	 * Zooming
	 */
	public static final Zoom scaler = new Zoom();
//	private static final ZoomMouse graphMouse = new ZoomMouse(scaler);
	private static final DefaultModalGraphMouse<String, String> graphMouse = new DefaultModalGraphMouse<String, String>();	
	
	/**
	 * Filters
	 */
	public static VertexFilter vertexFilter = new VertexFilter(true);
	public static EdgeFilter edgeFilter = new EdgeFilter(true, true);
	
	/**
	 * Shapers
	 */
	public static VertexShaper vertexShaper = new VertexShaper();
	
	/**
	 * Colorers and renderers
	 */
	public static EdgeRenderer edgeDrawPaint;
	
	/**
	 * Stringers
	 */
	public static VertexStringer vertexStringer = new VertexStringer();
	public static EdgeStringer edgeStringer = new EdgeStringer();
	
	/**
	 * 
	 */
	public static VisualizationViewer<String, String> getVV() {
		return vv;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void init(App app) {
		graph = RandomGraph.generate(20);
		vv = new VisualizationViewer<String, String>(new FRLayout(graph));
		
//		Add menubar algorithm buttons
		menubar(((MenuBar)app.getJMenuBar()).menu2);
		
//		Apply zooming and mouse
		vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());
        
//      Apply filters
		vv.getRenderContext().setVertexIncludePredicate(vertexFilter);
		vv.getRenderContext().setEdgeIncludePredicate(edgeFilter);
        edgeFilter.setPickedState(vv.getPickedVertexState());
        
//      Apply edge size
		vv.getRenderContext().setVertexShapeTransformer(vertexShaper);
		
//		Renderers
        vv.getRenderer().setVertexRenderer( new VertexRenderer(
        	new VertexColorer(), vv.getPickedVertexState(), false
        ));
        edgeDrawPaint = new EdgeRenderer(vv);
        edgeDrawPaint.setColorer(new EdgeColorer());
		vv.getRenderContext().setEdgeDrawPaintTransformer( edgeDrawPaint );
		vv.getRenderContext().setArrowFillPaintTransformer(edgeDrawPaint.arrowFillPaintTransformer);
        vv.getRenderContext().setArrowDrawPaintTransformer(edgeDrawPaint.arrowDrawPaintTransformer);
        vv.getRenderContext().getEdgeLabelRenderer().setRotateEdgeLabels(false);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
        
//      Labels 
        vv.setVertexToolTipTransformer(new VertexLabeler());
        vv.setEdgeToolTipTransformer(new EdgeLabeler());
        vv.getRenderContext().setVertexLabelTransformer(vertexStringer);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);
        vv.getRenderContext().setEdgeLabelTransformer(edgeStringer);
        
//      Info
        vv.getPickedVertexState().addItemListener(new VertexStateListener());
        vv.getPickedEdgeState().addItemListener(new EdgeStateListener());
        vv.addGraphMouseListener(new VisualizerMouseListener());
	}
	
	/**
	 * 
	 */
	private static void menubar(JMenu menu) {
		ButtonGroup menu_bg = new ButtonGroup();
//		Available layouts
		@SuppressWarnings("rawtypes")
		ArrayList<Class> layouts = new ArrayList<Class>();
		layouts.add(FRLayout.class);
		layouts.add(KKLayout.class);
        layouts.add(CircleLayout.class);
        layouts.add(SpringLayout.class);
        layouts.add(SpringLayout2.class);
        layouts.add(ISOMLayout.class);
//		Add elements to menu
		int i=0;
		for (@SuppressWarnings("rawtypes") final Class _class : layouts) {
			JCheckBoxMenuItem menu_item = new JCheckBoxMenuItem(_class.getSimpleName());
			menu_bg.add(menu_item);
			menu.add(menu_item);
			if (i==0) {
				menu_item.setSelected(true);
			}
			i++;
			menu_item.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent arg0) {
					// rerun visualization
					try {
						changeLayout(_class);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}	
		menu.addSeparator();
		JMenuItem menu_item = new JMenuItem("reset layout");
		menu.add(menu_item);
		menu_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TODO make it smoothly
				Layout<String, String> layout = vv.getGraphLayout();
				layout.initialize();
				Relaxer relaxer = vv.getModel().getRelaxer();
				if(relaxer != null) {
					relaxer.stop();
					relaxer.prerelax();
					relaxer.relax();
				}
			}});
		
	}
	
	private static void changeLayout(Class<? extends Layout<String,String>> layout) {
		try
        {
            Constructor<? extends Layout<String,String>> constructor = 
            	layout.getConstructor(new Class[] {Graph.class});
            Object o = constructor.newInstance(graph);
            @SuppressWarnings("unchecked")
			Layout<String,String> l = (Layout<String,String>) o;
            l.setInitializer(vv.getGraphLayout());
            l.setSize(vv.getSize());
            
			LayoutTransition<String,String> lt =
				new LayoutTransition<String,String>(vv, vv.getGraphLayout(), l);
			Animator animator = new Animator(lt);
			animator.start();
			vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
			vv.repaint(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
