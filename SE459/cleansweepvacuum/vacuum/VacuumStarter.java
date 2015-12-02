package vacuum;

public class VacuumStarter {

	public static void main(String[] args) {
		Vacuum vs = new Vacuum();
		
		try{
		//roam around a floor
			vs.getController().roam(100);
		} catch(Exception e) {
			
			System.out.println(e);
		}
		System.out.println("**********************************");
		System.out.println("Movement Complete! The Vacuum Report is Below:");
		System.out.print(vs.getReport());
	}

}
