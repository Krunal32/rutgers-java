public class MathRec
{
	// DO NOT ADD ANY FIELDS TO THIS CLASS


	public static double power(double base, int exponent)
	{
		if (exponent == 1)
			return base;
		
		return base*MathRec.power(base, exponent-1);
	}

	public static int log(int base, int value)
	{
		if (base == value)
			return 1;
		
		return 1+MathRec.log(base,value/base);
	}

	public static int convertPositiveInt(String s)
	{
		if (convertDigit(s.charAt(0)) == -1)
		{
			return -1;
		}
		
		if (s.length() == 1)
		{
			return convertDigit(s.charAt(0));
		}
		
		int nextNum = convertPositiveInt(s.substring(1));
		
		if (nextNum == -1)
		{
			return -1;
		}
		
		int convertedNum = (int) (convertDigit(s.charAt(0))*10*Math.pow(10, s.length()-2)+nextNum);
		
		return convertedNum;
	}

	// This method is provided for your use in convertPositiveInt:
	public static int convertDigit(char digit)
	{
		if (digit >= '0'  &&  digit <= '9')
		{
			return digit - '0';
		}
		else
		{
			return -1;
		}
	}
}