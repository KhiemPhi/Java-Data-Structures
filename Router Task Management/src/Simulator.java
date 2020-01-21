
/**The class contains a main method to demonstrate the simulation as described in the assignment
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *  
 *
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
public class Simulator {

	public static final int MAX_PACKETS = 3; // Number of Packets that can be generated in a time.

	Router dispatcher = new Router(); // Level 1 - Router
	int numIntRouters = 0; // number of intermediate routers in the network
	int maxBufferSize = 0; // the maximum number of Packets a Router can accommodate for.
	int minPacketSize = 0; // the minimum size of a Packet
	int maxPacketSize = 0; // the maximum size of a Packet
	int bandwith = 0;
	/*
	 * the maximum number of Packets the Destination router can accept at a given
	 * simulation unit
	 */
	double arrivalProb = 0.0; // the probability of a new packet arriving at the Dispatcher.
	ArrayList <Router> routers = new ArrayList <Router>(numIntRouters); // ArrayList of Intermediate Routers
	int totalServiceTime = 0;
	/*
	 * Contains the running sum of the total time each packet is in the network. The
	 * service time per packet is simply the time it has arrived to the Destination
	 * minus the time when the packet was created. When a packet counter reaches 0,
	 * dequeue it from the router queue and add the time to the total time. Ignore
	 * the leftover Packets in the network when simulation time is up.
	 */
	int totalPacketsArrived = 0;
	int packestDropped = 0;
	/*
	 * this can only happen when sendPacketTo(Collection routers) throws an
	 * exception.
	 */
	int duration = 0;
	
	/**Creates a Simulator Object to be run
	 * 
	 */
	public Simulator() {
		
		
	}
	/**
	 *Runs the simulator as described in the specs. Calculate and return the
	 * average time each packet spends within the network.
	 * 
	 * @return - average time each packet spends within the network.
	 */
	public double simulate() {
		Scanner input = new Scanner (System.in);
		String confirmation = "y";
		double averageRemovalTimes = 0;
		do {			
			routers = new ArrayList <Router>(numIntRouters);
			System.out.println("Starting simulator...");
			try {
				System.out.println("Enter the number of Intermediate Routers:");
				numIntRouters = input.nextInt();			
				input.nextLine(); // clear input
				for (int i = 0; i < numIntRouters; i++) {
					routers.add(new Router());
				} // add new Intermediate Routers to an ArrayList of Routers.
				System.out.println("Enter the arrival probability of a packet:");
				arrivalProb = input.nextDouble();
				input.nextLine(); // clear input
				System.out.println("Enter the maximum buffer size of a router:");
				maxBufferSize = input.nextInt();
				input.nextLine();
				System.out.println("Enter the minimum size of a packet:");
				minPacketSize = input.nextInt();
				input.nextLine();
				System.out.println("Enter the maximum size of a packet:");
				maxPacketSize = input.nextInt();
				input.nextLine();
				System.out.println("Enter the bandwith size:");
				bandwith = input.nextInt();
				input.nextLine();
				System.out.println("Enter the simulation duration:");
				duration = input.nextInt();
				input.nextLine();
			}catch (InputMismatchException ex) {
				input.nextLine(); // clear input stream
				System.out.println("Wrong Input! Can't simulate"); // clear input stream
				continue; // skip iteration with error
			}			
			int counterDuration = 1; // tracking the IDs of Packets		
			int totalPacketsDropped = 0; // packets that would be dropped
			int totalPacketsServed = 0; // packets that would be served
			ArrayList<Integer> removalTime = new ArrayList<Integer>(); //ArrayList of individual removal times of objects
			ArrayList<Integer> averageRemovalTime = new ArrayList<Integer>(); //ArrayList of averageRemovalTimes
			while (counterDuration <= duration) {
				System.out.println("Time: " + counterDuration);	
				int bandwithCounter = 0; // check if bandwith is full
				int subPacketCounter = 0; // check to see if any packets were generated
				for (int i = 0; i < MAX_PACKETS; i++) {
					int index;					
					if (Math.random() < arrivalProb) {						
						totalPacketsArrived++; // total Packets Arrived + Id of Packet
						subPacketCounter++; // increment total packet generated
						int size = randInt(minPacketSize, maxPacketSize); // generates random packet size
						int timeToDest = size / 100; // determines timeToDest of Packet generated
						removalTime.add(timeToDest); // add the time needed to be removed
						Packet p = new Packet(totalPacketsArrived, counterDuration, timeToDest, size); // creates new packet according to specifications
						System.out.println("Packet " + totalPacketsArrived + " arrives at dispatcher with size " + size);
						index = sendPacketTo(routers, maxBufferSize); // Determines which intermediate router the Packet would be sent to
						if (index == -1) {
							System.out.println("Network congested. Packet " + totalPacketsArrived + " is dropped.");
							totalPacketsDropped++;
						} else {
							routers.get(index).enqueue(p);
							System.out.println("Packet " + totalPacketsArrived + " sent to " + "Router " + (index+1) );
						}
					}else if (i == 2 && subPacketCounter == 0) {
						System.out.println("No Packets Arrived");
					}
				}
				if (counterDuration > 1) { // check for removal of Packets
					for (int i = 0; i < numIntRouters; i++) {
						Router a = routers.get(i);
						if (a.peek() != null) {
							if (a.peek().getTimeToDest() == 0 && bandwithCounter != bandwith) {
								System.out.println(
										"Packet " + a.peek().getId() + " has successfully arrived at its destination:"
												+ " +" + removalTime.get(a.peek().getId() - 1));
								averageRemovalTime.add(removalTime.get(a.peek().getId() - 1));
								a.dequeue();
								bandwithCounter++;
								totalPacketsServed++;
							} else {
								a.peek().setTimeToDest(a.peek().getTimeToDest() - 1);
								if (a.peek().getTimeToDest() == 0 && bandwithCounter != bandwith) {
									System.out.println("Packet " + a.peek().getId()
											+ " has successfully arrived at its destination:" + " +"
											+ removalTime.get(a.peek().getId() - 1));
									averageRemovalTime.add(removalTime.get(a.peek().getId() - 1));
									a.dequeue();
									bandwithCounter++;
									totalPacketsServed++;
								}
							}
						}

					}

				}
				for (int i = 0; i < numIntRouters; i++) {
					int index = i+1;
					System.out.println("R" + (index) + ": " + routers.get(i).toString() );
				} // Prints String Representation of Packets
				counterDuration++; //Increase the time increment
				System.out.println();
			}
			System.out.println("Simulation ending...");
			System.out.println("Total service time: " + totalServiceTime(averageRemovalTime));
			System.out.println("Total packets served: " + totalPacketsServed);
			System.out.println("Average service time: " + averageServiceTime(averageRemovalTime));
			averageRemovalTimes = averageServiceTime(averageRemovalTime);
			System.out.println("Total packets dropped: " + totalPacketsDropped);
			System.out.println("Do you want to try another simulation? (y/n) :");
			confirmation = input.nextLine();
			totalPacketsArrived = 0;
		}while (confirmation.toLowerCase().equals("y"));
		input.close();
		System.out.println("Program terminating successfully...");
		return averageRemovalTimes;
	}

	/**The method randomly generates a Packet size to be set for a Packet
	 * 
	 * @param minVal - minimum size of Packet
	 * @param maxVal - maximum size of Packet
	 * @return - size of Packet
	 */
	private int randInt(int minVal, int maxVal) {
		return (int) (Math.random() * (maxVal - minVal) + 1) + minVal;
	}
	
	/**
	 * The method goes through the list of Intermediate Routers and determine which
	 * Router can accept a new Packet. . Find the router with the most free buffer
	 * space (contains least Packets), and return the index of the router. If there
	 * are multiple routers, any corresponding indices will be acceptable.
	 * 
	 *  
	 * @param routers - List of Intermediate Routers
	 * @param max     - Maximum sizes of the Intermediate Routers
	 * @return - the index for the Router that can accept a new package
	 */
	public static int sendPacketTo(ArrayList<Router> routers, int max) {
		int freeIndex = 0;
		boolean allMax = true;
		ArrayList<Integer> routerCapacities = new ArrayList<Integer>();
		for (int i = 0; i < routers.size(); i++) {
			routerCapacities.add(routers.get(i).size());			
		}
		for (int i = 0; i < routerCapacities.size(); i++) {
			if (routerCapacities.get(i) != max)
				allMax = false;
		} // check if all Routers are max out with their capacity.
		if (allMax) {
			return -1; 
		} else {
			freeIndex = routerCapacities.indexOf(Collections.min(routerCapacities)); // find the index of the min.
			return freeIndex; 
		}
	}
	
	/**The method takes in an ArrayList of serviceTimes of all the packets that had been processed
	 * and finds the average of all the serviceTimes of all the packets that had been processed.
	 * 
	 * @param serviceTimes - ArrayList of ServiceTimes
	 * @return - the average time that it takes a package to be processed
	 */
	public static double averageServiceTime (ArrayList<Integer> serviceTimes) {		
		int i;
		double sum = 0;
		for(i = 1; i < serviceTimes.size(); i++)
		    sum += serviceTimes.get(i);
		return sum/serviceTimes.size();
	}
	
	/**The method takes in an ArrayList of serviceTimes of all the packets that had been processed
	 * and finds the sum of all the serviceTimes of all the packets that had been processed.
	 * 
	 * @param serviceTimes - ArrayList of ServiceTimes
	 * @return - the total time that it takes for all packages to be processed
	 */
	public static int totalServiceTime (ArrayList<Integer> serviceTimes) {		
		int i;
		int sum = 0;
		for(i = 1; i < serviceTimes.size(); i++)
		    sum += serviceTimes.get(i);
		return sum;
	}

	/**
	 * Main method to run the simulation based on the Simulate Method.
	 */
	public static void main(String[] args) {			
		Simulator a = new Simulator();
		a.simulate();
	}
}
