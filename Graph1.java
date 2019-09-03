package Graph;

import java.io.*; 
import java.util.*; 

public class Graph1 
{ 
    public int V;   // No. of vertices 
    public LinkedList<Integer> adj[]; // Adjacency List 
  
    //Constructor 
    public Graph1(int v) 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        
        for(int i=0; i<v; ++i) { 
            adj[i] = new LinkedList();
        }
    } 
    
    public void addEdge(int v,int w) { 
    	adj[v].add(w); 
    } 
}