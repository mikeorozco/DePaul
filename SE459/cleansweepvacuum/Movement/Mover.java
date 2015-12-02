package Movement;

// This interface will be implemented by a Movement controller which handles the move logic.

public interface Mover {

	MovementCheckPath MovementCheckPath = null; //create and initialize checkpath object to null

	boolean MoveTo(Point p); //can move to a new point?

	Point CurrLoc(); //get current location point
	
	Point PrevLoc(); //get previous location point

	void setDestPoint(Point p); // assigns next point

	Point getDestPoint(); //get next location point

	boolean MoveToDest(); ///can move to a destination point?

	void setMovementCheckPath(MovementCheckPath inc); //setter for MovementCheckPath

	void addObserver(MovementObserver observer); // add observer object to be 

	void returnToOrigin(); //method to return to origin or  (0,0)

	void roam(int i); //finds and stores the list of possible next points to visit 

	MoveState CurrentMoveState(); //get current move state move, stop, or return to origin
	
}
