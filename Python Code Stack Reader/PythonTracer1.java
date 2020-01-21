
/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279
 *
 */
import java.io.*;
import java.util.*;

public class PythonTracer1 {

	public static final int SPACES_COUNT = 4;

	public static void traceFile(String fileName) {

		BlockStack stack = new BlockStack();
		String keyword = "";
		String loopVariable = "";
		CodeBlock oldTop;
		Complexity oldTopComplexity;
		int nPower = 0;
		int logPower = 0;
		int prevIndents = 0; // holds the indentation of a function
		int indents = 0;
		String tracer = "";
		String name = ""; // name of loop (1.1, 1.1.1... etc)
		int occurenceOfDef = 0;
		int blockNumber = 0;
		String blockName = Integer.toString(blockNumber);
		String defName = "1";
		int functionIndents = 0;
		int[] keepTrack = new int[1000];
		int index = 0;
		String nestedBlock = defName;
		Complexity highestComplexity = new Complexity();
		if (fileName != null) {

			try {
				Scanner reader = new Scanner(new File(fileName));
				Scanner fileReader = new Scanner(new File(fileName));
				String line = "";
				while (reader.hasNext()) {

					int spaceInLine = 0;
					line = reader.nextLine();

					if (keyword.equals("while") && line.trim().startsWith(loopVariable)
							&& !(loopVariable.equalsIgnoreCase(""))) {
						
						if (line.contains("/="))
							stack.peek().setBlockComplexity(new Complexity(0, 1));
						else if (line.contains("-="))
							stack.peek().setBlockComplexity(new Complexity(1, 0));
						System.out.printf(
								"FOUND UPDATE STATEMENT BLOCK \'%s\'\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
								keyword, stack.peek().getName(), stack.peek().getBlockComplexity().toString(),
								stack.peek().getHighestSubComplexity());
					}

					if (!line.isEmpty() || !line.trim().startsWith("#")) {
						for (int i = 0; i < line.length(); i++) {
							if (line.charAt(spaceInLine) == ' ')
								spaceInLine++;
							else
								break;
						}
					}
					if (line.isEmpty() || line.trim().startsWith("#"))
						continue;

					if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.DEF])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.FOR])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELIF])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELSE])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.IF])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.WHILE])) {
						functionIndents = indents;
						keepTrack[index] = functionIndents;
						index++;
					}
					indents = spaceInLine / SPACES_COUNT;

					while (indents < stack.size()) {
						
						oldTop = stack.pop();
						oldTopComplexity = oldTop.getBlockComplexity();

						nPower += oldTopComplexity.getNPower();
						logPower += oldTopComplexity.getLogPower();

						if (oldTopComplexity.getNPower()
								+ oldTopComplexity.getLogPower() > (stack.peek().getHighestSubComplexity().getNPower()
										+ stack.peek().getHighestSubComplexity().getLogPower())) {

							stack.peek().getHighestSubComplexity().setNPower(nPower);
							stack.peek().getHighestSubComplexity().setLogPower(logPower);
						} else if (oldTopComplexity.getNPower() + oldTopComplexity.getLogPower() == (stack.peek()
								.getHighestSubComplexity().getNPower())) {

							stack.peek().getHighestSubComplexity().setNPower(nPower);
							stack.peek().getHighestSubComplexity().setLogPower(logPower);
						}else {
							
						}

						System.out.printf(
								"LEAVING BLOCK %s\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
								stack.peek().getName(), oldTop.getName(), stack.peek().getHighestSubComplexity(),
								stack.peek().getBlockComplexity());
						stack.peek().setName(oldTop.getName());
						int a = stack.peek().getBlockComplexity().getLogPower() + stack.peek().getHighestSubComplexity().getLogPower();
						int b = stack.peek().getBlockComplexity().getNPower() + stack.peek().getHighestSubComplexity().getNPower();
						Complexity highestComplexityInside = new Complexity(b, a);
						if (highestComplexityInside.getNPower() + highestComplexityInside.getLogPower() > highestComplexity.getNPower() + highestComplexity.getLogPower() ) {
							highestComplexity = highestComplexityInside;
							System.out.println("Overall complexity is " + highestComplexity.toString());
						}else {
							System.out.println("Overall complexity is " + highestComplexity.toString());
						}


					} // exit block

					if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.DEF])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.FOR])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELIF])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELSE])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.IF])
							|| line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.WHILE])
							|| (line.contains(loopVariable) && !loopVariable.equals(""))) {

						keyword = line.trim().substring(0, 5); // collect the keyword

						if (keyword.contains("for")) {

							if (line.trim().endsWith("log_N:")) {
								stack.push(new CodeBlock(new Complexity(0, 1), new Complexity(0, 0)));
							} else {
								stack.push(new CodeBlock(new Complexity(1, 0), new Complexity(0, 0)));
							}

							if (indents > keepTrack[index]) {
								System.out.println(keepTrack[index]);
								System.out.println(indents);
								System.out.println("Here1");
								nestedBlock = nestedBlock + "." + "1";
								stack.peek().setName(nestedBlock);
							} else if (indents == keepTrack[index]) {
								int blockCounter = 0;
								System.out.println("Here2");
								nestedBlock = nestedBlock + "." + "1";
								stack.peek().setName(nestedBlock);
							} else {
								System.out.println("Here3");
								nestedBlock = "";
								nestedBlock = nestedBlock + "." + "1";
								stack.peek().setName(nestedBlock);
							}

							System.out.printf(
									"ENTERING BLOCK \'%s\'\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
									keyword.substring(0, 4), stack.peek().getName(),
									stack.peek().getBlockComplexity().toString(),
									stack.peek().getHighestSubComplexity());

						} else if (keyword.contains("while")) {
							System.out.println("Here WHILE " + functionIndents + indents);
							stack.push(new CodeBlock(new Complexity(0, 0), new Complexity(0, 0)));
							loopVariable = "" + line.trim().charAt(6);
							stack.peek().setLoopVariable(loopVariable);
							System.out.println(loopVariable);

							if (indents < keepTrack[index]) {
								System.out.println("Here1");
								nestedBlock = nestedBlock + "." + "1";
								stack.peek().setName(nestedBlock);
							} else if (indents == keepTrack[index]) {
								int blockCounter = 0;
								System.out.println("Here2");
								nestedBlock = nestedBlock + "." + "1";
								stack.peek().setName(nestedBlock);
							} else {
								System.out.println(keepTrack[index]);
								System.out.println(indents);
								System.out.println("Here3");
								nestedBlock = "" + occurenceOfDef;
								nestedBlock = nestedBlock + "." + "2";
								stack.peek().setName(nestedBlock);
							}
							System.out.printf(
									"ENTERING BLOCK \'%s\'\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
									keyword.substring(0, 5), stack.peek().getName(),
									stack.peek().getBlockComplexity().toString(),
									stack.peek().getHighestSubComplexity());

						} else if (keyword.contains("def")) {

							occurenceOfDef++;
							defName = Integer.toString(occurenceOfDef);
							stack.push(new CodeBlock(new Complexity(0, 0), new Complexity(0, 0), defName));
							System.out.printf(
									"ENTERING BLOCK \'%s\'\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
									keyword.substring(0, 4), stack.peek().getName(),
									stack.peek().getBlockComplexity().toString(),
									stack.peek().getHighestSubComplexity());

						} else if (keyword.contains("IF")) {
							System.out.println("Here OTHER");
						} else if (keyword.contains("ELSE")) {

						} else if (keyword.contains("ELIF")) {

						}

					} else if (keyword.equals("while") && line.trim().substring(0, 0).equals(loopVariable)
							&& !(line.trim().substring(0, 0).equals(""))) {
						System.out.println("Here While!");
						if (line.contains("/="))
							stack.peek().setBlockComplexity(new Complexity(0, 1));
						else if (line.contains("-="))
							stack.peek().setBlockComplexity(new Complexity(1, 0));
						System.out.printf(
								"FOUND UPDATE STATEMENT BLOCK \'%s\'\n\tBLOCK: %s\tBLOCK COMPLEXITY: %-15s HIGHEST SUB-COMPLEXITY: %s\n\n",
								keyword, stack.peek().getName(), stack.peek().getBlockComplexity().toString(),
								stack.peek().getHighestSubComplexity());
					} else {

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else

	{
		throw new NullFileException("Error: File's name is null");

	}

	  System.out.println("Final Complexity is " + highestComplexity.toString());
	}

	public static void main(String[] args) {

		String userString = "";
		Scanner input = new Scanner(System.in);
		while (true) {

			System.out.println("Enter file to be traced or 'quit' to quit");
			userString = input.nextLine();
			if (userString.equalsIgnoreCase("quit")) {
				System.out.println("Program terminating successfully...");

				break;
			}
			traceFile(userString);

		}
		input.close();
	}

}
