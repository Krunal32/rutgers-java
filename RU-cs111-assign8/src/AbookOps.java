public class AbookOps
{
	public static AbookEntry makeAddressBook(
		String[] nicknames,
		String[] fullnames,
		String[] emailaddrs)
	{
		AbookEntry head = new AbookEntry();
		AbookEntry pointer = head;
		
		for (int i = 0; i < nicknames.length; i++)
		{
			if (i == 0)
			{
				head.nickname = nicknames[0].trim();
				head.fullname = fullnames[0].trim();
				head.emailaddr = emailaddrs[0].trim();
			}
			else
			{
				AbookEntry new_node = new AbookEntry();
				new_node.nickname = nicknames[i].trim();
				new_node.fullname = fullnames[i].trim();
				new_node.emailaddr = emailaddrs[i].trim();
				pointer.next = new_node;
				pointer = pointer.next;
			}
		}
		
		return head; // replace this line with your code
	}

	public static String getEmailList(AbookEntry head)
	{
		String email_list = null;
		while (head != null)
		{
			email_list = email_list+head.emailaddr;
			
			if (head.next != null)
			{
				email_list = email_list+", ";
			}
			
			head = head.next;
		}
		
		return email_list; // replace this line with your code
	}

	public static String lookupAddress(AbookEntry head, String nickname)
	{
		if (nickname == null || nickname.equals(""))
			return "";
		
		while (head != null)
		{
			if (head.nickname.equalsIgnoreCase(nickname))
			{
				return head.emailaddr;
			}
			
			head = head.next;
		}
		
		return ""; // replace this line with your code
	}

	public static AbookEntry addEntry(
		AbookEntry head,
		String nickname,
		String fullname,
		String emailaddr)
	{
		if (nickname == null || fullname == null || emailaddr == null)
		{
			return head;
		}
		
		AbookEntry pointer = head;
		
		AbookEntry new_node = new AbookEntry();
		new_node.fullname = fullname;
		new_node.nickname = nickname;
		new_node.emailaddr = emailaddr;
		
		if (pointer.nickname.compareToIgnoreCase(nickname) > 1)
		{
			new_node.next = head;
			head = new_node;
			return head;
		}
		
		while(pointer.next != null && pointer.next.nickname.compareToIgnoreCase(nickname) < 1)
		{
			pointer = pointer.next;
		}
		
		new_node.next = pointer.next;
		pointer.next = new_node;

		return head; // replace this line with your code
	}

	public static AbookEntry deleteEntry(
		AbookEntry head,
		String nickname)
	{
		if (nickname == null)
		{
			return head;
		}
		
		if (head == null)
		{
			return null;
		}
		
		if (head.nickname.equalsIgnoreCase(nickname))
		{
			return head.next;
		}
		
		head.next = AbookOps.deleteEntry(head.next, nickname);
		
		return head; // replace this line with your code
	}
}