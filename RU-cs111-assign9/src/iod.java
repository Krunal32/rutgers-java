public class iod
{
	public static void main(String[] args)
	{
		ItemNode choices =
			new ItemNode("tv", 15, 1000.00,
			new ItemNode("iphone", 2, 400.00,
			new ItemNode("laptop", 10, 700.00,
			new ItemNode("digital camera", 5, 300.00))));

		ItemNode best = ItemOps.pickBest(choices, 16);
		System.out.println("Picked items with total value: " + ItemOps.computeTotalValue(best));
		printList(best);
	}

	public static void printList(ItemNode head)
	{
		for (ItemNode ptr = head ; ptr != null ; ptr = ptr.next)
		{
			System.out.println(ptr.name + ": weight=" + ptr.weight + ", value=" + ptr.value);
		}
	}
}