package structures;

import java.util.*;

/**
 * Encapsulates an interval tree.
 * 
 * @author runb-cs112
 * @author Steven Lu sjlu at nbcs dot rutgers do edu
 */
public class IntervalTree {
	
	/**
	 * The root of the interval tree
	 */
	IntervalTreeNode root;
	
	/**
	 * Constructs entire interval tree from set of input intervals. Constructing the tree
	 * means building the interval tree structure and mapping the intervals to the nodes.
	 * 
	 * @param intervals Array list of intervals for which the tree is constructed
	 */
	public IntervalTree(ArrayList<Interval> intervals) {
		
		// make a copy of intervals to use for right sorting
		ArrayList<Interval> intervalsRight = new ArrayList<Interval>(intervals.size());
		for (Interval iv : intervals) {
			intervalsRight.add(iv);
		}
		
		// rename input intervals for left sorting
		ArrayList<Interval> intervalsLeft = intervals;
		
		// sort intervals on left and right end points
		Sorter.sortIntervals(intervalsLeft, 'l');
		Sorter.sortIntervals(intervalsRight,'r');
		
		// get sorted list of end points without duplicates
		ArrayList<Integer> sortedEndPoints = Sorter.getSortedEndPoints(intervalsLeft, intervalsRight);
		
		// build the tree nodes
		root = buildTreeNodes(sortedEndPoints);
		
		// map intervals to the tree nodes
		mapIntervalsToTree(intervalsLeft, intervalsRight);
	}
	
	/**
	 * Builds the interval tree structure given a sorted array list of end points.
	 * 
	 * @param endPoints Sorted array list of end points
	 * @return Root of the tree structure
	 */
	
	public static IntervalTreeNode buildTreeNodes(ArrayList<Integer> endPoints) {
		Queue<IntervalTreeNode> nodeQueue = new Queue<IntervalTreeNode>();
		
      // we simualte everything as a tree at first, giving it its splitValue, min and max splitValue
      for (int i = 0; i < endPoints.size(); i++)
		{
			IntervalTreeNode temp = new IntervalTreeNode(endPoints.get(i), endPoints.get(i), endPoints.get(i));
			nodeQueue.enqueue(temp);
		}
		
		while (true)
		{	
         // if we reach to only 1 item in the queue, then that's our tree
			if (nodeQueue.size() == 1)
			{
				break;
			}
		
         // we take the first two trees out of the list where temp1 < temp2
         // split value is (temp1+temp2)/2
         // since temp1 < temp2, our new tree's min vlaue is temp1's min splitvalue and temp2's maxValue
         // in "tree" terms, that is the item all the way to the right of the tree and the left of the tree
         // once we've done the calculations, we attach the two trees we popped off and attach it as our childs to our new root
         // when we'r edone, we put it back into the queue
			int temps = nodeQueue.size();
			while(temps > 1)
			{
				IntervalTreeNode temp1 = nodeQueue.dequeue();
				IntervalTreeNode temp2 = nodeQueue.dequeue();
				
				float splitValue = (((float) temp1.maxSplitValue+(float) temp2.minSplitValue)/2);
				
				IntervalTreeNode newNode = new IntervalTreeNode(splitValue, temp1.minSplitValue, temp2.maxSplitValue);
				newNode.leftChild = temp1;
				newNode.rightChild = temp2;
				
				nodeQueue.enqueue(newNode);
				
				temps = temps-2;
			}
			
			if (temps == 1) // we want to make sure we keep everything sorted from min to max
				nodeQueue.enqueue(nodeQueue.dequeue());
		}
		
		return nodeQueue.dequeue();
	}
	
	/**
	 * Maps a set of intervals to the nodes of this interval tree. 
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 */
	private IntervalTreeNode find(float minValue, float maxValue)
	{
      // begins our nice find function
		return this.find(minValue, maxValue, root);
	}
	
	private IntervalTreeNode find(float minValue, float maxValue, IntervalTreeNode head)
	{
		float splitValue = (minValue+maxValue)/2;
		
      // we see if the splitValue is within the interval
      // if it is, we'll return the first (maximum) node that contains a splitValue in the interval
      // if it isn't, we'll compare splitValues from the node and see if it leans to the left or right
      // if greater, go right, if less, go left (basically traversing a tree).
      // this is all done in recursion.
		if (minValue <= head.splitValue && maxValue >= head.splitValue)
		{
			return head;
		}
		
		if (splitValue > head.splitValue)
		{
			if (head.rightChild == null)
				return null;
			else
				return this.find(minValue, maxValue, head.rightChild);
		}
		else
		{
			if (head.leftChild == null)
				return null;
			else
				return this.find(minValue, maxValue, head.leftChild);
		}
	}
	
