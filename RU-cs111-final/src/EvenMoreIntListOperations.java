public class EvenMoreIntListOperations
{
	public static IntNode addAtIntervals(IntNode head, int interval, int value)
	{
		IntNode pointer = head;
		int count = 0;
		while (pointer != null)
		{
			count++;
			
			if (count % interval == 0)
			{
				IntNode insert = new IntNode();
				insert.data = value;
				insert.next = pointer.next;
				pointer.next = insert.next;
				pointer = insert.next;
			}
			else
			{
				pointer = pointer.next;
			}
		}
		
		return head; // replace this line with your code
	}

	public static IntNode removeAfterEvery(IntNode head, int oldvalue)
	{
		return null; // replace this line with your code
	}

	public static IntNode findAfter(IntNode head, int value)
	{
		return null; // replace this line with your code
	}
}