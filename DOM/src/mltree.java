import java.util.*;

// public class mltree
// parses any ml into a tree
// Russell Frank

public class mltree
{
   protected mlnode head;
   public static final String delims   =  "<>/";
   private StringTokenizer st;

   public mltree (String input)
   {
      st = new StringTokenizer (input, delims, true);
      head = parse(null);  // parse dat tree
   }

   // finds the next node in the input string
   private mlnode next ()
   {
      int flags = 0;
      mlnode ret;
      while (st.hasMoreTokens())
      {
         String tok = st.nextToken().trim();   // grab next token
         if (tok.equals("")) continue;                     // set flags so we
         else if (tok.equals("<")) flags |= mlnode.TAG;    // know what to do
         else if (tok.equals("/")) flags |= mlnode.CLOSE;  // in next iteration
         else
         {
            if (flags == 0) // token is just text
               ret = new mlnode (tok, mlnode.TEXT);  // return a text node
            else
            {
               ret = new mlnode (tok, flags);  // make a tag node
               st.nextToken();
            }
            return ret;
         }
      }
      return null;
   }

   // parses any ml tree recursively
   // throws an IllegalArgumentException when a tag is improperly matched
   // builds a tree of the dom
   private mlnode parse (mlnode head)
   throws IllegalArgumentException
   {
      if (head == null)  // create temporary empty root node
         head = new mlnode ("", mlnode.TAG | mlnode.CLOSE);
      mlnode t;
      while ((t = next()) != null)
      {
         if (t.isflag (mlnode.CLOSE)) // check to make sure tag is matched
            if (t.data.equals(head.data)) return head;
            else
               throw new IllegalArgumentException ("unmatched " + head.data);
         else if (t.isflag(mlnode.TAG))
            head.add(parse(t));  // recurse here
         else // node is just text
            head.add (t);
      }
      return head.children.get(0); // return head node out of temporary
   }                               // empty root node

   public String to_ml ()
   {
      return _to_ml ("", head);
   }
  
   // recursively converts a tree back to ml
   // also pretty prints it
   private String _to_ml (String indent, mlnode h)
   {
      String ret = new String ();
      ret += indent + "<" + h.data + ">\n";   // we remember how much to indent
      for (int i = 0; i < h.size(); i++)      // so that everything can be
         if (h.get(i).isflag(mlnode.TAG))     // printed nicely
            ret += _to_ml (indent + "   ", h.get(i));  // recurse!
         else ret += "   " + indent + h.get(i).data + "\n";
      ret += indent + "</" + h.data + ">\n";  // print close tag
      return ret;
   }
}

