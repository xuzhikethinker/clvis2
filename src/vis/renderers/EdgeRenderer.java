package vis.renderers;

import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import vis.colorers.AnalysisColorer;
import vis.colorers.GraphColorer;
import data.Edge;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.GradientEdgePaintTransformer;

public class EdgeRenderer extends GradientEdgePaintTransformer<String,String>{
	/**
	 * 
	 */
	public GraphColorer gc;
	private boolean gradient = true;
	
	/**
	 * 
	 * @param flag
	 */
	public void setGradient( boolean flag ) {
		gradient = flag;
	}
	
	/**
	 * 
	 * @param gc
	 * @param vv
	 */
	public EdgeRenderer(VisualizationViewer<String, String> vv) {
		super(Color.black, Color.green, vv);
//		super(gc.getColor(GraphColorer.GC_BASE_COLOR2), gc.getColor(GraphColorer.GC_BASE_COLOR1), vv);
	}
	
	public void setColorer(GraphColorer gc) {
		this.gc = gc;
	}
	
	/**
	 * 
	 */
	protected Color getColor1( String e ) {
		boolean picked = vv.getPickedEdgeState().isPicked(e);
		return gc.getColor(e, Edge.getClassID(e), picked ? GraphColorer.GC_PICK_COLOR2 : GraphColorer.GC_BASE_COLOR2);
	}
	
	/**
	 * 
	 */
	protected Color getColor2( String e ) {
		boolean picked = vv.getPickedEdgeState().isPicked(e);
		return gc.getColor(picked ? GraphColorer.GC_PICK_COLOR1 : GraphColorer.GC_BASE_COLOR1);
	}
	
	/**
	 * 
	 */
	public Paint transform(String e) {
		if(gradient) {
			return super.transform(e);
		} else {
			return getColor2(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Transformer arrowFillPaintTransformer = new Transformer() {
		public Color transform(Object e) {
			return getColor2((String)e);
		}
	};
	@SuppressWarnings("rawtypes")
	public Transformer arrowDrawPaintTransformer = new Transformer() {
		public Color transform(Object e) {
			return getColor2((String)e);
		}
	};
	
	
	
}
