/**This class represents a packet that will be sent through the network.
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */

public class Packet {

	public static int packetCount = 0; 	
	/*
	 * ID of packet, increment by 1 whenever new Packet object is created
	 *
	 */
	
	int id = 0; 	
	/*
	 * a unique identifier for the packet. This will be systematically determined by
	 * using packetCount
	 */
			
	int packetSize = 0; 
	/*
	 * The size of the packet being sent. This value is randomly determined by the
	 * simulator by using the Math.random() method.
	 */
	
	int timeArrive; //the time this Packet is created should be recorded in this variable
	
	int timeToDest;
	/*
	 * This variable contains the number of simulation units that it takes for a
	 * packet to arrive at the destination router. The value will start at one
	 * hundredth of the packet size, that is: packetSize/100. At every simulation
	 * time unit, this counter will decrease. Once it reaches 0, we can assume that
	 * the packet has arrived at the destination.
	 */
	
	/**The Packet constructor creates a new Packet with the specified parameters
	 * 
	 * 
	 * @param id - ID of a Packet as specified
	 * @param packetSize - size of a Packet as specified
	 * @param timeArrive - timeArrived of a Packet as specified
	 * @param timeToDest - timeToDest of Packet as specified.
	 */
	public Packet(int id, int timeArrive, int timeToDest, int packetSize) {
		this.id = id;
		this.timeArrive = timeArrive;
		this.timeToDest = timeToDest;
		this.packetSize = packetSize;
		
	}
	
	/** Empty Packet constructor, designed to create empty packets with other values to be set
	 * using setters at another date.
	 * 
	 */
	public Packet() {
		
	}
	
	/**Getter Method for the id variable
	 * 
	 * @return - ID of a Packet
	 */
	public int getId() {
		return id;
	}	

	/**Setter Method for the id variable
	 * 
	 * @param id - the value that the variable id would be set to
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**Getter Method for the packetSize variable
	 * 
	 * @return - size of a Packet
	 */
	public int getPacketSize() {
		return packetSize;
	}

	/**Setter Method for the packetSize variable
	 * 
	 * @param id - the value that the variable packetSize would be set to
	 */

	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	
	/**Getter Method for the timeArrive variable
	 * 
	 * @return - time instant in which a packet arrives to an intermediate router
	 */
	public int getTimeArrive() {
		return timeArrive;
	}

	/**Setter Method for the timeArrive variable
	 * 
	 * @param timeArrive - the value that the variable timeArrive would be set to
	 */
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}


	/**Getter Method for the timeToDest variable
	 * 
	 * @return - the time it takes for  a packet to leave an intermediate router
	 */
	public int getTimeToDest() {
		return timeToDest;
	}

	/**Setter Method for the timeToDest variable
	 * 
	 * @param timeToDest - the value that the variable timeToDest would be set to
	 */
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}

	
	/** The toString method prints the Packet object in the following format:
	 * [id, timeArrive, timeToDest]
	 * 
	 * @return - String representation of a packet.
	 */
	public String toString() {

		if (packetSize == 0 && timeArrive == 0 && timeToDest == 0) {
			return "... ... ...";
		} else {
			return "[" + id + "," + timeArrive + "," + timeToDest + "]";
		}

	}

}
