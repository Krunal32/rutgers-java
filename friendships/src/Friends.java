import java.util.*;

public class Friends
{
   public static void main (String argv[])
   {
      Graph myFriends = null;
      Vector <Node> path;
      
      System.out.println("Please input the file to build a graph off of:");
      String filename = IO.readString();
      
      try
      {
         myFriends = new Graph (filename);
      }
      catch (java.io.FileNotFoundException e)
      {
         System.out.println ("An issue occurred when reading the file...");
      }
      
      System.out.println(myFriends);
      
      while (true)
      {
    	  System.out.println("\nMain menu:");
    	  System.out.println("Please enter the number of the function you'd like to perform");
    	  System.out.println("1. Subgraph (Students at a school)");
    	  System.out.println("2. Shortest path (Intro chain)");
    	  System.out.println("3. Connected Islands (cliques)");
    	  System.out.println("4. Connectors (friends who keep friends together)");
    	  System.out.println("5. Quit");
    	  
    	  int choice = IO.readInt();
    	  
    	  if (choice == 1)
    	  { 
    		  System.out.println("Please enter the school's name:");
    		  String school = IO.readString();
    		  
    		  Graph sub_graph = myFriends.SubGraph(school);
    		  System.out.println(sub_graph);
    	  }
    	  else if (choice == 2)
    	  {
    		  System.out.println("Please enter beginning path, person's name:");
    		  String friend1 = IO.readString();
    		  System.out.println("Please enter ending path, person's name:");
    		  String friend2 = IO.readString();
    		  
    		  path = myFriends.LeastPath(friend1, friend2);
    		  
    		  ArrayList<String> printable_path = new ArrayList<String>();
    	      if (path!=null) for (int i = 0; i < path.size(); i++)
    	      {
    	         printable_path.add(path.get(i).GetName());
    	      }
    		  
    		  System.out.println(printable_path);
    	  }
    	  else if (choice == 3)
    	  {
    		  System.out.println("Please enter the school's name to find cliques:");
    		  String school = IO.readString();
    		  
    		  Vector<Graph> islands = myFriends.islands(school);
    		  
    		  System.out.println(islands);
    	  }
    	  else if (choice == 4)
    	  {
    		  System.out.println(myFriends.connectors());
    	  }
    	  else if (choice == 5)
    	  {
    		  return;
    	  }
    	  else
    	  {
    		  IO.reportBadInput();
    	  }
      }
      

   }
}

