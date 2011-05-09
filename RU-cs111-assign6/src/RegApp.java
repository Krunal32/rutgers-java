import java.io.*;

public class RegApp
{
	public static void main(String[] args)
	{
		Course[] catalog;
		Course[] mySchedule;
		int myNumCourses;
		
		Course[] results;
		Course course;
		String catalogFilename, courseName;
		int myMaxCourses, dept, courseNum, numCredits;
		boolean success;
		
		catalogFilename = (args.length > 0) ? args[0] : "catalog.txt";
		catalog = readFile(catalogFilename);
		if (catalog == null)
		{
			System.err.println("Error reading " + catalogFilename + ":");
			return;
		}
		
		myMaxCourses = getMaxCourses();
		mySchedule = new Course[myMaxCourses];
		myNumCourses = 0;
		
		while (true)
		{
			int choice = getMenuChoice();
			
			if (choice == 1)
			{
				System.out.println("Enter name of course:");
				courseName = IO.readString();
				System.out.println();
		
				course = RegOps.lookupCourseByName(catalog, courseName);
				if (course != null)
				{
					course.print();
				}
				else
				{
					System.out.println("No course found by that name.");
				}
			}
			else if (choice == 2)
			{
				System.out.println("Enter number of department:");
				dept = IO.readInt();
				System.out.println();

				results = RegOps.lookupCoursesByDept(catalog, dept);
				if (results != null)
				{
					printFullArray(results);
				}
				else
				{
					System.out.println("No courses found in that department.");
				}
			}
			else if (choice == 3)
			{
				RegOps.sortByNumber(catalog);
				printFullArray(catalog);
			}
			else if (choice == 4)
			{
				RegOps.sortByTime(catalog);
				printFullArray(catalog);
			}
			else if (choice == 5)
			{
				System.out.println("Current schedule:");
				printPartialArray(mySchedule, myNumCourses);
			}
			else if (choice == 6)
			{
				numCredits = RegOps.countCredits(mySchedule, myNumCourses);
				System.out.println("Credits being taken: " + numCredits);
			}
			else if (choice == 7)
			{
				System.out.println("Enter number of department:");
				dept = IO.readInt();
				System.out.println("Enter number of course:");
				courseNum = IO.readInt();
				System.out.println();

				success = RegOps.addCourse(catalog, mySchedule, myNumCourses, dept, courseNum);
				if (success)
				{
					System.out.println("Successfully registered.");
					myNumCourses++;
				}
				else
				{
					System.out.println("Attempt to register failed.");
				}
			}
			else if (choice == 8)
			{
				System.out.println("Enter number of department:");
				dept = IO.readInt();
				System.out.println("Enter number of course:");
				courseNum = IO.readInt();
				System.out.println();

				success = RegOps.dropCourse(mySchedule, myNumCourses, dept, courseNum);
				if (success)
				{
					System.out.println("Successfully withdrawn.");
					myNumCourses--;
				}
				else
				{
					System.out.println("Attempt to withdraw failed.");
				}
			}
			else
			{
				return;
			}
		}
	}
	
	private static final String[] menuChoices =
	{
		"Look up course by name",
		"Look up courses by department",
		"View catalog, sorted by department/course number",
		"View catalog, sorted by scheduled day/period",
		"View my course schedule",
		"View my current credit load",
		"Register for a course",
		"Withdraw from a course"
	};

	private static int getMenuChoice()
	{
		int numChoices = menuChoices.length+1;
		
		System.out.println();
		System.out.println("Menu:");

		for (int i = 0 ; i < menuChoices.length ; i++)
		{
			System.out.println((i+1) + ". " + menuChoices[i]);
		}
		System.out.println(numChoices + ". Quit");

		System.out.println();
		System.out.println("Choice (1-" + numChoices + ")?");
		int choice = IO.readInt();
		while (choice < 1 || choice > numChoices) 
		{
			System.out.println();
			System.out.print("That is not a valid menu option. Pick 1-" + numChoices + ". ");
			choice = IO.readInt();
		}

		System.out.println();
		return choice;
	}
	
	private static Course[] readFile(String filename)
	{
		BufferedReader file;
		int numEntries;
		Course[] entries;

		try
		{
			file = new BufferedReader(new FileReader(filename));
			numEntries = parseFile(file, false, null);
			entries = new Course[numEntries];
			file.close();
			file = new BufferedReader(new FileReader(filename));
			parseFile(file, true, entries);
			file.close();
			return entries;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private static int parseFile(BufferedReader file, boolean readEntries, Course[] catalog)
		throws IOException
	{
		int numEntries;
		String line;

		numEntries = 0;
		while ((line = file.readLine()) != null)
		{
			line = line.trim();
			if (line.equals(""))
			{
				continue;
			}
			
			if (readEntries)
			{
				catalog[numEntries] = new Course(line);
			}
			numEntries++;
		}
		
		return numEntries;
	}
	
	private static int getMaxCourses()
	{
		while (true)
		{
			System.out.println("Enter maximum number of courses for which a student may register:");
			int maxCourses = IO.readInt();
			if (maxCourses < 1)
			{
				System.out.println("A student must be allowed to register for at least one course.");
			}
			else
			{
				return maxCourses;
			}
			
		}
	}
	
	private static void printFullArray(Course[] array)
	{
		printPartialArray(array, array.length);
	}
	
	private static void printPartialArray(Course[] array, int numEntries)
	{
		for (int i = 0 ; i < numEntries ; i++)
		{
			if (array[i] == null)
			{
				System.out.println("null entry in array");
			}
			else
			{
				array[i].print();
			}
		}
	}
}