import java.util.Vector;

/* 
 * quicksort using vectors (smaller compact version of LL)
 * Created by sjlu - steven lu on 5/8/2011
 */

public class quicksort
{
	public static void main(String[] args)
	{
		Vector<Integer> numbers = new Vector<Integer>();
		numbers.add(14);
		numbers.add(4);
		numbers.add(55);
		numbers.add(32);
		numbers.add(1);
		numbers.add(7);
		numbers.add(44);
		numbers.add(17);
		numbers.add(77);
		
		System.out.println(numbers);
		System.out.println(sort(numbers));
	   System.out.println(sort2(numbers));
   }
	
	public static Vector<Integer> sort(Vector<Integer> numbers)
	{
		quick(numbers, 0, numbers.size()-1); // passing to helper function
		return numbers;
	}
		
   public static Vector<Integer> sort2(Vector<Integer> numbers)
   {
      quick2(numbers, 0, numbers.size()-1);
      return numbers;
   }

	public static void quick(Vector<Integer> numbers, int low, int high)
	{
		// if the low is ever greater than high
		// then there's something wrong
		// this is simple error conditioning
		if (low >= high)
			return;
		
		// creating our pointers
		int i = low;
		int j = high;
		
		// selecting our pivot value
		int pivot = numbers.get((low+high)/2);
		
		// we wanna continue while
		// our low pointer is less than our high pointer
		// if low pointer is over high pointer
		// we've reached our quicksort limit
		while (i < j)
		{
			// we need to select a value
			// that is greater than the pivot
			// for the low pointer/left hand side
			while (i < j && numbers.get(i) < pivot)
				i++;
			
			// we also need to select a value
			// that is less than the pivot
			// for the high pointer/right hand side
			while (i < j && numbers.get(j) > pivot)
				j--;
			
			// if we broke out of the while loops
			// then we found values that are not
			// supposed to be on the sides of those pivots
			if (i < j)
				swap(numbers, i, j);
		}
		
		// we are performing quicksorts
		// on the left, right partitions
		quick(numbers, low, i);
		
		// we need the i+1 or i because
		// if we selected a pivot
		// that is a value which is the min value
		// then we'd be stuck infinitely
		// so instead, we want to take a look at other values
		// so we can take a different value;
		quick(numbers, i == low ? i+1 : i, high); 
	}

   public static void quick2(Vector<Integer> numbers, int low, int high)
   {
      // error conditioning
      // base case
      if (low >= high)
         return;
      
      // creating our L and R pointers
      // selecting our pivot value
      int pivot = numbers.get(low); // we'll just choose it as our low
      int L = low;
      int R = high;

      // as long as our L pointer is <= to our R pointer
      // then we're in good shape
      // if they cross, we'd be incorrectly swapping values
      while (L <= R)
      {
         // selecting L value that is greater
         // than the pivot
         while (L <= R && numbers.get(L) <= pivot)
            L++;

         // selecting R value that is less than pivot
         while (L <= R && numbers.get(R) >= pivot)
            R--;   
      
         // since the L and R are values that
         // aren't supposed to be on those sides
         // we have to swap them
         // and move our pointers
         if (L <= R)
         {
            swap(numbers, L, R);
            L++;
            R--;
         }
      }

      // once our L and R pointers cross
      // we need to place our pivot in the correct position
      // which is basically the "middle"
      swap(numbers, low, R);

      // then we sort the left and right hand sides accordingly
      quick2(numbers, low, R-1);
      quick2(numbers, R+1, high);
   }  
	
	public static void swap (Vector<Integer> numbers, int n1, int n2)
	{
		int temp = numbers.get(n1);
		numbers.set(n1, numbers.get(n2));
		numbers.set(n2, temp);
	}
}
