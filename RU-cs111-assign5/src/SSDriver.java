public class SSDriver
{
    public static void main(String[] args)
    {
	// example: if we were testing the Math module rather than
	// your SentenceStats module, a test case would look like this

	System.out.println(SentenceStats.count_occur("in", "We are in in in"));
	// expected output: 3
	
	System.out.println(SentenceStats.count_occur("is", "Is there too much to say? or is there not enough to say"));
	// expected output: 2
	
	System.out.println(SentenceStats.count_occur("me", "Going home"));
	// expected output: 0
    }
}