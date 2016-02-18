package DynamicProgramming;

/*
Given an array p[] which represents the chain of matrices such that
the ith matrix Ai is of dimension p[i-1] x p[i]. We need to write a
function MatrixChainOrder() that should return the minimum number of
multiplications needed to multiply the chain.

Example:

  Input: p[] = {40, 20, 30, 10, 30}
  Output: 26000
  There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
  Let the input 4 matrices be A, B, C and D.  The minimum number of
  multiplications are obtained by putting parenthesis in following way
  (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30

  Input: p[] = {10, 20, 30, 40, 30}
  Output: 30000
  There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30.
  Let the input 4 matrices be A, B, C and D.  The minimum number of
  multiplications are obtained by putting parenthesis in following way
  ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30

 */
public class MatrixChainMultiplication {
    /*
    Native recursive solution
     */
    public static int matrixChainOrder(int[] p) {
        int i = 1;
        int j = p.length - 1;

        return matrixChainOrder(p, i, j);
    }

    public static int matrixChainOrder(int[] p, int i, int j) {
        if(i == j)
            return 0;

        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++) {
            int count = matrixChainOrder(p, i, k) + matrixChainOrder(p, k+1, j) + p[i-1]*p[k]*p[j];

            if(count < min)
                min = count;
        }

        return min;
    }


    /*
    DP solution
     */
    public static int matrixChainOrderBetter(int[] p) {
        int n = p.length;
        int[][] dp = new int[n][n];

        //l is the chain length
        for(int l = 2; l < n; l++) {
            for(int i = 1; i < n-l+1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    int count = dp[i][k] + dp[k+1][j] + p[i-1]*p[k]*p[j];
                    if(count < dp[i][j])
                        dp[i][j] = count;
                }
            }
        }
        return dp[1][n-1];
    }



    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3};
        System.out.println("Native recursive implementation: " + matrixChainOrder(arr));
        System.out.println("Dynamic Programming solution: : " + matrixChainOrderBetter(arr));
    }
}
