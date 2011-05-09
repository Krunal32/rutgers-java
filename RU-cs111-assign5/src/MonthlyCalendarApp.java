public class MonthlyCalendarApp
{
	public static void main(String[] args)
	{
		final int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int calendarMonth = getMonth();
		int firstDayOfWeek = getFirstDayOfWeek();
		int daysInMonth = daysPerMonth[calendarMonth - 1];
		String[] calendar = MonthlyCalendarOps.createCalendar(daysInMonth);
		
		boolean done;
		do
		{
			int choice = getMenuChoice();
			done = executeChoice(choice, calendar, firstDayOfWeek);
		}
		while ( ! done );
	}

	public static int getMonth()
	{
		System.out.print("For which month would you like to keep a calendar (1-12)? ");
		int month = IO.readInt();
		while (month < 1  ||  month > 12)
		{
			System.out.println("That is not a valid month. Pick 1-12. ");
			month = IO.readInt();
		}
		return month;
	}

	public static int getFirstDayOfWeek()
	{
		System.out.print("On what day of the week does the month start (0-6)? ");
		int firstDayOfWeek = IO.readInt();
		while (firstDayOfWeek < 0  ||  firstDayOfWeek > 6)
		{
			System.out.println("That is not a valid day of week. Pick 0-6. ");
			firstDayOfWeek = IO.readInt();
		}
		return firstDayOfWeek;
	}

	public static int getMenuChoice() 
	{
		System.out.println();
		System.out.println("Menu:");
		System.out.println("1. Make appointment on a specific date");
		System.out.println("2. Make appointment on next available weekday");
		System.out.println("3. Look up appointment on a specific date");
		System.out.println("4. Look up date of an appointment");
		System.out.println("5. Cancel appointment on a specific date");
		System.out.println("6. Quit");
		System.out.println();

		System.out.print("Choice (1-6)? ");
		int choice = IO.readInt();
		while (choice < 1 || choice > 6) 
		{
			System.out.print("That is not a valid menu option. Pick 1-6. ");
			choice = IO.readInt();
		}
		return choice;
	}
	
	public static boolean executeChoice(
		int choice,
		String[] calendar,
		int firstDayOfWeek)
	{
		if (choice == 1)
		{
			executeMakeAppointmentOnSpecificDate(calendar);
		}
		else if (choice == 2)
		{
			executeMakeAppointmentOnNextAvailableWeekday(calendar, firstDayOfWeek);
		}
		else if (choice == 3)
		{
			executeLookUpAppointmentOnSpecificDate(calendar);
		}
		else if (choice == 4)
		{
			executeLookUpDateOfAppointment(calendar);
		}
		else if (choice == 5)
		{
			executeCancelAppointmentOnSpecificDate(calendar);
		}
		else if (choice == 6)
		{
			return true;
		}
		else
		{
			System.out.println("Invalid menu choice passed to executeChoice().");
		}
		
		return false;
	}
	
	public static void executeMakeAppointmentOnSpecificDate(String[] calendar)
	{
		System.out.println("Enter date:");
		int date = IO.readInt();
		System.out.println("Enter appointment description:");
		String apptDescription = IO.readString();
		boolean result =
			MonthlyCalendarOps.makeAppointmentOnSpecificDate(
				calendar,
				date,
				apptDescription);
		IO.outputBooleanAnswer(result);
	}
	
	public static void executeMakeAppointmentOnNextAvailableWeekday(
		String[] calendar,
		int firstDayOfWeek)
	{
		System.out.println("Enter first possible date:");
		int date = IO.readInt();
		System.out.println("Enter appointment description:");
		String apptDescription = IO.readString();
		int result =
			MonthlyCalendarOps.makeAppointmentOnNextAvailableWeekday(
				calendar,
				firstDayOfWeek,
				date,
				apptDescription);
		IO.outputIntAnswer(result);
	}
	
	public static void executeLookUpAppointmentOnSpecificDate(String[] calendar)
	{
		System.out.println("Enter date:");
		int date = IO.readInt();
		String result =
			MonthlyCalendarOps.lookUpAppointmentOnSpecificDate(
				calendar,
				date);
		IO.outputStringAnswer(result);
	}
	
	public static void executeLookUpDateOfAppointment(String[] calendar)
	{
		System.out.println("Enter appointment description:");
		String apptDescription = IO.readString();
		int result =
			MonthlyCalendarOps.lookUpDateOfAppointment(
				calendar,
				apptDescription);
		IO.outputIntAnswer(result);
	}
	
	public static void executeCancelAppointmentOnSpecificDate(String[] calendar)
	{
		System.out.println("Enter date:");
		int date = IO.readInt();
		boolean result =
			MonthlyCalendarOps.cancelAppointmentOnSpecificDate(
				calendar,
				date);
		IO.outputBooleanAnswer(result);
	}
}
