public class Finance
{
	public static double APY(double rate, double compound)
	{
		if (rate <= 0 || compound <= 0)
		{
			return -1;
		}
	
		rate = rate/100;
		return (double) (Math.pow(1+(rate/compound), compound)-1)*100;
	}
	
	public static double loan_payment(double principal, double rate, double duration)
	{
		if (rate <= 0 || principal <= 0 || duration <= 0)
		{
			return -1;
		}
		
		rate = (rate/100)/12;
		duration = duration*12;
		return (double) ((principal*rate)/(1-Math.pow(1/(1+rate), duration)));
	}
}