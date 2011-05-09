public class ComplexNumber
{
	private double real;
	private double imaginary;

	public ComplexNumber(double real, double imaginary)
	{
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getReal()
	{
		return this.real; // remove this line when you write your code	
	}

	public double getImaginary()
	{
		return this.imaginary; // remove this line when you write your code	
	}

	public String toString()
	{
		return this.real+" + "+this.imaginary+"i"; // remove this line when you write your code	
	}

	public ComplexNumber add(ComplexNumber other)
	{
		return new ComplexNumber(this.real+other.real, this.imaginary+other.imaginary);
	}

	public ComplexNumber subtract(ComplexNumber other)
	{
		return new ComplexNumber(this.real-other.real, this.imaginary-other.imaginary);
	}

	public ComplexNumber multiply(ComplexNumber other)
	{
		return new ComplexNumber((this.real*other.real)-(this.imaginary*other.imaginary), (this.imaginary*other.real)+(this.real*other.imaginary)); // remove this line when you write your code	
	}

	public double getComplexModulus()
	{
		return Math.sqrt(Math.pow(real, 2)+Math.pow(imaginary,2)); // remove this line when you write your code	
	}
}