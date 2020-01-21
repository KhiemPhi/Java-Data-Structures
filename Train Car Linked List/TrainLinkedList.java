/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook  
 * University ID: 111667279
 * 
 *         The TrainLinkedList class forms a doubly linked list made of nodes
 *         formed by the wrapping the abstract data type TrainCar into node.
 *         Within the class, several methods are provided to manipulate the
 *         doubly linked list.
 * 
 * 
 */
public class TrainLinkedList {

	private TrainCarNode head;
	private TrainCarNode tail;
	private TrainCarNode cursor;
	private int size = 0;
	private double length = 0;
	protected double weight = 0;
	protected double value = 0;
	private boolean dangerous = false;

	/**
	 * Construct an empty doubly linked list of zero TrainCar objects by setting the
	 * cursor, head and tail to null
	 * 
	 * Postconditions: This TrainLinkedList has been initialized to an empty linked list. head,
	 * tail, and cursor are all set to null.
	 * 
	 * 
	 */
	public TrainLinkedList() {

		head = tail = cursor = null;

	}

	/**
	 * Returns a reference to the TrainCar at the node currently referenced by the
	 * cursor. 
	 * Preconditions: The list is not empty (cursor is not null)
	 *  
	 * @return the method returns a reference to the TrainCar object at the Cursor
	 *         position
	 */
	public TrainCar getCursorData() throws EndOfListException {

		if (cursor != null)
			return cursor.getCar();
		else
			throw new EndOfListException("List is empty!");
	}

	/**
	 * Places a TrainCar car object in the node currently referenced by the cursor.
	 * Preconditions: The list is not empty (cursor is not null).
	 * 
	 * Postconditions: The cursor node now contains a reference to car as its data.
	 * 
	 * @param car: The TrainCar object that will be placed into the cursor position    
	 */
	public void setCursorData(TrainCar car) throws EmptyCarException {

		if (cursor != null) {

			cursor = new TrainCarNode(car);

		} else {

			throw new EmptyCarException("Car is empty!");
		}
	}

	/**
	 * Moves the cursor to point at the next TrainCarNode. 
	 * Preconditions: The list is not empty (cursor is not null) and cursor does not currently reference the
	 * tail of the list. 
	 * 
	 * Postconditions: The cursor has been advanced to the next TrainCarNode, or has 
	 * remained at the tail of the list.
	 * 
	 * 
	 */
	public void cursorForward() throws OutOfRangeException {

		if (cursor != null && cursor != tail) {

			cursor = cursor.getNext();
			System.out.println("Cursor moved forward");
		} else if (cursor.getNext() == null) {

			throw new OutOfRangeException("No next car, can not move cursor forward");

		} else {

			cursor = tail;
		}
	}

	/** Moves the cursor to point at the previous TrainCarNode. 
	 *  Preconditions: The list is not empty (cursor is not null) and the cursor does not 
	 *  currently reference the head of the list. 
	 *  
	 *  Postconditions: The cursor has been moved back to the previous TrainCarNode, or 
	 *  has remained at the head of the list.
	 * 
	 */
	public void cursorBackward() throws OutOfRangeException {

		if (cursor != null && cursor != head) {
			cursor = cursor.getPrev();
			System.out.println("Cursor moved backward");
		} else if (cursor == head) {
			throw new OutOfRangeException("No car behind, can not move cursor backwards");
		} else {
			cursor = head;

		}

	}

	/** Moves the cursor to point at the head of the TrainCarList. 
	 *  Preconditions: The list is not empty (cursor is not null) and the cursor does not 
	 *  currently reference the head of the list. 
	 *  
	 *  Postconditions: The cursor has been moved back to the head TrainCarNode, or 
	 *  has remained at the head of the list.
	 * 
	 */
	public void cursorHeadReset() {

		if (head != null && cursor != null)
			cursor = head;
		else
			System.out.println("Can not reset cursor to head");

	}

	/**Determines the number of TrainCar objects currently on the train
	 * 
	 * @return The number of TrainCar objects on this train.
	 */
	public int size() {

		if (head != null)
			return size;
		else {
			return 0;
		}
	}

	/**
	 * Inserts a car into the train after the cursor position. 
	 * 
	 * Preconditions: This TrainCar object has been instantiated 
	 * 
	 * Postconditions: The new TrainCar has been inserted into the train after the 
	 * position of the cursor. All TrainCar objects previously on the train are still on the
	 * train, and their order has been preserved. The cursor now points to the inserted car.
	 * 
	 * @param newCar - the new TrainCar to be inserted into the train.
	 * @throws IllegalArgumentException - indicates that newCar is null
	 */
	public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException {

		if (newCar != null) {
			TrainCarNode carNode = new TrainCarNode(newCar);
			if (cursor != null) {
				carNode.setNext(cursor.getNext()); // setting new Node to the element after the cursor
				carNode.setPrev(cursor); // setting new Node's link back to cursor

				if (cursor.getNext() == null) {

					tail = carNode; // make new Node tail when there isn't another element to link on
				} else {

					cursor.getNext().setPrev(carNode); // set the Node of the element after cursor back into new Node
				}
				cursor.setNext(carNode); // establish next link for cursor.

			} else {

				head = carNode;
				tail = carNode;
			}
			cursor = carNode; // increment cursor
			size++;
			length = length + this.getCursorData().getCarLength();
			weight = weight + this.getCursorData().getCarWeight();
			

		} else {

			throw new IllegalArgumentException("Unable to add another TrainCar!");
		}

	}

