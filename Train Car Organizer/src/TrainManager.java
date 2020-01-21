import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Khiem Dinh Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 		ID: 111667279
 */
public class TrainManager {

	/**
	 * The main method runs a menu driven application which first creates an empty
	 * TrainLinkedList object. The program prompts the user for a command to execute
	 * an operation. Once a command has been chosen, the program may ask the user
	 * for additional information if necessary, and performs the operation.
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String userInput = "";
		TrainLinkedList userList = new TrainLinkedList(); // Train Linked List

		while (true) {

			System.out.println("(F) Move Cursor Forward");
			System.out.println("(B) Move Cursor Backward");
			System.out.println("(I) Insert Car After Cursor");
			System.out.println("(R) Remove Car At Cursor");
			System.out.println("(L) Set Load at Cursor");
			System.out.println("(S) Search For Product ");
			System.out.println("(T) Print Train");
			System.out.println("(M) Print Manifest");
			System.out.println("(D) Remove Dangerous Cars");
			System.out.println("(Q) Quit");
			System.out.println("Enter a selection:");
			userInput = input.nextLine();

			/*
			 * F - Move Cursor Forward
			 * Moves the cursor forward one car (if a next car exists). 
			 */
			if (userInput.equals("F") || userInput.equals("f")) {
				userList.cursorForward();
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream

				/*
				 * B - Move Cursor BackWard
				 * Moves the cursor backward one car (if a previous car exists). 
				 */
			} else if (userInput.equals("B") || userInput.equals("b")) {
				userList.cursorBackward();
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream

				/*
				 * I -  Insert Car After Cursor <Length> <Weight> 
				 * Inserts a new empty car after the cursor. If the cursor is null 
				 * (i.e. the train is empty), the car is set as the head of the train. After 
				 * insertion, the cursor is set to the newly inserted car.
				 * 
				 */

			} else if (userInput.equals("I") || userInput.equals("i")) {
				// clear input stream
				System.out.println("Enter car length in meters:");
				double length = 0;
				try {
					length = input.nextDouble();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print("Wrong input!"); // clear input stream
					continue; // skip iteration with error
				}				
				input.nextLine();
				System.out.println("Enter car weight in tons:");
				double weight = 0;
				try {
					weight = input.nextDouble();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print("Wrong input!"); // clear input stream
					continue; // skip iteration with error
				}
				TrainCar newCar = new TrainCar(length, weight);
				userList.insertAfterCursor(newCar);
				System.out.println("New train car " + newCar.getCarLength() + " meters " + newCar.getCarWeight()
						+ " tons inserted into train");
				ProductLoad empty = new ProductLoad ("Empty",0,0,false);
				newCar.setLoad(empty);
				input.nextLine();
				System.out.print("");

				
				/*
				 * R - Remove Car At Cursor 
				 * Removes the car at current position of the cursor. After deletion, 
				 * the cursor is set to the next car in the list if one exists,
				 * otherwise the previous car. If there is no previous car, the list is empty
				 * and the cursor is set to null. 
				 * 
				 */

						
			} else if (userInput.equals("R") || userInput.equals("r")) {
				TrainCar removed = userList.removeCursor();
				String yesOrNo = "NO";
				String setUp = String.format("%5s%12s%12s%12s", "Name", "Weight(t)", "Value($)", "Dangerous") + "\n"
						+ "==========================================";
				if (removed.getLoad() != null) {
					if (removed.getLoad().isDangerous() == true) {

						yesOrNo = "YES";
					}
					userList.value = userList.getValue() - removed.getLoad().getValue();
					System.out.println("Car sucessfully unlinked. The following load has been removed from the train:");

					String table = "\n" + String.format("%5s%12s%12s%12s", removed.getLoad().getName(),
							String.format("%,.2f", removed.getLoad().getWeight()),
							String.format("%,.2f", removed.getLoad().getValue()), yesOrNo);
					System.out.print(setUp + table);
				} else {
					System.out.println("Car sucessfully unlinked. The following load has been removed from the train:");
					String table = "\n" + String.format("%5s%12s%12s%12s", "Empty", "0.0", "0.0", "NO");
					System.out.print(setUp + table);

				}
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream

				/*
				 * L - Set Load At Cursor <Name> <Weight> <Value> <Is Dangerous>
				 * Sets the product load at the current position in the list. 
				 */

			} else if (userInput.equals("L") || userInput.equals("l")) {

				System.out.println("Enter produce name:");
				String productName = input.nextLine();
				System.out.println("Enter product weight in tons:");
				double productWeight = 0;
				try {
					productWeight = input.nextDouble();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print("Wrong input!"); // clear input stream
					continue; // skip iteration with error
				}
				input.nextLine();
				System.out.println("Enter product value in dollars:");
				double productValue = 0;
				try {
					productValue = input.nextDouble();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print("Wrong input!"); // clear input stream
					continue; // skip iteration with error
				}
				input.nextLine();
				System.out.println("Enter product is product dangerous? (y/n):");
				String productDangerous = input.nextLine().toLowerCase();
				boolean dangerous = false;
				if (productDangerous.equals("y")) {
					dangerous = true;
				}else if (productDangerous.equals("n")) {
					dangerous = false;
				}else {
					input.nextLine(); // clear input stream
					System.out.print("Wrong input!"); // clear input stream
					continue; // skip iteration with error
				}
				ProductLoad userLoad = new ProductLoad(productName, productWeight, productValue, dangerous);
				userList.getCursorData().setLoad(userLoad);
				System.out.println(String.format("%,.2f", productWeight) + " tons of " + productName
						+ " added to the current car.");
				
				userList.value = userList.value + userLoad.getValue();
				userList.weight = userList.weight + userLoad.getWeight();
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream

				/*
				 * S - Search For Product <name> 
				 * Searches the train for all the loads with the
				 * indicated name and prints out the total weight and value, and whether the
				 * load is dangerous or not. If the product could not be found, indicate to the
				 * user that the train does not contain the indicated product.
				 */

			} else if (userInput.equals("S") || userInput.equals("s")) {
				System.out.println("Enter product name:");
				String nameOfProduct = input.nextLine();
				userList.findProduct(nameOfProduct);
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				
				

				/*
				 * T - Print Train
				 * Prints the String value of the train to the console. 
				 */

			} else if (userInput.equals("T") || userInput.equals("t")) {
				System.out.print(userList.toString());
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream

				/*
				 * M - Print Manifest
				 * Prints the train manifest - the loads carried by each car. 
				 */

			} else if (userInput.equals("M") || userInput.equals("m")) {

				userList.printManifest();
				input.nextLine();
				System.out.print(""); // clear input stream

				/*
				 * D - Remove Dangerous Cars 
				 * Removes all the dangerous cars from the train. 
				 */

			} else if (userInput.equals("D") || userInput.equals("d")) {
				userList.removeDangerousCars();
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream

				/*
				 * Q - Quit
				 * Terminates the program
				 */

			} else if (userInput.equals("Q") || userInput.equals("q")) {
				System.out.println("Program terminating successfully...");
				break; // end the program

			} else {
				System.out.println("Wrong input! Please provide another input");

			}

		}

		input.close();
	}

}
