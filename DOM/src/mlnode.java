import java.util.Vector;

// node for ml tree

public class mlnode implements Comparable
{
   public static final int TAG   =  1;
   public static final int TEXT  =  2;   // for flags bitfield
   public static final int CLOSE =  4;

   public String data;
   public int flags;
   public Vector <mlnode> children;

   public boolean equals (String sometag)
   {
      return data.equals(sometag);
   }

   public mlnode (String newdata, int newflags)
   {
      data = newdata;
      flags = newflags;
      children = new Vector <mlnode> ();
   }

   public void add (mlnode n)
   {
      if (n == null) return;
      children.add (n);
   }

   public void add (Vector <mlnode> n)
   {
      if (n == null) return;
      children.addAll (n);
   }

   public String toString ()
   {
      return "mlnode: " + data + " flags: " + flags;
   }

   public void flag (int val)
   {
      flags |= val;
   }

   public boolean isflag (int val)
   {
      return (flags & val) == val;
   }

   public mlnode get (int index)
   {
      return children.get(index);
   }

   public int size ()
   {
      return children.size();
   }

   public int compareTo (Object other)
   {
      if (this.data.equals (((mlnode) other).data) &&
          this.flags == ((mlnode) other).flags)
         return 0;
      return 1;
   }
}

