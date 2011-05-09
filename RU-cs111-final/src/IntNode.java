public class IntNode
{
	public int data;
	public IntNode next;

	public IntNode()
	{
		data = 0;
		next = null;
	}

	public IntNode(int data)
	{
		this.data = data;
		next = null;
	}

	public IntNode(int data, IntNode next)
	{
		this.data = data;
		this.next = next;
	}
}