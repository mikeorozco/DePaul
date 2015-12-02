package vacuum;

import DirtMonitor.*;
import Movement.*;
import Sensor.*;
import PowerManagement.*;
import DiagnosticLogger.*;

public class Vacuum implements MovementObserver, MovementCheckPath, MoveAction {

	private MovementController Controller;  //vacuum movement controller
	
	private Sensors sensor; //vacuum sensor
	
	private PowerManagement power; //vacuum battery manager
	
    private DirtMonitor dirtmonitor; //vacuum monitor
	
	private VacuumReport report = new VacuumReport(); // vacuum string output
	
	public Vacuum() {
		//create the movement controller
		Action act = new Action();

		//starts the mover
		act.setMoveAction(this);
		
		Controller = act; //controller controls the mover
		
		// setup our movement events
		Controller.addObserver(this);
		Controller.setMovementCheckPath(this);
		
		//create the sensor
		sensor = new LocalSensorSource();

		//create the power management object
		power = new PowerManagement();
		
		//create dirt monitor
		 dirtmonitor = new DirtMonitor();
		
	}
	
	//method to enact movement
	@Override
	public boolean performMovement(Point movedTo) {
		// gets a points each time the Mover moves
		SensorPoint currPoint = sensor.getSensorData(movedTo.getX(), movedTo.getY()); 
		
		//while the point has dirt and the vacuum is moving then calculate the battery life
		while(currPoint.hasDirt() && Controller.CurrentMoveState() != MoveState.ReturnToOrigin) {
			double costToOrigin = this.GetWeightedCostToOrigin(Controller.CurrLoc());
			double currBatteryLife = power.getBatteryLife();
			double batteryLifeAftrDirtColl = currBatteryLife - currPoint.getFloorType().getInt();
			double batteryLifeAftrMoveToOrigin = batteryLifeAftrDirtColl - costToOrigin;
			
			if(batteryLifeAftrMoveToOrigin > 0) { //if there is still battery life
				
				//proceed with dirt collection
				if(dirtmonitor.collectDirt()) {
					currPoint.dirtCollected();
					
					//update our vacuum report
					report.dirtColl++;
					System.out.println("We've vacuumed dirt!!");
					if(power.BatteryUsage(currPoint.getFloorType().getInt()) == false) {
						throw new RuntimeException("We've run out of battery life!");
					}
					//update our report
					report.powerUsed = report.powerUsed + currPoint.getFloorType().getInt();
				} else {}	
			} else {
				//we are out of power to collect this dirt.
				this.Controller.returnToOrigin();
				break;
			}
		}  //point has been cleaned
		return (currPoint.hasDirt() == false);
	}

	@Override
	public void didmove(Point movedTo) {
		
		/// gets a points each time the Mover moves
		SensorPoint currPoint = sensor.getSensorData(movedTo.getX(), movedTo.getY()); 
		
		//update our report
		report.spacesMoved++;
		
		if (this.Controller.CurrentMoveState() == MoveState.Move) {
			report.spacesRoamed++;
		}
		
		//output of the vacuum current status
		System.out.println("====================================");
		System.out.println("Vacuum has moved to " + currPoint.getX() + ", " + currPoint.getY());
		System.out.println("Battery Life is: " + power.getBatteryLife());
		System.out.println("Dirt picked up: " + dirtmonitor.getCurrDirtCapacity());
		System.out.println("Movement State: " + Controller.CurrentMoveState());
		System.out.println("Current Cost to Origin: " + this.GetWeightedCostToOrigin(Controller.CurrLoc()));
		System.out.println("Sensor Info " + currPoint);
		System.out.println("====================================");

		StringBuilder sb = new StringBuilder();
		
		sb.append("====================================" + "\r\n");
		sb.append("Vacuum has moved to " + currPoint.getX() + ", " + currPoint.getY() + "\r\n");
		sb.append("Battery Life is: " + power.getBatteryLife() + "\r\n");
		sb.append("Dirt picked up: " + dirtmonitor.getCurrDirtCapacity() + "\r\n");
		sb.append("Movement State: " + Controller.CurrentMoveState() + "\r\n");
		sb.append("Current Cost to Origin: " + this.GetWeightedCostToOrigin(Controller.CurrLoc()) + "\r\n");
		sb.append("Sensor Info " + currPoint + "\r\n");
		sb.append("====================================" + "\r\n");

		//checks if we are at the charging station
		if (currPoint.isChargingStation()) {
			//update our report
			report.battRecharged++;
			
			//charge the battery
			power.chargeBattery();
			
			//we also empty the bag
			this.dirtmonitor.emptyBag();
		}
	}

