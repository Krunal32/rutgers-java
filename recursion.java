public class recursion
{
	public static void main(String[] args)
	{
		String test = "this is a sentence";
		System.out.println(reverseWords(test));
		
		// this is the trace of the function
		// "this is a sentence" -> reverseWords(1)
		// "this" -> reverseWord, "is a sentence" -> reverseWords(2)
		// reverseWord -> "siht"
		// "is" -> reverseWord, "a sentence" -> reverseWords(3)
		// reverseWord -> "si"
		// "a" -> reverseWord, "sentence" -> reverseWords(4)
		// reverseWord -> "a"
		// reverseWord -> "ecnetnes"
		// reverseWords(4) -> "ecnetnes"
		// reverseWords(3) -> "a ecnetnes"
		// reverseWords(2) -> "si a ecnetnes"
		// reverseWords(1) -> "siht si a ecnetnes"
		// complete
		
		String test2 = "mouse dog cat deer";
		System.out.println(findLeast(test2));
		
		// trace of the function
		// 
	}
	
	public static String reverse(String input)
	{
		if (input.indexOf(" ") == -1)
			return input;
		
		return input.substring(input.lastIndexOf(" ")+1, input.length()) + " " // this is the last word
			+reverse(input.substring(0, input.lastIndexOf(" "))); // recursion on the remaining sentence
	}
	
	public static String reverse2(String input)
	{
		if (input.indexOf(" ") == -1)
			return input;
		
		return reverse(input.substring(input.indexOf(" "))) + " " + input.substring(0, input.indexOf(" "));
	}
	
	public static String reverseWords(String input)
	{
		if (input.indexOf(" ") == -1)
			return reverseWord(input); // if we don't have anymore input, this will stop creating "children"
		
		return reverseWord(input.substring(0, input.indexOf(" ")))  // give our word to the helper function
		+ " " + reverseWords(input.substring(input.indexOf(" ")+1)); // give the rest of our sentence to the recursion function
	}
	
	private static String reverseWord(String input)
	{
		if (input.isEmpty())
			return ""; // this will occur if there is no more input
		
		return input.substring(input.length()-1, input.length()) // we get the last letter, and append it first
		+ reverseWord(input.substring(0, input.length()-1)); // then we send the rest of the string to the function
	}
	
	public static String findLeast(String input)
	{
		return findLeast(input.substring(0, input.indexOf(" ")), input.substring(input.indexOf(" ")+1, input.length()));
		// we are putting 
	}
	
	private static String findLeast(String needle, String haystack) // this is just a helper function to get us started off on our recursion
	{
		if (haystack.indexOf(" ") == -1)
			return getLeast(needle, haystack);
		
		return getLeast(needle, findLeast(haystack.substring(haystack.indexOf(" ")+1, haystack.length()))); 
		// whoops, i accidentally made this really confusing, it calls on the parent
		// where the parent will prepend the string properly
	}
	
	// cat dog mouse
	// (cat) dog mouse
	// (cat, dog) mouse
	// dog < mouse, return dog
	// (cat) dog
	// cat < dog, return cat
	
	private static String getLeast(String s1, String s2)
	{
		if (s1.compareToIgnoreCase(s2) < 0)
			return s1;
		else
			return s2;
				
	}
}