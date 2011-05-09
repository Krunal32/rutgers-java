public class DFSNode
{
	private Node element;
	public int dfs_num;
	public int back_num;
	public boolean starting_node;
	
	public DFSNode (Node element, int dfs_num, int back_num)
	{
		this.element = element;
		this.dfs_num = dfs_num;
		this.back_num = back_num;
		this.starting_node = false;;
	}
	
	public String getName()
	{
		return element.GetName();
	}
	
	public int getDFSNum()
	{
		return this.dfs_num;
	}
	
	public int getBackNum()
	{
		return this.back_num;
	}
	
	public String toString()
	{
		return "("+element.id + " " + element.GetName() + ") " + dfs_num + "/" + back_num;
	}
}