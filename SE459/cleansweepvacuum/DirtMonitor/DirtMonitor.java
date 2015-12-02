package DirtMonitor;

public class DirtMonitor {
	private static int MAX_CAPACITY = 50;
	private boolean emptyMe;
	private int currDirtCapacity;
	
	public DirtMonitor() {
		emptyBag();
		setEmptyMe(false);
	}
	
	public boolean getEmptyMe(){
		return emptyMe;
	}	
	
	private void setEmptyMe(boolean flag){
		emptyMe = flag;
	}	
  
   public void emptyBag(){
	   currDirtCapacity = 0;
	   setEmptyMe(false);
   }

	public int getCurrDirtCapacity(){
		return currDirtCapacity;
	}
	
	public void setCurrDirtCapacity(int setCurr){
		currDirtCapacity = setCurr;
	}
	
	public boolean isFull() {
		return (this.currDirtCapacity >= MAX_CAPACITY);
	}
	
	public Boolean collectDirt() 
	{
		if(this.isFull() == false)
		{
			currDirtCapacity++;;
			return true;
		}
		else
		{
			System.out.println("Clean Sweep Is Full!");
			return false;
		}
	}
}
