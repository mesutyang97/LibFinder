package Maps;

public abstract class DisplayObj {
	/* Name of display object. */
	String name;
	
	protected DisplayObj(String n){
		name = n;
	}
	
	protected String getName(){
		return name;
	}
}
