public class ild
{
	public static void main(String[] args)
	{
		System.out.print("Enter list (values separated by spaces): ");
		IntNode head = readList();

		while (true)
		{
			System.out.print("List is now: ");
			printList(head);
			System.out.println();
			System.out.println("Menu:");
			System.out.println("1. average()");
			System.out.println("2. addBetween()");
			System.out.println("3. removeIth()");
			System.out.println("4. removeAll()");
			System.out.println("5. unique()");
			System.out.println("6. Quit");
			System.out.println();

			System.out.print("Enter choice: ");
			int choice = IO.readInt();
			
			if (choice == 1)
			{
				double avg = IntListOps.average(head);
				System.out.println("Result: " + avg);
			}
			else if (choice == 2)
			{
				System.out.print("Enter oldvalue1: ");
				int oldv1 = IO.readInt();
				System.out.print("Enter oldvalue2: ");
				int oldv2 = IO.readInt();
				System.out.print("Enter newvalue: ");
				int newv = IO.readInt();
				head = IntListOps.addBetween(head, oldv1, oldv2, newv);
			}
			else if (choice == 3)
			{
				System.out.print("Enter value: ");
				int val = IO.readInt();
				System.out.print("Enter i: ");
				int i = IO.readInt();
				head = IntListOps.removeIth(head, val, i);
			}
			else if (choice == 4)
			{
				System.out.print("Enter value: ");
				int val = IO.readInt();
				head = IntListOps.removeAll(head, val);
			}
			else if (choice == 5)
			{
				System.out.print("Enter second list (values separated by spaces): ");
				IntNode head2 = readList();
				IntNode uniq = IntListOps.unique(head, head2);
				System.out.print("unique() returned: ");
				printList(uniq);
				System.out.print("Second list is now: ");
				printList(head2);
			}
			else if (choice == 6)
			{
				return;
			}
			else
			{
				System.out.println("That is not a valid choice.");
			}
		}
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
}