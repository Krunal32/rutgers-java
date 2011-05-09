package search;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
	public static void main(String[] args) throws IOException {
		LittleSearchEngine lse = new LittleSearchEngine();
		lse.makeIndex("docs.txt","noisewords.txt");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//lse.printHash();
		
		while(true)
		{
			System.out.print("Enter a word to check: ");
			String w1 = br.readLine().trim();
			
			System.out.print("Enter another word to check: ");
			String w2 = br.readLine().trim();
			
			System.out.println("Result = "+lse.top5search(w1, w2));
					
		}
	}
}