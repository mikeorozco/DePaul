package Movement;

import java.util.*;

// Controller class that controls the movement of the vacuum cleaner
public class MovementController implements Mover {
	
	Point currLoc;
	Point destPoint;
	private Point prevLoc;
	MoveState movState = MoveState.Stop;
	MovementCheckPath movChecker;
	List<MovementObserver> MovementObservers = new ArrayList<MovementObserver>();
	
	public MovementController() {
		this.currLoc = new Point(0,0);
	}

	//get previous location point
	public Point PrevLoc() {
		return this.prevLoc;
	}
	
	//get current location point
	public Point CurrLoc() {
		return this.currLoc;
	}

	// assigns next point
	@Override
	public void setDestPoint(Point p) {
		this.destPoint = p;
		
	}

	 //get next location point
	@Override
	public Point getDestPoint() {
		return this.destPoint;
	}
	
	//stores previous visited points
	public List<Point> prevRoamedPoints = new ArrayList<Point>();
	
	public boolean MoveTo(Point p) {
		//test
		//System.out.println("MoveTo: Moving to " + p.toString() + " from " + this.CurrLoc());
		//Calculates vacuum movement, if > than 1 then the vacuum moved illegally, prevents diagonal movement
		int diff_X = Math.abs(p.getX() - this.currLoc.getX());
		int diff_Y = Math.abs(p.getY() - this.currLoc.getY());
		if ((diff_X + diff_Y) > 1 || (diff_X + diff_Y) < 0) {
			throw new RuntimeException("You cannot move more than one space from our current location.");	
		}
		// conditional  if there is a next possible move then set a destination and tell the observer
		if (this.MovementCheckPath != null) {
			CheckPath nextmove= this.MovementCheckPath.CheckPoint(p);
			if (nextmove.getCanGoToOrigin()) {
					this.setDestPoint(p);		
				//if(this.MoveToDest()) {
				//	return true;
				//}
			}
			//movement is rejects and returns false flag
			boolean result = nextmove.getCanMove();
			if(result == false) { 
				//test
				//System.out.println("MoveTo: Cannot Move To Location");
				return false; 
			}
		}
		//Test
		//System.out.println("MoveTo: Moving to accepted");
		// move3ent is accepted and updates the current to next point and previous point to current 
		this.prevLoc = this.currLoc;
		this.currLoc = p;
		this.notifyObservers();
		return true;
	}
	
