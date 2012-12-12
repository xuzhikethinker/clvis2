package vis.info;

import java.util.ArrayList;

import org.apache.commons.collections15.Transformer;

import data.Edge;

public class EdgeStringer implements Transformer<String,String> {
	/**
	 * 
	 */
	ArrayList<String> map = new ArrayList<String>();
	
	/**
	 * 
	 */
	@Override
	public String transform(String id) {
		if( !map.contains(id) ) {
			return "";
		} else {
			return Edge.getName(id);
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	public void put( String id ) {
		if( map.contains(id) ) {
			map.remove(id);
		} else {
			map.add(id);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param state
	 */
	public void put( String id, boolean state ) {
		if( map.contains(id) ) {
			if(!state) map.remove(id);
		} else {
			if(state) map.add(id);
		}
	}
}
