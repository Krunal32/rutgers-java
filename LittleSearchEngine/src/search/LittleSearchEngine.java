package search;

import java.io.*;
import java.util.*;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 */
class Occurrence {
	/**
	 * Document in which a keyword occurs.
	 */
	String document;
	
	/**
	 * The frequency (number of times) the keyword occurs in the above document.
	 */
	int frequency;
	
	/**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 */
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			mergeKeyWords(kws);
		}
		
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeyWords(String docFile) 
	throws FileNotFoundException {
		HashMap<String, Occurrence> docMap = new HashMap<String, Occurrence>();
		Scanner scanner = new Scanner(new File(docFile));
		
		while(scanner.hasNext())
		{
			String word = getKeyWord(scanner.next());
			
			if (word != null)
			{
				if (docMap.containsKey(word))
					docMap.get(word).frequency++;
				else
					docMap.put(word, new Occurrence(docFile, 1));
			}
		}
		
		System.out.println(docMap);
		return docMap;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeyWords(HashMap<String,Occurrence> kws) {
		Iterator<String> keyIterator = kws.keySet().iterator();
		while (keyIterator.hasNext())
		{
			String keyword = keyIterator.next().toString();
			if (keywordsIndex.containsKey(keyword))
			{
				ArrayList<Occurrence> masterOccurList = keywordsIndex.get(keyword);
				masterOccurList.add(kws.get(keyword));
				this.insertLastOccurrence(masterOccurList); // will put it in its place
			}
			else
			{
				ArrayList<Occurrence> newOccurList = new ArrayList<Occurrence>();
				newOccurList.add(kws.get(keyword));
				keywordsIndex.put(keyword, newOccurList);
			}
		}
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * trailing punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyWord(String word) {
		word = word.trim().toLowerCase();
		
		if (word.indexOf('-') > -1 
				|| word.matches("[^A-Za-z]") 
				|| noiseWords.containsKey(word))
		{
			return null;
		}
		else
		{
			word = word.replaceAll("[^a-zA-Z0-9]", "");
		}
		
		if (word == "")
		{
			return null;
		}
		
		return word;
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion is done by
	 * first finding the correct spot using binary search, then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		System.out.println(occs);
		Occurrence lastElement = occs.remove(occs.size()-1);
		System.out.println(lastElement);
		System.out.println(this.binarySearch(occs, lastElement.frequency, 0, occs.size()-1));
		ArrayList<Integer> encounteredElements = this.binarySearch(occs, lastElement.frequency, 0, occs.size()-1);
		
		occs.add(encounteredElements.get(encounteredElements.size()-1), lastElement);
		System.out.println(occs);
		System.out.println("---------------");
		System.out.println(encounteredElements);
		return encounteredElements;
	}
	
	private ArrayList<Integer> binarySearch(ArrayList<Occurrence> occs, int value, int low, int high)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (high < low)
		{
			list.add(low);
			return list;
		}
		
		int mid = low + (high - low) / 2;
		list.add(mid);
		
		if (occs.get(mid).frequency < value)
		{
			list.addAll(this.binarySearch(occs, value, low, mid-1));
			return list;
		}
		else if (occs.get(mid).frequency > value)
		{
			list.addAll(this.binarySearch(occs, value, mid+1, high));
			return list;
		}
		else
			return list;
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		ArrayList<Occurrence> ow1 = keywordsIndex.get(kw1);
		ArrayList<Occurrence> ow2 = keywordsIndex.get(kw2);
		ArrayList<Occurrence> combinedTemp = ow1;
		
	//	System.out.println(ow1);
	//	System.out.println(ow2);
		
		if (ow1 == null && ow2 == null)
		{
			return null;
		}
		else if (combinedTemp == null)
		{
			combinedTemp = new ArrayList<Occurrence>();
		}
		
		if (ow2 != null)
		{
			

			for (int i = 0; i < ow2.size(); i++)
			{
				if (combinedTemp.contains(ow2.get(i).document))
				{
					if (ow2.get(i).frequency > combinedTemp.get(i).frequency)
					{
						combinedTemp.remove(ow2.get(i).document);
					}
					else
					{
						break;
					}
				}
				
				combinedTemp.add(ow2.get(i));
				this.insertLastOccurrence(combinedTemp); // this will sort for us
			}
			
		}
		
		ArrayList<String> top5 = new ArrayList<String>();
		int recurse = 0;
		
		if (combinedTemp.size() < 5)
		{
			recurse = combinedTemp.size();
		}
		else
		{
			recurse = 5;
		}
		
		for (int i = 0; i < recurse; i++)
		{
			if (combinedTemp.get(i) == null)
			{
				break;
			}
			
			System.out.println(combinedTemp.get(i).document);
			top5.add(combinedTemp.get(i).document);
		}
		
		return top5;
	}
}