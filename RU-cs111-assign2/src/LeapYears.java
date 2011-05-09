public class LeapYears
{
	public static void main(String[] args)
	{
		double year_input = IO.readDouble();
		
		if (year_input < 0)
		{
			IO.reportBadInput();
		}
		else
		{
			if (year_input % 4 == 0)
			{
				if (year_input%100 == 0)
				{
					if (year_input%400 == 0)
					{
						IO.outputBooleanAnswer(true);
						return;
					}
					IO.outputBooleanAnswer(false);
					return;
				}
				IO.outputBooleanAnswer(true);
				return;
			}
			IO.outputBooleanAnswer(false);
		}
		
	}
}