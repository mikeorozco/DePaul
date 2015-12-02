package Sensor;
import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;

import java.util.*;
import org.junit.*;
import org.mockito.Mockito;

import Sensor.*;

public class SensorTest {

	Sensors sensor;
	
	@Before
	public void setUp() throws Exception {
		
		sensor = Mockito.mock(Sensors.class);
		Mockito.when(sensor.getSensorData(0, 0)).thenReturn(new SensorPoint() {{ setFloorType(FloorType.Bare_Floor); }});
	}

	@After
	public void validate() {
		
	    validateMockitoUsage();
	}

	@Test
	public void TestSensorReturnsObjectWhenPassedValidPoint() {
		
		Object result = sensor.getSensorData(0, 0);		
		assertTrue(result != null);
		Mockito.verify(sensor).getSensorData(0, 0);
	}
	
	@Test(expected=RuntimeException.class)
	public void TestSensorThrowsRuntimeExceptionWhenPassedInvalidPoint() {
		
		Mockito.when(sensor.getSensorData(Mockito.anyInt(), Mockito.anyInt())).thenThrow(new RuntimeException());
		sensor.getSensorData(1, 1);
		Mockito.verify(sensor).getSensorData(1, 1);
	}
	
	@Test
	public void TestISensorReturnsSensorPointObjectWhenPassedValidPoint() {
		
		SensorPoint sp = sensor.getSensorData(0, 0);
		assertTrue(sp instanceof SensorPoint);
		Mockito.verify(sensor).getSensorData(0, 0);
	}
	
	@Test
	public void TestSensorReturnsCorrectFloorType() {
		
		SensorPoint sc = sensor.getSensorData(0, 0);
		assertNotNull(sc.getFloorType());
	}
	
	@Test
	public void TestSensorReturnsCorrectHasDirtCorrectly() {
		final int testAmount = 10;
		Mockito.when(sensor.getSensorData(1, 0)).thenReturn(new SensorPoint() {{
			setFloorType(FloorType.High_Carpet);
			setDirt(testAmount);
		}});
		
		SensorPoint sc = sensor.getSensorData(1, 0);
		assertTrue(sc.hasDirt());
		Mockito.verify(sensor).getSensorData(1, 0);
	}
	
	@Test
	public void TestSensorReturnsCorrectPathsData() {
		
		Mockito.when(sensor.getSensorData(1, 0)).thenReturn(new SensorPoint() {{
			setFloorType(FloorType.High_Carpet);
			setDirt(1);
			setEast(ObstacleType.Clear);
			setWest(ObstacleType.Stairs);
			setNorth(ObstacleType.Unknown);
			setSouth(ObstacleType.Door_Close);
		}});
		
		SensorPoint sp = sensor.getSensorData(1, 0);
		assertTrue(sp.getEast() == ObstacleType.Clear);
		assertTrue(sp.getWest() == ObstacleType.Stairs);
		assertTrue(sp.getNorth() == ObstacleType.Unknown);
		assertTrue(sp.getSouth() == ObstacleType.Door_Close);
		Mockito.verify(sensor).getSensorData(1, 0);
	}
	
	@Test
	public void TestLocalSensorSourceReturnsNotNullDataForValidPoint() {
		
		SensorSource isds = Mockito.mock(SensorSource.class);
		List<SensorPoint> cells = new ArrayList<SensorPoint>() {{
			add(new SensorPoint() {{ setY(0); setX(0); }});
			add(new SensorPoint() {{ setY(1); setX(0); }});
			add(new SensorPoint() {{ setY(2); setX(0); }});
			add(new SensorPoint() {{ setY(3); setX(0); }});
		}};
		Mockito.when(isds.LoadAllCells()).thenReturn(cells);
		Sensors sensorArray = new LocalSensorSource(isds);
		assertNotNull(sensorArray.getSensorData(0, 0));
	}
	
	@Test
	public void TestLocalSensorSourceReturnsNullForPointNotInDS() {
		
		SensorSource isds = Mockito.mock(SensorSource.class);
		List<SensorPoint> cells = new ArrayList<SensorPoint>() {{
			add(new SensorPoint() {{ setY(0); setX(0); }});
			add(new SensorPoint() {{ setY(1); setX(0); }});
			add(new SensorPoint() {{ setY(2); setX(0); }});
			add(new SensorPoint() {{ setY(3); setX(0); }});
		}};
		Mockito.when(isds.LoadAllCells()).thenReturn(cells);
		Sensors sensorArray = new LocalSensorSource(isds);
		assertNull(sensorArray.getSensorData(1, 1));	
	}
	
	@Test
	public void TestLocalSensorSourceReturnsCorrectCellForValidPoint() {
		
		SensorSource isds = Mockito.mock(SensorSource.class);
		final SensorPoint oneTwoCell = new SensorPoint() {{ setX(1); setY(2); }};
		List<SensorPoint> cells = new ArrayList<SensorPoint>() {{
			add(new SensorPoint() {{ setY(0); setX(0); }});
			add(new SensorPoint() {{ setY(1); setX(0); }});
			add(new SensorPoint() {{ setY(2); setX(0); }});
			add(new SensorPoint() {{ setY(3); setX(0); }});
			add(oneTwoCell);
		}};
		Mockito.when(isds.LoadAllCells()).thenReturn(cells);
		Sensors sensorArray = new LocalSensorSource(isds);
		assertTrue(oneTwoCell.equals(sensorArray.getSensorData(1, 2)));	
	}
}
