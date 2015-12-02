package Sensor;

//FloorType is the enum class that handles whether it is bare floor, low carpet or high carpet
public enum FloorType {
	
	Bare_Floor(1),
	Low_Carpet(2),
	High_Carpet(4);
	
private final int value;
	
	FloorType(int val) {
		
		value = val;
	}
	
	public int getInt() { return value; }
	
	public static FloorType FromInt(int floorTypeInt) {
		
		switch(floorTypeInt) {
			case 1:
				return Bare_Floor;
			case 2:
				return Low_Carpet;
			case 4:
				return High_Carpet;
			default:
				return Bare_Floor;
		}
	}

	public static FloorType FromString(String floorType) {
		
		switch(floorType) {
			case "Bare Floor":
				return Bare_Floor;
			case "Low-Pile Carpet":
				return Low_Carpet;
			case "High-Pile Carpet":
				return High_Carpet;
			default:
				return Bare_Floor;
		}
	}
}
