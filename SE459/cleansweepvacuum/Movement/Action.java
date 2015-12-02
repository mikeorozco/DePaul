package Movement;

import java.util.*;

import Movement.MoveAction;

public class Action extends MovementController implements MovementObserver, Mover {

	private MoveAction MoveAction;
	
	private boolean isMove = false;
	
	private List<Point> completedPoints = new ArrayList<Point>(); //list of visited points

	private List<Point> notcompletedPoints = new ArrayList<Point>(); // list of not visited points
	
	private List<Point> inaccessPoints = new ArrayList<Point>(); // list of not accessible points
	
	public List<Point> getCompletedPoints() { //get list of completed
		return completedPoints;
	}
	
	public List<Point> GetInacessiblePoints() { // get list of inaccessible points
		return inaccessPoints;
	}	
	
	public Action() { // create action and add to observer
		
		this.addObserver(this);	
	}

	public MoveAction getMoveAction() { // get MoveAction
		return MoveAction;
	}

	public void setMoveAction(MoveAction moveaction) {  // set MoveAction
		this.MoveAction = moveaction;
	}
// updated the completed and not completed  list
	@Override
	public void didmove(Point movedTo) {
		
		if(this.movState != MoveState.ReturnToOrigin && MoveAction != null) {
			if(MoveAction.performMovement(movedTo)) {
				//add to the list of points visited
				if (completedPoints.contains(movedTo) == false)  {
					completedPoints.add(movedTo);
				} //add to the list of not visited 
				if (notcompletedPoints.contains(movedTo)) {
					notcompletedPoints.remove(movedTo);
				}
			}
		}
		//get all the possible moves
		CheckPath result = this.movChecker.CheckPoint(currLoc);
		List<Point> possMoves = getMovesFromCheck(result);
		
		//compares the potential moves with the completed list
		for(Point p : possMoves) {
			if (completedPoints.contains(p) == false && notcompletedPoints.contains(p) == false 
					&& this.inaccessPoints.contains(p) == false) {
				notcompletedPoints.add(p);
			}
		}		
	}
 // creates the list of all possible moves
	private List<Point> getMovesFromCheck(
			CheckPath result) {
		List<Point> possMoves = new ArrayList<Point>();
		if (result.getCanGoNorth()) {
			possMoves.add(new Point(this.currLoc.getX(), this.currLoc.getY() + 1));
		}
		if (result.getCanGoSouth()) {
			possMoves.add(new Point(this.currLoc.getX(), this.currLoc.getY() - 1));
		}
		if (result.getCanGoWest()) {
			possMoves.add(new Point(this.currLoc.getX() - 1, this.currLoc.getY()));
		}
		if (result.getCanGoEast()) {
			possMoves.add(new Point(this.currLoc.getX() + 1, this.currLoc.getY()));
		}
		return possMoves;
	}

	@Override
	// this method runs the mover object through a cycle 
	public void roam(int ct) {

		this.cycle(ct);	
	}

	public void cycle(int maxCt) {
		isMove = true;
		this.didmove(currLoc);
		int ct = 0;
		while(isMove == true && ct < maxCt) {
			ct++;
			//if completed start over
			if (this.notcompletedPoints.size() == 0) {
				this.returnToOrigin();
				isMove = false;
			// else go through not completed and if not completed add to in accessible 	
			} else {
				Point d = this.notcompletedPoints.get(0);
				this.setDestPoint(d);
				System.out.println("Moving to: " + this.getDestPoint());
				if(this.MoveToDest() == false) {
					this.returnToOrigin();
					this.setDestPoint(d);
					if(this.MoveToDest() == false) {
						this.inaccessPoints.add(d);
						this.notcompletedPoints.remove(d);
					}
				}
			}
		}
	}

}
