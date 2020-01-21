
/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279
 *
 */
import java.io.*;
import java.util.*;

public class PythonTracer {

	public static final int SPACES_COUNT = 4;

	public static Complexity traceFile(String filename) {// change method signuature
		/// test_function.py
		BlockStack stack = new BlockStack();
		CodeBlock block;
		CodeBlock oldTop;
		Complexity oldTopComplexity;

		String[] types = { "def", "for", "while", "if", "elif", "else" };
		int[] occurence = { 0, 0, 0, 0, 0, 0 };
		int trace = 1;
		String whileVar = "";

		try {
			Scanner reader = new Scanner(new File(filename));
			Scanner fileReader = new Scanner(new File(filename));
			String py = "";
			String currentLine = "";
			String keyword = "";
			int line = 1;

			while (reader.hasNext()) {
				currentLine = reader.nextLine();

				int numberOfSpaces; // CHECKS NUMBER OF SPACES [STARTS]
				int indents = 0;
				for (numberOfSpaces = 0; numberOfSpaces < currentLine.length(); numberOfSpaces++) {
					if (currentLine.charAt(numberOfSpaces) != ' ')
						break;
				}
				int indent = numberOfSpaces / SPACES_COUNT;// CHECKS NUMBER OF SPACES [END] // ### COUNTS NUMBER OF
															// SPACES ### //
				if (currentLine.isEmpty() || currentLine.trim().startsWith("#"))
					continue;

				// System.out.println(line + "indent: " + indent + " " + currentLine);
				py += "" + line + " indent: " + indent + " " + currentLine + "\n";
				line++;

				int n = 0;
				int log = 0;
				while (indent < stack.size()) { // THIS IS WHERE THE BLOCK STARTS TO CHANGE. IF THERE IS NO MORE
												// KEYWORDS EMBEDED [START]

					oldTop = stack.pop();
					oldTopComplexity = oldTop.getBlockComplexity();
					String name = oldTop.getName();
					n += oldTopComplexity.getNPower();
					log += oldTopComplexity.getLogPower();

					if (oldTopComplexity.getNPower()
							+ oldTopComplexity.getLogPower() > (stack.peek().getHighestSubComplexity().getNPower()
									+ stack.peek().getHighestSubComplexity().getLogPower())) {

						stack.peek().getHighestSubComplexity().setNPower(n);
						stack.peek().getHighestSubComplexity().setLogPower(log);
					} else if (oldTopComplexity.getNPower()
							+ oldTopComplexity.getLogPower() == (stack.peek().getHighestSubComplexity().getNPower())) {

						stack.peek().getHighestSubComplexity().setNPower(n);
						stack.peek().getHighestSubComplexity().setLogPower(log);
					}

					System.out.printf(
							"LEAVING BLOCK %s\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
							stack.peek().getName(), name, stack.peek().getBlockComplexity(),
							stack.peek().getHighestSubComplexity());
					stack.peek().setName(name);
				} // THIS IS WHERE THE BLOCK STARTS TO CHANGE. IF THERE IS NO MORE KEYWORDS
					// EMBEDED [END]

				if (currentLine.contains(types[0]) || currentLine.contains(types[1]) || currentLine.contains(types[2])
						|| currentLine.contains(types[3]) || currentLine.contains(types[4])
						|| currentLine.contains(types[5])) {

					for (String type : types) {
						if (currentLine.trim().substring(0, 5).contains(type)) {
							keyword = type;
							break;
						}
					}

					if (keyword.equals("for")) {
						if (currentLine.trim().endsWith("log_N:")) {
							stack.push(new CodeBlock(new Complexity(0, 1), new Complexity(0, 0),
									stack.peek().getName() + "." + trace));
						} else {
							stack.push(new CodeBlock(new Complexity(1, 0), new Complexity(0, 0),
									stack.peek().getName() + "." + trace));
						}
					} else if (keyword.equals("while")) {
						stack.push(new CodeBlock(new Complexity(0, 0), new Complexity(0, 0),
								stack.peek().getName() + "." + trace));
						stack.peek().setLoopVariable("while");
						whileVar = "" + currentLine.trim().charAt(6);
					} else {
						if (keyword.equals("def")) {
							stack.push(new CodeBlock(new Complexity(0, 0), new Complexity(0, 0), "1"));
						} else {
							stack.push(new CodeBlock(new Complexity(1, 1), new Complexity(0, 0),
									stack.peek().getName() + "." + trace));
						}
					}
					System.out.printf(
							"ENTERING BLOCK \'%s\'\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
							keyword, stack.peek().getName(), stack.peek().getBlockComplexity().toString(),
							stack.peek().getHighestSubComplexity());

				}

				else if (keyword.equals("while") && currentLine.trim().startsWith(whileVar)) {
					if (currentLine.contains("/= 2"))
						stack.peek().setBlockComplexity(new Complexity(0, 1));
					else if (currentLine.contains("-="))
						stack.peek().setBlockComplexity(new Complexity(1, 0));
					System.out.printf(
							"FOUND UPDATE STATEMENT BLOCK \'%s\'\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
							keyword, stack.peek().getName(), stack.peek().getBlockComplexity().toString(),
							stack.peek().getHighestSubComplexity());
				}

				// else if(stack.peek().getName());

				// while(stack.size() > 1){
				// oldTop = stack.pop();
				// oldTopComplexity = oldTop.getBlockComplexity();
				// System.out.printf("LEAVING BLOCK \'%s\'\n\tBLOCK: \tBLOCK COMPLEXITY:
				// %s\tHIGHEST SUB-COMPLEXITY: %s\n\n"
				// , oldTop.getName(), oldTop.getBlockComplexity(),
				// oldTop.getHighestSubComplexity());

				// if(oldTopComplexity.getNPower() + oldTopComplexity.getLogPower() <
				// stack.peek().getHighestSubComplexity().getNPower() +
				// stack.peek().getHighestSubComplexity().getLogPower()){
				// stack.peek().setHighestSubComplexity(oldTopComplexity);
				// }
				// }

			}

			System.out.println("SIZE: " + stack.size());
			System.out.println("\n\n" + py);
			reader.close();

		} catch (IOException ex) {
			System.out.println("\n*** FILE DOES NOT EXIST ***");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getStackTrace());

		}

		return stack.peek().getBlockComplexity();

	}

	public static void main(String[] args) {
		traceFile("C:\\Eclipse Workspace\\test_function.py").toString();

	}

}
