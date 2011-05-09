public class GradebookApp
{
	public static void main(String[] args)
	{
		int maxStudents = getMaximumNumberOfStudents();
		int numGradedItems = getNumberOfGradedItems();
		
		Gradebook[] gradebook = new Gradebook[maxStudents];
		for (int i = 0; i < gradebook.length; i++)
		{
			gradebook[i] = new Gradebook(numGradedItems);
		}
		
		while (true)
		{
			int choice = getMenuChoice();
			
			if (choice == 1) // Set point value for graded item
			{
				System.out.println("Which graded item?");
				int whichItem = IO.readInt();
				System.out.println("Enter point value for that item:");
				int points = IO.readInt();
				
				if (whichItem > numGradedItems || whichItem < 0)
				{
					IO.reportBadInput();
				}
				else
				{
					GradebookOps.set_all_point_value(gradebook, whichItem, points);	// should return nothing
				}
			}
			else if (choice == 2) // Add student
			{
				System.out.println("What is the student's name?");
				String studentName = IO.readString();
				
				// your code here
				if (!GradebookOps.add_student(gradebook, studentName))
				{
					System.out.println("Failed to add student.");
				}
			}
			else if (choice == 3) // Assign score
			{
				System.out.println("What is the student's name?");
				String studentName = IO.readString();
				System.out.println("Which graded item?");
				int whichItem = IO.readInt();
				System.out.println("Enter score:");
				int points = IO.readInt();
				
				if (whichItem > numGradedItems || whichItem < 0)
				{
					IO.reportBadInput();
				}
				else
				{
					GradebookOps.assign_score(gradebook, studentName, whichItem, points);
				}
				
			}
			else if (choice == 4) // Get student's overall grade
			{
				System.out.println("What is the student's name?");
				String studentName = IO.readString();
				
				// your code here
				IO.outputDoubleAnswer(GradebookOps.get_overall_grade(gradebook, studentName));
			}
			else if (choice == 5) // Get class average on a particular graded item
			{
				System.out.println("Which graded item?");
				int whichItem = IO.readInt();
				
				if (whichItem > numGradedItems || whichItem < 0)
				{
					IO.reportBadInput();
				}
				else
				{
					IO.outputDoubleAnswer(GradebookOps.get_class_average_on_assign(gradebook, whichItem));
				}
			}
			else if (choice == 6) // Sort students alphabetically
			{
				// your code 
				GradebookOps.sort_alphebetically(gradebook);
			}
			else if (choice == 7) // Sort students by overall grade (descending order)
			{
				// your code here
				GradebookOps.sort_by_grade(gradebook);
			}
			else if (choice == 8) // Display students and overall grades
			{
				// your code here
				for (int i = 0; i < gradebook.length; i++)
				{
					IO.outputStringAnswer(gradebook[i].get_student_name());
					IO.outputDoubleAnswer(gradebook[i].get_average()*100);
				}
			}
			else // choice == 9, Quit
			{
				return;
			}
		}
	}

	public static int getMaximumNumberOfStudents()
	{
		System.out.print("How many students can register for the course? ");
		int maxStudents = IO.readInt();
		while (maxStudents < 1)
		{
			System.out.println("You must allow at least one student to register for the course.");
			maxStudents = IO.readInt();
		}
		return maxStudents;
	}

	public static int getNumberOfGradedItems()
	{
		System.out.print("How many graded items will there be? ");
		int numGradedItems = IO.readInt();
		while (numGradedItems < 1)
		{
			System.out.println("There must be at least one graded item for the course.");
			numGradedItems = IO.readInt();
		}
		return numGradedItems;
	}

	public static int getMenuChoice() 
	{
		System.out.println();
		System.out.println("Menu:");
		System.out.println("1. Set point value for graded item");
		System.out.println("2. Add student");
		System.out.println("3. Assign score");
		System.out.println("4. Get student's overall grade");
		System.out.println("5. Get class average on a particular graded item");
		System.out.println("6. Sort students alphabetically");
		System.out.println("7. Sort students by overall grade (descending order)");
		System.out.println("8. Display students and overall grades");
		System.out.println("9. Quit");
		System.out.println();

		System.out.print("Choice (1-9)? ");
		int choice = IO.readInt();
		while (choice < 1 || choice > 9) 
		{
			System.out.print("That is not a valid menu option. Pick 1-9. ");
			choice = IO.readInt();
		}
		return choice;
	}
}


