import java.util.HashMap;
import java.util.Map;

public class Grid2 {

	static Grid2 instance = new Grid2();
	Map<Integer[], Object[]> map = new HashMap<Integer[], Object[]>();
	
	private Grid2(){
		
		
	
	
		//System.out.println(test[0].obj);
	}
	
	class Cell<T extends Object>{
		
		
		Cell() {
			//this.obj = new Square(42,42);
			//System.out.println(obj.type);
		}
		
		
		
	}
		
	public static Grid2 getInstance() {
		return instance;
	}
}
