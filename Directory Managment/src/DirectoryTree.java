/**This class implements a ternary (3-child) tree of DirectoryNodes. The class contains a reference
 * to the root of the tree, a cursor for the present working directory, and various methods of insertion
 * and deletion. The user can also reset the cursor, move the cursor to another directory, create files and
 * directories as well as display their various operations on screen. 
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */
public class DirectoryTree {
	
	private DirectoryNode root;
	private DirectoryNode cursor;		
	private String path = ""; // a given path to a certain node from the root.
	
	/**Brief: Returns the root node of a Directory Tree
	 * 
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor still maintains its position
	 * 
	 * 
	 * @return - return the root node of a DirectoryTree
	 */
	public DirectoryNode getRoot() {
		return root;
	}

	/**Brief: Returns the path from the root node to a given node in a String
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor still maintains its position
	 * 
	 * @param path - the String representation of the path from the root node to a given node
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**Brief: Returns the node of a Directory Tree that the cursor is referencing to.
	 * 
	 * 
	 * Preconditions:
	 * None
	 * 
	 * Postconditions:
	 * Cursor still maintains its position
	 * 
	 * 
	 * @return - return the node of a Directory Tree that the cursor is referencing to
	 */
	public DirectoryNode getCursor() {
		return cursor;
	}

	/**
	 * Brief:
	 * 
	 * Initializes a DirectoryTree object with a single DirectoryNode named "root".
	 * 
	 * 
	 * Preconditions:
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The tree contains a single DirectoryNode named "root", and both cursor and
	 * root reference this node. NOTE: Do not confuse the name of the directory with
	 * the name of the reference variable. The DirectoryNode member variable of
	 * DirectoryTree named root should reference a DirectoryNode who's name is
	 * "root", i.e. root.getName().equals("root") is true.
	 * 
	 * 
	 * 
	 */
	public DirectoryTree() {
		
		root = new DirectoryNode("root");
		cursor = root;
		
	}
	
	/**
	 * Brief:
	 * 
	 * Moves the cursor to the root node of the tree.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The cursor now references the root node of the tree.
	 * 
	 * 
	 * 
	 */
	public void resetCursor() {
		
		cursor = root;
		
	}
	/**
	 * Brief:
	 * 
	 * Set the cursor to a given node of the tree.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The cursor now references the given node of the tree.
	 * 
	 * 
	 * 
	 */
	public void setCursor(DirectoryNode ptr) {
		
		cursor = ptr;
	}
	
	/**
	 * Brief:
	 * 
	 * Moves the cursor to the directory with the name indicated by name.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * 'name' references a valid directory ('name' cannot reference a file).
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The cursor now references the directory with the name indicated by name. If a
	 * child could not be found with that name, then the user is prompted to enter a
	 * different directory name. If the name was not a directory, a
	 * NotADirectoryException has been thrown
	 * 
	 * 
	 * Throws:
	 * 
	 * NotADirectoryException: Thrown if the node with the indicated name is a file,
	 * as files cannot be selected by the cursor, or cannot be found.
	 * 
	 * 
	 * @param name - name of the Child Directory that cursor would be moved to
	 */
	public void changeDirectory(String name) throws NotADirectoryException {

		if (this.getCursor().getLeft() != null || this.getCursor().getMiddle() != null
				|| this.getCursor().getRight() != null) {

			if (this.getCursor().getLeft().getName().equals(name)) {
				
				if (this.getCursor().getLeft().isFile()) {
					throw new NotADirectoryException ("Files Can't be Selected by a Cursor!");
				}
				this.setCursor(this.getCursor().getLeft());

			} else if (this.getCursor().getMiddle().getName().equals(name) ) {
				
				if (this.getCursor().getMiddle().isFile()) {
					throw new NotADirectoryException ("Files Can't be Selected by a Cursor!");
				}
				this.setCursor(this.getCursor().getMiddle());

			} else if (this.getCursor().getRight().getName().equals(name) ) {
				if (this.getCursor().getRight().isFile()) {
					throw new NotADirectoryException ("Files Can't be Selected by a Cursor!");
				}
				this.setCursor(this.getCursor().getRight());

			} else {
				System.out.println("Please enter another name! The following directory doesn't exist");
			}
		} else {
			System.out.println("Cursor has no children!");
		}
		
	
	}
	/**
	 * Brief:
	 * 
	 * Helper method for the presentWorkingDirectoryMethod, generates the String by recursively 
	 * going through the tree and collecting the names of all the nodes until the desired 
	 * directory node at cursor is reached. Then the String value path of the tree is set
	 * to the generated String.
	 * 
	 * Preconditions: 	
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The cursor remains at the same DirectoryNode.
	 * 
	 * 
	 *
	 */
	public void pathToNode (DirectoryNode cursor, String name, String path) {
		
		if (cursor.getName().equals(name)) {
			path = path + cursor.getName() ;
			this.path = path;
		}else {
			if (cursor.getLeft() != null) {
				pathToNode(cursor.getLeft(), name, path + cursor.getName() + "/");
			}
			if (cursor.getMiddle() != null) {
				pathToNode(cursor.getMiddle(), name, path + cursor.getName() + "/");
			}
			if (cursor.getRight() != null) {

				pathToNode(cursor.getRight(), name, path + cursor.getName() + "/");
			}
			
		}
	}
	
