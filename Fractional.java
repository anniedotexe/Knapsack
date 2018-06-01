/**
 * Author:		Annie Wu
 * Project:		2
 * 
 * Class:		CS 331 – Design and Analysis of Algorithms
 * Instructor:	Tannaz R.Damavandi
 * 
 * Date:		1 June 2018
 * 
 * Purpose:		This program contains all functions needed to solve a
 * 				fractional knapsack.
 * 
 */

import java.util.*;

public class Fractional {

	private static String maxProfit = "\nMax profit: ";
	
	//arraylist for sorted objectList
	private static ArrayList<KnapsackObject> sortedObjectList = new ArrayList<KnapsackObject>();
	//current object
	private static KnapsackObject object;
	//current total weight/capacity of the knapsack
	private static int currentWeight;
	//ratio of current object
	private static double currentRatio;
	//ratio of current sorted object in the list
	private static double sortedCurrentRatio;
	//keep track of total profit
	private static int currentProfit;
	//boolean for if the object has been added to arraylist
	private static boolean ADDED;
	//boolean for if the object is in the final solution knapsack
	private static boolean[] finalSolution;
	
	/**
	 * This is the function solveFractional.
	 * It solves the fractional knapsack to get the optimal solution.
	 */	
	public static void solveFractional(ArrayList<KnapsackObject> objectList, int maxCapacity) {
		//call function to sort by profit/weight ratio
		sortByRatios(objectList);
		
		//initialize final solution array
		finalSolution = new boolean[objectList.size()];
		//initialize current weight to 0
		currentWeight = 0;
		//initialize current profit to 0
		currentProfit = 0;
		
		//while the current total weight is less than the max capacity and the list is not empty
		while(currentWeight < maxCapacity && !sortedObjectList.isEmpty()) {
			//for every object in the sorted object list
			for (int i=0; i<sortedObjectList.size(); i++) {
				//the current object i is taken from the sorted object list
				object = sortedObjectList.remove(i);
	
	            //if the object's weight + the current weight of the knapsack <= max capacity
	            if(object.weight + currentWeight <= maxCapacity) {
	            	//this object index is a part of the final solution knapsack
	            	//object number -1 because array indexes are 0-19, while object numbers are 1-20
	                finalSolution[object.object-1] = true;
	                //the profit of the current object is added to the total profit
	                currentProfit += object.profit;
	                //the weight of the current object is added to the total weight of the knapsack
	                currentWeight += object.weight;
	            }
	        }
		}
		//print objects in knapsack
        for (int i=0; i<objectList.size(); i++) {
        	//if the object is in the final solution knapsack
        	if (finalSolution[i] == true) {
        		//print the object and its weight and profit
        		System.out.print(objectList.get(i).toString());
        	}
        }
		//print max profit
		System.out.println(maxProfit + currentProfit);
		//System.out.print(currentWeight);
	}
	
	/**
	 * This is the function sortByRatios.
	 * It will sort the profit/weight ratios of the objects in non-increasing order.
	 * @return 
	 */
	public static ArrayList<KnapsackObject> sortByRatios(ArrayList<KnapsackObject> objectList) {
		
		//for all objects in the list
        for(int i = 0; i < objectList.size(); i++) {
            //object has not been added
        	ADDED = false;
            //current object in the list
        	object = objectList.get(i);
        	//current ratio is the ratio of the current profit and weight
            currentRatio = (double)object.profit/object.weight;

            //for all objects in the sorted list
            for(int j = 0; j < sortedObjectList.size(); j++) {
                //the object to be sorted is the current index j
            	KnapsackObject sortedObject = sortedObjectList.get(j);
                //ratio of current object to be sorted
            	sortedCurrentRatio = (double)sortedObject.profit/sortedObject.weight;

            	//if the current ratio is greater than the ratio to be sorted
                if(currentRatio > sortedCurrentRatio) {
                	//add it to the sorted object list in index j
                    sortedObjectList.add(j, object);
                    //object has been added
                    ADDED = true;
                    break;
                }
            }

            //if the object has not been added
            if(!ADDED) {
            	//add it to the list
                sortedObjectList.add(object);
            }
        }
        //return the list of sorted objects
		return sortedObjectList;
	}

}
