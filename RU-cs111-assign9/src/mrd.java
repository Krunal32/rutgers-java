public class mrd
{
	public static void main(String[] args)
	{
		double p = MathRec.power(3.2, 3);
		System.out.println(p); // should print 32.768

		int l = MathRec.log(5, 125);
		System.out.println(l); // should print 3

		int x = MathRec.convertPositiveInt("123");
		System.out.println(x); // should print 54321
	}
}