	/**
	 * Brief:
	 * 
	 * Returns a String containing the path of directory names from the root node of
	 * the tree to the cursor, with each name separated by a forward slash "/". e.g.
	 * root/home/user/Documents if the cursor is at Documents in the example above.
	 * 
	 * 
	 * Preconditions: 	
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The cursor remains at the same DirectoryNode.
	 * 
	 * 
	 * @return - String representation of a path
	 */
	public String presentWorkingDirectory() {		
		
		return this.path;
		
	}

	/**
	 * Brief:
	 * 
	 * Prints a formatted nested list of names of all the nodes in the directory
	 * tree, starting from the cursor. See sample I/O for an example.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The cursor remains at the same DirectoryNode.
	 * 
	 * 
	 * 
	 */
	public void printDirectoryTree(DirectoryNode cursor) {
		DirectoryNode ptr = cursor;
		if (cursor.isFile()) {
			for (int i = 0; i < ptr.getDepth() * 4; i++) {
				System.out.print(" ");
			}
			System.out.print("- " + ptr.getName());
			System.out.println("");
		} else {
			for (int i = 0; i < ptr.getDepth() * 4; i++) {
				System.out.print(" ");
			}
			System.out.print("|- " + ptr.getName());
			System.out.println("");
		}
		if (cursor.getLeft() != null) {
			printDirectoryTree(cursor.getLeft());
		}

		if (cursor.getMiddle() != null) {
			printDirectoryTree(cursor.getMiddle());
		}

		if (cursor.getRight() != null) {
			printDirectoryTree(cursor.getRight());

		}

	}
	/**
	 * Brief:
	 * 
	 * Helper method for the printTreeDirectory method. Assigns the appropriate values of depth
	 * for the any given subtree or the whole tree in order for the printTreeDirectory method
	 * to display the correct indentation.
	 * 
	 * Preconditions:
	 * 
	 * None.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * The cursor remains at the same DirectoryNode.
	 * 
	 * 
	 * 
	 */
	public int findDepth(DirectoryNode root, int depth) {
		if (root == null)
			return 0;

		if (depth == 0)
			root.setDepth(0);

		if (root.getLeft() != null) {
			root.getLeft().setDepth(depth + 1);
			findDepth(root.getLeft(), depth + 1);
		}

		if (root.getMiddle() != null) {
			root.getMiddle().setDepth(depth + 1);
			findDepth(root.getMiddle(), depth + 1);
		}

		if (root.getRight() != null) {
			root.getRight().setDepth(depth + 1);
			findDepth(root.getRight(), depth + 1);
		}

		return depth;

	}
	
	
	/**
	 * Brief:
	 * 
	 * Creates a directory with the indicated name and adds it to the children of
	 * the cursor node. Remember that children of a node are added in left-to-right
	 * order.
	 * 
	 * 
	 * Parameters:
	 * 
	 * name The name of the directory to add.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * 'name' is a legal argument (does not contain spaces " " or forward slashes
	 * "/").
	 * 
	 * 
	 * Postconditions:
	 * 
	 * A new DirectoryNode has been added to the children of the cursor, or an
	 * exception has been thrown.
	 * 
	 * 
	 * Throws:
	 * 
	 * IllegalArgumentException: Thrown if the 'name' argument is invalid.
	 * FullDirectoryException: Thrown if all child references of this directory are
	 * occupied.
	 * 
	 * 
	 * @param name - the name of the DirectoryNode to be added
	 */
	public void makeDirectory(String name) throws FullDirectoryException, IllegalArgumentException {
		if (this.cursor.getParent() == null || this.cursor.getLeft() == null || this.cursor.getRight() == null
				|| this.cursor.getMiddle() == null) {
			DirectoryNode newChild = new DirectoryNode(name);
			if (this.cursor.getLeft() != null) {
				if (this.cursor.getLeft().getName().equals(name)) {
					throw new IllegalArgumentException("Invalid Name! Can't be added");
				}
			}
			if (this.cursor.getRight() != null) {
				if (this.cursor.getMiddle().getName().equals(name) || this.cursor.getRight().getName().equals(name) ) {
					throw new IllegalArgumentException("Invalid Name! Can't be added");
				}
			}
			this.cursor.addChild(newChild); // handles FullDirectoryException
			return;
		}
		DirectoryNode parent = this.getCursor().getParent();
		if (parent.getLeft().getName().equals(name) || parent.getMiddle().getName().equals(name)
				|| parent.getRight().getName().equals(name)) {
			throw new IllegalArgumentException("Invalid Name! Can't be added");
		}
		if (!(parent.isFile())) {
			DirectoryNode newChild = new DirectoryNode(name);
			this.cursor.addChild(newChild); // handles FullDirectoryException
		}else {
			throw new NotADirectoryException (" Parent is a file! ");
		}
	

	}
	
