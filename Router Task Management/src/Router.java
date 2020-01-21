/**The Router class creates Router objects that stores Packet objects in a LinkedList
 * and provides operations that can influence the Packet objects that is stored within 
 * a Router object's LinkedList. The operations are enqueue, dequeue and peek, as well
 * as isEmpty.
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */

import java.util.LinkedList;
public class Router extends LinkedList<Packet> {
	protected LinkedList<Packet> packets;
	/**
	 * Initializes the packets LinkedList to an empty packets in order for new elements to be added
	 */
	public Router() {
		
		packets = new LinkedList<Packet>();
	}
	
	/**The enqueue methods add a packet P  in the created a Router object's packets LinkedList
	 * 
	 * @param p - Packet to be added
	 */
	public void enqueue(Packet p) {

		packets.add(p);
		
	}

	/**The dequeue methods removes the first packet of a Router's objects LinkedList. The return type
	 * is Packet, so the final return call must be casted to Packet
	 * 
	 * @return - a Packet object that is first in Router's packets LinkedList
	 */
	public Packet dequeue() {

		return (Packet) packets.removeFirst();
	}

	/**
	 * 
	 * The peek method returns the first packet of a Router object's packets
	 * LinkedList from a method inherited from the parent class LinkedList
	 * 
	 * @return - packet that is at the head of the packets LinkedList.
	 * 
	 */
	public Packet peek() {

		return (Packet) packets.peek();
	}

	/**
	 * The size method returns the size of a Router object's packets 
	 * LinkedList from a method inherited from the parent class LinkedList
	 * 
	 * @return - the size of the packets LinkedList as specified in the constructor.
	 */
	public int size() {

		return packets.size();
	}

	/**
	 * The isEmpty method returns a boolean value based on whether a Router object's
	 * packets LinkedList is empty or not based on the method inherited from the 
	 * the parent class LinkedList.
	 * 
	 * @return - boolean value whether the packets LinkedList is empty or not.
	 * 
	 */
	public boolean isEmpty() {

		return packets.isEmpty();
	}

	/**The toString method returns a String representation of the router buffer in 
	 * in the following format:
	 * {[packet1], [packet2], ... , [packetN]} 
	 * 
	 * @return - Formated string of all the Packets in the packets LinkedList
	 */
	public String toString() {

		String userInterFace = "{";
		Packet p = new Packet();
		if (packets.size() == 0) {
			return "";
		}
		for (int i = 0; i < packets.size(); i++) {

			p = (Packet) packets.get(i);
			if (i == packets.size() - 1) {
				userInterFace = userInterFace + p.toString() + "}";
			} else {
				userInterFace = userInterFace + p.toString() + ", ";
			}

		}
		return userInterFace;
	}


}
