/**
 *	Assignment: Project 1
 *	Due date: 02/17/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The ZipCodeLocation class stores the data from the file as objects.
 * The class uses Strings and doubles to store a locations zipcode,
 * latitude, longitude, the name of the town, the state, the county, 
 * and the postal type. It extends the interface Comparable to allow
 * the data to be more easily sorted.
 *
 * @author Kate Evans
 */
public class ZipCodeLocation implements Comparable<ZipCodeLocation>{

  /**
  * The string representation of a locations zipcode.
  */
  private String zipcode;
  
  /**
  * A locations latitude.
  */
  private double latitude;
  
  /**
  * A locations longitude.
  */
  private double longitude;
  
  /**
  * The string name of the town.
  */
  private String townName;
  
  /**
  * The string name of the state.
  */
  private String state;
  
  /**
  * The string name of the county.
  */
  private String county;
  
  /**
  * The string name of the type. (Standard, Military, etc)
  */
  private String type;
  
  /**
  * The static variable used to control the switch statement in the
  * compareTo() method. Static so that when set, it is the same
  * for every object.
  */
  private static int compareType;
  
  /**
  * Creates a ZipCodeLocation object that does not accept parameter.
  */
  public ZipCodeLocation(){ }
  
   /**
  * Creates a ZipCodeLocation object from the provided data.
  *
  * @param  zip  The string representation of the location's zipcode.
  * @param  lat  The location's latitude.
  * @param  lon  The location's longitude.
  * @param  name The string name of the town.
  * @param  s    The string name of the state
  * @param  c    The string name of the county.
  * @param  c    The string name of the postal type.
  */
  public void setZipCodeLocation(String zip, double lat, double lon, String name, String s, String c, String t){
    zipcode = zip;
    latitude = lat;
    longitude = lon;
    townName = name;
    state = s;
    county = c;
    type = t;
  }
  
  /**
  * Returns the locations zipcode.
  *
  * @ return The location's zipcode as a string.
  */
  public String getZipCode(){
    return zipcode;
  }
  
  /**
  * Returns the locations latitude.
  *
  * @return The location's latitude as a double.
  */
  public double getLatitude(){
    return latitude;
  }
  
  /**
  * Returns the locations longitude.
  *
  * @return The location's longitude as a double.
  */
  public double getLongitude(){
    return longitude;
  }
  
  /**
  * Returns the town name.
  *
  * @return The town name as a string.
  */
  public String getTownName(){
    return townName;
  }
  
  /**
  * Returns the state name.
  *
  * @return The state name as a string.
  */
  public String getState(){
    return state;
  }
  
  /**
  * Returns the county name.
  *
  * @return The county name as a string.
  */
  public String getCounty(){
    return county;
  }
  
  /**
  * Returns the postal type.
  *
  * @return The postal type as a string.
  */
  public String getType(){
    return type;
  }
  
  /**
  * Sets the compareType for the compareTo method.
  *
  * @param  compare  The integer that represents how the array will be sorted
  */
  public static void setCompareType(int compare){
    compareType = compare;
  }
  
  /**
  * The compareTo() method taken from the Comparable interface.
  * Allows all ZipCodeLocation objects to be comparable.
  * Allows for sorting within the array.
  * Uses a switch case to allow for different types of sorting.
  *
  * @param  obj  The object being compared.
  */
  public int compareTo(ZipCodeLocation obj){
    int x = 0;
    int compare = 0;
    switch(compareType){
      case 1:
        compare = townName.compareTo(obj.getTownName());
      break;
      case 2:
        compare = county.compareTo(obj.getCounty());
      break;
      case 3:
       compare = type.compareTo(obj.getType());
      break;
      case 4:
        compare = zipcode.compareTo(obj.getZipCode());
      break;
      case 5:
       if(state.compareTo(obj.getState()) == 0){
          compare = county.compareTo(obj.getCounty());
        }
        else{
          compare = state.compareTo(obj.getState());
        }
      break;
    }
    return compare;
  } 
}