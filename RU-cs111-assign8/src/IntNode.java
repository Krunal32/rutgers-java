public class IntNode
{
	public int data;
	public IntNode next;

	public IntNode()
	{
		this.data = 0;
		this.next = null;
	}

	public IntNode(int data)
	{
		this.data = data;
		this.next = null;
	}

	public IntNode(int data, IntNode next)
	{
		this.data = data;
		this.next = next;
	}
}