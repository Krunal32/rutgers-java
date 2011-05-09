public class setd
{
	public static void main(String[] args)
	{
		boolean success;

		Set set1 = new Set(3);

		success = set1.add(4.0);
		System.out.println(success); // should print "true"
		success = set1.add(9.0);
		System.out.println(success); // should print "true"
		success = set1.add(2.0);
		System.out.println(success); // should print "true"
		success = set1.add(2.4);
		System.out.println(success); // should print "false"

		System.out.println(set1.contains(9.0)); // should print "true"
		System.out.println(set1.contains(2.4)); // should print "false"

		System.out.println(set1.toString()); // should print "{4.0, 9.0, 2.0}"

		Set set2 = new Set(1000);
		set2.add(3.0);
		set2.add(2.0);
		set2.add(8.0);
		set2.add(4.0);
		Set set3 = set1.difference(set2);
		System.out.println(set3.toString()); // should print "{9.0}"
	}
}