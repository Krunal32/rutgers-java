import java.io.*;

// DOM Menu
// Steven Lu

public class DOM
{
   public static void main (String[] argv)
   {
	  System.out.println("Please enter the filename you'd like to parse:");
      String filename = IO.readString();
      
      htmltreeops tree = new htmltreeops(readfile(filename));
      
      System.out.println("Passed validation...");
	   
      while(true)
      {
    	  System.out.println("Please select an operation:");
    	  System.out.println("1. Print tree");
    	  System.out.println("2. Search and replace a certain tag");
    	  System.out.println("3. Search and remove all occurances of a tag");
    	  System.out.println("4. Boldface a row in a table");
    	  System.out.println("0. Quit");
    	  int choice = IO.readInt();
    	  
    	  if (choice == 1)
    	  {
    		  System.out.println(tree.to_ml());
    	  }
    	  else if (choice == 2)
    	  {
    		  System.out.println("What tag do you want to replace?");
    		  String search = IO.readString();
    		  System.out.println("What do you want to replace it with?");
    		  String replace = IO.readString();
    		  
    		  if (tree.replaceTag(search, replace))
    			  System.out.println("Replace successfully executed...");
    		  else
    			  System.out.println("Tag search/replace logical mismatch...");
    	  }
    	  else if (choice == 3)
    	  {
    		  System.out.println("What tag do you want to remove?");
    		  String search = IO.readString();
    		  
    		  if (tree.removeTag(search))
    			  System.out.println("Tag successfully removed!");
    	  }
    	  else if (choice == 4)
    	  {
    		  System.out.println("Which row do you want to make bold?");
    		  int row_num = IO.readInt();
    		  
    		  if (tree.makeRowBold(row_num))
    			  System.out.println("Successfully made row text bold.");
    		  else
    			  System.out.println("There was an issue trying to find that row...");
    	  }
    	  else if (choice == 0)
    	  {
    		  return;
    	  }
    	  else
    	  {
    		  System.out.println("Not a valid operation...");
    	  }
      }
   }

   // read in a file
   public static String readfile (String filename)
   {
      try
      {
         byte[] buffer = new byte[(int) new File(filename).length()];
         FileInputStream f = new FileInputStream(filename);
         f.read(buffer);
         String file = new String(buffer);
         return file;
      }
      catch (Exception e)
      {
         return null;
      }
   }
}

