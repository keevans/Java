/**
 *	Assignment: Project 1
 *	Due date: 02/17/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Processor class process all of the data from the downloaded file.
 * It downloads and reads the file then stores the data as 
 * ZipCodeLocation objects in a Comparable array.
 * It sorts the Array in multiple ways to produce the necessary output.
 * The output is then printed to a file called "zipout.txt".
 *
 * @author Kate Evans
 */
 
import java.util.*;
import java.io.*;
import java.net.*;
public class Processor {

  /**
  * The array that holds of the ZipCodeLocation objects.
  */
  private Comparable[] ZipCodeObjects = new Comparable[45000]; 
  
  /**
  * The last filled index of the array. 
  * Used for sorting the array without nulls.
  */
  private int lastIndex = 0;
  
  /**
  * The method that downloads and reads the data file.
  * It eliminates an incomplete lines of data then creates
  * ZipCodeLocation objects and stores them to the array.
  */
  public void readData() throws IOException{
  
    /**
    * Downloads the file
    */ 
    URL url = new URL("https://s3.amazonaws.com/depasquale/datasets/zipcodes.txt");
    Scanner fileScan = new Scanner(url.openStream());
    /**
    * Scans the file and separates out the data then stores it.
    */
    while(fileScan.hasNextLine()){
      String line = fileScan.nextLine();
	  Scanner lineScan = new Scanner(line);
	  lineScan.useDelimiter(","); 
	  
	  /**
      * ZipCodeLocation objects are created in the array.
      */
	  ZipCodeObjects[lastIndex] = new ZipCodeLocation();
	
	  /**
      * Each individual line is divided up into individual strings.
      */
	  String zipCode = lineScan.next();
      String latitude = lineScan.next();
	  String longitude = lineScan.next();
	  String townName = lineScan.next();
	  String stateName = lineScan.next();
	  String countyName = lineScan.next();
	  String postalType = lineScan.next();
	
	  /**
      * Checks for valid data before adding it to the array.
      */
	  if(latitude.isEmpty() || longitude.isEmpty() || townName.isEmpty() || 
	  stateName.isEmpty() || countyName.isEmpty() || postalType.isEmpty()){
	    System.err.println("Incomplete line of data for: " + zipCode);
	  }
	  
	  else{
	    /**
        * If the strings are not empty, then the quotes are removed.
        */
	    String zip = zipCode.substring(1, zipCode.length()-1);
		String latt = latitude.substring(1, latitude.length()-1);
		String lonn = longitude.substring(1, longitude.length()-1);
		String town = townName.substring(1, townName.length()-1);
		String state = stateName.substring(1, stateName.length()-1);
		String county = countyName.substring(1, countyName.length()-1);
		String type = postalType.substring(1, postalType.length()-1);
		  
		/**
        * Converts the latitude and longitude from strings to doubles.
        */  
		double lat = Double.parseDouble(latt); //+ and - may cause problem substring from 2?
	    double lon = Double.parseDouble(lonn);
		
		/**
        * Objects are added to the array.
        */
		((ZipCodeLocation) ZipCodeObjects[lastIndex]).setZipCodeLocation(zip, lat, lon, town, state, county, type);
		lastIndex++;
	  }
    }
    /**
    * processData() is called to analyze the data.
    */
    processData();
  }
  
  /**
  * Sorts the data first by state and then by town 
  * in order to print an alphabetized list of towns in a state.
  *
  * @param  stateCompare  The string name of the state from which the towns are alphabetized.
  * @return The string array that holds the alphabetized town names.
  */ 
  public String[] listTownsAlphabetically(String state){
    /**
    * The compareType is set and the array is sorted according to town.
    */
    ZipCodeLocation.setCompareType(1);
    Arrays.sort(ZipCodeObjects, 0, lastIndex);
    
    String towns[] = new String [5000];
    int index = 0;
    
    /**
    * Moves through the array ZipCodeObjects.
    */
    for(int i = 0; i < lastIndex; i++){
    
      /**
      * Checks that the state is RI.
      */
      String s = ((ZipCodeLocation) (ZipCodeObjects[i])).getState();
      if(s.compareTo(state) == 0){
        
        /**
        * Adds RI towns to the string array towns.
        */
        if(index == 0){
          String t = ((ZipCodeLocation) (ZipCodeObjects[i])).getTownName();
          towns[index] = t;
          index++;
        }
        /**
        * Ensures that town names are not repeated by checking that the town at index i
        * is not the same as the one at index i-1, the index before it.
        */
        else if((((ZipCodeLocation) (ZipCodeObjects[i])).getTownName()).compareTo(towns[index-1]) !=0){
          String t = ((ZipCodeLocation) (ZipCodeObjects[i])).getTownName();
          towns[index] = t;
          index++;
        }
      }
    }
    return towns;
  }
  
