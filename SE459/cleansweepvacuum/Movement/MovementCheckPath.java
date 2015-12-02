package Movement;

//The interface provides the possible moves in a given checked path
public interface MovementCheckPath {

	CheckPath CheckPoint(Point point); //constructor to checkpoint 

	int GetWeightedCostToOrigin(Point fromPoint); //calculate the cost to get to origin

}
