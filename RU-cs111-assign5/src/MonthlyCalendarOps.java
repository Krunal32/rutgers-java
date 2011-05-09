public class MonthlyCalendarOps
{
	public static String[] createCalendar(int daysInMonth)
	{
		return new String[daysInMonth]; // replace this line with your code
	}
	
	public static boolean makeAppointmentOnSpecificDate(
		String[] calendar,
		int date,
		String apptDescription)
	{
		if (calendar[date-1] == null)
		{
			calendar[date-1] = apptDescription;
			return true;
		}
		
		return false; // replace this line with your code
	}

	public static int makeAppointmentOnNextAvailableWeekday(
		String[] calendar,
		int firstDayOfWeek,
		int firstDate,
		String apptDescription)
	{
		for (int i = firstDate-1; i < calendar.length; i++)
		{
			if (firstDayOfWeek == 0 || firstDayOfWeek == 6)
			{
				continue;
			}
			
			if (calendar[i] == null)
			{
				calendar[i] = apptDescription;
				return i+1;
			}
			
			firstDayOfWeek++;
			
			if (firstDayOfWeek == 7)
			{
				firstDayOfWeek = 0;
			}
		}

		return -1;
	}

	public static String lookUpAppointmentOnSpecificDate(
		String[] calendar,
		int date)
	{
		if (calendar[date-1] == null)
		{
			return "none";
		}
		
		return calendar[date-1];
	}

	public static int lookUpDateOfAppointment(
		String[] calendar,
		String apptDescription)
	{
		for (int i = 0; i < calendar.length; i++)
		{
			if (calendar[i] != null)
			{
				if (calendar[i].equalsIgnoreCase(apptDescription))
				{
					return i+1;
				}
			}
		}
		
		return -1; // replace this line with your code
	}

	public static boolean cancelAppointmentOnSpecificDate(
		String[] calendar,
		int date)
	{
		if (calendar[date-1] != null)
		{
			calendar[date-1] = null;
			return true;
		}

		return false;
	}
}
