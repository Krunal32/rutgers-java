public class cnd
{
	public static void main(String[] args)
	{
		ComplexNumber cn1, cn2, cn3;

		cn1 = new ComplexNumber(3, 4);
		System.out.println(cn1.getReal()); // should print "3.0"
		System.out.println(cn1.getImaginary()); // should print "4.0"
		System.out.println(cn1.toString()); // should print "3.0 + 4.0i"
		System.out.println(cn1.getComplexModulus()); // should print "5.0"

		cn2 = new ComplexNumber(-5, 10);
		cn3 = cn1.add(cn2);
		System.out.println(cn3.getReal()); // should print "-2.0"
		System.out.println(cn3.getImaginary()); // should print "14.0"

		// note that if you have not yet written the add() method,
		// the above lines will cause NullPointerExceptions
		// (because the empty add() method returns null)
	}
}