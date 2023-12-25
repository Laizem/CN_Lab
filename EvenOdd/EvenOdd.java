import java.util.Scanner;
public class EvenOdd {
	public static void main (String [] args) {
		int n1;
		Scanner in = new Scanner (System.in);
		System.out.print ("Enter a number: ");
		n1 = in.nextInt();
		if (n1 % 2 == 0) {
			System.out.println ("No. is Even");
		}
		else {
			System.out.println ("No. is Odd");
		}
	}
}

