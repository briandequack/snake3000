
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class Controls extends JFrame {

	static Controls object = new Controls();
	Map<Integer, String> keys = new HashMap<Integer, String>();
	Integer[] activeKeys;

	private Controls() {
	
		keys.put(38, "up");
		keys.put(40, "down");
		keys.put(37, "left" );
		keys.put(39, "right");	
		activeKeys = new Integer[keys.size()];
		
	}
		
	public String methodNames(Integer keyCode) {
	  for (Map.Entry<Integer, String> entry : keys.entrySet()) {
		  if(entry.getKey()==keyCode) {
			this.setActive(keyCode);
	        return entry.getValue();   
		  }
	  }
	  return null;
	}
	
	public boolean activeKeys(Integer keyCode) {
		for (int i = 0; i < activeKeys.length; i++) {
			if(activeKeys[i]==keyCode) {
				return true;
			}
		}
		return false;
	}
	
	
	public void setActive(Integer keyCode) {
		for (int i = 0; i < activeKeys.length; i++) {
			if(activeKeys[i]==null) {
				activeKeys[i]=keyCode;
				break;
			}
		}
	}
	
	
	public void setInactive(int keyCode) {
		for (int i = 0; i < activeKeys.length; i++) {
			if(activeKeys[i]==keyCode) {
				activeKeys[i]=null;
				break;
			}
		}
	}
	

	public static Controls getInstance() {
		return object;
	}
	
}