	private ArrayList<Interval> addToIntervals(ArrayList<Interval> node, Interval interval)
	{
      // since the arraylist inside the node is not defined yet
      // we need to make sure it is defined if null
		if (node == null)
		{
			node = new ArrayList<Interval>();
		}
		
      // then we properly add it to the ArrayList
		node.add(interval);
		
		return node;
	}
	
	public void mapIntervalsToTree(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		// we take all left sorted intervals and add it to the tree
      for (int i = 0; i < leftSortedIntervals.size(); i++)
		{
			if (leftSortedIntervals.get(i) == null)
				break;
			
			IntervalTreeNode nodeToAddAt = this.find(leftSortedIntervals.get(i).leftEndPoint, leftSortedIntervals.get(i).rightEndPoint);
			nodeToAddAt.leftIntervals = this.addToIntervals(nodeToAddAt.leftIntervals, leftSortedIntervals.get(i));
		}
		
      // we do the same thing for right sorted intervals
		for (int i = 0; i < rightSortedIntervals.size(); i++)
		{
			if (rightSortedIntervals.get(i) == null)
				break;
			
			IntervalTreeNode nodeToAddAt = this.find(rightSortedIntervals.get(i).leftEndPoint, rightSortedIntervals.get(i).rightEndPoint);
			nodeToAddAt.rightIntervals = this.addToIntervals(nodeToAddAt.rightIntervals, rightSortedIntervals.get(i));
		}

      // if all is done right, right and left intervals should be the same at every node
	}
	
	/**
	 * Gets all intervals in this interval tree that intersect with a given interval.
	 * 
	 * @param q The query interval for which intersections are to be found
	 * @return Array list of all intersecting intervals; size is 0 if there are no intersections
	 */
	private ArrayList<Interval> findIntersectingIntervals(float min, float max, ArrayList<Interval> q)
	{
		ArrayList<Interval> resultList = new ArrayList<Interval>();
		
		if (q == null)
			return resultList;
		
		for (int i = 0; i < q.size(); i++)
		{			
			if (q.get(i) == null)
				break;
			
			Interval checkInterval = q.get(i);
			
			// we need to check for 3 cases of intersection
			// if q.min is less than min and q.max is greater than min
			// if q.min is less than max and q.max is greater than max
			// if q.min is greater than min and q.max is less than max
			// this will guarantee detection of all intersections
			
			if ((checkInterval.leftEndPoint <= min && checkInterval.rightEndPoint >= min)
					|| (checkInterval.leftEndPoint <= max && checkInterval.rightEndPoint >= max)
					|| (checkInterval.leftEndPoint >= min && checkInterval.rightEndPoint <= max))
			{
				resultList.add(checkInterval);
			}
		}
		
		return resultList;
	}
	
	private ArrayList<Interval> findIntersectingIntervals(Interval q, IntervalTreeNode head)
	{
      // starting our array list of results
		ArrayList<Interval> resultList = new ArrayList<Interval>();
		float min = q.leftEndPoint;
		float max = q.rightEndPoint;
		
      // if it is a leaf, we're at the end of traversing our tree
		if (head.leftChild == null && head.rightChild == null)
		{
			return resultList;
		}
		
      // if our splitValue is contained in the interval, then we add all the itnervals that the node contains
      // and then traverse both sides of the tree
		if (min <= head.splitValue && max >= head.splitValue)
		{
			if (head.leftIntervals != null)
				resultList.addAll(head.leftIntervals);
			
			if (head.leftChild != null)
				resultList.addAll(findIntersectingIntervals(q, head.leftChild));
			
			if (head.rightChild != null)
				resultList.addAll(findIntersectingIntervals(q, head.rightChild));
		}
      // if we're greater than interval (leaning to the right)
      // then we have to find our which intervals in that root intersect with our inputted interval
      // then since we know its too big then we look at the left childs
		else if (max < head.splitValue) // falls to the right
		{
			resultList.addAll(findIntersectingIntervals(min, max, head.leftIntervals));
			
			if (head.leftChild != null)
				resultList.addAll(findIntersectingIntervals(q, head.leftChild));
		}
      // if we're less than the interval (leaning to the left)
      // we do the same thing
		else if (min > head.splitValue) // falls to the left
		{
			resultList.addAll(findIntersectingIntervals(min, max, head.rightIntervals));
			
			if (head.rightChild != null)
				resultList.addAll(findIntersectingIntervals(q, head.rightChild));

		}
			
		return resultList;
	}
	
	public ArrayList<Interval> findIntersectingIntervals(Interval q) {
		ArrayList<Interval> resultList = new ArrayList<Interval>();
		
		if (q == null)
		{
			return resultList;
		}
		
		resultList.addAll(this.findIntersectingIntervals(q, root));
		
		return resultList;
	}
	
	/**
	 * Returns the root of this interval tree.
	 * 
	 * @return Root of interval tree.
	 */
	public IntervalTreeNode getRoot() {
		return root;
	}
}

