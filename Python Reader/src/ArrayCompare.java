import java.util.Arrays;
public class ArrayCompare {

	public static void main(String[] args) {
		int[] a = {2,3,5};
		int[] b = {2,4,3};
		
		
	
		System.out.println(avg(b,b.length - 1));

	}
	
	public static double avg(int[] array, int index) {
		
		int sum = 0;
		if (index == 0) {			
			return array[index];
		}else {
			int a =  (int)avg(array, index-1);
			sum = array[index] + a;
			
			
		}
		double sumFinal = sum;
		double length = array.length;
		System.out.println(sumFinal/length);
		return sumFinal/length;
	}

}
