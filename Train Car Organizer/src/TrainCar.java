
/**
 * @author Khiem Dinh Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 		ID: 111667279
 */
public class TrainCar {

	private double carLength;
	private double carWeight;
	private ProductLoad load;

	/**Construct a TrainCar object with a new carLength, carWeight, and load
	 * 
	 * @param carLength - length of new TrainCar
	 * @param carWeight - weight of new TrainCar
	 * @param load - load of new TrainCar
	 */
	public TrainCar(double carLength, double carWeight, ProductLoad load) {

		this.carLength = carLength;
		this.carWeight = carWeight;
		this.load = load;
	}
	
	/**Construct a TrainCar object with a new carLength, carWeight
	 * 
	 * @param carLength - length of new TrainCar
	 * @param carWeight - weight of new TrainCar
	 */
	public TrainCar(double carLength, double carWeight) {

		this.carLength = carLength;
		this.carWeight = carWeight;
		load = null;
	}


	/**Getter method for the @param carLength
	 * 
	 * @return - length of TrainCar
	 */
	public double getCarLength() {
		return carLength;
	}

	/**Getter method for the @param carWeight
	 * 
	 * @return - weight of TrainCar
	 */
	public double getCarWeight() {
		return carWeight;
	}

	/**Getter method for the @param load
	 * 
	 * @return load of TrainCar
	 */
	public ProductLoad getLoad() {
		return load;
	}

	/**Setter method for the @param courseName
	 * @param load - load to be set on TrainCar object
	 */
	public void setLoad(ProductLoad load) {
		this.load = load;
	}

	/**Check if the load is empty
	 * @return - boolean according to whether load is empty
	 */
	public boolean isEmpty() {

		if (load == null) {

			return true;
		} else {

			return false;
		}
	}

}
