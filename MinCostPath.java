package DynamicProgramming;

/*
Given a cost matrix cost[][] and a position (m, n) in cost[][], write a
function that returns cost of minimum cost path to reach (m, n) from (0, 0).
Each cell of the matrix represents a cost to traverse through that cell.
Total cost of a path to reach (m, n) is sum of all the costs on that path
(including both source and destination). You can only traverse down, right and
diagonally lower cells from a given cell, i.e., from a given cell (i, j), cells
(i+1, j), (i, j+1) and (i+1, j+1) can be traversed.
You may assume that all costs are positive integers.

Solution: minCost(m,n)=min(minCost(m-1,n-1), minCost(m-1,n), minCost(m,n-1)) + cost[m][n]
*/

public class MinCostPath {
    /*
    Native recursive implementation
    Time complexity: O(3^n)
     */
    public static int minCost(int[][] cost) {
        if (cost == null || cost.length == 0 || cost[0].length == 0)
            return Integer.MAX_VALUE;

        //focus that the boundary of cost[][]
        int m = cost.length - 1;
        int n = cost[0].length - 1;

        return minCost(cost, m, n);
    }

    public static int minCost(int[][] cost, int m, int n) {
        if (m < 0 || n < 0)
            return Integer.MAX_VALUE;
        else if (m == 0 && n == 0)
            return cost[m][n];
        else
            return Math.min(minCost(cost, m - 1, n - 1),
                    Math.min(minCost(cost, m - 1, n), minCost(cost, m, n - 1)))
                    + cost[m][n];
    }


    /*
    DP solution
    Time Complexity: O(mn)
     */
    public static int minCostBetter(int[][] cost) {
        if (cost == null || cost.length == 0 || cost[0].length == 0)
            return Integer.MAX_VALUE;

        int m = cost.length;
        int n = cost[0].length;
        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 || j == 0)
                    dp[i][j] =  Integer.MAX_VALUE;
                else if(i == 1 && j == 1)
                    dp[i][j] = cost[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + cost[i-1][j-1];
            }
        }
        return dp[m][n];
    }



    public static void main(String[] args) {
        int[][] cost = {{1, 2, 1},
                        {4, 8, 2},
                        {1, 5, 3}};

        System.out.println("Native recursive implementation: " + minCost(cost));
        System.out.println("Dynamic Programming solution: : " + minCostBetter(cost));
    }
}
