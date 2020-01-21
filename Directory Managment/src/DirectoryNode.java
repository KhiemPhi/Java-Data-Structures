/**This class represents a node in the file tree. It contains three node references
 * left, middle and right. In addition to these references, the class contains a String
 * member variable name, that refers to the name of the node in the tree
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */
public class DirectoryNode {
	
	private DirectoryNode left;
	private DirectoryNode right;
	private DirectoryNode middle;
	private String name;
	private boolean isFile;
	private DirectoryNode parent;
	private int depth;

	/**Brief: Returns the depth of a node in reference to a root that starts at the beginning
	 * of the tree or a subtree
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 * 
	 * @return - the depth of a node in reference to the whole tree or a subtree
	 */
	public int getDepth() {
		return depth;
	}

	/**Brief: Sets the depth of a node in reference to a root that starts at the beginning
	 * of the tree or a subtree
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 * 
	 * @param depth - set the depth of a node in reference to the whole tree's root or a subtree's root
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**Brief: Returns the left child of a node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @return - the left child of the node referenced.
	 */
	public DirectoryNode getLeft() {
		return left;
	}

	/**Brief: Returns the right child of a node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @return - the right child of the node referenced.
	 */
	public DirectoryNode getRight() {
		return right;
	}

	/**Brief: Set a node as the left child of another node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @param left - the node that would be set as the left child a given node
	 */
	public void setLeft(DirectoryNode left) {
		this.left = left;
	}

	/**Brief: Set a node as the right child of another node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @param right - the node that would be set as the right child of a given node
	 */
	public void setRight(DirectoryNode right) {
		this.right = right;
	}

	/**Brief: Set a node as the middle child of another node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @param middle - the node that would be set as the middle child of a given node
	 */
	public void setMiddle(DirectoryNode middle) {
		this.middle = middle;
	}

	/**Brief: Returns the middle child of a node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @return - the middle child of the node referenced.
	 */
	public DirectoryNode getMiddle() {
		return middle;
	}

	/**Brief: Returns the name of a node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @return - the name of the node referenced.
	 */
	public String getName() {
		return name;
	}
	
	/**Brief: Returns the parent of a node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @return - the parent of the node referenced.
	 */
	public DirectoryNode getParent() {
		return parent;
	}

	/**Brief: Set a node as the parent of another node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @param parent - the node that would be as the parent to a given node
	 */
	public void setParent(DirectoryNode parent) {
		this.parent = parent;
	}

	/**Brief: Set the name of a node
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @param name - the name of that would be given toa specified node
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**Brief: Returns a boolean to show if a node is a file or not
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @return - a boolean that shows if a node is a file or not
	 */
	public boolean isFile() {
		return isFile;
	}
	
	/**Brief: Set if the node is a File or not
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor retains its original position
	 *  
	 * @param isFile - boolean that shows if a certain node is or is not a file
	 */
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	/**Brief: Constructor for a new Node.
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * New DirectoryNode has beenc created
	 * 
	 * @param name - name of the new DirectoryNode
	 */
	public DirectoryNode(String name) {
		
		this.name = name;
		this.left = null;
		this.middle = null;
		this.right = null;
		
	}
	
	
	/**
	 * Brief:
	 * 
	 * Adds newChild to any of the open child positions of this node (left, middle,
	 * or right). NOTE: Children should be added to this node in left-to-right
	 * order, i.e. left is filled first, middle is filled second, and right is
	 * filled last
	 * 
	 * 
	 * Preconditions: This node is not a file. There is at least one empty position
	 * in the children of this node (left, middle, or right).
	 * 
	 * 
	 * Postcondition: newChild has been added as a child of this node. If there is
	 * no room for a new node, throw a FullDirectoryException.
	 * 
	 * 
	 * Throws:
	 * 
	 * NotADirectoryException: Thrown if the current node is a file, as files cannot
	 * contain DirectoryNode references (i.e. all files are leaves).
	 * 
	 * FullDirectoryException: Thrown if all child references of this directory are
	 * occupied.
	 * 
	 * 
	 * @param newChild - newChild being added to any of the open child positions of
	 *                 this node at (left, middle or right)
	 */
	public void addChild(DirectoryNode newChild) throws NotADirectoryException, FullDirectoryException  {
		
		if (this.isFile() == true) {			
			throw new NotADirectoryException ("Not A Directory! Please add a different child");
		}
		
		if (this.getLeft() != null && this.getMiddle() != null && this.getRight() != null) {			
			throw new FullDirectoryException ("Directory is Full! No new Directory/File can be added");
		}
		
		if (this.getLeft() == null) {
			parent = this;
			this.setLeft(newChild);
		}else if (this.getMiddle() == null) {
			parent = this;
			this.setMiddle(newChild);
		}else {
			parent = this;
			this.setRight(newChild);
		}
		
	}

}
