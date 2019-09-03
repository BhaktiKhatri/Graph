package Graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import Graph.Graph;

class Graph 
{ 
    public static int V;   // No. of vertices 
    public static LinkedList<Integer>[] adj; // Adjacency List 
  
    //Constructor 
    public Graph(int v) 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        
        for(int i=0; i<v; ++i) { 
            adj[i] = new LinkedList();
        }
    } 
    
    void addEdge(int v,int w) { 
    	adj[v].add(w); 
    } 
}

/*
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, 
 * vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
 * There can be more than one topological sorting for a graph.
 * Time Complexity: The above algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E).
 * Topological sort using BFS can be done by Kahn's algorithm: https://www.youtube.com/watch?v=iI4HhxTZWO8
 * https://www.youtube.com/watch?v=tFpvX8T0-Pw
 * Find vertices that have no incoming egdes and remove its all outgoing edges
 * https://www.techiedelight.com/kahn-topological-sort-algorithm/
 */

public class TopologicalSort {

	// A recursive function used by topologicalSort 
    public static void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) 
    { 
    	System.out.println("v: "+v+" visited: "+Arrays.toString(visited)+" stack: "+stack);
    	
        // Mark the current node as visited. 
        visited[v] = true; 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> it = Graph.adj[v].iterator();
        System.out.println("it: "+it+" adj[v]: "+Graph.adj[v]);
        
        while(it.hasNext()) 
        { 
            int i = it.next(); 
            System.out.println("i: "+i+" visited[i]: "+visited[i]);
            
            if(!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        } 
        System.out.println("stack: "+stack);
  
        // Push current vertex to stack which stores result 
        stack.push(new Integer(v)); 
     }
  
    // The function to do Topological Sort. It uses recursive topologicalSortUtil() 
    //Topological sort using DFS
    public static void topologicalSort() 
    { 
        Stack<Integer> stack = new Stack<Integer>(); 
        boolean visited[] = new boolean[Graph.V];	// Mark all the vertices as not visited 
        
        // Call the recursive helper function to store Topological Sort starting from all vertices one by one 
        for(int i=0; i<Graph.V; i++) {
        	System.out.println("i: "+i+" visited[i]: "+visited[i]);
        	
            if(visited[i] == false) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        System.out.println("stack: "+stack);
        
        // Print contents of stack 
        while(!stack.empty()) { 
            System.out.print(stack.pop() + " ");
        }
    } 
  
    // Driver method 
    public static void main(String args[]) 
    { 
        // Create a graph given in the above diagram 
        Graph g = new Graph(6); 
        g.addEdge(5, 2); 
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
  
        System.out.println("Following is a Topological " + "sort of the given graph"); 
        topologicalSort(); 
    } 
}
