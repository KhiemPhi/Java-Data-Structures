/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */	
import java.util.Comparator;
public class IndexComparator implements Comparator<WebPage> {
	
	@Override
	public int compare(WebPage w1, WebPage w2) {
		Integer index1 = w1.getIndex();
		Integer index2 = w2.getIndex();
		return (index1.compareTo(index2));
	}
		
}
