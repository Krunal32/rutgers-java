// russ's graph class

import java.util.*;
import java.io.*;

public class Graph
{
   private HashMap <String, Node> myNodes;
   private Vector <Node> myNodesArray;

   public static final int INF = 999999999;

   public Graph ()
   {
      myNodesArray = new Vector<Node> ();
      myNodes = new HashMap<String, Node>();
   }

   public Graph (String filename)
   throws java.io.FileNotFoundException
   {
      myNodesArray = new Vector<Node> ();
      myNodes = new HashMap<String, Node>();
      Parse (filename);
   }

   // russ's parsing function
   private void Parse (String filename)
   throws java.io.FileNotFoundException
   {
      String name;
      String school;
      String tok;
      Scanner sc = new Scanner (new File(filename)).useDelimiter("\n");
      tok = sc.next();
      int num = Integer.parseInt(tok);
      for (int i = 0; i < num; i++)
      {
         school = null;
         tok = sc.next();
         String[] toks = tok.split ("\\|");
         name = toks[0];
         if (toks[1].equals("y")) school = toks[2];
         Node n = new Node (name,school);
         n.id = myNodesArray.size();
         myNodes.put (name, n);
         myNodesArray.add (n);
      }
      String from;
      String to;
      while (sc.hasNext())
      {
         tok = sc.next();
         String[] toks = tok.split ("\\|");
         Node fromnode = myNodes.get(toks[0]);
         Node tonode = myNodes.get(toks[1]);
         fromnode.Add(tonode);
         tonode.Add(fromnode);
      }
   }

   // russ's subgraph finder
   // naive algorithm that will return an empty graph if
   // input school doesn't exist
   public Graph SubGraph (String school)
   {
      Node n;
      Graph ret = new Graph ();
      Iterator it = myNodes.entrySet().iterator();
      while (it.hasNext())
      {
         Map.Entry pair = (Map.Entry) it.next();
         n = (Node) pair.getValue();
         if (n.GetSchool() == null) continue;
         if (n.GetSchool().equals(school))
            ret.Add (new Node(n));
      }
      it = ret.GetIterator();
      while (it.hasNext())
      {
         Map.Entry pair = (Map.Entry) it.next();
         n = (Node) pair.getValue();
         Iterator it2 = n.myEdges.iterator();
         Vector <Node> NewEdges = new Vector <Node> ();
         while (it2.hasNext())
         {
            Node next = (Node) it2.next();
            if (next.GetSchool() != null)
            {
               if (!next.GetSchool().equals(school))
                  it2.remove();
               else
                  NewEdges.add (ret.GetNode(next.GetName()));
            }
            else
               it2.remove();
         }
         n.myEdges = NewEdges;
      }
      return ret;
   }

   // min finding helper function with a punny name
   // written by russ
   private int Mindex (Vector<Integer> in)
   {
      int min = INF;
      int index = 0;
      for (int i = 0; i < in.size(); i++)
      {
         if (myNodesArray.get(i).t == 0) continue;
         if (in.get(i) < min)
         {
            min = in.get(i);
            index = i;
         }
      }
      return index;
   }

   // russ's dijkstra's alg implementation
   // with much help from wikipedia
   // returns null if no path
   public Vector<Node> LeastPath (String src, String dest)
   {
      int done = myNodesArray.size();
      Vector <Integer> dist = new Vector <Integer> (myNodesArray.size());
      Vector <Node> prev = new Vector <Node> (myNodesArray.size());
      for (int i = 0; i < myNodesArray.size(); i++)
      {
         myNodesArray.get(i).t = 1;
         dist.add (INF);
         prev.add (null);
      }
      dist.set (myNodes.get(src).id, 0);
      while (done > 0)
      {
         done--;
         int u = Mindex (dist);
         if (dist.get(u) == INF) break; // not found
         Node n = myNodesArray.get(u);
         n.t = 0;
         Iterator it = n.myEdges.iterator();
         while (it.hasNext())
         {
            Node v = (Node) it.next();
            int alt = dist.get(u) + 1;
            if (alt < dist.get(v.id))
            {
               dist.set(v.id, alt);
               prev.set(v.id, n);
            }
         }
      }
      Vector <Node> ret = new Vector <Node> ();
      Node t = myNodes.get(dest);
      while (prev.get(t.id) != null)
      {
         ret.insertElementAt(t, 0);
         t = prev.get(t.id);
      }
      if (ret.size() == 0) return null;
      ret.insertElementAt(myNodes.get(src), 0);
      return ret;
   }

   public Vector<Graph> islands()
   {
	   return islands(null);
   }
   
   private void islands (Graph add_to, Node check)
   {
	   for (int i = 0; i < check.myEdges.size(); i++)
	   {
		   if (!add_to.contains(check.myEdges.get(i)))
		   {
			   add_to.Add(check.myEdges.get(i));
			   this.islands(add_to, check.myEdges.get(i));
		   }
	   }
   }
   
