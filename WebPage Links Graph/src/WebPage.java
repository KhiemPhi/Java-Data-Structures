/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */	
import java.util.ArrayList;
public class WebPage implements Comparable {
	
	public int compareTo(Object arg0) {
		return 0;
	}
	
	private String url;
	private int index;
	private int rank;
	private ArrayList<String> keywords;
	
	
	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	public WebPage() {
		
		
	}
	
	public WebPage(String url, ArrayList<String> keywords, int index) {
		this.url = url;
		this.keywords = keywords;
		this.index = index;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public WebPage(String url, int index, int rank) {
		this.url = url;
		this.index = index;
		this.rank = rank;
	}
	
	public String toString() {
		String keywordsUsed = "";
		for (int i = 0; i < keywords.size(); i++) {
			keywordsUsed = keywordsUsed + keywords.get(i) + ", ";
			
		}
		keywordsUsed = keywordsUsed.substring(1,keywordsUsed.length()-2);
		String webpage = String.format("%1s%5s%15s%5s%5s%15s%1s%1s%1s", index, "|", url, "|", rank, "|", "Links Here", "|", keywordsUsed);
		return webpage;
	}
	

}
