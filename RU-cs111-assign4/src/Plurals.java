public class Plurals
{
	public static void main(String[] args)
	{
		String input = IO.readString();
		String output = null;
		String first_part = "";
		String last_part = "";
		String separator = "";
		
		if (input.lastIndexOf("-") != -1 || input.lastIndexOf(" ") != -1)
		{
			if (input.indexOf("-") > input.indexOf(" "))
			{
				first_part = input.substring(0,input.indexOf("-"));	
				last_part = input.substring(input.indexOf("-")+1, input.length());
				separator = "-";
			}
			else
			{
				first_part = input.substring(0,input.indexOf(" "));	
				last_part = input.substring(input.indexOf(" ")+1, input.length());
				separator = " ";
			}
		}
		else
		{
			first_part = input;
		}
		
		if (first_part.endsWith("ch") || first_part.endsWith("x") || first_part.endsWith("s"))
		{
			if (first_part.endsWith("us") && first_part.length() > 3)
			{
				output = first_part.substring(0, first_part.length()-2).concat("i");
			}
			else
			{
				output = first_part.concat("es");
			}
		}
		else
		{
			output = first_part.concat("s");
		}
		
		IO.outputStringAnswer(output+separator+last_part);
	}
}