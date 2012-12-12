package vis.mouse;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import vis.Visualizer;
import edu.uci.ics.jung.visualization.picking.MultiPickedState;

public class EdgeStateListener implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		@SuppressWarnings("rawtypes")
		Object[] clicked = ((MultiPickedState)arg0.getSource()).getSelectedObjects();
		for( Object obj : clicked ) {
			Visualizer.edgeStringer.put((String)obj);
		}
	}

}