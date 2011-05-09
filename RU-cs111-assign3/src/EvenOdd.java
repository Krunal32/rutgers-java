public class EvenOdd
{
	public static void main(String[] args)
	{
		int even = 0;
		int odd = 0;
		while (true)
		{
			int number = IO.readInt();
			if (number == 0)
			{
				break;
			}
			else if (number%2 == 0)
			{
				even++;
			}
			else
			{
				odd++;
			}
		}
		
		IO.outputIntAnswer(even);
		IO.outputIntAnswer(odd);
	}
}