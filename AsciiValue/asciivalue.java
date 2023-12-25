//To print ASCII value
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class asciivalue{
	public static void main (String [] args) throws IOException{
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		while (true) {
			System.out.print("Enter a character (or 'exit' to quit): ");
			String input = in.readLine();
			if (input.equalsIgnoreCase("exit")) {
				System.out.println("Exiting the program");
				break;
			}
			if (input.length()==1) {
				char inputChar = input.charAt(0);
				int asciiValue = (int)inputChar;
				System.out.println ("ASCII Value of " +inputChar+ " is " +asciiValue);
			}
			else {
				System.out.println("Please enter a single character");
			}
		}
	}
}

 