   public Vector<Graph> islands (String school)
   {
	   Vector<Graph> islands = new Vector<Graph>(); // this will contain other Graphs
	   
	   // we first need to get a subgraph for the school
	   // as given by design specifications
	   
	   Graph school_queue = null;
	   
	   if (school == null)
		   school_queue = this;
	   else
		   school_queue = this.SubGraph(school);
	   
	   Vector<Node> master_list = new Vector<Node>();
	   
	   for (int j = 0; j < school_queue.myNodesArray.size(); j++)
	   {
		   Node node = school_queue.myNodesArray.get(j);
		   
		   if (master_list.contains(node))
			   continue;
		   
		   Graph graph = new Graph();
		   graph.Add(node);
		   this.islands(graph, node);
		   islands.add(graph);
		   
		   for (int k = 0; k < graph.myNodesArray.size(); k++)
			   master_list.add(graph.myNodesArray.get(k));
	   }
		   
	   return islands;
   }
   
   private int dfsGetMin(int node1, int node2)
   {
	   if (node1 > node2)
		   return node2;
	   else
		   return node1;
   }
   
   private Vector<String> connectors(Node graph_node, HashMap<Integer, DFSNode> dfs_nodes, boolean parent_recurse)
   {
	   Vector<String> connectors = new Vector<String>();
	   
	   DFSNode new_node = new DFSNode(graph_node, dfs_nodes.size()+1, dfs_nodes.size()+1);
	   
	   if (parent_recurse)
	   {
		   new_node.starting_node = true;
	   }
	   
	   dfs_nodes.put(graph_node.id, new_node);
	   
	   for (int i = 0; i < graph_node.myEdges.size(); i++)
	   {
		   if (dfs_nodes.containsKey(graph_node.myEdges.get(i).id))
		   {
			   
			   dfs_nodes.get(graph_node.id).back_num = dfsGetMin(dfs_nodes.get(graph_node.id).back_num, dfs_nodes.get(graph_node.myEdges.get(i).id).dfs_num);
			   continue;
		   }
		   else
		   {
			   connectors.addAll(this.connectors(graph_node.myEdges.get(i), dfs_nodes, false));
		   }
		   
		   // if my current DFS num is less than or equal to the for loop's back num
		   if (dfs_nodes.get(graph_node.id).dfs_num > dfs_nodes.get(graph_node.myEdges.get(i).id).back_num)
			   dfs_nodes.get(graph_node.id).back_num = dfsGetMin(dfs_nodes.get(graph_node.id).back_num, dfs_nodes.get(graph_node.myEdges.get(i).id).back_num);
		   else if (dfs_nodes.get(graph_node.id).dfs_num <= dfs_nodes.get(graph_node.myEdges.get(i).id).back_num)
		   {
			   if (dfs_nodes.get(graph_node.id).starting_node)
			   {
				   dfs_nodes.get(graph_node.id).starting_node = false;
			   }
			   else
			   {
				   if (!connectors.contains(dfs_nodes.get(graph_node.id).getName()))
					   connectors.add(dfs_nodes.get(graph_node.id).getName());
			   }
		   }
	   }
	   
	   return connectors;
   }
   
   public Vector<String> connectors()
   {
	   // i believe in order to do this problem
	   // we need to maintain a hashmap
	   // and a queue (or a linked list)
	   // we will maintain what is in the queue
	   // in order to see how to step back
	   // and the hashmap will contain
	   // the id (of the node) with the proper
	   // DFSNode values (of back, etc)...
	   Vector<String> connectors = new Vector<String>();
	   
	   Vector<Graph> sub_graphs = this.islands();
	   
	   Iterator<Graph> graph_iter = sub_graphs.iterator();
	   
	   while (graph_iter.hasNext())
	   {
		   Graph graph = graph_iter.next();
		   connectors.addAll(connectors(graph.myNodesArray.get(0), new HashMap<Integer, DFSNode>(), true));
	   }
	   
	   return connectors;
   }
   
   public Node find(String id)
   {
	   for (int i = 0; i < this.myNodesArray.size(); i++)
	   {
		   if (this.myNodesArray.get(i).GetName().equals(id))
			   return this.myNodesArray.get(i);
	   }
	   
	   return null;
   }

   public boolean contains(Node needle)
   {
	   for (int i = 0; i < this.myNodesArray.size(); i++)
	   {
		   if (this.myNodesArray.get(i).equals(needle))
			   return true;
	   }
	   
	   return false;
   }
   
   public Iterator GetIterator ()
   {
      return myNodes.entrySet().iterator();
   }

   public void Add (Node n)
   {
	  n.id = myNodesArray.size();
      myNodes.put (n.GetName(), n);
      myNodesArray.add (n);
   }

   public Node GetNode (String n)
   {
      return myNodes.get (n);
   }

   public String toString ()
   {
      String ret = "Graph object: \n";
      Iterator it = myNodes.entrySet().iterator();
      while (it.hasNext())
      {
         Map.Entry pair = (Map.Entry) it.next();
         ret += "\t" + ((Node) pair.getValue()).toString() + "\n";
      }
      return ret;
   }
}

