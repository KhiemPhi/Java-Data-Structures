
/**
 * @author Khiem Dinh Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 		ID: 111667279
 */
public class TrainCarNode {

	private TrainCarNode prev;
	private TrainCarNode next;
	private TrainCar car;

	/**
	 * Construct an empty TrainCarNode object
	 * 
	 */
	public TrainCarNode() {

	}

	/**Construct a TrainCarNode object with a wrapped Object of type TrainCar
	 * 
	 * @param car - the TrainCar object that would be wrapped in a new TrainCarNode
	 */
	public TrainCarNode(TrainCar car) {

		this.car = car;
	}

	/**Returns the previous connection of a given TrainCarNode
	 * 
	 * @return - prev(the previous connection of the given TrainCarNode)
	 */
	public TrainCarNode getPrev() {
		return prev;
	}

	/**Set a TrainCarNode's previous connection
	 * 
	 * @param prev - the TrainCarNode that the current TrainCarNode shall be connected backwards to
	 */
	public void setPrev(TrainCarNode prev) {
		this.prev = prev;
	}

	/**Returns the next connection of a given TrainCarNode
	 * 
	 * @return - next(the next connection of the given TrainCarNode)
	 */
	public TrainCarNode getNext() {
		return next;
	}

	/**Set a TrainCarNode's next connection
	 * 
	 * @param prev - the TrainCarNode that the current TrainCarNode shall be connected forwards to
	 */
	public void setNext(TrainCarNode next) {
		this.next = next;
	}

	/**Returns a wrapped TrainCar in a TrainCarNode
	 * 
	 * @return - the TrainCar wrapped in the current TrainCarNode
	 */
	public TrainCar getCar() {
		return car;
	}

	/**Wrap a TrainCar object to a current node
	 * 
	 * @param car - TrainCar object to be wrapped
	 */
	public void setCar(TrainCar car) {
		this.car = car;
	}

	/** 
	 * Return a String representation of a current TrainCarNode
	 */
	@Override
	public String toString() {
		return "TrainCarNode [prev=" + prev + ", next=" + next + ", car=" + car + "]";
	}
	
	

}
