/**
* @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */	
import java.util.Collection;
import java.util.Hashtable;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.InputMismatchException;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.File;
public class AuctionSystem implements Serializable{
	
	private static String userName;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner input = new Scanner(System.in);
		String userInput = "";
		String URL = "";	
		System.out.println("Starting...");
		AuctionTable userTable;
		try {
			File file1 = new File("auction.obj");
			FileInputStream file2 = new FileInputStream(file1);
			ObjectInputStream inStream = new ObjectInputStream(file2);
			userTable = (AuctionTable) inStream.readObject();
			System.out.println("Loading Previous Auction Table...");
		} catch (FileNotFoundException ex) {
			userTable = new AuctionTable();
			System.out.println("No previous table detected");
			System.out.println("Creating new table ... \n");
		}		
	
		System.out.println("Please select a username: ");
		userName = input.nextLine();
		while (true) {
			System.out.println("(D) - Import Data from URL");
			System.out.println("(A) - Create a New Auction");
			System.out.println("(B) - Bid on an Item");
			System.out.println("(I) - Get Info on Auction");
			System.out.println("(P) - Print All Auctions");
			System.out.println("(R) - Remove Expired Auctions");
			System.out.println("(T) - Let Time Pass");
			System.out.println("(Q) - Quit");
			String userSelection = input.nextLine();
			userSelection.toUpperCase();
			if (userSelection.equals("D")) {
				System.out.println("Please enter a URL: ");
				URL = input.nextLine();
				try {
					URL url = new URL(URL);
					URLConnection conn = url.openConnection();
					conn.connect();
					userTable = AuctionTable.buildFromURL(URL);
				} catch (MalformedURLException e) {
					System.out.println("Invalid URL!");
				} catch (IOException e) {
					System.out.println("Can not Connect To URL");
				}
			} else if (userSelection.equals("A")) {
				System.out.println("Creating new Auction as " + userName);
				System.out.println("Please enter an Auction ID:");
				String iDInput = input.nextLine();
				System.out.println("Please enter an Auction time (hours):");
				int timeInput;
				try {
					timeInput = input.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Wrong Input!");
					System.out.print(""); // clear input stream
					continue; // skip iteration with error
				}
				System.out.println("Please enter some item info");
				input.nextLine();
				String itemInfo = input.nextLine();
				Auction newItem = new Auction(0, 0, iDInput, userName, "      ", itemInfo);
				userTable.put(iDInput, newItem);
			} else if (userSelection.equals("B")) {
				System.out.println("Please enter an Auction ID:");
				userInput = input.nextLine();
				if (userTable.containsKey(userInput)) {
					double userBid = 0;
					Auction inquiry = userTable.get(userInput);
					Collection<Auction> list = userTable.values();
					Object[] allAuction = list.toArray();
					userTable.clear();
					if (inquiry.getTimeRemaining() > 0) {
						System.out.println("Auction " + userInput + " is OPENED");
						System.out.println("\t" + "Current Bid: " + "$ " + inquiry.getCurrentBid());
						System.out.println("What would you like to bid?");
						try {
							userBid = input.nextDouble();
						} catch (InputMismatchException e) {
							System.out.println("Wrong Input!");
							System.out.print(""); // clear input stream
							continue; // skip iteration with error
						}
						input.nextLine(); // clearing the input stream
						System.out.print(""); // clearing the input stream
						if (userBid > inquiry.getCurrentBid()) {
							for (int i = 0; i < allAuction.length; i++) {
								Auction modified = (Auction) allAuction[i];
								if (modified.getAuctionID() == inquiry.getAuctionID()) {
									modified.newBid(userName, userBid);
								}
								userTable.put(modified.getAuctionID(), modified);
							}
						} else {
							System.out.println("Bid rejected.");
						}
					} else {
						System.out.println("Auction " + userInput + " is CLOSED");
					}
				} else {
					System.out.println("Invalid ID Input!");
				}

			} else if (userSelection.equals("I")) {
				System.out.println("Please enter an Auction ID:");
				userInput = input.nextLine();
				// check valid id
				if (userTable.containsKey(userInput)) {
					Auction inquiry = userTable.get(userInput);
					System.out.println("Auction " + userInput + ":");
					System.out.println("\t" + "Seller: " + inquiry.getSellerName());
					System.out.println("\t" + "Buyer: " + inquiry.getBuyerName());
					System.out.println("\t" + "Time: " + inquiry.getTimeRemaining() + " hours");
					System.out.println("\t" + "Info: " + inquiry.getItemInfo());
				} else {
					System.out.println("Invalid ID Input!");
				}

			} else if (userSelection.equals("P")) {
				userTable.printTable();
			} else if (userSelection.equals("R")) {
				System.out.println("Removing expired auctions...");
				userTable.removeExpiredAuctions();
				System.out.println("All expired auctions removed.");
			} else if (userSelection.equals("T")) {
				int hour = 0;
				System.out.println("How many hours should pass: ");
				try {
					hour = input.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Wrong Input!");
					System.out.print(""); // clear input stream
					continue; // skip iteration with error
				}
				System.out.println("Time passing...");
				userTable.letTimePass(hour);
				System.out.println("Auction times updated.");
			} else if (userSelection.equals("Q")) {						
				FileOutputStream file = new FileOutputStream("auction.obj");
				ObjectOutputStream outStream = new ObjectOutputStream(file);
				outStream.writeObject(userTable);
				System.out.println("Program Terminated Successfully");
				System.out.println("Writing Auction Table to file...");
				System.out.println("Done!" + "\n");
				System.out.println("Goodbye.");
				break; // end the program
			} else {
				System.out.println("Wrong input! Please provide another input");
			}

		}

	}

}
