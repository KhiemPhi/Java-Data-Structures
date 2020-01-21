/**
* @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */
import big.data.*;	
import java.io.Serializable;

public class Auction implements Serializable  {
	
	private int timeRemaining;
	private double currentBid;
	private String auctionID;
	private String sellerName= "";
	private String buyerName = "";
	private String itemInfo;
	
	
	public Auction() {
		
	}
	
	public Auction(int timeRemaining, double currentBid, String auctionID, String sellerName, String buyerName,
			String itemInfo) {
		super();
		this.timeRemaining = timeRemaining;
		this.currentBid = currentBid;
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
	}

	/**Brief: Getter Method for the timeRemaining variable
	 * 
	 * Preconditions: timeRemaining is properly calibrated
	 * 
	 * Postconditions: None
	 * 
	 * @return - the value of timeRemaining
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}

	/**Brief: Getter Method for the currentBid variable
	 * 
	 * Preconditions: currentBid is properly calibrated
	 * 
	 * Postconditions: None
	 * 
	 * @return - the value of currentBid
	 */
	public double getCurrentBid() {
		return currentBid;
	}

	/**Brief: Getter Method for the auctionID variable
	 * 
	 * Preconditions: auctionID is properly calibrated
	 * 
	 * Postconditions: None
	 * 
	 * @return - the value of auctionID
	 */
	public String getAuctionID() {
		return auctionID;
	}

	/**Brief: Getter Method for the sellerName variable
	 * 
	 * Preconditions: sellerName is properly calibrated
	 * 
	 * Postconditions: None
	 * 
	 * @return - the value of sellerName
	 */	public String getSellerName() {
		return sellerName;
	}

	/**
	 * Brief: Getter Method for the buyerName variable
	 * 
	 * Preconditions: buyerName is properly calibrated
	 * 
	 * Postconditions: None
	 * 
	 * @return - the value of buyerName
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**Brief: Getter Method for the itemInfo variable
	 * 
	 * Preconditions: timeRemaining is properly calibrated
	 * 
	 * Postconditions: None
	 * 
	 * @return - the value of itemInfo 
	 */
	public String getItemInfo() {
		return itemInfo;
	}

	/**
	 * Brief:
	 * 
	 * Decreases the time remaining for this auction by the specified amount. If
	 * time is greater than the current remaining time for the auction, then the
	 * time remaining is set to 0 (i.e. no negative times).
	 * 
	 * 
	 * Postconditions:
	 * 
	 * timeRemaining has been decremented by the indicated amount and is greater
	 * than or equal to 0.
	 * 
	 * 
	 * @param time - the time that timeRemaining would be decreased by.
	 */
	public void decrementTimeRemaining(int time) {
		if (timeRemaining - time >= 0)
			timeRemaining = timeRemaining - time;
		else
			timeRemaining = 0;
	}
	
	/**
	 * Brief:
	 * 
	 * Makes a new bid on this auction. If bidAmt is larger than currentBid, then
	 * the value of currentBid is replaced by bidAmt and buyerName is is replaced by
	 * bidderName.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * The auction is not closed (i.e. timeRemaining > 0).
	 * 
	 * 
	 * Postconditions:
	 * 
	 * currentBid Reflects the largest bid placed on this object. If the auction is
	 * closed, throw a ClosedAuctionException.
	 * 
	 * 
	 * Throws:
	 * 
	 * ClosedAuctionException: Thrown if the auction is closed and no more bids can
	 * be placed (i.e. timeRemaining == 0).
	 * 
	 * 
	 * @param bidderName - name of new Bidder
	 * @param bidAmt - amount of money made for new Bid
	 * @throws ClosedAuctionException - Thrown if the auction is closed and no more bids can
	 * be placed (i.e. timeRemaining == 0).
	 */
	public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException {

		if (timeRemaining < 0) {
			throw new ClosedAuctionException("The Bidding Process is Closed!");
		} else {
			if (bidAmt > currentBid) {
				currentBid = bidAmt;
				buyerName = bidderName;
			}
		}
	}
	/**Brief: Returns string of data members in tabular form.
	 * 
	 * 
	 * Preconditions: The values for auctionId, currentBid, sellerName, buyerName, timeRemaining, itemInfo
	 * are all properly calibrated.
	 * 
	 * Postconditions: None
	 * 
	 */
	public String toString() {
		
		String auctionIdModify = auctionID + " |";
		String currentBidModify = "$  " + currentBid ;
		String sellerNameModify = sellerName ;
		String buyerNameModify = "  " + buyerName ;
		String timeRemainingModify = "";
		if (timeRemaining < 100) {
			timeRemainingModify = "    " + timeRemaining + " hours";
		} else {
			timeRemainingModify = " " + timeRemaining + " hours";
		}
		String itemInfoModify = " " + itemInfo;
		String finalFormatString = String.format("%-10s%16s%10s%18s%11s%29s%15s%12s%10s%2s", auctionIdModify, currentBidModify,"|",
				sellerNameModify,"|", buyerNameModify,"|", timeRemainingModify, "|", itemInfoModify);
		return finalFormatString;
	}

}
