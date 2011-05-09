public class olrd
{
	public static void main(String[] args)
	{
		System.out.print("Enter list (values separated by spaces): ");
		IntNode head = readList();

		// complete your driver here
		// samples:
		String f = OneListRec.backwards(head);
		System.out.println(f);
		IntNode modifiedList = OneListRec.removeAll(head, 2);
		printList(modifiedList);
	}
	
	public static IntNode readList()
	{
		String text = IO.readString().trim();
		if (text.equals(""))
		{
			return null;
		}
		String[] parts = text.split(" +");
		IntNode head = null;
		for (int i = parts.length-1 ; i >= 0 ; i--)
		{
			IntNode newnode = new IntNode();
			newnode.data = Integer.parseInt(parts[i]);
			newnode.next = head;
			head = newnode;
		}
		return head;
	}

	public static void printList(IntNode head)
	{
		if (head == null)
		{
			System.out.println("empty");
		}
		else
		{
			System.out.print(head.data);
			for (IntNode ptr = head.next ; ptr != null ; ptr = ptr.next)
			{
				System.out.print(" -> " + ptr.data);
			}
			System.out.println();
		}
	}
}