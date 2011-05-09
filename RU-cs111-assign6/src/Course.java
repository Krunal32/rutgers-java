public class Course
{
	private int dept;
	private int courseNumber;
	private String name;
	private char day;
	private int period;
	private int credits;

	public int getDepartment()
	{
		return this.dept;
	}

	public int getCourseNumber()
	{
		return this.courseNumber;
	}

	public String getName()
	{
		return this.name;
	}

	public char getDay()
	{
		return this.day;
	}

	public int getPeriod()
	{
		return this.period;
	}
	
	public int getCredits()
	{
		return this.credits;
	}

	public void print()
	{
		String result =
			this.dept + ":" + this.courseNumber + " " + "[" + this.name + "] " +
			this.day + this.period +
			" credits:" + this.credits;

		if (this.dept < 100)
		{
			result = "0" + result;
		}

		if (this.dept < 10)
		{
			result = "0" + result;
		}

		System.out.println(result);
	}
	
	public boolean equals(Course other)
	{
		return this.dept == other.dept  &&  this.courseNumber == other.courseNumber;
	}

	public Course(String line)
	{
		String remaining, part;
		int index;

		remaining = line.trim();

		// read dept
		index = remaining.indexOf(':');
		if (index == -1)
		{
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		part = remaining.substring(0, index);
		try
		{
			this.dept = Integer.parseInt(part);
			if (this.dept < 0  ||  this.dept > 999)
			{
				throw new IllegalArgumentException("invalid department number on line: " + line);
			}
		}
		catch (NumberFormatException e)
		{
			throw new IllegalArgumentException("missing department number on line: " + line);
		}
		remaining = remaining.substring(index + 1);

		// read number
		index = remaining.indexOf(' ');
		if (index == -1)
		{
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		part = remaining.substring(0, index);
		try
		{
			this.courseNumber = Integer.parseInt(part);
			if (this.courseNumber < 0  ||  this.courseNumber > 999)
			{
				throw new IllegalArgumentException("invalid course number on line: " + line);
			}
		}
		catch (NumberFormatException e)
		{
			throw new IllegalArgumentException("missing course number on line: " + line);
		}
		remaining = remaining.substring(index + 1);

		// read name
		index = remaining.indexOf('[');
		if (index == -1)
		{
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		remaining = remaining.substring(index + 1);
		index = remaining.indexOf(']');
		if (index == -1)
		{
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		this.name = remaining.substring(0, index);
		remaining = remaining.substring(index + 1);
		
		// read day
		remaining = remaining.trim();
		if (remaining.length() < 2)
		{
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		this.day = Character.toUpperCase(remaining.charAt(0));
		if (day != 'M'  &&  day != 'T'  &&  day != 'W'  &&  day != 'H'  &&  day != 'F'  &&  day != 'S')
		{
			throw new IllegalArgumentException("invalid day on line: " + line);
		}
		try
		{
			this.period = Integer.parseInt(remaining.substring(1,2));
			if (this.period < 1  ||  this.period > 9)
			{
				throw new IllegalArgumentException("invalid class period on line: " + line);
			}
		}
		catch (NumberFormatException e)
		{
			throw new IllegalArgumentException("missing class period on line: " + line);
		}
		remaining = remaining.substring(2);
		
		// read credits
		remaining = remaining.trim();
		if ( ! remaining.startsWith("credits:") )
		{
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		remaining = remaining.substring("credits:".length());
		try
		{
			this.credits = Integer.parseInt(remaining);
			if (this.credits < 1)
			{
				throw new IllegalArgumentException("invalid number of credits on line: " + line);
			}
		}
		catch (NumberFormatException e)
		{
			throw new IllegalArgumentException("missing or invalid number of credits on line: " + line);
		}
	}
}