package vis.zoom;

import java.awt.geom.Point2D;

import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;

public class Zoom extends CrossoverScalingControl {
	private float zoom = 1;
	public float getZoom() {
		return zoom;
	} 	
	public void scale(VisualizationServer<?,?> vv, float amount, Point2D at) {
		zoom *= amount;
		super.scale(vv,amount,at);
	}
}
