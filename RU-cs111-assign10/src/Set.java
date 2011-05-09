public class Set
{
	// define fields here
	private Double[] numbers;

	public Set(int maxsize)
	{
		numbers = new Double[maxsize];
	}

	public boolean add(double value)
	{
		for (int i = 0; i < numbers.length; i++)
		{
			if (numbers[i] == null)
			{
				numbers[i] = value;
				return true;
			}
			else if (numbers[i] == value)
			{
				return false;
			}
		}
		
		return false; // replace this line with your code
	}

	public boolean remove(double value)
	{
		for (int i = 0; i < numbers.length; i++)
		{
			if (numbers[i] == value)
			{
				numbers[i] = null;
				for (int j = i; j < numbers.length-1; j++)
				{
					numbers[j] = numbers[j+1];
				}
				return true;
			}
		}
		return false; // replace this line with your code
	}

	public boolean contains(double value)
	{
		for (int i = 0; i < numbers.length; i++)
		{
			if (numbers[i] == null)
			{
				return false;
			}
			
			if (numbers[i] == value)
			{
				return true;
			}
		}
		
		return false;
	}

	public int size()
	{
		for (int i = 0; i < numbers.length; i++)
		{
			if (numbers[i] == null)
			{
				return i;
			}
		}
		
		return numbers.length;
	}

	public String toString()
	{
		String nums = "";
		for (int i = 0; i < numbers.length; i++)
		{
			if (numbers[i] == null)
			{
				break;
			}

			nums += numbers[i].toString()+", ";

		}
		return "{"+nums.substring(0, nums.length()-2)+"}"; // replace this line with your code
	}

	public Set union(Set other)
	{
		Set output = new Set(this.numbers.length+other.numbers.length);
		
		for (int i = 0; i < this.numbers.length; i++)
		{
			output.add(this.numbers[i]);
		}
		
		for (int i = 0; i < other.numbers.length; i++)
		{
			output.add(other.numbers[i]);
		}
		
		return output; // replace this line with your code
	}

	public Set intersection(Set other)
	{
		Set output = new Set(this.numbers.length+other.numbers.length);
		
		for (int i = 0; i < this.numbers.length; i++)
		{
			if (this.numbers[i] == null)
			{
				break;
			}
			
			if (other.contains(this.numbers[i]))
			{
				output.add(this.numbers[i]);
			}
		}
		
		return output; // replace this line with your code
	}

	public Set difference(Set other)
	{
		Set output = new Set(this.numbers.length+other.numbers.length);
		
		for (int i = 0; i < this.numbers.length; i++)
		{
			if (this.numbers[i] == null)
			{
				break;
			}
			
			if (!other.contains(this.numbers[i]))
			{
				output.add(this.numbers[i]);
			}
		}
		
		return output; // replace this line with your code
	}
}