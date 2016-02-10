package DynamicProgramming;/*

Given a matrix where every cell has some number of coins. Count number of ways
to reach bottom right from top left with exactly k coins. We can move to (i+1, j)
and (i, j+1) from a cell (i, j).

Example:

Input:  k = 12
        mat[][] = { {1, 2, 3},
                    {4, 6, 5},
                    {3, 2, 1}
                  };
Output:  2
There are two paths with 12 coins
1 -> 2 -> 6 -> 2 -> 1
1 -> 2 -> 3 -> 5 -> 1
 */

public class PathCount {
    //normal recursive method
    public static int pathCount(int[][] mat, int k) {
        if(mat == null)
            return 0;
        if(mat.length == 0 && mat[0].length == 0) {
            if(mat[0][0] == k)
                return 1;
            else
                return 0;
        }

        return pathCount(mat, mat.length - 1, mat[0].length - 1, k);
    }

    public static int pathCount(int[][] mat, int m, int n, int k) {
        if(m < 0 || n < 0)
            return 0;
        if(m == 0 && n == 0) {
            if(mat[m][n] == k)
                return 1;
            else
                return 0;
        }

        return pathCount(mat, m-1, n, k-mat[m][n]) + pathCount(mat, m, n-1, k-mat[m][n]);
    }


    /*
    The idea above is using recursive but the time complexity is not good, since we are
    calculating the previous pathCount again and again. We should store them.
     */
    static int[][][] dp;

    public static int pathCountBetter(int[][] mat, int k) {
        return pathCountBetter(mat, mat.length-1, mat[0].length-1, k);
    }

    public static int pathCountBetter(int[][] mat, int m, int n, int k) {
        if(m < 0 || n < 0 || k == 0)
            return 0;
        if(m == 0 && n == 0) {
            if(mat[m][n] == k)
                return 1;
            else
                return 0;
        }

        if(dp[m][n][k-1] != -1)
            return dp[m][n][k-1];

        dp[m][n][k-1] = pathCountBetter(mat, m-1, n, k-mat[m][n]) + pathCountBetter(mat, m, n-1, k-mat[m][n]);

        return dp[m][n][k-1];
    }





    public static void main(String[] args) {
        int k = 12;
        int mat[][] = {
                {1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };
        dp = new int[mat.length][mat[0].length][k];
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                for(int l = 0; l < k; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        int count = pathCount(mat, k);
        int countBetter = pathCountBetter(mat, k);

        System.out.println(count);
        System.out.println(countBetter);
    }
}
