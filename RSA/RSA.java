import java.math.BigInteger;
import java.util.Scanner;

public class RSA{
	public static void main(String args[]){
		BigInteger p=new BigInteger("17");
		BigInteger q=new BigInteger("23");
		BigInteger n=p.multiply(q);
		BigInteger phi=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		BigInteger e=findCoprime(phi);
		BigInteger d=findPrivateKey(e,phi);
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter plaintext: ");
		String plaintext=sc.nextLine();
		BigInteger[] ciphertext=rsaEncrypt(plaintext,e,n);
		System.out.print("Ciphertext: ");
		for(BigInteger cipher:ciphertext){
			System.out.print(cipher);
		}
		System.out.println();
		String decryptedText=rsaDecrypt(ciphertext,d,n);
		System.out.println("Decrypted plaintext: "+decryptedText);
	}
	static BigInteger findCoprime(BigInteger phi){
		BigInteger e=new BigInteger("2");
		while(e.compareTo(phi)<0){
			if(e.gcd(phi).equals(BigInteger.ONE)){
				return e;
			}
			e=e.add(BigInteger.ONE);
		}
		return null;
	}
	static BigInteger findPrivateKey(BigInteger e, BigInteger phi){
		BigInteger d=BigInteger.ZERO;
		BigInteger k=BigInteger.ONE;
		while(true){
			d=(BigInteger.ONE.add(k.multiply(phi))).divide(e);
			if(d.multiply(e).mod(phi).equals(BigInteger.ONE)){
				return d;
			}
			k=k.add(BigInteger.ONE);
		}
	}
	static BigInteger[] rsaEncrypt(String plaintext, BigInteger e, BigInteger n){
		char[] chars=plaintext.toCharArray();
		BigInteger[] ciphertext=new BigInteger[chars.length];
		for(int i=0;i<chars.length;i++){
			BigInteger charValue=BigInteger.valueOf((int) chars[i]);
			ciphertext[i]=charValue.modPow(e,n);
		}
		return ciphertext;
	}
	static String rsaDecrypt(BigInteger[] ciphertext, BigInteger d, BigInteger n){
		StringBuilder decryptedText=new StringBuilder();
		for(BigInteger cipher:ciphertext){
			BigInteger charValue=cipher.modPow(d,n);
			decryptedText.append((char)charValue.intValue());
		}
		return decryptedText.toString();
	}
}
