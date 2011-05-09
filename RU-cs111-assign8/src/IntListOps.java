public class IntListOps
{
	public static double average(IntNode head)
	{
		if (head == null)
		{
			return 0;
		}
		
		int sum = 0;
		int count = 0;
		while (head != null)
		{
			sum += head.data;
			count++;
			head = head.next;
		}
			
		return (double) sum/count; // replace this line with your code
	}	

	public static IntNode addBetween(
		IntNode head,
		int oldvalue1,
		int oldvalue2,
		int newvalue)
	{
		IntNode pointer = head;
		IntNode new_node = new IntNode();
		new_node.data = newvalue;
		
		while (pointer != null && pointer.next != null && pointer.data != oldvalue1 && pointer.next.data != oldvalue2)
		{
			pointer = pointer.next;
		}
		
		if (pointer != null && pointer.next != null && pointer.data == oldvalue1 && pointer.next.data == oldvalue2)
		{
			new_node.next = pointer.next;
			pointer.next = new_node;
		}
		
		return head; // replace this line with your code
	}

	public static IntNode removeIth(IntNode head, int value, int i)
	{
		int count = 0;
		IntNode pointer = head;
		
		if (pointer.data == value)
		{
			if (count == i)
			{
				if (head.next == null)
				{
					return null;
				}
				else
				{
					return head.next;
				}
			}
			
			count++;
		}
		
		while (pointer.next != null)
		{
			if (pointer.next.data == value)
			{
				if (count == i)
				{
					if (pointer.next.next == null)
					{
						pointer.next = null;
					}
					else
					{
						pointer.next = pointer.next.next;
					}
					
					break;
				}
				
				count++;
			}
			
			pointer = pointer.next;
		}
		
		return head; // replace this line with your code
	}	

	public static IntNode removeAll(IntNode head, int value)
	{
		IntNode pointer = head;
		if (head.data == value)
		{
			head = head.next;
		}
		
		while (pointer.next != null)
		{
			if (pointer.next.data == value)
			{
				if (pointer.next.next == null)
				{
					pointer.next = null;
				}
				else
				{
					pointer.next = pointer.next.next;
				}
			}
			else
			{
				pointer = pointer.next;
			}
		}
		
		return head; // replace this line with your code
	}

	public static IntNode unique(IntNode head1, IntNode head2)
	{
		IntNode head3 = new IntNode();
		IntNode pointer1 = head1;
		IntNode pointer2 = head2;
		int count = 0;
		
		while (pointer1 != null)
		{
			boolean broken = false;
			pointer2 = head2;
			while (pointer2 != null)
			{
				if (pointer1.data == pointer2.data)
				{
					broken = true;
					break;
				}
				
				pointer2 = pointer2.next;
			}
			
			if (broken == false)
			{
				if (count == 0)
				{
					head3.data = pointer1.data;
					count++;
				}
				else
				{
					IntNode new_node = new IntNode();
					new_node.data = pointer1.data;
					new_node.next = head3;
					head3 = new_node;
				}
			}
			
			pointer1 = pointer1.next;
		}
		
		IntNode temp = head1;
		head1 = head2;
		head2 = temp;
		
		pointer1 = head1;
		pointer2 = head2;
		
		while (pointer1 != null)
		{
			boolean broken = false;
			pointer2 = head2;
			while (pointer2 != null)
			{
				if (pointer1.data == pointer2.data)
				{
					broken = true;
					break;
				}
				
				pointer2 = pointer2.next;
			}
			
			if (broken == false)
			{
				if (count == 0)
				{
					head3.data = pointer1.data;
					count++;
				}
				else
				{
					IntNode new_node = new IntNode();
					new_node.data = pointer1.data;
					new_node.next = head3;
					head3 = new_node;
				}
			}
			
			pointer1 = pointer1.next;
		}
		
		return head3; // replace this line with your code
	}
}