	@Override
	public boolean MoveToDest() {
		
		int diff_x, diff_y;
		
		//sets state to move
		if (this.movState == MoveState.Stop) {
			this.movState = MoveState.Move;
		}
		Point origDest = this.getDestPoint();
		while ((this.movState != MoveState.Stop )&& this.CurrLoc().equals(this.getDestPoint()) == false) {
			diff_x = this.CurrLoc().getX() - this.getDestPoint().getX();
			diff_y = this.CurrLoc().getY() - this.getDestPoint().getY();
			if (diff_x != 0 ) {
				//condtional to see if we can move horizontal
				if(this.MoveTo(new Point(this.CurrLoc().getX() + ((diff_x > 0)? -1:1) ,this.CurrLoc().getY())) == false) {
					if (Math.abs(diff_y) > 0 && this.MoveTo(new Point(this.CurrLoc().getX(),this.CurrLoc().getY()  + ((diff_y > 0)? -1:1) )) == true) {
					
					} else {	
						int movedUpCount = 0;
						boolean movedHorizontal = false;
						while(movedUpCount < 5 && movedHorizontal == false /*&& failedToMoveVertically == false*/) {
							if(this.MoveTo(new Point(this.CurrLoc().getX(), this.CurrLoc().getY() + 1))) {
								movedUpCount++;
								movedHorizontal = this.MoveTo(new Point(this.CurrLoc().getX() + ((diff_x > 0)? -1:1) , this.CurrLoc().getY()));
							} else {
								movedHorizontal = false;
							}
						}
					}
				}
			} else {		
				// conditional to see if we can move vertical
				if (diff_y != 0) {
					if(this.MoveTo(new Point(this.CurrLoc().getX(),this.CurrLoc().getY()  + ((diff_y > 0)? -1:1) )) == false) {
						int movedUpCount = 0;
						boolean movedVertical = false;
						//boolean failedToMoveHorizontally = false;
						while(movedUpCount < 5 && movedVertical == false /*&& failedToMoveHorizontally == false*/) {
							if(this.MoveTo(new Point(this.CurrLoc().getX() + 1, this.CurrLoc().getY()))) {
								movedUpCount++;
								movedVertical = this.MoveTo(new Point(this.CurrLoc().getX(), this.CurrLoc().getY() + ((diff_y > 0)? -1:1) ));
							} else {
								movedVertical = false;
							}
						}
					}
				}
			}
		}
			
		if (this.movState != MoveState.Stop) {
			this.movState = MoveState.Stop;
		}
		if	(this.CurrLoc().equals(origDest)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setMovementCheckPath(MovementCheckPath inc) {
		this.movChecker = inc;
	}
	
	
	//Adds a mover to be observed
	@Override
	public void addObserver(MovementObserver observer) {
		if (observer != null ) {
			MovementObservers.add(observer);
		}
	}
	
	//updates the observer than a mover moved
	private void notifyObservers() {
		for(MovementObserver obs: this.MovementObservers) {
			try	{
			    obs.didmove(currLoc);	
			} catch(Exception e) {
				System.out.println("Observer Exception: " + e.getMessage());
			}
		}
	}

	//returned mover to the origin point
	@Override
	public void returnToOrigin() {
		this.movState = MoveState.ReturnToOrigin;
		this.setDestPoint(new Point(0,0));
		this.MoveToDest();
	}

	 //finds and stores the list of possible next points to visit 
	@Override
	public void roam(int count) {
		List<Point> possMoves = null;
		boolean didMove;
		this.movState = MoveState.Move;
		//create the list possible next points from the current point
		for(int i = 0; (i < count && this.movState != MoveState.ReturnToOrigin ); i++) {
			didMove = false;
			possMoves = new ArrayList<Point>();
			possMoves.add(new Point(currLoc.getX() + 1,currLoc.getY()));
			possMoves.add(new Point(currLoc.getX(),currLoc.getY() + 1));
			possMoves.add(new Point(currLoc.getX(),currLoc.getY() - 1));
			possMoves.add(new Point(currLoc.getX() - 1,currLoc.getY()));			
			
			try {} catch(Exception e) {	 
			}	
			Collections.sort(possMoves, new Comparator<Point>() {

				@Override //checks list to see if points were previously visited
				public int compare(Point arg0, Point arg1) {
					boolean haveRoamedtoArg0 = prevRoamedPoints.contains(arg0);
					boolean haveRoamedToArg1 = prevRoamedPoints.contains(arg1);
					if	(haveRoamedtoArg0 && haveRoamedToArg1 == false) {
						return 1;
					} else if(haveRoamedtoArg0 == false && haveRoamedToArg1) {
						return -1;
					}
					return 0;
				}
			}); //updates the possible moves list
			if (possMoves.contains(prevLoc)) {
				possMoves.remove(prevLoc);
				possMoves.add(prevLoc);
			} //checks for possible moves that were previously visited and adds to the previously visited list 
				for(Point test : possMoves) {
					if(this.MoveTo(test)) {
						didMove = true;
						try	{
							this.prevRoamedPoints.add(test);
						} catch(Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					} 
					//conditional if move state isn't Move then break
					if (this.movState != MoveState.Move) {
						break;
					}
				}
				//if you didnt move and the mover isn't returning to 0,0 then move to previous location
			if (didMove == false && this.movState != MoveState.ReturnToOrigin) {
				this.MoveTo(this.PrevLoc());
			}
		}
		//sets state to Stop
		this.movState = MoveState.Stop;
	}
	
	//gets the current Move State Move, Stop, Return to Origin
	@Override
	public MoveState CurrentMoveState() {
		return this.movState;
	}

}
