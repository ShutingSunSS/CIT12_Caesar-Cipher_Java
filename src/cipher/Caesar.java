package cipher;
//import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays; // initiate an int array to 0
/**
 * @author Shuting Sun
 *
 */
public class Caesar {
	Set<String> dic = new HashSet<>(); //hash set.
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String args[]){
		new Caesar().run();
	}
	
	/**
	 * Escape the static. Interact with users.
	 */
	void run(){
		readDictionary();
		System.out.println("Enter a message to decipher: ");
		while(scanner.hasNext()){
		String aMessage = scanner.nextLine();
	    String mine = decipher(aMessage);
	    System.out.println(mine);
		}
	}
	
	/** 
	 * Encipher the plain text 
	 * @param shift How much to shift
	 * @param plainText A plain text to encipher
	 * @return The enciphered text
	 */
	String encipher(int shift, String plainText){
		int realShift = shift % 26;
		char[] ary = plainText.toCharArray();// Change the String to an Array of Characters.
		String myText = "";
		for (int i = 0; i < plainText.length(); i++){
			if (ary[i] >= 'a' && ary[i] <= 'z'){
				ary[i] = (char)(ary[i] + realShift);
				if(ary[i] > 'z')
					ary[i] = (char)(ary[i] - 26); // 26 Characters.
				if(ary[i] < 'a')
					ary[i] = (char)(ary[i] + 26);
			}
			if (ary[i] >= 'A' && ary[i] <= 'Z'){
				ary[i] = (char)(ary[i] + realShift);
				if(ary[i] > 'Z')
					ary[i] = (char)(ary[i] - 26); // 26 Characters.
				if(ary[i] < 'A')
					ary[i] = (char)(ary[i] + 26);		
			}
			myText = String.copyValueOf(ary);
		}	
		return myText;
	}

	/**
	  * Read in the dictionary from "wordsEn.txt", read a line (a word) at a time. Put each word into a set (a Hash Set).
	  * @return Read in the dictionary from "wordsEn.txt", read a line (a word) at a time. 
	  *         Put each word into a set (a Hash Set).
	  */ 
	void readDictionary(){
		File myFile;
		Scanner file = null;
		try{
			myFile = new File("wordsEn.txt");
			file = new Scanner(myFile);
		}catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		//int count = 0; 
		while(file.hasNextLine()){
			dic.add(file.nextLine().trim());
			//count++;// Count the number of word: 109583
		}
		//System.out.println(count); // The total is 109583
		// wordList.add(Integer.toString(count));
		/*System.out.println("Enter a word to search for: ");
		Scanner input = new Scanner(System.in);
		String search = input.nextLine();
		if (dic.contains(search)){
			System.out.println("Yes");
		}else{
			System.out.println("No");
		}
		file.close();
		input.close();*/
		file.close();
	}
	 /**
	  * Given any message enciphered using a Caesar cipher, decipher it and return the deciphered message
	  * @param cipheredText A message that need to be ciphered.
	  * @return Given any message enciphered using a Caesar cipher, decipher it and return the deciphered message
	  */ 
	String decipher(String cipheredText){
		int[] myCount = new int[26]; //initialize as 0
		Arrays.fill(myCount, 0);
		int max = 0;
		for (int i = 0; i < 26; i++){
			String myDecipher = encipher(i, 
					cipheredText).toLowerCase().trim().replaceAll("[^A-Za-z]+", " "); // Wash the text, retain only the plain letters
			//System.out.println(myDecipher);
			String[] myText = myDecipher.split(" ");
			for (int j = 0; j < myText.length; j++){
				if (dic.contains(myText[j]))
					{
					myCount[i]++;
					}
			}
			if(myCount[i] > max){
				max = myCount[i];
			}
		}
		int myShift = 0;
		for (int i = 0; i < 26; i++){
			if (max == myCount[i]){
				myShift = i;
				break;
			}
		}
		return encipher(myShift, cipheredText);
	}
}
