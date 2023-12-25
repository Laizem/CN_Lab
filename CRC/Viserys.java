import java.util.Scanner;

public class Viserys {
    public static String crc(String rem, String poly, int checkError) {
        if (checkError == 0) {
            for (int i = 0; i < poly.length() - 1; ++i) {
                rem += "0";
            }
        }
        for (int i = 0; i < rem.length() - poly.length() + 1; ++i) {
            if (rem.charAt(i) == '1') {
                for (int j = 0; j < poly.length(); ++j) {
                    if (rem.charAt(i + j) == poly.charAt(j)) {
                        rem = rem.substring(0, i + j) + '0' + rem.substring(i + j + 1);
                    } else {
                        rem = rem.substring(0, i + j) + '1' + rem.substring(i + j + 1);
                    }
                }
            }
        }
        return rem.substring(rem.length() - poly.length() + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data, poly;
        System.out.print("Enter the data to be transmitted >> ");
        data = scanner.next();
        System.out.print("Enter the polynomial >> ");
        poly = scanner.next();
        System.out.println("Transmitted data >> " + data + crc(data, poly, 0));
        System.out.print("Enter the received data >> ");
        data = scanner.next();
        if (Integer.parseInt(crc(data, poly, 1)) == 0) {
            System.out.println("No error in data transmission ");
        } else {
            System.out.println("Error has occurred in data transmission ");
        }
    }
}
