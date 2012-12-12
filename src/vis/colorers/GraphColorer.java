package vis.colorers;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class GraphColorer {
	/**
	 * Basic colors
	 */
	private Color c11 = Color.BLUE;
	private Color c12 = Color.WHITE;
	private Color c21 = Color.RED;
	private Color c22 = Color.WHITE;
	
	/**
	 * Maps for special colors
	 */
	private Map<String,Color> colors1 = new HashMap<String, Color>(); // objects
	private Map<String,Color> colors2 = new HashMap<String, Color>(); // classes
	
	public static final int GC_BASE_COLOR1 = 0;
	public static final int GC_BASE_COLOR2 = 1;
	public static final int GC_PICK_COLOR1 = 2;
	public static final int GC_PICK_COLOR2 = 3;
	
	/**
	 * 
	 * @param mode
	 * @param c
	 */
	public void setColor(int mode, Color c) {
		switch (mode) {
			case GC_BASE_COLOR1 : c11 = c; break; 
			case GC_BASE_COLOR2 : c12 = c; break; 
			case GC_PICK_COLOR1 : c21 = c; break; 
			case GC_PICK_COLOR2 : c22 = c; break; 
		}
	}
	
	
	public Color getColor(int mode) {
		switch (mode) {
			case GC_BASE_COLOR1 : return c11; 
			case GC_BASE_COLOR2 : return c12; 
			case GC_PICK_COLOR1 : return c21; 
			case GC_PICK_COLOR2 : return c22; 
		}
		return c11;
	}
	
	/**
	 * 
	 * @param id
	 * @param c
	 * @param object
	 */
	public void setColor(String id, Color c, boolean object) {
		(object ? colors1 : colors2) . put(id, c);
	}
	
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @param mode
	 * @return
	 */
	public Color getColor(String id1, String id2, int mode ) {
		if (colors1.containsKey(id1)) {
			return colors1.get(id1);
		}
		if (colors2.containsKey(id2)) {
			return colors2.get(id2);
		}
		return getColor(mode); 
	}
}
