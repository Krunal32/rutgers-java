public class OrderCost
{
	public static void main(String[] args)
	{		
		System.out.println("How many DVDs do you want to buy?");
		int number_of_dvds = IO.readInt();
		
		System.out.println("What is the total value of coupons that you have?");
		double coupons = IO.readDouble();
		
		double pre_total = (number_of_dvds*19.95)-coupons;
		double total;
		
		if (pre_total > 100)
		{
			total = pre_total;
		}
		else
		{
			total = pre_total+(number_of_dvds*3);
		}
		
		
		if (number_of_dvds < 0 || coupons < 0 || total < 0)
			IO.reportBadInput();
		else
			IO.outputDoubleAnswer(Math.ceil(total*100)/100);
	}
}