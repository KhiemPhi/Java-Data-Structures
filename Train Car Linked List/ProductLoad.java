/**
 * @author Khiem Dinh Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 		ID: 111667279
 *
 */
public class ProductLoad {

	private String name;
	private double weight;
	private double value;
	private boolean isDangerous;

	/**
	 * Construct a Course object according the various parameters set by the
	 * Constructor.
	 * 
	 * @param name - name of the Load
	 * @param weight - weight of the Load
	 * @param value - value of the Load
	 * @param isDangerous - if the Load is dangerous
	 */
	public ProductLoad(String name, double weight, double value, boolean isDangerous) {

		this.name = name;
		this.weight = weight;
		this.value = value;
		this.isDangerous = isDangerous;
	}

	/**
	 * Getter method for the @param name
	 * 
	 * @return - name of load 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for the @param name
	 * 
	 * @param name - name of Load that would be set to the current Load. 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method for the @param weight
	 * 
	 * @return weight - weight of Load
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Setter method for the @param weight
	 * 
	 * @param weight - weight of Load that would be set to the current Load.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Getter method for the @param value
	 * 
	 * @return value - value of Load
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Setter method for the @param value
	 * 
	 * @param value - value of Load that would be set to the current Load.
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Getter method for the @param isDangerous
	 * 
	 * @return isDangerous - whether load is dangerous or not
	 */
	public boolean isDangerous() {
		return isDangerous;
	}

	/**
	 * Setter method for the @param isDangerous
	 * 
	 * @param isDangerous - set whether load is dangerous or not
	 */
	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}
	
	

}
