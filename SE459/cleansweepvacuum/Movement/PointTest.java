package Movement;

import junit.framework.*;

public class PointTest extends TestCase {
	 public PointTest(String name) {
		    super(name);
	 }
	 
	//Point Test  is a junit test class to test the Point class	
	@SuppressWarnings("deprecation")
	public void test_equals(){
		Point p1 = new Point(1,2);
		Point p2 = new Point(3,4);
	
		Assert.assertTrue(p1.getX() == 1);
		Assert.assertTrue(p1.getY() == 2);
		p1.setX(3);
		Assert.assertTrue(p1.getX() == 3);
		p1.setY(4);
		Assert.assertTrue(p1.getY() == 4);
		Assert.assertTrue(p1.getX() == 3);
		Assert.assertTrue(p1.getY() == 4);
		Assert.assertTrue(p1 != p2);
		Assert.assertTrue(p1.toString().equals(p2.toString()));
	}
}

