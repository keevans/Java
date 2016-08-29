/**
 *	Assignment: Project 1
 *	Due date: 02/17/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Application class is the driver for the whole program.
 * It calls two methods in Processor to produce the output.
 *
 * @author Kate Evans
 */
 
import java.io.*;
public class Application{
  
  public static void main(String[] args) throws IOException {
  Processor process = new Processor();
  
  System.out.println("Program starting.");
  process.readData();
  System.out.println("Program end.");
  }


}