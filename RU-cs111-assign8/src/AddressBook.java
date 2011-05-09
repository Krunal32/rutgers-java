import java.io.*;

public class AddressBook
{
	public static void main(String[] args)
	{
		AbookEntry abook;

		if (args.length > 0)
		{
			abook = readFromFile(args[0]);
		}
		else
		{
			abook = null;
		}

		while (true)
		{
			printMenu();

			System.out.print("\nEnter choice: ");
			int choice = IO.readInt();
			abook = executeChoice(abook, choice);
		}
	}

	private static AbookEntry readFromFile(String filename)
	{
		try
		{
			BufferedReader infile;
			int numlines;
			String[] nicknames, fullnames, emailaddrs;

			infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			numlines = 0;
			while (infile.readLine() != null)
			{
				numlines++;
			}
			infile.close();

			infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			nicknames = new String[numlines];
			fullnames = new String[numlines];
			emailaddrs = new String[numlines];
			for (int i = 0 ; i < numlines ; i++)
			{
				String line = infile.readLine();
				String[] parts = line.split(",");
				nicknames[i] = parts[0];
				fullnames[i] = parts[1];
				emailaddrs[i] = parts[2];
			}
			infile.close();

			return AbookOps.makeAddressBook(nicknames, fullnames, emailaddrs);			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("\nCould not find file \"" + filename + "\"");
			return null;
		}
		catch (IOException e)
		{
			System.out.println("\nError while reading from file \"" + filename + "\"");
			return null;
		}
	}

	private static void printMenu()
	{
		System.out.println("\nMenu:");
		System.out.println("1) Display address book");
		System.out.println("2) Display e-mail list");
		System.out.println("3) Look up a person's e-mail address");
		System.out.println("4) Add new entry");
		System.out.println("5) Delete entry");
		System.out.println("6) Quit");
	}

	private static AbookEntry executeChoice(AbookEntry abook, int choice)
	{
		String s1, s2, s3;
		boolean b;

		switch (choice)
		{

			case 1:
				display(abook);
				break;

			case 2:
				System.out.println("\nE-mail list:");
				System.out.println(AbookOps.getEmailList(abook));
				break;

			case 3:
				System.out.print("\nEnter nickname: ");
				s1 = IO.readString();
				s2 = AbookOps.lookupAddress(abook, s1);
				System.out.println("Result: " + s2);
				break;

			case 4:
				System.out.print("\nEnter nickname: ");
				s1 = IO.readString();
				System.out.print("Enter full name: ");
				s2 = IO.readString();
				System.out.print("Enter e-mail address: ");
				s3 = IO.readString();
				abook = AbookOps.addEntry(abook, s1, s2, s3);
				break;

			case 5:
				System.out.print("\nEnter nickname: ");
				s1 = IO.readString();
				abook = AbookOps.deleteEntry(abook, s1);
				break;

			case 6:
				System.exit(0);
				break;
		}

		return abook;
	}

	private static void display(AbookEntry abook)
	{
		int i;

		if (abook == null)
		{
			System.out.println("\nAddress book is empty.");
			return;
		}

		System.out.println("\nFormat: nickname - fullname <address>");
		i = 1;
		for (AbookEntry e = abook ; e != null ; e = e.next, i++)
		{
			System.out.print("Entry " + i + ": ");
			System.out.print(e.nickname + " - " + e.fullname);
			System.out.println(" <" + e.emailaddr + ">");
		}
	}
}