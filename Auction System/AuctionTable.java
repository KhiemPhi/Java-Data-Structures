/**
* @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */
import big.data.*;	
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
public class AuctionTable extends Hashtable<String, Auction> implements Serializable {
	
	private  static AuctionTable userTable = new AuctionTable();
		
	public AuctionTable () {
		
		super();
	}
	
	/**
	 * Brief:
	 * 
	 * Uses the BigData library to construct an AuctionTable from a remote data
	 * source.
	 * 
	 * 
	 * Parameters:
	 * 
	 * URL - String representing the URL fo the remote data source.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * URL represents a data source which can be connected to using the BigData
	 * library. The data source has proper syntax.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * None.
	 * 
	 * 
	 * Returns:
	 * 
	 * The AuctionTable constructed from the remote data source.
	 * 
	 * 
	 * Throws:
	 * 
	 * IllegalArgumentException: Thrown if the URL does not represent a valid
	 * data source (can't connect or invalid syntax).
	 * 
	 * 
	 * @param URL - the URL containing the specified XML file that the user wants to be analyzed.
	 * @return - AuctionTable constructed from the URL provided by the user. 
	 */

	public static AuctionTable buildFromURL(String URL) {
		userTable = new AuctionTable(); // reset to  a new Table
		DataSource ds = DataSource.connect(URL).load();
		String[] sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
		String[] currentBids = ds.fetchStringArray("listing/auction_info/current_bid");
		double [] bidValues = new double [currentBids.length];
		for (int i = 0 ; i < bidValues.length; i++) {
			String parsingString = currentBids[i];
			String bidValue = parsingString.substring(1,parsingString.length()).replaceAll(",","").replaceAll("\n", "");
			bidValues[i] = Double.parseDouble(bidValue);
		}
		String [] time_left = ds.fetchStringArray("listing/auction_info/time_left");
		int [] timeRemaining = new int [time_left.length];
		for (int i = 0; i < timeRemaining.length ; i++) {
			int hour = 0;
			String parsingString = time_left[i];
			String dayValue = "" + parsingString.substring(0, parsingString.indexOf(" "));
			int dayIntoHour = Integer.parseInt(dayValue) * 24;
			
			if (parsingString.contains(",")){
			String hourValue = parsingString.substring(parsingString.indexOf(",") + 2, parsingString.indexOf("h") - 1);
			hour = Integer.parseInt(hourValue);
			}
			int timeRemains = dayIntoHour + hour;
			timeRemaining[i] = timeRemains;
		}
		String [] idNumbers = ds.fetchStringArray("listing/auction_info/id_num");
		String [] highestBidders = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name/");
		String [] memoryList = ds.fetchStringArray("listing/item_info/memory");
		String [] hardDriveList = ds.fetchStringArray("listing/item_info/hard_drive");
		String [] cpuList = ds.fetchStringArray("listing/item_info/cpu/");
		Auction[] allAuctions = new Auction [idNumbers.length];
		
		for (int i = 0; i < idNumbers.length; i++) {
			String itemInfo = cpuList[i] + " - " + memoryList[i] + " - " + hardDriveList[i];
			allAuctions[i] = new Auction(timeRemaining[i], bidValues[i],
					idNumbers[i], sellerNames[i].replaceAll("\n", "").replaceAll(" ", ""), highestBidders[i], itemInfo.replaceAll("\n", ""));
		}
		
		for (int i = 0; i < allAuctions.length; i++) {
			userTable.put(allAuctions[i].getAuctionID(), allAuctions[i]);
		}
		
		return userTable;
		
	}

	/**
	 * Brief:
	 * 
	 * Manually posts an auction, and add it into the table.
	 * 
	 * 
	 * Parameters:
	 * 
	 * auctionID - the unique key for this object auction - The auction to insert
	 * into the table with the corresponding auctionID
	 * 
	 * 
	 * Preconditions:
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The item will be added to the table if all given parameters are correct.
	 * 
	 * 
	 * Throws:
	 * 
	 * IllegalArgumentException: If the given auctionID is already stored in the
	 * table.
	 * 
	 * @param auctionID - ID of the new Added Item
	 * @param auction - Auction object to be added to the hash.
	 * 
	 */
	public void putAuction (String auctionID, Auction auction) throws IllegalArgumentException {
		
		if (userTable.contains(auctionID)) {
			throw new IllegalArgumentException ("ID already exists!");
		}		
		userTable.put(auctionID, auction);
	}
	
	/**
	 * Brief:
	 * 
	 * Get the information of an Auction that contains the given ID as key
	 * 
	 * 
	 * Parameters:
	 * 
	 * auctionID - the unique key for this object
	 * 
	 * 
	 * Returns:
	 * 
	 * An Auction object with the given key, null otherwise.
	 * 
	 * 
	 * @param auctionID
	 * @return - Auction object with the given auctionID
	 */
	public Auction getAuction(String auctionID) {
		
		return userTable.get(auctionID);
	}
	
	/**
	 * Brief:
	 * 
	 * Simulates the passing of time. Decrease the timeRemaining of all Auction
	 * objects by the amount specified. The value cannot go below 0.
	 * 
	 * 
	 * Parameters:
	 * 
	 * numHours - the number of hours to decrease the timeRemaining value by.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * All Auctions in the table have their timeRemaining timer decreased. If the
	 * original value is less than the decreased value, set the value to 0.
	 * 
	 * 
	 * Throws:
	 * 
	 * IllegalArgumentException: If the given numHours is non positive
	 * 
	 * 
	 * @param numHours
	 * @throws IllegalArgumentException
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException {

		if (numHours < 0) {
			throw new IllegalArgumentException ("Hour value can not be negative");
		}		
		Collection<Auction> list = userTable.values();
		Object[] allAuction = list.toArray();
		userTable.clear();
		for (int i = 0; i < allAuction.length; i++) {
			Auction modified = (Auction) allAuction[i];
			modified.decrementTimeRemaining(numHours);
			userTable.put(modified.getAuctionID(), modified);
		}

	}
	
	/**
	 * Brief:
	 * 
	 * Iterates over all Auction objects in the table and removes them if they are
	 * expired (timeRemaining == 0).
	 * 
	 * 
	 * Postconditions:
	 * 
	 * Only open Auction remain in the table.
	 * 
	 * 
	 */
	public void removeExpiredAuctions() {
		Collection<Auction> list = userTable.values();
		Object[] allAuction = list.toArray();
		userTable.clear();
		for (int i = 0; i < allAuction.length; i++) {
			Auction modified = (Auction) allAuction[i];
			if (modified.getTimeRemaining() > 0)
			userTable.put(modified.getAuctionID(), modified);
		}
	}
	
	

	/**
	 * Brief:
	 * 
	 * Prints the AuctionTable in tabular form.
	 * 
	 * 
	 */
	public void printTable() {

		Collection<Auction> list = userTable.values();
		Object[] allAuction = list.toArray();
		String tableTop = String.format("%-15s%10s%13s%14s%15s%18s%26s%-25s%-2s", "Auction ID|", "Bid", "|", "Seller", "|", "Buyer","|",
				"   Time              |", "Item Info ") + "\n"
				+ "============================================================================================================================================================================================================" ;
		System.out.println(tableTop);
		for (int i = 0; i < allAuction.length; i++) {
			Auction printable = (Auction) allAuction[i];
			System.out.print(printable.toString());
			System.out.println(" ");
		}
	}

}
