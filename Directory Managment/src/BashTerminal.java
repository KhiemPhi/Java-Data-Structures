/**The class contains a single main method which allows a user to interact with
 * a file system implemented by an instance of DirectoryTree using the following
 * commands (note that commands are case-sensitive and will always be
 * lower-case):
 * 
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 */
import java.util.Scanner;
public class BashTerminal {

	
	public static void main(String[] args) {
		DirectoryTree userTree = new DirectoryTree();
		Scanner input = new Scanner(System.in);
		String userInput = "";
		while (!userInput.equals("exit")) {
			System.out.println("[user@host]: $");
			userInput = input.nextLine();
			if (userInput.equals("pwd")) {
				userTree.pathToNode(userTree.getRoot(), userTree.getCursor().getName(), "")  ;	
				System.out.println( userTree.presentWorkingDirectory() );
				userTree.setPath(""); // reset path for another path to be stored later.
			} else if (userInput.equals("ls")) {
				System.out.println(userTree.getCursor().getLeft().getName() + " " + userTree.getCursor().getMiddle().getName() + " " + userTree.getCursor().getRight().getName()	  );
			} else if (userInput.equals("ls -R")) {
				userTree.findDepth(userTree.getCursor(), 0);
				userTree.printDirectoryTree(userTree.getCursor());
			} else if (userInput.equals("cd /")) {
				userTree.resetCursor();
			} else if (userInput.startsWith("touch ")) {
				String name = userInput.substring(userInput.indexOf("h") + 2 , userInput.length() );
				try{
					userTree.makeFile(name);
				}catch (NotADirectoryException ex) {
					System.out.println("Cursor is not referecing a directory, no child can be added!");
					continue;
				}catch (FullDirectoryException ex) {
					System.out.println("Directory is Full! No new Directory/File can be added");
					continue;
				}catch (IllegalArgumentException ex) {
					System.out.println("Invalid Name! Name already exits");
					continue;
				}
			} else if (userInput.startsWith("mkdir ")) {
				String name = userInput.substring(userInput.indexOf("r") + 2, userInput.length() );
				try{
					userTree.makeDirectory(name);
				}catch (NotADirectoryException ex) {
					System.out.println("Cursor is not referecing a directory, no child can be added!");
					continue;
				}catch (FullDirectoryException ex) {
					System.out.println("Directory is Full! No new Directory/File can be added");
					continue;
				}catch (IllegalArgumentException ex) {
					System.out.println("Invalid Name! Name already exits");
					continue;
				}
			} else if (userInput.equals("cd ..")) {
				if (userTree.getCursor().getParent() != null)
					userTree.setCursor(userTree.getCursor().getParent());					
				else
					System.out.println("There is no parent for root");
			} else if (userInput.contains("cd ")) {
				String name = userInput.substring(userInput.indexOf("d") + 2, userInput.length() );
				try{
					userTree.changeDirectory(name);
				}catch (NotADirectoryException ex) {
					System.out.println("Files Can't be Selected by a Cursor!");
					continue;
				}
			} else if (userInput.equals("exit")) {
				System.out.println("Program terminating normally");
			} else {
				System.out.println("Invalid Input!");
				continue;
			}

		}
		input.close();

	}

}
