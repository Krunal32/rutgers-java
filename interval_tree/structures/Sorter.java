package structures;

import java.util.ArrayList;

/**
 * This class is a repository of sorting methods used by the interval tree.
 * It's a utility class - all methods are static, and the class cannot be instantiated
 * i.e. no objects can be created for this class.
 * 
 * @author runb-cs112
 */
public class Sorter {

	private Sorter() { }
	
	/**
	 * Sorts a set of intervals in place, according to left or right endpoints.  
	 * At the end of the method, the parameter array list is a sorted list. 
	 * 
	 * @param intervals Array list of intervals to be sorted.
	 * @param lr If 'l', then sort is on left endpoints; if 'r', sort is on right endpoints
	 */
	public static void sortIntervals(ArrayList<Interval> intervals, char lr) {
		for (int i = 0; i < intervals.size(); i++)
		{
			if (intervals.get(i) == null)
				break;
			
			for (int j = i+1; j < intervals.size(); j++)
			{
				if (intervals.get(j) == null)
					break;
				
				int iComp = 0, jComp = 0;
				
				if (lr == 'l')
				{
					iComp = intervals.get(i).leftEndPoint;
					jComp = intervals.get(j).leftEndPoint;
				}
				else if (lr == 'r')
				{
					iComp = intervals.get(i).rightEndPoint;
					jComp = intervals.get(j).rightEndPoint;
				}	
				else
				{
					break;
				}
					
				if (iComp > jComp)
				{
					Interval temp = intervals.get(i);
					Interval temp2 = intervals.get(j);
					intervals.remove(i);
					intervals.add(i, temp2);
					intervals.remove(j);
					intervals.add(j, temp);
				}
			}
		}
		
		System.out.println(intervals);
	}
	
	/**
	 * Given a set of intervals (left sorted and right sorted), extracts the left and right end points,
	 * and returns a sorted list of the combined end points without duplicates.
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 * @return Sorted array list of all endpoints without duplicates
	 */
	public static ArrayList<Integer> getSortedEndPoints(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		ArrayList<Integer> uniqueList = new ArrayList<Integer>();
		for (int i = 0; i < leftSortedIntervals.size(); i++)
		{
			if (leftSortedIntervals.get(i) == null)
				break;
			
			boolean unique = true;
			for (int j = 0; j < uniqueList.size(); j++) // faster operations
			{
				if (uniqueList.get(j) == null)
					break;
				
				if (leftSortedIntervals.get(i).leftEndPoint == uniqueList.get(j))
				{
					unique = false;
					break;
				}
			}
			
			if (unique == true)
				uniqueList.add(leftSortedIntervals.get(i).leftEndPoint);
		}
		
		System.out.println(uniqueList);
		
		// now we do a sorted insert
		for (int i = 0; i < rightSortedIntervals.size(); i++)
		{
			if (rightSortedIntervals.get(i) == null)
				break;
		
			for (int j = 0; j < uniqueList.size(); j++)
			{
				if (uniqueList.get(j) == null 
						|| rightSortedIntervals.get(i).rightEndPoint == uniqueList.get(j))
					break;
				
				if (rightSortedIntervals.get(i).rightEndPoint < uniqueList.get(j))
				{
					//insert
					uniqueList.add(j, rightSortedIntervals.get(i).rightEndPoint);
					break;
				}
				
				if (j == uniqueList.size()-1)
					uniqueList.add(rightSortedIntervals.get(i).rightEndPoint);
			}
		}
		
		System.out.println(uniqueList);
		return uniqueList;
	}
}
