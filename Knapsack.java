/**
 * Author:		Annie Wu
 * Project:		2
 * 
 * Class:		CS 331 – Design and Analysis of Algorithms
 * Instructor:		Tannaz R. Damavandi
 * 
 * Date:		1 June 2018
 * 
 * Purpose:		This program contains all user interactions with the program.
 * 
 */

import java.io.*;
import java.util.*;

public class Knapsack {
	
	private static String whichTask = "\nWhich task do you want to run?"
						+ "\n 1. Fractional Knapsack"
						+ "\n 2. 0/1 Knapsack"
						+ "\n 3. Exit\n";
	private static String fileNotFound = "\nError: The file could not be found.";
	private static String error = "\nError: Invalid Input. Please enter a number.";
	private static String exiting = "\nThe program has ended.";
	
	//list of objects in knapsack
	private static ArrayList<KnapsackObject> objectList;
 	//max capacity of the knapsack
	private static int maxCapacity;
    	//number of objects
	private static int nObjects;
	
	/**
	 * This is the function readFile.
	 * It will read the input file for...
	 *  - max capacity
	 *  - number of objects
	 *  - each objects weight and profit
	 */
	public static void readFile(String fileName) throws FileNotFoundException {
		//new arraylist for list of objects
		objectList = new ArrayList<KnapsackObject>();

		//new file with the fileName
		File file = new File(fileName);
		//file input stream to read the file
		FileInputStream read = new FileInputStream(file);
		//scanner will read from the file input stream
		Scanner scan = new Scanner(read);
		
		//first integer is max capacity
		maxCapacity = scan.nextInt();
		//next integer is number of objects
		nObjects = scan.nextInt();
		
		//add object number, its weight, and its profit to the object list
		for(int i = 0; i < nObjects; i++) {
            objectList.add(new KnapsackObject(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
		//close scanner
		scan.close();
	}

	public static void main (String args[]) {
		try {
			//read input.txt file
			readFile("input.txt");
		}
		catch (FileNotFoundException e) {
			System.out.println(fileNotFound);
		}
		
		//create scanner for keyboard input
		Scanner kb = new Scanner(System.in);
		//boolean to run/exit program
		boolean run = true;

		while (run) {
			//print string for which task to run
			System.out.print(whichTask);
			//next integer to be input is the choice number
			String choice = kb.next();
			//switch case for choice of task to run
			switch (choice) {
				// fractional
				case "1":
					//call function to solve fractional knapsack
					Fractional.solveFractional(objectList, maxCapacity);
					break;
				// 0/1
				case "2":
					//call function to solve 0/1 knapsack
					ZeroOne.solveZeroOne(objectList, maxCapacity);
					break;
				// exit
				case "3":
					//set run to false to exit while loop
					run = false;
					//print exit message
					System.out.print(exiting);
					break;
				default:
					System.out.println(error);
					break;
			}
		}
		//close keyboard scanner
		kb.close();
	}
}
