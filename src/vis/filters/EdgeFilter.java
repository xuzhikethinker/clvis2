package vis.filters;

import vis.Visualizer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.picking.PickedState;

public class EdgeFilter extends GraphFilter {
	/**
	 * 
	 * @param enabled
	 */
	public EdgeFilter(boolean enabled) {
		super(enabled);
	}
	
	/**
	 * 
	 * @param enabled
	 * @param hideBackReferences
	 */
	public EdgeFilter(boolean enabled, boolean hideBackReferences ) {
		super(enabled);
		hideBackReferences(hideBackReferences);
	}
	
	protected PickedState<String> pickedState = null;
	
	public void setPickedState(PickedState<String> pickedState) {
		this.pickedState = pickedState;
	}
	
	/**
	 * 
	 */
	protected boolean hideBackReferences = true;
	
	/**
	 * 
	 * @param hideBackReferences
	 */
	public void hideBackReferences( boolean hideBackReferences ) {
		this.hideBackReferences = hideBackReferences;
	} 
	
	/**
	 * 
	 */
	public boolean evaluate(Context<Graph<String, String>, String> context) {
		Graph<String, String> graph = context.graph;
		String e = context.element;
	    Pair<String> pair = graph.getEndpoints(e);
		
	    if (!isEnabled()) {
			return true;
		}
//	    Hide if disabled
	    if (!filter.status(e)) {
	    	return false;
	    }
//	    Show if not hidden backrefs
	    if (!hideBackReferences) {
	    	return true;
	    }
//		Else show picked vertice's 
	    if (this.pickedState != null) {
	    	if (pickedState.isPicked(pair.getFirst()) || pickedState.isPicked(pair.getSecond())) {
	    		return true;
	    	}
	    }
//	    Else hide direct edges
//	    if( 
//	    	!pair.getSecond().hasParent(pair.getFirst()) &&
//	    	!pair.getFirst().hasParent(pair.getSecond())
//	    ) {
//	    	return false;
//	    }
	    	    
	    return true;
	}

}
