package vis.renderers;


import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.apache.commons.collections15.Transformer;

public class VertexShaper implements Transformer<String,Shape> {
	
	public static final int SIZE_CONST = 0;
	public static final int SIZE_LOG = 1;
	public static final int SIZE_LINEAR = 2;
	
	public static int WIDTH = 10;
	public static int HEIGHT = 10;
	
	public static int mode = 0;
	
	@Override
	public Shape transform(String v){
        Ellipse2D circle;
        double mult = 1; 
        switch( mode ) {
	        case SIZE_CONST : 
	        	mult = 1;
	        break;
//	        case SIZE_LOG : 
//	        	mult = (tn.getLevel() >=1 ) ? 1/Math.log(1+tn.getLevel()) : 1;
//	        break;
//	        case SIZE_LINEAR : 
//	        	mult = Math.log(2+tn.getEdges().size());
//	        break;
	        default : 
	        	mult = 1;
	        break;
        }
        circle = new Ellipse2D.Double(-mult*WIDTH/2, -mult*HEIGHT/2, mult*WIDTH, mult*HEIGHT);
        
        return circle;
    }
}
