// extension of an mltree which will validate according to naive html rules
// Russell Frank

public class htmltree extends mltree
{
   public htmltree (String input)
   {
      super (input);    // parse the tree
      validate ();      // make sure it is valid html
   }

   // returns true if parent can contain child and false otherwise
   private boolean rule (mlnode parent, mlnode child)
   {
      if (parent.equals("html") && !child.equals("body")) return false;
      else if ( ( parent.equals("body") ||
                  parent.equals("li")   ||
                  parent.equals("td")   ||
                  parent.equals("p")    ||
                  parent.equals("b")    ||
                  parent.equals("em")
                )  &&  
                ( child.equals("li")    ||
                  child.equals("tr")    ||
                  child.equals("td")    || 
                  child.equals("html")
                ) ) return false;
      else if ( ( parent.equals("ul")   ||
                  parent.equals("ol")
                )  &&
               !( child.equals("li")    ||
                  child.equals("ul")    ||
                  child.equals("ol")
                ) ) return false;
      else if (parent.equals("table") && !child.equals("tr")) return false;
      else if (parent.equals("tr") && !child.equals("td")) return false;
      return true;
   }

   public void validate ()
   {
      if (!head.equals("html"))  // check start tag first
         throw new IllegalArgumentException ("invalid start tag: " + head.data);
      _validate (head);  // call recursive function
   }

   private void _validate (mlnode h)
   throws IllegalArgumentException
   {
      for (int i = 0; i < h.size(); i++)
         if (h.get(i).isflag(mlnode.TAG))
         {
            if (!rule (h, h.get(i)))      // check to make sure this tag can
               throw new IllegalArgumentException (  // contain its child
                  h.data + " cannot contain " + h.get(i).data);
            _validate (h.get(i)); // recurse
         }
   }
}