  /**
  * Sorts the data first by state and then by county 
  * in order to print a list of all of
  * the counties USPS delivers to.
  *
  * @return The string array that holds all of the unique counties.
  */ 
  public String [] getCounties(){
  
    /**
    * Creates an array to hold the returned values.
    * Sets the compareType and sorts the array by county.
    */
    String[] counties = new String[45000];
    ZipCodeLocation.setCompareType(2);
    Arrays.sort(ZipCodeObjects, 0, lastIndex);
    
    /**
    * Adds the first county to the array.
    */
    counties[0] = ((ZipCodeLocation)(ZipCodeObjects[0])).getCounty();
    int index = 1;
    
    /**
    * Adds the other counties to the array. 
    * Starts at 1 because the first county has already been added.
    * Also checks that there are no repeat counties.
    */
    for(int i = 1; i < lastIndex; i++){
      int comparison = ((ZipCodeLocation)(ZipCodeObjects[i])).compareTo((ZipCodeLocation)ZipCodeObjects[i-1]);
      if(comparison !=0){
        counties[index] = ((ZipCodeLocation)(ZipCodeObjects[i])).getCounty();
        index++;
      }
    }
    return counties;
  }
  
  /**
  * Sorts the data postal types
  * in order to print all of the different
  * types and their frequencies.
  * 
  * @return The String array that holds all of the postal types.
  */ 
  public String [] getPostalCodeTypes(){
   /**
    * Creates an array to hold the returned values.
    * Sets the compareType and sorts the array by type.
    */
    String [] postal = new String[45000];
    ZipCodeLocation.setCompareType(3);
    Arrays.sort(ZipCodeObjects, 0, lastIndex);
    
    /**
    * Adds unique postal types to the array. The frequencies are calculated at printing.
    */
    for(int i = 0; i < lastIndex; i++){
        postal [i] = ((ZipCodeLocation) (ZipCodeObjects[i])).getType();
    }
    return postal;
  }
  
  /**
  * Sorts the data first by state and then by county 
  * in order to calculate the total number
  * the counties USPS delivers to.
  * 
  * @return The integer amount of total counties delivered to.
  */ 
  public int getTotalCounties(){
    /**
    * The integer for total counties. Set to 1 because the first county will be skipped.
    */
    int totalCounties = 1; 
   /**
    * Creates an array to hold the returned values.
    * Sets the compareType and sorts the array by state.
    * The compareType is then set to compare by county.
    */
    ZipCodeLocation.setCompareType(5);
    Arrays.sort(ZipCodeObjects, 0, lastIndex);
    ZipCodeLocation.setCompareType(2);
    
    /**
    * Compares all of the counties and if they are unique, totalCounties is incremented.
    */
    for(int i = 1; i < lastIndex; i++){
      int compare = ((ZipCodeLocation)(ZipCodeObjects[i])).compareTo((ZipCodeLocation)(ZipCodeObjects[i-1]));
      if(compare != 0){
        totalCounties++;
      }
    }
    return totalCounties;
  }

