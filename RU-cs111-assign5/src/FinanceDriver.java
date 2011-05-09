public class FinanceDriver
{
    public static void main(String[] args)
    {
	// example: if we were testing the Math module rather than
	// your Finance module, a test case would look like this

	System.out.println(Finance.APY(20, 4));
	// expected output: 21.55
	
	System.out.println(Finance.APY(10, 12));
	// expected 10.47
	
	System.out.println(Finance.APY(0, 12));
	// expected -1
	
	System.out.println(Finance.APY(10, 0));
	// expected -1
	
	System.out.println(Finance.APY(23.2, 6));
	// expected 25.56
	
	System.out.println(Finance.APY(10, 12));
	// expected 10.47
	
	System.out.println(Finance.APY(2.125, 6));
	// expected 2.14
	
	System.out.println(Finance.loan_payment(10000, 2.125, 10));
	// expected 92.574
	
	System.out.println(Finance.loan_payment(0, 2.125, 10));
	// expected -1
	
	System.out.println(Finance.loan_payment(10000, 6.8, 10));
	// expected 115.08
    }
}