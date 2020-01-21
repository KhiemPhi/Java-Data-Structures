import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;

import big.data.*;
public class TestingFunctions {
	private static String test = "";
	public static void main(String[] args) {
		String test = "";
		try {
			System.out.println("Here2");
			FileInputStream file = new FileInputStream("String.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);
			String auctions = new String();
			auctions = (String)inStream.readObject();
			test = auctions;
			inStream.close();
		} catch (InputMismatchException e) {
			System.out.println("Incorrect Input!");
		} catch (IOException e) {
			System.out.println("Incorrect Input!");
		} catch (ClassNotFoundException e) {
			System.out.println("Invalid Class!");
		}
		
		System.out.println(test);
		
		try {
			System.out.println("Here1");
			FileOutputStream file = new FileOutputStream("String.obj");
			ObjectOutputStream outStream = new ObjectOutputStream(file);
			String auctions = "RealTime";
			outStream.writeObject(auctions);
			outStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Valid File Not Found!");
		} catch (IOException e) {
			System.out.println("Invalid Input!");
		}
	}

}
	