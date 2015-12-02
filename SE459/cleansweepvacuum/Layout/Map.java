package Layout;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Map {
	
	private int WIDTH, HEIGHT;
	protected Cell[][] LAYOUT;

	/* Constructor */
	public Map(){
		buildTestLayout();
	}
	public Map(int x, int y){
		setWidth(x);
		setHeight(y);
		buildEmptyLayout();
	}
	public Map(int x, int y, int type){
		setWidth(x);
		setHeight(y);
		buildPlainLayout(type);
	}
	public Map(File file){
		buildLayoutFromXMLFile(file);
	}

	private void buildEmptyLayout(){
		this.LAYOUT = new Cell[this.WIDTH][this.HEIGHT];
		int type = 0, north = 0, south = 0, east = 0, west = 0, dirt = 0;

		for(int i=0; i<this.WIDTH; i++){
			for(int j=0; j<this.HEIGHT; j++){
				this.LAYOUT[i][j] = new Cell(i, j, type, north, south, east, west, dirt);
			}
		}
	}

	private void buildPlainLayout(int type){
		this.LAYOUT = new Cell[this.WIDTH][this.HEIGHT];
		int north = 0, south = 0, east = 0, west = 0, dirt = 0;

		for(int i=0; i<this.WIDTH; i++){
			for(int j=0; j<this.HEIGHT; j++){
				this.LAYOUT[i][j] = new Cell(i, j, type, north, south, east, west, dirt);
			}
		}
	}

	private void buildTestLayout(){
		setWidth(3);
		setHeight(3);
		buildPlainLayout(0);
		
//		0 0 0
// 		1 1 1
// 		2 2 2
		
		setCell(0, 0, 0, // row, col, type
				1, 0, 0, 1); // north border, east border, south border, west border
		setCell(1, 0, 0,
				1, 0, 2, 0);
		setCell(2, 0, 0, 
				1, 1, 0, 0);
		
		setCell(0, 1, 1, 
				0, 1, 0, 1);
		setCell(1, 1, 1, 
				2, 1, 3, 1);
		setCell(2, 1, 1, 
				0, 1, 0, 1);
		
		setCell(0, 2, 2, 
				0, 0, 1, 1);
		setCell(1, 2, 2,
				3, 0, 1, 0);
		setCell(2, 2, 2,
				0, 1, 1, 0);
	}

	public Cell getCell(int x, int y){
		return this.LAYOUT[x][y];
	}
	public int getCellType(int x, int y){
		return this.LAYOUT[x][y].getType();
	}
	public int getWidth(){
		return this.WIDTH;
	}
	public int getHeight(){
		return this.HEIGHT;
	}

	private void setCell(int x, int y, int type){
		int north = 0, south = 0, east = 0, west = 0, dirt = 0;
		this.LAYOUT[x][y] = new Cell(x, y, type, north, east, south, west, dirt);
	}

	private void setCell(int x, int y, int type, int n, int e, int s, int w){
		this.LAYOUT[x][y] = new Cell(x, y, type, n, e, s, w, 0);
	}

	private void setCell(int x, int y, int type, int north,  int east, int south, int west,  int dirt, int chargingStation){
		this.LAYOUT[x][y] = new Cell(x,y,type, north, east, south, west,  dirt, chargingStation);
	}

	private void setWidth(int x){
		if(x < 0){
			throw new IllegalArgumentException("Width can not be less than zero");
		}
		this.WIDTH = x;
	}

	private void setHeight(int y){
		if(y < 0){
			throw new IllegalArgumentException("Width can not be less than zero");
		}
		this.HEIGHT = y;
	}

	private void buildLayoutFromXMLFile(File xFile){
		//Using a hashMap to keep track of the integer equivalents for the xml file inputs
		HashMap<String, Integer> definitions = new HashMap<String, Integer>();

		definitions.put("false", 0);
		definitions.put("true", 1);
		definitions.put("Bare Floor", 0);
		definitions.put("Low-Pile Carpet", 1);
		definitions.put("High-Pile Carpet", 2);
		definitions.put("Stairs Cell", 3);
		definitions.put("Clear", 0);
		definitions.put("Wall", 1);
		definitions.put("Door (open)", 2);
		definitions.put("Door (closed)", 3);
		definitions.put("Stairs Border", 4);

		try {
			// inputing from xml file using Document libraries
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document layout = dBuilder.parse(xFile);

		    layout.getDocumentElement().normalize();

		    // following snippets to get size of grid
		    NodeList rows = layout.getElementsByTagName("row");// list of all the nodes (rows)
		    int numberOfRows = rows.getLength();
		    setHeight(numberOfRows);

		    NodeList cellCount = layout.getElementsByTagName("cell");
		    int numberOfColumns = cellCount.getLength()/numberOfRows;
		    setWidth(numberOfColumns);

		    buildPlainLayout(0);

		    // method prints out incoming xml file to show that proper data is being retrieved,
		    // but System.outs can be commented out or deleted. best to leave until done

		    //System.out.println("<" + layout.getDocumentElement().getNodeName() + ">");//to show functionality

			  for (int i = 0; i < rows.getLength(); i++){ //iterate through all the rows
		    	Node rNode = rows.item(i);
		    	if (rNode.getNodeType() == Node.ELEMENT_NODE){
			    	Element row = (Element) rNode;
			    	String y = row.getAttribute("y");
			    	//System.out.println("\t<" + rNode.getNodeName() + " y=\""+ y + "\">");//to show functionality

			    	NodeList cells = row.getElementsByTagName("cell");
			    	for(int j = 0; j < cells.getLength(); j++){ // iterate through all the cells in each row
			    		Node cNode = cells.item(j);
			    		if (cNode.getNodeType() == Node.ELEMENT_NODE){
			    			Element cell = (Element) cNode;
			    			String x = cell.getAttribute("x");
			    			String chargingStation = cell.getAttribute("chargingStation");
			    			String dirt = cell.getAttribute("dirt");
			    			//System.out.println("\t\t<" + cNode.getNodeName() + "  x=\"" + x + "\" chargingStation=\"" + chargingStation + "\" dirt=\""+ dirt + "\">");//to show functionality

			    			String type = cell.getElementsByTagName("type").item(0).getTextContent().trim();
			    			//System.out.println("\t\t\t<type>" + type + "<type/>");//to show functionality

			    			Element border = (Element) cell.getElementsByTagName("borders").item(0);
			    			String north = border.getAttribute("north");
			    			String south = border.getAttribute("south");
			    			String east = border.getAttribute("east");
			    			String west = border.getAttribute("west");
			    			//System.out.println("\t\t\t<borders north=\"" + north + "\" south=\"" + south + "\" east=\"" + east + "\" west=\"" + west + "\"/>");//to show functionality

			    			setCell(Integer.parseInt(x),
					    			Integer.parseInt(y),
					    			definitions.get(type),
					    			definitions.get(north),
					    			definitions.get(east),
					    			definitions.get(south),
					    			definitions.get(west),
					    			Integer.parseInt(dirt),
					    			definitions.get(chargingStation));
			    		}
			    		//System.out.println("\t\t</cell>");//to show functionality

			    	}

		    	}//System.out.println("\t</row>");//to show functionality
		    }

			//System.out.println("</Map>");//to show functionality

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }


	}

	public static void printMap(Map map){

		System.out.println("\n\nInteger representation of Map Layout (numbers are cell types):\n");
		System.out.println("=========================================");

		for (int i = 0; i < map.getHeight(); i ++) {
			for (int j = 0; j < map.getWidth(); j++) {
				Cell cell = map.getCell(j, i);
				System.out.print("| " + cell.getType() + " ");
			}
			System.out.println("|");
			System.out.println("=========================================");
		}
	}

	public static void main(String[] args) {
		
		// importing a file does not work for me - Clark
		
		//File sampleLayoutXMLFile = new File("MapLayout.xml");
		//Map testMap3 = new Map(sampleLayoutXMLFile);
		//printMap(testMap3);
	}
}
