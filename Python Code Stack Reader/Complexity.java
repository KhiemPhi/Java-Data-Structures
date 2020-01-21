/**
 * 
 */

/**
 * The class represents the Big-Oh complexity of some block of code The possible
 * orders are restricted to powers of two base types: n, and log_n. The two
 * variables nPower and logPower keep track of what power each of the base types
 * represent in the complexity object
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279
 *
 */
public class Complexity {

	private int nPower;
	private int logPower;

	/**
	 * Construct a base <code>Complexity</code> object with <code>nPower</code> = <code>logPower</code> = 0
	 */
	public Complexity() {

		nPower = 0;
		logPower = 0;
	}

	/** Construct a <code>Complexity</code> object with two given <code>nPower</code> and <code>logPower</code>
	 * @param nPower - input nPower
	 * @param logPower - input logPower
	 */
	public Complexity(int nPower, int logPower) {

		this.nPower = nPower;
		this.logPower = logPower;
	}

	/** Returns the value of nPower of a <code>Complexity</code> object
	 * @return - the value of nPower
	 */
	public int getNPower() {
		return nPower;
	}

	/** Allows access of the variable to other classes to modify <code>nPower</code>.
	 * @param nPower - the value that nPower will be set to
	 */
	public void setNPower(int nPower) {
		this.nPower = nPower;
	}

	/** Returns the value of logPower of a <code>Complexity</code> object
	 * @return - the value of logPower
	 */
	public int getLogPower() {
		return logPower;
	}

	/** Allows access of the variable to other classes to modify <code>nPower</code>.
	 * @param logPower - the value that logPower will be set to
	 */
	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}

	
	/** Generates a String representing the Order of Complexity based on <code>nPower</code> and 
	 * <code>logPower</code>
	 * 
	 * Depending on <code>nPower</code> and <code>logPower</code>, the appropriate String will be generated
	 * 
	 * 
	 */
	public String toString() {

		if (nPower == 0 && logPower != 0) { // when nPower = 0, result only in log(n)^logPower

			if (logPower == 1) {

				return "O(log (n)" + ")";
			}

			return "O(log (n)^" + logPower + ")";

		} else if (nPower != 0 && logPower == 0) { // when logPower = 0, result only in n^nPower
			
			if (nPower == 1) {

				return "O(n" + ")";
			}

			return "O(n^" + nPower + ")";

		} else if (nPower == 0 && logPower == 0) { // when logPower = nPower = 0, result in only O(1)

			return "O(1)";

		} else  {
			
			if (nPower == 1 && logPower == 1) {
				
				return "O(n" + " * log (n)" + ")";
			}
			
			return "O(n^" + nPower + "*log (n)^" + logPower + ")";
		}

	}
	
	

}
