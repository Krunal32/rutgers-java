public class OneListRec
{
	public static String backwards(IntNode head)
	{
		if (head.next == null)
		{
			return Integer.toString(head.data);
		}
			
		return OneListRec.backwards(head.next)+", "+Integer.toString(head.data);
	}

	public static IntNode addBefore(IntNode head, int oldvalue, int newvalue)
	{
		if (head == null)
		{
			return null;
		}
		
		if (head.data == oldvalue)
		{
			IntNode temp = new IntNode();
			temp.data = newvalue;
			temp.next = head;
			return temp;
		}
		
		head.next = OneListRec.addBefore(head.next, oldvalue, newvalue);
		return head;
	}

	public static IntNode removeAll(IntNode head, int value)
	{
		if (head == null)
		{
			return null; // end of list
		}
			
		head.next = OneListRec.removeAll(head.next, value);
		
		if (head.data == value)
		{
			return head.next;
		}
		
		return head;
	}
}