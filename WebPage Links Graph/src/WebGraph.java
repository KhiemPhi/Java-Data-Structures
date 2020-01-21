/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;
public class WebGraph {
	
	public static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages = new ArrayList<WebPage>();
	private int[][] edges = new int[MAX_PAGES][MAX_PAGES];
	
	public ArrayList<WebPage> getPages() {
		return pages;
	}

	public void setPages(ArrayList<WebPage> pages) {
		this.pages = pages;
	}

	public int[][] getEdges() {
		return edges;
	}

	public void setEdges(int[][] edges) {
		this.edges = edges;
	}

	
	/**
	 * Brief:
	 * 
	 * Constructs a WebGraph object using the indicated files as the source for
	 * pages and edges.
	 * 
	 * Preconditions:
	 * 
	 * Both parameters reference text files which exist. The files follow proper
	 * format as outlined in the "Reading Graph from File" section below.
	 * 
	 * 
	 * Postconditions:
	 * 
	 * A WebGraph has been constructed and initialized based on the text files.
	 * 
	 * @param pagesFile - String of the relative path to the file containing the
	 *                  page information.
	 * @param linksFile - String of the relative path to the file containing the
	 *                  link information.
	 * @return - The WebGraph constructed from the text files.
	 * @throws IllegalArgumentException Thrown if either of the files does not
	 *                                  reference a valid text file, or if the files
	 *                                  are not formatted correctly.
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException{
		WebGraph newGraph = new WebGraph ();
		try {
			Scanner scanner = new Scanner(new File(pagesFile));
			int index = 0;
		    while (scanner.hasNextLine()) {
		    	String webPage = scanner.nextLine();
				String URL = webPage.substring(0, webPage.indexOf(".") + 4).replaceAll(" ", "");		
				String keywords = webPage.substring(webPage.indexOf(".") + 4, webPage.length());
				if (index > 0) {
					for (int i = 0; i < newGraph.getPages().size(); i++) {					
						if (newGraph.getPages().get(i).getUrl() != null ) {
							if (newGraph.getPages().get(i).getUrl().equals(URL))
								throw new IllegalArgumentException ("URL Already Exists");
						}
					}	
				}				
				WebPage newPage = new WebPage (URL,index,0);
				String [] allKeyWordsArray = keywords.split(" ");
				ArrayList<String> allKeyWords = new ArrayList<String>(Arrays.asList(allKeyWordsArray));
				newPage.setKeywords(allKeyWords);
				newGraph.getPages().add(index, newPage);
				for (int j = 0; j < newGraph.edges[index].length; j++) {
					newGraph.edges[index][j] = 0;
				} // setting the column of the index of the newPage to all zero to show available connection
				index++; // increment index of Page
		    }		    
		}catch (FileNotFoundException ex) {
			System.out.println("File Doesn't Exist!");
		}
		
		try {
			Scanner scanner = new Scanner(new File(linksFile));
			while (scanner.hasNextLine()) {
				String link = scanner.nextLine();
				String[] allLinks = link.split(" ");
				ArrayList<String> allLinksOnly = new ArrayList<String>(Arrays.asList(allLinks));
				for (int i = 0; i < allLinksOnly.size(); i++ ) {
					if (allLinksOnly.get(i).equals("")) {
						allLinksOnly.remove(i);
					}
				}
				if (allLinksOnly.size() == 4) {
					String beginingLink = allLinksOnly.get(2).replaceAll(" ", "");
					String destinationLink = allLinksOnly.get(3).replaceAll(" ", "");
					int fromIndex = -1;
					int toIndex = -1;
					for (int i = 0; i < newGraph.getPages().size(); i++) {
						if (newGraph.getPages().get(i).getUrl().equals(beginingLink)) {
							fromIndex = newGraph.getPages().get(i).getIndex();
						} else if (newGraph.getPages().get(i).getUrl().equals(destinationLink)) {
							toIndex = newGraph.getPages().get(i).getIndex();
						}
					}
					if (fromIndex != -1 && toIndex != -1) {
						newGraph.getEdges()[fromIndex][toIndex] = 1;
						
					}else {
						System.out.println("URL Doesn't Exist!");
					}
				}else {
					System.out.println("Links Can't Be Created");
				}
			}
		}catch (FileNotFoundException ex) {
			System.out.println("Invalid .txt File!");
		}				
		newGraph.updatePageRanks();		
		return newGraph;
	}

	/**
	 * Brief:
	 * 
	 * Adds a page to the WebGraph.
	 * 
	 * 
	 * Preconditions:
	 * 
	 * url is unique and does not exist as the URL of a WebPage already in the
	 * graph. url and keywords are not null.
	 * 
	 * Postconditions:
	 * 
	 * The page has been added to pages at index 'i' and links has been logically
	 * extended to include the new row and column indexed by 'i'.
	 * 
	 * @param url      - the URL of the WebPage (must not already exist in the
	 *                 WebGraph).
	 * @param keywords - the keywords associated with the WebPage.
	 * @throws IllegalArgumentException - if url is not unique and already exists in
	 *                                  the graph, or if either argument is null.
	 * 
	 * 
	 */
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
		
