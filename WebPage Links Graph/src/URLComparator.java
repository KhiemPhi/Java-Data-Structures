/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */	
import java.util.Comparator;
public class URLComparator implements Comparator<WebPage>{

	@Override
	public int compare(WebPage w1, WebPage w2) {
		String url1 = w1.getUrl();
		String url2 = w2.getUrl();
		return (url1.compareTo(url2));
	}
	
	
	
	

}
