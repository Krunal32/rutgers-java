public class MatrixOps
{
	public static double[][] multiply(double[][] matrix1, double[][] matrix2)
	{
		if (matrix1[0].length != matrix2.length)
		{
			return null;
		}
		
		double[][] output = new double[matrix1.length][matrix2[0].length];
		
		for (int i = 0; i < matrix1.length; i++)
		{
			for (int j = 0; j < matrix2[0].length; j++)
			{
				for (int k = 0; k < matrix1[0].length; k++)
				{
					output[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		
		return output;
	}
}