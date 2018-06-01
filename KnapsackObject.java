/**
 * Author:		Annie Wu
 * Project:		2
 * 
 * Class:		CS 331 – Design and Analysis of Algorithms
 * Instructor:	Tannaz R.Damavandi
 * 
 * Date:		1 June 2018
 * 
 * Purpose:		This is the class that contains each object and their 
 * 				respective weight and profit.
 * 
 */

public class KnapsackObject {
	
	public int object;
	public int weight;
	public int profit;
	
	/**
	 * This is the constructor to create an object with its 
	 * object number, weight, and profit.
	 */
	public KnapsackObject(int o, int w, int p) {
		this.object = o;
		this.weight = w;
		this.profit = p;
	}
	
	/**
	 * This is the function toString.
	 * 
	 * It will print the object with its weight and profit in this format.
	 */
	public String toString() {
        return "\nObject: " + this.object + "\tWeight: " + 
        		this.weight + "\tProfit: " + this.profit;
    }
}