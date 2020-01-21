/**
 * 
 */

/** The CodeBlock class describes a nested block of code, and generate a CodeBlock object that 
 * refers to this nested block. The types of blocks under consideration are "def", 
 * "for", "while", "if", "else", and "elif" blocks.
 * 
 * When parsing the blocks of Python code for the keywords representing the blocks, 
 * accidentally parsing the "for" in "fortune" (which might be a variable name) as the start of a for block 
 * is unacceptable. To avoid this, there is a single space (" ") before and after the keyword before starting a new block. 
 * This was factored into consideration in creating the constant array with the blocks of code as 
 * elements of the array. To enable unchanging access to these variables, their addresses in the array is 
 * stored permanently as constant integers.
 * 
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279
 *
 */
public class CodeBlock {
	
	public static final String[] BLOCK_TYPES = {"def ", " for ", "while ", " if ", " else ", " elif "};
	public static final int DEF = 0;
	public static final int FOR = 1;
	public static final int WHILE = 2;
	public static final int IF = 3;
	public static final int ELSE = 4;
	public static final int ELIF = 5;
	
	private Complexity blockComplexity; // represents the order of complexity the block ignoring the statements 
	                                    //inside
										// e.g. O(n) for a while block looping from n to 1
	
	 private Complexity highestSubComplexity; // represents the highest complexity of all the blocks nested 
											 // inside this block
	private String name; /*
	  	keep track of the nested structure of the blocks. The first block in the stack will always be 
	  	named "1". All blocks included directly under a block will be numbered increasingly using a dot "."
	  	 (e.g. blocks nested under block 1 will start with "1.1" and proceed to "1.2", "1.3", etc). 
	  	 Similarly, all blocks included directly under the block named "1.2" will be numbered 
	  	 "1.2.1", "1.2.2", "1.2.3", etc
	*/
	private String loopVariable = ""; // used only for while blocks 
	// should only be updated when a while block is traced and the name of it's loop variable 
	// has been determined
	
	
	
	
	
	/**Constructs a <code> CodeBlock </code> object that according to the specify parameters. 
	 * 
	 * 
	 * @param blockComplexity - the <code> blockComplexity </code> that the new <code> CodeBlock </code> 
	 * would be set to.  
	 * @param highestSubComplexity - the <code> highestSubComplexity </code> that the new 
	 * <code> CodeBlock </code> would be set to.  
	 * @param name - the <code> name </code> that the new <code> CodeBlock </code> 
	 * would be set to.  
	 * @param loopVariable - - the <code> loopVariable </code> that the new <code> CodeBlock </code> 
	 * would be set to.  
	 */
	public CodeBlock(Complexity blockComplexity, Complexity highestSubComplexity, String name, String loopVariable) {
		this.blockComplexity = blockComplexity;
		this.highestSubComplexity = highestSubComplexity;
		this.name = name;
		this.loopVariable = loopVariable;
	}

	/**Constructs an empty <code>CodeBlock</code> object where all 
	 * the member variables of CodeBlock is set to empty.
	 * 
	 */
	public CodeBlock() {
		blockComplexity = new Complexity();
		highestSubComplexity = new Complexity();
		name = "";
		loopVariable = "";

	}
	
	public CodeBlock(Complexity blockComplexity, Complexity highestSubComplexity) {
		this.blockComplexity = blockComplexity;
		this.highestSubComplexity = highestSubComplexity;
	}
	
	/** Returns the order of complexity of an overall block of code
	 * @return - the value of <code>BlockComplexity</code>, the order of complexity of block 
	 * ignoring the statements inside. Providing access to member variable to other classes.
	 */
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}

	public CodeBlock(Complexity blockComplexity, Complexity highestSubComplexity, String name) {
		this.blockComplexity = blockComplexity;
		this.highestSubComplexity = highestSubComplexity;
		this.name = name;
	}

	/** Set the order of complexity for an overall block of code
	 * @param blockComplexity - setting the blockComplexity to a given Complexity
	 */	
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}
	
	/** Returns the order of complexity of the highest complexity of all the blocks nested 
	 * inside a block 
	 * @return - the value of <code>BlockComplexity</code>, the highest complexity of all the blocks nested 
	 * inside a block
	 */	
	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}
	
	/** Set the highest order of complexity for an overall block of code
	 * @param highestSubComplexity - setting the highestSubComplexity to a given Complexity
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}
	
	
	/** Return the the nested structure of the blocks. The first block in the stack will always be 
	  	named "1". All blocks included directly under a block will be numbered increasingly using a dot "."
	  	 (e.g. blocks nested under block 1 will start with "1.1" and proceed to "1.2", "1.3", etc). 
	  	 Similarly, all blocks included directly under the block named "1.2" will be numbered 
	  	 "1.2.1", "1.2.2", "1.2.3", etc
	  	 
	 * @return - the name of the nested structures of a loop block that is being keeping track of
	 */
	public String getName() {
		return name;
	}
	
	/** Set the name of a nested structure to @param name
	 * @param name - the name that would set to a nested structure
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Returns the <code>loopVariable</code> of a while block that was updated when it was traced.
	 * @return - the loopVariable of a while block that is being traced.
	 */
	public String getLoopVariable() {
		return loopVariable;
	}
	
	/** Set the loopVariable of a while loop to <code>loopVariable</code>
	 * @param loopVariable - the variable of a while loop that loopVariable would be set to.
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
}
