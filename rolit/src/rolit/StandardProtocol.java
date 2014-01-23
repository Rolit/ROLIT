package protocollen;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

public class StandardProtocol {
	//Constanten voor de features die de client of server ondersteund
	public static final int NoExtentionSupport = 0;
	public static final int ChatOnly = 1;
	public static final int ChallengeOnly = 2;
	public static final int ChatAndChallenge = 3;
	
	//String-waarden voor booleans in het protocol
	public static final String BooleanTrue = "true";
	public static final String BooleanFalse = "false";
	
	//Versie in de handshake voor een standaardimplementatie
	public static final String NoVersion = "Standaard";
	
	//Einde van regel
	public static final String EndOfLine = "\r\n";
	
	//Delimiter van de commando's
	public static final String Delimiter = " ";
	
	//Alle Base64 character
	
	public static final String Base64Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	public static final char Base64Padding = '=';
	
	/*
	 * Methode om van een ImputStream een correcte PrintStream te maken
	 */
	public PrintStream getPrintStream(OutputStream output){
		try{
			return new PrintStream(output, true, "UTF-8");
		}
		catch (UnsupportedEncodingException e){
			return null;
		}
	}
	
	/*
	 * Methode om van een InputStream een correcte BufferedReader te maken
	 */
	public BufferedReader getBufferedReader(InputStream input){
		try{
			return new BufferedReader(new InputStreamReader(input, "UTF-8"));
		}
		catch (UnsupportedEncodingException e){
			return null;
		}
	}
	
	/*
	 * Methode om een commando naar een PrintStream te schrijven
	 */
	public void writeCommand(String[] commandParts, PrintStream output){
		boolean first = true;
		String command = "";
		for(String argument : commandParts){
			if(!first){
				command += " ";
			}
			first = false;
			command += argument;
		}
		output.print(command);
		output.print("\r\n");
	}
	
	/*
	 * Methode om een commando van een BufferedReader te lezen
	 */
	public String[] readCommandFrom(BufferedReader input) throws IOException{
		String line = input.readLine();
		return line.split(" ");
	}
	
	/*
	 * Converteert bytes naar Base64-String
	 */
	
	public String base64Encode(byte[] data) {
        String result = "";

        for(int i = 0; i < data.length / 3; i++) {
            byte byte1 = data[i * 3], byte2 = data[i * 3 + 1], byte3 = data[i * 3 + 2];

            result += Base64Char.charAt(byte1 / 4);
            result += Base64Char.charAt((byte1 % 4) * 16 + byte2 / 16);
            result += Base64Char.charAt((byte2 % 16) * 4 + byte3 / 64);
            result += Base64Char.charAt(byte3 % 64);
        }

        if(data.length % 3 == 1) {
            byte byte1 = data[data.length - 1];

            result += Base64Char.charAt(byte1 / 4);
            result += Base64Char.charAt((byte1 % 4) * 16);
            result += Base64Padding;
            result += Base64Padding;
        } else if(data.length % 3 == 2) {
            byte byte1 = data[data.length - 2], byte2 = data[data.length - 1];

            result += Base64Char.charAt(byte1 / 4);
            result += Base64Char.charAt((byte1 % 4) * 16 + byte2 / 16);
            result += Base64Char.charAt((byte2 % 16) * 4);
            result += Base64Padding;
        }

        return result;
    }
	
	/*
	 * Converteert een Base64-String naar bytes
	 */
	
	public byte[] base64Decode(String data) {
        byte[] result = new byte[data.length() / 4 * 3];

        for(int i = 0; i < data.length() / 4; i++) {
            char char1 = data.charAt(i * 4), char2 = data.charAt(i * 4 + 1),
                    char3 = data.charAt(i * 4 + 2), char4 = data.charAt(i * 4 + 3);

            result[i * 3] = (byte) (Base64Char.indexOf(char1) * 4 + Base64Char.indexOf(char2) / 16);
            result[i * 3 + 1] = (byte) ((Base64Char.indexOf(char2) % 16) * 16 + Base64Char.indexOf(char3) / 4);
            result[i * 3 + 2] = (byte) (((Base64Char.indexOf(char3) + 4) % 4) * 64 + Base64Char.indexOf(char4));
        }

        if(data.charAt(data.length() - 2) == '=') {
            return Arrays.copyOfRange(result, 0, result.length - 2);
        } else if(data.charAt(data.length() - 1) == '=') {
            return Arrays.copyOfRange(result, 0, result.length - 1);
        } else {
            return result;
        }
    }
	
	/*
	 * Converteert een String van de ss-security-server naar een PrivateKey
	 */
	
	  public PrivateKey stringToPrivateKey(String data) throws InvalidKeySpecException {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(data.getBytes());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
	
	/*
	 * Signt een bericht met een PrivateKey en retourneert het resultaat in Base64
	 */
	  
	  public String sign(String message, PrivateKey key) throws InvalidKeyException{
		  try{
			  Signature signature = Signature.getInstance("SHA1withRSA");
			  signature.initSign(key);
			  signature.update(message.getBytes());
			  return base64Encode(signature.sign());
		  }
		  catch (NoSuchAlgorithmException e) {
			  return null;
		  }
		  catch (SignatureException e) {
			  return null;
		  }
	  }
	
	/*
	 * Conveert een String van de ss-security-server naar een PublicKey
	 */
	  public PublicKey stringToPublicKey(String date) throws InvalidKeySpecException {
		  try{
			  PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(date.getBytes());
			  KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			  return keyFactory.generatePublic(keySpec);
		  }
		  catch(NoSuchAlgorithmException e){
			  return null;
		  }
	  }
	  
	  /*
	   * Verifieert een gesignd bericht voor een PublicKey
	   */
	  public boolean verify(String message, String signedMessage, PublicKey key) throws InvalidKeyException{
		  try{
			  Signature signature = Signature.getInstance("SHA1withRSA");
			  signature.initVerify(key);
			  signature.update(message.getBytes());
			  return signature.verify(base64Decode(signedMessage));
		  }
		  catch(NoSuchAlgorithmException e){
			  return false;
		  }
		  catch(SignatureException e){
			  return false;
		  }
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
