public class Scores
{
	public static void main(String[] args)
	{
		
		int num_of_judges = 0;
		while (num_of_judges < 3)
		{
			num_of_judges = IO.readInt();
			
			if (num_of_judges < 3)
			{
				IO.reportBadInput();
			}
		}
		
		if (num_of_judges < 3)
		{
			IO.reportBadInput(); // We want something bigger than 2...
		}
		
		int i = 0;
		double sum_of_scores = 0;
		double highest_score = 0;
		double lowest_score = 10;
		while (i < num_of_judges)
		{
			double inputted_score = IO.readDouble();
			if (inputted_score > 10 || inputted_score < 0)
			{
				IO.reportBadInput();
			}
			else
			{
				if (inputted_score > highest_score)
				{
					highest_score = inputted_score;
				}
				
				if (inputted_score < lowest_score)
				{
					lowest_score = inputted_score;
				}
				
				sum_of_scores = sum_of_scores + inputted_score;
				
				i++;
			}
		}
		
		IO.outputDoubleAnswer((sum_of_scores-(highest_score+lowest_score))/(num_of_judges-2));
	}
}