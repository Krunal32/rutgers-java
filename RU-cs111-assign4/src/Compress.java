public class Compress
{
	public static void main(String[] args)
	{
		String input = IO.readString();
		char prev_char = '1'; // since input cannot have numerals
		
		String output = "";
		for (int i = 0; i < input.length(); i++)
		{
			int char_count = 1;
			
			if (prev_char == input.charAt(i))
			{
				continue;
			}
			
			for (int j = i+1; j < input.length(); j++)
			{
				if (input.charAt(i) == input.charAt(j))
				{
					char_count++;
				}
				else
				{
					break;
				}
			}
			
			if (char_count > 1)
			{
				output = output + char_count + input.charAt(i);
			}
			else
			{
				output = output + input.charAt(i);
			}
			prev_char = input.charAt(i);
		}
		
		IO.outputStringAnswer(output);
	}
}