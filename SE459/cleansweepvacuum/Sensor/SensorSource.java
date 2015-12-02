package Sensor;

import java.util.List;

//interface for sensors that load all data from an list of sensor points
public interface SensorSource {

	List<SensorPoint> LoadAllCells();	
}
