package Movement;
//CheckPath flags what direction the mover is able to move at a given point 

public class CheckPath {

	private boolean canMove = false;
	
	private boolean canGoNorth = false;
	
	private boolean canGoWest = false;
	
	private boolean canGoEast = false;
	
	private boolean canGoSouth = false;
	
	private boolean canGoToOrigin = false;
	
	public boolean getCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canGo) {
		this.canMove = canGo;
	}

	public boolean getCanGoNorth() {
		return canGoNorth;
	}

	public void setCanGoNorth(boolean canGoNorth) {
		this.canGoNorth = canGoNorth;
	}

	public boolean getCanGoWest() {
		return canGoWest;
	}

	public void setCanGoWest(boolean canGoWest) {
		this.canGoWest = canGoWest;
	}

	public boolean getCanGoEast() {
		return canGoEast;
	}

	public void setCanGoEast(boolean canGoEast) {
		this.canGoEast = canGoEast;
	}

	public boolean getCanGoSouth() {
		return canGoSouth;
	}

	public void setCanGoSouth(boolean canGoSouth) {
		this.canGoSouth = canGoSouth;
	}

	public boolean getCanGoToOrigin() {
		return canGoToOrigin;
	}
	
	public void setCanGoToOrigin(boolean b) {
		canGoToOrigin = b;
	}	
}
