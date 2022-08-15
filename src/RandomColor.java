import java.awt.Color;
import java.util.Random;

public class RandomColor {
	
	static RandomColor object = new RandomColor();
	private Color color;
	private double rY = 0.212655;
	private double gY = 0.715158;
	private double bY = 0.072187;

	
	private RandomColor(){	
		
		color = this.generate();
		
	}
	
	
	private Color generate() {
		
		Random rand = new Random();
		
		Color randomColor = new Color(rand.nextFloat(),
				   			    rand.nextFloat(),
				   			    rand.nextFloat(),
				   			    rand.nextFloat());
		
		int lolo = this.gray(randomColor.getRed(),randomColor.getGreen(),randomColor.getBlue());
		
		Color randomColorRGB= new Color(randomColor.getRed(),
										randomColor.getGreen(), 
										randomColor.getBlue(), 
										lolo);
		
		return randomColorRGB;
		
	}
	
	
	public Color getComplimentary() {
				
		
		Color currentColor = this.getCurrent();
		int R = currentColor.getRed();
		int G = currentColor.getGreen();
		int B = currentColor.getBlue();
		int A = currentColor.getAlpha();
		int max = Math.max(R, Math.max(G, B));
		int min = Math.min(R, Math.min(G, B));
		R = max + min - R;   
		G = max + min - G;
		B = max + min - B;
		Color complementaryColor = new Color(R,G,B,A);
		return complementaryColor;
		
		
	}
	
	
	// Inverse of sRGB "gamma" function. (approx 2.2): Not written by Brian de Q.
	public double inv_gam_sRGB(int ic) {
		
	    double c = ic/255.0;
	    
	    if ( c <= 0.04045 )
	    	
	        return c/12.92;
	    
	    else 
	    	
	        return Math.pow(((c+0.055)/(1.055)),2.4);
	    
	}

	
	// sRGB "gamma" function (approx 2.2): Not written by Brian de Q.
	public int gam_sRGB(double v) {
		
	    if(v<=0.0031308)
	    	
	      v *= 12.92;
	    
	    else 
	    	
	      v = 1.055*Math.pow(v,1.0/2.4)-0.055;
	    
	    return (int) (v*255.0+0.5); 
	    
	}
	
	
	// GRAY VALUE ("brightness"): Not written by Brian de Q.
	public int gray(int r, int g, int b) {
		
	    return gam_sRGB(
	            rY*inv_gam_sRGB(r) +
	            gY*inv_gam_sRGB(g) +
	            bY*inv_gam_sRGB(b)
	    );
	    
	}
	
	
	public Color getNext() {
		
		color = this.generate();
		return color;
		
	}
	
	public Color getCurrent() {
		
		return color;
		
	}
	
	public static RandomColor getInstance() {
		
		return object;
		
	}
	
}
