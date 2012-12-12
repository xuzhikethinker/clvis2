package vis.info;


import org.apache.commons.collections15.Transformer;

import data.Edge;

public class EdgeLabeler implements Transformer<String, String> {

	@Override
	public String transform(String id) {
		return Edge.getLabel(id);
	}
}