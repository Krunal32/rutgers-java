public class Party
{
	public static void main(String[] args)
	{		
		System.out.println("How many people are attending the party?");
		double people_attending = IO.readInt();
		
		System.out.println("How many slices of pizza is each person allowed to eat?");
		double slice_per_person = IO.readInt();
		
		System.out.println("How many cans of soda is each person allowed to drink?");
		double cans_per_person = IO.readInt();
		
		System.out.println("How much is a pie of pizza?");
		double price_per_pizza = IO.readDouble();
		
		System.out.println("How many slices does a pie of pizza have?");
		double slices_per_pizza = IO.readInt();
		
		System.out.println("How much is a case of soda?");
		double price_per_case = IO.readDouble();
		
		System.out.println("How many cans does a case of soda have?");
		double cans_per_case = IO.readInt();
		
		double total_pizza = Math.ceil((people_attending*slice_per_person)/slices_per_pizza)*price_per_pizza; 
		double total_soda = Math.ceil((people_attending*cans_per_person)/cans_per_case)*price_per_case;
		
		if (people_attending < 0 || slice_per_person < 0 || cans_per_person < 0 || price_per_pizza < 0 || slices_per_pizza < 0 || price_per_case < 0 || cans_per_case < 0)
			IO.reportBadInput();
		else
			IO.outputDoubleAnswer(Math.ceil((total_pizza+total_soda)*100)/100);
		
	}
}