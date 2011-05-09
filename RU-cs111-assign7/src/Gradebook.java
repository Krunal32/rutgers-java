public class Gradebook
{
	private String student_name;
	private int[] scores_value;
	private int[] scores_pt_value;
	
	public String get_student_name()
	{
		return this.student_name;
	}
	
	public void set_student_name(String student_name)
	{
		this.student_name = student_name;
	}
	
	public double get_score(int assign)
	{
		return this.scores_value[assign];
	}
	
	public void set_score(int assign, int grade)
	{
		this.scores_value[assign] = grade;
	}
	
	public double get_point_value(int assign)
	{
		return this.scores_pt_value[assign];
	}
	
	public void set_point_value(int assign, int pt_value)
	{
		this.scores_pt_value[assign] = pt_value;
	}
	
	public double get_grade(int assign)
	{
		return (this.scores_value[assign]/this.scores_pt_value[assign])*100;
	}
	
	public int get_max_scores()
	{
		return this.scores_value.length;
	}
	
	public double get_average()
	{
		double score_sum = 0;
		double pt_value_sum = 0;
		for (int i = 0; i < scores_value.length; i++)
		{
			score_sum += this.scores_value[i];
			pt_value_sum += this.scores_pt_value[i];
		}
		
		return score_sum/pt_value_sum;
	}
	
	public Gradebook(int size)
	{
		scores_value = new int[size];
		scores_pt_value = new int[size];
	}
}