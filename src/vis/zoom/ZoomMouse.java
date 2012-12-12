package vis.zoom;

import java.awt.event.InputEvent;

import vis.Visualizer;
import edu.uci.ics.jung.visualization.control.AnimatedPickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.RotatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ShearingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;

public class ZoomMouse extends DefaultModalGraphMouse<String, String> {
    /**
	 * Override plug-ins 
	 */
	@Override
    protected void loadPlugins() {
        pickingPlugin = new PickingGraphMousePlugin<String, String>();
        animatedPickingPlugin = new AnimatedPickingGraphMousePlugin<String, String>();
        translatingPlugin = new TranslatingGraphMousePlugin(InputEvent.BUTTON1_MASK);
//        TODO
        scalingPlugin = new ScalingGraphMousePlugin(Visualizer.scaler, 0, in, out);
        rotatingPlugin = new RotatingGraphMousePlugin();
        shearingPlugin = new ShearingGraphMousePlugin();

        add(scalingPlugin);
        setMode(Mode.TRANSFORMING);
    }
}
