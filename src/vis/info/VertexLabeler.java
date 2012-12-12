package vis.info;


import org.apache.commons.collections15.Transformer;

import data.Vertex;


public class VertexLabeler implements Transformer<String,String> {

	@Override
	public String transform(String id) {
		return Vertex.getLabel(id);
	}
}