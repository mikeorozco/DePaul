import java.io.File;

import Layout.*;
import vacuum.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class main extends Application{
	
	int CELL_SIZE = 75;
	int CELL_GAP = 2;
	int MAP_PADDING = 10;
	int BORDER_WIDTH = 5;
	
	// surface type colors
	Color BARE_FLOOR_COLOR = Color.web("eee");
	Color LOW_PILE_COLOR = Color.web("ccc");
	Color HIGH_PILE_COLOR = Color.web("aaa");
	// TODO: find a surface for stairs
	
	// border colors
	Color OPEN_DOOR_COLOR = Color.GREEN;
	Color CLOSED_DOOR_COLOR = Color.YELLOW;
	Color WALL_COLOR = Color.BLACK;
	
	@Override
	public void start(Stage primaryStage){
	
		// generated layout to be converted to GUI
		File sampleLayoutXMLFile = new File("MapLayout.xml");// added to see functionality with xml file
		Map layout = new Map(sampleLayoutXMLFile);           // <==
		//Map layout = new Map();
				
		// main container for GUI: has areas for top/bottom/left/right/center
		BorderPane root = new BorderPane();

		// container for map GUI
		GridPane map = new GridPane();
		map.setPadding(new Insets(MAP_PADDING));
		map.setHgap(CELL_GAP);
		map.setVgap(CELL_GAP);
		
		// array of cells to be added to map GUI
		StackPane cells[][] = new StackPane[layout.getWidth()][layout.getHeight()];
		
		for(int row=0; row<layout.getHeight(); row++){
			for(int col=0; col<layout.getWidth(); col++){
				// create the cell's appearance
				cells[row][col] = buildCell(layout.getCell(row,col));
				// this is the correct way to have it
				// https://docs.oracle.com/javafx/2/api/javafx/scene/layout/GridPane.html#add(javafx.scene.Node, int, int)
				map.add(cells[row][col], row, col);
			}
		}
		
		
		root.setCenter(map);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Clean Sweep Vacuum");
		primaryStage.show();
	}
	
	private StackPane buildCell(Cell c){
		
		// query the map object to find out information about cell
		// build out the stack pane for that cell based on this info
		// * surface type
		// * borders
		
		int north_border = c.getNorth();
		int east_border = c.getEast();
		int south_border = c.getSouth();
		int west_border = c.getWest();
		
		Region floor = generateFloorSurfacePane(c.getType(), north_border, east_border, south_border, west_border);
		Region closedDoor = generateClosedDoorPane(north_border, east_border, south_border, west_border);
		Region openDoor = generateOpenDoorPane(north_border, east_border, south_border, west_border);
		
		StackPane sp = new StackPane(closedDoor, openDoor, floor);
		sp.setBackground(new Background(new BackgroundFill(WALL_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
		return sp;
	}
	
	private Region generateFloorSurfacePane(int t, int n, int e, int s, int w){
		Region floor = new Region();
		floor.setPrefHeight(CELL_SIZE);
		floor.setPrefWidth(CELL_SIZE);
		

		Color floorSurface = Color.web("fff");
		switch(t){
			case(0):
				floorSurface = BARE_FLOOR_COLOR;
				break;
			case(1):
				floorSurface = LOW_PILE_COLOR;
				break;
			case(2):
				floorSurface = HIGH_PILE_COLOR;
				break;
			case(3):
				// TODO: replace with stair color
				floorSurface = Color.web("f00");
				break;
			default:
				System.out.println("Unsupported floor type");
				break;
		}
		
		int floor_n = 0, floor_e = 0, floor_s = 0, floor_w = 0;
		if(n > 0){
			floor_n = BORDER_WIDTH;
		}
		if(e > 0){
			floor_e = BORDER_WIDTH;
		}
		if(s > 0){
			floor_s = BORDER_WIDTH;
		}
		if(w > 0){
			floor_w = BORDER_WIDTH;
		}
		Insets floorGap = new Insets(floor_n, floor_e, floor_s, floor_w);
		floor.setBackground(new Background(new BackgroundFill(floorSurface, CornerRadii.EMPTY, floorGap)));
		
		return floor;
	}
	
	private Region generateOpenDoorPane(int n, int e, int s, int w){
		Region openDoor = new Region();
		openDoor.setPrefHeight(CELL_SIZE);
		openDoor.setPrefWidth(CELL_SIZE);
		
		
		int north_gap = BORDER_WIDTH, east_gap = BORDER_WIDTH, south_gap = BORDER_WIDTH, west_gap = BORDER_WIDTH;
		
		if(n == 2){ north_gap = 0; }
		if(e == 2){ east_gap = 0; }
		if(s == 2){ south_gap = 0; }
		if(w == 2){ west_gap = 0; }
		
		Insets gap = new Insets(north_gap, east_gap, south_gap, west_gap);
		openDoor.setBackground(new Background(new BackgroundFill(OPEN_DOOR_COLOR, CornerRadii.EMPTY, gap)));
		
		return openDoor;
	}
	
	private Region generateClosedDoorPane(int n, int e, int s, int w){
		Region closedDoor = new Region();
		closedDoor.setPrefHeight(CELL_SIZE);
		closedDoor.setPrefWidth(CELL_SIZE);
		
		
		int north_gap = BORDER_WIDTH, east_gap = BORDER_WIDTH, south_gap = BORDER_WIDTH, west_gap = BORDER_WIDTH;
		
		if(n == 3){ north_gap = 0; }
		if(e == 3){ east_gap = 0; }
		if(s == 3){ south_gap = 0; }
		if(w == 3){ west_gap = 0; }
		
		Insets gap = new Insets(north_gap, east_gap, south_gap, west_gap);
		closedDoor.setBackground(new Background(new BackgroundFill(CLOSED_DOOR_COLOR, CornerRadii.EMPTY, gap)));
		
		return closedDoor;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
