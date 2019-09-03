package Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * Example 1: Input: 2, [[1,0]]; Output: true
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2: Input: 2, [[1,0],[0,1]] Output: false
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * Explanation and Code from: https://leetcode.com/problems/course-schedule/discuss/58516/Easy-BFS-Topological-sort-Java
 * https://www.cnblogs.com/yrbbest/p/4493547.html
 * O(V+E)
 */

public class CourseSchedule {

	/*
	 * As suggested by the hints, this problem is equivalent to detecting a cycle in the graph represented by prerequisites. Both BFS and DFS can be used to
	 * solve it using the idea of topological sort. If you find yourself unfamiliar with these concepts, you may refer to their wikipedia pages. Specifically, 
	 * you may only need to refer to the link in the third hint to solve this problem.
	 * BFS:
	 * BFS uses the in degrees of each node. We will first try to find a node with 0 indegree. If we fail to do so, there must be a cycle in the graph and we
	 * return false. Otherwise we have found one. We set its indegree to be -1 to prevent from visiting it again and reduce the indegrees of all its neighbors by 1.
	 * This process will be repeated for n (number of nodes) times. If we have not returned false, we will return true.
	 */
    public static boolean canFinish(int numCourses, int[][] prerequisites) { // Kahn's Algorithm
    	System.out.println("numCourses: "+numCourses+" prerequisites: "+prerequisites[0]);
    	
    	if(numCourses <= 0) {
            return false;
    	}
    	
        if(prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for(int[] pair: prerequisites) {
        	System.out.println("pair: "+Arrays.toString(pair)+" pair[0]: "+pair[0]);
        	indegree[pair[0]]++;
        	System.out.println("indegree[pair[1]]: "+indegree[pair[1]]);
        }
        System.out.println("indegree: "+Arrays.toString(indegree));
        
        for(int i=0; i<indegree.length; i++) {
        	System.out.println("i: "+i+" indegree[i]: "+indegree[i]+" queue: "+queue);
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        System.out.println("queue: "+queue);
        
        while(!queue.isEmpty()) {
        	System.out.println("queue: "+queue);
        	
        	numCourses--;
            int course = queue.poll();
            
            System.out.println("numCourses: "+numCourses+" course: "+course);
            
            for(int[] pair: prerequisites) {
            	System.out.println("pair: "+Arrays.toString(pair));
                
            	if(pair[1] == course) {
            		System.out.println("indegree[pair[0]]: "+indegree[pair[0]]);
            		
                    indegree[pair[0]]--;
                    
                    if(indegree[pair[0]] == 0) {
                        queue.add(pair[0]);
                    }
                }
            }
        }
        
        System.out.println("queue: "+queue+" numCourses: "+numCourses);
        return numCourses == 0;
    }
	
    //Refer this: similar to Course Schedule 2
    public static boolean findOrder(int numCourses, int[][] prerequisites) {
    	System.out.println("numCourses: "+numCourses+" prerequisites: "+prerequisites[0]);
    	
    	if(numCourses <= 0) {
            return false;
    	}
    	
        if(prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for(int[] pair: prerequisites) {
        	System.out.println("pair: "+Arrays.toString(pair)+" pair[0]: "+pair[0]);
        	indegree[pair[1]]++;
        	System.out.println("indegree[pair[1]]: "+indegree[pair[1]]);
        }
        System.out.println("indegree: "+Arrays.toString(indegree));
        
        for(int i=0; i<indegree.length; i++) {
        	System.out.println("i: "+i+" indegree[i]: "+indegree[i]+" queue: "+queue);
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        System.out.println("queue: "+queue);
        
        while(!queue.isEmpty()) {
        	System.out.println("queue: "+queue);
        	
        	numCourses--;
            int course = queue.poll();
            
            System.out.println("numCourses: "+numCourses+" course: "+course);
            
            for(int[] pair: prerequisites) {
            	System.out.println("pair: "+Arrays.toString(pair));
                
            	if(pair[0] == course) {
            		System.out.println("indegree[pair[1]]: "+indegree[pair[1]]);
            		
                    indegree[pair[1]]--;
                    
                    if(indegree[pair[1]] == 0) {
                        queue.add(pair[1]);
                    }
                }
            }
        }
        
        System.out.println("queue: "+queue+" numCourses: "+numCourses);
        return numCourses == 0;
    }
    
	public static void main(String[] args) {
		int numCourses = 4;
		int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}}; //{{1,0},{2,1},{3,2}};
		
		for(int i=0; i<prerequisites.length; i++) {
			for(int j=0; j<prerequisites[0].length; j++) {
				System.out.print(" "+prerequisites[i][j]);
			}
			System.out.println();
		}
		
		System.out.println(findOrder(numCourses, prerequisites));
	}
}