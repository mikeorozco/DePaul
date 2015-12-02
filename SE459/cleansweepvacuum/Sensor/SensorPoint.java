package Sensor;

//SensorPoint is class tracks the x,y, floortype, whether a point has a charging station, has dirt, 
// and tracks the obstacles in all four directions of the current point
public class SensorPoint {
	
	//x,y of the sensor point
	private int x_Value;
	private int y_Value;
	
	 // The floor type of the cell can be bare floor, low carpet, high carpet	
	private FloorType floorType; 
	
	//dirt is the amount of dirt on the floor
	private int dirt;
	
	//flag for whether the point has a charging station
	private boolean isChargingStation;
	
	public int getX() {
		return x_Value;
	}

	public void setX(int x) {
		this.x_Value = x;
	}

	public int getY() {
		return y_Value;
	}

	public void setY(int y) {
		this.y_Value = y;
	}

	public FloorType getFloorType() {
		return floorType;
	}

	public void setFloorType(FloorType floortype) {
		this.floorType = floortype;
	}

	protected int getDirt() {
		return dirt;
	}

	public void setDirt(int dirt) {
		this.dirt = dirt;
	}
	
   //checks if floor has dirt
	public boolean hasDirt() {
		return (dirt > 0);
	}
	
	
	public void dirtCollected() {
		if (dirt > 0) {
			dirt--;
		}	
	}
	
	//obstacles in all four directions of the sensor point
	private ObstacleType east, west, north, south;
	
	public ObstacleType getEast() {
		return east;
	}

	public void setEast(ObstacleType e) {
		this.east = e;
	}
	
	public ObstacleType getWest() {
		return west;
	}

	public void setWest(ObstacleType w) {
		this.west = w;
	}
	
	public ObstacleType getNorth() {
		return north;
	}

	public void setNorth(ObstacleType n) {
		this.north = n;
	}
	
	public ObstacleType getSouth() {
		return south;
	}

	public void setSouth(ObstacleType s) {
		this.south = s;
	}
	public boolean isChargingStation() {
		return isChargingStation;
	}

	public void setChargingStation(boolean isChargingStation) {
		this.isChargingStation = isChargingStation;
	}
	
	//prints out the sensor points
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("The Location is: X = " + this.getX() + ", Y = " + this.getY() + "\n");
		sb.append("The type of Floor = " + this.getFloorType() + "\n");
		sb.append("Is this the Charging Station? = " + this.isChargingStation() + "\n");
		sb.append("The amount dirt collected = " + this.getDirt() + "\n");
		sb.append("The Directional Data: \n");
		sb.append("\t" + "West Direction = " + this.getWest() + "\n");
		sb.append("\t" + "North Direction = " + this.getNorth() + "\n");
		sb.append("\t" + "East Direction = " + this.getEast() + "\n");
		sb.append("\t" + "South Direction = " + this.getSouth() + "\n");
	
		return sb.toString();
		
	}
	
	
/*public void setSensorDataFromPathsString(String paths) {
		
		if (paths.length() != 4) {
			throw new RuntimeException("Path string length must be 4.");
		}
		if(paths.matches("[0-9]+") == false) {
			throw new RuntimeException("Path string must only contain numeric characters.");
		}
		//for each direction, iterate once and try to grab the matching index from chars
		for(int i = 0; i < 4; i++) {
			
			Integer intSensorValue = Character.getNumericValue(paths.charAt(i));
			ObstacleType navType = ObstacleType.FromInt(intSensorValue);
			switch (i) {
				case 0:
					this.setEast(navType);
					break;
				case 1:
					this.setWest(navType);
					break;
				case 2:
					this.setNorth(navType);
					break;
				case 3:
					this.setSouth(navType);
					break;
				default:
					break;
			
			}
			
		}
		
	}*/
}
