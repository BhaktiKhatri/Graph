package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * 785. Is Graph Bipartite?
 * https://leetcode.com/problems/is-graph-bipartite/
 * Given an undirected graph, return true if and only if it is bipartite.
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * Example 1: Input: [[1,3], [0,2], [1,3], [0,2]]; Output: true
 * Explanation: The graph looks like this:
 * 	0----1
 * 	|    |
 * 	|    |
 * 	3----2
 * 	We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2: Input: [[1,2,3], [0,2], [0,1,3], [0,2]]; Output: false
 * Explanation: The graph looks like this:
 * 	0----1
 * 	| \  |
 * 	|  \ |
 * 	3----2
 * 	We cannot find a way to divide the set of nodes into two independent subsets.
 * Note: graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 * Explanation and Code from: @FLAGbigoffer https://leetcode.com/problems/is-graph-bipartite/discuss/115487/Java-Clean-DFS-solution-with-Explanation
 * https://www.youtube.com/watch?v=Gmp51E8HVVs
 * Time Complexity: O(N + E), where N is the number of nodes in the graph, and EE is the number of edges. We explore each node once when we transform
 * it from uncolored to colored, traversing all its edges in the process.
 * Space Complexity: O(N), the space used to store the color.
 */

public class IsGraphBipartile {

	public static void printGraph(int[][] graph) {
		for(int i=0; i<graph.length; i++) {
			for(int j=0; j<graph[0].length; j++) {
				System.out.print(" "+graph[i][j]);
			}
			System.out.println();
		}
	}
	
	//DFS
    public static boolean isBipartite(int[][] graph) {
    	printGraph(graph);
        int n = graph.length;
        int[] colors = new int[n];			
			
        System.out.println("n: "+n);
        for(int i=0; i<n; i++) {              //This graph might be a disconnected graph. So check each unvisited node.
        	System.out.println("i: "+i+" colors[i]: "+colors[i]);
            if(colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean validColor(int[][] graph, int[] colors, int color, int node) {
    	System.out.println("colors: "+Arrays.toString(colors)+" colors[node]: "+colors[node]+" color: "+color+" node: "+node);
        
    	if(colors[node] != 0) {
            return colors[node] == color;
        }
    	
        colors[node] = color;       
        System.out.println("colors: "+Arrays.toString(colors)+" colors[node]: "+colors[node]+" graph[node]: "+graph[node]);
        
        for(int next: graph[node]) {
        	System.out.println("next: "+next);
            
        	if(!validColor(graph, colors, -color, next)) {
                return false;
            }
        }
        return true;
    }
    
    /*
     * Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
     * Initialize a color[] array for each node. Here are three states for colors[] array:
     * 0: Haven't been colored yet.
     * 1: Blue.
     * -1: Red.
     * For each node,
     * If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
     * If it has been colored, check if the current color is the same as the color that is going to be used to color it. (Please forgive my english... Hope you can understand it.)
     */
    //BFS
    //{{1,3}, {0,2}, {1,3}, {0,2}}
    public static boolean isBipartiteBFS(int[][] graph) {
        int[] colors = new int[graph.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for(int i=0; i<graph.length; i++) {
        	System.out.println("i: "+i+" colors[i]: "+colors[i]);
            
        	if(colors[i] != 0) 
            	continue;
        	
            queue.offer(i);
            colors[i] = 1;   // Blue: 1; Red: -1.
            
            System.out.println("queue: "+queue+" colors[i]: "+colors[i]);
            
            while(!queue.isEmpty()) {
            	System.out.println("queue: "+queue);
            	
                int cur = queue.poll();
                System.out.println("queue: "+queue+" cur: "+cur+" graph[cur]: "+graph[cur]);
                
                for(int next: graph[cur]) {
                	System.out.println("next: "+next+" colors[next]: "+colors[next]+" colors[cur]: "+colors[cur]+" colors: "+Arrays.toString(colors));
                    
                	if(colors[next] == 0) {          // If this node hasn't been colored;
                        colors[next] = -colors[cur];  // Color it with a different color;
                        queue.offer(next);
                    } 
                	else if(colors[next] == colors[cur]) {   // If it is colored and its color is same as cur, return false;
                        return false;
                    }
                }
            }
        }
        return true;
    }

	public static void main(String[] args) {
		int[][] graph = {{1,3}, {0,2}, {1,3}, {0,2}};
		//int[][] graph = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
		print(graph);
		System.out.println(isBipartiteBFS(graph));
	}
	
	public static void print(int[][] graph) {
		for(int i=0; i<graph.length; i++) {
			for(int j=0; j<graph[i].length; j++) {
				System.out.print(" "+graph[i][j]);
			}
			System.out.println();
		}
	}
}