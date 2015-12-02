package PowerManagement;

public class PowerManagement {

	private static int MAX_CAPACITY = 50;
	
	private double batteryLife;
	
	//constructs battery	
	public PowerManagement() {
		this.batteryLife = MAX_CAPACITY;
	}
	
	//gets battery reading
	public double getBatteryLife() {
		return batteryLife;
	}
	
	//updates battery reading
	public boolean BatteryUsage(double EnergyAmt) {
		
		if (EnergyAmt <= batteryLife) {
			batteryLife -= EnergyAmt;
			return true;
		}
		return false;
	}

	//charges battery to 50 units or 100%
	public void chargeBattery() {
		batteryLife = MAX_CAPACITY;		
	}
}

