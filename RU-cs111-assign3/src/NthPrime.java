public class NthPrime
{
	public static void main(String[] args)
	{
		int num_of_prime;
		while (true)
		{
			num_of_prime = IO.readInt();
			if (num_of_prime > 0)
			{
				break;
			}
			else
			{
				IO.reportBadInput();
			}
		}
		
		int prime = 1;
		int i = 0;
		while (i < num_of_prime)
		{
			prime++;
			
			boolean isPrime = true;
			for (int j = 2; j < prime; j++)
			{
				if (prime%j == 0)
				{
					isPrime = false;
					break;
				}
			}
			
			if (isPrime)
			{
				i++;
			}
		}
		
		IO.outputIntAnswer(prime);
	}
}