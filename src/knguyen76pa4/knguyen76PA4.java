package knguyen76pa4;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


/**
 *  Programming Assignment 4 
    Write a algorithm that can perform AES encryption and decryption by using Java. Then use the 
    AES algorithm with a fixed key to encrypt a message "ProgrammingAssignment4"and decrypt 
    the encrypt message. Print out your encrypted message and decrypted message. 
    Instruction: 
    1. [Okay] This assignment only allowed one single .java submission. 
    2. [Complete] Your work should contains at least two methods: encryptionAES and decryptionAES. 
    3. [Complete] You can only call method in main method. Please don't put any logic in main method. 
    4. [Disagree] Any redundant/unnecessary code will cause penalty. 
    5. [Okay] Write comments for each line of your code. This worth 40points out of 100. 
    6. [Disagree] Unrunable file will have O as final grade of this assignment. No exception this time. 
    7. [Complete] Use your CampuslD as file name and class name like "yhuang30PA4.java". 
 * @author Vinh Nguyen
 */

//this is the main class, which has my name in the desired format
public class knguyen76PA4 {
    //this is the main method with no logic
    public static void main(String[] args) throws Exception 
    {
        //fixed key in 16 byte length
        String fkey = "fixedkeyfixedkey";
        //message to encode
        String plaintextmessage = "ProgrammingAssignment4";
        
        //Printing the original message
        System.out.println("[String] Original message: " + plaintextmessage);
        //Printing the key
        System.out.println("[String] Key: " + fkey);
        //Printing the encoded key
        System.out.println("[Hex] Key: " + DatatypeConverter.printHexBinary(makeKeyFromString(fkey).getEncoded()));
        //Printing the encoded message
        System.out.println("[Hex] Encrypted Message: " + DatatypeConverter.printHexBinary(encryptionAES(plaintextmessage ,makeKeyFromString(fkey))));
        //Printing the decrypted message
        System.out.println("[String] Decrypted Message: " + decryptionAES(encryptionAES(plaintextmessage ,makeKeyFromString(fkey)), makeKeyFromString(fkey)));
    }
    
    //this is the AES encryption method
    public static byte[] encryptionAES(String pT, SecretKey sK) throws Exception
    {
        //instantiate a cipher class
        Cipher cipher = Cipher.getInstance("AES");
        //tell the cipher to initalize into encryption mode and encrypt the secret key
        cipher.init(Cipher.ENCRYPT_MODE, sK);
        //get the form in bytes
        byte[] output = cipher.doFinal(pT.getBytes());
        //and return the output
        return output;
    }
    
    //this is the AES decryption method
    public static String decryptionAES(byte[] eT, SecretKey sK) throws Exception
    {
        //instantiate a cipher class
        Cipher cipher = Cipher.getInstance("AES");
        //tell the cipher to intitalize into decryption mode and decrypt the message with the private key
        cipher.init(Cipher.DECRYPT_MODE, sK);
        //get the form in bytes
        byte[] outputByte = cipher.doFinal(eT);
        //convert the bytes to String format
        String outputString = new String(outputByte);
        //return the output
        return outputString;
    }
    
    //this method makes a key from a string
    public static SecretKey makeKeyFromString(String key) throws Exception
    {
        //convert the string into byteform
        byte[] bKey = key.getBytes("UTF-8");
        //create a SecretKey from the given plaintext key
        SecretKey sk = new SecretKeySpec(bKey, "AES");
        //return the SecretKey
        return sk;
    }
    
    public static String makeStringFromKey(SecretKey sk)
    {
        //return the SecretKey back into plaintext format
        String ek = Base64.getEncoder().encodeToString(sk.getEncoded());
        //return the string in plaintext
        return ek;
    }
    
}
