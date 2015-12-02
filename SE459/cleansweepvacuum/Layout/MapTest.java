package Layout;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

	@Test
	public void testPassingXYToConstructor() {
		// normal values
		Map t = new Map(100, 50);
		assertEquals(t.getWidth(), 100);
		assertEquals(t.getHeight(), 50);
	}
	
	@Test(expected=NegativeArraySizeException.class)
	public void testPassingNegativeXYToConstructor() {
		// negative values
		Map e = new Map(0, -100);
		assertEquals(e.getWidth(), 0);
		assertEquals(e.getHeight(), -100);
	}

	@Test
	public void testBuildEmptyLayout(){
		Map t = new Map(100, 40);
		
		// make sure all the cells are created
		assertNotNull(t.getCell(0, 0));
		assertNotNull(t.getCell(30, 10));
		assertNotNull(t.getCell(99, 39));
		
		// make sure the cell cords are set
		assertEquals(t.getCell(0, 0).getX(), 0);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void textGetCellOutOfBounds(){
		Map t = new Map(30, 10);
		assertNull(t.getCell(30, 0)); // x too high
		assertNull(t.getCell(0, 10)); // y too high
		assertNull(t.getCell(-1, -1)); // negative values
	}
	
	
	@Test
	public void testBuildPlainLayout(){
		Map t = new Map(100, 40); // default layout is hardwood floor
		assertEquals(t.getCell(0, 0).getType(), 0);
		assertEquals(t.getCell(0, 0).getTypeName(), "Hardwood Floor");
		
		t = new Map(4,4,1); // make a whole map of one type
		assertEquals(t.getCell(0, 0).getType(), 1);
		assertEquals(t.getCell(0, 0).getTypeName(), "Low-pile Carpet");	
	}
	
	@Test
	public void testBuildTestLayout(){
		Map t = new Map();
		assertEquals(t.getCell(1, 1).getType(), 1);
		assertEquals(t.getCell(1, 1).getTypeName(), "Low-pile Carpet");
		assertEquals(t.getCell(3, 3).getType(), 1);
	}
}
