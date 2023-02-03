import java.util.*;
import java.io.*;

public class Auto
{
static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXY";
	public static void main (String[] args) throws IOException
	{
		String inFile = args[0];
		String outFile = args[1];
		String key = args[2];
		String input = "";
		String output = "";
		int flag = Integer.parseInt(args[3]);
			
		if (flag == 1) 
		{
		   File file = new File(args[0]);
			BufferedReader br= new BufferedReader(new FileReader(file));
			input = br.readLine();
            output = autoEncryption(input, key);

		} 

		if (flag == 0) 
		{
			
		File file = new File(args[0]);
			BufferedReader br= new BufferedReader(new FileReader(file));
			input = br.readLine();
            output = autoDecryption(input, key);

		}



        BufferedWriter f_writer = new BufferedWriter(new FileWriter(args[1]));
		f_writer.write(output);
		f_writer.close();

	}

  
	public static String autoEncryption(String msg, String key)
    {
        int len = msg.length();
  
        // generating the keystream
        String newKey = key.concat(msg);
        newKey = newKey.substring(0, newKey.length() - key.length());
        String encryptMsg = "";
  
        // applying encryption algorithm
        for (int x = 0; x < len; x++) {
            int first = alphabet.indexOf(msg.charAt(x));
            int second = alphabet.indexOf(newKey.charAt(x));
            int total = (first + second) % 26;
            encryptMsg += alphabet.charAt(total);
        }
        return encryptMsg;
    }
  
    public static String autoDecryption(String msg, String key)
    {
        String currentKey = key;
        String decryptMsg = "";
  
        // applying decryption algorithm
        for (int x = 0; x < msg.length(); x++) {
            int get1 = alphabet.indexOf(msg.charAt(x));
            int get2 = alphabet.indexOf(currentKey.charAt(x));
            int total = (get1 - get2) % 26;
            total = (total < 0) ? total + 26 : total;
            decryptMsg += alphabet.charAt(total);
            currentKey += alphabet.charAt(total);
        }
        return decryptMsg;
    }
}

