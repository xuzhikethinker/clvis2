package vis.filters;

import java.util.HashMap;

public class AbstractFilter {
	
	/**
	 * 
	 */
	private HashMap<String, AbstractState> map = new HashMap<String, AbstractState>();
	
	/**
	 * 
	 * @param id
	 * @param state
	 */
	public void put(String id, AbstractState state) {
		map.put(id, state);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AbstractState get(String id) {
		return map.containsKey(id) ? map.get(id) : AbstractState.SHOW;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void del(String id) {
		map.remove(id);
	}
	
	/**
	 * 
	 */
	public void clean() {
		map = new HashMap<String, AbstractState>();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean status(String id) {
		return get(id).isShown();
	}
	
}
