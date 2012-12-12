package vis.mouse;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import vis.Visualizer;
import data.Vertex;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;

public class VisualizerMouseListener implements GraphMouseListener<String>  {
	
	@Override
	public void graphClicked(String v, MouseEvent e) {
		if( e.isControlDown() ) {
			// put the vertex to center 
		} else if( e.isShiftDown() ) {
			// repaint with node in center
		} else if( e.getClickCount() > 1 ) {
			Visualizer.vertexStringer.put(v, false);
			JOptionPane.showMessageDialog(null, Vertex.getInfo(v));
		}		
	}

	@Override
	public void graphPressed(String arg0, MouseEvent arg1) {
		// 
	}

	@Override
	public void graphReleased(String arg0, MouseEvent arg1) {
		//
	}

}
