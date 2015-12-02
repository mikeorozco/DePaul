package Layout;

import java.io.File;
import java.util.Scanner;

import Layout.Map;

public class CellInfoFinder {

	public static void main(String[] args){
		
		File sampleLayoutXMLFile = new File("MapLayout.xml");
		Map map = new Map(sampleLayoutXMLFile);
		
		Scanner scan = new Scanner(System.in);
		String yn;
		do {
		
			System.out.print("Enter x coordinate: ");
			int x = scan.nextInt();
			
			System.out.print("Enter y coordinate: ");
			int y = scan.nextInt();
			
			System.out.println("\nCurrent cell coordinates: (" + x + ", "+ y +")");
			
			System.out.println("Current cell type: " + map.getCell(x, y).getTypeName());
			System.out.println("Current cell north border: " + map.getCell(x, y).getNorthName());
			System.out.println("Current cell south border: " + map.getCell(x, y).getSouthName());
			System.out.println("Current cell east border: " + map.getCell(x, y).getEastName());
			System.out.println("Current cell west border: " + map.getCell(x, y).getWestName());
			
			System.out.println("\nAgain?: ");
			yn = scan.next();
			System.out.println();
			
		} while (yn.equals("y"));
		scan.close();
		
	}
}