	//This method checks the path options for the vacuum before it moves to insure it doesn't go into an obstacle
	@Override
	public CheckPath CheckPoint(Point Point) {
		CheckPath checkResult = new CheckPath();
		
		SensorPoint currPoint = sensor.getSensorData(Controller.CurrLoc().getX(), Controller.CurrLoc().getY());
		checkResult.setCanGoWest(currPoint.getWest().CanMoveTo());
		checkResult.setCanGoEast(currPoint.getEast().CanMoveTo());
		checkResult.setCanGoSouth(currPoint.getSouth().CanMoveTo());
		checkResult.setCanGoNorth(currPoint.getNorth().CanMoveTo());	
		SensorPoint nextPoint = sensor.getSensorData(Point.getX(), Point.getY());
			
		System.out.println("Next position to move to: " + nextPoint);
		System.out.println("From current position: " + currPoint);
		if(nextPoint == null) {
			checkResult.setCanMove(false);
			return checkResult;
		}

		if (dirtmonitor.isFull() && this.Controller.CurrentMoveState() != MoveState.ReturnToOrigin) {
			
			//dirt bag is full, going to the origin
			this.Controller.returnToOrigin();
			checkResult.setCanGoToOrigin(true);
			return checkResult;
		}
		
		checkResult.setCanMove(checkObstacles(
				currPoint, nextPoint));
		
		//calculate power consumption of the move
		double nextMoveCost = (currPoint.getFloorType().getInt()  + nextPoint.getFloorType().getInt()) * .5;
		
		if (Controller.CurrentMoveState() != MoveState.ReturnToOrigin && checkResult.getCanMove()) {
			double costFromNextPoint = this.GetWeightedCostToOrigin(new Point(nextPoint.getX(), nextPoint.getY()));
			
			//this probably works but depends upon navigation to move back to the current space to be accurate
			double batteryLifeNeededToGetHomeFromNextSquare = costFromNextPoint + nextMoveCost; 
			if ((power.getBatteryLife() - nextMoveCost) <= batteryLifeNeededToGetHomeFromNextSquare) {
				Controller.returnToOrigin();
				checkResult.setCanMove(false);
				checkResult.setCanGoToOrigin(true);
			}
		}
		
		//checks to see if the battery has enough power
		if(checkResult.getCanMove()) {
			if(power.BatteryUsage(nextMoveCost) == false) {
				throw new RuntimeException("We've run out of battery life!");
			}
	
			//update our stats
			report.powerUsed = report.powerUsed + nextMoveCost;
		}
		return checkResult;
	}


	private boolean checkObstacles(SensorPoint currPoint,
			SensorPoint nextPoint) {
		
		if(currPoint == null || nextPoint == null) {
			return false;
		}
		
		//determine next destination
		int xDiff = currPoint.getX() - nextPoint.getX();
		int yDiff = currPoint.getY() - nextPoint.getY();
		
		boolean canWeMove = false;
		
		if(xDiff > 0) {
			//moving west
			canWeMove = currPoint.getWest().CanMoveTo();
		} else if(xDiff < 0) {
			//moving east
			canWeMove = currPoint.getEast().CanMoveTo();
		} else if(yDiff > 0) {
			//moving south
			canWeMove = currPoint.getSouth().CanMoveTo();
		} else if(yDiff < 0) {
			//we're moving north
			canWeMove = currPoint.getNorth().CanMoveTo();
		} else {}
		return canWeMove;
	}

