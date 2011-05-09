import java.util.Vector;

// tree operations
// Steven Lu

public class htmltreeops extends htmltree
{

	public htmltreeops(String input) {
		super(input); // create the tree
		validate (); // make sure this tree is good to go
	}
	
	public boolean replaceTag(String search, String replace)
	{
		// we only allow specific replace operations to occur
		if ((search.equalsIgnoreCase("b") && replace.equalsIgnoreCase("em"))
				|| (search.equalsIgnoreCase("ol") && replace.equalsIgnoreCase("ul"))
				|| (search.equalsIgnoreCase("em") && replace.equalsIgnoreCase("b"))
				|| (search.equalsIgnoreCase("ul") && replace.equalsIgnoreCase("ol"))
			)
		{
			_replaceTag(search, replace, head);
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	private void _replaceTag(String search, String replace, mlnode head)
	{
		// we go into deepest recursion first
		for (int i = 0; i < head.children.size(); i++)
		{
			_replaceTag(search, replace, head.children.get(i));
		}
		
		// once we hit a leaf, we'll replace it properly
		if (head.data.equalsIgnoreCase(search))
		{
			head.data = replace;
		}
	}
	
	public boolean removeTag(String search)
	{
		_removeTag(search, head);
		
		// if the tag we removed was ol or ul, we need to replace all the li's that were under it with "p"
		if (search.equalsIgnoreCase("ol") || search.equalsIgnoreCase("ul"))
			replaceTag("li","p");
		
		return true;
	}
	
	private Vector<mlnode> _removeTag(String search, mlnode head)
	{
		// we will make a new set of children
		// and develop a new list of children
		// basically, if it does not contain the tag we want to remove
		// we will just add that child back into the new list
		// however, if it does match, we'll take that guy's children
		// and put it into our new list
		Vector <mlnode> children = new Vector<mlnode>();
		for (int i = 0; i < head.children.size(); i++)
		{
			children.addAll(_removeTag(search, head.children.get(i)));
		}
		head.children = children;
		
		if (head.data.equalsIgnoreCase(search))
		{
			return head.children;
		}
		else
		{
			Vector<mlnode> temp = new Vector<mlnode>(); // since we are returning a vector, we need to make it accordingly
			temp.add(head);
			return temp;
		}
	}
	
	public boolean makeRowBold(int row_num)
	{
		// first find the table
		mlnode table = _findTable(head);
		
		if (table == null)
			return false; // no table found
		
		// then find the proper row in that table
		mlnode row = _findRow(row_num, table);
		
		if (row == null)
			return false; // row not found
		
		// since we found the row, throw it to our actual bold function
		_makeRowBold(row);
		
		return true;
	}
	
	private mlnode _findRow(int row_num, mlnode table)
	{
		// increment the count when we find a "tr"
		// if we hit our row_num "tr", we'll return that node
		int row_count = 0;
		for (int i = 0; i < table.children.size(); i++)
		{
			if (table.children.get(i).data.equalsIgnoreCase("tr"))
				row_count++;
			
			if (row_count == row_num)
				return table.children.get(i);
		}
		
		return null; // if we don't get it properly
	}
	
	private mlnode _findTable(mlnode head)
	{
		if (head.data.equalsIgnoreCase("table"))
			return head;
		
		if (head.children.size() == 0)
			return null; // at the leaf that doesn't contain table
		
		
		for(int i = 0; i < head.children.size(); i++)
		{
			mlnode return_data = _findTable(head.children.get(i)); // go recursively in
			
			if (return_data != null) // if we find the table, we'll return, killing our forloop
			{
				return return_data;
			}
		}
		
		return null; // this is if we don't find anything
	}
	
	private mlnode _makeRowBold(mlnode row)
	{			
		// we assume that TEXT is a leaf node, containing no children
		// if we hit this certain case, we'll create a new node
		// with data "b", and then append that TEXT node as a child
		if (row.children.size() == 0 && row.isflag(row.TEXT))
		{
			mlnode new_node = new mlnode("b", row.TAG);
			new_node.children.add(row);
			return new_node;
		}
		
		// we will create a new vector
		// and append children accordingly
		// for example:
		// if its not a text tag, we will get the original tag
		// if it is, we'll get that bold tag
		Vector<mlnode> children = new Vector<mlnode>();
		for (int i = 0; i < row.children.size(); i++)
		{
			children.add(_makeRowBold(row.children.get(i)));
		}
		
		row.children = children;
		
		return row;
	}
}