		if (url.equals(null) ) {
			throw new IllegalArgumentException ("URL is null!");
		}
		
		for (int i = 0; i < this.pages.size(); i++) {
			if (this.pages.get(i).getUrl().equals(url)) {
				throw new IllegalArgumentException ("URL is not unique!");
			}
		}
		
		WebPage newPage = new WebPage (url,keywords,this.getPages().size());
		this.getPages().add(newPage);
		  
	}
	
	/**
	 * Brief:
	 * 
	 * Adds a link from the WebPage with the URL indicated by source to the WebPage
	 * with the URL indicated by destination
	 * 
	 * Preconditions:
	 * 
	 * Both parameters reference WebPages which exist in the graph.
	 * 
	 * 
	 * @param source      - the URL of the page which contains the hyperlink to
	 *                    destination.
	 * @param destination - the URL of the page which the hyperlink points to.
	 * @throws IllegalArgumentException - if either of the URLs are null or could
	 *                                  not be found in pages.
	 */
	public void addLink(String source, String destination) throws IllegalArgumentException{
		
		if (source == null || destination == null) {
			throw new IllegalArgumentException ("URL is null");
		}
		
		boolean sourceCheck = false;
		int indexSource = -1;
		int indexDestination = -1;
		boolean destinationCheck = false;
		
		for (int i = 0; i < this.getPages().size(); i++) {
			if (this.getPages().get(i).getUrl().equals(source)) {
				sourceCheck = true;
				indexSource = i;
			}else if (this.getPages().get(i).getUrl().equals(destination)) {
				destinationCheck = true;
				indexDestination = i;
			}
		}
		if (sourceCheck && destinationCheck && indexSource != -1 && indexDestination != -1) {
			this.getEdges()[indexSource][indexDestination] = 1;
		}else {
			throw new IllegalArgumentException ("URL Doesn't Exist in Graph");
		}
	}
	
	/**
	 * Brief:
	 * 
	 * Removes the WebPage from the graph with the given URL.
	 * 
	 * Postconditions:
	 * 
	 * The WebPage with the indicated URL has been removed from the graph, and it's
	 * corresponding row and column has been removed from the adjacency matrix. All
	 * pages that has an index greater than the index that was removed should
	 * decrease their index value by 1. If url is null or could not be found in
	 * pages, the method ignores the input and does nothing.
	 * 
	 * 
	 * 
	 * @param url - The URL of the page to remove from the graph
	 */
	public void removePage(String url) throws IllegalArgumentException{
		
		if (url == null ) {
			throw new IllegalArgumentException ("URL is null");
		}
		
		boolean exist = false;
		int indexToBeRemoved = -1;
		if (url.equals(null)) {
			throw new IllegalArgumentException("URL is null!");
		}
		for (int i = 0; i < this.getPages().size(); i++) {
			if (this.getPages().get(i).getUrl().equals(url)) {
				exist = true;
				indexToBeRemoved = i;
				break;
			}
		}
		
		if (exist && indexToBeRemoved != -1) {
			
			for (int j = 0; j < this.getPages().size(); j++) {
				
				this.getEdges()[indexToBeRemoved][j] = 0;
				
			}
			
			for (int i = 0; i < this.getPages().size(); i++) {
				
				this.getEdges()[i][indexToBeRemoved] = 0;
				
			}

			this.getPages().remove(indexToBeRemoved);
			for (int i = 0; i < this.getPages().size();i++) {
				this.getPages().get(i).setIndex(i);
			}
			
		}
	}

	/**
	 * Brief:
	 * 
	 * Removes the link from WebPage with the URL indicated by source to the WebPage
	 * with the URL indicated by destination.
	 * 
	 * Postconditions:
	 * 
	 * The entry in links for the specified hyperlink has been set to 0 (no link).
	 * If either of the URLs could not be found, the input is ignored and the method
	 * does nothing.
	 * 
	 * @param source      - The URL of the WebPage to remove the link.
	 * @param destination - The URL of the link to be removed.
	 */
	public void removeLink(String source, String destination) {
		
		if (source == null || destination == null) {
			throw new IllegalArgumentException ("URL is null");
		}
		
		boolean sourceCheck = false;
		int indexSource = -1;
		int indexDestination = -1;
		boolean destinationCheck = false;
		
		for (int i = 0; i < this.getPages().size(); i++) {
			if (this.getPages().get(i).getUrl().equals(source)) {
				sourceCheck = true;		
				indexSource = i;
			}else if (this.getPages().get(i).getUrl().equals(destination)) {
				destinationCheck = true;
				indexDestination = i;
			}
		}
		if (sourceCheck && destinationCheck && indexSource != -1 && indexDestination != -1) {
			this.getEdges()[indexSource][indexDestination] = 0;
		}else {
			throw new IllegalArgumentException ("URL Doesn't Exist in Graph");
		}
	}
	
	/**
	 * Brief:
	 * 
	 * Calculates and assigns the PageRank for every page in the WebGraph (see the
	 * PageRank Algorithm section for further detail). Note: This operation should
	 * be performed after ANY alteration of the graph structure (adding/removing a
	 * link, adding/removing a page).
	 * 
	 * Postconditions:
	 * 
	 * All WebPages in the graph have been assigned their proper PageRank.
	 * 
	 * 
	 * 
	 */
	public void updatePageRanks() {
		int count = 0;
		for (int j = 0; j < this.getPages().size(); j++) {
			for (int i = 0; i < this.getPages().size(); i++) {
				if (this.getEdges()[i][j] == 1) {
					
					count++;
				}
			}
			this.getPages().get(j).setRank(count);
			count = 0;
		}
	}
	
	/**
	 * Brief:
	 * 
	 * Prints the WebGraph in tabular form organized ASC order based on the WebPages' indices
	 * 
	 */
	public void printTableIndex() {		

		String titleHead = String.format("%1s%7s%27s%15s%17s", "Index", "URL", "PageRank", "Links", "Keywords") + "\n"
				+ "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		String links = "";
		System.out.println(titleHead);
		String size = "";
		for (int i = 0; i < this.getPages().size(); i++) {
			size = size + i + ",";
		}
		int urlSize = 0;
		for (int i = 0; i < this.getPages().size(); i++) {
			if (this.getPages().get(i).getUrl().length() > urlSize) {
				urlSize = this.getPages().get(i).getUrl().length();
			}
		}

		for (int i = 0; i < this.getPages().size(); i++) {
			for (int j = 0; j < this.getPages().size(); j++) {
				if (this.getEdges()[i][j] == 1) {
					links = links + j + ",";
				}
			}

			if (links.length() > 0) {
				links = links.substring(0, links.length() - 1);
			}
			while (links.length() < size.length()) {
				links = links + " ";
			}
			WebPage modified = this.getPages().get(i);
			String modifiedString = modified.getUrl();
			while (modifiedString.length() < urlSize) {
				modifiedString = modifiedString + " ";
				modified.setUrl(modifiedString);
			}
			String pageWithLinks = this.getPages().get(i).toString().replaceAll("Links Here", links);
			System.out.println(pageWithLinks);
			modified.setUrl(modified.getUrl().replaceAll(" ", ""));
			links = "";
		}

	}
	/**
	 * Brief:
	 * 
	 * Prints the WebGraph in tabular form organized ASC order based on a user's given keyword 
	 * 
	 */
	public void printTableSearch (String keyWord) {
		
		String titleHead = String.format("%1s%20s%7s", "Rank", "PageRank", "URL") + "\n" + "---------------------------------------------";
		System.out.println(titleHead);
		ArrayList<WebPage> searched = new ArrayList<WebPage> ();
		for (int i = 0; i < this.getPages().size();i++) {
			if( this.getPages().get(i).getKeywords().contains(keyWord) ) {
				searched.add(this.getPages().get(i)); // Print Table
			}				
		}
		Collections.sort(searched, new RankComparator());
		if (searched.size() == 0) {
			System.out.println("No search results found for the keyword " + keyWord + ".");
		}
		
		for (int i = 0; i < searched.size(); i++) {
			String webPage = String.format("%10s%5s%5s%5s%5s", i+1,"|", searched.get(i).getRank(), "|",searched.get(i).getUrl());
			System.out.println(webPage);
		}
		
	}
	/**
	 * Brief:
	 * 
	 * Prints the WebGraph in tabular form organized ASC order alphabetically based on the WebPages' URLS 
	 * 
	 */
	public void printAlphabetRanking (ArrayList<WebPage> webPages) {
		
		Collections.sort(webPages, new URLComparator());
		String titleHead = String.format("%1s%7s%27s%15s%17s", "Index", "URL", "PageRank", "Links", "Keywords") + "\n"
				+ "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		String links = "";
		System.out.println(titleHead);
		String size = "";
		for (int i = 0; i < this.getPages().size(); i++) {
			size = size + i + ",";
		}
		int urlSize = 0;
		for (int i = 0; i < this.getPages().size(); i++) {
			if (this.getPages().get(i).getUrl().length() > urlSize) {
				urlSize = this.getPages().get(i).getUrl().length();
			}
		}

		for (int i = 0; i < this.getPages().size(); i++) {
			for (int j = 0; j < this.getPages().size(); j++) {
				if (this.getEdges()[webPages.get(i).getIndex()][j] == 1) {
					links = links + j + ",";
				}
			}

			if (links.length() > 0) {
				links = links.substring(0, links.length() - 1);
			}
			while (links.length() < size.length()) {
				links = links + " ";
			}
			WebPage modified = this.getPages().get(i);
			String modifiedString = modified.getUrl();
			while (modifiedString.length() < urlSize) {
				modifiedString = modifiedString + " ";
				modified.setUrl(modifiedString);
			}
			String pageWithLinks = this.getPages().get(i).toString().replaceAll("Links Here", links);
			System.out.println(pageWithLinks);
			modified.setUrl(modified.getUrl().replaceAll(" ", ""));
			links = "";
		}
	}
	/**
	 * Brief:
	 * 
	 * Prints the WebGraph in tabular form organized DSC order alphabetically based on the WebPages' PageRanks
	 * 
	 */
	public void printRankRanking (ArrayList<WebPage> webPages) {
		
		Collections.sort(webPages, new RankComparator());
		String titleHead = String.format("%1s%7s%27s%15s%17s", "Index", "URL", "PageRank", "Links", "Keywords") + "\n"
				+ "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		String links = "";
		System.out.println(titleHead);
		String size = "";
		for (int i = 0; i < this.getPages().size(); i++) {
			size = size + i + ",";
		}
		int urlSize = 0;
		for (int i = 0; i < this.getPages().size(); i++) {
			if (this.getPages().get(i).getUrl().length() > urlSize) {
				urlSize = this.getPages().get(i).getUrl().length();
			}
		}

		for (int i = 0; i < this.getPages().size(); i++) {
			for (int j = 0; j < this.getPages().size(); j++) {
				if (this.getEdges()[webPages.get(i).getIndex()][j] == 1) {
					links = links + j + ",";
				}
			}

			if (links.length() > 0) {
				links = links.substring(0, links.length() - 1);
			}
			while (links.length() < size.length()) {
				links = links + " ";
			}
			WebPage modified = this.getPages().get(i);
			String modifiedString = modified.getUrl();
			while (modifiedString.length() < urlSize) {
				modifiedString = modifiedString + " ";
				modified.setUrl(modifiedString);
			}
			String pageWithLinks = this.getPages().get(i).toString().replaceAll("Links Here", links);
			System.out.println(pageWithLinks);
			modified.setUrl(modified.getUrl().replaceAll(" ", ""));
			links = "";
		}
	}

}