	//get movement Controller
	public Mover getController() {
		return Controller;
	}

	//set movement Controller
	public void setController(Mover Controller) {
		this.Controller = (MovementController) Controller;
	}

	//get sensor
	public Sensors getSensor() {
		return sensor;
	}

	//set sensor
	public void setSensor(Sensors sensor) {
		this.sensor = sensor;
	}

	//get power management
	public PowerManagement getPower() {
		return power;
	}

	//set power management
	public void setPower(PowerManagement power) {
		this.power = power;
	}

	//get dirt monitor
	public DirtMonitor getDirtMonitor() {
		return dirtmonitor;
	}
	
	//set dirt monitor
	public void setDirtCollection(DirtMonitor dirtmonitor) {
		this.dirtmonitor = dirtmonitor;
	}
	
	//get vacuum report
	public VacuumReport getReport() {
		return report;
	}

	//set vacuum report
	public void setReport(VacuumReport _report) {
		this.report = _report;
	}
	
	//calculates the cost to return to the origin
	@Override
	public int GetWeightedCostToOrigin(Point fromPoint) {
		
		class OriginCostTracker implements MovementObserver {
			
			private int cost;
			
			@Override
			public void didmove(Point movedTo) {
				
				try	{
					cost++;
					SensorPoint sc = sensor.getSensorData(movedTo.getX(), movedTo.getY());
					System.out.println("Adding point (" + sc.getX() + ", " + sc.getY() + ") " + sc.getFloorType() + " Cost: " + cost);
					cost = cost + sc.getFloorType().getInt();
					System.out.println("Cost after adding point: " + cost);
				} catch(Exception e) {	
				}	
			}
	
			public int getTrackedWeight() {
				System.out.println("Returning Weight: " + cost);
				return cost;
			}	
		}
		
		class SimpleMovementCheckPath implements MovementCheckPath {

			private Mover smover;
			
			public SimpleMovementCheckPath(Mover mover) {
				
				smover = mover;
			}
			
			@Override
			public CheckPath CheckPoint(Point Point) {
				CheckPath result = new CheckPath();
				try	{
					SensorPoint moveFrom = sensor.getSensorData(smover.CurrLoc().getX(), smover.CurrLoc().getY());
					SensorPoint moveTo = sensor.getSensorData(Point.getX(), Point.getY());
					result.setCanMove(checkObstacles(moveFrom, moveTo));
					 
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				return result;
			}

			@Override
			public int GetWeightedCostToOrigin(Point fromPoint) {
				// TODO Auto-generated method stub
				return 0;
			}
		}
		
		Mover childMover = new MovementController();
		
		childMover.setDestPoint(fromPoint);
		childMover.MoveToDest();
		
		OriginCostTracker tracker = new OriginCostTracker();
		
		childMover.addObserver(tracker);
		childMover.setMovementCheckPath(new SimpleMovementCheckPath(childMover));
		childMover.returnToOrigin();
		
		return tracker.getTrackedWeight();
	}
	
	class VacuumReport {
		
		public int dirtColl;
		public int battRecharged;
		public int spacesMoved;
		public double powerUsed;
		public int spacesRoamed;
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();

			sb.append("Dirt Collected = " + this.dirtColl + "\r\n");
			sb.append("Spaces Moved = " + this.spacesMoved + "\r\n");
			sb.append("Spaces Roamed = " + this.spacesRoamed + "\r\n");
			sb.append("Units of Power Used = " + this.powerUsed + "\r\n");
			sb.append("Times Battery Recharged = " + this.battRecharged + "\r\n");
		
			return sb.toString();
		}
	}
}
