//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ScannerPractice
// Files: ScannerPractice.java output.txt file_output.txt
// Course: Comp Sci 400, section 002
//
// Author: Shihan Cheng
// Email: scheng93@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: A program for trying Scanner and PrintWriter to do the output by
// reading user's input and a file.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * This class is designed for using Scanner and PrintWriter to get user's input
 * or the information from a file. Also write a file from above information.
 * 
 * @Author Shihan Cheng
 */
public class ScannerPractice {

  /**
   * This method is designed to display the prompt menu
   */
  public static void prompt() {
    // Prompt for user to type in
    System.out.println("Choose following options with the number");
    System.out.println("1. Copy my words.");
    System.out.println("2. Load and read a file.");
    System.out.println("3. Copy my words to a file.");
    System.out.println("4. Quit.");
  }

  /**
   * This method is designed to access user's command 1 to copy and display the
   * content they type in
   */
  public static void inputAccessor() {
    // Create a new scanner and a string
    Scanner stdin = new Scanner(System.in);
    String input = "";

    // Prompt for user to do the input
    System.out.println("Type anything!");

    // Stops till user not typing
    while (stdin.hasNext()) {
      input = stdin.next(); // Get user's input
      if (input.contentEquals(":q")) { // If they want to quit, leave the loop
        break;
      }
      // Otherwise, display their input separated by {}
      System.out.println("{" + input + "} ");
    }
  }

  /**
   * This method is designed to access the file load by users
   */
  public static void fileInputAccessor() {
    // Create a new scanner
    Scanner stdin = new Scanner(System.in);

    // Prompt for user providing the file name
    System.out.println("Type file name with the format filename.txt.");

    try { // Avoid FileNotFoundException
      // Load the file
      File file = new File(stdin.nextLine());
      // Create a new scanner to read user's file
      Scanner reader = new Scanner(file);
      System.out.println("[The content of the file is]");
      // Keep scanning the file until the scanner cannot find anything
      while (reader.hasNextLine()) {
        // Display the content from user's file
        System.out.println(reader.nextLine());
      }
    } catch (FileNotFoundException e) {
      // Warning message
      System.out.println("No such file.");
    }
  }

  /**
   * This method is designed to write content from user's input to the file that
   * users want
   */
  public static void fileWriter() {
    // Create a new scanner and a string
    Scanner stdin = new Scanner(System.in);
    String input = "";

    // Prompt for user providing the file name
    System.out.println("Please type the file name!");
    input = stdin.nextLine();
    try { // Avoid FileNotFoundException
      // Create a new PrintWriter to write user's file
      PrintWriter writer = new PrintWriter(input + ".txt");
      // Prompt for user to type in
      System.out.println("Please tell me what you want to record!");
      System.out.println("Type :q to quit!");

      // Stops till user not typing
      while (stdin.hasNextLine()) {
        // Store user's input
        input = stdin.nextLine();
        if (!input.contentEquals(":q")) { // If they do not want to quit
          // Write the user's input to the file and separate them with new line
          writer.write(input + "\n");
        } else { // If user finishes writing
          writer.flush(); // Cover the original content in the file
          writer.close(); // Close the PrintWriter
          return; // Return to main
        }
      }
    } catch (FileNotFoundException e) {
      // Warning message
      System.out.println("No such file.");
    }
  }

  /**
   * The main method is where the application starts, run with calling all other
   * methods above by recognizing user's commands
   * 
   * @param arg any type
   */
  public static void main(String[] arg) {
    // Create a new scanner and an integer to get and analyze user's command
    Scanner stdin = new Scanner(System.in);
    int command = 0;

    // Call method prompt to display the menu
    System.out.println("Welcome!");
    prompt();

    // Start to recognize user's input
    // This loop keeps running until user chooses to quit
    while (stdin.hasNext()) {
      // If the user's input is not a valid integer, prompt them with the error
      // message
      if (!stdin.hasNextInt()) {
        System.out.println("Please type an integer 1 - 4");
      } else { // If the input is an integer, store it
        command = stdin.nextInt();

        // Check if the command is an integer within [1,4]
        if (command < 1 || command > 4) {
          // If not, prompt them with the error message
          System.out.println("The integer should be 1 - 4");
        } else {
          // Once the input is valid start to access user's command
          if (command == 1) { // Command 1 goes to method inputAccessor()
            inputAccessor();
          } else if (command == 2) { // Command 2 goes to method
                                     // fileInputAccessor()
            fileInputAccessor();
          } else if (command == 3) { // Command 3 goes to method fileWriter()
            fileWriter();
          } else { // When the command is 4, quit the application
            System.out.println("Thank you for using!");
            break;
          }
        }
      }
      System.out.println();
      prompt(); // When the command ends, if user not choose to quit, display
                // the menu again
      stdin.nextLine(); // Scanner goes to next line to get new input
    }
  }

}
