package DirtMonitor;

import static org.junit.Assert.*;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DirtMonitorTest extends TestCase {
	 public DirtMonitorTest(String name) {
		    super(name);
     }
	 
	 @SuppressWarnings("deprecation")
	 public void test_getter_setter(){
		 DirtMonitor d1 = new DirtMonitor();
		 Assert.assertFalse(d1.getEmptyMe());
		 Assert.assertTrue(d1.getCurrDirtCapacity() == 0);
		 d1.setCurrDirtCapacity(50);
		 Assert.assertTrue(d1.getCurrDirtCapacity() == 50);
		 Assert.assertTrue(d1.isFull());
		 d1.emptyBag();
		 Assert.assertFalse(d1.getEmptyMe());
		 Assert.assertTrue(d1.getCurrDirtCapacity() == 0); 
	 }
}
