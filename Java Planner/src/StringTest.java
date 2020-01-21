
public class StringTest {

	public static void main(String[] args) {
		String table1 = String.format("%-21s%16s", "CAR:", "LOAD:");
		String table2 = String.format("%5s%12s%12s%3s%8s%13s%12s%13s", "Num", "Length (m)", "Weight (t)", "|", "Name",
				"Weight(t)", "Value($)", "Dangerous");
		String table3 = "==============================================================================";
		String sumTotal = table1 + "\n" + table2 + "\n" + table3;
		System.out.println(sumTotal);
		String train = String.format("%5s%12s%12s%3s%8s%13s%12s%13s", "1", "15.0", "10.0", "|", "Empty",
				"0.0", "0.0", "NO");
		System.out.println(train);

	}

}
