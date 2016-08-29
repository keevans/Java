/**
 *	Assignment: Project 3
 *	Due date: 04/13/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Vehicle class holds all information for the Vehicle objects used in the simulation.
 *
 * @author Kate Evans
 */

import java.lang.*;
public class Vehicle{
  /**
  * The int that holds which number the Vehicle is (1 - 120).
  */
  private int vehicleNumber;
  
  /**
  * The enum for the streets the Vehicles are placed on (Church and Main).
  */
  private enum Street {Church, Main}
  
  /**
  * The enum for the directions the Vehicles are going.
  */
  private enum Direction {N, S, E, W}
  
  /**
  * The object that holds which street the Vehicle is on.
  */
  private Street street;
  
  /**
  * The object that holds which direction the Vehicle is going.
  */
  private Direction direction;
  
  /**
  * The int that holds what time the Vehicle arrives at the intersection.
  */ 
  private int arrivalTime;
  
  /**
  * The int that holds what time the Vehicles leaves the intersection.
  */
  private int departureTime;
  
  /**
  * The constructor for Vehicle objects.
  *
  * @param number The int for Vehicle number.
  * @param streetOn The String that determines the enum for which street the Vehicle is on.
  * @param directionGoing The String that determines the enum for which direction the Vehicle is going.
  * @param arrive The int the Vehicles arrival time. 
  */
  public Vehicle(int number, String streetOn, String directionGoing, int arrive){
    vehicleNumber = number;
    arrivalTime = arrive; 
    
    /**
    * The constructor accepts Strings and uses them to determine what value the
    * enum should be set to using a series of if statements.
    */
    if(streetOn.equals("Church")){
      street = Street.Church;
    }
    if(streetOn.equals("Main")){
      street = Street.Main;
    }
    
    if(directionGoing.equals("N")){
      direction = Direction.N;
    }
    if(directionGoing.equals("S")){
      direction = Direction.S;
    }
    if(directionGoing.equals("E")){
      direction = Direction.E;
    }
    if(directionGoing.equals("W")){
      direction = Direction.N;
    }
  }//end setVehicle
  
  /**
  * Sets the departure time for the Vehicle once it has left the intersection.
  *
  * @param depart The int for departure time.
  */
  public void setDepartureTime(int depart){
    departureTime = depart;
  }//end setDepartureTime
  
  /**
  * Accesses the Vehicle's number.
  *
  * @return The Vehicle number int.
  */
  public int getVehicleNumber(){
    return vehicleNumber;
  }//end getVehicleNumber
  
  /**
  * Determines how long the Vehicle was waiting at the intersection.
  *
  * @return The total wait time which is the difference between the departure and arrival times.
  */
  public int getTotalWaitTime(){
    int total = departureTime - arrivalTime;
    return total;
  }
  
  /**
  * Allows the Vehicle object to be easily printed.
  *
  * @return The String for the Vehicle and its number.
  */
  public String toString(){
    return "Vehicle " + vehicleNumber;
  }//end toString
}//end class