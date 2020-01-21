
public class Test {

	public static void main(String[] args) {
		String line = new String ("  while k < 4:");
		
		String trimmedOut = new String ("0001111011022030111020222012");
		String two = new String ("0001111011022030111020222012");
		String one = new String (" for ");
		
		String line1 = new String ("   for i in N:  ");
		String keyword = line1.trim().substring(0, 4);
		
		System.out.println(one);
		System.out.println(keyword);
		System.out.println(one.contains(keyword));
		
				

	}

}