	/**
	 * Brief:
	 * 
	 * Creates a file with the indicated name and adds it to the children of the
	 * cursor node. Remember that children of a node are added in left-to-right
	 * order.
	 * 
	 * 
	 * Parameters:
	 * 
	 * name - The name of the file to add.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * 'name' is a legal argument (does not contain spaces " " or forward slashes
	 * "/").
	 * 
	 * 
	 * Postconditions:
	 * 
	 * A new DirectoryNode has been added to the children of the cursor, or an
	 * exception has been thrown.
	 * 
	 * 
	 * Throws:
	 * 
	 * IllegalArgumentException: Thrown if the 'name' argument is invalid.
	 * FullDirectoryException: Thrown if all child references of this directory are
	 * occupied.
	 * 
	 * 
	 * @param name - the name of the file DirectoryNode to be added.
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException {
		if (this.cursor.getParent() == null || this.cursor.getLeft() == null || this.cursor.getRight() == null
				|| this.cursor.getMiddle() == null) {
			DirectoryNode newChild = new DirectoryNode(name);
			if (this.cursor.getLeft() != null) {
				if (this.cursor.getLeft().getName().equals(name)) {
					throw new IllegalArgumentException("Invalid Name! Can't be added");
				}
			}
			if (this.cursor.getRight() != null) {
				if (this.cursor.getMiddle().getName().equals(name) || this.cursor.getRight().getName().equals(name) ) {
					throw new IllegalArgumentException("Invalid Name! Can't be added");
				}
			}
			newChild.setFile(true);
			this.cursor.addChild(newChild); // handles FullDirectoryException
			return;
		}
		DirectoryNode parent = this.getCursor().getParent();
		if (parent.getLeft().getName().equals(name) || parent.getMiddle().getName().equals(name)
				|| parent.getRight().getName().equals(name)) {
			throw new IllegalArgumentException("Invalid Name! Can't be added");
		}
		if (!(parent.isFile())) {
			DirectoryNode newChild = new DirectoryNode(name);
			newChild.setFile(true); // establishes file status
			this.cursor.addChild(newChild); // handles FullDirectoryException
		}else {
			throw new IllegalArgumentException (" Parent is a file! ");
		}
		

	}
}
