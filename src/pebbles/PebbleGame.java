package pebbles;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**The main PebbleGame class that handles all of the objects
 * and controls the flow of the game.
 * 
 * @date 14/10/15
 * @author 35092 and 8744
 */

public class PebbleGame
{
	private int numOfPlayers;
	private Player[] p;
	private BagPair[] bags;
	
	public PebbleGame(int numOfPlayers, ArrayList<Integer> bag1, ArrayList<Integer> bag2, ArrayList<Integer> bag3)
	{
		this.numOfPlayers = numOfPlayers;
		
		this.p = new Player[numOfPlayers];
		
		this.bags = new BagPair[3];
		this.bags[0] = new BagPair(bag1);
		this.bags[1] = new BagPair(bag2);
		this.bags[2] = new BagPair(bag3);
	}
	
	/**Nested class that controls the players of PebbleGame.
	 * Acts as a thread.
	 * 
	 * @date 18/10/15
	 * @author 35092 and 8744
	 */
	
	public class Player extends Thread
	{	
		private ArrayList<Integer> hand;
		
		public Player()
		{
			hand = new ArrayList<Integer>();
		}
		
		public void run()
		{
			System.out.println("Hi from " + Thread.currentThread().getName());
		}
	}
	
	public void play()
	{	
		ExecutorService s = Executors.newFixedThreadPool(numOfPlayers);
		
		for(int i = 0; i < numOfPlayers; i++)
		{
			p[i] = new Player();
			s.execute(p[i]);
		}
	}
	
	public static void main(String[] args)
	{	
		//Create a scanner so we can read the command-line input
    	Scanner scanner = new Scanner(System.in);
    	
    	//Integer that stores the number of players
    	int numberOfPlayers = 0;
    	
    	//Arrays that store the integer weights of the pebbles in the bags
    	ArrayList<Integer> bag1 = null;
    	ArrayList<Integer> bag2 = null;
    	ArrayList<Integer> bag3 = null;
		
		//Boolean condition that is loops through input until valid input is received
		boolean canContinue = false;
			
		System.out.println("Welcome to Pebble Game!");
		
		//Loops through until a valid input is given
		while(!canContinue) 
		{
			try
			{
            			//Prompt for number of players
            			System.out.print("Number of players? ");
				
				String input = scanner.nextLine();
				
				//Testing program exit condition
				if(input.equals("X"))
				{
					System.out.println("Exiting...");
					System.exit(0);
				}
				
            			//Attempts to parse line to an integer
				numberOfPlayers = Integer.parseInt(input);
            
				//Valid input given; can move on
            			canContinue = true;
			}   
			//If not integer input then error is display
			catch(NumberFormatException e) 
			{
				System.out.println("Only integers accepted. Please try again.");
			}
		
		}
		
		canContinue = false;
		
		while(!canContinue)
		{
			try
			{
				
            			//Prompt for no.players
            			System.out.print("Please enter the path for csv file 1: ");
            	
            			String input = scanner.nextLine();
            	
				if(input.equals("X"))
				{
					System.out.println("Exiting...");
					System.exit(0);
				}
            	
            			bag1 = InputUtil.loadFile(input, numberOfPlayers);
            
            			canContinue = true;
			}   
			catch(IOException e)
			{
				System.out.println("Error loading file.");
			}
			catch(InvalidPathException e) 
			{
				System.out.println("Path name not valid.");
			}
			catch(NumberFormatException e)
			{
				System.out.println("File supplied does not exclusively contain integers.");
			}
			catch(NegativePebbleWeightException e)
			{
				System.out.println("Warning: File contains negative value integer(s). Continue anyway? (Y/N): ");
				if(scanner.nextLine().equals("Y"))
					canContinue = true;
			}
			catch(InsufficientNumberOfPebblesException e)
			{
				System.out.println("File contains an insufficient number of pebbles for the number of players supplied.");
			}
		}
		
		canContinue = false;
		
		while(!canContinue)
		{
			try
			{
				
            			//Prompt for no.players
            			System.out.print("Please enter the path for csv file 2: ");
            	
            			String input = scanner.nextLine();
            	
				if(input.equals("X"))
				{
					System.out.println("Exiting...");
					System.exit(0);
				}
            	
            			bag2 = InputUtil.loadFile(input, numberOfPlayers);
            
            			canContinue = true;
			}   
			catch(IOException e)
			{
				System.out.println("Error loading file.");
			}
			catch(InvalidPathException e) 
			{
				System.out.println("Path name not valid.");
			}
			catch(NumberFormatException e)
			{
				System.out.println("File supplied does not exclusively contain integers.");
			}
			catch(NegativePebbleWeightException e)
			{
				System.out.println("Warning: File contains negative value integer(s). Continue anyway? (Y/N): ");
				if(scanner.nextLine().equals("Y"))
					canContinue = true;
			}
			catch(InsufficientNumberOfPebblesException e)
			{
				System.out.println("File contains an insufficient number of pebbles for the number of players supplied.");
			}
		}
		
		canContinue = false;
		
		while(!canContinue)
		{
			try
			{
				
            			//Prompt for no.players
            			System.out.print("Please enter the path for csv file 3: ");
            			
            			String input = scanner.nextLine();
            	
				if(input.equals("X"))
				{
					System.out.println("Exiting...");
					System.exit(0);
				}
            	
            			bag3 = InputUtil.loadFile(input, numberOfPlayers);
            
            			canContinue = true;
			}   
			catch(IOException e)
			{
				System.out.println("Error loading file.");
			}
			catch(InvalidPathException e) 
			{
				System.out.println("Path name not valid.");
			}
			catch(NumberFormatException e)
			{
				System.out.println("File supplied does not exclusively contain integers.");
			}
			catch(NegativePebbleWeightException e)
			{
				System.out.println("Warning: File contains negative value integer(s). Continue anyway? (Y/N): ");
				if(scanner.nextLine().equals("Y"))
					canContinue = true;
			}
			catch(InsufficientNumberOfPebblesException e)
			{
				System.out.println("File contains an insufficient number of pebbles for the number of players supplied.");
			}
		}
		
		new PebbleGame(numberOfPlayers, bag1, bag2, bag3).play();
	}
		
}
