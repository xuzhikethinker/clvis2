package vis.renderers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

import data.Vertex;

import vis.Visualizer;
import vis.colorers.GraphColorer;
import vis.colorers.VertexColorer;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

public class VertexRenderer implements Renderer.Vertex<String,String> {
	/**
	 * 
	 */
	GraphColorer gc;
	PickedState<String> pickedState;
	boolean cyclic;
	
	/**
	 * 
	 * @param gc
	 * @param pickedState
	 * @param cyclic
	 */
	public VertexRenderer(GraphColorer gc, PickedState<String> pickedState, boolean cyclic) {
		this.gc = gc;
		this.pickedState = pickedState;
		this.cyclic = cyclic;
	}
	
	/**
	 * 
	 */
	@Override
	public void paintVertex(RenderContext<String, String> rc, Layout<String, String> layout, String v) {
		Graph<String,String> graph = layout.getGraph();
        if (rc.getVertexIncludePredicate().evaluate(Context.<Graph<String,String>,String>getInstance(graph,v))) {
            boolean vertexHit = true;
            // get the shape to be rendered
            Shape shape = rc.getVertexShapeTransformer().transform(v);
            Point2D p = layout.transform(v);
            p = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p);
            float x = (float)p.getX();
            float y = (float)p.getY();
            // create a transform that translates to the location of the vertex to be rendered
            AffineTransform xform = AffineTransform.getTranslateInstance(x,y);
            // transform the vertex shape with xtransform
            shape = xform.createTransformedShape(shape);
            vertexHit = vertexHit(rc, shape);
            //rc.getViewTransformer().transform(shape).intersects(deviceRectangle);
            if (vertexHit) {
            	paintShapeForVertex(rc, v, shape);
            }
        }
	}
	
	/**
	 * 
	 * @param rc
	 * @param s
	 * @return
	 */
	protected boolean vertexHit(RenderContext<String,String> rc, Shape s) {
        JComponent vv = rc.getScreenDevice();
        Rectangle deviceRectangle = null;
        
        if(vv != null) {
            Dimension d = vv.getSize();
            deviceRectangle = new Rectangle(
            	0,0,
            	d.width,d.height);
        }
        
        return rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(s).intersects(deviceRectangle);
    }
	
	/**
	 * 
	 * @param rc
	 * @param v
	 * @param shape
	 */
	protected void paintShapeForVertex(RenderContext<String,String> rc, String v, Shape shape) {
        GraphicsDecorator g = rc.getGraphicsContext();
        Paint oldPaint = g.getPaint();
        Rectangle r = shape.getBounds();
        float y2 = (float)r.getMaxY();
        if(cyclic) {
        	y2 = (float)(r.getMinY()+r.getHeight()/2);
        }
        
        Paint fillPaint = null;
        boolean picked = pickedState != null && pickedState.isPicked(v);
        Color c1 = gc.getColor(v, Vertex.getClassID(v), picked ? GraphColorer.GC_PICK_COLOR1 : GraphColorer.GC_BASE_COLOR1);
        Color c2 = gc.getColor(picked ? GraphColorer.GC_PICK_COLOR2 : GraphColorer.GC_BASE_COLOR2);
        fillPaint = new GradientPaint(
        	(float)r.getMinX(), (float)r.getMinY(), c1, 
        	(float)r.getMinX(), y2, c2, 
        	cyclic
        );
        
        if(fillPaint != null) {
            g.setPaint(fillPaint);
            g.fill(shape);
            g.setPaint(oldPaint);
        }
        Paint drawPaint = rc.getVertexDrawPaintTransformer().transform(v);
        if(drawPaint != null) {
            g.setPaint(drawPaint);
        }
        Stroke oldStroke = g.getStroke();
        Stroke stroke = rc.getVertexStrokeTransformer().transform(v);
        if(stroke != null) {
            g.setStroke(stroke);
        }
        g.draw(shape);
        g.setPaint(oldPaint);
        g.setStroke(oldStroke);
    }

}
