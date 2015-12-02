package Sensor;

// enum class that handles the different obstacles types whether clear or a wall
public enum ObstacleType {
	Clear(true),
	Door_Open(true),
	Door_Close(false),
	Stairs(false),
	Wall(false),
	Unknown(false);

	ObstacleType (boolean canMove) {
		this.canMove = canMove;
	}
	
	private final boolean canMove;
	
	public boolean CanMoveTo() {
		return canMove;
	}
	
	public static ObstacleType FromInt(int i) {
		switch (i) {
		case 1:
			return Clear ;
		case 2:
			return Wall;
		case 3:
			return Stairs;
		default:
			return ObstacleType.Unknown;
		}
	}	
	
	public static ObstacleType FromString(String s) {
		switch (s) {
		case "Clear":
			return Clear;
		case "Door (open)":	
		    return Door_Open;
		case "Door (closed)":
		    return Door_Close;
		case "Stairs":
			return Stairs;
		case "Wall":
			return Wall;
		default:
			return ObstacleType.Unknown;
		}
	}
}
