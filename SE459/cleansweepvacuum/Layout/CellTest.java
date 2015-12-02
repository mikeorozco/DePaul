package Layout;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {

	@Test
	public void testSetCords() {
		Cell c = new Cell(5, 10, 0, 0, 0, 0, 0, 0);
		assertEquals(c.getX(), 5);
		assertEquals(c.getY(), 10);
	}
	
	@Test
	public void testConstructCellWithType(){
		Cell c = new Cell(5, 10, 0, 0, 0, 0, 0, 0);
		assertEquals(c.getTypeName(), "Bare Floor");
		c = new Cell(5, 10, 1, 0, 0, 0, 0, 0);
		assertEquals(c.getTypeName(), "Low-pile Carpet");
	}
	
	
	@Test
	public void testGetTypeId(){
		Cell c = new Cell(5, 10, 0, 0, 0, 0, 0, 0);
		assertEquals(c.getType(), 0);
	}
	
	@Test
	public void testGetTypeName(){
		Cell c = new Cell(5, 10, 0, 0, 0, 0, 0, 0);
		assertEquals(c.getTypeName(), "Bare Floor");
	}
}
