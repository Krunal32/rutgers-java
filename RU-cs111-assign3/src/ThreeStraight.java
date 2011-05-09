public class ThreeStraight
{
	public static void main(String[] args)
	{
		int input = 0;
		int last_input = 0;
		int count = 0;
		while (true)
		{
			last_input = input;
			input = IO.readInt();
			
			if (count == 0)
			{
				count++;
			}
			else if (count == 1 && last_input == input)
			{
				count++;
			}
			else if (count == 2 && last_input == input)
			{
				IO.outputIntAnswer(input);
				break;
			}
			else
			{
				count = 1;
			}
			
		}
	}
}