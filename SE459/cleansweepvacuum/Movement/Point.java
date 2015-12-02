package Movement;

//Point class holds the x,y values of a point 
public class Point {
	private int x_Value;
	private int y_Value;
	
	public Point (int x, int y){
		setX(x);
		setY(y);
	}
	
	public void setX(int x){
		x_Value = x;
	}
	
	public void setY(int y){
		y_Value = y;
	}
	
	public int getX() {
		return x_Value;
	}
	
	public int getY() {
		return y_Value;
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 17 * hash + this.x_Value;
		hash = 17 * hash + this.y_Value;
		return hash;
	}
	
	//class to handle the equals of 2 objects vs using  "="
	@Override 
	public boolean equals(Object o){
		if( o == null){
			return false;
		}
		if (getClass() != o.getClass()){
			return false;
		}
		
		final Point other = (Point) o;
		if(this.x_Value != other.x_Value) {
			return false;	
		}
		
		if(this.y_Value != other.y_Value) {
			return false;	
		}
		
		return true;
	}
	
	//method to output the x and y
	public String toString() {
		return "Coordinate: x = " + this.getX() + ", y = " + this.getY();
	}
	
}