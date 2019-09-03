package Graph;

import java.util.Arrays;

/*
 * 743. Network Delay Time
 * https://leetcode.com/problems/network-delay-time/
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, 
 * and w is the time it takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 * Explanation and Code from: @wfei26 https://leetcode.com/problems/network-delay-time/discuss/109982/C%2B%2B-Bellman-Ford
 * Complexity is O(VE), but the problem said N will be no more than 100, so the complexity should be O(E)
 */

public class NetworkDelayTime {

	public static int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0) {
        	return -1;
        }
        
        System.out.println("N: "+N+" K: "+K);

	    /* Subproblem: dp(i) represents minimum distance from K to i (iteratively update dp(i) when we find another shorter distance from K to i)*/
	    int[] dp = new int[N + 1];
	
	    /* Base case 1: initialize MAX value as initial minimum distance from K to every point*/
	    /* Base case 2: initialize 0 as min distance to K itself*/
	    for(int i=0; i<N + 1; i++) {
	        dp[i] = Integer.MAX_VALUE;
	    }
	    dp[K] = 0;
	    
	    System.out.println("dp: "+Arrays.toString(dp));
	
	    /* traverse all destinations*/
	    for(int i=0; i<N; i++) {
	        for(int[] edge: times) {
	            System.out.println("i: "+i+" edge: "+Arrays.toString(edge));
	        	
	        	int u = edge[0];
	            int v = edge[1];
	            int w = edge[2];
	
	            System.out.println("u: "+u+" v: "+v+" w: "+w);
	            System.out.println("dp: "+Arrays.toString(dp));
	            System.out.println("dp[v]: "+dp[v]+" dp[u]: "+dp[u]+" w: "+w);
	            
	            /* if dp[u] (starting point of current edge) has already been visited, and new distance from u to v is smaller than previous saved distance
	            we should update minimum distance from u to v*/
	            if (dp[u] != Integer.MAX_VALUE && dp[v] > dp[u] + w) {
	                dp[v] = dp[u] + w;
	            }
	            System.out.println("dp[v]: "+dp[v]+" dp[u]: "+dp[u]+" w: "+w);
	        }
	    }
	
	    System.out.println("dp: "+Arrays.toString(dp));
	    
	    /* after getting minimum distance (travel time) to all points, we should retrieve the max distance from these minimum distance, 
	     * to ensure that we can travel all points in a specific travel time
	    */
	    int result = 0;
	    for(int i=1; i<=N; i++) {
	        result = Math.max(result, dp[i]);
	    }
	    System.out.println("result: "+result);
	    
	    /* if we do not visit all points, we should return -1*/
	    return result == Integer.MAX_VALUE ? -1 : result;
    }
	
	public static void main(String[] args) {
		int[][] times = {{1,2,1},{2,3,7},{1,3,4},{2,1,2}}; //{{2,1,1},{2,3,1},{3,4,1}};
		int N = 3; //4;
		int K = 2; //2;
		
		printTimes(times);
		System.out.println(networkDelayTime(times, N, K));
	}
	
	public static void printTimes(int[][] times) {
		for(int i=0; i<times.length; i++) {
			for(int j=0; j<times[0].length; j++) {
				System.out.print(" "+times[i][j]);
			}
			System.out.println();
		}
	}
}