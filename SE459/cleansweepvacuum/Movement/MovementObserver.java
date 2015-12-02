package Movement;

//Interface implemented by objects that will be notified when an Mover object moves to a new point

public interface MovementObserver {

	void didmove(Point movedTo);
	
}