	/**
	 * Removes the TrainCarNode referenced by the cursor and returns the TrainCar
	 * contained within the node. 
	 * 
	 * Preconditions: The cursor is not null.
	 * 
	 * Postconditions: The TrainCarNode referenced by the cursor has been removed from the 
	 * train. The cursor now references the next node, or the previous node if no next node 
	 * exists.   
     *
	 * @return the TrainCar object that was removed from the linked list
	 */
	public TrainCar removeCursor() throws EndOfListException {

		if (cursor == null)
			throw new EndOfListException("Error: The given cursor" +
					"is null.");
			
		TrainCar toBeRemoved = cursor.getCar();

		if (cursor.getPrev() == null) {
			if (cursor.getNext() == null) {
				cursor = null;
				head = null;
				tail = null;
			} else {
				// Runs when cursor is at the head but list has > 1 trips.
				cursor.getNext().setPrev(cursor.getPrev()); // get the next element after cursor at head, to set back
															// into a null connection
				head = cursor.getNext(); // setting the element after cursor as head;
				cursor = head; // move cursor to head
			}
		} else {
			if (cursor != tail) {
				cursor.getNext().setPrev(cursor.getPrev());
				cursor.getPrev().setNext(cursor.getNext());
				cursor = cursor.getNext();
			} else {
				cursor.getPrev().setNext(cursor.getNext());
				tail = cursor.getPrev();
				cursor = tail;

			}
		}
		size--;
		length = length - toBeRemoved.getCarLength();
		weight = weight - toBeRemoved.getCarWeight();
		return toBeRemoved;

	}

	/**Returns the total length of the train in meters.
	 *  
	 * @return The sum of the lengths of each TrainCar in the train. 
	 */
	public double getLength() {

		if (head != null)
			return length;
		else {
			return 0;
		}
	}

	/**Returns the total weight in tons of the train. Note that the weight of the train is 
	 * the sum of the weights of each empty TrainCar, plus the weight of the 
	 * ProductLoad carried by that car. 
	 * 
	 * @return The sum of the weight of each TrainCar plus the sum of the ProductLoad 
	 * carried by that car. 
	 */
	public double getWeight() {

		if (head != null)
			return weight;
		else {
			return 0;
		}
	}

	/**Returns the total value of product carried by the train. 
	 * 
	 * @return The sum of the values of each TrainCar in the train. 
	 */
	public double getValue() {

		if (head != null)
			return value;
		else {
			return 0;
		}
	}

	/**Whether or not there is a dangerous product on one of the TrainCar 
	 * objects on the train. 
	 * 
	 * @return Returns true if the train contains at least one TrainCar carrying a 
	 * dangerous ProductLoad, false otherwise. 
	 */
	public boolean isDangerous() {

		if (head != null)
			return dangerous;
		else {
			return false;
		}
	}

	/**Searches the list for all ProductLoad objects with the indicated name and 
	 * sums together their weight and value (Also keeps track of whether the product is 
	 * dangerous or not), then prints a single ProductLoad record to the console. 
	 * 
	 * @param name - the name of the ProductLoad to find on the train. 
	 */
	public void findProduct(String name) {

		this.cursorHeadReset();
		int matches = 0;
		double value = 0;
		double weight = 0;
		String yesOrNo = "NO";
		for (int i = 0; i < this.size(); i++) {

			if (this.getCursorData().getLoad().getName().equals(name)) {

				matches++;
				value = value + this.getCursorData().getLoad().getValue();
				weight = weight + this.getCursorData().getLoad().getWeight();
				if (this.getCursorData().getLoad().isDangerous() == true) {
					yesOrNo = "YES";
				}
			}
			cursor = cursor.getNext();

		}
		String setUp = String.format("%5s%12s%12s%12s", "Name", "Weight(t)", "Value($)", "Dangerous") + "\n"
				+ "==========================================";
		String table = "\n" + String.format("%5s%12s%12s%12s", name, String.format("%,.2f", weight),
				String.format("%,.2f", value), yesOrNo);

		if (matches > 0) {
			System.out.println("The following products were found on " + matches + " cars:");
			System.out.print(setUp + table);
		} else {

			System.out.println("No record of " + name + " on board train.");
		}

	}

