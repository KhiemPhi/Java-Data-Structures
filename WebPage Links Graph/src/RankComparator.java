/**
 * @author Khiem Dinh Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University ID: 111667279 
 * Recitation : 09
 *
 */	
import java.util.Comparator;
public class RankComparator implements Comparator<WebPage> {

	@Override
	public int compare(WebPage w1, WebPage w2) {
		if (w1.getRank() == w2.getRank())
            return 0;
        else if (w1.getRank() > w2.getRank())
            return -1;
        else
            return 1;
	}
	
	

}
