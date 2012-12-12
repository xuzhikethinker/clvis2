package vis;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.MixedRandomGraphGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class RandomGraph {
	private static Factory<Graph<String,String>> graphFactory =
		new Factory<Graph<String,String>>() {
		public Graph<String,String> create() {
			return new SparseMultigraph<String,String>();
		}
	};

	private static Factory<String> vertexFactory = new Factory<String>() {
		int count;
		public String create() {
			count++;
			return count + "";
		}};
	public static Factory<String> edgeFactory = new Factory<String>() {
		int count;
		public String create() {
			count++;
			return count + "";
		}};
		
	public static Graph<String,String> generate(int n) {
		return MixedRandomGraphGenerator.generateMixedRandomGraph(
			graphFactory,	vertexFactory, edgeFactory, 
			new HashMap<String,Number>(), n, true, new HashSet<String>());
	}
}
