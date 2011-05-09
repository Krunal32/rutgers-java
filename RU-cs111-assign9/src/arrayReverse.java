public class arrayReverse
{
	public static void main(String[] args)
	{
		double[] array = new double[5];
		
		for (int i = 0; i < array.length; i++)
		{
			array[i] = IO.readDouble();
		}
		
		double[] new_array = reverse(array);
		
		for (int i = 0; i < new_array.length; i++)
		{
			IO.outputDoubleAnswer(new_array[i]);
		}
	}
	
	public static double[] reverse(double[] array)
	{
		double[] new_array = new double[array.length];
		
		for (int i = 0; i < new_array.length; i++)
		{
			new_array[i] = array[(array.length-1) -i];
		}
		
		return new_array;
	}
}