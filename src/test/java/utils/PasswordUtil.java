package utils;

import java.io.Console;

import org.apache.commons.codec.binary.Base64;

public class PasswordUtil {

	public static void main(String[] args) {

			
		String str="YourPassword";
		

		// Encode data on your side using BASE64
			  byte[] encodedString = Base64.encodeBase64(str.getBytes());
			  System.out.println("encoded value is " + new String(encodedString));

			  // Decode data on other side, by processing encoded data
			  byte[] decodedString = Base64.decodeBase64(encodedString);
			  System.out.println("Decoded value is " + new String(decodedString));
	  

	}	

}
