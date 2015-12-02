package Sensor;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;



import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import Movement.MovementObserver;
import Sensor.*;


import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XMLParser implements SensorSource {
		
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	static DocumentBuilder builder;
	static Document floor_plan;
	static NodeList floor_List;
	static NodeList cell_List;
	static File xmlFile;
	
	public static void openFile(){
		try {
			xmlFile = new File ("MapLayout3.xml");
			builder = factory.newDocumentBuilder();
			floor_plan = builder.parse(xmlFile);
			System.out.println("File open");
			
		} catch(Exception e){
			System.out.println("Failed");
			e.printStackTrace();
		}
	}
	
	public static int getAll_X(){
		int x=0; 
		openFile();
		
		System.out.println("Root element: " + floor_plan.getDocumentElement().getNodeName() +"\n" );
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < floor_List.getLength(); i++){
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");
			
			if(node.getNodeType() == Node.ELEMENT_NODE){	
				for(int j = 0; j < cell_List.getLength(); j++ ){
					Node nodeIn = cell_List.item(j);
					System.out.println("Current Element: " + nodeIn.getNodeName() + "\n");
					Element eElementIn  =  (Element) nodeIn;
					String x_str = eElementIn.getAttribute("x");
					System.out.println("x: " + x_str);
					x = Integer.valueOf(String.valueOf(x_str)).intValue();
				}
			}
		}
		return x;
	}
	
	public static int getAll_Y(){
		
		int y = 0; 
		openFile();
		
		System.out.println("Root element: " + floor_plan.getDocumentElement().getNodeName() +"\n" );
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
	    floor_plan.getElementsByTagName("cell");
		for (int i=0; i < floor_List.getLength(); i++){
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");
			if(node.getNodeType() == Node.ELEMENT_NODE){
				for(int j = 0; j < cell_List.getLength(); j++ ){
					Node nodeIn = cell_List.item(j);
					System.out.println("Current Element: " + nodeIn.getNodeName() + "\n");
					Element eElementIn  =  (Element) nodeIn;
					String y_str = eElementIn.getAttribute("y");
					System.out.println("y: " + y_str);
					y = Integer.valueOf(String.valueOf(y_str)).intValue();
				}
			}
		}
		return y;
	}
	
	public static int getFirst_X(){
		
		int x = 0; 
		openFile();
		
		System.out.println("Root element: " + floor_plan.getDocumentElement().getNodeName() +"\n" );
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < floor_List.getLength(); i++){
			
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Node nodeIn = cell_List.item(0);
				System.out.println("Current Element: " + nodeIn.getNodeName() + "\n");
				Element eElementIn  =  (Element) nodeIn;
				String x_str = eElementIn.getAttribute("x");
				System.out.println("x: " + x_str);
				x = Integer.valueOf(String.valueOf(x_str)).intValue();	
			}
			
		}
		return x;
	}

	public static int getFirst_Y(){
		
		int y = 0; 
		openFile();
		
		System.out.println("Root element: " + floor_plan.getDocumentElement().getNodeName() +"\n" );
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < floor_List.getLength(); i++){
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");
		
			if(node.getNodeType() == Node.ELEMENT_NODE){		
				Node nodeIn = cell_List.item(0);
				System.out.println("Current Element: " + nodeIn.getNodeName() + "\n");
				Element eElementIn  =  (Element) nodeIn;
				String y_str = eElementIn.getAttribute("y");
				System.out.println("y: " + y_str);
				y = Integer.valueOf(String.valueOf(y_str)).intValue();
			
			}
		}
		return y;
	}

	public static String getSurfaceTypeFromCoordinates(int coord_x, int coord_y){
		String surf = "";
		openFile();
		
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < floor_List.getLength(); i++){
			
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Node nodeIn = cell_List.item(0);
				Element eElementIn  =  (Element) nodeIn;
				if( (Integer.valueOf(String.valueOf(eElementIn.getAttribute("y"))).intValue()== coord_y) &&
						(Integer.valueOf(String.valueOf(eElementIn.getAttribute("x"))).intValue()== coord_x)	){
					return String.valueOf(eElementIn.getAttribute("type")) ; 
				}
			}
		}
		return surf;
	}

	public static int getDirtFromCoordinates(int coord_x, int coord_y){
		int dirt=0;
		openFile();
		
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < floor_List.getLength(); i++){
			
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");
				
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Node nodeIn = cell_List.item(0);
				Element eElementIn  =  (Element) nodeIn;
				
				if( (Integer.valueOf(String.valueOf(eElementIn.getAttribute("y"))).intValue()== coord_y) &&
						(Integer.valueOf(String.valueOf(eElementIn.getAttribute("x"))).intValue()== coord_x)	){
					return Integer.valueOf(String.valueOf(eElementIn.getAttribute("dirt"))).intValue() ; 
				}
				
			}
		}
		return dirt;
	}
	

	public static List<String> getPathsFromCoordinates(int coord_x, int coord_y){
		List <String> obstacles = new  ArrayList<String>();
		openFile();
		
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < floor_List.getLength(); i++){
			
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Node nodeIn = cell_List.item(0);
				Element eElementIn  =  (Element) nodeIn;
				
				if( (Integer.valueOf(String.valueOf(eElementIn.getAttribute("y"))).intValue()== coord_y) &&
						(Integer.valueOf(String.valueOf(eElementIn.getAttribute("x"))).intValue()== coord_x)	){
							obstacles.add(0,String.valueOf(eElementIn.getAttribute("north")));
							obstacles.add(1,String.valueOf(eElementIn.getAttribute("south")));
							obstacles.add(2,String.valueOf(eElementIn.getAttribute("east")));
							obstacles.add(3,String.valueOf(eElementIn.getAttribute("west")));
					return obstacles ; 
				}
			}
		}
		return null;
	}
	
 	public static boolean isChargingStation(int coord_x, int coord_y){
		boolean charg_station = false;
		openFile();
		
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < floor_List.getLength(); i++){
			
			Node node = floor_List.item(i);
			System.out.println("Current Element: " + node.getNodeName() + "\n");		
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Node nodeIn = cell_List.item(0);
				Element eElementIn  =  (Element) nodeIn;
				
				if( (Integer.valueOf(String.valueOf(eElementIn.getAttribute("y"))).intValue()== coord_y) &&
						(Integer.valueOf(String.valueOf(eElementIn.getAttribute("x"))).intValue()== coord_x)	){
					if(String.valueOf(eElementIn.getAttribute("chargingStation"))== "true"){
						charg_station = true;
					}
					return charg_station ; 
				}		
			}
		}
		
		return charg_station;

	}
 	
 	public List<SensorPoint> LoadAllCells(){
		
 		ArrayList<SensorPoint> results = new ArrayList<SensorPoint>();
		openFile();
		
		floor_plan.getDocumentElement().normalize();
		floor_List = floor_plan.getElementsByTagName("row");
		cell_List= floor_plan.getElementsByTagName("cell");
		
		for (int i=0; i < cell_List.getLength(); i++){
				Node nodeIn = cell_List.item(i);
				Element eElementIn  =  (Element) nodeIn;

				try	{
					//parses the xml elements and adds it to the list
					results.add(XMLParser.GetSensorPointFrom(eElementIn));	
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
		}
		return results;
	}
 	
 	private static SensorPoint GetSensorPointFrom(Element cellElement) {
		
		SensorPoint sc = new SensorPoint();
		
		sc.setY(Integer.valueOf(String.valueOf(cellElement.getAttribute("y"))).intValue());
		sc.setX(Integer.valueOf(String.valueOf(cellElement.getAttribute("x"))).intValue());
		sc.setDirt(Integer.valueOf(String.valueOf(cellElement.getAttribute("dirt"))).intValue());
		sc.setFloorType(FloorType.FromString(String.valueOf(cellElement.getAttribute("type"))));
		sc.setNorth(ObstacleType.FromString(String.valueOf(cellElement.getAttribute("north"))));
		sc.setSouth(ObstacleType.FromString(String.valueOf(cellElement.getAttribute("south"))));
		sc.setEast(ObstacleType.FromString(String.valueOf(cellElement.getAttribute("east"))));
		sc.setWest(ObstacleType.FromString(String.valueOf(cellElement.getAttribute("west"))));
		sc.setChargingStation(Boolean.valueOf(String.valueOf(cellElement.getAttribute("chargingStation").equals("true"))));
		System.out.println(sc);
		
		return sc;	
   }
}

