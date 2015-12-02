package Movement;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import Movement.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class MovementTest implements MovementObserver {

	private Mover toMove;
	
	@Before
	public void setUp() throws Exception {
	
		toMove = new MovementController();
	}

	@Test
	public void TestCurrLocUpdateAfterMoveTo() {
		
		Point p = new Point(0, 1);
		toMove.MoveTo(p);		
		assert(toMove.CurrLoc().equals(p));
	}
	
	@Test
	public void TestValueIsOrigin() {
		assert(toMove.CurrLoc().equals(new Point(0,0)));
	}
	
	@Test(expected=RuntimeException.class)
	public void TestExceptionIsThrownIfAttemptingToMoveMoreThanOneSpace() {
		
		Point p = new Point(4,7);
		toMove.MoveTo(p);
	}

	@Test(expected=RuntimeException.class)
	public void TestExceptionIsThrownIfAttemptingToMoveDiagonally() {
		
		Point p = new Point(1,1);
		toMove.MoveTo(p);	
	}

	@Test
	public void TestMoverSetDestPointIsStored() {
		
		Point p = new Point(10,10);
		toMove.setDestPoint(p);
		assert(p.equals(toMove.getDestPoint()));
	}
	
	@Test
	public void TestMoverMovesToDestPoint() {
		
		Point p = new Point(1,1);
		toMove.setDestPoint(p);	
		toMove.MoveToDest();
		boolean result = p.equals(toMove.CurrLoc());
		assertTrue(result);	
	}
	
	@Test
	public void TestMoverReturnsToOrigin() {
	
		Point destination = new Point(8,5);
		toMove.setDestPoint(destination);
		toMove.MoveToDest();
		assertTrue(toMove.CurrLoc().equals(destination));
		toMove.returnToOrigin();
		assertTrue(toMove.CurrLoc().equals(new Point(0,0)));
	}
	
	@Test 
	public void TestRoamMovesMover() {
		
		toMove.addObserver(this);
		toMove.roam(10);
		assertFalse (toMove.CurrLoc().equals(new Point(0,0)));
	}
	
	@Test
	public void TestMoverReturnsToHomeAndStopsWhenInstructed() {
		
		class OriginObserver implements MovementObserver {

			int returnAfterCount = 10;
			int spacesNavigated = 0;
			
			@Override
			public void didmove(Point navigatedTo) {
				spacesNavigated++;
				
				if (spacesNavigated >= returnAfterCount) {
				toMove.returnToOrigin();
				}			
			}
		}
		
		toMove.addObserver(new OriginObserver());
		toMove.roam(20);
		assertTrue(toMove.CurrLoc().equals(new Point(0,0)));	
	}
	
	@Test
	public void TestMoverDefaultsToMovingToAnUnexploredPoint() {
		
		Point origin = new Point(0,0);
		toMove.roam(1);
		Point move1 = toMove.CurrLoc();
		toMove.MoveTo(origin);
		toMove.roam(1);
		assertFalse(toMove.CurrLoc().equals(move1));
	}
	
	@Test
	public void TestActionMovesToEveryPossLocAndPerformsAction() {
				
		final List<Point> possPoints = new ArrayList<Point>();
		
		possPoints.add(new Point(0,0));
		possPoints.add(new Point(1,0));
		possPoints.add(new Point(2,0));
		possPoints.add(new Point(0,1));
		possPoints.add(new Point(0,2));
		possPoints.add(new Point(1,1));
		possPoints.add(new Point(1,2));
		possPoints.add(new Point(2,1));
		possPoints.add(new Point(2,2));
		
		class ActionMovChecker implements MovementCheckPath {

			@Override
			public CheckPath CheckPoint(Point Point) {
				CheckPath checkResult = new CheckPath();
				
				if(possPoints.contains(Point)) {
					checkResult.setCanMove(true);
				}		
				if(possPoints.contains(new Point(Point.getX(), Point.getY() + 1))) {
					checkResult.setCanGoNorth(true);
				}
				if(possPoints.contains(new Point(Point.getX(), Point.getY() - 1))) {
					checkResult.setCanGoSouth(true);
				}
				if(possPoints.contains(new Point(Point.getX() + 1, Point.getY()))) {
					checkResult.setCanGoEast(true);
				}
				if(possPoints.contains(new Point(Point.getX() - 1, Point.getY()))) {
					checkResult.setCanGoWest(true);
				}
				return checkResult;
			}

			@Override
			public int GetWeightedCostToOrigin(Point fromPoint) {
				 //TODO Auto-generated method stub
				return 0;
			}	
		}
		
		class ActionReturnTrue implements MoveAction {

			@Override
			public boolean performMovement(Point navigatedTo) {
				return true;
			}			
		}
				
		Action act = new Action();
		act.setMoveAction(new ActionReturnTrue());
		act.setMovementCheckPath(new ActionMovChecker());
		act.cycle(1000);
		assertTrue(act.getCompletedPoints().size() == possPoints.size());
	}
	
	@Test
	public void TestMoverMovesToPointIfMovementCheckerReturnsTrue() {
		MovementCheckPath inc = Mockito.mock(MovementCheckPath.class);
		Point destination = new Point(0,1);
		when(inc.CheckPoint(destination)).thenReturn(new CheckPath() {{ setCanMove(true); }});
		toMove.setMovementCheckPath(inc);
		toMove.MoveTo(destination);
		assertTrue(toMove.CurrLoc().equals(destination));
	}
	
	@Test
	public void TestMoverMovesAroundObjectIfItCannotMoveToASpotInItsDefaultPath() {
	
		MovementCheckPath inc = Mockito.mock(MovementCheckPath.class);
		
		Point obstacle = new Point(0,1);
		Point destination = new Point(0,2);
		
		when(inc.CheckPoint((Point) anyObject())).thenReturn(new CheckPath() {{ setCanMove(true); }});
		when(inc.CheckPoint(obstacle)).thenReturn(new CheckPath() {{ setCanMove(false); }});
		toMove.setMovementCheckPath(inc);
		toMove.setDestPoint(destination);
		toMove.MoveToDest();
		assertTrue(toMove.CurrLoc().equals(destination));
		
	}
	
	@Override
	public void didmove(Point navigatedTo) {
		// TODO Auto-generated method stub
		
	}	
	
	
}