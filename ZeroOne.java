import java.util.ArrayList;

/**
 * Author:		Annie Wu
 * Project:		2
 * 
 * Class:		CS 331 – Design and Analysis of Algorithms
 * Instructor:		Tannaz R. Damavandi
 * 
 * Date:		1 June 2018
 * 
 * Purpose:		This program contains all functions needed to solve a
 * 			0/1 knapsack.
 * 
 */

public class ZeroOne {

	private static String maxProfit = "\nMax profit: ";
	
	//current weight
	private static int currentWeight;
	//current profit
	private static int currentProfit;
	//boolean for if the object is in the final solution knapsack
	private static boolean[] finalSolution;
	
	public static void solveZeroOne(ArrayList<KnapsackObject> objectList, int maxCapacity) {
		//width of the matrix
		int width = maxCapacity +1;
		//height of the matrix
		int height = objectList.size() +1;
		//matrix
		int[][] matrix = new int[width][height];
		//initialize final solution array
		finalSolution = new boolean[objectList.size()];		
		//offset in the matrix
		int offset;
		
		//initialize current weight to 0
		currentWeight = 0;
		//initialize current profit to 0
		currentProfit = 0;
		
		//set first column of the matrix to all zero
		for (int i=0; i<width; i++) {
			matrix[i][0] = 0;
		}
		
		//set first row of the matrix to all zero
		for (int j=0; j<height; j++) {
			matrix[0][j] = 0;
		}
		
		//for each object
		for(int i = 1; i < height; i++) {
			//current weight is the previous object's weight
			currentWeight = objectList.get(i-1).weight;
			//current profit is the previous object's profit
			currentProfit = objectList.get(i-1).profit;
        
			//matrix[i][j] = max(matrix[i][j-1], matrix[remainingCapacity][i-1] + profit)
			for(int matrixCapacity = 1; matrixCapacity < width; matrixCapacity++) {
				//set matrix at this place equal to the previous object
				matrix[matrixCapacity][i] = matrix[matrixCapacity][i - 1];
				
				//if the current weight is <= the max capacity of the knapsack
				if(currentWeight <= matrixCapacity) {
					//offset it by the difference of max capacity and the current weight
					offset = matrixCapacity - currentWeight;
					//get the maximum profit
					matrix[matrixCapacity][i] = Math.max(matrix[matrixCapacity][i], 
									     matrix[offset][i - 1] + currentProfit);
				}
			}
		}
       
	//reset total weight
        currentWeight = 0;
        //reset profit
        currentProfit = 0;

        /*
         * Trace back to get the objects that will be put into the knapsack
         */
        //trace width of matrix starting with width-1 = max capacity
        int traceWidth = width - 1;
        //trace height of matrix starting with height-1 = number of objects
        int traceHeight = height - 1;
        
        //while they are greater than 0
        while(traceHeight > 0 && traceWidth > 0) {
        	//if the profit is not equal to the profit above it
            if(matrix[traceWidth][traceHeight] != matrix[traceWidth][traceHeight - 1]) {
                //get the object
            	KnapsackObject object = objectList.get(traceHeight - 1);
            	//it is part of the final solution knapsack
            	finalSolution[traceHeight - 1] = true;

                //decrement the width counter by item weight
                traceWidth -= object.weight;

                //update weight
                currentWeight += object.weight;
                //update profit
                currentProfit += object.profit;                   
            }
            traceHeight--;
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
}
