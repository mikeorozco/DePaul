package vacuum;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import vacuum.*;
import Movement.*;
import Sensor.*;
import DirtMonitor.*;
import PowerManagement.*;


public class VacuumTest {
	
	Vacuum VC;
	
	@Before
	public void setup() {
		VC = new Vacuum();
	}
	
	@Test
	public void TestVacuumReturnsToDockingStationBeforeRunningOutOfBattery() {
		
		double originalBatteryLife = VC.getPower().getBatteryLife();
		
		VC.getController().roam(1);
		
		double cost = originalBatteryLife - VC.getPower().getBatteryLife();
		
		VC.getPower().BatteryUsage(VC.getPower().getBatteryLife() - cost);
		
		VC.getController().roam(1);
	}
	
	@Test
	public void TestVacuumEmptiesDirtWhenAtDockingStation() {
		
		VC.getController().setDestPoint(new Point(1,1));
		
		VC.getController().MoveToDest();
		
		VC.getDirtMonitor().setCurrDirtCapacity(50);
		
		VC.getController().returnToOrigin();

		assertTrue(VC.getDirtMonitor().getCurrDirtCapacity() == 0);
	}
	
	/*@Test
	public void TestVacuumReturnsToDockingStationAndContinuesToDestWhenFull() {
		
		class ReturnToOriginObserver implements MovementObserver {

			public int totalMoves = 0;
			public boolean didSetMoveStateToReturnToOrigin = false;
			public boolean didReturnToOrigin = false;
			
			@Override
			public void didmove(Point movedTo) {
				
				totalMoves++;
				if(VC.getController().CurrentMoveState() == MoveState.ReturnToOrigin) {
					
					didSetMoveStateToReturnToOrigin = true;
				}
				
				if(movedTo.equals(new Point(0,0))) {
					didReturnToOrigin = true;
				}
			}
		}
		
		VC.getController().setDestPoint(new Point(0,1));
		VC.getController().MoveToDest();
		VC.getDirtMonitor().setCurrDirtCapacity(50);
		ReturnToOriginObserver observer = new ReturnToOriginObserver();
		VC.getController().addObserver(observer);
		VC.getController().setDestPoint(new Point(1,1));
		VC.getController().MoveToDest();
		
		assertTrue(observer.didSetMoveStateToReturnToOrigin);
		assertTrue(observer.didReturnToOrigin);
		assertTrue(observer.totalMoves > 1);
		
	}*/
	
	@Test
	public void TestVacuumChargesWhenReturnsToDockingStation() {
		
		double startingBatteryLife = VC.getPower().getBatteryLife();
		
		VC.getController().setDestPoint(new Point(9,9));
		
		VC.getController().MoveToDest();
		
		VC.getController().returnToOrigin();
		
		assertTrue(startingBatteryLife == VC.getPower().getBatteryLife());
		
	}
	
}
