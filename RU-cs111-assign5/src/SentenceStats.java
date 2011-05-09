public class SentenceStats
{
	public static int count_occur(String needle, String haystack)
	{
		int count = 0;
		haystack = " "+haystack.toLowerCase().trim()+" ";
		needle = " "+needle.toLowerCase().trim()+" ";
		while (haystack.indexOf(needle) != -1)
		{
			count++;
			haystack = haystack.substring(haystack.indexOf(needle)+needle.length()-1, haystack.length());
		}
		
		return count;
	}
}