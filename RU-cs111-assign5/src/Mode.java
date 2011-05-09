public class Mode
{
	public static void main(String[] args)
	{
		int size = IO.readInt();
		Double[] set = new Double[size];
		
		int count = 0;
		for (int i = 0; i < size; i++)
		{
			set[i] = IO.readDouble();
			count++;
		}
		
		double number = 0;
		double occurs = 0;
		for (int i = 0; i < set.length; i++)
		{
			double k = 0;
			for (int j = 0; j < set.length; j++)
			{
				if (set[i].equals(set[j]))
				{
					k+=1;
				}
			}
			
			if (k > occurs)
			{
				number = set[i];
				occurs = k;
			}
		}
		
		IO.outputDoubleAnswer(number);
	}
}