	/**
	 * Removes all dangerous cars from the train, maintaining the order of the cars
	 * in the train.
	 * 
	 * Postconditions: All dangerous cars have been removed from this train. The
	 * order of all non-dangerous cars must be maintained upon the completion of
	 * this method.
	 * 
	 */
	public void removeDangerousCars() {
		
		for (int i = 0; i < this.size(); i++) {

			if (cursor.getCar() != null && cursor.getCar().getLoad() != null) {
				if (cursor.getCar().getLoad().isDangerous() == true) {
						this.removeCursor();
						if (cursor.getNext() == null) {
						break;
						}else {
							cursor = cursor.getNext();
						}
				}else {
					if (cursor.getNext() == null) {
						break;
						}else {
							cursor = cursor.getNext();
						}
				}
			}else {
				if (cursor.getNext() != null) {
					cursor = cursor.getNext();
					}else {
						break;
					}
			}
		}
		System.out.println("Dangerous cars sucessfully removed from the train.");
	}

	/**Prints a neatly formatted table of the car number, car length, car weight, 
	 * load name, load weight, load value, and load dangerousness for all of the car on the 
	 * train. 
	 * 
	 * There should be a record for each TrainCar printed to the console, numbered from 1 to 
	 * n. For each car, print the data of the car, followed by the ProductLoad data if the 
	 * car is not empty. If the car is empty, print "Empty" for name, 0 for weight and 
	 * value, and "NO" for dangerousness
	 * 
	 */
	public void printManifest() {

		String table1 = String.format("%-21s%16s", "CAR:", "LOAD:");
		String table2 = String.format("%5s%12s%12s%3s%8s%13s%12s%13s", "Num", "Length (m)", "Weight (t)", "|", "Name",
				"Weight(t)", "Value($)", "Dangerous");
		String table3 = "==============================================================================";
		String sumTotal = table1 + "\n" + table2 + "\n" + table3 + "\n";
		String allTrain = "";
		String cursorString = "";
		TrainCarNode pointer = head;
		for (int i = 0; i < this.size(); i++) {

			if (pointer.getCar() != null) {

				if (pointer.equals(cursor)) {

					if (pointer.getCar().isEmpty() == true) {
						cursorString = "->" + cursorString
								+ String.format("%3s%12s%12s%3s%8s%13s%12s%13s", i + 1, pointer.getCar().getCarLength(),
										pointer.getCar().getCarWeight(), "|", "Empty", "0.0", "0.0", "NO")
								+ "\n";
					} else {
						if (pointer.getCar().getLoad().isDangerous() == false)
							cursorString = "->" + cursorString + String.format("%3s%12s%12s%3s%8s%13s%12s%13s", i + 1,
									pointer.getCar().getCarLength(), pointer.getCar().getCarWeight(), "|",
									pointer.getCar().getLoad().getName(), pointer.getCar().getLoad().getWeight(),
									pointer.getCar().getLoad().getValue(), "NO") + "\n";
						else
							cursorString = "->" + cursorString + String.format("%3s%12s%12s%3s%8s%13s%12s%13s", i + 1,
									pointer.getCar().getCarLength(), pointer.getCar().getCarWeight(), "|",
									pointer.getCar().getLoad().getName(), pointer.getCar().getLoad().getWeight(),
									pointer.getCar().getLoad().getValue(), "YES") + "\n";

					}
					allTrain = allTrain + cursorString;

				} else if (pointer.getCar().isEmpty() == true) {
					allTrain = allTrain
							+ String.format("%5s%12s%12s%3s%8s%13s%12s%13s", i + 1, pointer.getCar().getCarLength(),
									pointer.getCar().getCarWeight(), "|", "Empty", "0.0", "0.0", "NO")
							+ "\n";
				} else {
					if (pointer.getCar().getLoad().isDangerous() == false)
						allTrain = allTrain + String.format("%5s%12s%12s%3s%8s%13s%12s%13s", i + 1,
								pointer.getCar().getCarLength(), pointer.getCar().getCarWeight(), "|",
								pointer.getCar().getLoad().getName(), pointer.getCar().getLoad().getWeight(),
								pointer.getCar().getLoad().getValue(), "NO") + "\n";
					else
						allTrain = allTrain + String.format("%5s%12s%12s%3s%8s%13s%12s%13s", i + 1,
								pointer.getCar().getCarLength(), pointer.getCar().getCarWeight(), "|",
								pointer.getCar().getLoad().getName(), pointer.getCar().getLoad().getWeight(),
								pointer.getCar().getLoad().getValue(), "YES") + "\n";

				}
			} else {
				i--;

			}

			if (pointer.getNext() != null) {

				pointer = pointer.getNext();
			} else {
				break;
			}

		}

		System.out.print(sumTotal + allTrain);
	}

	/** Returns a neatly formatted String representation of the train. 
	 * 
	 * A neatly formatted string containing information about the train, 
	 * including it's size (number of cars), length in meters, weight in tons,
	 * value in dollars, and whether it is dangerous or not. 
	 * 
	 */
	public String toString() {

		if (dangerous == false) {
			return "Train: " + size + " cars, " + String.format("%,.2f", length) + " meters, "
					+ String.format("%,.2f", weight) + " tons, $" + String.format("%,.2f", value) + " value, "
					+ " not dangerous.";
		} else {
			return "Train: " + size + " cars, " + String.format("%,.2f", length) + " meters, "
					+ String.format("%,.2f", weight) + " tons, $" + String.format("%,.2f", value) + " value, "
					+ " DANGEROUS.";
		}
	}

}
