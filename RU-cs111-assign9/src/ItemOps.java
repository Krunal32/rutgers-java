public class ItemOps
{
	public static double computeTotalValue(ItemNode list)
	{
		if (list == null)
		{
			return 0;
		}
		
		return list.value+computeTotalValue(list.next); // replace this line with your code
	}

	public static ItemNode pickBest(ItemNode itemsInStore, int maximumWeight)
	{	
		if (itemsInStore == null)
		{
			return null;
		}
		else if (maximumWeight < itemsInStore.weight)
		{
			return pickBest(itemsInStore.next, maximumWeight); //
		}
		else
		{
			ItemNode with = new ItemNode();
			with.name = itemsInStore.name;
			with.weight = itemsInStore.weight;
			with.value = itemsInStore.value;
			with.next = pickBest(itemsInStore.next, maximumWeight-itemsInStore.weight);
			
			ItemNode without = pickBest(itemsInStore.next, maximumWeight);
			
			if (computeTotalValue(with) > computeTotalValue(without))
			{
				return with;
			}
			else
			{
				return without;
			}
		}
	}
}