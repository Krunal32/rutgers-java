// Node class
// represents a single node of the graph
// Russell Frank

import java.util.*;

public class Node
{
   private String myName;
   private String mySchool;
   public Vector <Node> myEdges;
   public int id;   

   public int t; // temporary public int for algorithmic manipulation

   public Node (Node copy)
   {
      myName = copy.myName;
      mySchool = copy.mySchool;
      myEdges = new Vector (copy.myEdges);
   }

   public Node (String name)
   {
      myName = name;
      mySchool = null;
      myEdges = new Vector <Node> ();
   }

   public Node (String name, String school)
   {
      myName = name;
      mySchool = school;
      myEdges = new Vector <Node> ();
   }

   public String GetName ()
   {
      return myName;
   }

   public String GetSchool ()
   {
      return mySchool;
   }

   public String toString ()
   {
      String ret = "Student " + id + " (" + myName + ", " + mySchool + ")";
      for (int i = 0 ; i < myEdges.size(); i++)
         ret += " > " + myEdges.get(i).GetName();
      return ret;
   }
   
   public boolean equals (Node comp)
   {
	   if (comp.id == this.id && comp.GetName().equals(this.GetName()))
	   {
		   return true;
	   }
	   
	   return false;
   }

   public void Add (Node to)
   {
      myEdges.add (to);
   }
}

