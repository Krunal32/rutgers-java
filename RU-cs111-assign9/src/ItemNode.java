public class ItemNode
{
	public String name;
	public int weight;
	public double value;
	public ItemNode next;

	public ItemNode()
	{
	}

	public ItemNode(String name, int weight, double value)
	{
		this.name = name;
		this.weight = weight;
		this.value = value;
	}

	public ItemNode(String name, int weight, double value, ItemNode next)
	{
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.next = next;
	}
}