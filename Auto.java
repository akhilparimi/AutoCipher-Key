import java.util.*;
import java.io.*;

//Java class - main function
public class Auto
{
//
static char[] alpha_chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	public static void main (String[] args) throws IOException
	{
        //reading file arguements
        File file = new File(args[0]);
        BufferedReader br= new BufferedReader(new FileReader(file));
        String input = "";
		String output = "";
        //reading strings present in both input file (in/out)
        input = br.readLine();
        //checking for edge case to trim all the spaces
        String trimstr = input.replaceAll("\\s", "");
        //checking forr edge case where the input string has any capital letters
        String capstr = trimstr.toLowerCase();
        //checking for file to print outputfile
		String sec_key = args[2];
        //checking for argument to encrypt/decrypt the given string
		int value = Integer.parseInt(args[3]);
        //if the value is 1 - string will be encrypted
		if (value == 1) 
		{
            output = autoEncryption(capstr, sec_key.toLowerCase());
		} else if (value == 0) //if the value is 1 - string will be decrypted
		{
            output = autoDecryption(capstr, sec_key.toLowerCase());
		}
        BufferedWriter f_writer = new BufferedWriter(new FileWriter(args[1]));
        if (value == 1) {
            System.out.println("Encrypted string: " + output);
        } else if (value == 0) {
            System.out.println("Decrypted string: " + output);
        }
        //final output will be written in the output file
		f_writer.write(output);
		f_writer.close();

	}
    //function for encrypting the string
	public static String autoEncryption(String inp_msg, String sec_key)
    {
        int msglength = inp_msg.length();
        String concatstr = sec_key.concat(inp_msg);
        String substr = concatstr.substring(0, concatstr.length() - sec_key.length());
        String encryptMsg = "";
  
        int x = 0;
        while (x < msglength) {
            int fir_char_pos = 0;
            int sec_char_pos = 0;
            for (int i = 0; i < alpha_chars.length; i++) {
                if (inp_msg.charAt(x) == alpha_chars[i]) {
                    fir_char_pos = i;
                }
                if (substr.charAt(x) == alpha_chars[i]) {
                    sec_char_pos = i;
                }
            }
            encryptMsg += alpha_chars[(fir_char_pos + sec_char_pos) % 26];
            x++;
        }
        String finalEncryptedMsg = encryptMsg.toLowerCase();
        return finalEncryptedMsg;
    }
    //function for decrypting the string
    public static String autoDecryption(String inp_msg, String sec_key)
    {
        String currKey = sec_key;
        String decryptMsg = "";
        int x = 0;
        while (x < inp_msg.length()) {
            int fir_char_pos = 0;
            int sec_char_pos = 0;
            for (int i = 0; i < alpha_chars.length; i++) {
                if (inp_msg.charAt(x) == alpha_chars[i]) {
                    fir_char_pos = i;
                }
                if (currKey.charAt(x) == alpha_chars[i]) {
                    sec_char_pos = i;
                }
            }
            int total = (fir_char_pos - sec_char_pos) % 26;
            int finalVal = total = (total < 0) ? total + 26 : total;
            decryptMsg += alpha_chars[finalVal];
            currKey += alpha_chars[finalVal];
            x++;
        }
        String finalDecryptedMsg = decryptMsg.toLowerCase();
        return finalDecryptedMsg;
    }
}

