/**This Method creates a Stack object BlockStack that inherits the necessary method from 
 * the Stack master class. Allows CodeBlock objects to be pushed, popped into a stack and
 * to peek for these CodeBlock objects in a BlockStack
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279
 *
 */

import java.util.Stack;

public class BlockStack<E> extends Stack<CodeBlock> {
	
	private int size = 0; // size of the stack
	
	/**Pushes a <code>CodeBlock</code> on to the top of the stack
	 * so it is placed where the last element was added. 
	 * 
	 * @param block - the <code>CodeBlock</code> that would be pushed to the stack
	 * <code>BlockStack</code>
	 * 
	 * @return -  Returns a CodeBlock object in the stack which is a
     *      <code>CodeBlock</code> object.
	 * 
	 * 
	 */
	public CodeBlock push(CodeBlock block) {

		size++;
		return (CodeBlock)super.push(block);

	}
	
	/**Pops a <code>CodeBlock</code> at the top of the stack 
	 * <code>BlockStack</code>
	 * 
	 * @return -   Returns the <code>CodeBlock</code> object that was popped from the stack. 
	 * @throws -   This throws an <code>EmptyStackException</code> when the stack is empty.
	 * 
	 */
	public CodeBlock pop() {

		if (isEmpty()) {

			throw new EmtpyStackException("Error: The BlockStack is empty!");
		} else {

			size--;
			return (CodeBlock)super.pop();

		}

	}
	
	/** This method returns the <code>CodeBlock</code> object at the top
     * of the stack without modifying the order. 
     * 
     * @throws - This throws an <code>EmptyStackException</code> when the stack is empty.
	 * 
	 * @return - Returns the <code>CodeBlock</code> object that is on top of the stack. 
	 * 
	 * 
	 */
	public CodeBlock peek() {
		
		if (isEmpty()) {

			return null;
		} else {

			return (CodeBlock)super.peek();
		}
	}

	/** Returns whether if the stack is empty or not. If the
     * stack is empty, it returns true. Else, it returns
     * false.
     * 
     * @return - Returns true if the stack is empty and false if the stack
     * has more than one element.
	 */
	public boolean isEmpty() {
		
		return super.isEmpty();
	}
	
	/** Returns the size of the stack
	 * 
	 * 
	 * @return - the variable size, indicating the size of the <code>BlockStack</code>
	 */
	public int size() {
		
		return size;
	}

}
