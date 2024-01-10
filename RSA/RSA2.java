import java.math.BigInteger;
import java.util.Scanner;

public class RSA2 {
    public static void main(String args[]) {
        // Initialize prime numbers p and q
        BigInteger p = new BigInteger("17");
        BigInteger q = new BigInteger("23");

        // Calculate n (product of p and q)
        BigInteger n = p.multiply(q);

        // Calculate φ(n) (Euler's totient function)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Find the public exponent 'e' (a coprime of phi)
        BigInteger e = findCoprime(phi);

        // Find the private exponent 'd' using the public exponent 'e' and phi
        BigInteger d = findPrivateKey(e, phi);

        Scanner sc = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        // Encrypt plaintext using RSA encryption
        BigInteger[] ciphertext = rsaEncrypt(plaintext, e, n);

        // Output the ciphertext
        System.out.print("Ciphertext: ");
        for (BigInteger cipher : ciphertext) {
            System.out.print(cipher);
        }
        System.out.println();

        // Decrypt ciphertext using RSA decryption
        String decryptedText = rsaDecrypt(ciphertext, d, n);

        // Output decrypted plaintext
        System.out.println("Decrypted plaintext: " + decryptedText);

        sc.close();
    }

    // Method to find a coprime of phi (public exponent 'e')
    static BigInteger findCoprime(BigInteger phi) {
        BigInteger e = new BigInteger("2");
        while (e.compareTo(phi) < 0) {
            if (e.gcd(phi).equals(BigInteger.ONE)) {
                return e;
            }
            e = e.add(BigInteger.ONE);
        }
        return null;
    }

    // Method to find the private key 'd' using 'e' and phi
    static BigInteger findPrivateKey(BigInteger e, BigInteger phi) {
        BigInteger d = BigInteger.ZERO;
        BigInteger k = BigInteger.ONE;
        while (true) {
            d = (BigInteger.ONE.add(k.multiply(phi))).divide(e);
            if (d.multiply(e).mod(phi).equals(BigInteger.ONE)) {
                return d;
            }
            k = k.add(BigInteger.ONE);
        }
    }

    // Method to perform RSA encryption
    static BigInteger[] rsaEncrypt(String plaintext, BigInteger e, BigInteger n) {
        char[] chars = plaintext.toCharArray();
        BigInteger[] ciphertext = new BigInteger[chars.length];
        for (int i = 0; i < chars.length; i++) {
            BigInteger charValue = BigInteger.valueOf((int) chars[i]);
            ciphertext[i] = charValue.modPow(e, n);
        }
        return ciphertext;
    }

    // Method to perform RSA decryption
    static String rsaDecrypt(BigInteger[] ciphertext, BigInteger d, BigInteger n) {
        StringBuilder decryptedText = new StringBuilder();
        for (BigInteger cipher : ciphertext) {
            BigInteger charValue = cipher.modPow(d, n);
            decryptedText.append((char) charValue.intValue());
        }
        return decryptedText.toString();
    }
}
