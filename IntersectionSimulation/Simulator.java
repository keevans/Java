/**
 *	Assignment: Project 3
 *	Due date: 04/13/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Simulator class controls the entire program. It creates the 8 queues that make up 
 * the intersection, the vehicles, and moves the vehicles through.
 *
 * @author Kate Evans
 */

import jsjf.*;
import jsjf.exceptions.*;
import java.util.*;
import java.io.*;
public class Simulator{
  
  /**
  * The array that holds 8 LinkedQueue objects, one for each lane of the intersection.
  */
  LinkedQueue<Vehicle>[] lanes = (LinkedQueue<Vehicle>[]) (new LinkedQueue[8]);
  
  /**
  * The counter that keeps track of how much time has passed during the simulation.
  */
  private int time = 0;
  
  /**
  * The counter that keeps track of how many vehicles are created.
  */
  private int vehicleCount = 0;
  
  /**
  * The counter that keeps track of how many vehicles have left the intersection.
  */
  private int vehiclesLeavingIntersection = 120;
  
  /**
  * The counter used for exception handling. Will show which queue is empty.
  */
  private int errorLane = 0;

  /**
  * Controls the entire simulation. Calls other methods to create and move the vehicles.
  *
  * @throws IOException For when there is an error writing to a file.
  */
  public void simulate() throws IOException{ 
  
    /**
    * Try-catch block to handle any exceptions.
    */
    try{
      /**
      * Initiates the 8 queues that represent the lanes of the intersection.
      */
      setLanes();
      /**
      * Populates the queues.
      */
      generateVehicles(1);
      /**
      * While loop that runs the initial simulation. 
      * Moves vehicles then populates the queues.
      * Stops when a total of 120 vehicles have been created.
      */
      while(vehicleCount > 0  && vehiclesLeavingIntersection > 0){
        moveVehicles(1);
        generateVehicles(2);
        moveVehicles(2);
        generateVehicles(3);
      }
      /**
      * While loop that runs the second stage of the simulation.
      * Starts when 120 vehicles have been created.
      * Runs until no vehicles are remaining.
      */
      while(vehiclesLeavingIntersection > 0){
        moveVehicles(1);
        moveVehicles(2);
      }
    }//end try
    /**
    * Catch for if the program tries to move vehicles from empty lanes.
    * Prints output to an error file.
    * (I wanted to have an inner try-catch for IOExceptions as was suggested in class,
    *  however the program would not compile as it said it did not throw an IOException.)
    */
    catch(EmptyCollectionException exception){
      String file = "error.txt";
      FileWriter fw = new FileWriter(file, true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter outFile = new PrintWriter(bw);
  
      outFile.println(exception.getMessage());
      outFile.println("This occurred at " + time + "seconds. Cause lanes: " + errorLane); 
      outFile.println(exception.getStackTrace());
      outFile.close();
    }//end catch1
    /**
    * Catch for if there are any problems writing to files.
    */
    catch(IOException exceptions){
      System.err.println("There was an IOException at " + time + "seconds.");
      System.exit(1);
    }
  }//end simulate

  /**
  * Populates the array lanes with 8 LinkedQueues that hold vehicles.
  */
  public void setLanes(){
    for(int index = 0; index < lanes.length; index++){
      lanes[index] = new LinkedQueue<Vehicle>();
    }
  }//end setLanes
  
  /**
  * Populates the intersection with Vehicle objects.
  * Has 3 options for each listed in the spec.
  *
  * @param generationType This int determines what range should be used to generate Vehicles.
  */
  public void generateVehicles(int generationType){
    
    Random random = new Random();
    
    /**
    * int that determines how many Vehicles are created.
    */
    int randomIndex = 0;
    
    /**
    * Check to make sure that no more than 120 Vehicles are created.
    */
    if(vehicleCount < 120){
    
      /**
      * Generation procedure used once the number of Vehicles approaches 120.
      */
      if(vehicleCount > 105 && vehicleCount < 120){
        randomIndex = 120 - vehicleCount;
      }
      else{
        /**
        * First range for generating Vehicles.
        * Used for the initial population of the intersection and then never again.
        */
        if(generationType == 1){
          randomIndex = random.nextInt(13 - 7) + 7; 
        }
        /**
        * Second range for generating Vehicles.
        * First of two population ranges used until there are 120 Vehicles.
        */
        if(generationType == 2){
          randomIndex = random.nextInt(16 - 8) + 8; 
        }
        /**
        * Third range for generating Vehicles.
        * First of two population ranges used until there are 120 Vehicles.
        */
        if(generationType == 3){
          randomIndex = random.nextInt(16 - 3) + 3; 
        }
      }//else
      
      /**
      * Populates the intersection based on how many Vehicles are determined to be created.
      * Then picks a random lane to place the new Vehicle in.
      */
      for(int index = 0; index < randomIndex; index++){
        int randomLane = random.nextInt(8 - 0) + 0; 
        if(randomLane == 0){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Church", "S", time));
        }
        if(randomLane == 1){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Church", "S", time));
        }
        if(randomLane == 2){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Church", "N", time));
        }
        if(randomLane == 3){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Church", "N", time));
        }
        if(randomLane == 4){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Main", "E", time));
        }
        if(randomLane == 5){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Main", "E", time));
        }
        if(randomLane == 6){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Main", "W", time));
        }
        if(randomLane == 7){
          lanes[randomLane].enqueue(new Vehicle(vehicleCount+1, "Main", "W", time));
        }
        /**
        * vehicleCount is incremented to keep track of how many Vehicles have been created.
        */
        vehicleCount++;
      }//end for
    }//end if
  }//end generateVehicles
  
  
  /**
  * Moves Vehicles through the intersection.
  * Has 2 options for each type of movement listed in the spec.
  *
  * @param movementType This int determines which lanes can move.
  */
  public void moveVehicles(int movementType) throws IOException{
    
    /**
    * The output file is set up.
    */
    String file = "output.txt";
    FileWriter fw = new FileWriter(file, true);
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter outFile = new PrintWriter(bw);
    
    /**
    * The start of the simulation is printed to output.txt
    */
    if(time == 0){
      outFile.println("---Start of simulation, time set to 0---");
    }
    
    /**
    * Movement type1 moves Vehicles in north/south-bound lanes.
    */
    if(movementType == 1){
      outFile.println("\n---Light changed. Now processing north/south-bound traffic ---");
      
      /**
      * This for loop keeps track of the "rounds" of traffic going through the intersection.
      * This one has 2 rounds or 2 3-second intervals in which Vehicles can move through the
      * intersection, for a total of 6 seconds of movement.
      */
      for(int localCount = 0; localCount < 2; localCount++){
        /**
        * Time is incremented by three for each round of traffic.
        */
        time = time + 3;
        errorLane = 0;
        /**
        * This for loop goes to each lane and moves 1 Vehicle.
        * First it checks that the queue is not empty, then
        * it dequeues a Vehicle and temporarily holds the object.
        * The departure time of the Vehicle is set, the output is printed
        * and vehiclesLeaving Intersection is decremented.
        */
        for(int index = 0; index < 4; index++){
          
          if(lanes[index].isEmpty() == false && vehiclesLeavingIntersection > 0){
            Vehicle tempVehicle = lanes[index].dequeue();
            
            tempVehicle.setDepartureTime(time);
            
            outFile.println("[Time " + timeOutput() + "] Vehicle #" + tempVehicle.getVehicleNumber() + 
         								 directionalOutput(index) + "Total wait time " + tempVehicle.getTotalWaitTime() + ".");
         		
         		vehiclesLeavingIntersection--;
         		errorLane++;
          }//end if isEmpty
          
        }//end for index
      }//end for localCount
    }//end type1
    
    /**
    * Movement type2 moves Vehicles in east/west-bound lanes.
    */
    if(movementType == 2){
      outFile.println("\n---Light changed. Now processing east/west-bound traffic ---");
      
      /**
      * This for loop keeps track of the "rounds" of traffic going through the intersection.
      * This one has 3 rounds or 3 3-second intervals in which Vehicles can move through the
      * intersection, for a total of 9 seconds of movement.
      */
      for(int localCount = 0; localCount < 3; localCount++){
        /**
        * Time is incremented by three for each round of traffic.
        */
        time = time + 3;
        errorLane = 4;
        /**
        * This for loop goes to each lane and moves 1 Vehicle.
        * First it checks that the queue is not empty, then
        * it dequeues a Vehicle and temporarily holds the object.
        * The departure time of the Vehicle is set, the output is printed
        * and vehiclesLeaving Intersection is decremented.
        */
        for(int index = 4; index < 8; index++){
          
          if(lanes[index].isEmpty() == false && vehiclesLeavingIntersection > 0){
            Vehicle tempVehicle = lanes[index].dequeue();
            
            tempVehicle.setDepartureTime(time);
            
            outFile.println("[Time " + timeOutput() + "] Vehicle #" + tempVehicle.getVehicleNumber() + 
         								 directionalOutput(index) + "Total wait time " + tempVehicle.getTotalWaitTime() + ".");
            
            vehiclesLeavingIntersection--;
            errorLane++;
          }//end if isEmpty
        
        }//end for index
      }//end for localCount
    }//end type2
    /**
    * The output file is closed.
    */
    outFile.close();
  }//end moveVehicles
 
  /**
  * This method is used for output. It prints the direction the Vehicle
  * was facing and then were it went (continued straight or turned).
  *
  * @param movementType This int determines which lanes can move.
  * @return A string for the directional output of the Vehicle.
  */
  public String directionalOutput(int lanesIndex){
    String directionalOutput = "";
   
    if(lanesIndex == 0){
      directionalOutput = " (southbound) continued straight. ";
    }
    if(lanesIndex == 1){
      directionalOutput = " (southbound) turned right and headed westbound. ";
    }
    if(lanesIndex == 2){
      directionalOutput = " (northbound) continued straight. ";
    }
    if(lanesIndex == 3){
      directionalOutput = " (northbound) turned right and headed eastbound. ";
    }
    if(lanesIndex == 4){
      directionalOutput = " (eastbound) continued straight. ";
    }
    if(lanesIndex == 5){
      directionalOutput = " (eastbound) turned right and headed southbound. ";
    }
    if(lanesIndex == 6){
      directionalOutput = " (westbound) continued straight. ";
    }
    if(lanesIndex == 7){
      directionalOutput = " (westbound) turned right and headed northbound. ";
    }
    return directionalOutput;
  }
 
  /**
  * This method is used for output. It prints the time. 
  * Mostly necessary for when time < 10 and must have a 0
  * at the front (as in 03 or 06 etc).
  *
  * @return The time, formatted if necessary
  */
  public String timeOutput(){
    String formattedTime;
   
    if(time < 10){
      formattedTime = "0" + time;
    }
    else{
      formattedTime = "" + time;
    }
    return formattedTime;
  }
}//end class