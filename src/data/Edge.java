package data;

public class Edge {
	/**
	 * 
	 */
	private static EdgeDataProvider data = new EdgeDataProvider();
	
	/**
	 * TODO
	 * 
	 * @param id
	 * @return
	 */
	public static String getClassID(String id) {
		return "";
	}
	
	/**
	 * TODO
	 * 
	 * @param id
	 * @return
	 */
	public static String getLabel(String id) {
		return data.getLabel(id);
	}
	

	/**
	 * TODO
	 * 
	 * @param id
	 * @return
	 */
	public static String getName(String id) {
		return data.getName(id);
	}
	
	/**
	 * TODO
	 * 
	 * @param id
	 * @return
	 */
	public static String getInfo(String id) {
		return data.getInfo(id);
	}
	
}
