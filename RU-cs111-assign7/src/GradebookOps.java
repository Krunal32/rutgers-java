public class GradebookOps
{
	// add methods here as needed
	public static void set_all_point_value(Gradebook[] gradebook, int assign, int value)
	{
		for (int i = 0; i < gradebook.length; i++)
		{
			gradebook[i].set_point_value(assign-1, value); // logical input
		}
	}
	
	public static boolean add_student(Gradebook[] gradebook, String student_name)
	{
		for (int i = 0; i < gradebook.length; i++)
		{
			if (gradebook[i].get_student_name() == null)
			{
				gradebook[i].set_student_name(student_name);
				return true;
			}
		}
		
		return false;
	}
	
	public static void assign_score(Gradebook[] gradebook, String student_name, int assign, int grade)
	{
		for (int i = 0; i < gradebook.length; i++)
		{
			if (gradebook[i].get_student_name().equalsIgnoreCase(student_name))
			{
				gradebook[i].set_score(assign-1, grade); // logical input
			}
		}
	}
	
	public static double get_overall_grade(Gradebook[] gradebook, String student_name)
	{
		for (int i = 0; i < gradebook.length; i++)
		{
			if (gradebook[i].get_student_name().equalsIgnoreCase(student_name))
			{
				return gradebook[i].get_average()*100;
			}
		}
		
		return -1;
	}
	
	public static double get_class_average_on_assign(Gradebook[] gradebook, int assign)
	{
		int total_num_of_students = 0;
		int total_sum_of_grades = 0;
		for (int i = 0; i < gradebook.length; i++)
		{
			if (gradebook[i].get_student_name() != null)
			{
				total_sum_of_grades += gradebook[i].get_score(assign-1);
				total_num_of_students++;
			}
		}
		
		return (double) total_sum_of_grades/total_num_of_students;
	}
	
	public static void sort_alphebetically(Gradebook[] gradebook)
	{
		for (int i = 0; i < gradebook.length; i++)
		{
			for (int j = i+1; j < gradebook.length; j++)
			{
				if (gradebook[j].get_student_name().compareTo(gradebook[i].get_student_name()) < 1)
				{
					Gradebook temp = gradebook[i];
					gradebook[i] = gradebook[j];
					gradebook[j] = temp;
				}
			}
		}
	}
	
	public static void sort_by_grade(Gradebook[] gradebook)
	{
		for (int i = 0; i < gradebook.length; i++)
		{
			for (int j = i+1; j < gradebook.length; j++)
			{
				if (gradebook[j].get_average() > gradebook[i].get_average())
				{
					Gradebook temp = gradebook[i];
					gradebook[i] = gradebook[j];
					gradebook[j] = temp;
				}
			}
		}
	}
}