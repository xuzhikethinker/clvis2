package vis.filters;

import org.apache.commons.collections15.Predicate;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;

public class GraphFilter implements Predicate<Context<Graph<String, String>, String>> {
	/**
	 * 
	 */
	protected boolean enabled;
		
	/**
	 * 
	 * @param enabled
	 */
	public GraphFilter(boolean enabled) {
		setEnabled(enabled);
	}
	
	/**
	 * 
	 */
	public void toggle() {
		enabled = !enabled;
	}
	
	/**
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * 
	 */
	protected AbstractFilter filter = new AbstractFilter();
	
	/**
	 * 
	 * @param filter
	 */
	public void setFilter(AbstractFilter filter) {
		this.filter = filter;
	}
	
	
	@Override
	public boolean evaluate(Context<Graph<String, String>, String> context) {
		Graph<String, String> graph = context.graph;
		String v = context.element;
		
		if (!isEnabled()) {
			return true;
		}
		
		return filter.status(v);		
	}

}
