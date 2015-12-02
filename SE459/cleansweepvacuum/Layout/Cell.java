package Layout;

// TODO : write some tests for this crap
public class Cell {

	public static final String[] CELL_TYPE_NAMES = {
			"Bare Floor",       // 0
			"Low-pile Carpet",  // 1
			"High-pile Carpet", // 2
			"Stairs Cell",      // 3
	};

	private final String[] BORDER_TYPE_NAMES = {
			"Clear",         // 0
			"Wall",          // 1
			"Door (open)",   // 2
			"Door (closed)", // 3
			"Stairs Border", // 4
	};
	
	private int x;
	private int y;
	private int type;
	private int dirt;
	private int north_border;
	private int south_border;
	private int east_border;
	private int west_border;
	private int chargingStation;

	//private String up, down, left, right, dirty;

	private int[] default_borders = {0,0,0,0};

	public Cell(int x, int y){
		setX(x);
		setY(y);
	}

	public Cell(int x, int y, int type){
		setX(x);
		setY(y);
		setType(type);
	}

	public Cell(int x, int y, int type, int north, int east, int south, int west, int dirt){
		setX(x);
		setY(y);
		setType(type);
		setNorth(north);
		setEast(east);
		setSouth(south);
		setWest(west);
		setDirt(dirt);
	}

	public Cell(int x, int y, int type, int north,  int east, int south, int west, int dirt, int chargingStation){
		setX(x);
		setY(y);
		setType(type);
		setNorth(north);
		setEast(east);
		setSouth(south);
		setWest(west);
		setDirt(dirt);
		setChargingStation(chargingStation);
	}

	/*public Cell(int x, int y, int type, String up, String down, String left, String right, String dirty){
		setX(x);
		setY(y);
		setType(type);
		setUp(down);
		setDown(down);
		setLeft(left);
		setRight(right);
		setDirty(dirty);
	}*/


	private void setX(int foo){
		if(foo < 0){
			throw new IllegalArgumentException("X value can not be less than zero");
		}
		this.x = foo;
	}

	private void setY(int bar){
		if(bar < 0){
			throw new IllegalArgumentException("Y value can not be less than zero");
		}
		this.y = bar;
	}

	private void setType(int t){
		if(t < 0 || t > CELL_TYPE_NAMES.length){
			throw new IllegalArgumentException("The type can not be less than zero or more than that are defined");
		}
		this.type = t;
	}

	private void setNorth(int n){
		if ( n < 0 || n > BORDER_TYPE_NAMES.length){
			throw new IllegalArgumentException("The border type can not be less than zero or more than are defined");
		}
		this.north_border = n;
	}
	private void setSouth(int s){
		if ( s < 0 || s > BORDER_TYPE_NAMES.length){
			throw new IllegalArgumentException("The border type can not be less than zero or more than are defined");
		}
		this.south_border = s;
	}
	private void setEast(int e){
		if ( e < 0 || e > BORDER_TYPE_NAMES.length){
			throw new IllegalArgumentException("The border type can not be less than zero or more than are defined");
		}
		this.east_border = e;
	}
	private void setWest(int w){
		if ( w < 0 || w > BORDER_TYPE_NAMES.length){
			throw new IllegalArgumentException("The border type can not be less than zero or more than are defined");
		}
		this.west_border = w;
	}

	private void setDirt(int d){
		if ( d >= 0 ){
			this.dirt = d;
		} else {
			throw new IllegalArgumentException("The cell can not have less than zero dirt");
		}
	}

	private void setChargingStation(int c){
		if ( c >= 0 ){
			this.chargingStation = c;
		} else {
			throw new IllegalArgumentException("The cell can not have less than zero for value of chargingStation");
		}
	}


	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}

	public int getType(){
		return this.type;
	}
	public String getTypeName(){
		return CELL_TYPE_NAMES[this.type];
	}

	public int getNorth(){
		return this.north_border;
	}
	public String getNorthName(){
		return this.BORDER_TYPE_NAMES[this.north_border];
	}
	public int getSouth(){
		return this.south_border;
	}
	public String getSouthName(){
		return this.BORDER_TYPE_NAMES[this.south_border];
	}
	public int getEast(){
		return this.east_border;
	}
	public String getEastName(){
		return this.BORDER_TYPE_NAMES[this.east_border];
	}
	public int getWest(){
		return this.west_border;
	}
	public String getWestName(){
		return this.BORDER_TYPE_NAMES[this.west_border];
	}


	/* Michael code
	private void setUp(String t){
		this.up = t;
	}
	private void setDown(String t){
		this.down = t;
	}
	private void setLeft(String t){
		this.left = t;
	}
	private void setRight(String t){
		this.right = t;
	}
	private void setDirty(String t){
		this.dirty = t;
	}*/

}
