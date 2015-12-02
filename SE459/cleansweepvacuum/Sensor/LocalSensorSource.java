package Sensor;

import java.util.*;

//stores a list of sensor points
public class LocalSensorSource implements Sensors {

	private List<SensorPoint> dataFromSensor = new ArrayList<SensorPoint>();
	
	
	public LocalSensorSource() {
		
		SensorSource ds = new XMLParser();
		dataFromSensor = ds.LoadAllCells();
	}
	
	public LocalSensorSource(SensorSource sSource) {
	dataFromSensor = sSource.LoadAllCells();
	}
	
	@Override
	public SensorPoint getSensorData(int x, int y) {
		for(SensorPoint spoint : dataFromSensor) {
			if (spoint.getX() == x && spoint.getY() == y) {
				return spoint;
			}
		}
		return null;
	}
	
}
