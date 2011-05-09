public class ReverseWords
{
	public static void main(String[] args)
	{
		String input = IO.readString();
		String[] split = input.split(" ");
		String output = "";
		
		for (int i = 0; i < split.length; i++)
		{
			for (int j = split[i].length()-1; j >= 0; j--)
			{
				output = output + split[i].charAt(j);
			}
			
			output = output.concat(" ");
		}

		output = output.substring(0, output.length()-1);
		IO.outputStringAnswer(output);
	}
}