  /**
  * Sorts the data first by town name
  * in order to print a list of all the zipcodes
  * for towns called Portsmouth.
  *
  * @param  location  The string name of the town.
  * @return The string array of different zipcodes.
  */ 
  public String[] getZipcodesByLocationName(String location){ 
    /**
    * Creates an array to hold the returned values.
    * Sets the compareType and sorts the array by zipcode.
    */
    String [] zips = new String [5000];
    ZipCodeLocation.setCompareType(4);
    Arrays.sort(ZipCodeObjects, 0 , lastIndex);
    int index = 0;
    /**
    * Finds all of the towns called Portsmouth and then adds the zipcodes to the array.
    * Had to use String methods toLowerCase() and contains() because it wouldn't work
    * with a regular to compareTo() for some reason.
    */
    for(int i = 0; i < lastIndex; i++){
      String town = ((ZipCodeLocation)(ZipCodeObjects[i])).getTownName();
      if((town.toLowerCase()).contains(location.toLowerCase())){
        zips[index] = ((ZipCodeLocation)(ZipCodeObjects[i])).getZipCode();
        index++;
      }
    }
    return zips;
  }
  
  /**
  * This method prints to the output file and calls all of the other  
  * methods in the class to get the correct output.
  */ 
  public void processData() throws IOException{
  
    /**
    * The output file is created and printer manipulated to print to it.
    */
    String file = "zipout.txt";
    FileWriter fw = new FileWriter(file);
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter outFile = new PrintWriter(bw);
    

    outFile.println("Program start");
    outFile.println("-------------------------------------------------------");
    /**
    * Prints the list of alphabetized towns.
    * Sets the returned array to another String array.
    * Prints the new array with a comma and space.
    */
      outFile.println("Alphabetized towns in Rhode Island:");
      String [] towns = this.listTownsAlphabetically("RI");
      for(int i = 0; i <  towns.length; i++){
        if(towns[i] != null){
          outFile.print(towns[i] + ", ");
        }
        else{
          i = towns.length;
        }
      }
      
    outFile.println("-------------------------------------------------------");
    outFile.println("-------------------------------------------------------");
    /**
    * Prints the list of counties.
    * Sets the returned array to another String array.
    * Prints the new array with a comma and space.
    */
      outFile.println("List of Counties:");
      String [] counties = this.getCounties();
      for(int i = 0; i <  counties.length; i++){
        if(counties[i] != null){
          outFile.print(counties[i] + ", ");
        }
        else{
          i = counties.length;
        }
      }
    outFile.println("\n-------------------------------------------------------");
    outFile.println("-------------------------------------------------------");
    /**
    * Prints the list of postal types and their frequencies.
    * Sets the returned array to another String array.
    * The frequency is calculated by counting how many times the type occurs
    * in the returned array. That type is then saved to a string and it's 
    * frequency is saved to an int and these are printed.
    */
      outFile.println("Frequency of postal types:");
      String [] postal = this.getPostalCodeTypes();
      String type = postal[0];
      int count = 1;
      for(int i = 0; i <  lastIndex ; i++){
        if(type.compareTo(postal[i]) == 0){
           count++;
        }
        else if(type.compareTo(postal[i]) != 0){
          outFile.print(type + ": " + count + "\n");
          count = 1;
          type = postal[i];
        }
        
        if(i == lastIndex-1){
          outFile.print(type + ": " + count + "\n");
        }
      }
    outFile.println("-------------------------------------------------------");
    outFile.println("-------------------------------------------------------");
    /**
    * Prints the total number of counties.
    * Saves the returned int to a new int and prints it.
    */
      outFile.println("Total number of counties delivered to:");
      int total = getTotalCounties();
      outFile.print(total);
    outFile.println("\n-------------------------------------------------------");
    outFile.println("-------------------------------------------------------");
    /**
    * Prints the list of zipcodes for towns called Portsmouth.
    * Sets the returned array to another String array.
    * Prints the new array with a comma and space.
    */
      outFile.println("Portsmouth zipcodes:");
      String [] zips = this.getZipcodesByLocationName("Portsmouth");
      for(int i = 0; i <  zips.length; i++){
        if(zips[i] != null){
          outFile.print(zips[i] + ", ");
        }
        else{
          i = zips.length;
        }
      }
    outFile.println("\n-------------------------------------------------------");
    outFile.println("Program end");
    outFile.close();
  }
}