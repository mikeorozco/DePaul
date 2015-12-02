package PowerManagement;

import static org.junit.Assert.*;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PowerManagementTest extends TestCase {
	 public PowerManagementTest() {};
	 
	 @SuppressWarnings("deprecation")
	 public void test_getter_setter(){
		 PowerManagement pm1 = new PowerManagement();
		 Assert.assertTrue(pm1.getBatteryLife() == 50);
		 pm1.BatteryUsage(10.0);
		 Assert.assertTrue(pm1.getBatteryLife() == 40);
		 pm1.BatteryUsage(20.0);
		 Assert.assertTrue(pm1.getBatteryLife() == 20);
		 pm1.chargeBattery();
		 Assert.assertTrue(pm1.getBatteryLife() == 50);
		 
	 }
}