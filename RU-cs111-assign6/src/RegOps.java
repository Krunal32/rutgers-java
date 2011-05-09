public class RegOps
{
	public static Course lookupCourseByName(Course[] catalog, String courseName)
	{
		for (int i = 0; i < catalog.length; i++)
		{
			if (catalog[i].getName().equalsIgnoreCase(courseName))
			{
				return catalog[i];
			}
		}
		
		return null; // replace this line with your code
	}
	
	public static Course[] lookupCoursesByDept(Course[] catalog, int dept)
	{
		int count = 0;
		for (int i = 0; i < catalog.length; i++)
		{
			if (catalog[i].getDepartment() == dept)
			{
				count++;
			}
		}
		
		Course[] catalog_dept = new Course[count];
		count = 0;
		for (int i = 0; i < catalog.length; i++)
		{
			if (catalog[i].getDepartment() == dept)
			{
				catalog_dept[count] = catalog[i];
				count++;
			}
		}
		
		return catalog_dept; // replace this line with your code
	}
	
	public static void sortByNumber(Course[] catalog)
	{
		for (int i = 0; i < catalog.length; i++)
		{
			for (int j = i+1; j < catalog.length; j++)
			{
				if (catalog[j].getDepartment() < catalog[i].getDepartment())
				{
					Course temp = catalog[i];
					catalog[i] = catalog[j];
					catalog[j] = temp;
				}
			}
		}
		
		for (int i = 0; i < catalog.length; i++)
		{
			for (int j = i+1; j < catalog.length; j++)
			{
				if (catalog[j].getCourseNumber() < catalog[i].getCourseNumber())
				{
					if (catalog[j].getDepartment() == catalog[i].getDepartment())
					{
						Course temp = catalog[i];
						catalog[i] = catalog[j];
						catalog[j] = temp;
					}
				}
			}
		}
		
		return;
	}
	
	public static void sortByTime(Course[] catalog)
	{
		for (int i = 0; i < catalog.length; i++)
		{
			for (int j = i+1; j < catalog.length; j++)
			{
				int j_val = 0;
				switch (catalog[j].getDay())
				{
					case 'M': j_val = 1; break;
					case 'T': j_val = 2; break;
					case 'W': j_val = 3; break;
					case 'H': j_val = 4; break;
					case 'F': j_val = 5; break;
					case 'S': j_val = 6; break;
				}
					
				int i_val = 0;
				switch (catalog[i].getDay())
				{
					case 'M': i_val = 1; break;
					case 'T': i_val = 2; break;
					case 'W': i_val = 3; break;
					case 'H': i_val = 4; break;
					case 'F': i_val = 5; break;
					case 'S': i_val = 6; break;
				}
				
				if (j_val < i_val)
				{
					Course temp = catalog[i];
					catalog[i] = catalog[j];
					catalog[j] = temp;
				}
			}
		}
		
		for (int i = 0; i < catalog.length; i++)
		{
			for (int j = i+1; j < catalog.length; j++)
			{
				if (catalog[j].getPeriod() < catalog[i].getPeriod())
				{
					if (catalog[i].getDay() == catalog[j].getDay())
					{
						Course temp = catalog[i];
						catalog[i] = catalog[j];
						catalog[j] = temp;
					}
				}
			}
		}
		
		return; // replace this line with your code
	}
	
	public static boolean addCourse(
		Course[] catalog,
		Course[] mySchedule,
		int myNumCourses,
		int dept,
		int courseNum)
	{
		// sees if we have availability in our schedule
		if (myNumCourses == mySchedule.length)
		{
			return false;
		}
		
		// checking if we already have the course in our schedule
		for (int i = 0; i < myNumCourses; i++)
		{
			if (mySchedule[i].getDepartment() == dept && mySchedule[i].getCourseNumber() == courseNum)
			{
				return false;
			}
		}
		
		// placing it in our schedule now
		for (int i = 0; i < catalog.length; i++)
		{
			if (catalog[i].getDepartment() == dept && catalog[i].getCourseNumber() == courseNum)
			{
				mySchedule[myNumCourses] = catalog[i];
				return true;
			}
		}
		
		return false; // replace this line with your code
	}
	
	public static boolean dropCourse(
		Course[] mySchedule,
		int myNumCourses,
		int dept,
		int courseNum)
	{
		if (courseNum <= 0)
		{
			return false;
		}
		
		for (int i = 0; i < myNumCourses; i++)
		{
			if (mySchedule[i].getDepartment() == dept && mySchedule[i].getCourseNumber() == courseNum)
			{
				for (int j = i; i < myNumCourses-1; j++)
				{
					mySchedule[j] = mySchedule[j+1];
				}
				return true;
			}
		}

		return false;
	}

	public static int countCredits(Course[] mySchedule, int myNumCourses)
	{
		int count = 0;
		
		for (int i = 0; i < myNumCourses; i++)
		{
			count = count+mySchedule[i].getCredits();
		}
		
		return count; // replace this line with your code
	}
}