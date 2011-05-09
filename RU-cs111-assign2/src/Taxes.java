public class Taxes
{
	public static void main(String[] args)
	{
		double income = IO.readDouble();
		double temp_income = income;
		double tax = 0;
		
		if (temp_income >= 8000)
		{
			tax = tax + 8000*0.1;
			temp_income = temp_income-8000;
			if (temp_income >= 24000)
			{
				tax = tax + 24000*0.15;
				temp_income = temp_income-24000;
				if (temp_income >= 46000)
				{
					tax = tax + 46000*0.25;
					temp_income = temp_income-46000;
					if (temp_income >= 86000)
					{
						tax = tax + 86000*0.28;
						if (temp_income >= 193000)
						{
							tax = tax + 193000*0.33;
							temp_income = temp_income - 193000;
							tax = tax + temp_income*0.35;
						}
						else
							tax = tax+temp_income*0.33;
					}
					else
						tax = tax + temp_income*0.28;
				}
				else
					tax = tax + temp_income*0.25;
			}
			else
				tax = tax + temp_income*0.15;
			
		}
		else
			tax = tax + temp_income*0.1;
		
		if (income < 0)
			IO.reportBadInput();
		else
			IO.outputDoubleAnswer(Math.ceil(tax*100)/100);
	}
}