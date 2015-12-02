package Console;

import java.util.Scanner;

import vacuum.Vacuum;

public class Console {
	private static Vacuum vacuum;
	private static Scanner keyboard;
	
	
	private static void startListening(){
		System.out.println("The world is now listening for commands, type help for a list...");
		keyboard = new Scanner(System.in);
		prompt();
	}
	
	private static void stopListening(){
		keyboard.close();
	}
	
	private static void prompt(){
		String input = keyboard.nextLine();
		checkCommand(input.toLowerCase());
	}
	
	private static void checkCommand(String command){
		boolean doNotPrompt = false;
		switch(command){
			case("on"):
				vacuum = new Vacuum();
				break;
				
			case("off"):
				vacuum.turnOff();
				break;
				
			case("quit"):
				stopListening();
				System.out.println("The world is now shutting down, thanks for playing!");
				doNotPrompt = true;
				break;
				
			case("help"):
				printHelp();
				break;
				
			default:
				System.out.println("I did not recognize that command, please try again");
				break;
		}
		if(doNotPrompt){
			return;
		} else {
			prompt();
		}
	}
	
	private static void printHelp(){
		System.out.println("Type 'on' to turn on the vacuum.");
		System.out.println("Type 'off' to turn off the vacuum.");
		System.out.println("Type 'quit' to exit the program.");
		System.out.println("Type 'help' to print this message.");
	}
	
	public static void main(String[] args) {
		startListening();
	}
}
