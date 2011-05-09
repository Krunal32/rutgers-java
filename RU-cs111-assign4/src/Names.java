public class Names
{
	public static void main(String[] args)
	{	
		String[] list = new String[3]; // configurable variable
		
		for (int i = 0; i < list.length; i++)
		{
			String input = IO.readString();
			if (input.lastIndexOf(",") != -1)
			{
				list[i] = input.substring(0, input.indexOf(","));
			}
			else
			{
				list[i] = input.substring(input.lastIndexOf(" ")+1, input.length());
			}
		}
		
		for (int i = 0; i < list.length; i++)
		{
			for (int j = i+1; j < list.length; j++)
			{
				if (list[j].compareTo(list[i]) < 0)
				{
					String temp = list[i];
					list[i] = list[j];
					list[j] = temp;
				}
				
			}
		}
		
		for (int i = 0; i < list.length; i++)
		{
			IO.outputStringAnswer(list[i]);
		}
		
	}
}