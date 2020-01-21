/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */	
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
public class SearchEngine {
	
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private static WebGraph web = new WebGraph();
	
	public static void main(String[] args) {
		System.out.println("Loading WebGraph data ...");
		try {
			web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
			System.out.println("Success!");
		}catch (IllegalArgumentException ex) {
			System.out.println("Files Do Not Exist!");			
		}
		SearchEngine userSearchEngine = new SearchEngine ();
		Scanner input = new Scanner (System.in);		
		while (true) {			
			System.out.println("Menu:");
			System.out.println("\t" + "(AP) - Add a new page to the graph.");
			System.out.println("\t" + "(RP) - Remove a page from the graph.");
			System.out.println("\t" + "(AL) - Add a link between pages in the graph.");
			System.out.println("\t" + "(RL) - Remove a link between pages in the graph.");
			System.out.println("\t" + "(P) - Print the graph.");
			System.out.println("\t" + "(S) - Search for pages with a keyword.");
			System.out.println("\t" + "(Q) - Quit.");
			System.out.println("Please Select An Option: ");
			String userInput = input.nextLine();
			
			if (userInput.equals("AP")) {
				
				System.out.println("Enter a URL");
				String addedURL = input.nextLine();
				System.out.println("Enter keywords (space-separated): ");
				String keyWordsUsed = input.nextLine();
				String [] allKeyWordsArray = keyWordsUsed.split(" ");
				ArrayList<String> allKeyWords = new ArrayList<String>(Arrays.asList(allKeyWordsArray));
				try {
					web.addPage(addedURL, allKeyWords);
					web.updatePageRanks();
					System.out.println(addedURL + " successuflly added to the WebGraph!");
				} catch (IllegalArgumentException ex) {
					System.out.println("Error:" + " " + addedURL + " already exists in the WebGraph. Could not add new WebPage.");
					continue;
				}
				
			}else if (userInput.equals("RP")) {
				
				System.out.println("Enter a URL");
				String removal = input.nextLine();
				try {
					web.removePage(removal);
					web.updatePageRanks();
					System.out.println(removal + " has been removed from the WebGraph!");
				}catch(IllegalArgumentException ex){
					System.out.println("Error:" + " " +  " URL does not exist in the WebGraph. Could not remove WebPage.");
					continue;
				}
				
			}else if (userInput.equals("AL")) {
				
				System.out.println("Enter a source URL");
				String sourceURL = input.nextLine();
				System.out.println("Enter a destination URL");
				String destinationURL = input.nextLine();
				try {
					web.addLink(sourceURL, destinationURL);
					web.updatePageRanks();
					System.out.println("Link successfully added from " + sourceURL + " to " +  destinationURL + "!");
				} catch (IllegalArgumentException ex) {
					System.out.println("Error:" + " " +  " URL does not exist in the WebGraph. Could not add new Link.");
					continue;
				}
				
				
			}else if (userInput.equals("RL")) {
				
				System.out.println("Enter a source URL:");
				String sourceURL = input.nextLine();
				System.out.println("Enter a destination URL:");
				String destinationURL = input.nextLine();
				try {
					web.removeLink(sourceURL, destinationURL);
					web.updatePageRanks();
					System.out.println(destinationURL + " has been unlinked from " + sourceURL);
				} catch (IllegalArgumentException ex) {
					System.out.println("Error:" + " " +  " URL does not exist in the WebGraph. Could not remove Link.");
					continue;
				}
				
			}else if (userInput.equals("P")) {
				
				System.out.println("\t" + " (I) Sort based on index (ASC) ");
				System.out.println("\t" + " (U) Sort based on URL (ASC) ");
				System.out.println("\t" + " (R) Sort based on rank (DSC)" + "\n");
				System.out.println("Please Select An Option: ");
				String sortSelection = input.nextLine();
				if (sortSelection.equals("I"))
					web.printTableIndex();
				else if (sortSelection.equals("U")) 					
					web.printAlphabetRanking(web.getPages());
				else if (sortSelection.equals("R"))
					web.printRankRanking(web.getPages());
				else
					System.out.println("Invalid Input Detected.");
					
			}else if (userInput.equals("S")) {
				
				System.out.println("Search keyword:");
				String keywordSelection = input.nextLine();
				web.printTableSearch(keywordSelection);
			
				
			}else if (userInput.equals("Q")) {
				
				System.out.println("Goodbye");
				break;
				
			}else {
				System.out.println("Wrong input! Please provide another input");
			}
		}
		
	}

}
