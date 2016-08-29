/**
 *	Assignment: Project 3
 *	Due date: 04/13/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Driver class starts the program.
 * It calls the simulate method in Simulator.
 *
 * @author Kate Evans
 */

import java.io.*;
public class Driver{
  /**
  * The main method runs the program.
  * It creates a Simulator object and uses it to call simulate()
  *
  * @param String [] args
  */
	public static void main(String [] args) throws IOException {
	  Simulator simulator = new Simulator();
	  simulator.simulate();
	
	}
}